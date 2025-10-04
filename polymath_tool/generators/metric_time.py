#!/usr/bin/env python3
"""
Metric time unit generation.
"""

from .base_time_generator import BaseTimeGenerator


class MetricTimeGenerator(BaseTimeGenerator):
    """Generator for metric time units (Second prefixes)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="metric",
            package_name="com.measures.time.metric"
        )
    
    def _get_units(self):
        """Get metric time units."""
        return [
            ("Attosecond", "Second(value * Consts.ATTO)"),
            ("Decisecond", "Second(value * Consts.DECI)"),
            ("Dekasecond", "Second(value * Consts.DEKA)"),
            ("Exasecond", "Second(value * Consts.EXA)"),
            ("Femtosecond", "Second(value * Consts.FEMTO)"),
            ("Gigasecond", "Second(value * Consts.GIGA)"),
            ("Hectosecond", "Second(value * Consts.HECTO)"),
            ("Megasecond", "Second(value * Consts.MEGA)"),
            ("Microsecond", "Second(value * Consts.MICRO)"),
            ("Millisecond", "Second(value * Consts.MILLI)"),
            ("Nanosecond", "Second(value * Consts.NANO)"),
            ("Petasecond", "Second(value * Consts.PETA)"),
            ("Picosecond", "Second(value * Consts.PICO)"),
            ("Terasecond", "Second(value * Consts.TERA)"),
            ("Yoctosecond", "Second(value * Consts.YOCTO)"),
            ("Yottasecond", "Second(value * Consts.YOTTA)"),
            ("Zeptosecond", "Second(value * Consts.ZEPTO)"),
            ("Zettasecond", "Second(value * Consts.ZETTA)")
        ]
    
    def get_additional_imports(self):
        """Get additional imports for metric units."""
        return ["com.measures.Consts"]


def generate_metric_time() -> int:
    """Generate metric time units (Second prefixes)."""
    generator = MetricTimeGenerator()
    return generator.generate()
