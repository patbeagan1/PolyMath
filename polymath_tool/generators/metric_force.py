#!/usr/bin/env python3
"""
Metric force unit generation.
"""

from .common import get_measures_base


def generate_metric_force() -> int:
    """Generate metric force units (Newton prefixes)."""
    base_dir = get_measures_base() / "force" / "metric"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("Attonewton", "Consts.ATTO"),
        ("Decinewton", "Consts.DECI"),
        ("Dekanewton", "Consts.DEKA"),
        ("Exanewton", "Consts.EXA"),
        ("Femtonewton", "Consts.FEMTO"),
        ("Giganewton", "Consts.GIGA"),
        ("Hectonewton", "Consts.HECTO"),
        ("Meganewton", "Consts.MEGA"),
        ("Micronewton", "Consts.MICRO"),
        ("Millinewton", "Consts.MILLI"),
        ("Nanonewton", "Consts.NANO"),
        ("Petanewton", "Consts.PETA"),
        ("Piconewton", "Consts.PICO"),
        ("Teranewton", "Consts.TERA"),
        ("Yoctonewton", "Consts.YOCTO"),
        ("Yottanewton", "Consts.YOTTA"),
        ("Zeptonewton", "Consts.ZEPTO"),
        ("Zettanewton", "Consts.ZETTA")
    ]
    
    template = """package com.measures.force.metric

import com.measures.Consts
import com.measures.force.Newton
import com.measures.force.UnitForce
import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import com.measures.weight.UnitMass
import com.measures.acceleration.UnitAcceleration
import com.measures.energy.Joule
import com.measures.pressure.Pascal
import com.measures.acceleration.MetersPerSecondPerSecond
import com.measures.weight.KiloGram
import kotlin.jvm.JvmInline

@JvmInline
value class {unit_name}(override val value: Double) : UnitForce<{unit_name}> {{
    override fun asType(d: Double) = {unit_name}(d)
    override fun asBaseUnit() = Newton(value * {const_name})

    override operator fun plus(other: UnitForce<*>) = UnitForce.plusUnit(this, other)
    override operator fun minus(other: UnitForce<*>) = UnitForce.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>): Pascal = UnitForce.divUnit(this, other)
    override operator fun times(other: UnitDistance<*>): Joule = UnitForce.timesUnit(this, other)
    override operator fun div(other: UnitMass<*>): MetersPerSecondPerSecond = UnitForce.divUnit(this, other)
    override operator fun div(other: UnitAcceleration<*>): KiloGram = UnitForce.divUnit(this, other)
}}

fun UnitForce<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""
    
    for unit_name, const_name in units:
        file_path = base_dir / f"{unit_name}.kt"
        content = template.format(unit_name=unit_name, const_name=const_name)
        file_path.write_text(content)
        print(f"Created: {file_path}")
    
    return 0
