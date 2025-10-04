#!/usr/bin/env python3
"""
Weight systems unit generation (1959 agreement).
Based on the 1959 International Yard and Pound Agreement and historical systems.
"""

from .base_weight_generator import BaseWeightGenerator


class Avoirdupois1959WeightGenerator(BaseWeightGenerator):
    """Generator for Avoirdupois weight units (1959 agreement)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="avoirdupois_1959",
            package_name="com.measures.weight.avoirdupois_1959"
        )
    
    def _get_units(self):
        """Get Avoirdupois weight units (1959 agreement)."""
        return [
            ("Grain", "Pound(value / 7000).asBaseUnit()"),  # 1/7000 pound
            ("Dram", "Pound(value / 256).asBaseUnit()"),  # 1/256 pound
            ("Ounce", "Pound(value / 16).asBaseUnit()"),  # 1/16 pound
            ("Pound", "KiloGram(value * 0.45359237)"),  # 1959 agreement
            ("Stone", "Pound(value * 14).asBaseUnit()"),  # 14 pounds
            ("Quarter", "Pound(value * 28).asBaseUnit()"),  # 28 pounds
            ("Hundredweight", "Pound(value * 100).asBaseUnit()"),  # 100 pounds (US)
            ("LongHundredweight", "Pound(value * 112).asBaseUnit()"),  # 112 pounds (UK)
            ("ShortTon", "Pound(value * 2000).asBaseUnit()"),  # 2000 pounds (US)
            ("LongTon", "Pound(value * 2240).asBaseUnit()"),  # 2240 pounds (UK)
        ]


def generate_avoirdupois_1959() -> int:
    """Generate Avoirdupois weight units (1959 agreement)."""
    generator = Avoirdupois1959WeightGenerator()
    return generator.generate()


class Troy1959WeightGenerator(BaseWeightGenerator):
    """Generator for Troy weight units (1959 agreement)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="troy_1959",
            package_name="com.measures.weight.troy_1959"
        )
    
    def _get_units(self):
        """Get Troy weight units (1959 agreement)."""
        return [
            ("TroyGrain", "KiloGram(value * 0.06479891 / 1000)"),  # Same grain as avoirdupois
            ("TroyPennyweight", "TroyGrain(value * 24).asBaseUnit()"),  # 24 grains
            ("TroyOunce", "TroyPennyweight(value * 20).asBaseUnit()"),  # 20 pennyweights
            ("TroyPound", "TroyOunce(value * 12).asBaseUnit()"),  # 12 troy ounces
        ]
    
    def get_template(self):
        """Get the template with Gram import for troy 1959 units."""
        return """package {package_name}

import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import com.measures.weight.KiloGram
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


def generate_troy_1959() -> int:
    """Generate Troy weight units (1959 agreement)."""
    generator = Troy1959WeightGenerator()
    return generator.generate()


class ApothecariesWeightGenerator(BaseWeightGenerator):
    """Generator for Apothecaries weight units."""
    
    def __init__(self):
        super().__init__(
            subdirectory="apothecaries",
            package_name="com.measures.weight.apothecaries"
        )
    
    def _get_units(self):
        """Get Apothecaries weight units."""
        return [
            ("ApothecariesGrain", "KiloGram(value * 0.06479891 /1000)"),  # Same as troy grain
            ("ApothecariesScruple", "ApothecariesGrain(value * 20).asBaseUnit()"),  # 20 grains
            ("ApothecariesDram", "ApothecariesScruple(value * 3).asBaseUnit()"),  # 3 scruples
            ("ApothecariesOunce", "ApothecariesDram(value * 8).asBaseUnit()"),  # 8 drams
            ("ApothecariesPound", "ApothecariesOunce(value * 12).asBaseUnit()"),  # 12 ounces
        ]
    
    def get_template(self):
        """Get the template with Gram import for apothecaries units."""
        return """package {package_name}

import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import com.measures.weight.KiloGram
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


def generate_apothecaries() -> int:
    """Generate Apothecaries weight units."""
    generator = ApothecariesWeightGenerator()
    return generator.generate()

