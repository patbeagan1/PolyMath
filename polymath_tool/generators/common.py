#!/usr/bin/env python3
"""
Common utilities for unit generation.
"""

from pathlib import Path


def get_repo_root() -> Path:
    """Get the PolyMath repository root dynamically."""
    # This file is in polymath_tool/generators/, so parent.parent is PolyMath/
    return Path(__file__).parent.parent.parent.resolve()


def get_measures_base() -> Path:
    """Get the measures base directory."""
    return get_repo_root() / "units-common" / "src" / "commonMain" / "kotlin" / "com" / "measures"
