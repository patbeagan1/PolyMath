#!/usr/bin/env python3
"""
American Customary unit generation.
"""

from .common import get_measures_base


def generate_american_customary_distance() -> int:
    """Generate American Customary distance units."""
    base_dir = get_measures_base() / "distance" / "american_customary"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("Foot", "Meter(value * 0.3048)"),
        ("Inch", "Foot(value / 12).asBaseUnit()"),
        ("Yard", "Foot(value * 3).asBaseUnit()"),
        ("Mile", "Foot(value * 5280).asBaseUnit()"),
    ]
    
    template = """package com.measures.distance.american_customary

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


def generate_american_customary_area() -> int:
    """Generate American Customary area units."""
    base_dir = get_measures_base() / "area" / "american_customary"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("Acre", "SquareFoot(value * 43560).asBaseUnit()"),
        ("SquareFoot", "SquareMeter(value * 0.09290304)"),
    ]
    
    template = """package com.measures.area.american_customary

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


def generate_american_customary_fluid() -> int:
    """Generate American Customary fluid volume units."""
    base_dir = get_measures_base() / "volume" / "american_customary_fluid"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("USFluidGallon", "Liter(value * 3.785411784)"),
        ("USFluidQuart", "USFluidGallon(value / 4).asBaseUnit()"),
        ("USFluidPint", "USFluidQuart(value / 2).asBaseUnit()"),
        ("USCup", "USFluidPint(value / 2).asBaseUnit()"),
        ("USFluidOunce", "USCup(value / 8).asBaseUnit()"),
    ]
    
    template = """package com.measures.volume.american_customary_fluid

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


def generate_american_customary_dry() -> int:
    """Generate American Customary dry volume units."""
    base_dir = get_measures_base() / "volume" / "american_customary_dry"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("USDryGallon", "Liter(value * 4.40488377086)"),
        ("USDryQuart", "USDryGallon(value / 4).asBaseUnit()"),
        ("USDryPint", "USDryQuart(value / 2).asBaseUnit()"),
        ("USDryBarrel", "USDryGallon(value * 26.25).asBaseUnit()"),
    ]
    
    template = """package com.measures.volume.american_customary_dry

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
