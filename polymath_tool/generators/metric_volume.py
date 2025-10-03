#!/usr/bin/env python3
"""
Metric volume unit generation.
"""

from .common import get_measures_base


def generate_metric_volume() -> int:
    """Generate metric volume units (liter prefixes)."""
    base_dir = get_measures_base() / "volume" / "metric"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("Attoliter", "Consts.ATTO"),
        ("Centiliter", "Consts.CENTI"),
        ("CubicCentimeter", "Consts.MILLI"),
        ("CubicMeter", "Consts.KILO"),
        ("Deciliter", "Consts.DECI"),
        ("Dekaliter", "Consts.DEKA"),
        ("Exaliter", "Consts.EXA"),
        ("Femtoliter", "Consts.FEMTO"),
        ("Gigaliter", "Consts.GIGA"),
        ("Hectoliter", "Consts.HECTO"),
        ("Kiloliter", "Consts.KILO"),
        ("Megaliter", "Consts.MEGA"),
        ("Microliter", "Consts.MICRO"),
        ("Milliliter", "Consts.MILLI"),
        ("Nanoliter", "Consts.NANO"),
        ("Petaliter", "Consts.PETA"),
        ("Picoliter", "Consts.PICO"),
        ("Teraliter", "Consts.TERA"),
        ("Yoctoliter", "Consts.YOCTO"),
        ("Yottaliter", "Consts.YOTTA"),
        ("Zeptoliter", "Consts.ZEPTO"),
        ("Zettaliter", "Consts.ZETTA")
    ]
    
    template = """package com.measures.volume.metric

import com.measures.Consts
import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import com.measures.volume.Liter
import com.measures.volume.UnitVolume
import kotlin.jvm.JvmInline

@JvmInline
value class {unit_name}(override val value: Double) : UnitVolume<{unit_name}> {{
    override fun asType(d: Double) = {unit_name}(d)
    override fun asBaseUnit() = Liter(value * {const_name})

    override operator fun plus(other: UnitVolume<*>) = UnitVolume.plusUnit(this, other)
    override operator fun minus(other: UnitVolume<*>) = UnitVolume.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitVolume.divUnit(this, other)
    override operator fun div(other: UnitDistance<*>): SquareMeter = UnitVolume.divUnit(this, other)
}}

fun UnitVolume<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""
    
    for unit_name, const_name in units:
        file_path = base_dir / f"{unit_name}.kt"
        content = template.format(unit_name=unit_name, const_name=const_name)
        file_path.write_text(content)
        print(f"Created: {file_path}")
    
    return 0
