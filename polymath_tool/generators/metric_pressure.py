#!/usr/bin/env python3
"""
Metric pressure unit generation.
"""

from .common import get_measures_base


def generate_metric_pressure() -> int:
    """Generate metric pressure units (Pascal prefixes)."""
    base_dir = get_measures_base() / "pressure" / "metric"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("Attopascal", "Consts.ATTO"),
        ("Decipascal", "Consts.DECI"),
        ("Dekapascal", "Consts.DEKA"),
        ("Exapascal", "Consts.EXA"),
        ("Femtopascal", "Consts.FEMTO"),
        ("Gigapascal", "Consts.GIGA"),
        ("Hectopascal", "Consts.HECTO"),
        ("Megapascal", "Consts.MEGA"),
        ("Micropascal", "Consts.MICRO"),
        ("Millipascal", "Consts.MILLI"),
        ("Nanopascal", "Consts.NANO"),
        ("Petapascal", "Consts.PETA"),
        ("Picopascal", "Consts.PICO"),
        ("Terapascal", "Consts.TERA"),
        ("Yoctopascal", "Consts.YOCTO"),
        ("Yottapascal", "Consts.YOTTA"),
        ("Zeptopascal", "Consts.ZEPTO"),
        ("Zettapascal", "Consts.ZETTA")
    ]
    
    template = """package com.measures.pressure.metric

import com.measures.Consts
import com.measures.pressure.Pascal
import com.measures.pressure.UnitPressure
import com.measures.area.UnitArea
import com.measures.force.UnitForce
import kotlin.jvm.JvmInline

@JvmInline
value class {unit_name}(override val value: Double) : UnitPressure<{unit_name}> {{
    override fun asType(d: Double) = {unit_name}(d)
    override fun asBaseUnit() = Pascal(value * {const_name})

    override operator fun plus(other: UnitPressure<*>) = UnitPressure.plusUnit(this, other)
    override operator fun minus(other: UnitPressure<*>) = UnitPressure.minusUnit(this, other)
    override operator fun times(other: UnitArea<*>) = UnitPressure.timesUnit(this, other)
}}

fun UnitPressure<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""
    
    for unit_name, const_name in units:
        file_path = base_dir / f"{unit_name}.kt"
        content = template.format(unit_name=unit_name, const_name=const_name)
        file_path.write_text(content)
        print(f"Created: {file_path}")
    
    return 0
