#!/usr/bin/env python3
"""
US Survey unit generation (deprecated since 2023, legacy support).
Based on the 1959 International Yard and Pound Agreement.
"""

from .base_distance_generator import BaseDistanceGenerator
from .base_area_generator import BaseAreaGenerator


class USSurveyDistanceGenerator(BaseDistanceGenerator):
    """Generator for US Survey distance units (deprecated since 2023)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="us_survey_1959",
            package_name="com.measures.distance.us_survey_1959"
        )
    
    def _get_units(self):
        """Get US Survey distance units (deprecated since 2023)."""
        return [
            ("SurveyFoot", "Meter(value * 1200/3937)"),  # 0.3048006 m
            ("SurveyInch", "SurveyFoot(value / 12).asBaseUnit()"),
            ("SurveyYard", "SurveyFoot(value * 3).asBaseUnit()"),
            ("SurveyMile", "SurveyFoot(value * 5280).asBaseUnit()"),
            ("SurveyRod", "SurveyFoot(value * 16.5).asBaseUnit()"),  # 25 links
            ("SurveyChain", "SurveyFoot(value * 66).asBaseUnit()"),  # 4 rods
            ("SurveyFurlong", "SurveyFoot(value * 660).asBaseUnit()"),  # 10 chains
            ("SurveyLeague", "SurveyFoot(value * 15840).asBaseUnit()"),  # 3 miles
        ]


def generate_us_survey_distance() -> int:
    """Generate US Survey distance units (deprecated since 2023)."""
    generator = USSurveyDistanceGenerator()
    return generator.generate()


class USSurveyAreaGenerator(BaseAreaGenerator):
    """Generator for US Survey area units (deprecated since 2023)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="us_survey_1959",
            package_name="com.measures.area.us_survey_1959"
        )
    
    def _get_units(self):
        """Get US Survey area units (deprecated since 2023)."""
        return [
            ("SurveySquareFoot", "SquareMeter(value * 0.09290304)"),  # Same as international
            ("SurveySquareYard", "SurveySquareFoot(value * 9).asBaseUnit()"),
            ("SurveySquareChain", "SurveySquareFoot(value * 4356).asBaseUnit()"),
            ("SurveyAcre", "SurveySquareFoot(value * 43560).asBaseUnit()"),
            ("SurveySection", "SurveyAcre(value * 640).asBaseUnit()"),
            ("SurveyTownship", "SurveySection(value * 36).asBaseUnit()"),
        ]


def generate_us_survey_area() -> int:
    """Generate US Survey area units (1959, deprecated since 2023)."""
    generator = USSurveyAreaGenerator()
    return generator.generate()

