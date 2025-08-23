#!/usr/bin/env python3
import os
import subprocess
import json
import re
import shutil
import fnmatch
import xml.etree.ElementTree as ET
from git import Repo

# --- Configuration ---
REPO_URL = "https://github.com/apache/commons-collections.git"
PROJECT_NAME = "commons-collections"
LOCAL_REPO_PATH = PROJECT_NAME
EVOSUITE_JAR = "evosuite.jar"
EVOSUITE_RUNTIME_VERSION = "1.0.6"
VERSION_LOG_FILE = "pr_versions.txt"
OUTPUT_JSON_FILE = "generated_evosuite_tests.json"
PROJECT_TEST_DIR = os.path.join(LOCAL_REPO_PATH, "src", "test", "java")
VERSIONS_JSON_FILE = "pr_shas.json"
EVOSUITE_GEN_SUMMARY_FILE = "evosuite_generation_summary.json"

# --- Maven XML Namespace ---
NAMESPACE = {"mvn": "http://maven.apache.org/POM/4.0.0"}
ET.register_namespace('', NAMESPACE["mvn"])

# ========================= Utility: Report parsing =========================
def append_not_processed(pr, error_msg: str):
    """Write a minimal record to OUTPUT_JSON_FILE when a PR can't be processed."""
    if os.path.exists(OUTPUT_JSON_FILE):
        try:
            with open(OUTPUT_JSON_FILE, "r", encoding="utf-8") as f:
                data = json.load(f)
        except json.JSONDecodeError:
            data = []
    else:
        data = []

    entry = {
        "pr_number": pr.get("pr_number"),
        "title": pr.get("title"),
        "state": pr.get("state"),
        "created_at": pr.get("created_at"),
        "merge_commit_sha": pr.get("merge_commit_sha"),
        "base_sha": pr.get("base_sha") or pr.get("base_commit"),
        "head_sha": pr.get("head_sha"),
        "user_login": pr.get("user_login"),
        "status": "not_processed",
        "error": error_msg[-4000:],  # keep it readable; cap size
    }
    data.append(entry)
    with open(OUTPUT_JSON_FILE, "w", encoding="utf-8") as f:
        json.dump(data, f, indent=2)


def summarize_evosuite_generation(pr: dict, generated_tests_dir: str, evosuite_logs: list,
                                  out_json: str = EVOSUITE_GEN_SUMMARY_FILE):
    """
    Build a per-PR summary of EvoSuite generation results and append it to out_json.

    - Counts generated test files (excluding scaffolding)
    - Lists generated file paths (relative to generated_tests_dir)
    - Matches generation to targeted classes (by simple name heuristic)
    - Flags which targets got 0 tests
    - Includes per-class EvoSuite stdout/stderr log file paths and return codes
    - Works even if generated_tests_dir does not exist (counts=0)
    """
    pr_number = pr.get("pr_number")
    if not pr_number:
        return

    tests_dir_exists = os.path.isdir(generated_tests_dir)

    # 1) Collect all generated test files
    generated_files = []
    if tests_dir_exists:
        for root, _, files in os.walk(generated_tests_dir):
            for f in files:
                if _is_evosuite_test_java(f):
                    full = os.path.join(root, f)
                    rel = os.path.relpath(full, generated_tests_dir)
                    generated_files.append(rel)

    total_generated = len(generated_files)

    # 2) Map test files -> target simple name via filename
    tests_by_target_simple = {}
    for rel in generated_files:
        base = os.path.basename(rel)
        simple = _target_simple_from_test_filename(base)
        if simple is None:
            # fallback (rare): strip .java only
            simple = os.path.splitext(base)[0]
        tests_by_target_simple.setdefault(simple, []).append(rel)

    # 3) Per-class records based on what we attempted (from logs)
    def _target_simple_from_fqn(fqn: str) -> str:
        # take last segment after '.' then strip any '$Inner'
        s = fqn.split(".")[-1]
        s = s.split("$")[-1]
        return s

    per_class = []
    missing_targets = []
    attempted_targets = []

    for logrec in (evosuite_logs or []):
        target_fqn = logrec.get("class")
        if not target_fqn:
            # Skip malformed entries
            continue
        attempted_targets.append(target_fqn)
        simple = _target_simple_from_fqn(target_fqn)
        rels = tests_by_target_simple.get(simple, [])

        per_class.append({
            "target_class": target_fqn,
            "target_simple": simple,
            "generated_count": len(rels),
            "generated_files": rels,  # relative to generated_tests_dir
            "evosuite_stdout": logrec.get("stdout"),
            "evosuite_stderr": logrec.get("stderr"),
            "evosuite_returncode": logrec.get("returncode"),
        })

        if len(rels) == 0:
            missing_targets.append(target_fqn)

    # 4) Assemble entry and append to JSON (append-only)
    entry = {
        "pr_number": pr_number,
        "head_sha": pr.get("head_sha"),
        "base_sha": pr.get("base_sha") or pr.get("base_commit"),
        "title": pr.get("title"),
        "created_at": pr.get("created_at"),
        "evosuite": {
            "tests_dir": generated_tests_dir,
            "tests_dir_exists": tests_dir_exists,
            "total_generated_tests": total_generated,
            "generated_files": generated_files,
            "attempted_targets": attempted_targets,
            "missing_targets": missing_targets,
            "per_class": per_class
        }
    }

    if os.path.exists(out_json):
        try:
            with open(out_json, "r", encoding="utf-8") as f:
                data = json.load(f)
        except json.JSONDecodeError:
            data = []
    else:
        data = []

    data.append(entry)
    with open(out_json, "w", encoding="utf-8") as f:
        json.dump(data, f, indent=2)


from typing import Optional

def _is_evosuite_test_java(filename: str) -> bool:
    """
    True for real EvoSuite tests, excluding scaffolding.
    Recognizes: *_ESTest.java and *ES_Test.java
    """
    if not filename.endswith(".java"):
        return False
    base = os.path.basename(filename)
    if base.endswith("_scaffolding.java"):
        return False
    return base.endswith("_ESTest.java") or base.endswith("ES_Test.java")


def _target_simple_from_test_filename(base: str) -> Optional[str]:
    """
    Given a test file basename, return the simple name of the target class,
    or None if it doesn't match an EvoSuite test naming convention.
    Examples:
      GrowthList_ESTest.java -> GrowthList
      FooES_Test.java        -> Foo
    """
    if base.endswith("_ESTest.java"):
        return base[:-len("_ESTest.java")]
    if base.endswith("ES_Test.java"):
        return base[:-len("ES_Test.java")]
    return None

def filter_details_failures_only(details):
    if not details:
        return details
    out = {"summary": details.get("summary", {}), "by_class": []}
    for cls in details.get("by_class", []):
        bad_cases = [c for c in cls.get("cases", []) if c.get("status") in ("failure", "error")]
        if bad_cases:
            out["by_class"].append({
                "class": cls.get("class"),
                "tests": cls.get("tests", 0),
                "failures": cls.get("failures", 0),
                "errors": cls.get("errors", 0),
                "skipped": cls.get("skipped", 0),
                "cases": bad_cases
            })
    return out

def collect_surefire_summary(surefire_dir):
    totals = {"tests": 0, "failures": 0, "errors": 0, "skipped": 0}
    if not surefire_dir or not os.path.isdir(surefire_dir):
        return totals
    import glob
    xmls = glob.glob(os.path.join(surefire_dir, "*.xml"))
    for path in xmls:
        try:
            t = ET.parse(path).getroot()
            if t.tag.endswith("testsuite"):
                totals["tests"]    += int(t.attrib.get("tests", "0"))
                totals["failures"] += int(t.attrib.get("failures", "0"))
                totals["errors"]   += int(t.attrib.get("errors", "0"))
                totals["skipped"]  += int(t.attrib.get("skipped", "0"))
        except Exception:
            pass
    return totals

def collect_surefire_details(surefire_dir):
    import glob
    details = {"summary": {"tests":0,"failures":0,"errors":0,"skipped":0}, "by_class": []}
    if not surefire_dir or not os.path.isdir(surefire_dir):
        return details
    xmls = glob.glob(os.path.join(surefire_dir, "*.xml"))
    for path in xmls:
        try:
            t = ET.parse(path).getroot()
            if not t.tag.endswith("testsuite"):
                continue
            cl = t.attrib.get("name")
            tests    = int(t.attrib.get("tests", "0"))
            failures = int(t.attrib.get("failures", "0"))
            errors   = int(t.attrib.get("errors", "0"))
            skipped  = int(t.attrib.get("skipped", "0"))
            details["summary"]["tests"]    += tests
            details["summary"]["failures"] += failures
            details["summary"]["errors"]   += errors
            details["summary"]["skipped"]  += skipped
            cases = []
            for tc in t.findall(".//testcase"):
                name = tc.attrib.get("name", "")
                status = "passed"
                msg = typ = None
                if tc.find("failure") is not None:
                    status = "failure"
                    f = tc.find("failure")
                    msg = f.attrib.get("message")
                    typ = f.attrib.get("type")
                elif tc.find("error") is not None:
                    status = "error"
                    e = tc.find("error")
                    msg = e.attrib.get("message")
                    typ = e.attrib.get("type")
                elif tc.find("skipped") is not None:
                    status = "skipped"
                cases.append({"name": name, "status": status, "message": msg, "type": typ})
            details["by_class"].append({
                "class": cl, "tests": tests, "failures": failures, "errors": errors, "skipped": skipped,
                "cases": cases
            })
        except Exception:
            continue
    return details

def archive_surefire_reports(pr_number, phase_label, surefire_dir):
    if not surefire_dir or not os.path.isdir(surefire_dir):
        return None
    dst = f"surefire_{phase_label}_pr_{pr_number}"
    if os.path.exists(dst):
        shutil.rmtree(dst)
    shutil.copytree(surefire_dir, dst)
    return dst

def _is_empty_or_missing_surefire(surefire_dir: str) -> bool:
    if not surefire_dir or not os.path.isdir(surefire_dir):
        return True
    xmls = [f for f in os.listdir(surefire_dir) if f.endswith(".xml")]
    return len(xmls) == 0

# ========================= Outcome classification (no keywords) =========================

def classify_test_phase(res_returncode: int, summary: dict, surefire_missing: bool):
    """
    Classify test-phase outcome without relying on log keywords.
    We compiled separately; compile_error here should always be False.
    """
    tests = summary.get("tests", 0) if summary else 0
    fails = summary.get("failures", 0) if summary else 0
    errs  = summary.get("errors", 0) if summary else 0

    # No XML present → either no tests or plugin/infra error.
    if surefire_missing:
        # If Maven said success but Surefire has nothing, treat as "no_tests".
        # If Maven non-zero here, it's likely a plugin/infra failure.
        if res_returncode == 0:
            return {
                "outcome": "no_tests",
                "compile_error": False,
                "test_failures": False,
                "maven_failed_other": False,
                "no_tests_ran": True,
                "surefire_missing": True,
                "all_passed": False,
            }
        else:
            return {
                "outcome": "maven_failed_other",
                "compile_error": False,
                "test_failures": False,
                "maven_failed_other": True,
                "no_tests_ran": True,
                "surefire_missing": True,
                "all_passed": False,
            }

    # XML exists; decide using counts (ignore returncode because we set maven.test.failure.ignore=true)
    if tests == 0:
        return {
            "outcome": "no_tests",
            "compile_error": False,
            "test_failures": False,
            "maven_failed_other": False,
            "no_tests_ran": True,
            "surefire_missing": False,
            "all_passed": False,
        }
    if (fails > 0) or (errs > 0):
        return {
            "outcome": "test_failures",
            "compile_error": False,
            "test_failures": True,
            "maven_failed_other": False,
            "no_tests_ran": False,
            "surefire_missing": False,
            "all_passed": False,
        }
    # All passed
    return {
        "outcome": "passed",
        "compile_error": False,
        "test_failures": False,
        "maven_failed_other": False,
        "no_tests_ran": False,
        "surefire_missing": False,
        "all_passed": True,
    }

# ========================= Git & Build Helpers =========================

def ensure_pr_refspecs():
    out = subprocess.run(
        ["git", "config", "--get-all", "remote.origin.fetch"],
        cwd=LOCAL_REPO_PATH, text=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE
    )
    existing = set((out.stdout or "").splitlines())
    spec = "+refs/pull/*/head:refs/remotes/origin/pr/*"
    if spec not in existing:
        subprocess.run(
            ["git", "config", "--add", "remote.origin.fetch", spec],
            cwd=LOCAL_REPO_PATH, check=True
        )

def checkout_pr_head_sha_with_refspecs(pr, repo):
    ensure_pr_refspecs()
    repo.git.fetch("--all", "--tags")
    repo.git.reset("--hard")
    repo.git.clean("-fdx")
    head_sha = pr.get("head_sha")
    if not head_sha:
        raise ValueError("PR JSON missing 'head_sha'")
    cp = subprocess.run(
        ["git", "rev-parse", "--verify", f"{head_sha}^{{commit}}"],
        cwd=LOCAL_REPO_PATH, text=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE
    )
    if cp.returncode != 0:
        raise RuntimeError(f"Commit not found locally after fetch: {head_sha}\n{cp.stderr or cp.stdout}")
    repo.git.checkout(head_sha)
    print(f"Checked out PR HEAD (by SHA): {head_sha}")

def clone_repo():
    if not os.path.exists(LOCAL_REPO_PATH):
        print("Cloning repository...")
        Repo.clone_from(REPO_URL, LOCAL_REPO_PATH)
    else:
        print("Using existing local repository.")
    return Repo(LOCAL_REPO_PATH)

def checkout_commit(commit_sha, repo):
    print(f"Checking out commit {commit_sha}...")
    repo.git.reset('--hard')
    repo.git.clean('-fdx')
    repo.git.fetch("--all", "--tags")
    repo.git.checkout(commit_sha)

def compile_project():
    print("Compiling project with Maven (skipping RAT check)…")
    result = subprocess.run(
        ["mvn", "clean", "compile", "-Drat.skip=true"],
        cwd=LOCAL_REPO_PATH,
        stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True
    )
    if result.returncode != 0:
        print("Maven build failed!")
        print("STDOUT:", result.stdout)
        print("STDERR:", result.stderr)
        result.check_returncode()

def build_classpath(pr_number):
    print("Generating classpath using Maven…")
    subprocess.run(
        [
            "mvn", "dependency:build-classpath",
            "-Dmdep.outputFile=classpath.txt",
            "-Dmdep.includeScope=test",
            "-Drat.skip=true",
        ],
        cwd=LOCAL_REPO_PATH, check=True
    )
    cp_repo = os.path.join(LOCAL_REPO_PATH, "classpath.txt")
    cp_new  = f"classpath_pr_{pr_number}.txt"
    shutil.move(cp_repo, cp_new)
    with open(cp_new) as f:
        classpath = f.read().strip()
    classes_dir = os.path.join(LOCAL_REPO_PATH, "target", "classes")
    return classpath + os.pathsep + classes_dir if os.path.exists(classes_dir) else classpath

def package_project():
    print("Packaging project with Maven (skipping RAT check)…")
    subprocess.run(
        ["mvn", "clean", "package", "-Drat.skip=true", "-Dmaven.test.skip=true"],
        cwd=LOCAL_REPO_PATH, check=True
    )
    target_dir = os.path.join(LOCAL_REPO_PATH, "target")
    jars = [f for f in os.listdir(target_dir) if f.endswith(".jar") and "sources" not in f]
    if not jars:
        raise Exception("No jar file found in target directory")
    return os.path.join(target_dir, jars[0])

def get_project_version():
    result = subprocess.run(
        ["mvn", "help:evaluate", "-Dexpression=project.version", "-q", "-DforceStdout"],
        cwd=LOCAL_REPO_PATH, text=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE, check=True
    )
    return result.stdout.strip()

def write_version_info(pr_number, version):
    with open(VERSION_LOG_FILE, "a") as f:
        f.write(f"PR {pr_number}: {version}\n")

# ========================= EvoSuite & Tests =========================

def run_evosuite_for_classes(class_names, project_version, pr_number, project_classpath, project_jar):
    """
    Runs EvoSuite per outer class and stores raw stdout/stderr to disk.
    Returns a list of {"class","stdout","stderr","returncode"} for JSON.
    """
    os.makedirs("evosuite_logs", exist_ok=True)
    outer_classes = [c for c in class_names if '$' not in c]
    outer_classes.sort()

    logs = []

    for cls in outer_classes:
        safe_cls = re.sub(r'[^A-Za-z0-9_.-]+', '_', cls)
        out_path = os.path.join("evosuite_logs", f"evosuite_{safe_cls}_pr_{pr_number}.out")
        err_path = os.path.join("evosuite_logs", f"evosuite_{safe_cls}_pr_{pr_number}.err")

        cmd = [
            "java", "-jar", EVOSUITE_JAR,
            "-target", project_jar,
            "-projectCP", project_classpath,
            "-Dsearch_budget=120",
            "-Duse_separate_classloader=false",
            "-Dalgorithm=DynaMOSA",
            "-class", cls
        ]

        print(f"[PR {pr_number}] → EvoSuite targeting class: {cls}")
        print("Full command:", " ".join(cmd))

        res = subprocess.run(
            cmd, text=True,
            stdout=subprocess.PIPE, stderr=subprocess.PIPE
        )

        # Write raw EvoSuite output verbatim
        with open(out_path, "w", encoding="utf-8") as f:
            f.write(res.stdout or "")
        with open(err_path, "w", encoding="utf-8") as f:
            f.write(res.stderr or "")

        logs.append({
            "class": cls,
            "stdout": out_path,
            "stderr": err_path,
            "returncode": res.returncode
        })

    return logs


def move_evosuite_output(pr_number, default_folder="evosuite-tests"):
    """
    Moves EvoSuite's output folder to a PR-specific dir and returns its path.
    Example: evosuite-tests-commons-imaging_pr_67
    """
    dest = f"{default_folder}-{PROJECT_NAME}_pr_{pr_number}"
    if os.path.exists(default_folder):
        if os.path.exists(dest):
            shutil.rmtree(dest)
        shutil.move(default_folder, dest)
    return dest


def remove_comments(code):
    code = re.sub(r'/\*.*?\*/', '', code, flags=re.DOTALL)
    return re.sub(r'//.*', '', code)

def extract_class_names(content: str, file_path: str):
    code = remove_comments(content)
    pkg_match = re.search(r'\bpackage\s+([\w\.]+)\s*;', code)
    pkg = pkg_match.group(1) + "." if pkg_match else ""
    outer = os.path.splitext(os.path.basename(file_path))[0]
    simple_names = re.findall(r'\b(?:class|interface|enum)\s+(\w+)', code)
    fq_names = []
    for name in simple_names:
        if name == outer:
            fq_names.append(pkg + name)
        else:
            fq_names.append(pkg + outer + '$' + name)
    return fq_names

def replace_tests_in_project(src, dest):
    os.makedirs(dest, exist_ok=True)
    for root, _, files in os.walk(src):
        rel = os.path.relpath(root, src)
        out_dir = os.path.join(dest, rel) if rel != "." else dest
        os.makedirs(out_dir, exist_ok=True)
        for f in files:
            shutil.copy2(os.path.join(root, f), os.path.join(out_dir, f))

# ========================= Compile-only & test runs =========================

def compile_tests_only(pr_number, phase_label):
    """
    Compile main + test sources WITHOUT running tests.
    Returns dict with success flag and log file paths.
    """
    cmd = [
        "mvn", "clean", "test-compile",
        "-Drat.skip=true",
        "-DskipTests=true",          # don't run tests, but do compile them
        "-Dmaven.test.skip=false",   # ensure test sources are compiled
        "-Dsurefire.printSummary=true",
        "-q"
    ]
    print("> " + " ".join(cmd))
    res = subprocess.run(
        cmd, cwd=LOCAL_REPO_PATH,
        text=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE
    )

    log_out = f"mvn_compile_{phase_label}_pr_{pr_number}.out"
    log_err = f"mvn_compile_{phase_label}_pr_{pr_number}.err"
    with open(log_out, "w", encoding="utf-8") as f: f.write(res.stdout or "")
    with open(log_err, "w", encoding="utf-8") as f: f.write(res.stderr or "")

    return {
        "ok": (res.returncode == 0),
        "returncode": res.returncode,
        "logs": {"stdout": log_out, "stderr": log_err},
    }

def make_compile_error_result(pr_number, phase_label, logs):
    """Standardized result when compilation fails (no tests executed)."""
    summary = {"tests": 0, "failures": 0, "errors": 0, "skipped": 0}
    return {
        "returncode": 1,
        "summary": summary,
        "details": {"summary": summary, "by_class": []},
        "all_passed": False,
        "compile_error": True,
        "test_failures": False,
        "maven_failed_other": False,
        "no_tests_ran": True,
        "surefire_missing": True,
        "outcome": "compile_error",
        "logs": logs,
        "surefire_dir": None,
        "surefire_archive": None,
        "compiled_ok": False
    }

def run_tests_and_generate_coverage(pr_number, phase_label):
    """
    Runs ONLY EvoSuite tests. Classifies outcome without keyword heuristics.
    Assumes compilation has already succeeded for this phase.
    """
    cmd = [
        "mvn", "clean", "test",
        "-Dtest=*ESTest,*ES_Test,!*_scaffolding",
        "-Dsurefire.failIfNoSpecifiedTests=false",
        "-Dmaven.test.failure.ignore=true",   # keep return code 0 when tests fail
        "-fn",
        "-Drat.skip=true",
        "-DskipTests=false",
        "-Dmaven.test.skip=false",
        "-Dsurefire.printSummary=true",
        "-Dsurefire.useFile=true",
        "-Dsurefire.redirectTestOutputToFile=true",
    ]
    print("> " + " ".join(cmd))
    res = subprocess.run(
        cmd, cwd=LOCAL_REPO_PATH,
        text=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE
    )

    # Save logs per-phase
    log_out = f"mvn_{phase_label}_pr_{pr_number}.out"
    log_err = f"mvn_{phase_label}_pr_{pr_number}.err"
    with open(log_out, "w", encoding="utf-8") as f:
        f.write(res.stdout or "")
    with open(log_err, "w", encoding="utf-8") as f:
        f.write(res.stderr or "")

    surefire_dir = os.path.join(LOCAL_REPO_PATH, "target", "surefire-reports")
    surefire_effectively_missing = _is_empty_or_missing_surefire(surefire_dir)

    if not surefire_effectively_missing:
        summary = collect_surefire_summary(surefire_dir)
        details_full = collect_surefire_details(surefire_dir)
    else:
        summary = {"tests": 0, "failures": 0, "errors": 0, "skipped": 0}
        details_full = {"summary": summary, "by_class": []}

    # Classify based on XML presence and counts (ignore returncode since failure.ignore=true)
    diag = classify_test_phase(
        res_returncode=res.returncode,
        summary=summary,
        surefire_missing=surefire_effectively_missing
    )

    details = filter_details_failures_only(details_full)
    archive_path = None
    if not surefire_effectively_missing:
        archive_path = archive_surefire_reports(pr_number, phase_label, surefire_dir)

    return {
        "returncode": res.returncode,
        "summary": summary,
        "details": details,
        "all_passed": diag["all_passed"],
        "compile_error": diag["compile_error"],          # should be False here
        "test_failures": diag["test_failures"],
        "maven_failed_other": diag["maven_failed_other"],
        "no_tests_ran": diag["no_tests_ran"],
        "surefire_missing": diag["surefire_missing"],
        "outcome": diag["outcome"],
        "logs": {"stdout": log_out, "stderr": log_err},
        "surefire_dir": None if surefire_effectively_missing else surefire_dir,
        "surefire_archive": archive_path
    }

# ========================= Base verification =========================

def verify_tests_on_base(pr, repo, generated_tests_dir):
    base_sha = pr.get("base_sha") or pr.get("base_commit")
    if not base_sha:
        raise ValueError("PR missing base_sha/base_commit for base verification")
    pr_number = pr.get("pr_number")
    checkout_commit(base_sha, repo)
    update_pom_for_pr(pr_number)
    replace_tests_in_project(generated_tests_dir, PROJECT_TEST_DIR)

    # Compile-only check first
    base_compile = compile_tests_only(pr_number, phase_label="base")
    if not base_compile["ok"]:
        result = make_compile_error_result(pr_number, "base", base_compile["logs"])
        result["base_sha"] = base_sha
        print(f"[Base check] outcome={result['outcome']} summary={result['summary']}")
        return result

    # If compiled OK, then run tests
    result = run_tests_and_generate_coverage(pr_number, phase_label="base")
    result["compiled_ok"] = True
    result["base_sha"] = base_sha
    print(f"[Base check] outcome={result['outcome']} summary={result['summary']}")
    return result

# ========================= JSON persistence =========================

def append_tests_to_json(pr_data, merge_result, base_result, evosuite_logs, generated_tests_dir):
    """
    Appends one PR entry including:
    - merge/base outcomes,
    - top-level convenience flags,
    - EvoSuite tests dir and per-class stdout/stderr log file paths.
    """
    if os.path.exists(OUTPUT_JSON_FILE):
        try:
            with open(OUTPUT_JSON_FILE) as f:
                data = json.load(f)
        except json.JSONDecodeError:
            data = []
    else:
        data = []

    entry = {
        "pr_number": pr_data.get("pr_number"),
        "title": pr_data.get("title"),
        "state": pr_data.get("state"),
        "created_at": pr_data.get("created_at"),
        "merge_commit_sha": pr_data.get("merge_commit_sha"),
        "base_sha": pr_data.get("base_sha"),
        "head_sha": pr_data.get("head_sha"),
        "user_login": pr_data.get("user_login"),

        # EvoSuite artifacts
        "evosuite": {
            "tests_dir": generated_tests_dir,  # e.g., "evosuite-tests-commons-imaging_pr_67"
            "logs": evosuite_logs              # list of {"class","stdout","stderr","returncode"}
        },

        "merge_phase": {
            "outcome": merge_result.get("outcome"),
            "compiled_ok": bool(merge_result.get("compiled_ok", False)),
            "compile_error": merge_result.get("compile_error", False),
            "test_failures": merge_result.get("test_failures", False),
            "maven_failed_other": merge_result.get("maven_failed_other", False),
            "no_tests_ran": merge_result.get("no_tests_ran", False),
            "surefire_missing": merge_result.get("surefire_missing", False),
            "all_passed": merge_result.get("all_passed"),
            "summary": merge_result.get("summary"),
            "surefire_archive": merge_result.get("surefire_archive"),
            "details": merge_result.get("details"),
            "logs": merge_result.get("logs"),
        },

        "base_phase": {
            "base_sha": base_result.get("base_sha"),
            "outcome": base_result.get("outcome"),
            "compiled_ok": bool(base_result.get("compiled_ok", False)),
            "compile_error": base_result.get("compile_error", False),
            "test_failures": base_result.get("test_failures", False),
            "maven_failed_other": base_result.get("maven_failed_other", False),
            "no_tests_ran": base_result.get("no_tests_ran", False),
            "surefire_missing": base_result.get("surefire_missing", False),
            "all_passed": base_result.get("all_passed"),
            "summary": base_result.get("summary"),
            "surefire_archive": base_result.get("surefire_archive"),
            "details": base_result.get("details"),
            "logs": base_result.get("logs"),
        }
    }

    # Convenience flags
    entry["had_compile_error"] = bool(
        entry["merge_phase"].get("compile_error") or
        entry["base_phase"].get("compile_error")
    )
    entry["had_test_failures"] = bool(
        entry["merge_phase"].get("test_failures") or
        entry["base_phase"].get("test_failures")
    )
    entry["is_compiled_merge"] = entry["merge_phase"]["compiled_ok"]
    entry["is_compiled_base"]  = entry["base_phase"]["compiled_ok"]

    data.append(entry)
    with open(OUTPUT_JSON_FILE, "w") as f:
        json.dump(data, f, indent=2)


def append_shas(pr):
    rec = {
        "pr_number": pr.get("pr_number"),
        "head_sha": pr.get("head_sha"),
        "base_sha": pr.get("base_sha") or pr.get("base_commit"),
    }
    path = VERSIONS_JSON_FILE
    if os.path.exists(path):
        try:
            with open(path) as f: data = json.load(f)
        except json.JSONDecodeError:
            data = []
    else:
        data = []
    data.append(rec)
    with open(path, "w") as f:
        json.dump(data, f, indent=2)

# ========================= pom.xml modifications =========================

def add_plugins_to_pom(pom_path, pr_number):
    if not os.path.exists(pom_path):
        raise FileNotFoundError(pom_path)
    tree = ET.parse(pom_path)
    root = tree.getroot()

    def find_ns(parent, tag):
        return parent.find(f"mvn:{tag}", NAMESPACE)
    def findall_ns(parent, tag):
        return parent.findall(f"mvn:{tag}", NAMESPACE)

    build   = find_ns(root, "build")    or ET.SubElement(root, "build")
    plugins = find_ns(build, "plugins") or ET.SubElement(build, "plugins")
    if not any(find_ns(p, "groupId") is not None and find_ns(p, "groupId").text == "org.jacoco"
               for p in findall_ns(plugins, "plugin")):
        jacoco = ET.SubElement(plugins, "plugin")
        ET.SubElement(jacoco, "groupId").text    = "org.jacoco"
        ET.SubElement(jacoco, "artifactId").text = "jacoco-maven-plugin"
        ET.SubElement(jacoco, "version").text    = "0.8.8"
        execs = ET.SubElement(jacoco, "executions")
        e1 = ET.SubElement(execs, "execution")
        goals1 = ET.SubElement(e1, "goals")
        goal1  = ET.SubElement(goals1, "goal"); goal1.text = "prepare-agent"
        e2 = ET.SubElement(execs, "execution")
        ET.SubElement(e2, "id").text    = "report"
        ET.SubElement(e2, "phase").text = "test"
        goals2 = ET.SubElement(e2, "goals")
        goal2  = ET.SubElement(goals2, "goal"); goal2.text = "report"

    deps = find_ns(root, "dependencies") or ET.SubElement(root, "dependencies")
    def dep_exists(gid, aid):
        return any(
            find_ns(d, "groupId") is not None and find_ns(d, "groupId").text == gid and
            find_ns(d, "artifactId") is not None and find_ns(d, "artifactId").text == aid
            for d in deps.findall("mvn:dependency", NAMESPACE)
        )
    if not dep_exists("org.evosuite", "evosuite-standalone-runtime"):
        evo = ET.SubElement(deps, "dependency")
        ET.SubElement(evo, "groupId").text    = "org.evosuite"
        ET.SubElement(evo, "artifactId").text = "evosuite-standalone-runtime"
        ET.SubElement(evo, "version").text    = EVOSUITE_RUNTIME_VERSION
        ET.SubElement(evo, "scope").text      = "test"
    for gid, aid in [
        ("org.junit.jupiter", "junit-jupiter"),
        ("org.junit.jupiter", "junit-jupiter-params"),
        ("org.junit.vintage", "junit-vintage-engine"),
    ]:
        if not dep_exists(gid, aid):
            d = ET.SubElement(deps, "dependency")
            ET.SubElement(d, "groupId").text    = gid
            ET.SubElement(d, "artifactId").text = aid
            ET.SubElement(d, "version").text    = "5.10.0"
            ET.SubElement(d, "scope").text      = "test"

    reporting   = find_ns(root, "reporting")    or ET.SubElement(root, "reporting")
    rep_plugins = find_ns(reporting, "plugins") or ET.SubElement(reporting, "plugins")
    if not any(find_ns(p, "artifactId") is not None and find_ns(p, "artifactId").text == "jacoco-maven-plugin"
               for p in findall_ns(rep_plugins, "plugin")):
        rep = ET.SubElement(rep_plugins, "plugin")
        ET.SubElement(rep, "groupId").text    = "org.jacoco"
        ET.SubElement(rep, "artifactId").text = "jacoco-maven-plugin"
        reportSets = ET.SubElement(rep, "reportSets")
        reportSet  = ET.SubElement(reportSets, "reportSet")
        reports    = ET.SubElement(reportSet, "reports")
        ET.SubElement(reports, "report").text = "report"

    tree.write(pom_path, encoding="utf-8", xml_declaration=True)
    print(f"pom.xml updated successfully at {pom_path}.")

def update_pom_for_pr(pr_number):
    pom_path = os.path.join(LOCAL_REPO_PATH, "pom.xml")
    add_plugins_to_pom(pom_path, pr_number)

# ========================= Main =========================

def check_java_version():
    print("Checking Java version...")
    result = subprocess.run(
        ["java", "-version"],
        stdout=subprocess.PIPE,
        stderr=subprocess.PIPE,
        text=True
    )
    output = result.stderr.strip() or result.stdout.strip()
    print(output)

def main():
    # Import here so you don't have to modify the header
    import traceback

    check_java_version()

    # Load PR list
    with open("collections4.json", "r", encoding="utf-8") as f:
        pr_data_list = json.load(f)

    repo = clone_repo()

    for pr in pr_data_list:
        pr_number = pr.get("pr_number")
        if not pr_number:
            continue

        try:
            # === MERGE PHASE: checkout PR HEAD and generate tests ===
            checkout_pr_head_sha_with_refspecs(pr, repo)
            update_pom_for_pr(pr_number)

            # Build the project to get a jar & classpath for EvoSuite
            compile_project()
            cp  = build_classpath(pr_number)
            jar = package_project()
            ver = get_project_version()
            write_version_info(pr_number, ver)

            # ---- Safe class targeting: derive FQCNs from paths (no decoding) ----
            classes = set()
            for file in pr.get("changed_files", []):
                if not file.get("do_experiment"):
                    continue
                path = file.get("filename", "")
                if not (path.startswith("src/main/java/") and path.endswith(".java")):
                    continue
                # src/main/java/org/apache/Foo.java -> org.apache.Foo
                rel = path[len("src/main/java/"):]
                fqn = rel[:-len(".java")].replace("/", ".").replace("\\", ".")
                classes.add(fqn)  # run_evosuite_for_classes filters inner classes later

            if not classes:
                print(f"[PR {pr_number}] No class names extracted; skipping EvoSuite.")
                append_shas(pr)
                continue

            # Run EvoSuite and capture raw stdout/stderr per class
            evosuite_logs = run_evosuite_for_classes(classes, ver, pr_number, cp, jar)

            # Move EvoSuite tests to a PR-specific folder & summarize
            generated_dir = move_evosuite_output(pr_number)
            summarize_evosuite_generation(
                pr=pr,
                generated_tests_dir=generated_dir,
                evosuite_logs=evosuite_logs,
                out_json=EVOSUITE_GEN_SUMMARY_FILE
            )

            if not os.path.exists(generated_dir):
                print(f"[PR {pr_number}] No tests generated; skipping base verification.")
                append_shas(pr)
                continue

            # MERGE phase: compile tests, run Surefire, classify
            replace_tests_in_project(generated_dir, PROJECT_TEST_DIR)
            merge_compile = compile_tests_only(pr_number, phase_label="merge")
            if not merge_compile["ok"]:
                merge_result = make_compile_error_result(pr_number, "merge", merge_compile["logs"])
            else:
                merge_result = run_tests_and_generate_coverage(pr_number, phase_label="merge")
                merge_result["compiled_ok"] = True

            # BASE phase: same tests on base commit
            base_result = verify_tests_on_base(pr, repo, generated_dir)

            # Persist
            append_shas(pr)
            append_tests_to_json(
                pr_data=pr,
                merge_result=merge_result,
                base_result=base_result,
                evosuite_logs=evosuite_logs,
                generated_tests_dir=generated_dir
            )

        except Exception:
            # Hard reset the repo so the next PR starts clean
            try:
                repo.git.reset("--hard")
                repo.git.clean("-fdx")
            except Exception:
                pass

            err_text = traceback.format_exc()
            print(f"[PR {pr_number}] ERROR:\n{err_text}")

            # Optional: write a per-PR error log file for debugging
            try:
                with open(f"error_pr_{pr_number}.log", "w", encoding="utf-8") as f:
                    f.write(err_text)
            except Exception:
                pass

            # Record as not processed in the main JSON
            append_not_processed(pr, err_text)

            # Also persist SHAs we do know (helps auditing)
            try:
                append_shas(pr)
            except Exception:
                pass

            # Continue with the next PR
            continue



if __name__ == "__main__":
    main()

