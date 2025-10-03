#!/usr/bin/env python3
"""
Generate individual Avoirdupois weight unit files
"""

import os
from pathlib import Path

# Base directory
BASE_DIR = Path("/home/patrick/repo/incubator/PolyMath/units-common/src/commonMain/kotlin/com/measures/weight/avoirdupois")

# Units to create (excluding already created ones)
units = [
    ("Dram", "Pound(value / 256).asBaseUnit()"),
    ("Grain", "Pound(value / 7000).asBaseUnit()"),
    ("LongHundredWeight", "Pound(value * 112).asBaseUnit()"),
    ("LongTon", "Pound(value * 2240).asBaseUnit()"),
    ("ShortQuarter", "Pound(value * 25).asBaseUnit()"),
    ("LongQuarter", "Pound(value * 28).asBaseUnit()"),
    ("ShortHundredWeight", "Pound(value * 100).asBaseUnit()"),
    ("Stone", "Pound(value * 14).asBaseUnit()"),
    ("ShortTon", "Pound(value * 2000).asBaseUnit()")
]

template = """package com.measures.weight.avoirdupois

import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class {unit_name}(override val value: Double) : UnitMass<{unit_name}> {{
    override fun asType(d: Double) = {unit_name}(d)
    override fun asBaseUnit() = {base_unit_conversion}

    override fun plus(other: UnitMass<*>): KiloGram = UnitMass.Companion.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): KiloGram = UnitMass.Companion.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.Companion.timesUnit(this, other)
}}

fun UnitMass<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""

for unit_name, base_unit_conversion in units:
    file_path = BASE_DIR / f"{unit_name}.kt"
    content = template.format(unit_name=unit_name, base_unit_conversion=base_unit_conversion)
    
    with open(file_path, 'w') as f:
        f.write(content)
    
    print(f"Created: {file_path}")
