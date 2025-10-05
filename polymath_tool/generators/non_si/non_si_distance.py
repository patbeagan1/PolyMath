#!/usr/bin/env python3
"""
Non-SI distance unit generation.
"""

from .base_distance_generator import BaseDistanceGenerator


class NonSiDistanceGenerator(BaseDistanceGenerator):
    """Generator for non-SI distance units."""
    
    def __init__(self):
        super().__init__(
            subdirectory="non_si",
            package_name="com.measures.distance.non_si"
        )
    
    def _get_units(self):
        """Get non-SI distance units."""
        return [
            ("Angstroms", "Meter(value * 1e-10)"),
            ("Microns", "Meter(value * 1e-6)"),
            ("Mils", "Meter(value * 0.0000254)"),
        ]


def generate_non_si_distance() -> int:
    """Generate non-SI distance units."""
    generator = NonSiDistanceGenerator()
    return generator.generate()
