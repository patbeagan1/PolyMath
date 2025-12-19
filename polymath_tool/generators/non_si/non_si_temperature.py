#!/usr/bin/env python3
"""
Non-SI temperature unit generation.
"""

from ..base.base_temperature_generator import BaseTemperatureGenerator


class NonSiTemperatureGenerator(BaseTemperatureGenerator):
    """Generator for non-SI temperature units."""
    
    def __init__(self):
        super().__init__(
            subdirectory="non_si",
            package_name="com.measures.temperature.non_si"
        )
    
    def _get_units(self):
        """Get non-SI temperature units."""
        return [
            # Celsius: K = C + 273.15, inverse: C = K - 273.15
            ("Celsius", "Kelvin(value + 273.15)", "kelvin - 273.15"),
            # Fahrenheit: K = (F - 32) / 1.8 + 273.15, inverse: F = (K - 273.15) * 1.8 + 32.0
            ("Fahrenheit", "Kelvin((value - 32.0) / 1.8 + 273.15)", "(kelvin - 273.15) * 1.8 + 32.0"),
        ]


def generate_non_si_temperature() -> int:
    """Generate non-SI temperature units."""
    generator = NonSiTemperatureGenerator()
    return generator.generate()
