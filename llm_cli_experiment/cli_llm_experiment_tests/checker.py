#!/usr/bin/env python3
"""
LLM Tests Fail→Pass Pipeline (no pom edits)
===========================================

- For each PR in io.json:
  * Checkout PR HEAD using the SAME refspec method as your EvoSuite script
    (ensures '+refs/pull/*/head:refs/remotes/origin/pr/*' and verifies head SHA).
  * Copy ONLY the LLM-generated tests from ./pr_{PR}/src/test/java/** into the repo.
  * Compile and run ONLY those tests on:
      - MERGE (HEAD)
      - BASE  (base_sha / base_commit)
  * Write results to:
      - generated_evosuite_tests.json  (same structure you already use)
      - pr_shas.json
      - evosuite_generation_summary.json (compat “look-alike” entry)
- If a PR has NO LLM tests:
  * Do NOT run Maven.
  * Append a normal JSON row with:
        "status": "no_tests_found", "note": "no tests found"
        merge_phase/base_phase both with outcome "no_tests".

Assumptions:
- Your LLM test files reside under: ./pr_<NUMBER>/src/test/java/**.java
- Input file name: io.json  (list of PR dicts with pr_number, head_sha, base_sha/base_commit, etc.)
"""

import os
import json
import shutil
import subprocess
import xml.etree.ElementTree as ET
from pathlib import Path
from typing import List, Dict, Optional
from git import Repo

# --------------------------- Config ----------------------------
REPO_URL                  = "https://github.com/apache/commons-cli.git"
PROJECT_NAME              = "commons-cli"
LOCAL_REPO_PATH           = PROJECT_NAME

INPUT_PR_FILE             = "cli.json"
OUTPUT_JSON_FILE          = "generated_evosuite_tests.json"
EVOSUITE_GEN_SUMMARY_FILE = "evosuite_generation_summary.json"
VERSIONS_JSON_FILE        = "pr_shas.json"

PROJECT_TEST_DIR          = os.path.join(LOCAL_REPO_PATH, "src", "test", "java")
LLM_TESTS_ROOT            = Path(".")

# --- Maven XML Namespace (only for parsing surefire XML; no pom edits) ---
NAMESPACE = {"mvn": "http://maven.apache.org/POM/4.0.0"}
ET.register_namespace('', NAMESPACE["mvn"])


# ========================= Utility: logging "not processed" =========================
def append_not_processed(pr: dict, error_msg: str):
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
        "error": (error_msg or "")[-4000:],
    }
    data.append(entry)
    with open(OUTPUT_JSON_FILE, "w", encoding="utf-8") as f:
        json.dump(data, f, indent=2)


# ========================= Surefire XML parsing =========================
def collect_surefire_summary(surefire_dir: str) -> Dict[str, int]:
    totals = {"tests": 0, "failures": 0, "errors": 0, "skipped": 0}
    if not surefire_dir or not os.path.isdir(surefire_dir):
        return totals
    import glob
    for path in glob.glob(os.path.join(surefire_dir, "*.xml")):
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


def collect_surefire_details(surefire_dir: str) -> Dict:
    import glob
    details = {"summary": {"tests":0, "failures":0, "errors":0, "skipped":0}, "by_class": []}
    if not surefire_dir or not os.path.isdir(surefire_dir):
        return details
    for path in glob.glob(os.path.join(surefire_dir, "*.xml")):
        try:
            t = ET.parse(path).getroot()
            if not t.tag.endswith("testsuite"):
                continue
            cl       = t.attrib.get("name")
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
                name   = tc.attrib.get("name", "")
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


def filter_details_failures_only(details: Dict) -> Dict:
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


def _is_empty_or_missing_surefire(surefire_dir: str) -> bool:
    if not surefire_dir or not os.path.isdir(surefire_dir):
        return True
    xmls = [f for f in os.listdir(surefire_dir) if f.endswith(".xml")]
    return len(xmls) == 0


def archive_surefire_reports(pr_number: int, phase_label: str, surefire_dir: str) -> Optional[str]:
    if not surefire_dir or not os.path.isdir(surefire_dir):
        return None
    dst = f"surefire_{phase_label}_pr_{pr_number}"
    if os.path.exists(dst):
        shutil.rmtree(dst)
    shutil.copytree(surefire_dir, dst)
    return dst


# ========================= Outcome classification =========================
def classify_test_phase(res_returncode: int, summary: Dict, surefire_missing: bool) -> Dict:
    tests = summary.get("tests", 0) if summary else 0
    fails = summary.get("failures", 0) if summary else 0
    errs  = summary.get("errors", 0) if summary else 0

    if surefire_missing:
        if res_returncode == 0:
            return {"outcome": "no_tests", "compile_error": False, "test_failures": False,
                    "maven_failed_other": False, "no_tests_ran": True,
                    "surefire_missing": True, "all_passed": False}
        else:
            return {"outcome": "maven_failed_other", "compile_error": False, "test_failures": False,
                    "maven_failed_other": True, "no_tests_ran": True,
                    "surefire_missing": True, "all_passed": False}

    if tests == 0:
        return {"outcome": "no_tests", "compile_error": False, "test_failures": False,
                "maven_failed_other": False, "no_tests_ran": True,
                "surefire_missing": False, "all_passed": False}

    if (fails > 0) or (errs > 0):
        return {"outcome": "test_failures", "compile_error": False, "test_failures": True,
                "maven_failed_other": False, "no_tests_ran": False,
                "surefire_missing": False, "all_passed": False}

    return {"outcome": "passed", "compile_error": False, "test_failures": False,
            "maven_failed_other": False, "no_tests_ran": False,
            "surefire_missing": False, "all_passed": True}


# ========================= Git helpers (IDENTICAL HEAD behavior) =========================
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
    repo.git.checkout(head_sha)
    print(f"Checked out PR HEAD (by SHA): {head_sha}")


def clone_repo() -> Repo:
    if not os.path.exists(LOCAL_REPO_PATH):
        print("Cloning repository...")
        Repo.clone_from(REPO_URL, LOCAL_REPO_PATH)
    else:
        print("Using existing local repository.")
    return Repo(LOCAL_REPO_PATH)


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


def class_simple_name_from_path(java_path: Path) -> str:
    return java_path.stem  # filename without .java


def copy_llm_tests_into_repo(pr_number: int) -> List[str]:
    """
    Non-destructive copy: keep existing human tests.
    Copies pr_{n}/src/test/java/** -> PROJECT_TEST_DIR/** (preserve package paths).
    Returns a list of FQCNs (fully-qualified class names) to pass to -Dtest=...
    """
    src_root = pr_folder(pr_number) / "src" / "test" / "java"
    if not src_root.exists():
        return []

    fqcns: List[str] = []
    for p in src_root.rglob("*.java"):
        rel = p.relative_to(src_root)  # e.g. org/apache/commons/.../JpegDecoderLLM_Test.java
        dst = Path(PROJECT_TEST_DIR) / rel
        dst.parent.mkdir(parents=True, exist_ok=True)

        # Safety: never overwrite an existing human test
        if dst.exists():
            print(f"[PR {pr_number}] WARNING: {dst} already exists, skipping to avoid overwriting.")
            continue

        shutil.copy2(p, dst)

        # Build FQCN from the relative path (assumes folders match package)
        fqcns.append(".".join(rel.with_suffix("").parts))

    # deduplicate
    seen = set()
    uniq_fqcns = []
    for c in fqcns:
        if c not in seen:
            seen.add(c)
            uniq_fqcns.append(c)
    return uniq_fqcns




# ========================= Maven runs =========================
def compile_tests_only(pr_number: int, phase_label: str) -> Dict:
    cmd = [
        "mvn", "clean", "test-compile",
        "-Drat.skip=true",
        "-DskipTests=true",
        "-Dmaven.test.skip=false",
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


def run_llm_tests_only(pr_number: int, phase_label: str, include_classes: List[str], pattern: Optional[str] = None) -> Dict:
    """
    Run only LLM-generated tests using a fixed Surefire pattern:
      -Dtest=*LLMTest,*LLM_Test,*llm_test*,!*_scaffolding

    Notes:
    - Ignores `include_classes` and `pattern` on purpose.
    - Keeps all logging, surefire parsing, and outcome classification identical.
    """
    cmd = [
        "mvn", "clean", "test",
        "-Dtest=*LLMTest,*LLM_Test,*llm_test*,!*_scaffolding",
        "-Dsurefire.failIfNoSpecifiedTests=false",
        "-Dmaven.test.failure.ignore=true",
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

    log_out = f"mvn_{phase_label}_pr_{pr_number}.out"
    log_err = f"mvn_{phase_label}_pr_{pr_number}.err"
    with open(log_out, "w", encoding="utf-8") as f:
        f.write(res.stdout or "")
    with open(log_err, "w", encoding="utf-8") as f:
        f.write(res.stderr or "")

    surefire_dir = os.path.join(LOCAL_REPO_PATH, "target", "surefire-reports")
    surefire_missing = _is_empty_or_missing_surefire(surefire_dir)

    if not surefire_missing:
        summary = collect_surefire_summary(surefire_dir)
        details_full = collect_surefire_details(surefire_dir)
        archive_path = archive_surefire_reports(pr_number, phase_label, surefire_dir)
    else:
        summary = {"tests": 0, "failures": 0, "errors": 0, "skipped": 0}
        details_full = {"summary": summary, "by_class": []}
        archive_path = None

    diag = classify_test_phase(
        res_returncode=res.returncode,
        summary=summary,
        surefire_missing=surefire_missing
    )

    details = filter_details_failures_only(details_full)

    return {
        "returncode": res.returncode,
        "summary": summary,
        "details": details,
        "all_passed": diag["all_passed"],
        "compile_error": diag["compile_error"],
        "test_failures": diag["test_failures"],
        "maven_failed_other": diag["maven_failed_other"],
        "no_tests_ran": diag["no_tests_ran"],
        "surefire_missing": diag["surefire_missing"],
        "outcome": diag["outcome"],
        "logs": {"stdout": log_out, "stderr": log_err},
        "surefire_dir": None if surefire_missing else surefire_dir,
        "surefire_archive": archive_path,
        "compiled_ok": True
    }




def make_compile_error_result(pr_number: int, phase_label: str, logs: Dict) -> Dict:
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


# ========================= JSON persistence (SAME STRUCTURE) =========================
def append_tests_to_json(pr_data: Dict, merge_result: Dict, base_result: Dict,
                         llm_src_dir: str, status: Optional[str] = None, note: Optional[str] = None):
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
        "base_sha": pr_data.get("base_sha") or pr_data.get("base_commit"),
        "head_sha": pr_data.get("head_sha"),
        "user_login": pr_data.get("user_login"),

        # Keep section name 'evosuite' for compatibility with your downstream consumers
        "evosuite": {
            "tests_dir": llm_src_dir,
            "logs": []
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
            "base_sha": pr_data.get("base_sha") or pr_data.get("base_commit"),
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

    if status is not None:
        entry["status"] = status
    if note is not None:
        entry["note"] = note

    # Convenience flags
    entry["had_compile_error"] = bool(
        entry["merge_phase"].get("compile_error") or entry["base_phase"].get("compile_error")
    )
    entry["had_test_failures"] = bool(
        entry["merge_phase"].get("test_failures") or entry["base_phase"].get("test_failures")
    )
    entry["is_compiled_merge"] = bool(entry["merge_phase"]["compiled_ok"])
    entry["is_compiled_base"]  = bool(entry["base_phase"]["compiled_ok"])

    data.append(entry)
    with open(OUTPUT_JSON_FILE, "w") as f:
        json.dump(data, f, indent=2)


def append_shas(pr: Dict):
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


def summarize_llm_tests_like_evosuite(pr: Dict, llm_src_dir: str, test_classes: List[str]):
    """
    Create a compatibility record in evosuite_generation_summary.json
    (mimics the structure your tools expect).
    """
    pr_number = pr.get("pr_number")
    if not pr_number:
        return

    src = Path(llm_src_dir)
    generated_files = [str(p) for p in src.rglob("*.java")] if src.exists() else []
    total_generated = len(generated_files)

    per_class = []
    for c in test_classes:
        per_class.append({
            "target_class": c,
            "target_simple": c,
            "generated_count": 1,
            "generated_files": [f for f in generated_files if f.endswith(f"{c}.java")],
            "evosuite_stdout": None,
            "evosuite_stderr": None,
            "evosuite_returncode": 0,
        })

    entry = {
        "pr_number": pr_number,
        "head_sha": pr.get("head_sha"),
        "base_sha": pr.get("base_sha") or pr.get("base_commit"),
        "title": pr.get("title"),
        "created_at": pr.get("created_at"),
        "evosuite": {
            "tests_dir": llm_src_dir,
            "tests_dir_exists": src.exists(),
            "total_generated_tests": total_generated,
            "generated_files": generated_files,
            "attempted_targets": test_classes,
            "missing_targets": [],
            "per_class": per_class
        }
    }

    if os.path.exists(EVOSUITE_GEN_SUMMARY_FILE):
        try:
            with open(EVOSUITE_GEN_SUMMARY_FILE, "r", encoding="utf-8") as f:
                data = json.load(f)
        except json.JSONDecodeError:
            data = []
    else:
        data = []

    data.append(entry)
    with open(EVOSUITE_GEN_SUMMARY_FILE, "w", encoding="utf-8") as f:
        json.dump(data, f, indent=2)


# ========================= Main =========================
def check_java_version():
    print("Checking Java version...")
    result = subprocess.run(["java", "-version"], stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True)
    output = result.stderr.strip() or result.stdout.strip()
    print(output)


def main():
    import traceback

    check_java_version()

    with open(INPUT_PR_FILE, "r", encoding="utf-8") as f:
        pr_list = json.load(f)

    repo = clone_repo()

    # Optional: set a Surefire pattern for your LLM tests, e.g. "*LLMTest" or "*llm_test*"
    # (run_llm_tests_only now ignores this and always uses the fixed LLM pattern)
    test_pattern = os.environ.get("LLM_TEST_PATTERN")

    for pr in pr_list:
        pr_number = pr.get("pr_number")
        if not pr_number:
            continue

        llm_src_dir = str(pr_folder(pr_number) / "src" / "test" / "java")

        try:
            # ================= MERGE (HEAD) =================
            checkout_pr_head_sha_with_refspecs(pr, repo)

            # Put ONLY LLM tests into the repo test dir (non-destructive)
            include_classes = copy_llm_tests_into_repo(pr_number)

            # Compile-only
            merge_compile = compile_tests_only(pr_number, phase_label="merge")
            if not merge_compile["ok"]:
                merge_result = make_compile_error_result(pr_number, "merge", merge_compile["logs"])
            else:
                # Always runs with fixed LLM-only pattern inside the function
                merge_result = run_llm_tests_only(pr_number, "merge", include_classes, pattern=test_pattern)

            # ================= BASE =================
            base_sha = pr.get("base_sha") or pr.get("base_commit")
            if not base_sha:
                raise ValueError("PR missing base_sha/base_commit for base verification")
            checkout_commit(base_sha, repo)

            # Re-copy LLM tests (repo was reset)
            include_classes = copy_llm_tests_into_repo(pr_number)

            base_compile = compile_tests_only(pr_number, phase_label="base")
            if not base_compile["ok"]:
                base_result = make_compile_error_result(pr_number, "base", base_compile["logs"])
            else:
                # Always runs with fixed LLM-only pattern inside the function
                base_result = run_llm_tests_only(pr_number, "base", include_classes, pattern=test_pattern)

            # ================= Persist =================
            append_shas(pr)
            append_tests_to_json(pr, merge_result, base_result, llm_src_dir)
            summarize_llm_tests_like_evosuite(pr, llm_src_dir, include_classes)

            print(f"[PR {pr_number}] Done: merge={merge_result['outcome']}, base={base_result['outcome']}")

        except Exception:
            err_text = traceback.format_exc()
            print(f"[PR {pr_number}] ERROR:\n{err_text}")

            # Record as not processed
            try:
                append_not_processed(pr, err_text)
            except Exception:
                pass

            # Per-PR error log
            try:
                with open(f"error_pr_{pr_number}.log", "w", encoding="utf-8") as f:
                    f.write(err_text)
            except Exception:
                pass

            # Clean for next PR
            try:
                repo.git.reset("--hard")
                repo.git.clean("-fdx")
            except Exception:
                pass

            # Persist known SHAs
            try:
                append_shas(pr)
            except Exception:
                pass

            continue




if __name__ == "__main__":
    main()
