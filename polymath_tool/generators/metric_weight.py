#!/usr/bin/env python3
"""
Metric weight unit generation.
"""

from .common import get_measures_base


def generate_metric_weight() -> int:
    """Generate metric weight units (Kilogram prefixes)."""
    base_dir = get_measures_base() / "weight" / "metric"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        # weight is special because the base unit is kilogram, not gram
        # all other units are divided by 1000 to get the base unit
        ("Gram", "1"),
        ("Attogram", "Consts.ATTO"),
        ("Decigram", "Consts.DECI"),
        ("Dekagram", "Consts.DEKA"),
        ("Exagram", "Consts.EXA"),
        ("Femtogram", "Consts.FEMTO"),
        ("Gigagram", "Consts.GIGA"),
        ("Hectogram", "Consts.HECTO"),
        ("Megagram", "Consts.MEGA"),
        ("Microgram", "Consts.MICRO"),
        ("Milligram", "Consts.MILLI"),
        ("Milligram", "Consts.MILLI"),
        ("Nanogram", "Consts.NANO"),
        ("Petagram", "Consts.PETA"),
        ("Picogram", "Consts.PICO"),
        ("Teragram", "Consts.TERA"),
        ("Yoctogram", "Consts.YOCTO"),
        ("Yottagram", "Consts.YOTTA"),
        ("Zeptogram", "Consts.ZEPTO"),
        ("Zettagram", "Consts.ZETTA")
    ]
    
    template = """package com.measures.weight.metric

import com.measures.Consts
import com.measures.weight.KiloGram
import com.measures.weight.UnitMass
import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import kotlin.jvm.JvmInline

@JvmInline
value class {unit_name}(override val value: Double) : UnitMass<{unit_name}> {{
    override fun asType(d: Double) = {unit_name}(d)
    override fun asBaseUnit() = KiloGram(value * {const_name} / 1000)

    override fun plus(other: UnitMass<*>): KiloGram = UnitMass.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): KiloGram = UnitMass.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.timesUnit(this, other)
}}

fun UnitMass<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""
    
    for unit_name, const_name in units:
        file_path = base_dir / f"{unit_name}.kt"
        content = template.format(unit_name=unit_name, const_name=const_name)
        file_path.write_text(content)
        print(f"Created: {file_path}")
    
    return 0
