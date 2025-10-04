#!/usr/bin/env python3
"""
Metric force unit generation.
"""

from .base_force_generator import BaseForceGenerator


class MetricForceGenerator(BaseForceGenerator):
    """Generator for metric force units (Newton prefixes)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="metric",
            package_name="com.measures.force.metric"
        )
    
    def _get_units(self):
        """Get metric force units."""
        return [
            ("Attonewton", "Newton(value * Consts.ATTO)"),
            ("Decinewton", "Newton(value * Consts.DECI)"),
            ("Dekanewton", "Newton(value * Consts.DEKA)"),
            ("Exanewton", "Newton(value * Consts.EXA)"),
            ("Femtonewton", "Newton(value * Consts.FEMTO)"),
            ("Giganewton", "Newton(value * Consts.GIGA)"),
            ("Hectonewton", "Newton(value * Consts.HECTO)"),
            ("Meganewton", "Newton(value * Consts.MEGA)"),
            ("Micronewton", "Newton(value * Consts.MICRO)"),
            ("Millinewton", "Newton(value * Consts.MILLI)"),
            ("Nanonewton", "Newton(value * Consts.NANO)"),
            ("Petanewton", "Newton(value * Consts.PETA)"),
            ("Piconewton", "Newton(value * Consts.PICO)"),
            ("Teranewton", "Newton(value * Consts.TERA)"),
            ("Yoctonewton", "Newton(value * Consts.YOCTO)"),
            ("Yottanewton", "Newton(value * Consts.YOTTA)"),
            ("Zeptonewton", "Newton(value * Consts.ZEPTO)"),
            ("Zettanewton", "Newton(value * Consts.ZETTA)")
        ]
    
    def get_additional_imports(self):
        """Get additional imports for metric units."""
        return ["com.measures.Consts"]


def generate_metric_force() -> int:
    """Generate metric force units (Newton prefixes)."""
    generator = MetricForceGenerator()
    return generator.generate()
