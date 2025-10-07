#!/usr/bin/env python3
"""
US Customary unit generation.
"""

from ..base.base_distance_generator import BaseDistanceGenerator
from ..base.base_area_generator import BaseAreaGenerator
from ..base.base_volume_generator import BaseVolumeGenerator
from ..base.base_weight_generator import BaseWeightGenerator


class UsDistanceGenerator(BaseDistanceGenerator):
    """Generator for US Customary distance units."""

    def __init__(self):
        super().__init__(
            subdirectory="us_customary",
            package_name="com.measures.distance.us_customary",
        )

    def _get_units(self):
        """Get US Customary distance units."""
        return [
            # Basic units (based on international yard agreement of 1959)
            ("USTwip", "USInch(value / 1440).asBaseUnit()"),
            ("USMil", "USFoot(value / 1000).asBaseUnit()"),
            ("USPoint", "USInch(value / 72).asBaseUnit()"),
            ("USPica", "USInch(value / 6).asBaseUnit()"),
            ("USInch", "USFoot(value / 12).asBaseUnit()"),
            ("USFoot", "Meter(value * 0.3048)"),  # Base unit for US system
            ("USYard", "USFoot(value * 3).asBaseUnit()"),
            ("USMile", "USFoot(value * 5280).asBaseUnit()"),
            ("USLeague", "USFoot(value * 15840).asBaseUnit()"),
 
            # Nautical units (US version)
            ("USFathom", "USYard(value * 2).asBaseUnit()"),
            ("USCable", "USFathom(value * 120).asBaseUnit()"),
            ("USNauticalMile", "USCable(value * 8.43904926).asBaseUnit()"),
 
            # Survey units (US Survey foot - obsolete as of 2023)
            ("USSurveyLink", "USSurveyFoot(value * 0.66).asBaseUnit()"),
            ("USSurveyFoot", "Meter(value * (1200/3937))"),  # US Survey foot (obsolete as of 2023)
            ("USSurveyRod", "USSurveyFoot(value * 16.5).asBaseUnit()"),
            ("USSurveyChain", "USSurveyFoot(value * 66).asBaseUnit()"),
            ("USSurveyFurlong", "USSurveyFoot(value * 660).asBaseUnit()"),
            ("USSurveyMile", "USSurveyFoot(value * 5280).asBaseUnit()"),
            ("USSurveyLeague", "USSurveyFoot(value * 15840).asBaseUnit()"),
        ]


class UsAreaGenerator(BaseAreaGenerator):
    """Generator for US Customary area units."""

    def __init__(self):
        super().__init__(
            subdirectory="us_customary",
            package_name="com.measures.area.us_customary",
        )

    def _get_units(self):
        """Get US Customary area units."""
        return [
            # Basic area units (square feet, yards, miles)
            ("USSquareInch", "USSquareFoot(value / 144).asBaseUnit()"),
            ("USSquareFoot", "SquareMeter(value * 0.09290304)"),  # Base unit for US area
            ("USSquareYard", "USSquareFoot(value * 9).asBaseUnit()"),
            ("USSquareMile", "USSquareFoot(value * 27878400).asBaseUnit()"),

            # Before 2023 units (US Survey/Customary)
            ("USSquareSurveyFoot1959to2022", "SquareMeter(value * 1440000/15499969)"),
            ("USSquareSurveyChain1959to2022", "USSquareSurveyFoot1959to2022(value * 4356).asBaseUnit()"),
            ("USSurveyAcre1959to2022", "USSquareSurveyFoot1959to2022(value * 43560).asBaseUnit()"),
            ("USSurveySection1959to2022", "USSquareSurveyFoot1959to2022(value * 27878400).asBaseUnit()"),
            ("USSurveyTownship1959to2022", "USSquareSurveyFoot1959to2022(value * 1003622400).asBaseUnit()"),

            ("USSquareSurveyChain", "USSquareFoot(value * 4356).asBaseUnit()"),
            ("USSurveyAcre", "USSquareFoot(value * 43560).asBaseUnit()"),
        ]


class UsFluidVolumeGenerator(BaseVolumeGenerator):
    """Generator for US Customary fluid volume units."""

    def __init__(self):
        super().__init__(
            subdirectory="us_customary_fluid",
            package_name="com.measures.volume.us_customary_fluid",
        )

    def _get_units(self):
        """Get US Customary fluid volume units."""
        return [
             # Basic fluid units (based on 231 cubic inch wine gallon)
            ("USMinim", "USFluidOunce(value / 480).asBaseUnit()"),  # 1/480 US fluid ounce
            ("USFluidDram", "USFluidOunce(value / 8).asBaseUnit()"),  # 1/8 US fluid ounce
            ("USTeaspoon", "USFluidOunce(value / 6).asBaseUnit()"),  # 1/6 US fluid ounce
            ("USTablespoon", "USFluidOunce(value / 2).asBaseUnit()"),  # 1/2 US fluid ounce
            ("USFluidOunce", "USFluidGallon(value / 128).asBaseUnit()"),
            ("USShot", "USFluidOunce(value * 1.5).asBaseUnit()"),  # 1.5 US fluid ounces
            ("USGill", "USFluidOunce(value * 4).asBaseUnit()"),  # 4 US fluid ounces
            ("USCup", "USFluidOunce(value * 8).asBaseUnit()"),  # 8 US fluid ounces
            ("USFluidPint", "USCup(value * 2).asBaseUnit()"),  # 2 US cups (liquid pint)
            ("USFluidQuart", "USPint(value * 2).asBaseUnit()"),  # 2 US pints (liquid quart)
            ("USPottle", "USQuart(value * 2).asBaseUnit()"),  # 2 US quarts (liquid pottle)
            ("USFluidGallon", "Liter(value * 3.785411784)"),  # 1 US gallon (liquid), base unit
            ("USFluidBarrel", "USFluidGallon(value * 31.5).asBaseUnit()"),  # 31.5 US gallons (liquid barrel)
            ("USOilBarrel", "USFluidGallon(value * 42).asBaseUnit()"),  # 42 US gallons (oil barrel)
            ("USHogshead", "USFluidGallon(value * 63).asBaseUnit()"),  # 63 US gallons (hogshead)
         ]


class UsDryVolumeGenerator(BaseVolumeGenerator):
    """Generator for US Customary dry volume units."""

    def __init__(self):
        super().__init__(
            subdirectory="us_customary_dry",
            package_name="com.measures.volume.us_customary_dry",
        )

    def _get_units(self):
        """Get US Customary dry volume units."""
        return [
            # Dry volume units (based on Winchester measure)
            ("USDryPint", "Liters(value * 0.5506104713575).asBaseUnit()"),  # Dry pint
            ("USDryQuart", "USDryPint(value * 2).asBaseUnit()"),  # Dry quart
            ("USDryGallon", "USDryPint(value * 8).asBaseUnit()"),  # Dry gallon
            ("USPeck", "USDryGallon(value * 2).asBaseUnit()"),  # Peck
            ("USBushel", "USDryGallon(value * 8).asBaseUnit()"),  # Bushel
            ("USDryBarrel", "USDryGallon(value * 26.25).asBaseUnit()"),  # 3.281 bushels
        ]


class UsWeightGenerator(BaseWeightGenerator):
    """Generator for US Customary weight units."""

    def __init__(self):
        super().__init__(
            subdirectory="us_customary",
            package_name="com.measures.weight.us_customary",
        )
        
    def _get_units(self):
        """Get US Customary weight units."""
        return [
            # Avoirdupois mass units
            ("USGrain", "USPound(value / 7000).asBaseUnit()"),  # 1/7000 pound (grain)
            ("USDram", "USPound(value / 256).asBaseUnit()"),    # 1/256 pound (dram)
            ("USOunce", "USPound(value / 16).asBaseUnit()"),    # 1/16 pound (ounce)
            ("USPound", "Kilogram(value * 0.45359237)"),        # 1 pound = 0.45359237 kg (base unit)
            ("USShortHundredweight", "USPound(value * 100).asBaseUnit()"),  # 100 pounds (short hundredweight)
            ("USLongHundredweight", "USPound(value * 112).asBaseUnit()"),  # 100 pounds (short hundredweight)
            ("USShortTon", "USPound(value * 2000).asBaseUnit()"),      # 2000 pounds (short ton)
            ("USLongTon", "USPound(value * 2240).asBaseUnit()"),      # 2240 pounds (long ton)
        ]

