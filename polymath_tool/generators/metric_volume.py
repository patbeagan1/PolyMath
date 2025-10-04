#!/usr/bin/env python3
"""
Metric volume unit generation.
"""

from .base_volume_generator import BaseVolumeGenerator


class MetricVolumeGenerator(BaseVolumeGenerator):
    """Generator for metric volume units (liter prefixes)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="metric",
            package_name="com.measures.volume.metric"
        )
    
    def _get_units(self):
        """Get metric volume units."""
        return [
            ("Attoliter", "Liter(value * Consts.ATTO)"),
            ("Centiliter", "Liter(value * Consts.CENTI)"),
            ("CubicCentimeter", "Liter(value * Consts.MILLI)"),
            ("CubicMeter", "Liter(value * Consts.KILO)"),
            ("Deciliter", "Liter(value * Consts.DECI)"),
            ("Dekaliter", "Liter(value * Consts.DEKA)"),
            ("Exaliter", "Liter(value * Consts.EXA)"),
            ("Femtoliter", "Liter(value * Consts.FEMTO)"),
            ("Gigaliter", "Liter(value * Consts.GIGA)"),
            ("Hectoliter", "Liter(value * Consts.HECTO)"),
            ("Kiloliter", "Liter(value * Consts.KILO)"),
            ("Megaliter", "Liter(value * Consts.MEGA)"),
            ("Microliter", "Liter(value * Consts.MICRO)"),
            ("Milliliter", "Liter(value * Consts.MILLI)"),
            ("Nanoliter", "Liter(value * Consts.NANO)"),
            ("Petaliter", "Liter(value * Consts.PETA)"),
            ("Picoliter", "Liter(value * Consts.PICO)"),
            ("Teraliter", "Liter(value * Consts.TERA)"),
            ("Yoctoliter", "Liter(value * Consts.YOCTO)"),
            ("Yottaliter", "Liter(value * Consts.YOTTA)"),
            ("Zeptoliter", "Liter(value * Consts.ZEPTO)"),
            ("Zettaliter", "Liter(value * Consts.ZETTA)")
        ]
    
    def get_additional_imports(self):
        """Get additional imports for metric units."""
        return ["com.measures.Consts"]


def generate_metric_volume() -> int:
    """Generate metric volume units (liter prefixes)."""
    generator = MetricVolumeGenerator()
    return generator.generate()
