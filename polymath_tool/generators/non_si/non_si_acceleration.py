#!/usr/bin/env python3
"""
Non-SI acceleration unit generation.
"""

from .base_acceleration_generator import BaseAccelerationGenerator


class NonSiAccelerationGenerator(BaseAccelerationGenerator):
    """Generator for non-SI acceleration units."""
    
    def __init__(self):
        super().__init__(
            subdirectory="non_si",
            package_name="com.measures.acceleration.non_si"
        )
    
    def _get_units(self):
        """Get non-SI acceleration units."""
        return [
            ("Gal", "MetersPerSecondPerSecond(this.value * 0.01)"),
            ("CentimetersPerSecondSquared", "MetersPerSecondPerSecond(this.value * 0.01)"),
            ("FeetPerSecondPerSecond", "MetersPerSecondPerSecond(this.value * 0.3048)"),
            ("KilometersPerSecondPerSecond", "MetersPerSecondPerSecond(this.value * 1000.0)"),
        ]


def generate_non_si_acceleration() -> int:
    """Generate non-SI acceleration units."""
    generator = NonSiAccelerationGenerator()
    return generator.generate()

