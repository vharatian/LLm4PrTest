#!/usr/bin/env python3
"""
commons-imaging PR pipeline
---------------------------
For every PR in imaging.json:

1.  Checkout the base commit (pre-PR) and copy its src/test/java
    → baseline_tests_pr_<n>/

2.  Checkout the PR head (after code changes).

3.  Patch pom.xml   <── now identical to your “new” patcher  # === CHANGED →
      • add JaCoCo plugin (prepare-agent + report)
      • add EvoSuite runtime
      • add JUnit 5 + Vintage

4.  Build, then run EvoSuite only on classes touched by the PR.

5.  Reset src/test/java, restore
      a) baseline human tests
      b) EvoSuite tests generated in step 4.

6.  Run Maven tests with JaCoCo → jacoco_report_pr_<n>/

7.  Append run info to generated_evosuite_tests.json
"""

import os, subprocess, json, re, shutil, xml.etree.ElementTree as ET
from git import Repo

# ───────────────────────────── configuration
REPO_URL                 = "https://github.com/apache/commons-imaging.git"
PROJECT_NAME             = "commons-imaging"
LOCAL_REPO_PATH          = PROJECT_NAME
EVOSUITE_JAR             = "evosuite.jar"
EVOSUITE_RUNTIME_VERSION = "1.0.6"
PROJECT_TEST_DIR         = os.path.join(LOCAL_REPO_PATH, "src", "test", "java")
BASELINE_DIR_TPL         = "baseline_tests_pr_{pr}"
EVOSUITE_DIR_TPL         = "evosuite-tests-pr_{pr}"
REPORT_DIR_TPL           = "jacoco_report_pr_{pr}"
LOG_JSON                 = "generated_evosuite_tests.json"

NS = {"mvn": "http://maven.apache.org/POM/4.0.0"}
ET.register_namespace("", NS["mvn"])

# ───────────────────────────── helpers
def run(cmd, cwd=None):
    subprocess.run(cmd, cwd=cwd, check=True)

def clone_repo():
    if not os.path.exists(LOCAL_REPO_PATH):
        return Repo.clone_from(REPO_URL, LOCAL_REPO_PATH)
    return Repo(LOCAL_REPO_PATH)

def checkout(repo, ref):
    repo.git.reset("--hard")
    repo.git.clean("-fd")
    repo.git.checkout(ref)

def checkout_pr_head(repo, pr):
    ref = f"pull/{pr}/head"
    local = f"pr_{pr}"
    repo.git.fetch("origin", f"{ref}:{local}")
    checkout(repo, local)

def maven(cmd, *args):
    run(["mvn"] + list(cmd) + list(args), cwd=LOCAL_REPO_PATH)

# === CHANGED –––> full “new” pom-patcher dropped in wholesale
def patch_pom():
    pom = os.path.join(LOCAL_REPO_PATH, "pom.xml")
    tree = ET.parse(pom); root = tree.getroot()

    find = lambda p, t: p.find(f"mvn:{t}", NS)
    findall = lambda p, t: p.findall(f"mvn:{t}", NS)

    # BUILD / PLUGINS ▸ JaCoCo prepare-agent + report
    build   = find(root, "build")    or ET.SubElement(root, "build")
    plugins = find(build, "plugins") or ET.SubElement(build, "plugins")
    if not any(find(p, "artifactId") is not None and find(p, "artifactId").text == "jacoco-maven-plugin"
               for p in findall(plugins, "plugin")):
        jac = ET.SubElement(plugins, "plugin")
        ET.SubElement(jac, "groupId").text    = "org.jacoco"
        ET.SubElement(jac, "artifactId").text = "jacoco-maven-plugin"
        ET.SubElement(jac, "version").text    = "0.8.8"
        ex = ET.SubElement(jac, "executions")
        # prepare-agent
        e1 = ET.SubElement(ex, "execution")
        goals1 = ET.SubElement(e1, "goals")
        ET.SubElement(goals1, "goal").text = "prepare-agent"
        # report
        e2 = ET.SubElement(ex, "execution")
        ET.SubElement(e2, "id").text    = "report"
        ET.SubElement(e2, "phase").text = "test"
        goals2 = ET.SubElement(e2, "goals")
        ET.SubElement(goals2, "goal").text = "report"

    # DEPENDENCIES ▸ EvoSuite runtime + JUnit5 + Vintage
    deps = find(root, "dependencies") or ET.SubElement(root, "dependencies")
    def dep_ok(gid, aid):
        return any(find(d, "groupId") is not None and find(d, "groupId").text == gid and
                   find(d, "artifactId") is not None and find(d, "artifactId").text == aid
                   for d in findall(deps, "dependency"))
    def add_dep(gid, aid, ver):
        d = ET.SubElement(deps, "dependency")
        ET.SubElement(d, "groupId").text = gid
        ET.SubElement(d, "artifactId").text = aid
        ET.SubElement(d, "version").text = ver
        ET.SubElement(d, "scope").text = "test"

    if not dep_ok("org.evosuite", "evosuite-standalone-runtime"):
        add_dep("org.evosuite", "evosuite-standalone-runtime", EVOSUITE_RUNTIME_VERSION)

    for gid, aid in [("org.junit.jupiter", "junit-jupiter"),
                     ("org.junit.jupiter", "junit-jupiter-params"),
                     ("org.junit.vintage", "junit-vintage-engine")]:
        if not dep_ok(gid, aid):
            add_dep(gid, aid, "5.10.0")

    # REPORTING ▸ JaCoCo so `mvn site` works
    rep  = find(root, "reporting") or ET.SubElement(root, "reporting")
    rpls = find(rep, "plugins")   or ET.SubElement(rep, "plugins")
    if not any(find(p, "artifactId") is not None and find(p, "artifactId").text == "jacoco-maven-plugin"
               for p in findall(rpls, "plugin")):
        p = ET.SubElement(rpls, "plugin")
        ET.SubElement(p, "groupId").text = "org.jacoco"
        ET.SubElement(p, "artifactId").text = "jacoco-maven-plugin"
        rs = ET.SubElement(p, "reportSets")
        rs1= ET.SubElement(rs, "reportSet")
        reports = ET.SubElement(rs1, "reports")
        ET.SubElement(reports, "report").text = "report"

    tree.write(pom, encoding="utf-8", xml_declaration=True)
# <— END of CHANGED block

def reset_tests():
    shutil.rmtree(PROJECT_TEST_DIR, ignore_errors=True)
    os.makedirs(PROJECT_TEST_DIR, exist_ok=True)

def copy_tree(src, dst):
    shutil.copytree(src, dst, dirs_exist_ok=True)

def remove_comments(code):
    code = re.sub(r"/\*.*?\*/", "", code, flags=re.DOTALL)
    return re.sub(r"//.*", "", code)

def extract_classes(pr, repo_path):
    names = set()
    for f in pr.get("changed_files", []):
        if f.get("do_experiment") and f.get("filename", "").startswith("src/main/java/"):
            p = os.path.join(repo_path, f["filename"])
            if os.path.exists(p):
                code = remove_comments(open(p, encoding="utf-8").read())
                pkgm = re.search(r"\bpackage\s+([\w\.]+)\s*;", code)
                pkg = (pkgm.group(1) + ".") if pkgm else ""
                outer = os.path.splitext(os.path.basename(p))[0]
                for n in re.findall(r"\b(?:class|interface|enum)\s+(\w+)", code):
                    names.add(pkg + (n if n == outer else f"{outer}${n}"))
    return names

def build_classpath(tag):
    maven(["dependency:build-classpath", "-Dmdep.outputFile=classpath.txt", "-Drat.skip=true"])
    shutil.move(os.path.join(LOCAL_REPO_PATH, "classpath.txt"), f"classpath_{tag}.txt")
    cp = open(f"classpath_{tag}.txt").read().strip()
    cls = os.path.join(LOCAL_REPO_PATH, "target", "classes")
    return cp + os.pathsep + cls if os.path.exists(cls) else cp

def package_jar():
    maven(["clean", "package", "-Dmaven.test.skip=true", "-Drat.skip=true"])
    tgt = os.path.join(LOCAL_REPO_PATH, "target")
    jar = next(f for f in os.listdir(tgt) if f.endswith(".jar") and "sources" not in f)
    return os.path.join(tgt, jar)

def run_evosuite(classes, cp, jar, out_dir):
    for cls in sorted(c for c in classes if "$" not in c):
        run(["java", "-jar", EVOSUITE_JAR, "-target", jar,
             "-projectCP", cp, "-Djunit=5",        # insure JUnit 5 output
             "-Dsearch_budget=120", "-Dalgorithm=DynaMOSA",
             "-class", cls])
    if os.path.exists("evosuite-tests"):
        shutil.rmtree(out_dir, ignore_errors=True)
        shutil.move("evosuite-tests", out_dir)

def comment_evosuite_annotations():
    for root, _, files in os.walk(PROJECT_TEST_DIR):
        for f in files:
            if f.endswith("_ESTest.java"):
                p = os.path.join(root, f)
                lines = open(p).readlines()
                mod = False
                for i, ln in enumerate(lines):
                    if "@RunWith" in ln or "@EvoRunnerParameters" in ln:
                        lines[i] = "// " + ln; mod = True
                if mod:
                    open(p, "w").writelines(lines)

def coverage(pr):
    comment_evosuite_annotations()
    maven(["clean", "test", "-Dmaven.test.failure.ignore=true",
           "-fn", "-Drat.numUnapprovedLicenses=100"])
    src = os.path.join(LOCAL_REPO_PATH, "target", "site", "jacoco")
    dst = REPORT_DIR_TPL.format(pr=pr)
    if os.path.exists(src):
        shutil.rmtree(dst, ignore_errors=True)
        shutil.move(src, dst)
    return dst if os.path.exists(dst) else None

def log_json(pr, report):
    data = []
    if os.path.exists(LOG_JSON):
        try:
            data = json.load(open(LOG_JSON))
        except json.JSONDecodeError:
            pass
    data.append({"pr_number": pr["pr_number"],
                 "title": pr.get("title"),
                 "coverage_data": report,
                 "created_at": pr.get("created_at"),
                 "user_login": pr.get("user_login")})
    json.dump(data, open(LOG_JSON, "w"), indent=2)

# ───────────────────────────── main
def main():
    prs = json.load(open("imaging.json", encoding="utf-8"))
    repo = clone_repo()

    for pr in prs:
        num = pr["pr_number"]
        base = pr["base_sha"]

        # 1. snapshot baseline human tests
        checkout(repo, base)
        base_dir = BASELINE_DIR_TPL.format(pr=num)
        copy_tree(PROJECT_TEST_DIR, base_dir)

        # 2. PR head
        checkout_pr_head(repo, num)
        patch_pom()                                      # === CHANGED →
        maven(["clean", "compile", "-Drat.skip=true"])

        # 3. EvoSuite generation
        classes = extract_classes(pr, LOCAL_REPO_PATH)
        evo_dir = EVOSUITE_DIR_TPL.format(pr=num)
        if classes:
            cp  = build_classpath(f"{num}_pr")
            jar = package_jar()
            run_evosuite(classes, cp, jar, evo_dir)

        # 4. merge baseline + generated tests
        reset_tests()
        copy_tree(base_dir, PROJECT_TEST_DIR)          # human tests
        if os.path.exists(evo_dir):
            copy_tree(evo_dir, PROJECT_TEST_DIR)       # generated tests

        # 5. coverage
        report = coverage(num)
        log_json(pr, report)
        print(f"[PR {num}] JaCoCo → {report}")

if __name__ == "__main__":
    main()
