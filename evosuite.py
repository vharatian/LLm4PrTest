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
    pr_ref = f"pull/{pr_number}/head"
    local_branch = f"pr_{pr_number}"
    print(f"Checking out PR #{pr_number}...")
    # Forcefully reset any local changes
    repo.git.reset('--hard')
    repo.git.clean('-fd')  # Also remove any untracked files
    repo.git.fetch("origin", f"{pr_ref}:{local_branch}")
    repo.git.checkout(local_branch)

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
        ["mvn", "dependency:build-classpath", "-Dmdep.outputFile=classpath.txt", "-Drat.skip=true"],
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
    if os.path.exists(dest):
        shutil.rmtree(dest)
    shutil.copytree(src, dest)

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

def run_tests_and_generate_coverage(pr_number):
    # First comment out EvoSuite annotations
    comment_out_evosuite_annotations()
    
    # Then run the tests and generate coverage
    subprocess.run(
        ["mvn", "clean", "test", "-Dmaven.test.skip=false", "-Dmaven.test.failure.ignore=true", "-fn", 
         "-Drat.numUnapprovedLicenses=100", "-DskipTests=false"],
        cwd=LOCAL_REPO_PATH, check=True
    )
    src = os.path.join(LOCAL_REPO_PATH, "target", "site", "jacoco")
    dst = f"jacoco_report_pr_{pr_number}"
    if os.path.exists(src):
        if os.path.exists(dst):
            shutil.rmtree(dst)
        shutil.move(src, dst)
        return dst
    return None

def append_tests_to_json(pr_data, tests_info, coverage_folder=None):
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
        "generated_tests": tests_info,
        "coverage_data": coverage_folder,
        **{k: pr_data.get(k) for k in ("title", "state", "created_at", "merge_commit_sha", "base_sha", "head_sha", "user_login")}
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
    for pr in pr_data_list[30:]:
        pr_number = pr.get("pr_number")
        if not pr_number:
            continue
        checkout_pr(pr_number, repo)
        update_pom_for_pr(pr_number)
        compile_project()
        cp  = build_classpath(pr_number)
        jar = package_project()
        ver = get_project_version()
        write_version_info(pr_number, ver)
        classes = set()
        for file in pr.get("changed_files", []):
            if file.get("do_experiment"):
                path = file.get("filename", "")
                if path.startswith("src/main/java/"):
                    with open(os.path.join(LOCAL_REPO_PATH, path), encoding="utf-8") as fh:
                        classes.update(extract_class_names(fh.read(), path))
        if classes:
            run_evosuite_for_classes(classes, ver, pr_number, cp, jar)
            test_dir = move_evosuite_output(pr_number)
            if not os.path.exists(test_dir):
                print(f"No tests generated for PR {pr_number}; skipping integration.")
                continue  
            replace_tests_in_project(test_dir, PROJECT_TEST_DIR)
            cov = run_tests_and_generate_coverage(pr_number)
            append_tests_to_json(pr, [], cov)
        else:
            print("No class names extracted; skipping EvoSuite.")

if __name__ == "__main__":
    main()
