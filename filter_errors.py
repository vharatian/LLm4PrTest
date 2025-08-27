#!/usr/bin/env python3
"""filter_pr_errors.py

Reads all project-level JSON files in a source directory, filters out only the PRs
whose numbers are marked as "Error" in a separate filter JSON, and writes those
filtered PRs into identically-named JSON files inside a target directory.
All paths are hard-coded below; adjust them as needed.
"""

import os
import json
from pathlib import Path

# --- Hard-coded paths ---------------------------------------------------------
SOURCE_DIR = Path("files/replication")      # Folder with <project>.json files
FILTER_JSON_PATH = Path("files/evaluations/evosuite_pr_categories.json") # JSON that contains error lists
OUTPUT_DIR = Path("files/error_prs")     # Destination folder for output files
# -----------------------------------------------------------------------------


def load_filter_map(path: Path) -> dict:
    """Return a nested mapping: {project: {"Error": [<pr_numbers>]}}"""
    with path.open("r", encoding="utf-8") as f:
        return json.load(f)


def filter_error_prs(project_path: Path, error_numbers: set[str]) -> list[dict]:
    """Return only the PR dictionaries whose pr_number is in *error_numbers*."""
    with project_path.open("r", encoding="utf-8") as f:
        prs = json.load(f)  # expecting a list of dicts
    return [pr for pr in prs if str(pr.get("pr_number")) in error_numbers]


def main() -> None:
    OUTPUT_DIR.mkdir(parents=True, exist_ok=True)

    filter_map = load_filter_map(FILTER_JSON_PATH)

    for file in SOURCE_DIR.iterdir():
        if file.suffix != ".json":
            continue  # skip non-JSON files

        project_name = file.stem  # «codec» for codec.json, etc.
        error_list = (
            filter_map.get(project_name, {})
            .get("Error", [])
        )
        if not error_list:
            continue  # nothing to filter for this project

        error_numbers = {str(num) for num in error_list}
        filtered_prs = filter_error_prs(file, error_numbers)
        if not filtered_prs:
            continue  # no matching PRs inside the file

        out_path = OUTPUT_DIR / file.name  # keep same filename
        with out_path.open("w", encoding="utf-8") as out_file:
            json.dump(filtered_prs, out_file, indent=2)

        print(f"Wrote {len(filtered_prs)} error PR(s) → {out_path.relative_to(OUTPUT_DIR.parent)}")


if __name__ == "__main__":
    main()
