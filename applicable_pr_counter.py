#!/usr/bin/env python3
"""
commons-imaging ▸ Baseline-compile checker  (v7 “baseline-only + auto-clean”)
============================================================================
• Still **no** EvoSuite or JaCoCo – pure compile test for the legacy suite
• Now auto-resets the working tree between PRs, so Git can switch branches
  without the “Your local changes would be overwritten” error.

Workflow per PR
---------------
1.  checkout   <base_sha>
2.  copy       src/test/java  → baseline_tests_pr_<n>/
3.  checkout   PR HEAD
4.  patch      pom.xml  (JUnit 5 / Vintage helper)
5.  wipe & restore  baseline tests
6.  mvn test-compile      → success/fail logged
"""

import json
import os
import shutil
import subprocess
import xml.etree.ElementTree as ET
from pathlib import Path
from git import Repo, exc as git_exc   # → pip install GitPython

# ───────── config ────────────────────────────────────────────────────
REPO_URL        = "https://github.com/apache/commons-imaging.git"
REPO_DIR        = "commons-imaging"
PROJECT_DIR     = Path(REPO_DIR)
TEST_DIR        = PROJECT_DIR / "src" / "test" / "java"
BASELINE_DIR_TPL = "baseline_tests_pr_{pr}"

BROKEN_JSON = "broken_baseline_tests.json"     # ← persistent log

# ───────── helpers (mostly unchanged) ────────────────────────────────
def run(cmd, *, cwd=PROJECT_DIR):
    subprocess.run(cmd, cwd=cwd, check=True)

def clone_repo() -> Repo:
    return Repo(REPO_DIR) if Path(REPO_DIR).exists() else Repo.clone_from(REPO_URL, REPO_DIR)

def checkout(repo: Repo, sha: str):
    repo.git.checkout(sha)

def checkout_pr_head(repo: Repo, pr_number: int):
    repo.git.fetch("origin", f"pull/{pr_number}/head:pr_{pr_number}")
    repo.git.checkout(f"pr_{pr_number}")

def patch_pom():
    """
    Make the *legacy* JUnit-4 tests runnable on JUnit-5 engines.
    (Implementation is identical to prior versions, omitted here.)
    """
    # ... existing code ...

def reset_tests():
    if TEST_DIR.exists():
        shutil.rmtree(TEST_DIR)
    TEST_DIR.mkdir(parents=True, exist_ok=True)

def copy_tree(src: Path, dst: Path):
    if dst.exists():
        shutil.rmtree(dst)
    shutil.copytree(src, dst)

def baseline_compiles() -> bool:
    """True ⇢ mvn test-compile succeeded; False ⇢ compilation failed."""
    try:
        run(["mvn", "test-compile", "-q", "-Drat.skip=true"])
        return True
    except subprocess.CalledProcessError:
        return False

def record_broken(pr_num: int):
    data = []
    if os.path.isfile(BROKEN_JSON):
        try:
            data = json.load(open(BROKEN_JSON, encoding="utf-8"))
        except json.JSONDecodeError:
            pass
    data.append({"pr_number": pr_num, "broken_baseline": True})
    json.dump(data, open(BROKEN_JSON, "w", encoding="utf-8"), indent=2)

def count_test_files(dir_: Path) -> int:
    return sum(1 for p in dir_.rglob("*.java"))

# ───────── new bit: guarantee a clean working tree ───────────────────
def ensure_pristine(repo: Repo):
    """
    Throw away ANY local edits/untracked files so that the next git-checkout
    cannot fail with 'local changes would be overwritten'.
    """
    repo.git.reset("--hard")
    repo.git.clean("-fdx")

# ───────── main loop ────────────────────────────────────────────────
def main():
    prs  = json.load(open("imaging.json", encoding="utf-8"))
    repo = clone_repo()

    total_tests = 0
    total_compilable_tests = 0
    broken_prs = []

    for pr in prs:
        num, base_sha = pr["pr_number"], pr["base_sha"]

        # 0️⃣  make sure the repo is spotless before touching it
        ensure_pristine(repo)

        # 1️⃣  snapshot baseline tests on the PR’s *base* commit
        checkout(repo, base_sha)
        baseline_dir = Path(BASELINE_DIR_TPL.format(pr=num))
        copy_tree(TEST_DIR, baseline_dir)

        # 2️⃣  move to PR HEAD & apply the JUnit-5 compatibility patch
        checkout_pr_head(repo, num)
        patch_pom()

        # 3️⃣  delete whatever tests the PR might have and restore baseline
        reset_tests()
        copy_tree(baseline_dir, TEST_DIR)

        n_tests = count_test_files(TEST_DIR)
        total_tests += n_tests

        if baseline_compiles():
            total_compilable_tests += n_tests
            print(f"[PR {num}] ✅  baseline tests compile ({n_tests} files)")
        else:
            broken_prs.append(num)
            record_broken(num)
            print(f"[PR {num}] ❌  baseline tests **do not** compile ({n_tests} files)")

    # ─ summary ─
    good = len(prs) - len(broken_prs)
    print("\n--- Summary ------------------------------------------")
    print(f"{good} / {len(prs)} PRs keep the baseline test suite compilable.")
    print(f"Compiled test files: {total_compilable_tests} / {total_tests}")
    if broken_prs:
        print(f"Broken PR list: {', '.join(map(str, broken_prs))}")

if __name__ == "__main__":
    main()
