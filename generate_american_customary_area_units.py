#!/usr/bin/env python3
"""
Generate individual American Customary area unit files
"""

import os
from pathlib import Path

# Base directory
BASE_DIR = Path("/home/patrick/repo/incubator/PolyMath/units-common/src/commonMain/kotlin/com/measures/area/american_customary")

# Units to create (excluding already created ones)
units = [
    ("SquareSurveyFoot", "SquareMeter(value * 0.09290341)"),
    ("SquareSurveyChain", "SquareSurveyFoot(value * 4356.0).asBaseUnit()"),
    ("SurveySection", "SurveyAcre(value * 640).asBaseUnit()"),
    ("SurveyTownship", "SurveySection(value * 36.0).asBaseUnit()")
]

template = """package com.measures.area.american_customary

import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import kotlin.jvm.JvmInline

@JvmInline
value class {unit_name}(override val value: Double) : UnitArea<{unit_name}> {{
    override fun asType(d: Double) = {unit_name}(d)
    override fun asBaseUnit() = {base_unit_conversion}

    override operator fun plus(other: UnitArea<*>) = UnitArea.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitArea<*>) = UnitArea.Companion.minusUnit(this, other)
    override operator fun times(other: UnitDistance<*>) = UnitArea.Companion.timesUnit(this, other)
    override operator fun div(other: UnitDistance<*>) = UnitArea.Companion.divUnit(this, other)
}}

fun UnitArea<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""

for unit_name, base_unit_conversion in units:
    file_path = BASE_DIR / f"{unit_name}.kt"
    content = template.format(unit_name=unit_name, base_unit_conversion=base_unit_conversion)
    
    with open(file_path, 'w') as f:
        f.write(content)
    
    print(f"Created: {file_path}")
