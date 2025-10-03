#!/usr/bin/env python3
"""
Weight systems unit generation.
"""

from .common import get_measures_base


def generate_avoirdupois() -> int:
    """Generate Avoirdupois weight units."""
    base_dir = get_measures_base() / "weight" / "avoirdupois"
    base_dir.mkdir(parents=True, exist_ok=True)
    
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
    override fun asBaseUnit() = {base_conversion}

    override fun plus(other: UnitMass<*>): KiloGram = UnitMass.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): KiloGram = UnitMass.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.timesUnit(this, other)
}}

fun UnitMass<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""
    
    for unit_name, base_conversion in units:
        file_path = base_dir / f"{unit_name}.kt"
        content = template.format(unit_name=unit_name, base_conversion=base_conversion)
        file_path.write_text(content)
        print(f"Created: {file_path}")
    
    return 0


def generate_troy() -> int:
    """Generate Troy weight units."""
    base_dir = get_measures_base() / "weight" / "troy"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("TroyGrain", "TroyPennyweight(value / 24).asBaseUnit()"),
        ("TroyPennyweight", "TroyOunce(value / 20).asBaseUnit()"),
        ("TroyOunce", "Gram(value * 31.1034768)"),
        ("TroyPound", "TroyOunce(value * 12).asBaseUnit()")
    ]
    
    template = """package com.measures.weight.troy

import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import com.measures.weight.Gram
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class {unit_name}(override val value: Double) : UnitMass<{unit_name}> {{
    override fun asType(d: Double) = {unit_name}(d)
    override fun asBaseUnit() = {base_conversion}

    override fun plus(other: UnitMass<*>): KiloGram = UnitMass.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): KiloGram = UnitMass.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.timesUnit(this, other)
}}

fun UnitMass<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""
    
    for unit_name, base_conversion in units:
        file_path = base_dir / f"{unit_name}.kt"
        content = template.format(unit_name=unit_name, base_conversion=base_conversion)
        file_path.write_text(content)
        print(f"Created: {file_path}")
    
    return 0
