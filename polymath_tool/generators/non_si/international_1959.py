#!/usr/bin/env python3
"""
International 1959 area unit generation.
"""
from .base.base_distance_generator import BaseDistanceGenerator
from .base.base_area_generator import BaseAreaGenerator
from .base.base_volume_generator import BaseVolumeGenerator
from .base.base_weight_generator import BaseWeightGenerator


class International1959DistanceGenerator(BaseDistanceGenerator):
    """Generator for US International distance units (1959 standard)."""

    def __init__(self):
        super().__init__(
            subdirectory="international_1959",
            package_name="com.measures.distance.international_1959",
        )

    def _get_units(self):
        """Get US International distance units (1959 standard)."""
        return [
            ("Foot", "Meter(value * 0.3048)"),  # 1959 agreement
            ("Inch", "Foot(value / 12).asBaseUnit()"),
            ("Yard", "Foot(value * 3).asBaseUnit()"),
            ("Mile", "Foot(value * 5280).asBaseUnit()"),
        ]


class International1959AreaGenerator(BaseAreaGenerator):
    """Generator for International 1959 area units."""

    def __init__(self):
        super().__init__(
            subdirectory="international_1959",
            package_name="com.measures.area.international_1959",
        )

    def _get_units(self):
        """Get International 1959 area units."""
        return [
            ("SquareInch", "SquareMeter(value * 0.00064516)"),
            ("SquareFoot", "SquareMeter(value * 0.09290304)"),
            ("SquareYard", "SquareMeter(value * 0.83612736)"),
            ("SquareMile", "SquareMeter(value * 2589988.110336)"),
            ("Acre", "SquareMeter(value * 4046.8564224)"),
        ]



class Avoirdupois1959WeightGenerator(BaseWeightGenerator):
    """Generator for Avoirdupois weight units (1959 agreement)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="avoirdupois_1959",
            package_name="com.measures.weight.avoirdupois_1959"
        )
    
    def _get_units(self):
        """Get Avoirdupois weight units (1959 agreement)."""
        return [
            ("Grain", "Pound(value / 7000).asBaseUnit()"),  # 1/7000 pound
            ("Dram", "Pound(value / 256).asBaseUnit()"),  # 1/256 pound
            ("Ounce", "Pound(value / 16).asBaseUnit()"),  # 1/16 pound
            ("Pound", "KiloGram(value * 0.45359237)"),  # 1959 agreement
            ("Stone", "Pound(value * 14).asBaseUnit()"),  # 14 pounds
            ("Quarter", "Pound(value * 28).asBaseUnit()"),  # 28 pounds
            ("Hundredweight", "Pound(value * 100).asBaseUnit()"),  # 100 pounds (US)
            ("LongHundredweight", "Pound(value * 112).asBaseUnit()"),  # 112 pounds (UK)
            ("ShortTon", "Pound(value * 2000).asBaseUnit()"),  # 2000 pounds (US)
            ("LongTon", "Pound(value * 2240).asBaseUnit()"),  # 2240 pounds (UK)
        ]
