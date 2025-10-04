#!/usr/bin/env python3
"""
Metric energy unit generation.
"""

from .base_energy_generator import BaseEnergyGenerator


class MetricEnergyGenerator(BaseEnergyGenerator):
    """Generator for metric energy units (Joule prefixes)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="metric",
            package_name="com.measures.energy.metric"
        )
    
    def _get_units(self):
        """Get metric energy units."""
        return [
            ("Attojoule", "Joule(value * Consts.ATTO)"),
            ("Decijoule", "Joule(value * Consts.DECI)"),
            ("Dekajoule", "Joule(value * Consts.DEKA)"),
            ("Exajoule", "Joule(value * Consts.EXA)"),
            ("Femtojoule", "Joule(value * Consts.FEMTO)"),
            ("Gigajoule", "Joule(value * Consts.GIGA)"),
            ("Hectojoule", "Joule(value * Consts.HECTO)"),
            ("Megajoule", "Joule(value * Consts.MEGA)"),
            ("Microjoule", "Joule(value * Consts.MICRO)"),
            ("Millijoule", "Joule(value * Consts.MILLI)"),
            ("Nanojoule", "Joule(value * Consts.NANO)"),
            ("Petajoule", "Joule(value * Consts.PETA)"),
            ("Picojoule", "Joule(value * Consts.PICO)"),
            ("Terajoule", "Joule(value * Consts.TERA)"),
            ("Yoctojoule", "Joule(value * Consts.YOCTO)"),
            ("Yottajoule", "Joule(value * Consts.YOTTA)"),
            ("Zeptojoule", "Joule(value * Consts.ZEPTO)"),
            ("Zettajoule", "Joule(value * Consts.ZETTA)")
        ]
    
    def get_additional_imports(self):
        """Get additional imports for metric units."""
        return ["com.measures.Consts"]


def generate_metric_energy() -> int:
    """Generate metric energy units (Joule prefixes)."""
    generator = MetricEnergyGenerator()
    return generator.generate()
