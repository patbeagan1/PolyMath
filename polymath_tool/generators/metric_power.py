#!/usr/bin/env python3
"""
Metric power unit generation.
"""

from .base_power_generator import BasePowerGenerator


class MetricPowerGenerator(BasePowerGenerator):
    """Generator for metric power units (Watt prefixes)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="metric",
            package_name="com.measures.power.metric"
        )
    
    def _get_units(self):
        """Get metric power units."""
        return [
            ("Attowatt", "Watt(value * Consts.ATTO)"),
            ("Deciwatt", "Watt(value * Consts.DECI)"),
            ("Dekawatt", "Watt(value * Consts.DEKA)"),
            ("Exawatt", "Watt(value * Consts.EXA)"),
            ("Femtowatt", "Watt(value * Consts.FEMTO)"),
            ("Gigawatt", "Watt(value * Consts.GIGA)"),
            ("Hectowatt", "Watt(value * Consts.HECTO)"),
            ("Megawatt", "Watt(value * Consts.MEGA)"),
            ("Microwatt", "Watt(value * Consts.MICRO)"),
            ("Milliwatt", "Watt(value * Consts.MILLI)"),
            ("Nanowatt", "Watt(value * Consts.NANO)"),
            ("Petawatt", "Watt(value * Consts.PETA)"),
            ("Picowatt", "Watt(value * Consts.PICO)"),
            ("Terawatt", "Watt(value * Consts.TERA)"),
            ("Yoctowatt", "Watt(value * Consts.YOCTO)"),
            ("Yottawatt", "Watt(value * Consts.YOTTA)"),
            ("Zeptowatt", "Watt(value * Consts.ZEPTO)"),
            ("Zettawatt", "Watt(value * Consts.ZETTA)")
        ]
    
    def get_additional_imports(self):
        """Get additional imports for metric units."""
        return ["com.measures.Consts"]


def generate_metric_power() -> int:
    """Generate metric power units (Watt prefixes)."""
    generator = MetricPowerGenerator()
    return generator.generate()
