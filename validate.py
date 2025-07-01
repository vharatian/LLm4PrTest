#!/usr/bin/env python3
"""
commons-lang ▸ Test-transfer checker  (v2 “merge-commit mode”)
==============================================================

For every PR listed in *lang.json* this script now:

    1. Checks out the PR’s **merge_commit_sha** (after-merge code) and runs Maven tests.
    2. If that succeeds, copies those PR tests, then checks out the
       **base commit** (base_sha), grafts the tests onto it and runs Maven tests again.

A single JSON file (*test_transfer_report.json*) is produced.  
For each PR it contains:

    ├ after_pr_compiling   (bool)
    ├ after_pr_passing     (bool)
    ├ base_compiling       (bool | null)
    ├ base_passing         (bool | null)
    └ is_valid             (bool)

A PR is **valid** only when all four preceding fields are true.
"""

import json, shutil, subprocess
from pathlib import Path
from git import Repo

# ───────── configuration ───────────────────────────────────────────
REPO_URL         = "https://github.com/apache/commons-lang.git"
REPO_DIR         = "commons-lang"
PROJECT_DIR      = Path(REPO_DIR)
TEST_ROOT        = PROJECT_DIR / "src" / "test"
TEMP_DIR_TPL     = "pr_{pr}_tests_from_pr"
OUTPUT_JSON      = Path("test_transfer_report.json")

# ───────── git helpers ─────────────────────────────────────────────
def clone_repo() -> Repo:
    return Repo(PROJECT_DIR) if PROJECT_DIR.exists() else Repo.clone_from(REPO_URL, REPO_DIR)

def checkout(repo: Repo, ref: str):                                   # unchanged
    repo.git.checkout(ref, force=True)

def checkout_merge_commit(repo: Repo, sha: str):                      # ⇠ added
    """
    Make the working tree point at the given merge_commit_sha.

    • Always detaches HEAD first so the temp branch can be force-deleted safely.
    • Creates/forces a local branch named 'merge_<sha7>' for clarity in `git status`.
    """
    branch = f"merge_{sha[:7]}"
    repo.git.checkout("--detach")
    if branch in repo.heads:
        repo.git.branch("-D", branch)
    repo.git.fetch("origin", sha)
    repo.git.checkout("-B", branch, "FETCH_HEAD")

def ensure_pristine(repo: Repo): repo.git.reset("--hard"); repo.git.clean("-fdx")

# ───────── maven & test helpers ────────────────────────────────────
def run_maven_tests(cwd: Path = PROJECT_DIR):
    """Return (compiled_ok, tests_passed)."""
    proc = subprocess.run(
        ["mvn", "-q", "-Drat.skip=true", "clean", "test"],
        cwd=cwd,
    )
    surefire_dir = cwd / "target" / "surefire-reports"
    compiled_ok  = any(surefire_dir.glob("TEST-*.xml"))
    tests_passed = proc.returncode == 0
    return compiled_ok, tests_passed

def copy_tree(src: Path, dst: Path):
    if dst.exists(): shutil.rmtree(dst)
    shutil.copytree(src, dst)

def reset_tests():
    if TEST_ROOT.exists(): shutil.rmtree(TEST_ROOT)
    TEST_ROOT.mkdir(parents=True, exist_ok=True)

# ───────── pipeline ────────────────────────────────────────────────
def main():
    prs   = json.load(open("lang.json", encoding="utf-8"))
    repo  = clone_repo()
    stats = []

    for pr in prs[17:]:
        num        = pr["pr_number"]
        base_sha   = pr["base_sha"]
        merge_sha  = pr["merge_commit_sha"]                           # ⇠ added
        ensure_pristine(repo)

        # ── STEP 1: merge_commit + PR tests ─────────────────────────
        checkout_merge_commit(repo, merge_sha)                        # ⇠ changed
        after_comp, after_pass = run_maven_tests()

        entry = {
            "pr_number":          num,
            "after_pr_compiling": after_comp,
            "after_pr_passing":   after_pass,
            "base_compiling":     None,
            "base_passing":       None,
            "is_valid":           False,
        }

        if after_comp and after_pass:
            tmp_tests = Path(TEMP_DIR_TPL.format(pr=num))
            copy_tree(TEST_ROOT, tmp_tests)

            # ── STEP 2: base commit + PR tests ─────────────────────
            ensure_pristine(repo)
            checkout(repo, base_sha)
            reset_tests(); copy_tree(tmp_tests, TEST_ROOT)

            base_comp, base_pass = run_maven_tests()
            entry.update({
                "base_compiling": base_comp,
                "base_passing":   base_pass,
                "is_valid":       all([after_comp, after_pass, base_comp, base_pass]),
            })

            shutil.rmtree(tmp_tests, ignore_errors=True)

        stats.append(entry)
        OUTPUT_JSON.write_text(json.dumps(stats, indent=2), encoding="utf-8")
        print(f"[PR {num}] {'✅ valid' if entry['is_valid'] else '❌ invalid'}")

    print(f"\nFinished. Report written to {OUTPUT_JSON}")

if __name__ == "__main__":
    main()
