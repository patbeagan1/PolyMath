#!/usr/bin/env python3
"""
Current unit generation (Ampere).
"""

from .base_current_generator import BaseCurrentGenerator


class MetricCurrentGenerator(BaseCurrentGenerator):
    """Generator for metric current units (Ampere prefixes)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="metric",
            package_name="com.measures.current.metric"
        )
    
    def _get_units(self):
        """Get metric current units."""
        return [
            ("Milliampere", "Ampere(value * Consts.MILLI)"),
            ("Microampere", "Ampere(value * Consts.MICRO)"),
            ("Nanoampere", "Ampere(value * Consts.NANO)"),
            ("Picoampere", "Ampere(value * Consts.PICO)"),
            ("Femtoampere", "Ampere(value * Consts.FEMTO)"),
            ("Attoampere", "Ampere(value * Consts.ATTO)"),
            ("Zeptoampere", "Ampere(value * Consts.ZEPTO)"),
            ("Yoctoampere", "Ampere(value * Consts.YOCTO)"),
            ("Centiampere", "Ampere(value * Consts.CENTI)"),
            ("Deciampere", "Ampere(value * Consts.DECI)"),
            ("Decaampere", "Ampere(value * Consts.DEKA)"),
            ("Hectoampere", "Ampere(value * Consts.HECTO)"),
            ("Kiloampere", "Ampere(value * Consts.KILO)"),
            ("Megaampere", "Ampere(value * Consts.MEGA)"),
            ("Gigaampere", "Ampere(value * Consts.GIGA)"),
            ("Teraampere", "Ampere(value * Consts.TERA)"),
            ("Petaampere", "Ampere(value * Consts.PETA)"),
            ("Exaampere", "Ampere(value * Consts.EXA)"),
            ("Zettaampere", "Ampere(value * Consts.ZETTA)"),
            ("Yottaampere", "Ampere(value * Consts.YOTTA)"),
        ]
    
    def get_additional_imports(self):
        """Get additional imports for metric units."""
        return ["com.measures.Consts"]


def generate_current() -> int:
    """Generate current units (Ampere)."""
    generator = MetricCurrentGenerator()
    return generator.generate()
