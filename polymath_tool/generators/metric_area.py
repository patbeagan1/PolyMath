#!/usr/bin/env python3
"""
Metric area unit generation.
"""

from .base_area_generator import BaseAreaGenerator


class MetricAreaGenerator(BaseAreaGenerator):
    """Generator for metric area units (m² prefixes)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="metric",
            package_name="com.measures.area.metric"
        )
    
    def _get_units(self):
        """Get metric area units."""
        return [
            ("AttometerSquared", "SquareMeter(value * Consts.ATTO)"),
            ("DecimeterSquared", "SquareMeter(value * Consts.DECI)"),
            ("DekameterSquared", "SquareMeter(value * Consts.DEKA)"),
            ("ExameterSquared", "SquareMeter(value * Consts.EXA)"),
            ("FemtometerSquared", "SquareMeter(value * Consts.FEMTO)"),
            ("GigameterSquared", "SquareMeter(value * Consts.GIGA)"),
            ("HectometerSquared", "SquareMeter(value * Consts.HECTO)"),
            ("MegameterSquared", "SquareMeter(value * Consts.MEGA)"),
            ("MicrometerSquared", "SquareMeter(value * Consts.MICRO)"),
            ("MillimeterSquared", "SquareMeter(value * Consts.MILLI)"),
            ("NanometerSquared", "SquareMeter(value * Consts.NANO)"),
            ("PetameterSquared", "SquareMeter(value * Consts.PETA)"),
            ("PicometerSquared", "SquareMeter(value * Consts.PICO)"),
            ("TerameterSquared", "SquareMeter(value * Consts.TERA)"),
            ("YoctometerSquared", "SquareMeter(value * Consts.YOCTO)"),
            ("YottameterSquared", "SquareMeter(value * Consts.YOTTA)"),
            ("ZeptometerSquared", "SquareMeter(value * Consts.ZEPTO)"),
            ("ZettameterSquared", "SquareMeter(value * Consts.ZETTA)")
        ]
    
    def get_template(self):
        """Get the template with Consts import for metric units."""
        return """package {package_name}

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
    override fun asBaseUnit() = {base_conversion}

    override fun plus(other: UnitArea<*>): SquareMeter = UnitArea.plusUnit(this, other)
    override fun minus(other: UnitArea<*>): SquareMeter = UnitArea.minusUnit(this, other)
    override fun times(other: UnitDistance<*>): Liter = UnitArea.timesUnit(this, other)
    override fun div(other: UnitDistance<*>): Meter = UnitArea.divUnit(this, other)
}}

fun UnitArea<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""


def generate_metric_area() -> int:
    """Generate metric area units (m² prefixes)."""
    generator = MetricAreaGenerator()
    return generator.generate()
