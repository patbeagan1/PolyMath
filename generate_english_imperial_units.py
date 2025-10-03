#!/usr/bin/env python3
"""
Generate individual English Imperial distance unit files
"""

import os
from pathlib import Path

# Base directory
BASE_DIR = Path("/home/patrick/repo/incubator/PolyMath/units-common/src/commonMain/kotlin/com/measures/distance/english_imperial")

# Units to create (excluding already created ones)
units = [
    ("ImperialThou", "ImperialFoot(this.value / 12000.0).asBaseUnit()"),
    ("ImperialBarleycorn", "ImperialFoot(this.value / 36.0).asBaseUnit()"),
    ("ImperialHand", "ImperialFoot(this.value / 3.0).asBaseUnit()"),
    ("ImperialYard", "ImperialFoot(this.value * 3.0).asBaseUnit()"),
    ("ImperialChain", "ImperialFoot(this.value * 66.0).asBaseUnit()"),
    ("ImperialFurlong", "ImperialFoot(this.value * 660.0).asBaseUnit()"),
    ("ImperialMile", "ImperialFoot(this.value * 5280).asBaseUnit()"),
    ("ImperialLeague", "ImperialFoot(this.value * 15840).asBaseUnit()")
]

template = """package com.measures.distance.english_imperial

import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import com.measures.time.UnitTime
import com.measures.velocity.MetersPerSecond
import kotlin.jvm.JvmInline

@JvmInline
value class {unit_name}(override val value: Double) : UnitDistance<{unit_name}> {{
    override fun asType(d: Double) = {unit_name}(d)
    override fun asBaseUnit() = {base_unit_conversion}

    override operator fun plus(other: UnitDistance<*>) = UnitDistance.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitDistance<*>) = UnitDistance.Companion.minusUnit(this, other)
    override operator fun times(other: UnitDistance<*>) = UnitDistance.Companion.timesUnit(this, other)
    override operator fun times(other: UnitArea<*>) = UnitDistance.Companion.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>): MetersPerSecond = UnitDistance.Companion.divUnit(this, other)
}}

fun UnitDistance<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""

for unit_name, base_unit_conversion in units:
    file_path = BASE_DIR / f"{unit_name}.kt"
    content = template.format(unit_name=unit_name, base_unit_conversion=base_unit_conversion)
    
    with open(file_path, 'w') as f:
        f.write(content)
    
    print(f"Created: {file_path}")
