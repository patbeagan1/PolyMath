#!/usr/bin/env python3
"""
Generate individual Non-SI acceleration unit files
"""

import os
from pathlib import Path

# Base directory
BASE_DIR = Path("/home/patrick/repo/incubator/PolyMath/units-common/src/commonMain/kotlin/com/measures/acceleration/non_si")

# Units to create (excluding already created ones)
units = [
    ("FeetPerSecondPerSecond", "MetersPerSecondPerSecond(this.value * 0.3048)"),
    ("Gal", "MetersPerSecondPerSecond(this.value * 0.01)"),
    ("CentimetersPerSecondSquared", "MetersPerSecondPerSecond(this.value * 0.01)"),
    ("KilometersPerSecondPerSecond", "MetersPerSecondPerSecond(this.value * 1000.0)")
]

template = """package com.measures.acceleration.non_si

import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import com.measures.time.UnitTime
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class {unit_name}(override val value: Double) : UnitAcceleration<{unit_name}> {{
    override fun asType(d: Double) = {unit_name}(d)
    override fun asBaseUnit() = {base_unit_conversion}

    override operator fun plus(other: UnitAcceleration<*>) = UnitAcceleration.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitAcceleration<*>) = UnitAcceleration.Companion.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitAcceleration.Companion.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitAcceleration.Companion.divUnit(this, other)
    override operator fun times(other: UnitMass<*>): Newton = Newton(this.asBaseUnit().value * other.asBaseUnit().value * 100)
}}

fun UnitAcceleration<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""

for unit_name, base_unit_conversion in units:
    file_path = BASE_DIR / f"{unit_name}.kt"
    content = template.format(unit_name=unit_name, base_unit_conversion=base_unit_conversion)
    
    with open(file_path, 'w') as f:
        f.write(content)
    
    print(f"Created: {file_path}")
