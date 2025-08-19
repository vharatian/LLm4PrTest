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
REPO_URL = "https://github.com/apache/commons-imaging.git"
PROJECT_NAME = "commons-imaging"
LOCAL_REPO_PATH = PROJECT_NAME
EVOSUITE_JAR = "evosuite.jar"
EVOSUITE_RUNTIME_VERSION = "1.0.6"
VERSION_LOG_FILE = "pr_versions.txt"
OUTPUT_JSON_FILE = "generated_evosuite_tests.json"
PROJECT_TEST_DIR = os.path.join(LOCAL_REPO_PATH, "src", "test", "java")

# --- Maven XML Namespace ---
NAMESPACE = {"mvn": "http://maven.apache.org/POM/4.0.0"}
ET.register_namespace('', NAMESPACE["mvn"])

# --- Utility Functions ---
def filter_details_failures_only(details):
    """
    Keep only classes that have at least one failure/error,
    and inside them keep only the failing/erroring test cases.
    """
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

def ensure_pr_refspecs():
    """
    Ensure we fetch GitHub PR HEAD refs into origin/pr/*:
      pull/*/head -> refs/remotes/origin/pr/*
    Idempotent: only adds if missing.
    """
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

def collect_surefire_summary(surefire_dir):
    """
    Aggregate Surefire XML results.
    Returns: {"tests": int, "failures": int, "errors": int, "skipped": int}
    """
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

def checkout_commit(commit_sha, repo):
    print(f"Checking out commit {commit_sha}...")
    repo.git.reset('--hard')
    repo.git.clean('-fd')
    repo.git.fetch("--all", "--tags")
    repo.git.checkout(commit_sha)

def verify_tests_on_base(pr, repo, generated_tests_dir):
    """
    Checkout base, inject deps, copy generated tests, run, archive Surefire.
    Returns the full result dict from run_tests_and_generate_coverage(...,"base")
    """
    base_sha = pr.get("base_sha") or pr.get("base_commit")
    if not base_sha:
        raise ValueError("PR missing base_sha/base_commit for base verification")

    pr_number = pr.get("pr_number")
    checkout_commit(base_sha, repo)
    update_pom_for_pr(pr_number)

    # Place generated tests into the base version
    merge_dest = PROJECT_TEST_DIR
    replace_tests_in_project(generated_tests_dir, merge_dest)

    # Run EvoSuite tests only (base phase)
    result = run_tests_and_generate_coverage(pr_number, phase_label="base")
    result["base_sha"] = base_sha
    print(f"[Base check] {result['summary']} | all_passed={result['all_passed']}")
    return result



def collect_surefire_details(surefire_dir):
    """
    Returns:
      {
        "summary": {"tests":int,"failures":int,"errors":int,"skipped":int},
        "by_class": [
            {"class":"...","tests":..,"failures":..,"errors":..,"skipped":..,
             "cases":[{"name":"...","status":"passed|failure|error|skipped",
                       "message": "...", "type": "..."}]}
        ]
      }
    """
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
    """
    Move Surefire folder to a phase-tagged archive dir.
    Returns the archive path, or None if source missing.
    """
    if not surefire_dir or not os.path.isdir(surefire_dir):
        return None
    dst = f"surefire_{phase_label}_pr_{pr_number}"
    if os.path.exists(dst):
        shutil.rmtree(dst)
    shutil.copytree(surefire_dir, dst)
    return dst

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

# --- Git & Build Helpers ---
def clone_repo():
    if not os.path.exists(LOCAL_REPO_PATH):
        print("Cloning repository...")
        Repo.clone_from(REPO_URL, LOCAL_REPO_PATH)
    else:
        print("Using existing local repository.")
    return Repo(LOCAL_REPO_PATH)

def checkout_pr(pr_number, repo):
    """
    Check out PR HEAD via origin/pr/<id>, matching the javaparser pipeline:
    - ensure PR refspecs
    - fetch
    - hard reset + clean (incl. ignored) for a deterministic tree
    - detached HEAD at origin/pr/<id>
    """
    print(f"Checking out PR #{pr_number} (HEAD via origin/pr/{pr_number})...")

    ensure_pr_refspecs()
    repo.git.fetch("origin")

    # reset & clean before switching (remove untracked + ignored)
    repo.git.reset("--hard")
    repo.git.clean("-fdx")

    ref = f"origin/pr/{pr_number}"

    # verify ref exists
    cp = subprocess.run(
        ["git", "rev-parse", "--verify", ref],
        cwd=LOCAL_REPO_PATH, text=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE
    )
    if cp.returncode != 0:
        raise RuntimeError(f"PR ref not found: {ref}\n{cp.stderr or cp.stdout}")

    repo.git.checkout(ref)
    print(f"On {ref} @ {repo.head.commit.hexsha}")


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

# --- EvoSuite & Test Helpers ---
def run_evosuite_for_classes(class_names, project_version, pr_number, project_classpath, project_jar):
    # 1) keep only top-level classes (no '$' in the name)
    outer_classes = [c for c in class_names if '$' not in c]
    outer_classes.sort()

    for cls in outer_classes:
        # 2) build a fresh EvoSuite command for each class
        cmd = [
            "java", "-jar", EVOSUITE_JAR,
            "-target", project_jar,
            "-projectCP", project_classpath,
            "-Dsearch_budget=120",
            "-Dalgorithm=DynaMOSA",
            "-class", cls
        ]

        # — print class and its full command —
        print(f"[PR {pr_number}] → EvoSuite targeting class: {cls}")
        print("Full command:", " ".join(cmd))

        # 3) run EvoSuite for this single class
        subprocess.run(cmd, check=True)

        # — optional per-class output folder rename (commented out) —
        # import shutil
        # dest = f"evosuite-tests-{cls.replace('.', '_')}"
        # if os.path.exists("evosuite-tests"):
        #     if os.path.exists(dest):
        #         shutil.rmtree(dest)
        #     shutil.move("evosuite-tests", dest)


def move_evosuite_output(pr_number, default_folder="evosuite-tests"):
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
    """
    Return a list of fully‑qualified names that actually exist on disk.

    If the file  src/main/java/mypkg/Outer.java  contains
        public enum INNER { … }
    the function yields both
        mypkg.Outer
        mypkg.Outer$INNER
    so EvoSuite can recognise nested types.
    """
    code = remove_comments(content)

    # package mypkg;
    pkg_match = re.search(r'\bpackage\s+([\w\.]+)\s*;', code)
    pkg = pkg_match.group(1) + "." if pkg_match else ""

    # outer type name comes from the file’s basename (Outer.java → Outer)
    outer = os.path.splitext(os.path.basename(file_path))[0]

    # every type token declared in the file
    simple_names = re.findall(r'\b(?:class|interface|enum)\s+(\w+)', code)

    fq_names = []
    for name in simple_names:
        if name == outer:
            fq_names.append(pkg + name)                 # top‑level class
        else:
            fq_names.append(pkg + outer + '$' + name)   # nested → Outer$Inner

    return fq_names


def replace_tests_in_project(src, dest):
    """
    Merge-copy generated tests into the project's test tree without deleting
    existing human tests.
    """
    os.makedirs(dest, exist_ok=True)
    for root, _, files in os.walk(src):
        rel = os.path.relpath(root, src)
        out_dir = os.path.join(dest, rel) if rel != "." else dest
        os.makedirs(out_dir, exist_ok=True)
        for f in files:
            shutil.copy2(os.path.join(root, f), os.path.join(out_dir, f))


def comment_out_evosuite_annotations():
    print("Commenting out EvoSuite annotations in test files...")
    for root, _, files in os.walk(PROJECT_TEST_DIR):
        for file in files:
            if file.endswith("_ESTest.java"):
                file_path = os.path.join(root, file)
                with open(file_path, 'r') as f:
                    lines = f.readlines()
                
                modified = False
                for i, line in enumerate(lines):
                    if '@RunWith' in line or '@EvoRunnerParameters' in line:
                        lines[i] = '// ' + line
                        modified = True
                
                if modified:
                    with open(file_path, 'w') as f:
                        f.writelines(lines)
                    print(f"Modified: {file_path}")


def run_tests_and_generate_coverage(pr_number, phase_label):
    """
    Runs ONLY EvoSuite tests (no coverage).
    Detects 'compile_error' when Maven fails before Surefire XMLs exist.
    Returns:
      {
        "returncode": int,
        "summary": {...},
        "details": {...},   # filtered: only failing/error testcases
        "all_passed": bool,
        "compile_error": bool,
        "logs": {"stdout": str, "stderr": str},
        "surefire_dir": str|None,
        "surefire_archive": str|None
      }
    """
    # Keep EvoSuite tests compatible with plain JUnit
    comment_out_evosuite_annotations()

    cmd = [
        "mvn", "clean", "test",
        "-Dtest=*ESTest,*ES_Test,!*_scaffolding",
        "-Dsurefire.failIfNoSpecifiedTests=false",
        "-Dmaven.test.failure.ignore=true",
        "-fn",
        "-Drat.numUnapprovedLicenses=100",
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
    )  # don't raise on failures

    # Save logs per-phase for later inspection
    log_out = f"mvn_{phase_label}_pr_{pr_number}.out"
    log_err = f"mvn_{phase_label}_pr_{pr_number}.err"
    with open(log_out, "w", encoding="utf-8") as f: f.write(res.stdout or "")
    with open(log_err, "w", encoding="utf-8") as f: f.write(res.stderr or "")

    surefire_dir = os.path.join(LOCAL_REPO_PATH, "target", "surefire-reports")
    surefire_exists = os.path.isdir(surefire_dir)

    # Heuristic: if Maven failed and there are no surefire reports, it's a compile error
    stderr_lower = (res.stderr or "").lower()
    stdout_lower = (res.stdout or "").lower()
    compile_signals = (
        "compilation error" in stderr_lower or "compilation failure" in stderr_lower or
        "maven-compiler-plugin" in stderr_lower or "maven-compiler-plugin" in stdout_lower
    )
    compile_error = (res.returncode != 0 and (not surefire_exists or compile_signals))

    # Summaries/details only if reports exist
    summary = collect_surefire_summary(surefire_dir) if surefire_exists else {"tests":0,"failures":0,"errors":0,"skipped":0}
    details_full = collect_surefire_details(surefire_dir) if surefire_exists else {"summary": summary, "by_class": []}

    # Keep only failing/erroring test cases in details
    def filter_details_failures_only(details):
        if not details: return details
        out = {"summary": details.get("summary", {}), "by_class": []}
        for cls in details.get("by_class", []):
            bad = [c for c in cls.get("cases", []) if c.get("status") in ("failure", "error")]
            if bad:
                out["by_class"].append({
                    "class": cls.get("class"),
                    "tests": cls.get("tests", 0),
                    "failures": cls.get("failures", 0),
                    "errors": cls.get("errors", 0),
                    "skipped": cls.get("skipped", 0),
                    "cases": bad
                })
        return out

    details = filter_details_failures_only(details_full)

    compile_ok = (res.returncode == 0)
    all_passed = (compile_ok and summary["tests"] > 0 and summary["failures"] == 0 and summary["errors"] == 0)

    archive_path = archive_surefire_reports(pr_number, phase_label, surefire_dir) if surefire_exists else None

    return {
        "returncode": res.returncode,
        "summary": summary,
        "details": details,
        "all_passed": all_passed,
        "compile_error": compile_error,
        "logs": {"stdout": log_out, "stderr": log_err},
        "surefire_dir": surefire_dir if surefire_exists else None,
        "surefire_archive": archive_path
    }



def append_tests_to_json(pr_data, merge_result, base_result):
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
        "merge_phase": {
            "compile_error": merge_result.get("compile_error", False),
            "all_passed": merge_result.get("all_passed"),
            "summary": merge_result.get("summary"),
            "surefire_archive": merge_result.get("surefire_archive"),
            "details": merge_result.get("details"),
            "logs": merge_result.get("logs"),
        },
        "base_phase": {
            "base_sha": base_result.get("base_sha"),
            "compile_error": base_result.get("compile_error", False),
            "all_passed": base_result.get("all_passed"),
            "summary": base_result.get("summary"),
            "surefire_archive": base_result.get("surefire_archive"),
            "details": base_result.get("details"),
            "logs": base_result.get("logs"),
        }
    }
    data.append(entry)
    with open(OUTPUT_JSON_FILE, "w") as f:
        json.dump(data, f, indent=2)



# --- Automatic pom.xml Modification Functions ---
def add_plugins_to_pom(pom_path, pr_number):
    if not os.path.exists(pom_path):
        raise FileNotFoundError(pom_path)
    tree = ET.parse(pom_path)
    root = tree.getroot()

    def find_ns(parent, tag):
        return parent.find(f"mvn:{tag}", NAMESPACE)
    def findall_ns(parent, tag):
        return parent.findall(f"mvn:{tag}", NAMESPACE)

    # 1) BUILD → PLUGINS: add jacoco-maven-plugin with prepare-agent + report
    build   = find_ns(root, "build")    or ET.SubElement(root, "build")
    plugins = find_ns(build, "plugins") or ET.SubElement(build, "plugins")
    if not any(find_ns(p, "groupId") is not None and find_ns(p, "groupId").text == "org.jacoco"
               for p in findall_ns(plugins, "plugin")):
        jacoco = ET.SubElement(plugins, "plugin")
        ET.SubElement(jacoco, "groupId").text    = "org.jacoco"
        ET.SubElement(jacoco, "artifactId").text = "jacoco-maven-plugin"
        ET.SubElement(jacoco, "version").text    = "0.8.8"
        execs = ET.SubElement(jacoco, "executions")

        # prepare-agent execution
        e1 = ET.SubElement(execs, "execution")
        goals1 = ET.SubElement(e1, "goals")
        goal1  = ET.SubElement(goals1, "goal")
        goal1.text = "prepare-agent"

        # report execution
        e2 = ET.SubElement(execs, "execution")
        ET.SubElement(e2, "id").text    = "report"
        ET.SubElement(e2, "phase").text = "test"
        goals2 = ET.SubElement(e2, "goals")
        goal2  = ET.SubElement(goals2, "goal")
        goal2.text = "report"

    # 2) DEPENDENCIES: (unchanged: EvoSuite runtime + JUnit5/Vintage)
    deps = find_ns(root, "dependencies") or ET.SubElement(root, "dependencies")
    def dep_exists(gid, aid):
        return any(
            find_ns(d, "groupId") is not None and find_ns(d, "groupId").text == gid and
            find_ns(d, "artifactId") is not None and find_ns(d, "artifactId").text == aid
            for d in deps.findall("mvn:dependency", NAMESPACE)
        )

    # EvoSuite runtime
    if not dep_exists("org.evosuite", "evosuite-standalone-runtime"):
        evo = ET.SubElement(deps, "dependency")
        ET.SubElement(evo, "groupId").text    = "org.evosuite"
        ET.SubElement(evo, "artifactId").text = "evosuite-standalone-runtime"
        ET.SubElement(evo, "version").text    = EVOSUITE_RUNTIME_VERSION
        ET.SubElement(evo, "scope").text      = "test"

    # JUnit 5 + Vintage
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

    # 3) REPORTING → PLUGINS: add jacoco-maven-plugin so mvn site picks it up
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

# --- Main Pipeline ---
def main():
    check_java_version()
    with open("imaging.json", "r", encoding="utf-8") as f:
        pr_data_list = json.load(f)

    repo = clone_repo()

    for pr in pr_data_list[:3]:  # adjust slice as you like
        pr_number = pr.get("pr_number")
        if not pr_number:
            continue

        # === MERGE PHASE: checkout PR head and generate tests ===
        checkout_pr(pr_number, repo)
        update_pom_for_pr(pr_number)
        compile_project()
        cp  = build_classpath(pr_number)
        jar = package_project()
        ver = get_project_version()
        write_version_info(pr_number, ver)

        # Classes from changed files
        classes = set()
        for file in pr.get("changed_files", []):
            if file.get("do_experiment"):
                path = file.get("filename", "")
                if path.startswith("src/main/java/"):
                    with open(os.path.join(LOCAL_REPO_PATH, path), encoding="utf-8") as fh:
                        classes.update(extract_class_names(fh.read(), path))

        if not classes:
            print(f"[PR {pr_number}] No class names extracted; skipping EvoSuite.")
            continue

        # Generate EvoSuite tests on merge
        run_evosuite_for_classes(classes, ver, pr_number, cp, jar)
        generated_dir = move_evosuite_output(pr_number)
        if not os.path.exists(generated_dir):
            print(f"[PR {pr_number}] No tests generated; skipping base verification.")
            continue

        # Copy tests into project and run them on MERGE phase
        replace_tests_in_project(generated_dir, PROJECT_TEST_DIR)
        merge_result = run_tests_and_generate_coverage(pr_number, phase_label="merge")

        # === BASE PHASE: verify same tests on base SHA ===
        base_result = verify_tests_on_base(pr, repo, generated_dir)

        # Record both phases
        append_tests_to_json(pr, merge_result=merge_result, base_result=base_result)



if __name__ == "__main__":
    main()