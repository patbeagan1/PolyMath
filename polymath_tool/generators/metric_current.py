#!/usr/bin/env python3
"""
Current unit generation (Ampere).
"""

from .common import get_measures_base


def generate_current() -> int:
    """Generate current units (Ampere)."""
    base_dir = get_measures_base() / "current" / "metric"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("Milliampere", "Ampere(value * Consts.MILLI)"),
        ("Microampere", "Ampere(value * Consts.MICRO)"),
        ("Nanoampere", "Ampere(value * Consts.NANO)"),
        ("Picoampere", "Ampere(value * Consts.PICO)"),
        ("Femtoampere", "Ampere(value * Consts.FEMTO)"),
        ("Attoampere", "Ampere(value * Consts.ATTO)"),
        ("Zeptoampere", "Ampere(value * Consts.ZEPTO)"),
        ("Yoctoampere", "Ampere(value * Consts.YOCTO)"),
        ("Centiampere", "Ampere(value * Consts.CENTI)"),
        ("Deciampere", "Ampere(value * Consts.DECI)"),
        ("Decaampere", "Ampere(value * Consts.DEKA)"),
        ("Hectoampere", "Ampere(value * Consts.HECTO)"),
        ("Kiloampere", "Ampere(value * Consts.KILO)"),
        ("Megaampere", "Ampere(value * Consts.MEGA)"),
        ("Gigaampere", "Ampere(value * Consts.GIGA)"),
        ("Teraampere", "Ampere(value * Consts.TERA)"),
        ("Petaampere", "Ampere(value * Consts.PETA)"),
        ("Exaampere", "Ampere(value * Consts.EXA)"),
        ("Zettaampere", "Ampere(value * Consts.ZETTA)"),
        ("Yottaampere", "Ampere(value * Consts.YOTTA)"),
    ]
    
    template = """package com.measures.current.metric

import com.measures.Consts
import com.measures.current.UnitCurrent
import com.measures.current.Ampere
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

@JvmInline
value class {unit_name}(override val value: Double) : UnitCurrent<{unit_name}> {{
    override fun asType(d: Double) = {unit_name}(d)
    override fun asBaseUnit() = {base_conversion}

    override operator fun plus(other: UnitCurrent<*>) = UnitCurrent.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitCurrent<*>) = UnitCurrent.Companion.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitCurrent.Companion.timesUnit(this, other)
}}

fun UnitCurrent<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""
    
    for unit_name, base_conversion in units:
        file_path = base_dir / f"{unit_name}.kt"
        content = template.format(unit_name=unit_name, base_conversion=base_conversion)
        file_path.write_text(content)
        print(f"Created: {file_path}")
    
    return 0
