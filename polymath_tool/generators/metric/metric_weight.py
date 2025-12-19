#!/usr/bin/env python3
"""
Metric weight unit generation.
"""

from ..base.base_mass_generator import BaseMassGenerator


class MetricWeightGenerator(BaseMassGenerator):
    """Generator for metric weight units (Kilogram prefixes)."""

    def __init__(self):
        super().__init__(
            subdirectory="metric", package_name="com.measures.weight.metric"
        )

    def _get_units(self):
        """Get metric weight units."""
        return [
            # weight is special because the base unit is kilogram, not gram
            # all other units are divided by 1000 to get the base unit
            ("Gram", "Kilogram(value / 1000)"),
            ("Attogram", "Kilogram(value * Consts.ATTO / 1000)"),
            ("Decigram", "Kilogram(value * Consts.DECI / 1000)"),
            ("Dekagram", "Kilogram(value * Consts.DEKA / 1000)"),
            ("Exagram", "Kilogram(value * Consts.EXA / 1000)"),
            ("Femtogram", "Kilogram(value * Consts.FEMTO / 1000)"),
            ("Gigagram", "Kilogram(value * Consts.GIGA / 1000)"),
            ("Hectogram", "Kilogram(value * Consts.HECTO / 1000)"),
            ("Megagram", "Kilogram(value * Consts.MEGA / 1000)"),
            ("Microgram", "Kilogram(value * Consts.MICRO / 1000)"),
            ("Milligram", "Kilogram(value * Consts.MILLI / 1000)"),
            ("Nanogram", "Kilogram(value * Consts.NANO / 1000)"),
            ("Petagram", "Kilogram(value * Consts.PETA / 1000)"),
            ("Picogram", "Kilogram(value * Consts.PICO / 1000)"),
            ("Teragram", "Kilogram(value * Consts.TERA / 1000)"),
            ("Yoctogram", "Kilogram(value * Consts.YOCTO / 1000)"),
            ("Yottagram", "Kilogram(value * Consts.YOTTA / 1000)"),
            ("Zeptogram", "Kilogram(value * Consts.ZEPTO / 1000)"),
            ("Zettagram", "Kilogram(value * Consts.ZETTA / 1000)"),
        ]

    def get_additional_imports(self):
        """Get additional imports for metric units."""
        return ["com.measures.Consts"]
