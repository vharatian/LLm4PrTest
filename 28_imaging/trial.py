#!/usr/bin/env python3
"""
Combined EvoSuite Pipeline (Generation from PR changes + Evaluation/JSON like v2)

This merges:
  • From script #1: Only the **generation** logic (class selection, packaging, classpath,
    run EvoSuite per class, move output into a PR‑specific folder).
  • From script #2: The **evaluation** logic (copy tests into repo, POM edits with
    namespaced XML, JUnit/Vintage detection, compile/test on MERGE and BASE, and 
    write a simple JSON row per PR like v2).

Output JSON (append‑only): generated_evosuite_tests.json
Each row:
{
  "pr_number": <int>,
  "is_evosuite_tests_found": true/false,
  "merge_phase": { "is_compiled": bool, "is_tests_passed": bool|None },
  "base_phase" : { "is_compiled": bool|None, "is_tests_passed": bool|None },
  "is_junit_detected": bool,
  "detected_versions": {"jupiter": str, "vintage": str, "junit4": str} | {"evosuite_only": true, ...} ,
  "skip_reason": "exception" | <optional>
}

Notes / Improvements vs v2:
  - Fallback when no Jupiter is detected now adds **JUnit 4** (so EvoSuite JUnit4 tests compile) + EvoSuite runtime.
  - Test pattern includes both *ESTest and *ES_Test (and excludes scaffolding implicitly).
  - POM edits are namespace‑correct and idempotent.

Requirements:
  - Java + Maven installed and on PATH.
  - evosuite.jar present in working directory (EVOSUITE_JAR path below).
  - PR list JSON has entries with: pr_number, head_sha, base_sha|base_commit, changed_files[*].
    Each changed_files item: {"filename": "src/main/java/...Foo.java", "do_experiment": true|false}

Configure the section marked # --- Config --- to switch project/repo/PR list.
"""

import os
import re
import glob
import json
import shutil
import subprocess
import traceback
import xml.etree.ElementTree as ET
from typing import Optional, Iterable
from git import Repo

# --- Config ---
REPO_URL = "https://github.com/apache/commons-imaging.git"  # change if needed
PROJECT_NAME = "commons-imaging"                              # used for folder names
LOCAL_REPO_PATH = PROJECT_NAME

PR_LIST_FILE = "imaging.json"                                 # list of PR dicts
OUTPUT_JSON_FILE = "generated_evosuite_tests.json"            # append-only output
VERSION_LOG_FILE = "pr_versions.txt"                          # optional audit log

PROJECT_TEST_DIR = os.path.join(LOCAL_REPO_PATH, "src", "test", "java")

# Maven POM namespace
NAMESPACE = {"mvn": "http://maven.apache.org/POM/4.0.0"}
ET.register_namespace('', NAMESPACE["mvn"])  # pretty print with default ns

# Fixed versions
JUNIT4_VERSION = "4.13.2"              # use up-to-date JUnit 4
EVOSUITE_RUNTIME_VERSION = "1.0.6"

# EvoSuite executable (jar) in CWD
EVOSUITE_JAR = "evosuite.jar"

# ===== Utility: XML ns helpers =====

def E(tag: str) -> str:
    return f"{{{NAMESPACE['mvn']}}}{tag}"


def _find_ns(parent, tag):
    return parent.find(f"mvn:{tag}", NAMESPACE)


def _findall_ns(parent, tag):
    return parent.findall(f"mvn:{tag}", NAMESPACE)


# ========================= Git Helpers =========================

def clone_repo() -> Repo:
    if not os.path.exists(LOCAL_REPO_PATH):
        print("Cloning repository…")
        return Repo.clone_from(REPO_URL, LOCAL_REPO_PATH)
    print("Using existing local repository.")
    return Repo(LOCAL_REPO_PATH)


def ensure_pr_refspecs():
    """Ensure git is configured to fetch PR refs from GitHub"""
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
    """
    Simplified version matching the second script - fails fast if SHA not found.
    No fallback logic.
    """
    ensure_pr_refspecs()
    repo.git.fetch("--all", "--tags")
    repo.git.reset("--hard")
    repo.git.clean("-fdx")
    
    head_sha = pr.get("head_sha")
    if not head_sha:
        raise ValueError("PR JSON missing 'head_sha'")
    
    # Verify SHA exists locally
    cp = subprocess.run(
        ["git", "rev-parse", "--verify", f"{head_sha}^{{commit}}"],
        cwd=LOCAL_REPO_PATH, text=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE
    )
    if cp.returncode != 0:
        raise RuntimeError(f"Commit not found locally after fetch: {head_sha}\n{cp.stderr or cp.stdout}")
    
    # Check out the SHA
    repo.git.checkout(head_sha)
    print(f"Checked out PR HEAD (by SHA): {head_sha}")


def checkout_commit(commit_sha, repo):
    """
    Checkout a specific commit for base testing.
    This function is already the same in both scripts.
    """
    print(f"Checking out commit {commit_sha}...")
    repo.git.reset('--hard')
    repo.git.clean('-fdx')
    repo.git.fetch("--all", "--tags")
    repo.git.checkout(commit_sha)


# ========================= Script #1: Generation logic =========================

def check_java_version():
    res = subprocess.run(["java", "-version"], stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True)
    print((res.stderr or res.stdout or "").strip())


def compile_project():
    print("Compiling project (skip RAT)…")
    res = subprocess.run(["mvn", "clean", "compile", "-Drat.skip=true"], cwd=LOCAL_REPO_PATH)
    if res.returncode != 0:
        raise RuntimeError("mvn compile failed")


def build_classpath(pr_number: int) -> str:
    print("Building test classpath…")
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
    cp_new = f"classpath_pr_{pr_number}.txt"
    shutil.move(cp_repo, cp_new)
    with open(cp_new, 'r', encoding='utf-8') as f:
        classpath = f.read().strip()
    classes_dir = os.path.join(LOCAL_REPO_PATH, "target", "classes")
    return classpath + os.pathsep + classes_dir if os.path.exists(classes_dir) else classpath


def _pick_main_jar_from_target(target_dir: str) -> Optional[str]:
    if not os.path.isdir(target_dir):
        return None
    jars = [j for j in os.listdir(target_dir) if j.endswith('.jar')]
    # prefer non-classifier main jar
    jars = [j for j in jars if all(x not in j for x in ["-tests", "-sources", "-javadoc"])]
    if not jars:
        return None
    # pick longest common pattern: artifactId-version.jar
    jars.sort(key=len)  # shortest first; then prefer last for more specific
    return os.path.join(target_dir, jars[-1])


def package_project() -> str:
    print("Packaging project (skip tests & RAT)…")
    subprocess.run(["mvn", "clean", "package", "-Drat.skip=true", "-Dmaven.test.skip=true"], cwd=LOCAL_REPO_PATH, check=True)
    target_dir = os.path.join(LOCAL_REPO_PATH, "target")
    jar = _pick_main_jar_from_target(target_dir)
    if not jar:
        raise RuntimeError("No suitable jar found in target/")
    return jar


def get_project_version() -> str:
    res = subprocess.run(
        ["mvn", "help:evaluate", "-Dexpression=project.version", "-q", "-DforceStdout"],
        cwd=LOCAL_REPO_PATH, text=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE
    )
    return (res.stdout or "").strip()


def write_version_info(pr_number: int, version: str):
    with open(VERSION_LOG_FILE, 'a', encoding='utf-8') as f:
        f.write(f"PR {pr_number}: {version}\n")


def extract_changed_fqcns(pr: dict) -> Iterable[str]:
    fqcns = set()
    for file in pr.get("changed_files", []) or []:
        if not file.get("do_experiment"):
            continue
        path = file.get("filename", "")
        if not (path.startswith("src/main/java/") and path.endswith(".java")):
            continue
        rel = path[len("src/main/java/"):]
        fqn = rel[:-len(".java")].replace("/", ".").replace("\\", ".")
        fqcns.add(fqn)
    return sorted(fqcns)


def run_evosuite_for_classes(class_names, pr_number, project_classpath, project_jar):
    """
    Run EvoSuite per class and stream output directly to the terminal
    while also writing it to evosuite_logs/<class>_pr_<PR>.log
    """
    if not os.path.exists(EVOSUITE_JAR):
        raise FileNotFoundError(f"EvoSuite jar not found: {EVOSUITE_JAR}")

    os.makedirs("evosuite_logs", exist_ok=True)

    # Only outer classes (skip inner $)
    targets = [c for c in class_names if '$' not in c]
    targets.sort()

    for cls in targets:
        safe = re.sub(r'[^A-Za-z0-9_.-]+', '_', cls)
        log_path = os.path.join("evosuite_logs", f"evosuite_{safe}_pr_{pr_number}.log")

        cmd = [
            "java", "-jar", EVOSUITE_JAR,
            "-target", project_jar,
            "-projectCP", project_classpath,
            "-Dsearch_budget=120",
            "-Duse_separate_classloader=false",
            "-Dalgorithm=DynaMOSA",
            "-class", cls,
        ]

        print(f"[PR {pr_number}] EvoSuite → {cls}")
        print("> " + " ".join(cmd), flush=True)

        # Stream combined stdout+stderr to terminal and to a .log file
        with open(log_path, "w", encoding="utf-8") as lf:
            proc = subprocess.Popen(
                cmd,
                stdout=subprocess.PIPE,
                stderr=subprocess.STDOUT,
                text=True,
                bufsize=1  # line-buffered
            )
            for line in proc.stdout:
                lf.write(line)
                print(line, end="", flush=True)
            rc = proc.wait()

        if rc != 0:
            print(f"[PR {pr_number}] EvoSuite exit={rc} for {cls} (see {log_path})", flush=True)
        else:
            print(f"[PR {pr_number}] EvoSuite done for {cls} (log: {log_path})", flush=True)



def move_evosuite_output(pr_number: int, default_folder: str = "evosuite-tests") -> Optional[str]:
    """
    Move EvoSuite output to a PR-specific folder.
    Simplified version without unnecessary complexity.
    """
    dest = f"{default_folder}-{PROJECT_NAME}_pr_{pr_number}"
    
    # Small delay to ensure EvoSuite finished writing
    
    
    if os.path.exists(default_folder):
        if os.path.exists(dest):
            shutil.rmtree(dest)
        try:
            shutil.move(default_folder, dest)
            print(f"[PR {pr_number}] Moved {default_folder} -> {dest}")
            return os.path.abspath(dest)
        except Exception as e:
            print(f"[PR {pr_number}] Error moving evosuite output: {e}")
            return None
    else:
        print(f"[PR {pr_number}] No {default_folder} directory found")
        return None


# ========================= Script #2: Evaluation logic =========================

# --- External tests → copy into repo ---

def _strip_leading_src_test_java(rel_path: str) -> str:
    parts = rel_path.split(os.sep)
    if len(parts) >= 3 and parts[0] == "src" and parts[1] == "test" and parts[2] == "java":
        return os.path.join(*parts[3:]) if len(parts) > 3 else ""
    return rel_path


def copy_evosuite_tests_into_project(evosuite_dir: str) -> int:
    """
    Replace tests in project with EvoSuite generated tests.
    Uses the simpler logic from the second script.
    """
    if not os.path.exists(evosuite_dir):
        print(f"ERROR: EvoSuite directory doesn't exist: {evosuite_dir}")
        return 0
    
    # Count test files before copying
    test_count = 0
    for root, _, files in os.walk(evosuite_dir):
        for f in files:
            if (f.endswith("_ESTest.java") or f.endswith("ES_Test.java") or 
                f.endswith("_ESTest_scaffolding.java")):
                test_count += 1
    
    if test_count == 0:
        print(f"[WARNING] No test files found in {evosuite_dir}")
        return 0
    
    print(f"Copying {test_count} test files from {evosuite_dir} to {PROJECT_TEST_DIR}")
    
    # Ensure destination exists
    os.makedirs(PROJECT_TEST_DIR, exist_ok=True)
    
    # Copy all files preserving structure (like replace_tests_in_project does)
    copied = 0
    for root, _, files in os.walk(evosuite_dir):
        # Get relative path from source
        rel = os.path.relpath(root, evosuite_dir)
        
        # Determine output directory
        out_dir = os.path.join(PROJECT_TEST_DIR, rel) if rel != "." else PROJECT_TEST_DIR
        
        # Create output directory if needed
        if not os.path.exists(out_dir):
            os.makedirs(out_dir, exist_ok=True)
        
        # Copy each file
        for f in files:
            if (f.endswith("_ESTest.java") or f.endswith("ES_Test.java") or 
                f.endswith("_ESTest_scaffolding.java")):
                src_file = os.path.join(root, f)
                dst_file = os.path.join(out_dir, f)
                try:
                    shutil.copy2(src_file, dst_file)
                    copied += 1
                    print(f"  Copied: {os.path.relpath(dst_file, PROJECT_TEST_DIR)}")
                except Exception as e:
                    print(f"  ERROR copying {f}: {e}")
    
    print(f"Total files copied: {copied}")
    return copied


# --- POM Editing (ONLY the 3 deps) ---

def _ensure_dependencies(root):
    deps = _find_ns(root, "dependencies")
    if deps is None:
        deps = ET.SubElement(root, E("dependencies"))
    return deps


def _find_dependency(deps_elem, gid: str, aid: str):
    for d in _findall_ns(deps_elem, "dependency"):
        g = _find_ns(d, "groupId")
        a = _find_ns(d, "artifactId")
        if g is not None and a is not None and g.text == gid and a.text == aid:
            return d
    return None


def _set_child_text(elem, child_tag, text):
    c = _find_ns(elem, child_tag)
    if c is None:
        c = ET.SubElement(elem, E(child_tag))
    c.text = text


def ensure_dep_with_version(pom_path: str, gid: str, aid: str, version: str, scope: str = "test"):
    tree = ET.parse(pom_path)
    root = tree.getroot()
    deps = _ensure_dependencies(root)
    dep = _find_dependency(deps, gid, aid)
    if dep is None:
        dep = ET.SubElement(deps, E("dependency"))
        ET.SubElement(dep, E("groupId")).text = gid
        ET.SubElement(dep, E("artifactId")).text = aid
    _set_child_text(dep, "version", version)
    _set_child_text(dep, "scope", scope)
    tree.write(pom_path, encoding="utf-8", xml_declaration=True)


# --- Strict-ish Jupiter detection via mvn dependency:list ---

def _semver_key(v: str):
    nums = [int(x) for x in re.split(r'[^0-9]+', v) if x.isdigit()]
    return (*nums[:4], 10 if re.search(r'[A-Za-z-]', v) is None else 0)


def _run_mvn_dependency_list_for_jupiter() -> str:
    cmd = [
        "mvn", "dependency:list",
        "-DskipTests",
        "-Drat.skip=true",
        "-DincludeGroupIds=org.junit.jupiter",
        "-DexcludeTransitive=false",
    ]
    res = subprocess.run(cmd, cwd=LOCAL_REPO_PATH, text=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    return (res.stdout or "") + "\n" + (res.stderr or "")


def detect_jupiter_version_via_dep_list_strict() -> Optional[str]:
    out = _run_mvn_dependency_list_for_jupiter()
    candidates = set()
    # match the aggregator first; if not found, also try engine/api
    patterns = [
        r"org\.junit\.jupiter:junit-jupiter:jar:([\w.\-]+)",
        r"org\.junit\.jupiter:junit-jupiter-engine:jar:([\w.\-]+)",
        r"org\.junit\.jupiter:junit-jupiter-api:jar:([\w.\-]+)",
    ]
    for line in out.splitlines():
        for pat in patterns:
            m = re.search(pat, line)
            if m:
                ver = m.group(1).strip()
                if ver:
                    candidates.add(ver)
    if not candidates:
        return None
    return sorted(candidates, key=_semver_key, reverse=True)[0]


def add_test_deps_detecting_vintage_from_dep_list() -> dict:
    """
    Try to detect a JUnit Jupiter version via `mvn dependency:list`.

    - If FOUND:
        Add exactly:
          * junit:junit:<JUNIT4_VERSION>                      (test)
          * org.evosuite:evosuite-standalone-runtime:1.0.6    (test)
          * org.junit.vintage:junit-vintage-engine:<jupiter>  (test)
        Return versions dict with jupiter/vintage/junit4.

    - If NOT FOUND:
        Add:
          * junit:junit:<JUNIT4_VERSION>                      (test)
          * org.evosuite:evosuite-standalone-runtime:1.0.6    (test)
        Return {"evosuite_only": True, "junit4": <>, "evosuite_runtime": <>}.
    """
    pom_path = os.path.join(LOCAL_REPO_PATH, "pom.xml")
    if not os.path.exists(pom_path):
        raise FileNotFoundError(pom_path)

    jupiter_ver = detect_jupiter_version_via_dep_list_strict()
    # Always ensure JUnit4 + EvoSuite runtime
    ensure_dep_with_version(pom_path, "junit", "junit", JUNIT4_VERSION, scope="test")
    ensure_dep_with_version(pom_path, "org.evosuite", "evosuite-standalone-runtime", EVOSUITE_RUNTIME_VERSION, scope="test")

    if not jupiter_ver:
        return {"evosuite_only": True, "junit4": JUNIT4_VERSION, "evosuite_runtime": EVOSUITE_RUNTIME_VERSION}

    # Also Vintage when Jupiter exists
    ensure_dep_with_version(pom_path, "org.junit.vintage", "junit-vintage-engine", jupiter_ver, scope="test")
    return {"jupiter": jupiter_ver, "vintage": jupiter_ver, "junit4": JUNIT4_VERSION}


# --- Maven Steps ---

def package_without_running_tests() -> bool:
    """
    Run `mvn clean test-compile` without executing tests,
    but still compiling main + test sources.
    Prints all Maven output directly to the console.
    Returns True if the command succeeded, False otherwise.
    """
    cmd = [
        "mvn", "clean", "test-compile",
        "-Drat.skip=true",
        "-DskipTests=true",          # don't run tests, but do compile them
        "-Dmaven.test.skip=false",   # ensure test sources are compiled
    ]

    print("> " + " ".join(cmd))
    res = subprocess.run(cmd, cwd=LOCAL_REPO_PATH)  # output goes to console
    return res.returncode == 0

def run_tests_estest_only() -> bool:
    cmd = [
        "mvn", "clean", "test",
        "-Dtest=*ESTest,*ES_Test",   # include both patterns; scaffolding won't match
        "-DfailIfNoTests=false",
        "-Drat.skip=true",
        "-Dsurefire.printSummary=true",
    ]
    print("> " + " ".join(cmd))
    res = subprocess.run(cmd, cwd=LOCAL_REPO_PATH)
    return res.returncode == 0


# --- JSON persistence ---

def append_result_row(pr_number, is_found,
                      merge_compiled, merge_passed,
                      base_compiled, base_passed,
                      is_junit_detected: Optional[bool] = None,
                      detected_versions: Optional[dict] = None,
                      skip_reason: Optional[str] = None):
    entry = {
        "pr_number": pr_number,
        "is_evosuite_tests_found": bool(is_found),
        "merge_phase": {
            "is_compiled": bool(merge_compiled) if merge_compiled is not None else False,
            "is_tests_passed": (None if merge_passed is None else bool(merge_passed)),
        },
        "base_phase": {
            "is_compiled": (None if base_compiled is None else bool(base_compiled)),
            "is_tests_passed": (None if base_passed is None else bool(base_passed)),
        },
    }
    if is_junit_detected is not None:
        entry["is_junit_detected"] = bool(is_junit_detected)
    if detected_versions:
        entry["detected_versions"] = detected_versions
    if skip_reason:
        entry["skip_reason"] = skip_reason

    if os.path.exists(OUTPUT_JSON_FILE):
        try:
            data = json.load(open(OUTPUT_JSON_FILE, "r", encoding="utf-8"))
        except Exception:
            data = []
    else:
        data = []
    data.append(entry)
    json.dump(data, open(OUTPUT_JSON_FILE, "w", encoding="utf-8"), indent=2)


# ========================= Main =========================

def main():
    check_java_version()

    with open(PR_LIST_FILE, "r", encoding="utf-8") as f:
        pr_list = json.load(f)

    repo = clone_repo()

    for pr in pr_list:
        pr_number = pr.get("pr_number")
        if not pr_number:
            continue
        print(f"\n=== PR {pr_number} ===")

        is_found = False
        merge_compiled = False
        merge_passed = None
        base_compiled = None
        base_passed = None
        versions = None
        is_junit_detected = False

        try:
            # --- MERGE (HEAD): checkout ---
            checkout_pr_head_sha_with_refspecs(pr, repo)

            # --- GENERATION: package, classpath, run EvoSuite for changed classes ---
            compile_project()
            cp  = build_classpath(pr_number)
            jar = package_project()
            ver = get_project_version()
            if ver:
                write_version_info(pr_number, ver)

            targets = list(extract_changed_fqcns(pr))
            generated_dir = None
            if targets:
                run_evosuite_for_classes(targets, pr_number, cp, jar)
                generated_dir = move_evosuite_output(pr_number)
            else:
                print(f"[PR {pr_number}] No eligible changed classes → skipping generation.")

            # --- EVALUATION: POM deps, copy tests, compile/test ---
            versions = add_test_deps_detecting_vintage_from_dep_list()
            is_junit_detected = ("jupiter" in (versions or {}))

            # Handle copying of tests - simplified logic
            if generated_dir and os.path.isdir(generated_dir):
                # Direct copy from generated directory
                copied_count = copy_evosuite_tests_into_project(generated_dir)
                is_found = copied_count > 0
            else:
                # Fallback: look for existing test directories
                print(f"[PR {pr_number}] Looking for existing evosuite test directories...")
                preferred = f"evosuite-tests-{PROJECT_NAME}_pr_{pr_number}"
                
                # Try exact match first
                if os.path.isdir(preferred):
                    copied_count = copy_evosuite_tests_into_project(preferred)
                    is_found = copied_count > 0
                else:
                    # Try pattern matching
                    candidates = glob.glob(f"evosuite-tests-*_pr_{pr_number}")
                    if candidates:
                        # Use the first matching directory
                        test_dir = candidates[0]
                        print(f"[PR {pr_number}] Found test directory: {test_dir}")
                        copied_count = copy_evosuite_tests_into_project(test_dir)
                        is_found = copied_count > 0
                    else:
                        print(f"[PR {pr_number}] No evosuite test directories found")
                        is_found = False

            if not is_found:
                append_result_row(
                    pr_number, is_found,
                    merge_compiled, merge_passed,
                    base_compiled, base_passed,
                    is_junit_detected=is_junit_detected,
                    detected_versions=versions
                )
                print(f"[PR {pr_number}] No EvoSuite tests copied → skipping Maven steps.")
                continue
            
            # MERGE compile/test
            merge_compiled = package_without_running_tests()
            if merge_compiled:
                merge_passed = run_tests_estest_only()
            else:
                append_result_row(
                    pr_number, is_found,
                    merge_compiled, merge_passed,
                    base_compiled, base_passed,
                    is_junit_detected=is_junit_detected,
                    detected_versions=versions
                )
                print(f"[PR {pr_number}] Merge compile failed → skipping base.")
                continue

            if merge_passed is False:
                append_result_row(
                    pr_number, is_found,
                    merge_compiled, merge_passed,
                    base_compiled, base_passed,
                    is_junit_detected=is_junit_detected,
                    detected_versions=versions
                )
                print(f"[PR {pr_number}] Merge tests failed → skipping base.")
                continue

            # --- BASE phase ---
            base_sha = pr.get("base_sha") or pr.get("base_commit")
            if not base_sha:
                print(f"[PR {pr_number}] Missing base_sha/base_commit; skipping base phase.")
            else:
                checkout_commit(base_sha, repo)
                
                # Re-add deps for base
                versions_base = add_test_deps_detecting_vintage_from_dep_list()
                
                # Re-copy tests for base (using the same simplified logic)
                if generated_dir and os.path.isdir(generated_dir):
                    copy_evosuite_tests_into_project(generated_dir)
                else:
                    # Try to find the test directory again
                    preferred = f"evosuite-tests-{PROJECT_NAME}_pr_{pr_number}"
                    if os.path.isdir(preferred):
                        copy_evosuite_tests_into_project(preferred)
                    else:
                        candidates = glob.glob(f"evosuite-tests-*_pr_{pr_number}")
                        if candidates:
                            copy_evosuite_tests_into_project(candidates[0])

                base_compiled = package_without_running_tests()
                if base_compiled:
                    base_passed = run_tests_estest_only()
                else:
                    print(f"[PR {pr_number}] Base compile failed → not running tests.")

            # Final JSON row for this PR
            append_result_row(
                pr_number, is_found,
                merge_compiled, merge_passed,
                base_compiled, base_passed,
                is_junit_detected=is_junit_detected,
                detected_versions=versions
            )

        except Exception as e:
            print(f"[PR {pr_number}] ERROR: {e}\n{traceback.format_exc()}")
            try:
                append_result_row(
                    pr_number, is_found,
                    merge_compiled, merge_passed,
                    base_compiled, base_passed,
                    is_junit_detected=is_junit_detected,
                    detected_versions=versions if versions is not None else None,
                    skip_reason="exception"
                )
            except Exception:
                pass
            try:
                repo.git.reset("--hard")
                repo.git.clean("-fdx")
            except Exception:
                pass
            continue


if __name__ == "__main__":
    main()
