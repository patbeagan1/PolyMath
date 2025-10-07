#!/usr/bin/env -S uv run python
"""
PolyMath tool launcher (uv-compatible).

Usage:
  ./pm generate --list
  ./pm generate metric
  ./pm fix all
  ./pm search --pattern "UnitDistance"
  
Or with uv directly:
  uv run python pm generate --list
"""

from polymath_tool.cli import main

if __name__ == "__main__":
    raise SystemExit(main())

