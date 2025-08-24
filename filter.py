#!/usr/bin/env python3
# Extract generated tests and normalize class names:
# - remove trailing digits (e.g., ...Test2 -> ...Test)
# - remove trailing 'Test' or 'Tests'
# - append 'LLM_Test'
#
# Configure these two:
INPUT_JSON = "lang3.json"
OUT_DIR    = "out_tests"

import json, os, re, sys
from pathlib import Path
from typing import Dict, Any, Optional, Tuple, List

PACKAGE_RE = re.compile(r'^\s*package\s+([a-zA-Z_][\w\.]*)\s*;', re.MULTILINE)
PUBLIC_CLASS_RE = re.compile(r'^\s*public\s+(?:final\s+|abstract\s+)?class\s+([A-Za-z_]\w*)\b', re.MULTILINE)
ANY_CLASS_RE = re.compile(r'^\s*(?:public|protected|private)?\s*(?:final\s+|abstract\s+)?class\s+([A-Za-z_]\w*)\b', re.MULTILINE)

def read_json(path: Path) -> Any:
    with path.open("r", encoding="utf-8") as f:
        return json.load(f)

def write_text(path: Path, content: str) -> None:
    path.parent.mkdir(parents=True, exist_ok=True)
    with path.open("w", encoding="utf-8", newline="\n") as f:
        f.write(content)

def write_json(path: Path, payload: Any) -> None:
    path.parent.mkdir(parents=True, exist_ok=True)
    with path.open("w", encoding="utf-8") as f:
        json.dump(payload, f, indent=2, ensure_ascii=False)

def norm_java_pkg_to_path(pkg: str) -> Path:
    return Path(*pkg.split(".")) if pkg and pkg != "UNPACKAGED" else Path()

def guess_pkg_from_source_filename(source_filename: str) -> Optional[str]:
    sf = source_filename.replace("\\", "/")
    marker = "src/main/java/"
    if marker in sf:
        after = sf.split(marker, 1)[1]
        parts = after.split("/")
        if len(parts) >= 2:
            pkg_parts = parts[:-1]
            if pkg_parts:
                safe_parts = []
                for p in pkg_parts:
                    if re.match(r"^[A-Za-z_]\w*$", p):
                        safe_parts.append(p)
                    else:
                        return None
                return ".".join(safe_parts)
    return None

def base_name_without_ext(path_str: str) -> str:
    return Path(path_str).stem

def parse_java_test_metadata(java_src: str, fallback_src_filename: str) -> Tuple[str, str]:
    pkg_match = PACKAGE_RE.search(java_src)
    if pkg_match:
        pkg = pkg_match.group(1)
    else:
        pkg = guess_pkg_from_source_filename(fallback_src_filename) or "UNPACKAGED"

    m = PUBLIC_CLASS_RE.search(java_src)
    if not m:
        m = ANY_CLASS_RE.search(java_src)
    if m:
        cls = m.group(1)
    else:
        base = re.sub(r"Test$", "", base_name_without_ext(fallback_src_filename))
        cls = f"{base}Test"
    return pkg, cls

def normalize_class_name(cls: str) -> str:
    # 1) remove trailing digits (covers the “2 at the end” case)
    cls = re.sub(r"\d+$", "", cls)
    # 2) remove trailing 'Tests' or 'Test'
    cls = re.sub(r"(Tests|Test)$", "", cls)
    # 3) append LLM_Test if not already present
    if not cls.endswith("LLM_Test"):
        cls = f"{cls}LLM_Test"
    return cls

def replace_class_decl(java_src: str, old_cls: str, new_cls: str) -> str:
    # Replace ONLY the class declaration line’s name (first match).
    pat = re.compile(
        rf'^(\s*(?:public|protected|private)?\s*(?:final\s+|abstract\s+)?class\s+){old_cls}\b',
        re.MULTILINE
    )
    return pat.sub(rf'\1{new_cls}', java_src, count=1)

def summarize_result(pr_number: int,
                     pr_meta: Dict[str, Any],
                     per_file: List[Dict[str, Any]]) -> Dict[str, Any]:
    keep_keys = ["title", "state", "created_at", "merge_commit_sha", "base_sha", "head_sha", "user_login"]
    return {
        "pr_number": pr_number,
        **{k: pr_meta.get(k) for k in keep_keys if k in pr_meta},
        "extracted_count": sum(1 for x in per_file if x.get("status") == "written"),
        "skipped_count": sum(1 for x in per_file if x.get("status") == "skipped"),
        "files": per_file,
    }

def process(input_path: Path, out_dir: Path) -> None:
    data = read_json(input_path)
    if not isinstance(data, list):
        print("ERROR: Input JSON root must be a list of PR objects.", file=sys.stderr)
        sys.exit(1)

    out_dir.mkdir(parents=True, exist_ok=True)
    all_summaries = []

    for idx, pr in enumerate(data):
        pr_number = pr.get("pr_number")
        if not isinstance(pr_number, int):
            print(f"[WARN] Skipping item {idx}: missing/invalid pr_number.", file=sys.stderr)
            continue

        pr_out = out_dir / f"pr_{pr_number}"
        per_file_results: List[Dict[str, Any]] = []

        for cf in pr.get("changed_files") or []:
            do_exp = cf.get("do_experiment") is True
            gen = cf.get("generated_test_file_content")
            src_filename = cf.get("filename")
            status_in_pr = cf.get("status")

            rec = {
                "source_filename": src_filename,
                "status_in_pr": status_in_pr,
                "do_experiment": do_exp,
                "reason": None,
                "status": None,  # "written" | "skipped"
                "out_test_path": None,
                "package": None,
                "original_class_name": None,
                "final_class_name": None,
            }

            try:
                if not do_exp:
                    rec["status"] = "skipped"; rec["reason"] = "do_experiment is not true"
                    per_file_results.append(rec); continue
                if not gen or not str(gen).strip():
                    rec["status"] = "skipped"; rec["reason"] = "generated_test_file_content is empty"
                    per_file_results.append(rec); continue
                if not src_filename or not isinstance(src_filename, str):
                    rec["status"] = "skipped"; rec["reason"] = "missing source filename"
                    per_file_results.append(rec); continue

                pkg, cls = parse_java_test_metadata(gen, src_filename)
                rec["package"] = pkg
                rec["original_class_name"] = cls

                # —— rename: strip trailing digits/Test(s) and enforce LLM_Test
                final_cls = normalize_class_name(cls)
                rec["final_class_name"] = final_cls

                # Update the source with the new class name in the declaration
                gen_norm = replace_class_decl(gen, cls, final_cls)

                pkg_path = norm_java_pkg_to_path(pkg)
                out_test_path = pr_out / "src" / "test" / "java" / pkg_path / f"{final_cls}.java"
                write_text(out_test_path, gen_norm)
                rec["out_test_path"] = str(out_test_path.relative_to(out_dir))
                rec["status"] = "written"

            except Exception as e:
                rec["status"] = "skipped"
                rec["reason"] = f"exception: {type(e).__name__}: {e}"

            per_file_results.append(rec)

        pr_summary = summarize_result(pr_number, pr, per_file_results)
        write_json(pr_out / "summary.json", pr_summary)
        all_summaries.append(pr_summary)

    write_json(out_dir / "all_summaries.json", all_summaries)
    total_written = sum(s["extracted_count"] for s in all_summaries)
    total_skipped = sum(s["skipped_count"] for s in all_summaries)
    print(f"[OK] Wrote {total_written} tests; skipped {total_skipped}.")
    print(f"[OUT] Staged to: {out_dir.resolve()}")

def main():
    input_path = Path(os.path.expanduser(INPUT_JSON)).resolve()
    out_dir = Path(os.path.expanduser(OUT_DIR)).resolve()
    if not input_path.exists():
        print(f"ERROR: INPUT_JSON not found: {input_path}", file=sys.stderr)
        sys.exit(1)
    process(input_path, out_dir)

if __name__ == "__main__":
    main()
