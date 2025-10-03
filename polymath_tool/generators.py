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
        ("Millimeter", "Consts.MILLI"),
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

    override operator fun plus(other: UnitDistance<*>) = UnitDistance.plusUnit(this, other)
    override operator fun minus(other: UnitDistance<*>) = UnitDistance.minusUnit(this, other)
    override operator fun times(other: UnitDistance<*>) = UnitDistance.timesUnit(this, other)
    override operator fun times(other: UnitArea<*>) = UnitDistance.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>): MetersPerSecond = UnitDistance.divUnit(this, other)
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

    override fun plus(other: UnitMass<*>): KiloGram = UnitMass.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): KiloGram = UnitMass.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.timesUnit(this, other)
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

    override fun plus(other: UnitMass<*>): KiloGram = UnitMass.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): KiloGram = UnitMass.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.timesUnit(this, other)
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

    override operator fun plus(other: UnitAcceleration<*>) = UnitAcceleration.plusUnit(this, other)
    override operator fun minus(other: UnitAcceleration<*>) = UnitAcceleration.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitAcceleration.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitAcceleration.divUnit(this, other)
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


# ==================== METRIC ACCELERATION ====================

def generate_metric_acceleration() -> int:
    """Generate metric acceleration units (m/s² prefixes)."""
    base_dir = get_measures_base() / "acceleration" / "metric"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("AttometerPerSecondPerSecond", "Consts.ATTO"),
        ("DecimeterPerSecondPerSecond", "Consts.DECI"),
        ("DekameterPerSecondPerSecond", "Consts.DEKA"),
        ("ExameterPerSecondPerSecond", "Consts.EXA"),
        ("FemtometerPerSecondPerSecond", "Consts.FEMTO"),
        ("GigameterPerSecondPerSecond", "Consts.GIGA"),
        ("HectometerPerSecondPerSecond", "Consts.HECTO"),
        ("MegameterPerSecondPerSecond", "Consts.MEGA"),
        ("MicrometerPerSecondPerSecond", "Consts.MICRO"),
        ("MillimeterPerSecondPerSecond", "Consts.MILLI"),
        ("NanometerPerSecondPerSecond", "Consts.NANO"),
        ("PetameterPerSecondPerSecond", "Consts.PETA"),
        ("PicometerPerSecondPerSecond", "Consts.PICO"),
        ("TerameterPerSecondPerSecond", "Consts.TERA"),
        ("YoctometerPerSecondPerSecond", "Consts.YOCTO"),
        ("YottameterPerSecondPerSecond", "Consts.YOTTA"),
        ("ZeptometerPerSecondPerSecond", "Consts.ZEPTO"),
        ("ZettameterPerSecondPerSecond", "Consts.ZETTA")
    ]
    
    template = """package com.measures.acceleration.metric

import com.measures.Consts
import com.measures.acceleration.MetersPerSecondPerSecond
import com.measures.acceleration.UnitAcceleration
import com.measures.time.UnitTime
import com.measures.weight.UnitMass
import com.measures.force.Newton
import kotlin.jvm.JvmInline

@JvmInline
value class {unit_name}(override val value: Double) : UnitAcceleration<{unit_name}> {{
    override fun asType(d: Double) = {unit_name}(d)
    override fun asBaseUnit() = MetersPerSecondPerSecond(value * {const_name})

    override operator fun plus(other: UnitAcceleration<*>) = UnitAcceleration.plusUnit(this, other)
    override operator fun minus(other: UnitAcceleration<*>) = UnitAcceleration.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitAcceleration.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitAcceleration.divUnit(this, other)
    override operator fun times(other: UnitMass<*>): Newton = UnitAcceleration.timesUnit(this, other)
}}

fun UnitAcceleration<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""
    
    for unit_name, const_name in units:
        file_path = base_dir / f"{unit_name}.kt"
        content = template.format(unit_name=unit_name, const_name=const_name)
        file_path.write_text(content)
        print(f"Created: {file_path}")
    
    return 0


# ==================== METRIC AREA ====================

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


# ==================== METRIC ENERGY ====================

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


# ==================== METRIC FORCE ====================

def generate_metric_force() -> int:
    """Generate metric force units (Newton prefixes)."""
    base_dir = get_measures_base() / "force" / "metric"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("Attonewton", "Consts.ATTO"),
        ("Decinewton", "Consts.DECI"),
        ("Dekanewton", "Consts.DEKA"),
        ("Exanewton", "Consts.EXA"),
        ("Femtonewton", "Consts.FEMTO"),
        ("Giganewton", "Consts.GIGA"),
        ("Hectonewton", "Consts.HECTO"),
        ("Meganewton", "Consts.MEGA"),
        ("Micronewton", "Consts.MICRO"),
        ("Millinewton", "Consts.MILLI"),
        ("Nanonewton", "Consts.NANO"),
        ("Petanewton", "Consts.PETA"),
        ("Piconewton", "Consts.PICO"),
        ("Teranewton", "Consts.TERA"),
        ("Yoctonewton", "Consts.YOCTO"),
        ("Yottanewton", "Consts.YOTTA"),
        ("Zeptonewton", "Consts.ZEPTO"),
        ("Zettanewton", "Consts.ZETTA")
    ]
    
    template = """package com.measures.force.metric

import com.measures.Consts
import com.measures.force.Newton
import com.measures.force.UnitForce
import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import com.measures.weight.UnitMass
import com.measures.acceleration.UnitAcceleration
import com.measures.energy.Joule
import com.measures.pressure.Pascal
import com.measures.acceleration.MetersPerSecondPerSecond
import com.measures.weight.KiloGram
import kotlin.jvm.JvmInline

@JvmInline
value class {unit_name}(override val value: Double) : UnitForce<{unit_name}> {{
    override fun asType(d: Double) = {unit_name}(d)
    override fun asBaseUnit() = Newton(value * {const_name})

    override operator fun plus(other: UnitForce<*>) = UnitForce.plusUnit(this, other)
    override operator fun minus(other: UnitForce<*>) = UnitForce.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>): Pascal = UnitForce.divUnit(this, other)
    override operator fun times(other: UnitDistance<*>): Joule = UnitForce.timesUnit(this, other)
    override operator fun div(other: UnitMass<*>): MetersPerSecondPerSecond = UnitForce.divUnit(this, other)
    override operator fun div(other: UnitAcceleration<*>): KiloGram = UnitForce.divUnit(this, other)
}}

fun UnitForce<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""
    
    for unit_name, const_name in units:
        file_path = base_dir / f"{unit_name}.kt"
        content = template.format(unit_name=unit_name, const_name=const_name)
        file_path.write_text(content)
        print(f"Created: {file_path}")
    
    return 0


# ==================== METRIC POWER ====================

def generate_metric_power() -> int:
    """Generate metric power units (Watt prefixes)."""
    base_dir = get_measures_base() / "power" / "metric"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("Attowatt", "Consts.ATTO"),
        ("Deciwatt", "Consts.DECI"),
        ("Dekawatt", "Consts.DEKA"),
        ("Exawatt", "Consts.EXA"),
        ("Femtowatt", "Consts.FEMTO"),
        ("Gigawatt", "Consts.GIGA"),
        ("Hectowatt", "Consts.HECTO"),
        ("Megawatt", "Consts.MEGA"),
        ("Microwatt", "Consts.MICRO"),
        ("Milliwatt", "Consts.MILLI"),
        ("Nanowatt", "Consts.NANO"),
        ("Petawatt", "Consts.PETA"),
        ("Picowatt", "Consts.PICO"),
        ("Terawatt", "Consts.TERA"),
        ("Yoctowatt", "Consts.YOCTO"),
        ("Yottawatt", "Consts.YOTTA"),
        ("Zeptowatt", "Consts.ZEPTO"),
        ("Zettawatt", "Consts.ZETTA")
    ]
    
    template = """package com.measures.power.metric

import com.measures.Consts
import com.measures.power.Watt
import com.measures.power.UnitPower
import com.measures.time.UnitTime
import com.measures.energy.UnitEnergy
import kotlin.jvm.JvmInline

@JvmInline
value class {unit_name}(override val value: Double) : UnitPower<{unit_name}> {{
    override fun asType(d: Double) = {unit_name}(d)
    override fun asBaseUnit() = Watt(value * {const_name})

    override operator fun plus(other: UnitPower<*>) = UnitPower.plusUnit(this, other)
    override operator fun minus(other: UnitPower<*>) = UnitPower.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitPower.timesUnit(this, other)
}}

fun UnitPower<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""
    
    for unit_name, const_name in units:
        file_path = base_dir / f"{unit_name}.kt"
        content = template.format(unit_name=unit_name, const_name=const_name)
        file_path.write_text(content)
        print(f"Created: {file_path}")
    
    return 0


# ==================== METRIC PRESSURE ====================

def generate_metric_pressure() -> int:
    """Generate metric pressure units (Pascal prefixes)."""
    base_dir = get_measures_base() / "pressure" / "metric"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("Attopascal", "Consts.ATTO"),
        ("Decipascal", "Consts.DECI"),
        ("Dekapascal", "Consts.DEKA"),
        ("Exapascal", "Consts.EXA"),
        ("Femtopascal", "Consts.FEMTO"),
        ("Gigapascal", "Consts.GIGA"),
        ("Hectopascal", "Consts.HECTO"),
        ("Megapascal", "Consts.MEGA"),
        ("Micropascal", "Consts.MICRO"),
        ("Millipascal", "Consts.MILLI"),
        ("Nanopascal", "Consts.NANO"),
        ("Petapascal", "Consts.PETA"),
        ("Picopascal", "Consts.PICO"),
        ("Terapascal", "Consts.TERA"),
        ("Yoctopascal", "Consts.YOCTO"),
        ("Yottapascal", "Consts.YOTTA"),
        ("Zeptopascal", "Consts.ZEPTO"),
        ("Zettapascal", "Consts.ZETTA")
    ]
    
    template = """package com.measures.pressure.metric

import com.measures.Consts
import com.measures.pressure.Pascal
import com.measures.pressure.UnitPressure
import com.measures.area.UnitArea
import com.measures.force.UnitForce
import kotlin.jvm.JvmInline

@JvmInline
value class {unit_name}(override val value: Double) : UnitPressure<{unit_name}> {{
    override fun asType(d: Double) = {unit_name}(d)
    override fun asBaseUnit() = Pascal(value * {const_name})

    override operator fun plus(other: UnitPressure<*>) = UnitPressure.plusUnit(this, other)
    override operator fun minus(other: UnitPressure<*>) = UnitPressure.minusUnit(this, other)
    override operator fun times(other: UnitArea<*>) = UnitPressure.timesUnit(this, other)
}}

fun UnitPressure<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""
    
    for unit_name, const_name in units:
        file_path = base_dir / f"{unit_name}.kt"
        content = template.format(unit_name=unit_name, const_name=const_name)
        file_path.write_text(content)
        print(f"Created: {file_path}")
    
    return 0


# ==================== METRIC TIME ====================

def generate_metric_time() -> int:
    """Generate metric time units (Second prefixes)."""
    base_dir = get_measures_base() / "time" / "metric"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("Attosecond", "Consts.ATTO"),
        ("Decisecond", "Consts.DECI"),
        ("Dekasecond", "Consts.DEKA"),
        ("Exasecond", "Consts.EXA"),
        ("Femtosecond", "Consts.FEMTO"),
        ("Gigasecond", "Consts.GIGA"),
        ("Hectosecond", "Consts.HECTO"),
        ("Megasecond", "Consts.MEGA"),
        ("Microsecond", "Consts.MICRO"),
        ("Millisecond", "Consts.MILLI"),
        ("Nanosecond", "Consts.NANO"),
        ("Petasecond", "Consts.PETA"),
        ("Picosecond", "Consts.PICO"),
        ("Terasecond", "Consts.TERA"),
        ("Yoctosecond", "Consts.YOCTO"),
        ("Yottasecond", "Consts.YOTTA"),
        ("Zeptosecond", "Consts.ZEPTO"),
        ("Zettasecond", "Consts.ZETTA")
    ]
    
    template = """package com.measures.time.metric

import com.measures.Consts
import com.measures.time.Second
import com.measures.time.UnitTime
import com.measures.frequency.Hertz
import kotlin.jvm.JvmInline

@JvmInline
value class {unit_name}(override val value: Double) : UnitTime<{unit_name}> {{
    override fun asType(d: Double) = {unit_name}(d)
    override fun asBaseUnit() = Second(value * {const_name})

    override operator fun plus(other: UnitTime<*>) = UnitTime.plusUnit(this, other)
    override operator fun minus(other: UnitTime<*>) = UnitTime.minusUnit(this, other)
    override fun inv(): Hertz = UnitTime.invUnit(this)
}}

fun UnitTime<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""
    
    for unit_name, const_name in units:
        file_path = base_dir / f"{unit_name}.kt"
        content = template.format(unit_name=unit_name, const_name=const_name)
        file_path.write_text(content)
        print(f"Created: {file_path}")
    
    return 0


# ==================== METRIC VELOCITY ====================

def generate_metric_velocity() -> int:
    """Generate metric velocity units (m/s prefixes)."""
    base_dir = get_measures_base() / "velocity" / "metric"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        ("AttometerPerSecond", "Consts.ATTO"),
        ("DecimeterPerSecond", "Consts.DECI"),
        ("DekameterPerSecond", "Consts.DEKA"),
        ("ExameterPerSecond", "Consts.EXA"),
        ("FemtometerPerSecond", "Consts.FEMTO"),
        ("GigameterPerSecond", "Consts.GIGA"),
        ("HectometerPerSecond", "Consts.HECTO"),
        ("MegameterPerSecond", "Consts.MEGA"),
        ("MicrometerPerSecond", "Consts.MICRO"),
        ("MillimeterPerSecond", "Consts.MILLI"),
        ("NanometerPerSecond", "Consts.NANO"),
        ("PetameterPerSecond", "Consts.PETA"),
        ("PicometerPerSecond", "Consts.PICO"),
        ("TerameterPerSecond", "Consts.TERA"),
        ("YoctometerPerSecond", "Consts.YOCTO"),
        ("YottameterPerSecond", "Consts.YOTTA"),
        ("ZeptometerPerSecond", "Consts.ZEPTO"),
        ("ZettameterPerSecond", "Consts.ZETTA")
    ]
    
    template = """package com.measures.velocity.metric

import com.measures.Consts
import com.measures.velocity.MetersPerSecond
import com.measures.velocity.UnitVelocity
import com.measures.time.UnitTime
import com.measures.distance.UnitDistance
import kotlin.jvm.JvmInline

@JvmInline
value class {unit_name}(override val value: Double) : UnitVelocity<{unit_name}> {{
    override fun asType(d: Double) = {unit_name}(d)
    override fun asBaseUnit() = MetersPerSecond(value * {const_name})

    override operator fun plus(other: UnitVelocity<*>) = UnitVelocity.plusUnit(this, other)
    override operator fun minus(other: UnitVelocity<*>) = UnitVelocity.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitVelocity.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitVelocity.divUnit(this, other)
}}

fun UnitVelocity<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""
    
    for unit_name, const_name in units:
        file_path = base_dir / f"{unit_name}.kt"
        content = template.format(unit_name=unit_name, const_name=const_name)
        file_path.write_text(content)
        print(f"Created: {file_path}")
    
    return 0


# ==================== METRIC WEIGHT ====================

def generate_metric_weight() -> int:
    """Generate metric weight units (Kilogram prefixes)."""
    base_dir = get_measures_base() / "weight" / "metric"
    base_dir.mkdir(parents=True, exist_ok=True)
    
    units = [
        # weight is special because the base unit is kilogram, not gram
        # all other units are divided by 1000 to get the base unit
        ("Gram", "1"),
        ("Attogram", "Consts.ATTO"),
        ("Decigram", "Consts.DECI"),
        ("Dekagram", "Consts.DEKA"),
        ("Exagram", "Consts.EXA"),
        ("Femtogram", "Consts.FEMTO"),
        ("Gigagram", "Consts.GIGA"),
        ("Hectogram", "Consts.HECTO"),
        ("Megagram", "Consts.MEGA"),
        ("Microgram", "Consts.MICRO"),
        ("Milligram", "Consts.MILLI"),
        ("Milligram", "Consts.MILLI"),
        ("Nanogram", "Consts.NANO"),
        ("Petagram", "Consts.PETA"),
        ("Picogram", "Consts.PICO"),
        ("Teragram", "Consts.TERA"),
        ("Yoctogram", "Consts.YOCTO"),
        ("Yottagram", "Consts.YOTTA"),
        ("Zeptogram", "Consts.ZEPTO"),
        ("Zettagram", "Consts.ZETTA")
    ]
    
    template = """package com.measures.weight.metric

import com.measures.Consts
import com.measures.weight.KiloGram
import com.measures.weight.UnitMass
import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import kotlin.jvm.JvmInline

@JvmInline
value class {unit_name}(override val value: Double) : UnitMass<{unit_name}> {{
    override fun asType(d: Double) = {unit_name}(d)
    override fun asBaseUnit() = KiloGram(value * {const_name} / 1000)

    override fun plus(other: UnitMass<*>): KiloGram = UnitMass.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): KiloGram = UnitMass.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.timesUnit(this, other)
}}

fun UnitMass<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""
    
    for unit_name, const_name in units:
        file_path = base_dir / f"{unit_name}.kt"
        content = template.format(unit_name=unit_name, const_name=const_name)
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

