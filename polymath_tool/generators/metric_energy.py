#!/usr/bin/env python3
"""
Metric energy unit generation.
"""

from .common import get_measures_base


def generate_metric_energy() -> int:
    """Generate metric energy units (Joule prefixes)."""
    base_dir = get_measures_base() / "energy" / "metric"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("Attojoule", "Consts.ATTO"),
        ("Decijoule", "Consts.DECI"),
        ("Dekajoule", "Consts.DEKA"),
        ("Exajoule", "Consts.EXA"),
        ("Femtojoule", "Consts.FEMTO"),
        ("Gigajoule", "Consts.GIGA"),
        ("Hectojoule", "Consts.HECTO"),
        ("Megajoule", "Consts.MEGA"),
        ("Microjoule", "Consts.MICRO"),
        ("Millijoule", "Consts.MILLI"),
        ("Nanojoule", "Consts.NANO"),
        ("Petajoule", "Consts.PETA"),
        ("Picojoule", "Consts.PICO"),
        ("Terajoule", "Consts.TERA"),
        ("Yoctojoule", "Consts.YOCTO"),
        ("Yottajoule", "Consts.YOTTA"),
        ("Zeptojoule", "Consts.ZEPTO"),
        ("Zettajoule", "Consts.ZETTA")
    ]
    
    template = """package com.measures.energy.metric

import com.measures.Consts
import com.measures.energy.Joule
import com.measures.energy.UnitEnergy
import com.measures.charge.UnitCharge
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

@JvmInline
value class {unit_name}(override val value: Double) : UnitEnergy<{unit_name}> {{
    override fun asType(d: Double) = {unit_name}(d)
    override fun asBaseUnit() = Joule(value * {const_name})

    override operator fun plus(other: UnitEnergy<*>) = UnitEnergy.plusUnit(this, other)
    override operator fun minus(other: UnitEnergy<*>) = UnitEnergy.minusUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitEnergy.divUnit(this, other)
    override operator fun div(other: UnitCharge<*>) = UnitEnergy.divUnit(this, other)
}}

fun UnitEnergy<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""
    
    for unit_name, const_name in units:
        file_path = base_dir / f"{unit_name}.kt"
        content = template.format(unit_name=unit_name, const_name=const_name)
        file_path.write_text(content)
        print(f"Created: {file_path}")
    
    return 0
