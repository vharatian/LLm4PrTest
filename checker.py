#!/usr/bin/env python3
"""
Pipeline: Generate EvoSuite tests from PR code (merge), then evaluate on:
  • MERGE (after PR changes)
  • BASE  (before PR changes)
No JaCoCo. Keeps human tests. One aggregate JSON written per PR.

Expected input JSON (javaparser_javaparser.json):
[
  {
    "pr_number": 4792,
    "merge_commit": "862450e...",
    "base_commit": "2789df2...",
    "files": [
      {"path": "javaparser-core/src/main/java/com/github/..../Foo.java"},
      "javaparser-symbol-solver-core/src/main/java/....../Bar.java"
    ]
  },
  ...
]
"""

import os
import re
import json
import glob
import shutil
import datetime
import subprocess
import xml.etree.ElementTree as ET
from pathlib import Path
from typing import Dict, List, Tuple, Optional

from git import Repo

# --------------------------- Config ----------------------------
REPO_URL         = "https://github.com/javaparser/javaparser.git"
PROJECT_NAME     = "javaparser"
LOCAL_REPO_PATH  = PROJECT_NAME
PARENT_POM       = os.path.join(LOCAL_REPO_PATH, "pom.xml")
PR_LIST_JSON     = "javaparser_javaparser.json"

EVOSUITE_JAR     = "evosuite.jar"          # <-- set this to your EvoSuite jar
EVOSUITE_RUNTIME_VERSION = "1.0.6"         # test-scope dep to run generated tests

REPORTS_DIR      = "compare_reports"
LOGS_DIR         = "build_logs"
GEN_TESTS_DIR    = "generated_tests"       # where we stash generated tests per PR
EVOSUITE_LOG_DIR = "evosuite_logs"
AGGREGATE_REPORT = os.path.join(REPORTS_DIR, "reports.json")

NAMESPACE = {"mvn": "http://maven.apache.org/POM/4.0.0"}
ET.register_namespace("", NAMESPACE["mvn"])

os.makedirs(REPORTS_DIR, exist_ok=True)
os.makedirs(LOGS_DIR, exist_ok=True)
os.makedirs(GEN_TESTS_DIR, exist_ok=True)
os.makedirs(EVOSUITE_LOG_DIR, exist_ok=True)
TEST_SELECTOR = "*ESTest,*ES_Test,!*_scaffolding"


# ---------------------- small helpers --------------------------
def run(cmd: List[str], cwd: Optional[str] = None, capture: bool = False) -> subprocess.CompletedProcess:
    print(">", *cmd, flush=True)
    if capture:
        return subprocess.run(cmd, cwd=cwd, text=True, stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
    return subprocess.run(cmd, cwd=cwd, text=True, check=True)

def clone_repo() -> Repo:
    if not os.path.exists(LOCAL_REPO_PATH):
        Repo.clone_from(REPO_URL, LOCAL_REPO_PATH)
    return Repo(LOCAL_REPO_PATH)

def git_checkout(repo: Repo, ref: str):
    # hard reset to avoid dirty state and ensure deterministic files
    repo.git.reset("--hard")
    repo.git.clean("-fdx")
    repo.git.checkout(ref)

def list_modules() -> List[str]:
    """Return <modules> from parent POM (top-level module names)."""
    tree = ET.parse(PARENT_POM)
    root = tree.getroot()
    mods_el = root.find("mvn:modules", NAMESPACE)
    if not mods_el:
        return []
    return [m.text for m in mods_el.findall("mvn:module", NAMESPACE) if m.text]

def module_src_main(module: str) -> str:
    return os.path.join(LOCAL_REPO_PATH, module, "src", "main", "java")

def module_test_dir(module: str) -> str:
    return os.path.join(LOCAL_REPO_PATH, module, "src", "test", "java")

def rm_tree(path: str):
    shutil.rmtree(path, ignore_errors=True)

def copy_tree(src: str, dst: str):
    if not os.path.exists(src):
        return
    for root, _, files in os.walk(src):
        rel = os.path.relpath(root, src)
        target_dir = os.path.join(dst, "" if rel == "." else rel)
        os.makedirs(target_dir, exist_ok=True)
        for f in files:
            shutil.copy2(os.path.join(root, f), os.path.join(target_dir, f))

def merge_tree(src: str, dest: str):
    if not os.path.exists(src):
        return
    for root, _, files in os.walk(src):
        rel = os.path.relpath(root, src)
        tgt_dir = os.path.join(dest, rel) if rel != "." else dest
        os.makedirs(tgt_dir, exist_ok=True)
        for f in files:
            shutil.copy2(os.path.join(root, f), os.path.join(tgt_dir, f))

# ------------- POM patch: ensure test deps to run EvoSuite tests -------------
def ensure_test_deps_in_parent_pom():
    """
    Minimal patch: ensure evosuite runtime + junit4 test deps in parent POM.
    Does NOT touch surefire or jacoco.
    """
    tree = ET.parse(PARENT_POM)
    root = tree.getroot()
    def fn(p, t): return p.find(f"mvn:{t}", NAMESPACE)
    deps = fn(root, "dependencies") or ET.SubElement(root, "dependencies")

    def dep_exists(g, a):
        for d in deps.findall("mvn:dependency", NAMESPACE):
            gid = d.find("mvn:groupId", NAMESPACE)
            aid = d.find("mvn:artifactId", NAMESPACE)
            if gid is not None and aid is not None and gid.text == g and aid.text == a:
                return True
        return False

    changed = False
    if not dep_exists("org.evosuite", "evosuite-standalone-runtime"):
        d = ET.SubElement(deps, "dependency")
        ET.SubElement(d, "groupId").text = "org.evosuite"
        ET.SubElement(d, "artifactId").text = "evosuite-standalone-runtime"
        ET.SubElement(d, "version").text = EVOSUITE_RUNTIME_VERSION
        ET.SubElement(d, "scope").text = "test"
        changed = True

    if not dep_exists("junit", "junit"):
        d = ET.SubElement(deps, "dependency")
        ET.SubElement(d, "groupId").text = "junit"
        ET.SubElement(d, "artifactId").text = "junit"
        ET.SubElement(d, "version").text = "4.13.2"
        ET.SubElement(d, "scope").text = "test"
        changed = True

    if changed:
        tree.write(PARENT_POM, encoding="utf-8", xml_declaration=True)
        print("[POM] Added missing test deps (EvoSuite runtime and/or JUnit4).")
    else:
        print("[POM] Test deps already present; no changes.")

# ----------------- Maven build & parsing -----------------------
ERROR_PATH_RE = re.compile(r"^\[ERROR\]\s+(/.*?\.java):\[(\d+),(\d+)\]\s+(.*)$")
ERROR_PATH_WIN_RE = re.compile(r"^\[ERROR\]\s+([A-Za-z]:\\.*?\.java):\[(\d+),(\d+)\]\s+(.*)$")

def run_maven_verify(pr_id: int, side: str, test_selector: Optional[str] = None) -> Tuple[int, str]:
    """
    Run maven verify capturing stdout. If test_selector is provided, only those tests run.
    """
    log_path = os.path.join(LOGS_DIR, f"pr_{pr_id}_{side}_mvn_verify.log")
    cmd = [
        "mvn", "clean", "verify",
        "-DfailIfNoTests=false",
        "-Dmaven.test.failure.ignore=true",
        "-q",
        "-DtrimStackTrace=false"
    ]
    if test_selector:
        cmd.append(f"-Dtest={test_selector}")
    cp = run(cmd, cwd=LOCAL_REPO_PATH, capture=True)
    with open(log_path, "w", encoding="utf-8") as f:
        f.write(cp.stdout or "")
    return cp.returncode, log_path


def parse_compile_errors_from_log(log_path: str, modules: List[str]) -> Dict[str, List[dict]]:
    """
    Parse javac test compile errors from maven log:
      [ERROR] /abs/path/TestX.java:[line,col] message...
    Group by module, for files under .../module/src/test/java.
    """
    per_module: Dict[str, List[dict]] = {m: [] for m in modules}
    with open(log_path, encoding="utf-8") as f:
        for line in f:
            m = ERROR_PATH_RE.match(line) or ERROR_PATH_WIN_RE.match(line)
            if not m:
                continue
            abs_path, line_no, col_no, message = m.group(1), int(m.group(2)), int(m.group(3)), m.group(4).strip()
            for mod in modules:
                troot = os.path.abspath(module_test_dir(mod))
                if abs_path.startswith(troot + os.sep):
                    per_module[mod].append({
                        "file": abs_path,
                        "line": line_no,
                        "column": col_no,
                        "message": message
                    })
                    break
    return {m: errs for m, errs in per_module.items() if errs}

def collect_surefire_failures(modules: List[str]) -> List[dict]:
    """
    Parse surefire TEST-*.xml under each module and extract failing/error testcases.
    """
    failures = []
    for m in modules:
        rep_dir = os.path.join(LOCAL_REPO_PATH, m, "target", "surefire-reports")
        if not os.path.isdir(rep_dir):
            continue
        for xmlf in glob.glob(os.path.join(rep_dir, "TEST-*.xml")):
            try:
                tree = ET.parse(xmlf)
                suite = tree.getroot()
            except ET.ParseError:
                continue
            suite_name = suite.attrib.get("name", os.path.basename(xmlf))
            for tc in suite.findall("testcase"):
                status, node = None, None
                if tc.find("failure") is not None:
                    status, node = "failure", tc.find("failure")
                elif tc.find("error") is not None:
                    status, node = "error", tc.find("error")
                if status:
                    failures.append({
                        "module": m,
                        "suite": suite_name,
                        "classname": tc.attrib.get("classname"),
                        "test": tc.attrib.get("name"),
                        "time": tc.attrib.get("time"),
                        "status": status,
                        "message": node.attrib.get("message") if node is not None else None,
                        "type": node.attrib.get("type") if node is not None and "type" in node.attrib else None,
                        "stacktrace": (node.text or "").strip() if node is not None else None
                    })
    return failures

# ---------------- EvoSuite: target selection & generation ----------------
def normalize_changed_paths(pr_files_entry) -> List[str]:
    """
    Accepts:
      - dicts with 'path'
      - plain strings
    Returns list of path strings.
    """
    paths = []
    for item in pr_files_entry or []:
        if isinstance(item, str):
            paths.append(item)
        elif isinstance(item, dict) and "path" in item:
            paths.append(item["path"])
    return paths

def path_is_main_java(p: str, modules: List[str]) -> Optional[Tuple[str, str]]:
    """
    If p is under <module>/src/main/java/**.java, return (module, rel_to_main).
    Otherwise None.
    """
    if not p.endswith(".java"):
        return None
    parts = p.split("/")
    if len(parts) < 5:
        return None
    # expect: <module>/src/main/java/...
    mod = parts[0]
    if mod not in modules:
        return None
    if parts[1:4] != ["src", "main", "java"]:
        return None
    rel = "/".join(parts[4:])
    return mod, rel

def rel_main_path_to_fqn(rel: str) -> str:
    # com/x/Y.java -> com.x.Y
    noext = rel[:-5] if rel.lower().endswith(".java") else rel
    return noext.replace("/", ".")

def build_classpath(module: str, pr_id: int) -> str:
    mod_path = os.path.join(LOCAL_REPO_PATH, module)
    out = f"classpath_{module}_{pr_id}.txt"
    run(["mvn", "dependency:build-classpath", f"-Dmdep.outputFile={out}"],
        cwd=mod_path)
    cp_file = os.path.join(mod_path, out)
    with open(cp_file, encoding="utf-8") as f:
        cp = f.read().strip()
    classes = os.path.join(mod_path, "target", "classes")
    return os.pathsep.join([cp, classes]) if os.path.exists(classes) else cp

def package_module(module: str) -> str:
    run(["mvn", "clean", "package", "-Dmaven.test.skip=true", "-q"], cwd=LOCAL_REPO_PATH)
    tgt = os.path.join(LOCAL_REPO_PATH, module, "target")
    jars = [f for f in os.listdir(tgt) if f.endswith(".jar") and "sources" not in f and "javadoc" not in f]
    if not jars:
        raise RuntimeError(f"No jar found for module {module} after package.")
    return os.path.join(tgt, jars[0])

def run_evosuite_for_classes(module: str, fqns: List[str], cp: str, jar: str, pr_id: int) -> Tuple[str, Dict[str,str]]:
    """
    Run EvoSuite per top-level FQN. Capture per-class logs.
    Move generated 'evosuite-tests' -> a stable folder and return its path.
    """
    logs: Dict[str, str] = {}
    for cls in sorted(fqns):
        # EvoSuite log file
        safe = cls.replace(".", "_")
        log_file = os.path.join(EVOSUITE_LOG_DIR, f"{module}_{safe}_pr_{pr_id}.log")

        # Launch EvoSuite
        with open(log_file, "w", encoding="utf-8") as lf:
            proc = subprocess.Popen(
                ["java", "-jar", EVOSUITE_JAR,
                 "-target", jar,
                 "-projectCP", cp,
                 "-class", cls,
                 "-Duse_separate_classloader=false",
                 "-Dsearch_budget=120",
                 "-Dalgorithm=DynaMOSA"],
                stdout=subprocess.PIPE,
                stderr=subprocess.STDOUT,
                text=True,
                bufsize=1
            )
            for line in proc.stdout:
                print(line, end="")
                lf.write(line)

        rc = proc.wait()
        logs[cls] = log_file
        if rc != 0:
            print(f"[EvoSuite] ❌ non-zero exit for {cls} – see {log_file}")

    # If nothing generated, there may be no 'evosuite-tests' dir
    dst = os.path.join(GEN_TESTS_DIR, f"evosuite_{module}_pr_{pr_id}")
    rm_tree(dst)
    if os.path.exists("evosuite-tests"):
        shutil.move("evosuite-tests", dst)
    else:
        os.makedirs(dst, exist_ok=True)  # create empty dir for consistency
    return dst, logs

# ------------------ Reporting for one side (merge/base) ------------------
def build_and_report(pr_id: int, modules: List[str], side: str, test_selector: Optional[str]) -> dict:
    code, log_path = run_maven_verify(pr_id, side, test_selector=test_selector)
    compile_errors = parse_compile_errors_from_log(log_path, modules)
    failures = collect_surefire_failures(modules)
    return {
        "maven_exit_code": code,
        "maven_log": log_path,
        "compile_errors": compile_errors,
        "test_failures": failures
    }


# --------------------------- main -------------------------------
def main():
    # prep repo
    repo = clone_repo()

    # load PR list
    with open(PR_LIST_JSON, encoding="utf-8") as f:
        prs = json.load(f)

    # try to checkout a valid ref to parse modules
    repo.git.fetch("origin")
    try:
        git_checkout(repo, "origin/master")
    except Exception:
        git_checkout(repo, "origin/main")

    base_modules = list_modules()

    # load or init aggregate report
    if os.path.exists(AGGREGATE_REPORT):
        try:
            with open(AGGREGATE_REPORT, encoding="utf-8") as f:
                aggregate = json.load(f)
            if not isinstance(aggregate, list):
                aggregate = []
        except Exception:
            aggregate = []
    else:
        aggregate = []

    for pr in prs:
        pr_id = pr.get("pr_number")
        merge_sha = pr.get("merge_commit")
        base_sha  = pr.get("base_commit")
        if not pr_id or not merge_sha or not base_sha:
            print(f"[SKIP] PR missing pr_number/base_commit/merge_commit: {pr}")
            continue

        print(f"\n==================== PR {pr_id} ====================")

        # ------------------- MERGE side: generate tests -------------------
        print(f"[PR {pr_id}] Checkout MERGE {merge_sha}")
        git_checkout(repo, merge_sha)

        # ensure minimal deps to run EvoSuite tests if needed
        ensure_test_deps_in_parent_pom()

        # discover modules from merge commit (fallback to base_modules)
        modules = list_modules() or base_modules
        print(f"[PR {pr_id}] Modules: {modules}")

        # detect changed production classes from PR file list
        changed_paths = normalize_changed_paths(pr.get("files", []))
        touched: Dict[str, List[str]] = {m: [] for m in modules}
        for p in changed_paths:
            hit = path_is_main_java(p, modules)
            if hit:
                mod, rel = hit
                fqn = rel_main_path_to_fqn(rel)
                if fqn not in touched[mod]:
                    touched[mod].append(fqn)

        # generate EvoSuite tests per module (only for changed classes)
        evosuite_dirs: Dict[str, str] = {}  # module -> generated tests dir
        evosuite_logs: Dict[str, str] = {}  # FQN -> log path
        for mod, fqns in touched.items():
            if not fqns:
                continue
            print(f"[PR {pr_id}] Generating EvoSuite tests for {mod}: {len(fqns)} classes")
            cp = build_classpath(mod, pr_id)
            jar = package_module(mod)
            gen_dir, logs = run_evosuite_for_classes(mod, fqns, cp, jar, pr_id)
            evosuite_dirs[mod] = gen_dir
            evosuite_logs.update(logs)

            # merge into module's test tree (keep human tests)
            dest_tests = module_test_dir(mod)
            os.makedirs(dest_tests, exist_ok=True)
            merge_tree(gen_dir, dest_tests)

        # run tests on MERGE side and collect results
        merge_results = build_and_report(pr_id, modules, side="merge", test_selector=TEST_SELECTOR)


        # ------------------- BASE side: reuse generated tests --------------
        print(f"[PR {pr_id}] Checkout BASE {base_sha}")
        git_checkout(repo, base_sha)

        # ensure test deps also on base (if needed)
        ensure_test_deps_in_parent_pom()

        # module layout may differ; try same modules else re-list
        base_side_modules = list_modules() or modules
        print(f"[PR {pr_id}] (BASE) Modules: {base_side_modules}")

        # place the previously generated tests into base modules
        for mod in base_side_modules:
            gen_dir = evosuite_dirs.get(mod)
            if not gen_dir or not os.path.isdir(gen_dir):
                continue
            dest_tests = module_test_dir(mod)
            rm_tree(dest_tests)  # replace base tests with generated + (optionally) keep human tests?
            os.makedirs(dest_tests, exist_ok=True)
            merge_tree(gen_dir, dest_tests)
            # If you want to keep human tests too, comment the rm_tree above
            # and call merge_tree(gen_dir, dest_tests) without deleting.

        base_results = build_and_report(pr_id, base_side_modules, side="base", test_selector=TEST_SELECTOR)


        # ---------------------- Aggregate report entry ---------------------
        entry = {
            "pr_number": pr_id,
            "merge_commit": merge_sha,
            "base_commit": base_sha,
            "modules_merge": modules,
            "modules_base": base_side_modules,
            "touched_classes": {m: cls for m, cls in touched.items() if cls},
            "evosuite_logs": evosuite_logs,           # FQN -> log file
            "generated_tests_dirs": evosuite_dirs,     # module -> dir with generated tests
            "results_merge": merge_results,            # compile errors + failures on MERGE
            "results_base": base_results,              # compile errors + failures on BASE
            "generated_at": datetime.datetime.utcnow().isoformat() + "Z"
        }

        # replace old entry for this PR and append updated
        aggregate = [r for r in aggregate if r.get("pr_number") != pr_id]
        aggregate.append(entry)
        aggregate.sort(key=lambda r: r.get("pr_number", 0))

        with open(AGGREGATE_REPORT, "w", encoding="utf-8") as f:
            json.dump(aggregate, f, indent=2)
        print(f"[PR {pr_id}] Updated aggregate report: {AGGREGATE_REPORT}")

    print("\nDone.")

if __name__ == "__main__":
    main()
