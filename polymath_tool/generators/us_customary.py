#!/usr/bin/env python3
"""
US Customary unit generation.
"""

from .common import get_measures_base


def generate_us_customary_area() -> int:
    """Generate US Customary area units."""
    base_dir = get_measures_base() / "area" / "us_customary"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("USSquareFoot", "SquareMeter(value * 0.09290304)"),
        ("USSquareYard", "USSquareFoot(value * 9).asBaseUnit()"),
        ("USSquareMile", "USSquareFoot(value * 27878400).asBaseUnit()"),
        ("USAcre", "USSquareFoot(value * 43560).asBaseUnit()"),
    ]
    
    template = """package com.measures.area.us_customary

import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import com.measures.volume.UnitVolume
import kotlin.jvm.JvmInline

@JvmInline
value class {unit_name}(override val value: Double) : UnitArea<{unit_name}> {{
    override fun asType(d: Double) = {unit_name}(d)
    override fun asBaseUnit() = {base_conversion}

    override fun plus(other: UnitArea<*>): SquareMeter = UnitArea.plusUnit(this, other)
    override fun minus(other: UnitArea<*>): SquareMeter = UnitArea.minusUnit(this, other)
    override fun times(other: UnitDistance<*>): UnitVolume<*> = UnitArea.timesUnit(this, other)
}}

fun UnitArea<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""
    
    for unit_name, base_conversion in units:
        file_path = base_dir / f"{unit_name}.kt"
        content = template.format(unit_name=unit_name, base_conversion=base_conversion)
        file_path.write_text(content)
        print(f"Created: {file_path}")
    
    return 0
