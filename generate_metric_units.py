#!/usr/bin/env python3
"""
Generate individual metric distance unit files
"""

import os
from pathlib import Path

# Base directory
BASE_DIR = Path("/home/patrick/repo/incubator/PolyMath/units-common/src/commonMain/kotlin/com/measures/distance/metric")

# Units to create (excluding already created ones)
units = [
    ("Attometer", "Consts.ATTO"),
    ("Decimeter", "Consts.DECI"), 
    ("Dekameter", "Consts.DEKA"),
    ("Exameter", "Consts.EXA"),
    ("Femtometer", "Consts.FEMTO"),
    ("Gigameter", "Consts.GIGA"),
    ("Hectometer", "Consts.HECTO"),
    ("Megameter", "Consts.MEGA"),
    ("Micrometer", "Consts.MICRO"),
    ("Nanometer", "Consts.NANO"),
    ("Petameter", "Consts.PETA"),
    ("Picometer", "Consts.PICO"),
    ("Terameter", "Consts.TERA"),
    ("Yoctometer", "Consts.YOCTO"),
    ("Yottameter", "Consts.YOTTA"),
    ("Zeptometer", "Consts.ZEPTO"),
    ("Zettameter", "Consts.ZETTA")
]

template = """package com.measures.distance.metric

import com.measures.Consts
import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import com.measures.time.UnitTime
import com.measures.velocity.MetersPerSecond
import kotlin.jvm.JvmInline

@JvmInline
value class {unit_name}(override val value: Double) : UnitDistance<{unit_name}> {{
    override fun asType(d: Double) = {unit_name}(d)
    override fun asBaseUnit() = Meter(value * {const_name})

    override operator fun plus(other: UnitDistance<*>) = UnitDistance.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitDistance<*>) = UnitDistance.Companion.minusUnit(this, other)
    override operator fun times(other: UnitDistance<*>) = UnitDistance.Companion.timesUnit(this, other)
    override operator fun times(other: UnitArea<*>) = UnitDistance.Companion.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>): MetersPerSecond = UnitDistance.Companion.divUnit(this, other)
}}

fun UnitDistance<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""

for unit_name, const_name in units:
    file_path = BASE_DIR / f"{unit_name}.kt"
    content = template.format(unit_name=unit_name, const_name=const_name)
    
    with open(file_path, 'w') as f:
        f.write(content)
    
    print(f"Created: {file_path}")
