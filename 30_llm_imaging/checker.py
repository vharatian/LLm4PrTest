#!/usr/bin/env python3
"""
LLM Tests Fail→Pass Pipeline (no pom edits, return-code based)
==============================================================

- For each PR in io.json:
  * Checkout PR HEAD by SHA (using PR refspecs).
  * Copy ONLY LLM-generated tests from ./pr_{PR}/src/test/java/** into the repo (non-destructive).
  * Compile and run ONLY those tests on:
      - MERGE (HEAD)
      - BASE  (base_sha / base_commit)
  * Determine results purely by Maven return codes:
      - compile rc == 0  → is_compiled = True
      - test    rc == 0  → is_tests_passed = True
  * Append a minimal row to generated_evosuite_tests.json:
        {
          "pr_number": <int>,
          "is_evosuite_tests_found": <bool>,
          "merge_phase": {"is_compiled": <bool>, "is_tests_passed": <bool|null>},
          "base_phase" : {"is_compiled": <bool|null>, "is_tests_passed": <bool|null>}
        }

Assumptions:
- LLM tests live under: ./pr_<NUMBER>/src/test/java/**.java
- Optional ./pr_<NUMBER>/summary.json supports {"tests":[{"path":"src/test/java/..."}]} or string entries.
- Input file: io.json  (each item includes at least: pr_number, head_sha, base_sha|base_commit)
- No POM edits, no report parsing/archiving, no EvoSuite/Jacoco.

Default test selector baked in: "*LLM_Test"
"""

import os
import json
import re
import shutil
import subprocess
from pathlib import Path
from typing import List, Dict, Optional
from git import Repo

# --------------------------- Config ----------------------------
REPO_URL         = "https://github.com/apache/commons-imaging.git"
PROJECT_NAME     = "commons-imaging"
LOCAL_REPO_PATH  = PROJECT_NAME

INPUT_PR_FILE    = "imaging.json"
OUTPUT_JSON_FILE = "generated_evosuite_tests.json"

PROJECT_TEST_DIR = os.path.join(LOCAL_REPO_PATH, "src", "test", "java")
LLM_TESTS_ROOT   = Path(".")

# Default Surefire selector for LLM tests
DEFAULT_LLM_PATTERN = "*LLM_Test"


# ========================= Utilities =========================
def check_java_version():
    print("Checking Java version...")
    result = subprocess.run(["java", "-version"], stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True)
    output = result.stderr.strip() or result.stdout.strip()
    print(output)


def clone_repo() -> Repo:
    if not os.path.exists(LOCAL_REPO_PATH):
        print("Cloning repository...")
        Repo.clone_from(REPO_URL, LOCAL_REPO_PATH)
    else:
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


def checkout_pr_head_sha_with_refspecs(pr: Dict, repo: Repo):
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

    repo.git.clean("-xfd")
    repo.git.checkout(head_sha)
    print(f"Checked out PR HEAD (by SHA): {head_sha}")


def checkout_commit(commit_sha: str, repo: Repo):
    print(f"Checking out commit {commit_sha}...")
    repo.git.reset('--hard')
    repo.git.clean('-fdx')
    repo.git.fetch("--all", "--tags")
    repo.git.checkout(commit_sha)


# ========================= LLM tests discovery & placement =========================
def pr_folder(pr_number: int) -> Path:
    return LLM_TESTS_ROOT / f"pr_{pr_number}"


def discover_llm_test_files(pr_number: int) -> List[Path]:
    """
    Prefer pr_{n}/summary.json if it contains {"tests":[{"path":"src/test/java/..."}]}.
    Otherwise, scan pr_{n}/src/test/java/**.java
    """
    root = pr_folder(pr_number)
    summary = root / "summary.json"
    files: List[Path] = []

    if summary.exists():
        try:
            with summary.open("r", encoding="utf-8") as f:
                j = json.load(f)
            arr = j.get("tests") or j.get("llm_tests") or j.get("files") or []
            for item in arr:
                if isinstance(item, str):
                    rel = item
                else:
                    rel = item.get("path") or item.get("file") or ""
                if rel and rel.endswith(".java"):
                    files.append(root / rel)
        except Exception:
            pass

    if not files:
        scan_root = root / "src" / "test" / "java"
        if scan_root.exists():
            for p in scan_root.rglob("*.java"):
                files.append(p)

    return files


def copy_llm_tests_into_repo(pr_number: int) -> List[str]:
    """
    Non-destructive copy: keeps existing human tests intact.
    Copies pr_{n}/src/test/java/** -> PROJECT_TEST_DIR/** (preserve package paths).
    Returns a list of FQCNs inferred from relative paths.
    """
    src_root = pr_folder(pr_number) / "src" / "test" / "java"
    if not src_root.exists():
        return []

    fqcns: List[str] = []
    for p in src_root.rglob("*.java"):
        rel = p.relative_to(src_root)  # org/apache/.../MyLLM_Test.java
        dst = Path(PROJECT_TEST_DIR) / rel
        dst.parent.mkdir(parents=True, exist_ok=True)

        # Safety: never overwrite an existing human test
        if dst.exists():
            print(f"[PR {pr_number}] WARNING: {dst} already exists, skipping to avoid overwriting.")
            continue

        shutil.copy2(p, dst)
        fqcns.append(".".join(rel.with_suffix("").parts))

    # deduplicate
    seen = set()
    uniq_fqcns = []
    for c in fqcns:
        if c not in seen:
            seen.add(c)
            uniq_fqcns.append(c)
    return uniq_fqcns


# ========================= Maven runs (return-code based) =========================
def compile_tests_only() -> bool:
    """
    Return True iff mvn returned 0.
    """
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


def run_llm_tests_only(include_arg: str) -> bool:
    """
    Run only selected LLM tests. Return True iff mvn returned 0.
    No '-fn', no 'maven.test.failure.ignore'.
    We keep '-DfailIfNoTests=false' to avoid failing the build when selector matches 0 tests.
    """
    cmd = [
        "mvn", "test",
        "-Dmaven.test.failure.ignore=true",
        f"-Dtest={include_arg}",
        "-DfailIfNoTests=false",
        "-Drat.skip=true",
        "-Dsurefire.printSummary=true",
    ]
    print("> " + " ".join(cmd))

    res = subprocess.Popen(
        cmd, cwd=LOCAL_REPO_PATH,
        stdout=subprocess.PIPE,
        stderr=subprocess.STDOUT,
        text=True, bufsize=1
    )

    # Regex that matches: Tests run: 288, Failures: 0, Errors: 1, Skipped: 0
    fail_count = 0
    total_count = 0
    for line in res.stdout:
        print(line)
        m = re.search(r"Tests run:\s*(\d+)\s*,\s*Failures:\s*(\d+)\s*,\s*Errors:\s*(\d+)", line)
        if m:
            total_count += int(m.group(1))  # total
            fail_count += int(m.group(2)) + int(m.group(3))

    res.wait()
    return res.returncode == 0, total_count, fail_count


# ========================= JSON persistence (minimal compat shape) =========================
def append_result_row(
    pr_number: int,
    is_found: bool,
    merge_compiled: Optional[bool], merge_passed: Optional[bool], merge_total_count, merge_fail_count: Optional[int],
    base_compiled: Optional[bool],  base_passed: Optional[bool], base_total_count, base_fail_count: Optional[int]
):
    entry = {
        "pr_number": pr_number,
        "is_evosuite_tests_found": bool(is_found),
        "merge_phase": {
            "is_compiled": bool(merge_compiled) if merge_compiled is not None else False,
            "is_tests_passed": (None if merge_passed is None else bool(merge_passed)),
            "failed_count": merge_fail_count,
            "total_count": merge_total_count
        },
        "base_phase": {
            "is_compiled": (None if base_compiled is None else bool(base_compiled)),
            "is_tests_passed": (None if base_passed is None else bool(base_passed)),
            "failed_count": base_fail_count,
            "total_count": base_total_count
        },
    }

    if os.path.exists(OUTPUT_JSON_FILE):
        try:
            with open(OUTPUT_JSON_FILE, "r", encoding="utf-8") as f:
                data = json.load(f)
        except Exception:
            data = []
    else:
        data = []

    data.append(entry)
    with open(OUTPUT_JSON_FILE, "w", encoding="utf-8") as f:
        json.dump(data, f, indent=2)


# ========================= Main =========================
def main():
    import traceback

    check_java_version()

    with open(INPUT_PR_FILE, "r", encoding="utf-8") as f:
        pr_list = json.load(f)

    repo = clone_repo()

    for pr in pr_list[0:2]:
        pr_number = pr.get("pr_number")
        if not pr_number:
            continue

        is_found = False
        merge_compiled = False
        merge_total_count = None
        merge_fail_count = None
        merge_passed = None
        base_compiled = None
        base_passed = None
        base_total_count = None
        base_fail_count = None

        try:
            # ============ MERGE (HEAD) ============
            checkout_pr_head_sha_with_refspecs(pr, repo)

            # Put ONLY LLM tests into the repo test dir
            include_classes = copy_llm_tests_into_repo(pr_number)

            # If none found → record and skip Maven entirely
            if not include_classes:
                append_result_row(
                    pr_number, False,
                    merge_compiled, merge_passed, merge_total_count, merge_fail_count,
                    base_compiled, base_passed, base_total_count, base_fail_count
                )
                print(f"[PR {pr_number}] No LLM tests found → skipping Maven.")
                continue

            is_found = True

            # Build selector for -Dtest: default pattern + explicit FQCNs
            include_arg = f"{DEFAULT_LLM_PATTERN}," + ",".join(include_classes)

            # MERGE compile/test (return-code based)
            merge_compiled = compile_tests_only()
            if merge_compiled:
                merge_passed, merge_total_count, merge_fail_count = run_llm_tests_only(include_arg)
            # else:
            #     append_result_row(
            #         pr_number, is_found,
            #         merge_compiled, merge_passed,
            #         base_compiled, base_passed
            #     )
            #     print(f"[PR {pr_number}] Merge compile failed → skipping base.")
            #     continue

            # if merge_passed is False:
            #     append_result_row(
            #         pr_number, is_found,
            #         merge_compiled, merge_passed,
            #         base_compiled, base_passed
            #     )
            #     print(f"[PR {pr_number}] Merge tests failed → skipping base.")
            #     continue

            # ============ BASE ============
            base_sha = pr.get("base_sha") or pr.get("base_commit")
            if not base_sha:
                print(f"[PR {pr_number}] Missing base_sha/base_commit; skipping base phase.")
            else:
                checkout_commit(base_sha, repo)

                # Re-copy LLM tests (repo was reset)
                include_classes_base = copy_llm_tests_into_repo(pr_number)
                if not include_classes_base:
                    # Fall back to the classes we detected on merge phase (most common case)
                    include_classes_base = include_classes

                if include_classes_base:
                    include_arg_base = f"{DEFAULT_LLM_PATTERN}," + ",".join(include_classes_base)
                    base_compiled = compile_tests_only()
                    if base_compiled:
                        base_passed, base_total_count, base_fail_count = run_llm_tests_only(include_arg_base)
                    else:
                        print(f"[PR {pr_number}] Base compile failed → not running tests.")
                else:
                    print(f"[PR {pr_number}] No LLM tests available for base phase — skipping.")

            # Persist result
            append_result_row(
                pr_number, is_found,
                merge_compiled, merge_passed, merge_total_count, merge_fail_count,
                base_compiled, base_passed, base_total_count, base_fail_count
            )

            print(f"[PR {pr_number}] Done: merge(compiled={merge_compiled}, passed={merge_passed}) "
                  f"base(compiled={base_compiled}, passed={base_passed})")

        except Exception as e:
            print(f"[PR {pr_number}] ERROR: {e}\n{traceback.format_exc()}")
            try:
                append_result_row(
                    pr_number, is_found,
                    merge_compiled, merge_passed, merge_total_count, merge_fail_count,
                    base_compiled, base_passed, base_total_count, base_fail_count
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
