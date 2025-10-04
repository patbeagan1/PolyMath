#!/usr/bin/env python3
"""
UK Imperial unit generation (1824 agreement).
Based on the 1824 British Imperial system.
"""

from .base_distance_generator import BaseDistanceGenerator


class UKImperial1824DistanceGenerator(BaseDistanceGenerator):
    """Generator for UK Imperial distance units (1824 agreement)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="uk_imperial_1824",
            package_name="com.measures.distance.uk_imp"
        )
    
    def _get_units(self):
        """Get UK Imperial distance units (1824 agreement)."""
        return [
            ("ImperialFoot", "Meter(value * 0.3048)"),  # Same as international
            ("ImperialInch", "ImperialFoot(value / 12).asBaseUnit()"),
            ("ImperialYard", "ImperialFoot(value * 3).asBaseUnit()"),
            ("ImperialMile", "ImperialFoot(value * 5280).asBaseUnit()"),
        ]


def generate_uk_imperial_1824_distance() -> int:
    """Generate UK Imperial distance units (1824 agreement)."""
    generator = UKImperial1824DistanceGenerator()
    return generator.generate()


def generate_uk_imperial_1824_volume() -> int:
    """Generate UK Imperial volume units (1824 agreement)."""
    base_dir = get_measures_base() / "volume" / "uk_imp"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("ImperialFluidOunce", "Liter(value * 0.0284130625)"),  # 1/160 imperial gallon
        ("ImperialGill", "ImperialFluidOunce(value * 5).asBaseUnit()"),
        ("ImperialPint", "ImperialFluidOunce(value * 20).asBaseUnit()"),
        ("ImperialQuart", "ImperialPint(value * 2).asBaseUnit()"),
        ("ImperialGallon", "ImperialQuart(value * 4).asBaseUnit()"),
    ]
    
    template = """package com.measures.volume.uk_imp

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

class EnglishImperialDistanceGenerator(BaseDistanceGenerator):
    """Generator for English Imperial distance units (1824 agreement)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="uk_imp",
            package_name="com.measures.distance.uk_imp"
        )
    
    def _get_units(self):
        """Get English Imperial distance units (1824 agreement)."""
        return [
            ("ImperialFoot", "Meter(value * 0.3048)"),
            ("ImperialInch", "ImperialFoot(value / 12).asBaseUnit()"),
            ("ImperialYard", "ImperialFoot(value * 3).asBaseUnit()"),
            ("ImperialMile", "ImperialFoot(value * 5280).asBaseUnit()"),
        ]


def generate_english_imperial() -> int:
    """Generate English Imperial units (1824 agreement)."""
    generator = EnglishImperialDistanceGenerator()
    return generator.generate()


def generate_english_international_volume() -> int:
    """Generate English International volume units (1824 agreement)."""
    base_dir = get_measures_base() / "volume" / "uk_imp"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("InternationalGallon", "Liter(value * 4.54609)"),
        ("InternationalQuart", "InternationalGallon(value / 4).asBaseUnit()"),
        ("InternationalPint", "InternationalQuart(value / 2).asBaseUnit()"),
        ("InternationalFluidOunce", "InternationalPint(value / 20).asBaseUnit()"),
    ]
    
    template = """package com.measures.volume.uk_imp

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


def generate_english_imperial_volume() -> int:
    """Generate English Imperial volume units (1824 agreement)."""
    base_dir = get_measures_base() / "volume" / "uk_imp"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("ImperialGallon", "Liter(value * 4.54609)"),
        ("ImperialQuart", "ImperialGallon(value / 4).asBaseUnit()"),
        ("ImperialPint", "ImperialQuart(value / 2).asBaseUnit()"),
        ("ImperialFluidOunce", "ImperialPint(value / 20).asBaseUnit()"),
    ]
    
    template = """package com.measures.volume.uk_imp

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
