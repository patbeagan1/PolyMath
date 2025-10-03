#!/usr/bin/env python3
"""
Acceleration unit generation.
"""

from .common import get_measures_base


def generate_acceleration() -> int:
    """Generate acceleration units."""
    base_dir = get_measures_base() / "acceleration" / "non_si"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("FeetPerSecondPerSecond", "MetersPerSecondPerSecond(this.value * 0.3048)"),
        ("Gal", "MetersPerSecondPerSecond(this.value * 0.01)"),
        ("CentimetersPerSecondSquared", "MetersPerSecondPerSecond(this.value * 0.01)"),
        ("KilometersPerSecondPerSecond", "MetersPerSecondPerSecond(this.value * 1000.0)")
    ]
    
    template = """package com.measures.acceleration.non_si

import com.measures.acceleration.UnitAcceleration
import com.measures.acceleration.MetersPerSecondPerSecond
import com.measures.force.Newton
import com.measures.time.UnitTime
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class {unit_name}(override val value: Double) : UnitAcceleration<{unit_name}> {{
    override fun asType(d: Double) = {unit_name}(d)
    override fun asBaseUnit() = {base_conversion}

    override operator fun plus(other: UnitAcceleration<*>) = UnitAcceleration.plusUnit(this, other)
    override operator fun minus(other: UnitAcceleration<*>) = UnitAcceleration.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitAcceleration.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitAcceleration.divUnit(this, other)
    override operator fun times(other: UnitMass<*>): Newton = Newton(this.asBaseUnit().value * other.asBaseUnit().value * 100)
}}

fun UnitAcceleration<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""
    
    for unit_name, base_conversion in units:
        file_path = base_dir / f"{unit_name}.kt"
        content = template.format(unit_name=unit_name, base_conversion=base_conversion)
        file_path.write_text(content)
        print(f"Created: {file_path}")
    
    return 0


def generate_metric_acceleration() -> int:
    """Generate metric acceleration units (m/s² prefixes)."""
    base_dir = get_measures_base() / "acceleration" / "metric"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("AttometerPerSecondPerSecond", "Consts.ATTO"),
        ("DecimeterPerSecondPerSecond", "Consts.DECI"),
        ("DekameterPerSecondPerSecond", "Consts.DEKA"),
        ("ExameterPerSecondPerSecond", "Consts.EXA"),
        ("FemtometerPerSecondPerSecond", "Consts.FEMTO"),
        ("GigameterPerSecondPerSecond", "Consts.GIGA"),
        ("HectometerPerSecondPerSecond", "Consts.HECTO"),
        ("MegameterPerSecondPerSecond", "Consts.MEGA"),
        ("MicrometerPerSecondPerSecond", "Consts.MICRO"),
        ("MillimeterPerSecondPerSecond", "Consts.MILLI"),
        ("NanometerPerSecondPerSecond", "Consts.NANO"),
        ("PetameterPerSecondPerSecond", "Consts.PETA"),
        ("PicometerPerSecondPerSecond", "Consts.PICO"),
        ("TerameterPerSecondPerSecond", "Consts.TERA"),
        ("YoctometerPerSecondPerSecond", "Consts.YOCTO"),
        ("YottameterPerSecondPerSecond", "Consts.YOTTA"),
        ("ZeptometerPerSecondPerSecond", "Consts.ZEPTO"),
        ("ZettameterPerSecondPerSecond", "Consts.ZETTA")
    ]
    
    template = """package com.measures.acceleration.metric

import com.measures.Consts
import com.measures.acceleration.MetersPerSecondPerSecond
import com.measures.acceleration.UnitAcceleration
import com.measures.time.UnitTime
import com.measures.weight.UnitMass
import com.measures.force.Newton
import kotlin.jvm.JvmInline

@JvmInline
value class {unit_name}(override val value: Double) : UnitAcceleration<{unit_name}> {{
    override fun asType(d: Double) = {unit_name}(d)
    override fun asBaseUnit() = MetersPerSecondPerSecond(value * {const_name})

    override operator fun plus(other: UnitAcceleration<*>) = UnitAcceleration.plusUnit(this, other)
    override operator fun minus(other: UnitAcceleration<*>) = UnitAcceleration.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitAcceleration.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitAcceleration.divUnit(this, other)
    override operator fun times(other: UnitMass<*>): Newton = UnitAcceleration.timesUnit(this, other)
}}

fun UnitAcceleration<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""
    
    for unit_name, const_name in units:
        file_path = base_dir / f"{unit_name}.kt"
        content = template.format(unit_name=unit_name, const_name=const_name)
        file_path.write_text(content)
        print(f"Created: {file_path}")
    
    return 0
