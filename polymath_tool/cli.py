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
    "metric": [
        generators.generate_metric_distance, 
        generators.generate_metric_volume,
        generators.generate_metric_area,
        generators.generate_metric_acceleration,
        generators.generate_metric_energy,
        generators.generate_metric_force,
        generators.generate_metric_power,
        generators.generate_metric_pressure,
        generators.generate_metric_time,
        generators.generate_metric_velocity,
        generators.generate_metric_weight
    ],
    "metric-distance": [generators.generate_metric_distance],
    "metric-volume": [generators.generate_metric_volume],
    "metric-area": [generators.generate_metric_area],
    "metric-acceleration": [generators.generate_metric_acceleration],
    "metric-energy": [generators.generate_metric_energy],
    "metric-force": [generators.generate_metric_force],
    "metric-power": [generators.generate_metric_power],
    "metric-pressure": [generators.generate_metric_pressure],
    "metric-time": [generators.generate_metric_time],
    "metric-velocity": [generators.generate_metric_velocity],
    "metric-weight": [generators.generate_metric_weight],
    
    "american-customary": [
        generators.generate_american_customary_distance,
        generators.generate_american_customary_area,
        generators.generate_american_customary_fluid,
        generators.generate_american_customary_dry,
    ],
    "american-customary-distance": [generators.generate_american_customary_distance],
    "american-customary-area": [generators.generate_american_customary_area],
    "american-customary-fluid-volume": [generators.generate_american_customary_fluid],
    "american-customary-dry-volume": [generators.generate_american_customary_dry],
    
    
    "avoirdupois": [generators.generate_avoirdupois],
    "troy": [generators.generate_troy],
    
    # Weight systems (1959 agreement)
    "international_weight_avoirdupois": [generators.generate_avoirdupois_1959],
    "international_weight_troy": [generators.generate_troy_1959],
    "international_weight_apothecaries": [generators.generate_apothecaries],
    
    # US Survey units (deprecated)
    "us-survey-distance": [generators.generate_us_survey_distance],
    "us-survey-area": [generators.generate_us_survey_area],
    
    # US units (current)
    "us-distance": [generators.generate_us_international_distance],
    "us-area": [generators.generate_us_international_area],
    "us-fluid-volume": [generators.generate_us_international_fluid_volume],
    "us-dry-volume": [generators.generate_us_international_dry_volume],
    
    # UK Imperial units (pre-1824)
    "uk-imperial-pre1824-distance": [generators.generate_uk_imperial_pre1824_distance],
    "uk-imperial-pre1824-volume": [generators.generate_uk_imperial_pre1824_volume],
    
    # UK Imperial units (1824)
    "uk-imperial-1824-distance": [generators.generate_uk_imperial_1824_distance],
    "uk-imperial-1824-volume": [generators.generate_uk_imperial_1824_volume],
    
    # International 1959 agreement
    "international-1959-distance": [generators.generate_international_1959_distance],
    "international-1959-nautical": [generators.generate_international_1959_nautical],
    "international-1959-volume": [generators.generate_international_1959_volume],
    
    
    "all": [
        generators.generate_metric_distance,
        generators.generate_metric_volume,
        generators.generate_metric_area,
        generators.generate_metric_acceleration,
        generators.generate_metric_energy,
        generators.generate_metric_force,
        generators.generate_metric_power,
        generators.generate_metric_pressure,
        generators.generate_metric_time,
        generators.generate_metric_velocity,
        generators.generate_metric_weight,
        generators.generate_american_customary_distance,
        generators.generate_american_customary_area,
        generators.generate_american_customary_fluid,
        generators.generate_american_customary_dry,
        generators.generate_english_imperial,
        generators.generate_english_imperial_volume,
        generators.generate_english_international_volume,
        generators.generate_avoirdupois,
        generators.generate_troy,
        generators.generate_acceleration,
        generators.generate_non_si,
        generators.generate_remaining,
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
            print(f"[WARN] Unknown family '{family}'. Use --list to see options.", file=sys.stderr)
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
            print(f"[WARN] Unknown fix task '{task}'. Choices: {', '.join(sorted(FIX_MAP))}", file=sys.stderr)
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
    parser = argparse.ArgumentParser(prog="polymath", description="PolyMath generation/fix/search tool")
    sub = parser.add_subparsers(dest="command", required=True)

    p_gen = sub.add_parser("generate", help="Generate unit families")
    p_gen.add_argument("families", nargs="*", help="Families to generate (use --list)")
    p_gen.add_argument("--list", action="store_true", help="List available families and exit")
    p_gen.set_defaults(func=cmd_generate)

    p_fix = sub.add_parser("fix", help="Run fix utilities")
    p_fix.add_argument("tasks", nargs="*", help=f"Fix task groups. Choices: {', '.join(sorted(FIX_MAP))}")
    p_fix.set_defaults(func=cmd_fix)

    p_search = sub.add_parser("search", help="Search Kotlin sources for a regex pattern")
    p_search.add_argument("--pattern", required=True, help="Regex pattern to search for")
    p_search.add_argument("--root", help="Root directory to search (defaults to PolyMath roots)")
    p_search.add_argument("--ignore-case", action="store_true", help="Case-insensitive search")
    p_search.add_argument("--limit", type=int, default=0, help="Max number of results (0 for unlimited)")
    p_search.set_defaults(func=cmd_search)

    return parser


def main(argv: Sequence[str] | None = None) -> int:
    parser = build_parser()
    args = parser.parse_args(argv)
    return int(args.func(args))


if __name__ == "__main__":
    raise SystemExit(main())


