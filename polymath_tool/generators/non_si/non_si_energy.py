#!/usr/bin/env python3
"""
Non-SI energy unit generation.
"""

from .base_energy_generator import BaseEnergyGenerator


class NonSiEnergyGenerator(BaseEnergyGenerator):
    """Generator for non-SI energy units."""
    
    def __init__(self):
        super().__init__(
            subdirectory="non_si",
            package_name="com.measures.energy.non_si"
        )
    
    def _get_units(self):
        """Get non-SI energy units."""
        return [
            ("BritishThermalUnit", "Joule(value * 1055.05585262)"),
            ("Calorie", "Joule(value * 4.184)"),
            ("ElectronVolt", "Joule(value * 1.602176634e-19)"),
            ("Erg", "Joule(value * 1e-7)"),
            ("Kilocalorie", "Joule(value * 4184.0)"),
            ("KilowattHour", "Joule(value * 3600000.0)"),
        ]


def generate_non_si_energy() -> int:
    """Generate non-SI energy units."""
    generator = NonSiEnergyGenerator()
    return generator.generate()
