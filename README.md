# Commons-Imaging PR Pipeline

A command-line tool to automate test generation, execution, and coverage analysis for Apache Commons Imaging pull requests using EvoSuite and JaCoCo.

---

## Overview

This pipeline will:

1. Clone (or reuse) the local **commons-imaging** GitHub repository  
2. For each PR listed in `imaging.json`:
   - Checkout the PR branch
   - Modify `pom.xml` to include JaCoCo and EvoSuite dependencies
   - Compile and package the project with Maven
   - Extract the list of changed Java classes
   - Generate unit tests per class using EvoSuite
   - Integrate those tests into the project
   - Run the full test suite and produce a JaCoCo coverage report
   - Record results (version, tests generated, coverage folder) into `generated_evosuite_tests.json`

---

## Prerequisites

- **Java** (version 8 or above)  
- **Maven** installed and on your PATH  
- **Python 3** with the `gitpython` package (`pip install gitpython`)  
- **EvoSuite standalone JAR** named `evosuite.jar` in the same folder as this script  
- **Network access** to GitHub  

---

## Configuration

At the top of `pr_pipeline.py`, update any paths or versions:

```python
REPO_URL                 = "https://github.com/apache/commons-imaging.git"
PROJECT_NAME             = "commons-imaging"
LOCAL_REPO_PATH          = PROJECT_NAME
EVOSUITE_JAR             = "evosuite.jar"
EVOSUITE_RUNTIME_VERSION = "1.0.6"
VERSION_LOG_FILE         = "pr_versions.txt"
OUTPUT_JSON_FILE         = "generated_evosuite_tests.json"
PROJECT_TEST_DIR         = os.path.join(LOCAL_REPO_PATH, "src", "test", "java")
