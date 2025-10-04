#!/usr/bin/env python3
"""
Metric distance unit generation.
"""

from .base_distance_generator import BaseDistanceGenerator


class MetricDistanceGenerator(BaseDistanceGenerator):
    """Generator for metric distance units (meter prefixes)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="metric",
            package_name="com.measures.distance.metric"
        )
    
    def _get_units(self):
        """Get metric distance units."""
        return [
            ("Attometer", "Meter(value * Consts.ATTO)"),
            ("Decimeter", "Meter(value * Consts.DECI)"),
            ("Dekameter", "Meter(value * Consts.DEKA)"),
            ("Exameter", "Meter(value * Consts.EXA)"),
            ("Femtometer", "Meter(value * Consts.FEMTO)"),
            ("Gigameter", "Meter(value * Consts.GIGA)"),
            ("Hectometer", "Meter(value * Consts.HECTO)"),
            ("Megameter", "Meter(value * Consts.MEGA)"),
            ("Micrometer", "Meter(value * Consts.MICRO)"),
            ("Millimeter", "Meter(value * Consts.MILLI)"),
            ("Nanometer", "Meter(value * Consts.NANO)"),
            ("Petameter", "Meter(value * Consts.PETA)"),
            ("Picometer", "Meter(value * Consts.PICO)"),
            ("Terameter", "Meter(value * Consts.TERA)"),
            ("Yoctometer", "Meter(value * Consts.YOCTO)"),
            ("Yottameter", "Meter(value * Consts.YOTTA)"),
            ("Zeptometer", "Meter(value * Consts.ZEPTO)"),
            ("Zettameter", "Meter(value * Consts.ZETTA)")
        ]
    
    def get_additional_imports(self):
        """Get additional imports for metric units."""
        return ["com.measures.Consts"]


def generate_metric_distance() -> int:
    """Generate metric distance units (meter prefixes)."""
    generator = MetricDistanceGenerator()
    return generator.generate()
