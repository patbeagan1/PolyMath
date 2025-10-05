#!/usr/bin/env python3
"""
Non-SI power unit generation.
"""

from .base_power_generator import BasePowerGenerator


class NonSiPowerGenerator(BasePowerGenerator):
    """Generator for non-SI power units."""
    
    def __init__(self):
        super().__init__(
            subdirectory="non_si",
            package_name="com.measures.power.non_si"
        )
    
    def _get_units(self):
        """Get non-SI power units."""
        return [
            ("ErgPerSecond", "Watt(value * 1e-7)"),
            ("Horsepower", "Watt(value * 745.6998715822702)"),
            ("FootPoundPerSecond", "Watt(value * 1.3558179483314004)"),
        ]


def generate_non_si_power() -> int:
    """Generate non-SI power units."""
    generator = NonSiPowerGenerator()
    return generator.generate()
