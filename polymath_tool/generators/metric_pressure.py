#!/usr/bin/env python3
"""
Metric pressure unit generation.
"""

from .base_pressure_generator import BasePressureGenerator


class MetricPressureGenerator(BasePressureGenerator):
    """Generator for metric pressure units (Pascal prefixes)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="metric",
            package_name="com.measures.pressure.metric"
        )
    
    def _get_units(self):
        """Get metric pressure units."""
        return [
            ("Attopascal", "Pascal(value * Consts.ATTO)"),
            ("Decipascal", "Pascal(value * Consts.DECI)"),
            ("Dekapascal", "Pascal(value * Consts.DEKA)"),
            ("Exapascal", "Pascal(value * Consts.EXA)"),
            ("Femtopascal", "Pascal(value * Consts.FEMTO)"),
            ("Gigapascal", "Pascal(value * Consts.GIGA)"),
            ("Hectopascal", "Pascal(value * Consts.HECTO)"),
            ("Megapascal", "Pascal(value * Consts.MEGA)"),
            ("Micropascal", "Pascal(value * Consts.MICRO)"),
            ("Millipascal", "Pascal(value * Consts.MILLI)"),
            ("Nanopascal", "Pascal(value * Consts.NANO)"),
            ("Petapascal", "Pascal(value * Consts.PETA)"),
            ("Picopascal", "Pascal(value * Consts.PICO)"),
            ("Terapascal", "Pascal(value * Consts.TERA)"),
            ("Yoctopascal", "Pascal(value * Consts.YOCTO)"),
            ("Yottapascal", "Pascal(value * Consts.YOTTA)"),
            ("Zeptopascal", "Pascal(value * Consts.ZEPTO)"),
            ("Zettapascal", "Pascal(value * Consts.ZETTA)")
        ]
    
    def get_additional_imports(self):
        """Get additional imports for metric units."""
        return ["com.measures.Consts"]


def generate_metric_pressure() -> int:
    """Generate metric pressure units (Pascal prefixes)."""
    generator = MetricPressureGenerator()
    return generator.generate()
