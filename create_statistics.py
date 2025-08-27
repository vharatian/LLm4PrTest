#!/usr/bin/env python3
"""
Summarise Evosuite JSON runs, draw colour‑coded diagrams (bar + stacked),
**and emit a JSON report listing which PR numbers fall into which category for
every project**.

Author: ChatGPT • 26‑Aug‑2025
"""
from __future__ import annotations
from pathlib import Path
import json
import re
import matplotlib.pyplot as plt

# ──────────────────────────────────────────────────────────────────────────────
JSON_DIR = Path("files/evaluations")          # evosuit_*.json live here
DIAG_DIR = Path("files/diagrams")             # all PNGs land here
DIAG_DIR.mkdir(exist_ok=True, parents=True)
PREFIX    = "llm"                          # filename prefix to pick up
OUT_JSON  = JSON_DIR / f"{PREFIX}_pr_categories.json"
# ──────────────────────────────────────────────────────────────────────────────

# Category order *and* colours (used for charts)
CATEGORIES = [
    ("Error",        "#E53935"),  # 0
    ("Ineffective",           "#FB8C00"),  # 2
    ("F2P",    "#388E3C"),  # 4
]

# ──────────────────────────────────────────────────────────────────────────────
# ― helpers -------------------------------------------------------------------

def is_ineffective(o: dict) -> bool:
    if "merge_phase" in o and "base_phase" in o:
        if o["merge_phase"]["is_compiled"] and o["base_phase"]["is_compiled"]:
            if o["merge_phase"]["failed_count"] != o["merge_phase"]["total_count"]:
                if o["merge_phase"]["failed_count"] == o["base_phase"]["failed_count"]:
                    return True

        return False

def is_f2p(o: dict) -> bool:
    if "merge_phase" in o and "base_phase" in o:
        if o["merge_phase"]["is_compiled"]:
            if o["merge_phase"]["failed_count"] != o["merge_phase"]["total_count"]:

                if not o["base_phase"]["is_compiled"]:
                    return True

                if o["merge_phase"]["failed_count"] != o["base_phase"]["failed_count"]:
                    return True

    return False


_PR_RE = re.compile(r"_pr_(\d+)", re.I)


def extract_pr_number(obj: dict) -> str:
    """
    Return the PR number as string, trying several known places.
    Falls back to '?' when nothing useful is found.
    """
    for key in ("pr_number", "pr", "number", "pull_request", "pull_request_number"):
        if key in obj:
            return str(obj[key])
    for ph_key in ("merge_phase", "base_phase"):
        arc = obj.get(ph_key, {}).get("surefire_archive")
        if isinstance(arc, str):
            m = _PR_RE.search(arc)
            if m:
                return m.group(1)
    return "?"

# ──────────────────────────────────────────────────────────────────────────────


def stacked_chart(labels: list[str], perc_rows: list[list[float]], out: Path) -> None:
    """Draw a *percentage* stacked horizontal bar chart.

    Parameters
    ----------
    labels     : list[str]
        Row labels (top‑to‑bottom).
    perc_rows  : list[list[float]]
        Same length as *labels*; each inner list must have one percentage per
        entry in ``CATEGORIES``.
    out        : Path
        PNG file name to save the chart to.
    """
    _, colours = zip(*CATEGORIES)
    fig, ax = plt.subplots(figsize=(10, 0.6 * len(labels) + 1))

    left = [0.0] * len(labels)
    for idx, (cat, colour) in enumerate(CATEGORIES):
        vals = [row[idx] for row in perc_rows]
        ax.barh(labels, vals, left=left, color=colour, label=cat)
        for y, (lft, val) in enumerate(zip(left, vals)):
            if val >= 5:  # only label sizeable slices
                ax.text(lft + val / 2, y, f"{val:.0f}%", ha="center", va="center",
                        color="white", fontsize=12, fontweight="bold")
        left = [l + v for l, v in zip(left, vals)]

    ax.set_xlim(0, 100)
    ax.set_xlabel("Percentage of PRs", fontsize=13, fontweight="bold")
    ax.set_yticklabels(labels, fontsize=12, fontweight="bold")
    ax.tick_params(axis="x", labelsize=11)
    ax.invert_yaxis()
    ax.legend(loc="upper center", ncol=2, bbox_to_anchor=(0.5, -0.08),
              prop={"size": 12, "weight": "bold"})
    plt.tight_layout()
    plt.savefig(out, bbox_inches="tight")
    plt.close()

# ──────────────────────────────────────────────────────────────────────────────
# ― categorisation ------------------------------------------------------------

def categorise_project(objs: list[dict]) -> dict[str, list[str]]:
    """Return a mapping  { category_name -> [pr numbers] }  for one project."""
    buckets = {c[0]: [] for c in CATEGORIES}
    buckets["Incomplete"] = []  # track missing merge/base phases

    for idx, o in enumerate(objs, 1):
        pr = extract_pr_number(o)
        merge, base = o.get("merge_phase"), o.get("base_phase")

        if merge is None or base is None:
            buckets["Incomplete"].append(pr)
            print(f"   ⚠️  object #{idx} (PR {pr}) missing merge_phase or base_phase")
            continue

        if is_f2p(o):
            buckets["F2P"].append(pr)
        elif is_ineffective(o):
            buckets["Ineffective"].append(pr)
        else:
            buckets["Error"].append(pr)


    return buckets

# ──────────────────────────────────────────────────────────────────────────────
# ― main ----------------------------------------------------------------------

def main() -> None:
    files = sorted(JSON_DIR.glob(f"{PREFIX}_*.json"))
    if not files:
        raise SystemExit(f"No {PREFIX}_*.json files found in {JSON_DIR.resolve()}")

    # final JSON to be dumped
    report: dict[str, dict[str, list[str]]] = {}

    grand_counts = [0, 0, 0]  # order matches CATEGORIES
    proj_counts: dict[str, list[int]] = {}

    for jf in files:
        try:
            data = json.loads(jf.read_text(encoding="utf-8"))
        except (json.JSONDecodeError, UnicodeDecodeError) as e:
            print(f"⚠️  Skipping {jf.name}: {e}")
            continue
        if not isinstance(data, list):
            print(f"⚠️  Skipping {jf.name}: root element is not a list")
            continue

        buckets = categorise_project(data)
        proj = jf.stem.replace(f"{PREFIX}_", "")
        report[proj] = buckets

        # --- counts & console log ---
        counts = [len(buckets[c[0]]) for c in CATEGORIES]   # keep same order
        inc     = len(buckets["Incomplete"])
        print(f"{jf.name:<30}  "
              f"Error={counts[0]:>4}  Ineffective={counts[1]:>4}  "
              f"F2P={counts[2]:>4} ")

        proj_counts[proj] = counts
        for i, v in enumerate(counts):
            grand_counts[i] += v

        # per-file bar‑chart (uncomment if needed)
        # bar_chart(f"Results for {jf.stem}", counts, DIAG_DIR / f"{jf.stem}_stats.png")

    # ---------- write JSON report ----------
    OUT_JSON.write_text(json.dumps(report, indent=2, ensure_ascii=False))
    print(f"\n📄  Detailed PR lists saved to  {OUT_JSON.resolve()}")

    # ---------- aggregate diagrams ----------
    # Bar‑chart with absolute counts over *all* files.
    # bar_chart("Aggregate over all files", grand_counts, DIAG_DIR / "aggregate_stats.png")

    # Stacked chart with per‑project category percentages
    if proj_counts:
        labels = list(proj_counts.keys())
        perc_rows: list[list[float]] = []

        for proj in labels:
            ct = proj_counts[proj]
            tot = sum(ct)
            perc_rows.append([(v / tot * 100) if tot else 0.0 for v in ct])

        # ── NEW: append an overall “Total” row ────────────────────────────────
        grand_total = sum(grand_counts)
        total_perc = [(v / grand_total * 100) if grand_total else 0.0
                      for v in grand_counts]

        labels.append("Total")
        perc_rows.append(total_perc)
        # ─────────────────────────────────────────────────────────────────────

        stacked_chart(labels, perc_rows,
                      DIAG_DIR / f"{PREFIX}_stacked.png")
        print(f"\n📈  Aggregate stacked chart saved to "
              f"{(DIAG_DIR / f'{PREFIX}_stacked.png').resolve()}")

    print("\nAggregate totals:")
    for (label, _), total in zip(CATEGORIES, grand_counts):
        print(f"  {label:<30}: {total}")

    print(f"\n✅  All diagrams saved inside {DIAG_DIR.resolve()}")


if __name__ == "__main__":
    main()
