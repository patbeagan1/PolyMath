#!/usr/bin/env python3
"""
Metric velocity unit generation.
"""

from .common import get_measures_base


def generate_metric_velocity() -> int:
    """Generate metric velocity units (m/s prefixes)."""
    base_dir = get_measures_base() / "velocity" / "metric"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("AttometerPerSecond", "Consts.ATTO"),
        ("DecimeterPerSecond", "Consts.DECI"),
        ("DekameterPerSecond", "Consts.DEKA"),
        ("ExameterPerSecond", "Consts.EXA"),
        ("FemtometerPerSecond", "Consts.FEMTO"),
        ("GigameterPerSecond", "Consts.GIGA"),
        ("HectometerPerSecond", "Consts.HECTO"),
        ("MegameterPerSecond", "Consts.MEGA"),
        ("MicrometerPerSecond", "Consts.MICRO"),
        ("MillimeterPerSecond", "Consts.MILLI"),
        ("NanometerPerSecond", "Consts.NANO"),
        ("PetameterPerSecond", "Consts.PETA"),
        ("PicometerPerSecond", "Consts.PICO"),
        ("TerameterPerSecond", "Consts.TERA"),
        ("YoctometerPerSecond", "Consts.YOCTO"),
        ("YottameterPerSecond", "Consts.YOTTA"),
        ("ZeptometerPerSecond", "Consts.ZEPTO"),
        ("ZettameterPerSecond", "Consts.ZETTA")
    ]
    
    template = """package com.measures.velocity.metric

import com.measures.Consts
import com.measures.velocity.MetersPerSecond
import com.measures.velocity.UnitVelocity
import com.measures.time.UnitTime
import com.measures.distance.UnitDistance
import kotlin.jvm.JvmInline

@JvmInline
value class {unit_name}(override val value: Double) : UnitVelocity<{unit_name}> {{
    override fun asType(d: Double) = {unit_name}(d)
    override fun asBaseUnit() = MetersPerSecond(value * {const_name})

    override operator fun plus(other: UnitVelocity<*>) = UnitVelocity.plusUnit(this, other)
    override operator fun minus(other: UnitVelocity<*>) = UnitVelocity.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitVelocity.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitVelocity.divUnit(this, other)
}}

fun UnitVelocity<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""
    
    for unit_name, const_name in units:
        file_path = base_dir / f"{unit_name}.kt"
        content = template.format(unit_name=unit_name, const_name=const_name)
        file_path.write_text(content)
        print(f"Created: {file_path}")
    
    return 0
