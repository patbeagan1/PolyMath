#!/usr/bin/env python3
"""
UK Imperial unit generation.
"""

from ..base.base_distance_generator import BaseDistanceGenerator
from ..base.base_area_generator import BaseAreaGenerator
from ..base.base_volume_generator import BaseVolumeGenerator
from ..base.base_mass_generator import BaseMassGenerator


class UkDistanceGenerator(BaseDistanceGenerator):
    """Generator for UK Imperial distance units."""

    def __init__(self):
        super().__init__(
            subdirectory="uk_imperial",
            package_name="com.measures.distance.uk_imperial",
        )

    def _get_units(self):
        """Get UK Imperial distance units."""
        return [
            # Basic units (based on international yard agreement of 1959)
            ("UKTwip", "UKFoot(value / 17280).asBaseUnit()"),
            ("UKThou", "UKFoot(value / 12000).asBaseUnit()"),
            ("UKBarleycorn", "UKFoot(value / 36).asBaseUnit()"),
            ("UKInch", "UKFoot(value / 12).asBaseUnit()"),
            ("UKHand", "UKFoot(value / 3).asBaseUnit()"),
            ("UKFoot", "Meter(value * 0.3048).asBaseUnit()"),
            ("UKYard", "UKFoot(value * 3).asBaseUnit()"),
            ("UKChain", "UKFoot(value * 66).asBaseUnit()"),
            ("UKFurlong", "UKFoot(value * 660).asBaseUnit()"),
            ("UKMile", "UKFoot(value * 5280).asBaseUnit()"),
            ("UKLeague", "UKFoot(value * 15840).asBaseUnit()"),
 
            # Nautical units (UK version)
            ("UKFathom", "UKNauticalMile(value / 1000).asBaseUnit()"),
            ("UKCable", "UKNauticalMile(value / 100).asBaseUnit()"),
            ("UKNauticalMile", "UKFoot(value * 6076.1154855643).asBaseUnit()"),
 
            # Survey units 
            ("UKLink", "UKFoot(value * (66/100)).asBaseUnit()"),
            ("UKRod", "UKFoot(value * (66/4)).asBaseUnit()"),
        ]


class UkAreaGenerator(BaseAreaGenerator):
    """Generator for UK Imperial area units."""

    def __init__(self):
        super().__init__(
            subdirectory="uk_imperial",
            package_name="com.measures.area.uk_imperial",
        )

    def _get_units(self):
        """Get UK Imperial area units."""
        return [
            # Basic area units (square feet, yards, miles)
            ("UKSquareInch", "UKSquareFoot(value / 144).asBaseUnit()"),
            ("UKSquareFoot", "SquareMeter(value * 0.09290304)"),  # Base unit for UK area
            ("UKSquareYard", "UKSquareFoot(value * 9).asBaseUnit()"),
            ("UKSquareMile", "UKSquareFoot(value * 27878400).asBaseUnit()"),
            # Traditional UK area units
            ("UKAcre", "UKSquareFoot(value * 43560).asBaseUnit()"),  # 43,560 square feet
            ("UKRood", "UKSquareFoot(value * 10890).asBaseUnit()"),  # 1/4 acre
            ("UKPerch", "UKSquareFoot(value * 272.25).asBaseUnit()"),  # 1/160 acre
        ]


class UkVolumeGenerator(BaseVolumeGenerator):
    """Generator for UK Imperial volume units."""

    def __init__(self):
        super().__init__(
            subdirectory="uk_imperial",
            package_name="com.measures.volume.uk_imperial",
        )

    def _get_units(self):
        """Get UK Imperial volume units."""
        return [
            # Imperial fluid units (based on 4.54609 L gallon)
            ("UKFluidOunce", "UKGallon(value / 160).asBaseUnit()"),  # 1/160 gallon
            ("UKGill", "UKGallon(value / 32).asBaseUnit()"),  # 5 fl oz
            ("UKPint", "UKGallon(value / 8).asBaseUnit()"),  # 20 fl oz
            ("UKQuart", "UKGallon(value / 4).asBaseUnit()"),  # 40 fl oz
            ("UKGallon", "Liters(value * 4.54609)"),  # Base unit for UK system
            # Imperial dry units (bushel system)
            ("UKPeck", "UKGallon(value * 2).asBaseUnit()"),  # 2 gallons
            ("UKBushel", "UKGallon(value * 8).asBaseUnit()"),  # 8 gallons

            # Apothecary fluid units (UK)
            ("UKApothecaryMinim", "UKApothecaryPint(value / 9600).asBaseUnit()"),
            ("UKApothecaryFluidScruple", "UKApothecaryPint(value / 480).asBaseUnit()"),   # 1/480 pint
            ("UKApothecaryFluidDram", "UKApothecaryPint(value / 160).asBaseUnit()"),      # 1/160 pint
            ("UKApothecaryFluidOunce", "UKApothecaryPint(value / 20).asBaseUnit()"),  # 1/20 pint
            ("UKApothecaryPint", "Liters(value / 1000 * 28.4130625).asBaseUnit()"),        # 1 pint (apothecary)
            ("UKApothecaryQuart", "UKApothecaryPint(value * 2).asBaseUnit()"),   # 2 pints
            ("UKApothecaryGallon", "UKApothecaryPint(value * 8).asBaseUnit()"),  # 8 pints
        ]

class UkWeightGenerator(BaseMassGenerator):
    """Generator for UK Imperial weight units."""

    def __init__(self):
        super().__init__(
            subdirectory="uk_imperial",
            package_name="com.measures.weight.uk_imperial",
        )
        
    def _get_units(self):
        """Get UK Imperial weight units."""
        return [
            ("UKGrain", "UKPound(value / 7000).asBaseUnit()"),  # 1/7000 pound
            ("UKDrachm", "UKPound(value / 256).asBaseUnit()"),  # 1/256 pound
            ("UKOunce", "UKPound(value / 16).asBaseUnit()"),    # 1/16 pound
            ("UKPound", "Kilogram(value * 0.45359237)"),        # Base unit for UK system
            ("UKStone", "UKPound(value * 14).asBaseUnit()"),    # 14 pounds
            ("UKQuarter", "UKPound(value * 28).asBaseUnit()"),  # 28 pounds
            ("UKHundredweight", "UKPound(value * 112).asBaseUnit()"),  # 112 pounds
            ("UKLongTon", "UKPound(value * 2240).asBaseUnit()"),  # 2240 pounds

            # Other units
            ("UKSlug", "Kilogram(value * 14.59390294).asBaseUnit()"),
        ]