#!/usr/bin/env python3
"""
Non-SI pressure unit generation.
"""

from .base_pressure_generator import BasePressureGenerator


class NonSiPressureGenerator(BasePressureGenerator):
    """Generator for non-SI pressure units."""
    
    def __init__(self):
        super().__init__(
            subdirectory="non_si",
            package_name="com.measures.pressure.non_si"
        )
    
    def _get_units(self):
        """Get non-SI pressure units."""
        return [
            ("PoundsPerSquareInch", "Pascal(value * 6894.757293168361)"),
            ("Atmosphere", "Pascal(value * 101325.0)"),
            ("Bar", "Pascal(value * 100000.0)"),
            ("Millibar", "Pascal(value * 100.0)"),
            ("MillimeterOfMercury", "Pascal(value * 133.322387415)"),
            ("Torr", "Pascal(value * 133.32236842105263)"),
        ]


def generate_non_si_pressure() -> int:
    """Generate non-SI pressure units."""
    generator = NonSiPressureGenerator()
    return generator.generate()
