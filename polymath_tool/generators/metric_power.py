#!/usr/bin/env python3
"""
Metric power unit generation.
"""

from .common import get_measures_base


def generate_metric_power() -> int:
    """Generate metric power units (Watt prefixes)."""
    base_dir = get_measures_base() / "power" / "metric"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("Attowatt", "Consts.ATTO"),
        ("Deciwatt", "Consts.DECI"),
        ("Dekawatt", "Consts.DEKA"),
        ("Exawatt", "Consts.EXA"),
        ("Femtowatt", "Consts.FEMTO"),
        ("Gigawatt", "Consts.GIGA"),
        ("Hectowatt", "Consts.HECTO"),
        ("Megawatt", "Consts.MEGA"),
        ("Microwatt", "Consts.MICRO"),
        ("Milliwatt", "Consts.MILLI"),
        ("Nanowatt", "Consts.NANO"),
        ("Petawatt", "Consts.PETA"),
        ("Picowatt", "Consts.PICO"),
        ("Terawatt", "Consts.TERA"),
        ("Yoctowatt", "Consts.YOCTO"),
        ("Yottawatt", "Consts.YOTTA"),
        ("Zeptowatt", "Consts.ZEPTO"),
        ("Zettawatt", "Consts.ZETTA")
    ]
    
    template = """package com.measures.power.metric

import com.measures.Consts
import com.measures.power.Watt
import com.measures.power.UnitPower
import com.measures.time.UnitTime
import com.measures.energy.UnitEnergy
import kotlin.jvm.JvmInline

@JvmInline
value class {unit_name}(override val value: Double) : UnitPower<{unit_name}> {{
    override fun asType(d: Double) = {unit_name}(d)
    override fun asBaseUnit() = Watt(value * {const_name})

    override operator fun plus(other: UnitPower<*>) = UnitPower.plusUnit(this, other)
    override operator fun minus(other: UnitPower<*>) = UnitPower.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitPower.timesUnit(this, other)
}}

fun UnitPower<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""
    
    for unit_name, const_name in units:
        file_path = base_dir / f"{unit_name}.kt"
        content = template.format(unit_name=unit_name, const_name=const_name)
        file_path.write_text(content)
        print(f"Created: {file_path}")
    
    return 0
