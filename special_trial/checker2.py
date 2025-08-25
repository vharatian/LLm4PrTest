#!/usr/bin/env python3
"""
Minimal EvoSuite Test Pipeline (external tests → copy into repo, then compile/run)

Per PR (from lang3.json):
  - Search *outside* the repo for: evosuite-tests-*_pr_<PR>
  - If found, copy:
        *_ESTest.java
        *_ESTest_scaffolding.java
    into <repo>/src/test/java (preserving package structure)
  - Edit pom.xml to add ONLY:
      junit:junit:4.12 (test)
      org.evosuite:evosuite-standalone-runtime:1.0.6 (test)
      org.junit.vintage:junit-vintage-engine:<detected jupiter version> (test)
  - Merge phase: compile-only → if ok, run tests; then checkout base, re-copy tests,
    re-edit pom, and repeat compile/run
  - Append one row to generated_evosuite_tests.json with:
      is_evosuite_tests_found, merge_phase.is_compiled, merge_phase.is_tests_passed,
      base_phase.is_compiled, base_phase.is_tests_passed
"""

import os
import re
import glob
import json
import shutil
import subprocess
import xml.etree.ElementTree as ET
from git import Repo

# --- Config ---
REPO_URL = "https://github.com/apache/commons-imaging.git"  # change if needed
PROJECT_NAME = "commons-imaging"                             # used for preferred folder match
LOCAL_REPO_PATH = PROJECT_NAME

PR_LIST_FILE = "imaging.json"
OUTPUT_JSON_FILE = "generated_evosuite_tests.json"

PROJECT_TEST_DIR = os.path.join(LOCAL_REPO_PATH, "src", "test", "java")

# Maven POM namespace
NAMESPACE = {"mvn": "http://maven.apache.org/POM/4.0.0"}
ET.register_namespace('', NAMESPACE["mvn"])

# Fixed versions
JUNIT4_VERSION = "4.12"
EVOSUITE_RUNTIME_VERSION = "1.0.6"

# ========================= Git Helpers =========================

def clone_repo():
    if not os.path.exists(LOCAL_REPO_PATH):
        print("Cloning repository...")
        return Repo.clone_from(REPO_URL, LOCAL_REPO_PATH)
    print("Using existing local repository.")
    return Repo(LOCAL_REPO_PATH)

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
    print(f"[PR {pr.get('pr_number')}] Checked out HEAD {head_sha}")

def checkout_commit(commit_sha, repo, label="base"):
    print(f"Checking out {label} commit {commit_sha}…")
    repo.git.reset('--hard')
    repo.git.clean('-fdx')
    repo.git.fetch("--all", "--tags")
    repo.git.checkout(commit_sha)

# ========================= External tests → copy into repo =========================

def find_external_evosuite_dir(pr_number: int) -> str | None:
    """
    Look in current working dir for folders like:
      evosuite-tests-<anything>_pr_<pr_number>
    Prefers exact: evosuite-tests-<PROJECT_NAME>_pr_<pr_number>
    """
    preferred = f"evosuite-tests-{PROJECT_NAME}_pr_{pr_number}"
    if os.path.isdir(preferred):
        return os.path.abspath(preferred)

    candidates = []
    # Robust patterns (fixes the earlier glob issue)
    for pat in (f"evosuite-tests-*_pr_{pr_number}", f"evosuite-tests-*{pr_number}"):
        for p in glob.glob(pat):
            if os.path.isdir(p):
                candidates.append(os.path.abspath(p))
    return sorted(candidates)[0] if candidates else None

def _strip_leading_src_test_java(rel_path: str) -> str:
    parts = rel_path.split(os.sep)
    if len(parts) >= 3 and parts[0] == "src" and parts[1] == "test" and parts[2] == "java":
        return os.path.join(*parts[3:]) if len(parts) > 3 else ""
    return rel_path

def copy_evosuite_tests_into_project(evosuite_dir: str) -> int:
    """
    Copy both *_ESTest.java and *_ESTest_scaffolding.java into <repo>/src/test/java,
    preserving package structure. Returns count of files copied.
    """
    copied = 0
    for root, _, files in os.walk(evosuite_dir):
        # Copy scaffolding first, then tests (helps when tests reference scaffolding types)
        files_sorted = sorted(
            files,
            key=lambda n: 0 if n.endswith("_ESTest_scaffolding.java") else 1
        )
        for f in files_sorted:
            if not (f.endswith("_ESTest.java") or f.endswith("_ESTest_scaffolding.java")):
                continue
            rel = os.path.relpath(os.path.join(root, f), evosuite_dir)
            rel = _strip_leading_src_test_java(rel)
            if not rel:
                continue
            dest = os.path.join(PROJECT_TEST_DIR, rel)
            os.makedirs(os.path.dirname(dest), exist_ok=True)
            shutil.copy2(os.path.join(root, f), dest)
            copied += 1
    return copied

def ensure_external_tests_copied(pr_number: int) -> bool:
    """
    If any *_ESTest.java already exists in src/test/java → True.
    Else, find external folder and copy tests in. Return True iff tests present after.
    """
    if os.path.isdir(PROJECT_TEST_DIR):
        for _, _, files in os.walk(PROJECT_TEST_DIR):
            if any(f.endswith("_ESTest.java") for f in files):
                return True
    ext = find_external_evosuite_dir(pr_number)
    if not ext:
        return False
    return copy_evosuite_tests_into_project(ext) > 0

# ========================= POM Editing (ONLY the 3 deps) =========================

def E(tag: str) -> str:
    return f"{{{NAMESPACE['mvn']}}}{tag}"

def _find_ns(parent, tag):
    return parent.find(f"mvn:{tag}", NAMESPACE)

def _findall_ns(parent, tag):
    return parent.findall(f"mvn:{tag}", NAMESPACE)

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
        ET.SubElement(dep, E("groupId")).text    = gid
        ET.SubElement(dep, E("artifactId")).text = aid
    _set_child_text(dep, "version", version)
    _set_child_text(dep, "scope", scope)
    tree.write(pom_path, encoding="utf-8", xml_declaration=True)

# ========================= Strict JUnit (Jupiter) detection via mvn dependency:list =========================

_VERSION_LINE = re.compile(
    r"""^\s*(?:\[INFO\]\s*)?          # optional [INFO]
         (?P<g>org\.junit\.jupiter)   # groupId
         :(?P<a>[\w\.\-]+)            # artifactId
         :(?P<p>[\w\.\-]+)            # packaging
         :(?P<v>[0-9]+(?:\.[0-9]+){0,3}(?:[-A-Za-z0-9\._]+)?) # version
         (?::(?P<s>\w+))?             # optional scope
         \s*$""",
    re.VERBOSE
)

def _semver_key(v: str):
    nums = [int(x) for x in re.split(r'[^0-9]+', v) if x.isdigit()]
    return (*nums[:4], 10 if re.search(r'[A-Za-z-]', v) is None else 0)

def _run_mvn_dependency_list_for_jupiter() -> str:
    """
    Run `mvn dependency:list` and return stdout+stderr.
    Only limit by groupId (org.junit.jupiter). Do NOT use -q; it may hide needed lines.
    """
    cmd = [
        "mvn", "dependency:list",
        "-DskipTests",
        "-Drat.skip=true",
        "-DincludeGroupIds=org.junit.jupiter",
        "-DexcludeTransitive=false",
    ]
    res = subprocess.run(cmd, cwd=LOCAL_REPO_PATH, text=True,
                         stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    return (res.stdout or "") + "\n" + (res.stderr or "")

def detect_jupiter_version_via_dep_list_strict() -> str | None:
    """
    Parse mvn dependency:list output and return highest org.junit.jupiter version.
    Returns None if no org.junit.jupiter artifact is found.
    """
    
    out = _run_mvn_dependency_list_for_jupiter()
    candidates = set()
    for line in out.splitlines():
        m = _VERSION_LINE.match(line)
        if m:
            ver = m.group("v").strip()
            if ver:
                candidates.add(ver)
    if not candidates:
        return None
    return sorted(candidates, key=_semver_key, reverse=True)[0]

def add_test_deps_detecting_vintage_from_dep_list_strict() -> dict | None:
    """
    STRICT on Jupiter:
      - Detect a JUnit Jupiter version via dependency:list.
      - If NOT found -> return None (caller should skip PR and log).
      - If found -> add ONLY these test deps, with versions:
          * junit:junit:4.12
          * org.evosuite:evosuite-standalone-runtime:<EVOSUITE_RUNTIME_VERSION>
          * org.junit.vintage:junit-vintage-engine:<detected_jupiter_version>
      - Return a dict of versions used.
    """
    pom_path = os.path.join(LOCAL_REPO_PATH, "pom.xml")
    if not os.path.exists(pom_path):
        raise FileNotFoundError(pom_path)

    jupiter_ver = detect_jupiter_version_via_dep_list_strict()
    if not jupiter_ver:
        return None  # main() will skip this PR

    ensure_dep_with_version(pom_path, "junit", "junit", JUNIT4_VERSION, scope="test")
    ensure_dep_with_version(pom_path, "org.evosuite", "evosuite-standalone-runtime",
                            EVOSUITE_RUNTIME_VERSION, scope="test")
    ensure_dep_with_version(pom_path, "org.junit.vintage", "junit-vintage-engine",
                            jupiter_ver, scope="test")

    return {"jupiter": jupiter_ver, "vintage": jupiter_ver, "junit4": JUNIT4_VERSION}

# ========================= Maven Steps =========================

def compile_tests_only() -> bool:
    cmd = [
        "mvn", "clean", "test-compile",
        "-Drat.skip=true",
        "-DskipTests=true",
        "-Dmaven.test.skip=false",
        "-Dsurefire.printSummary=true",
    ]
    print("> " + " ".join(cmd))
    res = subprocess.run(cmd, cwd=LOCAL_REPO_PATH)
    return res.returncode == 0

def run_tests_estest_only() -> bool:
    cmd = [
        "mvn", "clean", "test",
        "-Dtest=*ESTest",
        "-DfailIfNoTests=false",   # don't fail build if no matching tests are found
        "-Drat.skip=true",
    ]
    print("> " + " ".join(cmd))
    res = subprocess.run(cmd, cwd=LOCAL_REPO_PATH)
    return res.returncode == 0

# ========================= JSON persistence =========================

def append_result_row(pr_number, is_found,
                      merge_compiled, merge_passed,
                      base_compiled, base_passed,
                      is_junit_detected: bool | None = None,
                      detected_versions: dict | None = None,
                      skip_reason: str | None = None):
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
    import traceback

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
        versions = None  # detected versions for MERGE

        try:
            # --- MERGE (HEAD) ---
            checkout_pr_head_sha_with_refspecs(pr, repo)

            # STRICT JUnit/Jupiter detection via mvn dependency:list (no fallbacks)
            versions = add_test_deps_detecting_vintage_from_dep_list_strict()
            if versions is None:
                # Could not detect from mvn list -> record & skip PR
                append_result_row(
                    pr_number,
                    is_found=False,
                    merge_compiled=False,
                    merge_passed=None,
                    base_compiled=None,
                    base_passed=None,
                    is_junit_detected=False,
                    detected_versions=None,
                    skip_reason="jupiter_not_detected"
                )
                continue

            # Copy tests from external folder (if any) into repo (includes scaffolding)
            is_found = ensure_external_tests_copied(pr_number)
            if not is_found:
                # Still record junit detection so you can audit why it skipped
                append_result_row(
                    pr_number, is_found,
                    merge_compiled, merge_passed,
                    base_compiled, base_passed,
                    is_junit_detected=True,
                    detected_versions=versions
                )
                print(f"[PR {pr_number}] No external *_ESTest.java found → skipping this PR.")
                continue

            # Compile then test on MERGE
            merge_compiled = compile_tests_only()
            if merge_compiled:
                merge_passed = run_tests_estest_only()
            else:
                append_result_row(
                    pr_number, is_found,
                    merge_compiled, merge_passed,
                    base_compiled, base_passed,
                    is_junit_detected=True,
                    detected_versions=versions
                )
                print(f"[PR {pr_number}] Merge compile failed → skipping base.")
                continue

            if merge_passed is False:
                append_result_row(
                    pr_number, is_found,
                    merge_compiled, merge_passed,
                    base_compiled, base_passed,
                    is_junit_detected=True,
                    detected_versions=versions
                )
                print(f"[PR {pr_number}] Merge tests failed → skipping base.")
                continue

            # --- BASE ---
            base_sha = pr.get("base_sha") or pr.get("base_commit")
            if not base_sha:
                print(f"[PR {pr_number}] Missing base_sha/base_commit; skipping base phase.")
            else:
                checkout_commit(base_sha, repo, label="base")

                # Re-copy tests (repo was reset/cleaned)
                _ = ensure_external_tests_copied(pr_number)

                # STRICT detection for BASE too (train can differ on base)
                versions_base = add_test_deps_detecting_vintage_from_dep_list_strict()
                if versions_base is None:
                    base_compiled = None
                    base_passed = None
                    append_result_row(
                        pr_number, is_found,
                        merge_compiled, merge_passed,
                        base_compiled, base_passed,
                        is_junit_detected=True,
                        detected_versions=versions,
                        skip_reason="base_jupiter_not_detected"
                    )
                    continue

                base_compiled = compile_tests_only()
                if base_compiled:
                    base_passed = run_tests_estest_only()
                else:
                    print(f"[PR {pr_number}] Base compile failed → not running tests.")

            # Final record for this PR
            append_result_row(
                pr_number, is_found,
                merge_compiled, merge_passed,
                base_compiled, base_passed,
                is_junit_detected=True,
                detected_versions=versions
            )

        except Exception as e:
            # Log and record partial state, then hard reset/clean and continue
            print(f"[PR {pr_number}] ERROR: {e}\n{traceback.format_exc()}")
            try:
                append_result_row(
                    pr_number, is_found,
                    merge_compiled, merge_passed,
                    base_compiled, base_passed,
                    is_junit_detected=(versions is not None),
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
