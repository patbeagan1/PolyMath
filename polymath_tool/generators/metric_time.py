#!/usr/bin/env python3
"""
Metric time unit generation.
"""

from .common import get_measures_base


def generate_metric_time() -> int:
    """Generate metric time units (Second prefixes)."""
    base_dir = get_measures_base() / "time" / "metric"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("Attosecond", "Consts.ATTO"),
        ("Decisecond", "Consts.DECI"),
        ("Dekasecond", "Consts.DEKA"),
        ("Exasecond", "Consts.EXA"),
        ("Femtosecond", "Consts.FEMTO"),
        ("Gigasecond", "Consts.GIGA"),
        ("Hectosecond", "Consts.HECTO"),
        ("Megasecond", "Consts.MEGA"),
        ("Microsecond", "Consts.MICRO"),
        ("Millisecond", "Consts.MILLI"),
        ("Nanosecond", "Consts.NANO"),
        ("Petasecond", "Consts.PETA"),
        ("Picosecond", "Consts.PICO"),
        ("Terasecond", "Consts.TERA"),
        ("Yoctosecond", "Consts.YOCTO"),
        ("Yottasecond", "Consts.YOTTA"),
        ("Zeptosecond", "Consts.ZEPTO"),
        ("Zettasecond", "Consts.ZETTA")
    ]
    
    template = """package com.measures.time.metric

import com.measures.Consts
import com.measures.time.Second
import com.measures.time.UnitTime
import com.measures.frequency.Hertz
import kotlin.jvm.JvmInline

@JvmInline
value class {unit_name}(override val value: Double) : UnitTime<{unit_name}> {{
    override fun asType(d: Double) = {unit_name}(d)
    override fun asBaseUnit() = Second(value * {const_name})

    override operator fun plus(other: UnitTime<*>) = UnitTime.plusUnit(this, other)
    override operator fun minus(other: UnitTime<*>) = UnitTime.minusUnit(this, other)
    override fun inv(): Hertz = UnitTime.invUnit(this)
}}

fun UnitTime<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""
    
    for unit_name, const_name in units:
        file_path = base_dir / f"{unit_name}.kt"
        content = template.format(unit_name=unit_name, const_name=const_name)
        file_path.write_text(content)
        print(f"Created: {file_path}")
    
    return 0
