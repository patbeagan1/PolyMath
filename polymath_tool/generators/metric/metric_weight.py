#!/usr/bin/env python3
"""
Metric weight unit generation.
"""

from .base_weight_generator import BaseWeightGenerator


class MetricWeightGenerator(BaseWeightGenerator):
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
            ("Gram", "KiloGram(value / 1000)"),
            ("Attogram", "KiloGram(value * Consts.ATTO / 1000)"),
            ("Decigram", "KiloGram(value * Consts.DECI / 1000)"),
            ("Dekagram", "KiloGram(value * Consts.DEKA / 1000)"),
            ("Exagram", "KiloGram(value * Consts.EXA / 1000)"),
            ("Femtogram", "KiloGram(value * Consts.FEMTO / 1000)"),
            ("Gigagram", "KiloGram(value * Consts.GIGA / 1000)"),
            ("Hectogram", "KiloGram(value * Consts.HECTO / 1000)"),
            ("Megagram", "KiloGram(value * Consts.MEGA / 1000)"),
            ("Microgram", "KiloGram(value * Consts.MICRO / 1000)"),
            ("Milligram", "KiloGram(value * Consts.MILLI / 1000)"),
            ("Nanogram", "KiloGram(value * Consts.NANO / 1000)"),
            ("Petagram", "KiloGram(value * Consts.PETA / 1000)"),
            ("Picogram", "KiloGram(value * Consts.PICO / 1000)"),
            ("Teragram", "KiloGram(value * Consts.TERA / 1000)"),
            ("Yoctogram", "KiloGram(value * Consts.YOCTO / 1000)"),
            ("Yottagram", "KiloGram(value * Consts.YOTTA / 1000)"),
            ("Zeptogram", "KiloGram(value * Consts.ZEPTO / 1000)"),
            ("Zettagram", "KiloGram(value * Consts.ZETTA / 1000)"),
        ]

    def get_additional_imports(self):
        """Get additional imports for metric units."""
        return ["com.measures.Consts"]
