#!/usr/bin/env python3
"""
Metric amount unit generation.
"""

from .base_amount_generator import BaseAmountGenerator


class MetricAmountGenerator(BaseAmountGenerator):
    """Generator for metric amount units (mole)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="metric",
            package_name="com.measures.amount.metric"
        )
    
    def _get_units(self):
        """Get metric amount units."""
        return [
            ("Yoctomole", "Mole(value * Consts.YOCTO)"),
            ("Zeptomole", "Mole(value * Consts.ZEPTO)"),
            ("Attomole", "Mole(value * Consts.ATTO)"),
            ("Femtomole", "Mole(value * Consts.FEMTO)"),
            ("Picomole", "Mole(value * Consts.PICO)"),
            ("Nanomole", "Mole(value * Consts.NANO)"),
            ("Micromole", "Mole(value * Consts.MICRO)"),
            ("Millimole", "Mole(value * Consts.MILLI)"),
            ("Centimole", "Mole(value * Consts.CENTI)"),
            ("Decimole", "Mole(value * Consts.DECI)"),
            ("Dekamole", "Mole(value * Consts.DEKA)"),
            ("Hectomole", "Mole(value * Consts.HECTO)"),
            ("Kilomole", "Mole(value * Consts.KILO)"),
            ("Megamole", "Mole(value * Consts.MEGA)"),
            ("Gigamole", "Mole(value * Consts.GIGA)"),
            ("Teramole", "Mole(value * Consts.TERA)"),
            ("Petamole", "Mole(value * Consts.PETA)"),
            ("Examole", "Mole(value * Consts.EXA)"),
            ("Zettamole", "Mole(value * Consts.ZETTA)"),
            ("Yottamole", "Mole(value * Consts.YOTTA)"),
        ]


def generate_metric_amount() -> int:
    """Generate metric amount units (mole)."""
    generator = MetricAmountGenerator()
    return generator.generate()
