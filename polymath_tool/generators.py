#!/usr/bin/env python3
"""
Consolidated unit generation logic for PolyMath.
All generation families are defined here with their templates and unit data.
"""

from pathlib import Path


def get_repo_root() -> Path:
    """Get the PolyMath repository root dynamically."""
    # This file is in polymath_tool/, so parent is PolyMath/
    return Path(__file__).parent.parent.resolve()


def get_measures_base() -> Path:
    """Get the measures base directory."""
    return get_repo_root() / "units-common" / "src" / "commonMain" / "kotlin" / "com" / "measures"


# ==================== METRIC DISTANCE ====================

def generate_metric_distance() -> int:
    """Generate metric distance units (meter prefixes)."""
    base_dir = get_measures_base() / "distance" / "metric"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("Attometer", "Consts.ATTO"),
        ("Decimeter", "Consts.DECI"),
        ("Dekameter", "Consts.DEKA"),
        ("Exameter", "Consts.EXA"),
        ("Femtometer", "Consts.FEMTO"),
        ("Gigameter", "Consts.GIGA"),
        ("Hectometer", "Consts.HECTO"),
        ("Megameter", "Consts.MEGA"),
        ("Micrometer", "Consts.MICRO"),
        ("Nanometer", "Consts.NANO"),
        ("Petameter", "Consts.PETA"),
        ("Picometer", "Consts.PICO"),
        ("Terameter", "Consts.TERA"),
        ("Yoctometer", "Consts.YOCTO"),
        ("Yottameter", "Consts.YOTTA"),
        ("Zeptometer", "Consts.ZEPTO"),
        ("Zettameter", "Consts.ZETTA")
    ]
    
    template = """package com.measures.distance.metric

import com.measures.Consts
import com.measures.area.UnitArea
import com.measures.distance.Meter
import com.measures.distance.UnitDistance
import com.measures.time.UnitTime
import com.measures.velocity.MetersPerSecond
import kotlin.jvm.JvmInline

@JvmInline
value class {unit_name}(override val value: Double) : UnitDistance<{unit_name}> {{
    override fun asType(d: Double) = {unit_name}(d)
    override fun asBaseUnit() = Meter(value * {const_name})

    override operator fun plus(other: UnitDistance<*>) = UnitDistance.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitDistance<*>) = UnitDistance.Companion.minusUnit(this, other)
    override operator fun times(other: UnitDistance<*>) = UnitDistance.Companion.timesUnit(this, other)
    override operator fun times(other: UnitArea<*>) = UnitDistance.Companion.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>): MetersPerSecond = UnitDistance.Companion.divUnit(this, other)
}}

fun UnitDistance<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""
    
    for unit_name, const_name in units:
        file_path = base_dir / f"{unit_name}.kt"
        content = template.format(unit_name=unit_name, const_name=const_name)
        file_path.write_text(content)
        print(f"Created: {file_path}")
    
    return 0


# ==================== METRIC VOLUME ====================

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

    override operator fun plus(other: UnitVolume<*>) = UnitVolume.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitVolume<*>) = UnitVolume.Companion.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitVolume.Companion.divUnit(this, other)
    override operator fun div(other: UnitDistance<*>): SquareMeter = UnitVolume.Companion.divUnit(this, other)
}}

fun UnitVolume<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""
    
    for unit_name, const_name in units:
        file_path = base_dir / f"{unit_name}.kt"
        content = template.format(unit_name=unit_name, const_name=const_name)
        file_path.write_text(content)
        print(f"Created: {file_path}")
    
    return 0


# ==================== AMERICAN CUSTOMARY ====================

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

    override operator fun plus(other: UnitDistance<*>) = UnitDistance.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitDistance<*>) = UnitDistance.Companion.minusUnit(this, other)
    override operator fun times(other: UnitDistance<*>) = UnitDistance.Companion.timesUnit(this, other)
    override operator fun times(other: UnitArea<*>) = UnitDistance.Companion.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>): MetersPerSecond = UnitDistance.Companion.divUnit(this, other)
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

    override fun plus(other: UnitArea<*>): SquareMeter = UnitArea.Companion.plusUnit(this, other)
    override fun minus(other: UnitArea<*>): SquareMeter = UnitArea.Companion.minusUnit(this, other)
    override fun times(other: UnitDistance<*>): UnitVolume<*> = UnitArea.Companion.timesUnit(this, other)
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

    override operator fun plus(other: UnitVolume<*>) = UnitVolume.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitVolume<*>) = UnitVolume.Companion.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitVolume.Companion.divUnit(this, other)
    override operator fun div(other: UnitDistance<*>): SquareMeter = UnitVolume.Companion.divUnit(this, other)
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

    override operator fun plus(other: UnitVolume<*>) = UnitVolume.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitVolume<*>) = UnitVolume.Companion.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitVolume.Companion.divUnit(this, other)
    override operator fun div(other: UnitDistance<*>): SquareMeter = UnitVolume.Companion.divUnit(this, other)
}}

fun UnitVolume<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""
    
    for unit_name, base_conversion in units:
        file_path = base_dir / f"{unit_name}.kt"
        content = template.format(unit_name=unit_name, base_conversion=base_conversion)
        file_path.write_text(content)
        print(f"Created: {file_path}")
    
    return 0


# ==================== ENGLISH IMPERIAL ====================

def generate_english_imperial() -> int:
    """Generate English Imperial units."""
    base_dir = get_measures_base() / "distance" / "english_imperial"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("ImperialFoot", "Meter(value * 0.3048)"),
        ("ImperialInch", "ImperialFoot(value / 12).asBaseUnit()"),
        ("ImperialYard", "ImperialFoot(value * 3).asBaseUnit()"),
        ("ImperialMile", "ImperialFoot(value * 5280).asBaseUnit()"),
    ]
    
    template = """package com.measures.distance.english_imperial

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

    override operator fun plus(other: UnitDistance<*>) = UnitDistance.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitDistance<*>) = UnitDistance.Companion.minusUnit(this, other)
    override operator fun times(other: UnitDistance<*>) = UnitDistance.Companion.timesUnit(this, other)
    override operator fun times(other: UnitArea<*>) = UnitDistance.Companion.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>): MetersPerSecond = UnitDistance.Companion.divUnit(this, other)
}}

fun UnitDistance<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""
    
    for unit_name, base_conversion in units:
        file_path = base_dir / f"{unit_name}.kt"
        content = template.format(unit_name=unit_name, base_conversion=base_conversion)
        file_path.write_text(content)
        print(f"Created: {file_path}")
    
    return 0


def generate_english_international_volume() -> int:
    """Generate English International volume units."""
    base_dir = get_measures_base() / "volume" / "english_international"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("InternationalGallon", "Liter(value * 4.54609)"),
        ("InternationalQuart", "InternationalGallon(value / 4).asBaseUnit()"),
        ("InternationalPint", "InternationalQuart(value / 2).asBaseUnit()"),
        ("InternationalFluidOunce", "InternationalPint(value / 20).asBaseUnit()"),
    ]
    
    template = """package com.measures.volume.english_international

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

    override operator fun plus(other: UnitVolume<*>) = UnitVolume.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitVolume<*>) = UnitVolume.Companion.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitVolume.Companion.divUnit(this, other)
    override operator fun div(other: UnitDistance<*>): SquareMeter = UnitVolume.Companion.divUnit(this, other)
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
    """Generate English Imperial volume units."""
    base_dir = get_measures_base() / "volume" / "english_imperial"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("ImperialGallon", "Liter(value * 4.54609)"),
        ("ImperialQuart", "ImperialGallon(value / 4).asBaseUnit()"),
        ("ImperialPint", "ImperialQuart(value / 2).asBaseUnit()"),
        ("ImperialFluidOunce", "ImperialPint(value / 20).asBaseUnit()"),
    ]
    
    template = """package com.measures.volume.english_imperial

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

    override operator fun plus(other: UnitVolume<*>) = UnitVolume.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitVolume<*>) = UnitVolume.Companion.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitVolume.Companion.divUnit(this, other)
    override operator fun div(other: UnitDistance<*>): SquareMeter = UnitVolume.Companion.divUnit(this, other)
}}

fun UnitVolume<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""
    
    for unit_name, base_conversion in units:
        file_path = base_dir / f"{unit_name}.kt"
        content = template.format(unit_name=unit_name, base_conversion=base_conversion)
        file_path.write_text(content)
        print(f"Created: {file_path}")
    
    return 0


# ==================== WEIGHT SYSTEMS ====================

def generate_avoirdupois() -> int:
    """Generate Avoirdupois weight units."""
    base_dir = get_measures_base() / "weight" / "avoirdupois"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("Dram", "Pound(value / 256).asBaseUnit()"),
        ("Grain", "Pound(value / 7000).asBaseUnit()"),
        ("LongHundredWeight", "Pound(value * 112).asBaseUnit()"),
        ("LongTon", "Pound(value * 2240).asBaseUnit()"),
        ("ShortQuarter", "Pound(value * 25).asBaseUnit()"),
        ("LongQuarter", "Pound(value * 28).asBaseUnit()"),
        ("ShortHundredWeight", "Pound(value * 100).asBaseUnit()"),
        ("Stone", "Pound(value * 14).asBaseUnit()"),
        ("ShortTon", "Pound(value * 2000).asBaseUnit()")
    ]
    
    template = """package com.measures.weight.avoirdupois

import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class {unit_name}(override val value: Double) : UnitMass<{unit_name}> {{
    override fun asType(d: Double) = {unit_name}(d)
    override fun asBaseUnit() = {base_conversion}

    override fun plus(other: UnitMass<*>): KiloGram = UnitMass.Companion.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): KiloGram = UnitMass.Companion.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.Companion.timesUnit(this, other)
}}

fun UnitMass<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""
    
    for unit_name, base_conversion in units:
        file_path = base_dir / f"{unit_name}.kt"
        content = template.format(unit_name=unit_name, base_conversion=base_conversion)
        file_path.write_text(content)
        print(f"Created: {file_path}")
    
    return 0


def generate_troy() -> int:
    """Generate Troy weight units."""
    base_dir = get_measures_base() / "weight" / "troy"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("TroyGrain", "TroyPennyweight(value / 24).asBaseUnit()"),
        ("TroyPennyweight", "TroyOunce(value / 20).asBaseUnit()"),
        ("TroyOunce", "Gram(value * 31.1034768)"),
        ("TroyPound", "TroyOunce(value * 12).asBaseUnit()")
    ]
    
    template = """package com.measures.weight.troy

import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import com.measures.weight.Gram
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class {unit_name}(override val value: Double) : UnitMass<{unit_name}> {{
    override fun asType(d: Double) = {unit_name}(d)
    override fun asBaseUnit() = {base_conversion}

    override fun plus(other: UnitMass<*>): KiloGram = UnitMass.Companion.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): KiloGram = UnitMass.Companion.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.Companion.timesUnit(this, other)
}}

fun UnitMass<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""
    
    for unit_name, base_conversion in units:
        file_path = base_dir / f"{unit_name}.kt"
        content = template.format(unit_name=unit_name, base_conversion=base_conversion)
        file_path.write_text(content)
        print(f"Created: {file_path}")
    
    return 0


# ==================== ACCELERATION ====================

def generate_acceleration() -> int:
    """Generate acceleration units."""
    base_dir = get_measures_base() / "acceleration" / "non_si"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("FeetPerSecondPerSecond", "MetersPerSecondPerSecond(this.value * 0.3048)"),
        ("Gal", "MetersPerSecondPerSecond(this.value * 0.01)"),
        ("CentimetersPerSecondSquared", "MetersPerSecondPerSecond(this.value * 0.01)"),
        ("KilometersPerSecondPerSecond", "MetersPerSecondPerSecond(this.value * 1000.0)")
    ]
    
    template = """package com.measures.acceleration.non_si

import com.measures.acceleration.UnitAcceleration
import com.measures.acceleration.MetersPerSecondPerSecond
import com.measures.force.Newton
import com.measures.time.UnitTime
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class {unit_name}(override val value: Double) : UnitAcceleration<{unit_name}> {{
    override fun asType(d: Double) = {unit_name}(d)
    override fun asBaseUnit() = {base_conversion}

    override operator fun plus(other: UnitAcceleration<*>) = UnitAcceleration.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitAcceleration<*>) = UnitAcceleration.Companion.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitAcceleration.Companion.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitAcceleration.Companion.divUnit(this, other)
    override operator fun times(other: UnitMass<*>): Newton = Newton(this.asBaseUnit().value * other.asBaseUnit().value * 100)
}}

fun UnitAcceleration<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""
    
    for unit_name, base_conversion in units:
        file_path = base_dir / f"{unit_name}.kt"
        content = template.format(unit_name=unit_name, base_conversion=base_conversion)
        file_path.write_text(content)
        print(f"Created: {file_path}")
    
    return 0


# ==================== NON-SI & REMAINING ====================

def generate_non_si() -> int:
    """Generate Non-SI units (placeholder - would need full implementation)."""
    print("[INFO] Non-SI unit generation (stub)")
    return 0


def generate_remaining() -> int:
    """Generate remaining/misc units (placeholder - would need full implementation)."""
    print("[INFO] Remaining unit generation (stub)")
    return 0

