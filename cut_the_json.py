import json
from pathlib import Path

INPUT = Path("files/evaluations/evosuite_lang.json")

with INPUT.open("r", encoding="utf-8") as f:
    data = json.load(f)


data = data[0:50]

with INPUT.open("w", encoding="utf-8") as f:
    json.dump(data, f, indent=4)