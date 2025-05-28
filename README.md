# Commons-Imaging PR Pipeline for EVOSUITE

## Requirements

- **Java 8+** installed and on your `PATH`  
- **Maven** installed and on your `PATH`  
- **Python 3** (tested with 3.7+)  
- **gitpython** package (`pip install gitpython`)  
- **EvoSuite** standalone JAR named `evosuite.jar` in the same folder as `pr_pipeline.py`  
- **Network access** to GitHub  
- A file named `imaging.json` describing PR metadata (see earlier section)  

# Commons-Imaging PR Pipeline

A command‐line tool to automate test generation, execution, and coverage analysis for Apache Commons Imaging pull requests using EvoSuite and JaCoCo.

---

## Function Reference

### `check_java_version()`
**Purpose:** Verify and display the installed Java version.  
**Description:** Runs `java -version`, captures stderr/stdout, and prints it so you can confirm Java is available.

---

### `clone_repo()`
**Purpose:** Clone or open the local Git repository.  
**Description:**  
- If `LOCAL_REPO_PATH` does not exist, clones from `REPO_URL`.  
- Otherwise, opens the existing folder as a `git.Repo`.  
- Returns the `Repo` object for further operations.

---

### `checkout_pr(pr_number, repo)`
**Purpose:** Fetch and checkout a specific PR branch.  
**Description:**  
1. Resets any local changes (`git reset --hard`).  
2. Cleans untracked files (`git clean -fd`).  
3. Fetches `origin/pull/{pr_number}/head` into local branch `pr_{pr_number}`.  
4. Checks out that newly created branch.

---

### `compile_project()`
**Purpose:** Compile the project source.  
**Description:**  
- Runs `mvn clean compile -Drat.skip=true`.  
- Prints error output if the build fails and raises an exception.

---

### `build_classpath(pr_number)`
**Purpose:** Generate the classpath needed for EvoSuite.  
**Description:**  
1. Executes `mvn dependency:build-classpath -Dmdep.outputFile=classpath.txt`.  
2. Renames the output to `classpath_pr_{pr_number}.txt`.  
3. Reads its contents and appends `target/classes` if it exists.  
4. Returns the full classpath string.

---

### `package_project()`
**Purpose:** Package the project into a JAR.  
**Description:**  
- Runs `mvn clean package -Drat.skip=true -Dmaven.test.skip=true`.  
- Finds the generated JAR in `target/` (excluding sources), and returns its path.

---

### `get_project_version()`
**Purpose:** Retrieve the Maven project version.  
**Description:**  
- Uses `mvn help:evaluate -Dexpression=project.version -q -DforceStdout`  
- Returns the version string, e.g. `"1.3.0"`.

---

### `write_version_info(pr_number, version)`
**Purpose:** Log the project version for each PR.  
**Description:**  
- Appends a line `PR {pr_number}: {version}` to `VERSION_LOG_FILE`.

---

### `run_evosuite_for_classes(class_names, project_version, pr_number, project_classpath, project_jar)`
**Purpose:** Generate unit tests using EvoSuite.  
**Description:**  
1. Filters out nested classes (names containing `$`).  
2. Sorts top-level class names.  
3. For each class, runs:
4. Prints progress before invoking EvoSuite.

---

### `remove_comments(code)`
**Purpose:** Strip comments from Java source.  
**Description:**  
- Uses regex to remove `/* … */` and `// …` comments so parsing isn’t confused.

---

### `extract_class_names(content, file_path)`
**Purpose:** Identify fully-qualified class names in a Java file.  
**Description:**  
1. Removes comments.  
2. Detects `package org.apache....;`.  
3. Finds all `class|interface|enum Name` tokens.  
4. Emits `pkg.Outer` and nested `pkg.Outer$Inner` names based on file basename.

---

### `replace_tests_in_project(src, dest)`
**Purpose:** Replace the existing test directory with generated tests.  
**Description:**  
- Deletes `dest` if it exists, then copies the entire folder `src` into `dest`.

---

### `comment_out_evosuite_annotations()`
**Purpose:** Disable EvoSuite test runners for coverage.  
**Description:**  
- Scans `PROJECT_TEST_DIR` for files ending `_ESTest.java`.  
- Comments out lines containing `@RunWith` or `@EvoRunnerParameters` to let JaCoCo instrument tests.

---

### `run_tests_and_generate_coverage(pr_number)`
**Purpose:** Execute tests and produce JaCoCo coverage report.  
**Description:**  
1. Calls `comment_out_evosuite_annotations()`.  
2. Runs `mvn clean test -Dmaven.test.skip=false -Dmaven.test.failure.ignore=true -fn -Drat.numUnapprovedLicenses=100 -DskipTests=false`.  
3. Moves `target/site/jacoco` to `jacoco_report_pr_{pr_number}` and returns that folder name.

---

### `append_tests_to_json(pr_data, tests_info, coverage_folder=None)`
**Purpose:** Record test generation and coverage metadata.  
**Description:**  
- Loads existing `OUTPUT_JSON_FILE` (creates a new list if invalid).  
- Appends an object with keys:
- `pr_number`, `generated_tests`, `coverage_data`, plus PR metadata (`title`, `state`, `created_at`, etc.).  
- Writes the updated array back to disk.

---

### `add_plugins_to_pom(pom_path, pr_number)`
**Purpose:** Inject JaCoCo and EvoSuite into `pom.xml`.  
**Description:**  
1. Parses `pom.xml` using `xml.etree.ElementTree`.  
2. Under `<build><plugins>`, adds `jacoco-maven-plugin` if missing:
- Execution goals: `prepare-agent`, `report`.  
3. Under `<dependencies>`, adds:
- `org.evosuite:evosuite-standalone-runtime` (test scope)  
- JUnit 5 & vintage-engine dependencies (test scope).  
4. Under `<reporting><plugins>`, adds JaCoCo plugin for site generation.  
5. Writes the modified `pom.xml` back.

---

### `update_pom_for_pr(pr_number)`
**Purpose:** Wrapper to update the main `pom.xml`.  
**Description:** Constructs the path to `pom.xml` and calls `add_plugins_to_pom()`.

---

### `main()`
**Purpose:** Orchestrate the full pipeline.  
**Description:**  
1. `check_java_version()`  
2. Load PR list from `imaging.json`.  
3. `clone_repo()`  
4. For each PR:
- `checkout_pr()`  
- `update_pom_for_pr()`  
- `compile_project()`  
- `build_classpath()`  
- `package_project()`  
- `get_project_version()` → `write_version_info()`  
- Extract changed classes → `run_evosuite_for_classes()`  
- `replace_tests_in_project()`  
- `run_tests_and_generate_coverage()`  
- `append_tests_to_json()`  

---

# Commons-Imaging PR Pipeline for EVOSUITE and HUMAN


# Test Merge Process in the Commons-Imaging PR Pipeline

When running the pipeline on a given pull request (PR), we preserve the original human-written tests (baseline) and combine them with the new EvoSuite-generated tests. This happens in three distinct steps:

---

## 1. Snapshot Baseline Human Tests (Pre-PR)

- **When?** Immediately after checking out the repository at the PR’s _base commit_ (i.e., before any PR changes are applied).
- **How?**
  1. `checkout(repo, base_sha)` resets the working copy to the commit identified by `base_sha`.
  2. The entire `src/test/java` directory (which contains all human-written tests) is copied to `baseline_tests_pr_<pr_number>`.
- **Result:** A folder named `baseline_tests_pr_<pr_number>` holds exactly the tests that existed _before_ the PR’s code changes.

---

## 2. EvoSuite Test Generation (PR Head)

- **When?** After checking out the repository at the PR’s _head commit_ (i.e., with all PR changes applied) and patching the `pom.xml` to include EvoSuite and JaCoCo.
- **How?**
  1. `checkout_pr_head(repo, pr_number)` fetches and checks out the branch containing the PR’s proposed code changes.
  2. `patch_pom()` injects EvoSuite and JaCoCo plugins/dependencies into `pom.xml` so that EvoSuite can run and JaCoCo can instrument tests.
  3. `maven(["clean", "compile", "-Drat.skip=true"])` compiles the changed code.
  4. `extract_classes(pr, LOCAL_REPO_PATH)` identifies which classes were modified in the PR (by parsing changed Java files).
  5. For each top-level class, `run_evosuite(...)` invokes EvoSuite to generate new tests, writing them into a temporary `evosuite-tests` folder.
  6. This folder is then renamed to `evosuite-tests-pr_<pr_number>`.

---

## 3. Merging Baseline + EvoSuite Tests

- **When?** After EvoSuite has generated new tests at the PR head, but _before_ running the final coverage analysis.
- **How?**
  1. `reset_tests()` deletes the current `src/test/java` (containing no tests after compile) and recreates it empty.
  2. `copy_tree(baseline_tests_pr_<pr_number>, PROJECT_TEST_DIR)` copies back the original human-written tests into `src/test/java`.
  3. If `evosuite-tests-pr_<pr_number>` exists, `copy_tree(evosuite-tests-pr_<pr_number>, PROJECT_TEST_DIR)` then overlays the EvoSuite-generated tests into the same `src/test/java` directory.
- **Result:** The working test directory now contains both:
  - The exact set of human-written tests that existed prior to the PR, and
  - The freshly generated EvoSuite tests for classes changed by the PR.

---

> **Key Point:** EvoSuite tests are always generated _against_ the PR’s modified code (the head state), but they are only merged _after_ you restore the pre-PR human tests. This ensures that coverage metrics reflect both the original test suite and the new, automatically generated tests.

---

## Function Reference

### `run(cmd, cwd=None)`
**Purpose:** Invoke a subprocess command and ensure it succeeds.  
**Description:**  
- Executes `subprocess.run(cmd, cwd=cwd, check=True)`.  
- Raises an exception if the command exits with a non-zero status.

### `clone_repo()`
**Purpose:** Clone or open the local Git repository.  
**Description:**  
- If `LOCAL_REPO_PATH` does not exist, runs `Repo.clone_from(REPO_URL, LOCAL_REPO_PATH)`.  
- Otherwise opens it via `Repo(LOCAL_REPO_PATH)`.  
- Returns the `Repo` object.

### `checkout(repo, ref)`
**Purpose:** Reset local changes and switch to a specific Git ref.  
**Description:**  
- Runs `git reset --hard` and `git clean -fd` to clean the workspace.  
- Checks out the specified `ref`.

### `checkout_pr_head(repo, pr)`
**Purpose:** Fetch and checkout the pull request’s head branch.  
**Description:**  
- Fetches `origin/pull/{pr}/head` as `pr_{pr}`.  
- Calls `checkout(repo, local)` to switch.

### `maven(cmd, *args)`
**Purpose:** Run Maven commands in the project directory.  
**Description:**  
- Constructs `['mvn'] + list(cmd) + list(args)`.  
- Executes it via `run(..., cwd=LOCAL_REPO_PATH)`.

### `patch_pom()`
**Purpose:** Inject JaCoCo and EvoSuite plugins/dependencies into `pom.xml`.  
**Description:**  
1. Parses `pom.xml` with `ElementTree`.  
2. Adds `jacoco-maven-plugin` under `<build><plugins>` if missing (with `prepare-agent` and `report` executions).  
3. Ensures test-scope dependencies for EvoSuite runtime and JUnit 5/Vintage.  
4. Adds JaCoCo under `<reporting><plugins>` for `mvn site`.  
5. Writes changes back to `pom.xml`.

### `reset_tests()`
**Purpose:** Clear out the existing test directory.  
**Description:**  
- Removes `PROJECT_TEST_DIR` and recreates it empty.

### `copy_tree(src, dst)`
**Purpose:** Copy a directory tree, overwriting as needed.  
**Description:**  
- Uses `shutil.copytree(src, dst, dirs_exist_ok=True)`.

### `remove_comments(code)`
**Purpose:** Strip Java comments from source code.  
**Description:**  
- Removes `/*...*/` blocks and `//...` lines via regex.

### `extract_classes(pr, repo_path)`
**Purpose:** Identify fully-qualified class names in changed files.  
**Description:**  
- Scans PR’s `changed_files` for Java sources.  
- Strips comments, extracts `package` and class/interface/enum declarations.  
- Returns a set of names including nested classes.

### `build_classpath(tag)`
**Purpose:** Assemble the classpath for EvoSuite.  
**Description:**  
- Runs `mvn dependency:build-classpath`, moves output to `classpath_{tag}.txt`.  
- Reads its content and appends `target/classes` if present.  
- Returns the full classpath string.

### `package_jar()`
**Purpose:** Build the project JAR without tests.  
**Description:**  
- Runs `mvn clean package -Dmaven.test.skip=true`.  
- Selects the first `.jar` in `target/` (excluding sources) and returns its path.

### `run_evosuite(classes, cp, jar, out_dir)`
**Purpose:** Generate EvoSuite tests for each class.  
**Description:**  
- Filters out nested classes (`$`).  
- Runs EvoSuite for each top-level class.  
- Moves generated `evosuite-tests` folder to `out_dir`.

### `comment_evosuite_annotations()`
**Purpose:** Disable EvoSuite runners so JaCoCo can instrument tests.  
**Description:**  
- Walks `PROJECT_TEST_DIR` for `_ESTest.java` files.  
- Comments out `@RunWith` and `@EvoRunnerParameters` lines.

### `coverage(pr)`
**Purpose:** Run tests and produce a JaCoCo report.  
**Description:**  
- Calls `comment_evosuite_annotations()`.  
- Runs `mvn clean test -Dmaven.test.failure.ignore=true`.  
- Moves `target/site/jacoco` → `jacoco_report_pr_{pr}` and returns that path.

### `log_json(pr, report)`
**Purpose:** Append PR metadata and report path to the JSON log.  
**Description:**  
- Loads `generated_evosuite_tests.json` (ignoring errors).  
- Appends an object with `pr_number`, `title`, `coverage_data`, `created_at`, `user_login`.  
- Writes back with indentation.

### `main()`
**Purpose:** Orchestrate the end-to-end pipeline.  
**Description:**  
1. Load PR list from `imaging.json`.  
2. Clone or open the repo.  
3. For each PR:
   - Snapshot baseline tests (pre-PR).
   - Checkout PR head, patch POM, compile.
   - Generate EvoSuite tests for changed classes.
   - Merge baseline + generated tests.
   - Run coverage and log results.
```

