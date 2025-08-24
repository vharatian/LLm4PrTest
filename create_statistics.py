#!/usr/bin/env python3
"""
Summarise Evosuite JSON runs and draw colour-coded diagrams (saved in files/diagrams).

Author: ChatGPT • 24-Aug-2025
"""
from __future__ import annotations
from pathlib import Path
import json
from sys import prefix

import matplotlib.pyplot as plt

# ──────────────────────────────────────────────────────────────────────────────
JSON_DIR = Path("files/evaluations")          # evosuit_*.json live here
DIAG_DIR = Path("files/diagrams")             # all PNGs land here
DIAG_DIR.mkdir(exist_ok=True, parents=True)
# PREFIX = "evosuit"
PREFIX = "llm"
# ──────────────────────────────────────────────────────────────────────────────

# Category order *and* new colours
CATEGORIES = [
    # (label, colour)   ← keep order: Merge CE → Merge TF → Pass both → Base CE → Base TF
    ("Head Compile Error",           "#E53935"),   # Red 600  – strong crimson
    ("Head Test Failure",           "#EF9A9A"),   # Red 200  – soft rose (same hue, lighter)
    ("Pass both phases (Not effective)",   "#FB8C00"),   # Orange 600 – muted, prints well
    ("Base Compile Error (F2P)", "#7ca46c"),   # Green 200 – light sage; white text readable
    ("Base Test Failure (F2P)",      "#388E3C"),   # Green 600 – deep emerald
]
ORDER_IDX = [0, 1, 4, 2, 3]  # map original counts -> order above

# ──────────────────────────────────────────────────────────────────────────────
def is_compile_error(ph: dict) -> bool:
    return bool(ph.get("compile_error"))

def is_test_failure(ph: dict) -> bool:
    if is_compile_error(ph):
        return False
    if ph.get("test_failures") or ph.get("maven_failed_other"):
        return True
    s = ph.get("summary", {})
    return s.get("failures", 0) > 0 or s.get("errors", 0) > 0

def classify(objs: list[dict]) -> tuple[int, int, int, int, int, int]:
    mce = mtf = bce = btf = ok = inc = 0
    for idx, o in enumerate(objs, 1):
        merge, base = o.get("merge_phase"), o.get("base_phase")
        if merge is None or base is None:
            inc += 1
            print(f"   ⚠️  object #{idx} missing merge_phase or base_phase")
            continue
        if merge.get("summary", {}).get("skipped", 0):
            continue
        if is_compile_error(merge):
            mce += 1
            continue
        if is_test_failure(merge):
            mtf += 1
            continue
        if is_compile_error(base):
            bce += 1
        elif is_test_failure(base):
            btf += 1
        else:
            ok += 1
    return mce, mtf, bce, btf, ok, inc

# ──────────────────────────────────────────────────────────────────────────────
def bar_chart(title: str, counts: tuple[int, int, int, int, int], out: Path) -> None:
    vals = [counts[i] for i in ORDER_IDX]
    labels, colours = zip(*CATEGORIES)
    plt.figure(figsize=(8, 4))
    plt.bar(labels, vals, color=colours)
    plt.ylabel("Number of objects")
    plt.title(title)
    plt.xticks(rotation=15, ha="right")
    plt.tight_layout()
    plt.savefig(out)
    plt.close()

def stacked_chart(labels: list[str], perc_rows: list[list[float]], out: Path) -> None:
    _, colours = zip(*CATEGORIES)
    fig, ax = plt.subplots(figsize=(10, 0.6 * len(labels) + 1))

    left = [0.0] * len(labels)
    for idx, (cat, colour) in enumerate(CATEGORIES):
        vals = [row[idx] for row in perc_rows]
        ax.barh(labels, vals, left=left, color=colour, label=cat)

        # bigger, bolder in-bar % labels
        for y, (lft, val) in enumerate(zip(left, vals)):
            if val >= 5:
                ax.text(lft + val / 2, y, f"{val:.0f}%",
                        ha="center", va="center",
                        color="white", fontsize=12, fontweight="bold")
        left = [l + v for l, v in zip(left, vals)]

    # ---------- enlarge axis texts ----------
    ax.set_xlim(0, 100)
    ax.set_xlabel("Percentage of objects", fontsize=13, fontweight="bold")

    ax.set_yticklabels(labels, fontsize=12, fontweight="bold")   # ← add this line
    ax.tick_params(axis="x", labelsize=11)                       # keep x-ticks sized

    ax.invert_yaxis()

    # bigger, bold legend
    ax.legend(loc="upper center", ncol=2, bbox_to_anchor=(0.5, -0.08),
              prop={"size": 12, "weight": "bold"})

    plt.tight_layout()
    plt.savefig(out, bbox_inches="tight")
    plt.close()

# ──────────────────────────────────────────────────────────────────────────────
def main() -> None:
    files = sorted(JSON_DIR.glob("%s_*.json" % PREFIX))
    if not files:
        raise SystemExit(f"No %s_*.json files found in {JSON_DIR.resolve()}" % PREFIX)
    grand = [0, 0, 0, 0, 0, 0]
    rows, labels = [], []
    for jf in files:
        try:
            data = json.loads(jf.read_text(encoding="utf-8"))
        except (json.JSONDecodeError, UnicodeDecodeError) as e:
            print(f"⚠️  Skipping {jf.name}: {e}")
            continue
        if not isinstance(data, list):
            print(f"⚠️  Skipping {jf.name}: root element is not a list")
            continue
        c = classify(data)
        mce, mtf, bce, btf, ok, inc = c
        print(f"{jf.name:<30}  merge_CE={mce:>4}  merge_TF={mtf:>4}  "
              f"base_CE={bce:>4}  base_TF={btf:>4}  pass_both={ok:>4}  incomplete={inc:>4}")
        rows.append([mce, mtf, ok, bce, btf])
        labels.append(jf.stem.replace(f"{PREFIX}_", ""))
        grand = [g + v for g, v in zip(grand, c)]
        # bar_chart(f"Results for {jf.stem}", c, DIAG_DIR / f"{jf.stem}_stats.png")

    rows.append([grand[0], grand[1], grand[4], grand[2], grand[3]])
    labels.append("TOTAL")
    perc_rows = [[100 * v / sum(r) for v in r] for r in rows if sum(r)]
    stacked_chart(labels, perc_rows, DIAG_DIR / f"{PREFIX}_stacked.png")
    # bar_chart("Aggregate over all files",
    #           (grand[0], grand[1], grand[4], grand[2], grand[3]),
    #           DIAG_DIR / "aggregate_stats.png")

    print("\nAggregate totals:")
    print(f"  Merge compile error           : {grand[0]}")
    print(f"  Merge test failure            : {grand[1]}")
    print(f"  Base compile error (F2P)      : {grand[2]}")
    print(f"  Base test failure  (F2P)      : {grand[3]}")
    print(f"  Pass in both phases           : {grand[4]}")
    print(f"  Incomplete objects            : {grand[5]}")
    print(f"\n✅  All diagrams saved inside {DIAG_DIR.resolve()}")

if __name__ == "__main__":
    main()
