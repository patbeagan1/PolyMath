#!/usr/bin/env python3
"""
Generate individual Troy weight unit files
"""

import os
from pathlib import Path

# Base directory
BASE_DIR = Path("/home/patrick/repo/incubator/PolyMath/units-common/src/commonMain/kotlin/com/measures/weight/troy")

# Units to create (excluding already created ones)
units = [
    ("TroyGrain", "TroyPennyweight(value / 24).asBaseUnit()"),
    ("TroyPennyweight", "TroyOunce(value / 20).asBaseUnit()"),
    ("TroyPound", "TroyOunce(value * 12.0).asBaseUnit()")
]

template = """package com.measures.weight.troy

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
