#!/usr/bin/env python3
"""
Weight systems unit generation (1959 agreement).
Based on the 1959 International Yard and Pound Agreement and historical systems.
"""

from .common import get_measures_base


def generate_avoirdupois_1959() -> int:
    """Generate Avoirdupois weight units (1959 agreement)."""
    base_dir = get_measures_base() / "weight" / "avoirdupois_1959"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("Grain", "Pound(value / 7000).asBaseUnit()"),  # 1/7000 pound
        ("Dram", "Pound(value / 256).asBaseUnit()"),  # 1/256 pound
        ("Ounce", "Pound(value / 16).asBaseUnit()"),  # 1/16 pound
        ("Pound", "KiloGram(value * 0.45359237)"),  # 1959 agreement
        ("Stone", "Pound(value * 14).asBaseUnit()"),  # 14 pounds
        ("Quarter", "Pound(value * 28).asBaseUnit()"),  # 28 pounds
        ("Hundredweight", "Pound(value * 100).asBaseUnit()"),  # 100 pounds (US)
        ("LongHundredweight", "Pound(value * 112).asBaseUnit()"),  # 112 pounds (UK)
        ("ShortTon", "Pound(value * 2000).asBaseUnit()"),  # 2000 pounds (US)
        ("LongTon", "Pound(value * 2240).asBaseUnit()"),  # 2240 pounds (UK)
    ]
    
    template = """package com.measures.weight.avoirdupois_1959

import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import com.measures.weight.KiloGram
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


def generate_troy_1959() -> int:
    """Generate Troy weight units (1959 agreement)."""
    base_dir = get_measures_base() / "weight" / "troy_1959"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("TroyGrain", "Gram(value * 0.06479891)"),  # Same grain as avoirdupois
        ("TroyPennyweight", "TroyGrain(value * 24).asBaseUnit()"),  # 24 grains
        ("TroyOunce", "TroyPennyweight(value * 20).asBaseUnit()"),  # 20 pennyweights
        ("TroyPound", "TroyOunce(value * 12).asBaseUnit()"),  # 12 troy ounces
    ]
    
    template = """package com.measures.weight.troy_1959

import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import com.measures.weight.KiloGram
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


def generate_apothecaries() -> int:
    """Generate Apothecaries weight units."""
    base_dir = get_measures_base() / "weight" / "apothecaries"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("ApothecariesGrain", "Gram(value * 0.06479891)"),  # Same as troy grain
        ("ApothecariesScruple", "ApothecariesGrain(value * 20).asBaseUnit()"),  # 20 grains
        ("ApothecariesDram", "ApothecariesScruple(value * 3).asBaseUnit()"),  # 3 scruples
        ("ApothecariesOunce", "ApothecariesDram(value * 8).asBaseUnit()"),  # 8 drams
        ("ApothecariesPound", "ApothecariesOunce(value * 12).asBaseUnit()"),  # 12 ounces
    ]
    
    template = """package com.measures.weight.apothecaries

import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import com.measures.weight.KiloGram
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

