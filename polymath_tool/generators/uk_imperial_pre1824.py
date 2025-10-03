#!/usr/bin/env python3
"""
UK Imperial unit generation (pre-1824).
Based on the pre-1824 British Imperial system.
"""

from .common import get_measures_base


def generate_uk_imperial_pre1824_distance() -> int:
    """Generate UK Imperial distance units (pre-1824)."""
    base_dir = get_measures_base() / "distance" / "uk_imperial_pre1824"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("ImperialFootPre1824", "Meter(value * 0.3048)"),  # Same as international
        ("ImperialInchPre1824", "ImperialFootPre1824(value / 12).asBaseUnit()"),
        ("ImperialYardPre1824", "ImperialFootPre1824(value * 3).asBaseUnit()"),
        ("ImperialMilePre1824", "ImperialFootPre1824(value * 5280).asBaseUnit()"),
    ]
    
    template = """package com.measures.distance.uk_imperial_pre1824

import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import com.measures.distance.Meter
import com.measures.time.UnitTime
import com.measures.velocity.MetersPerSecond
import kotlin.jvm.JvmInline

@JvmInline
value class {unit_name}(override val value: Double) : UnitDistance<{unit_name}> {{
    override fun asType(d: Double) = {unit_name}(d)
    override fun asBaseUnit() = {base_conversion}

    override operator fun plus(other: UnitDistance<*>) = UnitDistance.plusUnit(this, other)
    override operator fun minus(other: UnitDistance<*>) = UnitDistance.minusUnit(this, other)
    override operator fun times(other: UnitDistance<*>) = UnitDistance.timesUnit(this, other)
    override operator fun times(other: UnitArea<*>) = UnitDistance.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>): MetersPerSecond = UnitDistance.divUnit(this, other)
}}

fun UnitDistance<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""
    
    for unit_name, base_conversion in units:
        file_path = base_dir / f"{unit_name}.kt"
        content = template.format(unit_name=unit_name, base_conversion=base_conversion)
        file_path.write_text(content)
        print(f"Created: {file_path}")
    
    return 0


def generate_uk_imperial_pre1824_volume() -> int:
    """Generate UK Imperial volume units (pre-1824)."""
    base_dir = get_measures_base() / "volume" / "uk_imperial_pre1824"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("ImperialFluidOuncePre1824", "Liter(value * 0.0284130625)"),  # 1/160 imperial gallon
        ("ImperialGillPre1824", "ImperialFluidOuncePre1824(value * 5).asBaseUnit()"),
        ("ImperialPintPre1824", "ImperialFluidOuncePre1824(value * 20).asBaseUnit()"),
        ("ImperialQuartPre1824", "ImperialPintPre1824(value * 2).asBaseUnit()"),
        ("ImperialGallonPre1824", "ImperialQuartPre1824(value * 4).asBaseUnit()"),
    ]
    
    template = """package com.measures.volume.uk_imperial_pre1824

import com.measures.area.UnitArea
import com.measures.area.SquareMeter
import com.measures.distance.UnitDistance
import com.measures.volume.Liter
import com.measures.volume.UnitVolume
import kotlin.jvm.JvmInline

@JvmInline
value class {unit_name}(override val value: Double) : UnitVolume<{unit_name}> {{
    override fun asType(d: Double) = {unit_name}(d)
    override fun asBaseUnit() = {base_conversion}

    override operator fun plus(other: UnitVolume<*>) = UnitVolume.plusUnit(this, other)
    override operator fun minus(other: UnitVolume<*>) = UnitVolume.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitVolume.divUnit(this, other)
    override operator fun div(other: UnitDistance<*>): SquareMeter = UnitVolume.divUnit(this, other)
}}

fun UnitVolume<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""
    
    for unit_name, base_conversion in units:
        file_path = base_dir / f"{unit_name}.kt"
        content = template.format(unit_name=unit_name, base_conversion=base_conversion)
        file_path.write_text(content)
        print(f"Created: {file_path}")
    
    return 0

