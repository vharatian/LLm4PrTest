# Commons-Imaging PR Pipeline for EVOSUITE

```markdown
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
