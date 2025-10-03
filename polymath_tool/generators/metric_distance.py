#!/usr/bin/env python3
"""
Metric distance unit generation.
"""

from .common import get_measures_base


def generate_metric_distance() -> int:
    """Generate metric distance units (meter prefixes)."""
    base_dir = get_measures_base() / "distance" / "metric"
    base_dir.mkdir(parents=True, exist_ok=True)
    
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
        ("Millimeter", "Consts.MILLI"),
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
import com.measures.distance.Meter
import com.measures.distance.UnitDistance
import com.measures.time.UnitTime
import com.measures.velocity.MetersPerSecond
import kotlin.jvm.JvmInline

@JvmInline
value class {unit_name}(override val value: Double) : UnitDistance<{unit_name}> {{
    override fun asType(d: Double) = {unit_name}(d)
    override fun asBaseUnit() = Meter(value * {const_name})

    override operator fun plus(other: UnitDistance<*>) = UnitDistance.plusUnit(this, other)
    override operator fun minus(other: UnitDistance<*>) = UnitDistance.minusUnit(this, other)
    override operator fun times(other: UnitDistance<*>) = UnitDistance.timesUnit(this, other)
    override operator fun times(other: UnitArea<*>) = UnitDistance.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>): MetersPerSecond = UnitDistance.divUnit(this, other)
}}

fun UnitDistance<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""
    
    for unit_name, const_name in units:
        file_path = base_dir / f"{unit_name}.kt"
        content = template.format(unit_name=unit_name, const_name=const_name)
        file_path.write_text(content)
        print(f"Created: {file_path}")
    
    return 0
