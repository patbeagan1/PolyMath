#!/usr/bin/env python3
"""
International unit generation (1959 agreement).
Based on the 1959 International Yard and Pound Agreement.
"""

from .base_distance_generator import BaseDistanceGenerator
from .base_area_generator import BaseAreaGenerator


class USInternationalDistanceGenerator(BaseDistanceGenerator):
    """Generator for US International distance units (1959 standard)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="us_international_1959",
            package_name="com.measures.distance.us_international_1959"
        )
    
    def _get_units(self):
        """Get US International distance units (1959 standard)."""
        return [
            ("InternationalFoot", "Meter(value * 0.3048)"),  # 1959 agreement
            ("InternationalInch", "InternationalFoot(value / 12).asBaseUnit()"),
            ("InternationalYard", "InternationalFoot(value * 3).asBaseUnit()"),
            ("InternationalMile", "InternationalFoot(value * 5280).asBaseUnit()"),
        ]


def generate_us_international_distance() -> int:
    """Generate US International distance units (1959 standard)."""
    generator = USInternationalDistanceGenerator()
    return generator.generate()


class USInternationalAreaGenerator(BaseAreaGenerator):
    """Generator for US International area units (1959)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="us_international_1959",
            package_name="com.measures.area.us_international_1959"
        )
    
    def _get_units(self):
        """Get US International area units (1959)."""
        return [
            ("InternationalSquareFoot", "SquareMeter(value * 0.09290304)"),
            ("InternationalSquareYard", "InternationalSquareFoot(value * 9).asBaseUnit()"),
            ("InternationalAcre", "InternationalSquareFoot(value * 43560).asBaseUnit()"),
        ]


def generate_us_international_area() -> int:
    """Generate US International area units (1959)."""
    generator = USInternationalAreaGenerator()
    return generator.generate()


def generate_us_international_fluid_volume() -> int:
    """Generate US International fluid volume units (1959)."""
    base_dir = get_measures_base() / "volume" / "us_international_fluid_1959"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("USFluidOunce", "Liter(value * 0.0295735295625)"),  # 1/128 US gallon
        ("USCup", "USFluidOunce(value * 8).asBaseUnit()"),
        ("USPint", "USFluidOunce(value * 16).asBaseUnit()"),
        ("USQuart", "USPint(value * 2).asBaseUnit()"),
        ("USGallon", "USQuart(value * 4).asBaseUnit()"),
    ]
    
    template = """package com.measures.volume.us_international_fluid_1959

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


def generate_us_international_dry_volume() -> int:
    """Generate US International dry volume units (1959)."""
    base_dir = get_measures_base() / "volume" / "us_international_dry_1959"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("USDryPint", "Liter(value * 0.5506104713575)"),  # 33.6003125 cu in
        ("USDryQuart", "USDryPint(value * 2).asBaseUnit()"),
        ("USDryGallon", "USDryQuart(value * 4).asBaseUnit()"),
        ("USPeck", "USDryGallon(value * 2).asBaseUnit()"),
        ("USBushel", "USPeck(value * 4).asBaseUnit()"),
    ]
    
    template = """package com.measures.volume.us_international_dry_1959

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

class International1959DistanceGenerator(BaseDistanceGenerator):
    """Generator for International distance units (1959 agreement)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="international_1959",
            package_name="com.measures.distance.international_1959"
        )
    
    def _get_units(self):
        """Get International distance units (1959 agreement)."""
        return [
            ("InternationalFoot1959", "Meter(value * 0.3048)"),  # 1959 agreement
            ("InternationalInch1959", "InternationalFoot1959(value / 12).asBaseUnit()"),
            ("InternationalYard1959", "InternationalFoot1959(value * 3).asBaseUnit()"),
            ("InternationalMile1959", "InternationalFoot1959(value * 5280).asBaseUnit()"),
        ]


def generate_international_1959_distance() -> int:
    """Generate International distance units (1959 agreement)."""
    generator = International1959DistanceGenerator()
    return generator.generate()


class International1959NauticalDistanceGenerator(BaseDistanceGenerator):
    """Generator for International nautical distance units (1959 agreement)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="international_1959_nautical",
            package_name="com.measures.distance.international_1959_nautical"
        )
    
    def _get_units(self):
        """Get International nautical distance units (1959 agreement)."""
        return [
            ("Fathom", "Meter(value * 1.8288)"),  # 2 yards
            ("Cable", "Meter(value * 219.456)"),  # 120 fathoms
            ("NauticalMile", "Meter(value * 1852)"),  # 1.151 statute miles
        ]


def generate_international_1959_nautical() -> int:
    """Generate International nautical units (1959 agreement)."""
    generator = International1959NauticalDistanceGenerator()
    return generator.generate()


def generate_international_1959_volume() -> int:
    """Generate International volume units (1959 agreement)."""
    base_dir = get_measures_base() / "volume" / "international_1959"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("InternationalFluidOunce1959", "Liter(value * 0.0295735295625)"),  # US fluid ounce
        ("InternationalCup1959", "InternationalFluidOunce1959(value * 8).asBaseUnit()"),
        ("InternationalPint1959", "InternationalFluidOunce1959(value * 16).asBaseUnit()"),
        ("InternationalQuart1959", "InternationalPint1959(value * 2).asBaseUnit()"),
        ("InternationalGallon1959", "InternationalQuart1959(value * 4).asBaseUnit()"),
    ]
    
    template = """package com.measures.volume.international_1959

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
