#!/usr/bin/env python3
"""
Generate individual metric volume unit files
"""

import os
from pathlib import Path

# Base directory
BASE_DIR = Path("/home/patrick/repo/incubator/PolyMath/units-common/src/commonMain/kotlin/com/measures/volume/metric")

# Units to create (excluding already created ones)
units = [
    ("Attoliter", "Consts.ATTO"),
    ("Centiliter", "Consts.CENTI"),
    ("CubicCentimeter", "0.000001 * 1000"),
    ("CubicMeter", "1 * 1000"),
    ("Deciliter", "Consts.DECI"),
    ("Dekaliter", "Consts.DEKA"),
    ("Exaliter", "Consts.EXA"),
    ("Femtoliter", "Consts.FEMTO"),
    ("Gigaliter", "Consts.GIGA"),
    ("Hectoliter", "Consts.HECTO"),
    ("Kiloliter", "Consts.KILO"),
    ("Megaliter", "Consts.MEGA"),
    ("Microliter", "Consts.MICRO"),
    ("Nanoliter", "Consts.NANO"),
    ("Petaliter", "Consts.PETA"),
    ("Picoliter", "Consts.PICO"),
    ("Teraliter", "Consts.TERA"),
    ("Yoctoliter", "Consts.YOCTO"),
    ("Yottaliter", "Consts.YOTTA"),
    ("Zeptoliter", "Consts.ZEPTO"),
    ("Zettaliter", "Consts.ZETTA")
]

template = """package com.measures.volume.metric

import com.measures.Consts
import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import com.measures.volume.UnitVolume
import kotlin.jvm.JvmInline

@JvmInline
value class {unit_name}(override val value: Double) : UnitVolume<{unit_name}> {{
    override fun asType(d: Double) = {unit_name}(d)
    override fun asBaseUnit() = Liter(value * {const_name})

    override operator fun plus(other: UnitVolume<*>) = UnitVolume.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitVolume<*>) = UnitVolume.Companion.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitVolume.Companion.divUnit(this, other)
    override operator fun div(other: UnitDistance<*>): SquareMeter = UnitVolume.Companion.divUnit(this, other)
}}

fun UnitVolume<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""

for unit_name, const_name in units:
    file_path = BASE_DIR / f"{unit_name}.kt"
    content = template.format(unit_name=unit_name, const_name=const_name)
    
    with open(file_path, 'w') as f:
        f.write(content)
    
    print(f"Created: {file_path}")
