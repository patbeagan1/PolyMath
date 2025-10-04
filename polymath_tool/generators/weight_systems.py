#!/usr/bin/env python3
"""
Weight systems unit generation.
"""

from .base_weight_generator import BaseWeightGenerator


class AvoirdupoisWeightGenerator(BaseWeightGenerator):
    """Generator for Avoirdupois weight units."""
    
    def __init__(self):
        super().__init__(
            subdirectory="avoirdupois",
            package_name="com.measures.weight.avoirdupois"
        )
    
    def _get_units(self):
        """Get Avoirdupois weight units."""
        return [
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


def generate_avoirdupois() -> int:
    """Generate Avoirdupois weight units."""
    generator = AvoirdupoisWeightGenerator()
    return generator.generate()


class TroyWeightGenerator(BaseWeightGenerator):
    """Generator for Troy weight units."""
    
    def __init__(self):
        super().__init__(
            subdirectory="troy",
            package_name="com.measures.weight.troy"
        )
    
    def _get_units(self):
        """Get Troy weight units."""
        return [
            ("TroyGrain", "TroyPennyweight(value / 24).asBaseUnit()"),
            ("TroyPennyweight", "TroyOunce(value / 20).asBaseUnit()"),
            ("TroyOunce", "KiloGram(value * 31.1034768 / 1000)"),
            ("TroyPound", "TroyOunce(value * 12).asBaseUnit()")
        ]
    
    def get_template(self):
        """Get the template with Gram import for troy units."""
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


def generate_troy() -> int:
    """Generate Troy weight units."""
    generator = TroyWeightGenerator()
    return generator.generate()
