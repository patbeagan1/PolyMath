#!/usr/bin/env python3
"""
US Survey unit generation (deprecated since 2023, legacy support).
Based on the 1959 International Yard and Pound Agreement.
"""

from .base_distance_generator import BaseDistanceGenerator


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


def generate_us_survey_area() -> int:
    """Generate US Survey area units (1959, deprecated since 2023)."""
    base_dir = get_measures_base() / "area" / "us_survey_1959"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("SurveySquareFoot", "SquareMeter(value * 0.09290304)"),  # Same as international
        ("SurveySquareYard", "SurveySquareFoot(value * 9).asBaseUnit()"),
        ("SurveySquareChain", "SurveySquareFoot(value * 4356).asBaseUnit()"),
        ("SurveyAcre", "SurveySquareFoot(value * 43560).asBaseUnit()"),
        ("SurveySection", "SurveyAcre(value * 640).asBaseUnit()"),
        ("SurveyTownship", "SurveySection(value * 36).asBaseUnit()"),
    ]
    
    template = """package com.measures.area.us_survey_1959

import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import com.measures.volume.UnitVolume
import kotlin.jvm.JvmInline

@JvmInline
value class {unit_name}(override val value: Double) : UnitArea<{unit_name}> {{
    override fun asType(d: Double) = {unit_name}(d)
    override fun asBaseUnit() = {base_conversion}

    override fun plus(other: UnitArea<*>): SquareMeter = UnitArea.plusUnit(this, other)
    override fun minus(other: UnitArea<*>): SquareMeter = UnitArea.minusUnit(this, other)
    override fun times(other: UnitDistance<*>): UnitVolume<*> = UnitArea.timesUnit(this, other)
}}

fun UnitArea<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""
    
    for unit_name, base_conversion in units:
        file_path = base_dir / f"{unit_name}.kt"
        content = template.format(unit_name=unit_name, base_conversion=base_conversion)
        file_path.write_text(content)
        print(f"Created: {file_path}")
    
    return 0

