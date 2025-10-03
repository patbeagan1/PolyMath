#!/usr/bin/env python3
"""
Metric area unit generation.
"""

from .common import get_measures_base


def generate_metric_area() -> int:
    """Generate metric area units (m² prefixes)."""
    base_dir = get_measures_base() / "area" / "metric"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("AttometerSquared", "Consts.ATTO"),
        ("DecimeterSquared", "Consts.DECI"),
        ("DekameterSquared", "Consts.DEKA"),
        ("ExameterSquared", "Consts.EXA"),
        ("FemtometerSquared", "Consts.FEMTO"),
        ("GigameterSquared", "Consts.GIGA"),
        ("HectometerSquared", "Consts.HECTO"),
        ("MegameterSquared", "Consts.MEGA"),
        ("MicrometerSquared", "Consts.MICRO"),
        ("MillimeterSquared", "Consts.MILLI"),
        ("NanometerSquared", "Consts.NANO"),
        ("PetameterSquared", "Consts.PETA"),
        ("PicometerSquared", "Consts.PICO"),
        ("TerameterSquared", "Consts.TERA"),
        ("YoctometerSquared", "Consts.YOCTO"),
        ("YottameterSquared", "Consts.YOTTA"),
        ("ZeptometerSquared", "Consts.ZEPTO"),
        ("ZettameterSquared", "Consts.ZETTA")
    ]
    
    template = """package com.measures.area.metric

import com.measures.Consts
import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.distance.Meter
import com.measures.distance.UnitDistance
import com.measures.volume.Liter
import kotlin.jvm.JvmInline

@JvmInline
value class {unit_name}(override val value: Double) : UnitArea<{unit_name}> {{
    override fun asType(d: Double) = {unit_name}(d)
    override fun asBaseUnit() = SquareMeter(value * {const_name})

    override fun plus(other: UnitArea<*>): SquareMeter = UnitArea.plusUnit(this, other)
    override fun minus(other: UnitArea<*>): SquareMeter = UnitArea.minusUnit(this, other)
    override fun times(other: UnitDistance<*>): Liter = UnitArea.timesUnit(this, other)
    override fun div(other: UnitDistance<*>): Meter = UnitArea.divUnit(this, other)
}}

fun UnitArea<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""
    
    for unit_name, const_name in units:
        file_path = base_dir / f"{unit_name}.kt"
        content = template.format(unit_name=unit_name, const_name=const_name)
        file_path.write_text(content)
        print(f"Created: {file_path}")
    
    return 0
