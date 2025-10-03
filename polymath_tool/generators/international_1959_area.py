#!/usr/bin/env python3
"""
International area unit generation.
"""

from .common import get_measures_base


def generate_international_square_foot() -> int:
    """Generate International square foot and related area units."""
    base_dir = get_measures_base() / "area" / "international"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("InternationalSquareFoot", "SquareMeter(value * 0.09290304)"),
        ("InternationalSquareYard", "InternationalSquareFoot(value * 9).asBaseUnit()"),
        ("InternationalSquareMile", "InternationalSquareFoot(value * 27878400).asBaseUnit()"),
        ("InternationalAcre", "InternationalSquareFoot(value * 43560).asBaseUnit()"),
    ]
    
    template = """package com.measures.area.international

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
