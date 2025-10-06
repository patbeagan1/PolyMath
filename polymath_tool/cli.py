#!/usr/bin/env python3
"""
Unified generation, fix, and search tool for the PolyMath project.

Usage (uv recommended):
  ./pm --help
  ./pm generate --list
  ./pm generate metric metric-volume
  ./pm fix all
  ./pm search --pattern "UnitDistance"

Or with uv/python:
  uv run python -m polymath_tool generate --list
  python -m polymath_tool fix all
"""

from __future__ import annotations

import argparse
import re
import sys
from pathlib import Path
from typing import Callable, Dict, Iterable, List, Sequence

from . import generators
from . import fixers


def get_repo_root() -> Path:
    """Get the PolyMath repository root dynamically."""
    # This file is in polymath_tool/, so parent is PolyMath/
    return Path(__file__).parent.parent.resolve()


def get_kotlin_roots() -> List[Path]:
    """Get all Kotlin source root directories."""
    repo_root = get_repo_root()
    return [
        repo_root / "units-common" / "src" / "commonMain" / "kotlin",
        repo_root / "units-base" / "src",
        repo_root / "physics-classical" / "src",
        repo_root / "math-base" / "src",
        repo_root / "math-geometry" / "src",
        repo_root / "math-algebra" / "src",
    ]


GENERATION_MAP: Dict[str, List[Callable[[], int]]] = {
    "astronomical-distance": [
        lambda: generators.AstronomicalDistanceGenerator().generate()
    ],
    "g-force-acceleration": [
        lambda: generators.GForceAccelerationGenerator().generate()
    ],

    "metric": [
        lambda: generators.MetricAccelerationGenerator().generate(),
        lambda: generators.MetricAmountGenerator().generate(),
        lambda: generators.MetricAreaGenerator().generate(),
        lambda: generators.MetricChargeGenerator().generate(),
        lambda: generators.MetricCurrentGenerator().generate(),
        lambda: generators.MetricDistanceGenerator().generate(),
        lambda: generators.MetricEnergyGenerator().generate(),
        lambda: generators.MetricForceGenerator().generate(),
        lambda: generators.MetricPowerGenerator().generate(),
        lambda: generators.MetricPressureGenerator().generate(),
        lambda: generators.MetricTimeGenerator().generate(),
        lambda: generators.MetricVelocityGenerator().generate(),
        lambda: generators.MetricVolumeGenerator().generate(),
        lambda: generators.MetricWeightGenerator().generate(),
    ],
    "metric-acceleration": [ lambda: generators.MetricAccelerationGenerator().generate() ],
    "metric-amount": [lambda: generators.MetricAmountGenerator().generate()],
    "metric-area": [lambda: generators.MetricAreaGenerator().generate()],
    "metric-charge": [lambda: generators.MetricChargeGenerator().generate()],
    "metric-current": [lambda: generators.MetricCurrentGenerator().generate()],
    "metric-distance": [lambda: generators.MetricDistanceGenerator().generate()],
    "metric-energy": [lambda: generators.MetricEnergyGenerator().generate()],
    "metric-force": [lambda: generators.MetricForceGenerator().generate()],
    "metric-power": [lambda: generators.MetricPowerGenerator().generate()],
    "metric-pressure": [lambda: generators.MetricPressureGenerator().generate()],
    "metric-time": [lambda: generators.MetricTimeGenerator().generate()],
    "metric-velocity": [lambda: generators.MetricVelocityGenerator().generate()],
    "metric-volume": [lambda: generators.MetricVolumeGenerator().generate()],
    "metric-weight": [lambda: generators.MetricWeightGenerator().generate()],
   
   "non-si-acceleration": [lambda: generators.NonSiAccelerationGenerator().generate()],
    "non-si-charge": [lambda: generators.NonSiChargeGenerator().generate()],
    "non-si-distance": [lambda: generators.NonSiDistanceGenerator().generate()],
    "non-si-energy": [lambda: generators.NonSiEnergyGenerator().generate()],
    "non-si-force": [lambda: generators.NonSiForceGenerator().generate()],
    "non-si-power": [lambda: generators.NonSiPowerGenerator().generate()],
    "non-si-pressure": [lambda: generators.NonSiPressureGenerator().generate()],
    "non-si-time": [lambda: generators.NonSiTimeGenerator().generate()],
    "non-si-volume": [lambda: generators.NonSiVolumeGenerator().generate()],
    "non-si-weight": [lambda: generators.NonSiWeightGenerator().generate()],
    
    "international-yard-distance": [lambda: generators.InternationalYardDistanceGenerator().generate()],

    "all": [
        lambda: generators.AstronomicalDistanceGenerator().generate(),
        lambda: generators.GForceAccelerationGenerator().generate(),
        lambda: generators.InternationalYardDistanceGenerator().generate(),
        lambda: generators.MetricAccelerationGenerator().generate(),
        lambda: generators.MetricAmountGenerator().generate(),
        lambda: generators.MetricAreaGenerator().generate(),
        lambda: generators.MetricChargeGenerator().generate(),
        lambda: generators.MetricCurrentGenerator().generate(),
        lambda: generators.MetricDistanceGenerator().generate(),
        lambda: generators.MetricEnergyGenerator().generate(),
        lambda: generators.MetricForceGenerator().generate(),
        lambda: generators.MetricPowerGenerator().generate(),
        lambda: generators.MetricPressureGenerator().generate(),
        lambda: generators.MetricTimeGenerator().generate(),
        lambda: generators.MetricVelocityGenerator().generate(),
        lambda: generators.MetricVolumeGenerator().generate(),
        lambda: generators.MetricWeightGenerator().generate(),
        lambda: generators.NonSiAccelerationGenerator().generate(),
        lambda: generators.NonSiChargeGenerator().generate(),
        lambda: generators.NonSiDistanceGenerator().generate(),
        lambda: generators.NonSiEnergyGenerator().generate(),
        lambda: generators.NonSiForceGenerator().generate(),
        lambda: generators.NonSiPowerGenerator().generate(),
        lambda: generators.NonSiPressureGenerator().generate(),
        lambda: generators.NonSiTimeGenerator().generate(),
        lambda: generators.NonSiVolumeGenerator().generate(),
        lambda: generators.NonSiWeightGenerator().generate(),
    ],
}


FIX_MAP: Dict[str, List[Callable[[], int]]] = {
    "imports": [
        fixers.fix_all_imports,
        fixers.fix_missing_imports,
        fixers.fix_all_remaining_imports,
        fixers.fix_import_paths,
    ],
    "force": [
        fixers.fix_force_units_simple,
        fixers.fix_force_units_completely,
    ],
    "types": [fixers.fix_misc_unit_types],
    "units": [fixers.fix_kilogram_imports, fixers.fix_liter_imports],
    "circular": [fixers.fix_circular_imports],
    "interfaces": [fixers.fix_extension_interface_mismatches],
    "final": [
        fixers.fix_final_compilation_errors,
        fixers.fix_final_compilation_issues,
        fixers.fix_final_issues,
        fixers.fix_remaining_import_issues,
    ],
    "all": [fixers.fix_all],
}


def cmd_generate(args: argparse.Namespace) -> int:
    if args.list:
        print("Available generation families:")
        for key in sorted(k for k in GENERATION_MAP.keys() if k != "all"):
            print(f"  - {key}")
        print("  - all")
        return 0

    families: List[str] = args.families or ["all"]
    exit_code = 0
    for family in families:
        funcs = GENERATION_MAP.get(family)
        if not funcs:
            print(
                f"[WARN] Unknown family '{family}'. Use --list to see options.",
                file=sys.stderr,
            )
            exit_code = 2
            continue
        print(f"[GEN] {family} -> {len(funcs)} generator(s)")
        for func in funcs:
            print(f"  - Running {func.__name__}")
            try:
                code = func()
                exit_code = exit_code or code
            except Exception as exc:
                print(f"[ERROR] {func.__name__} failed: {exc}", file=sys.stderr)
                exit_code = 1
    return exit_code


def cmd_fix(args: argparse.Namespace) -> int:
    tasks: List[str] = args.tasks or ["all"]
    exit_code = 0
    for task in tasks:
        funcs = FIX_MAP.get(task)
        if not funcs:
            print(
                f"[WARN] Unknown fix task '{task}'. Choices: {', '.join(sorted(FIX_MAP))}",
                file=sys.stderr,
            )
            exit_code = 2
            continue
        print(f"[FIX] {task} -> {len(funcs)} fixer(s)")
        for func in funcs:
            print(f"  - Running {func.__name__}")
            try:
                code = func()
                exit_code = exit_code or code
            except Exception as exc:
                print(f"[ERROR] {func.__name__} failed: {exc}", file=sys.stderr)
                exit_code = 1
    return exit_code


def iter_kotlin_files(root_dirs: Sequence[Path]) -> Iterable[Path]:
    for base in root_dirs:
        if not base.exists():
            continue
        for path in base.rglob("*.kt"):
            yield path
        for path in base.rglob("*.kts"):
            yield path


def cmd_search(args: argparse.Namespace) -> int:
    repo_root = get_repo_root()
    root = Path(args.root) if args.root else repo_root
    if (root / "src").exists() or (root / "units-common").exists():
        roots: List[Path] = get_kotlin_roots() if root == repo_root else [root]
    else:
        roots = [root]

    pattern = args.pattern
    flags = re.IGNORECASE if args.ignore_case else 0
    try:
        regex = re.compile(pattern, flags)
    except re.error as exc:
        print(f"[ERROR] Invalid regex: {exc}", file=sys.stderr)
        return 2

    max_results = args.limit
    num_matches = 0
    for file_path in iter_kotlin_files(roots):
        try:
            text = file_path.read_text(encoding="utf-8", errors="ignore")
        except (OSError, UnicodeDecodeError):
            continue
        for idx, line in enumerate(text.splitlines(), start=1):
            if regex.search(line):
                print(f"{file_path}:{idx}: {line.strip()}")
                num_matches += 1
                if 0 < max_results <= num_matches:
                    return 0
    return 0


def build_parser() -> argparse.ArgumentParser:
    parser = argparse.ArgumentParser(
        prog="polymath", description="PolyMath generation/fix/search tool"
    )
    sub = parser.add_subparsers(dest="command", required=True)

    p_gen = sub.add_parser("generate", help="Generate unit families")
    p_gen.add_argument("families", nargs="*", help="Families to generate (use --list)")
    p_gen.add_argument(
        "--list", action="store_true", help="List available families and exit"
    )
    p_gen.set_defaults(func=cmd_generate)

    p_fix = sub.add_parser("fix", help="Run fix utilities")
    p_fix.add_argument(
        "tasks",
        nargs="*",
        help=f"Fix task groups. Choices: {', '.join(sorted(FIX_MAP))}",
    )
    p_fix.set_defaults(func=cmd_fix)

    p_search = sub.add_parser(
        "search", help="Search Kotlin sources for a regex pattern"
    )
    p_search.add_argument(
        "--pattern", required=True, help="Regex pattern to search for"
    )
    p_search.add_argument(
        "--root", help="Root directory to search (defaults to PolyMath roots)"
    )
    p_search.add_argument(
        "--ignore-case", action="store_true", help="Case-insensitive search"
    )
    p_search.add_argument(
        "--limit", type=int, default=0, help="Max number of results (0 for unlimited)"
    )
    p_search.set_defaults(func=cmd_search)

    return parser


def main(argv: Sequence[str] | None = None) -> int:
    parser = build_parser()
    args = parser.parse_args(argv)
    return int(args.func(args))


if __name__ == "__main__":
    raise SystemExit(main())
