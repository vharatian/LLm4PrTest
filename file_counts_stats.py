#!/usr/bin/env python3
"""
Stacked distribution of PR results by number of do_experiment files
+ line chart of F2P success-rate per repo.

Author: ChatGPT • 25-Aug-2025
"""
from __future__ import annotations
from pathlib import Path
import json
import matplotlib.pyplot as plt

# ──────────────────────────────────────────────────────────────────────────────
RAW_DIR       = Path("files/replication")              # raw PR descriptors
PREFIX        = "llm"                                  # file prefix
CATEGORY_JSON = Path("files/evaluations") / f"{PREFIX}_pr_categories.json"

DIAG_DIR = Path("files/diagrams")
DIAG_DIR.mkdir(parents=True, exist_ok=True)

OUT_STACKED = DIAG_DIR / f"{PREFIX}_file_count_stacked.png"
OUT_LINE    = DIAG_DIR / f"{PREFIX}_success_rate.png"
# ──────────────────────────────────────────────────────────────────────────────

CATEGORIES = [
    "Merge compile error",
    "Merge test failure",
    "Pass both phases",
    "Base compile error (F2P)",
    "Base test failure (F2P)",
]
BINS = ["1", "2-3", "4-5", "5+"]

# ──────────────────────────────────────────────────────────────────────────────
def load_category_mapping(path: Path) -> dict[str, str]:
    data = json.loads(path.read_text(encoding="utf-8"))
    pr_map: dict[str, str] = {}
    for proj, buckets in data.items():
        for cat, pr_list in buckets.items():
            if cat == "Incomplete":
                continue
            for pr in pr_list:
                pr_map[str(pr)] = cat
    return pr_map


def bin_for_count(n: int) -> str:
    if n <= 1:
        return "1"
    if n <= 3:
        return "2-3"
    if n <= 5:
        return "4-5"
    return "5+"


# ──────────────────────────────────────────────────────────────────────────────
def stacked_chart(bin_labels, perc_rows, out: Path) -> None:
    colours = ["#E53935", "#EF9A9A", "#FB8C00", "#7CA46C", "#388E3C"]
    fig, ax = plt.subplots(figsize=(10, 0.6 * len(bin_labels) + 1))
    left = [0.0] * len(bin_labels)

    for idx, colour in enumerate(colours):
        vals = [row[idx] for row in perc_rows]
        ax.barh(bin_labels, vals, left=left, color=colour, label=CATEGORIES[idx])
        for y, (lft, val) in enumerate(zip(left, vals)):
            if val >= 5:
                ax.text(lft + val / 2, y, f"{val:.0f}%",
                        ha="center", va="center",
                        color="white", fontsize=11, fontweight="bold")
        left = [l + v for l, v in zip(left, vals)]

    ax.set_xlim(0, 100)
    ax.set_xlabel("Percentage of PRs", fontweight="bold")
    ax.invert_yaxis()
    ax.legend(loc="upper center", ncol=2, bbox_to_anchor=(0.5, -0.08),
              prop={"size": 11, "weight": "bold"})
    plt.tight_layout()
    plt.savefig(out, bbox_inches="tight")
    plt.close()


def line_chart(repo_rates: dict[str, list[float]], out: Path) -> None:
    """
    Draw one line per repo with success-rate across bins.
    The y-axis upper-limit is set to the next ‘nice’ multiple of 10
    above the largest observed rate (with a minimum of 10 % and a cap at 100 %).
    """
    # ── determine dynamic y-axis upper bound ────────────────────────────────
    observed_max = max((max(rates) for rates in repo_rates.values()), default=0.0)
    if observed_max <= 0:
        observed_max = 1.0                      # avoid a flat axis

    # round UP to nearest 10, then add a small margin (e.g. +5)
    y_top = min(100, ((int(observed_max) // 10 + 1) * 10) + 5)
    y_top = max(y_top, 10)                      # never below 10 %

    # ── plot ────────────────────────────────────────────────────────────────
    x = range(len(BINS))
    plt.figure(figsize=(8, 5))

    for repo, rates in repo_rates.items():
        plt.plot(x, rates, marker="o", label=repo)

    plt.xticks(x, BINS)
    plt.xlabel("Number of files with do_experiment = true")
    plt.ylabel("F2P success-rate (%)")
    plt.title("Fail-to-Pass success-rate vs. changed files")
    plt.ylim(0, y_top)
    plt.grid(True, linestyle="--", linewidth=0.5, alpha=0.6)
    plt.legend(title="Repository", bbox_to_anchor=(1.02, 1), loc="upper left")
    plt.tight_layout()
    plt.savefig(out)
    plt.close()

# ──────────────────────────────────────────────────────────────────────────────
def main() -> None:
    if not CATEGORY_JSON.is_file():
        raise SystemExit(f"Category file not found: {CATEGORY_JSON}")
    pr_category = load_category_mapping(CATEGORY_JSON)
    print(f"Loaded {len(pr_category):,} PR-category pairs from report.")

    # --- data structures -----------------------------------------------------
    # bin_counts_all[bin][category]            → int (aggregate across repos)
    bin_counts_all = {b: {c: 0 for c in CATEGORIES} for b in BINS}

    # repo_bin_counts[repo][bin][category]     → int (per repo)
    repo_bin_counts: dict[str, dict[str, dict[str, int]]] = {}

    unmatched: set[str] = set()

    # --- scan raw descriptor files ------------------------------------------
    for jf in sorted(RAW_DIR.glob("*.json")):
        repo = jf.stem.replace(f"{PREFIX}_", "")
        repo_bin_counts[repo] = {b: {c: 0 for c in CATEGORIES} for b in BINS}

        try:
            objs = json.loads(jf.read_text(encoding="utf-8"))
        except (json.JSONDecodeError, UnicodeDecodeError) as e:
            print(f"⚠️  Skipping {jf.name}: {e}")
            del repo_bin_counts[repo]
            continue
        if not isinstance(objs, list):
            print(f"⚠️  Skipping {jf.name}: root element is not a list")
            del repo_bin_counts[repo]
            continue

        for pr_obj in objs:
            pr_num = str(pr_obj.get("pr_number", "?"))
            changed = pr_obj.get("changed_files", [])
            count_do = sum(1 for f in changed if f.get("do_experiment"))
            bin_lbl = bin_for_count(count_do)

            cat = pr_category.get(pr_num)
            if cat is None:
                unmatched.add(pr_num)
                continue

            # update aggregates
            bin_counts_all[bin_lbl][cat] += 1
            repo_bin_counts[repo][bin_lbl][cat] += 1

    # ---------- stacked chart (unchanged behaviour) --------------------------
    perc_rows = []
    for b in BINS:
        total = sum(bin_counts_all[b].values())
        perc_rows.append([100 * bin_counts_all[b][c] / total if total else 0
                          for c in CATEGORIES])
    stacked_chart(BINS, perc_rows, OUT_STACKED)
    print(f"✅  Stacked chart saved to {OUT_STACKED.relative_to(DIAG_DIR.parent)}")

    # ---------- success-rate per repo ----------------------------------------
    repo_rates: dict[str, list[float]] = {}
    for repo, bdict in repo_bin_counts.items():
        rates = []
        for b in BINS:
            f2p = bdict[b]["Base compile error (F2P)"] + bdict[b]["Base test failure (F2P)"]
            total = sum(bdict[b].values())
            rates.append(100 * f2p / total if total else 0.0)
        repo_rates[repo] = rates

    line_chart(repo_rates, OUT_LINE)
    print(f"✅  Success-rate line chart saved to {OUT_LINE.relative_to(DIAG_DIR.parent)}")

    # ---------- console summary ----------------------------------------------
    print("\nCounts per bin (aggregate):")
    for b in BINS:
        print(f"  {b:<3}: " + ", ".join(f"{c.split()[0]}={bin_counts_all[b][c]}"
                                        for c in CATEGORIES))

    if unmatched:
        print(f"\n⚠️  {len(unmatched)} PR(s) in raw data weren’t present in "
              f"{CATEGORY_JSON.name}: {', '.join(sorted(unmatched)[:10])}"
              f"{' …' if len(unmatched) > 10 else ''}")


if __name__ == "__main__":
    main()
