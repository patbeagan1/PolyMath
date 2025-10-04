#!/usr/bin/env python3
"""
Metric distance unit generation.
"""

from .base_distance_generator import BaseDistanceGenerator


class MetricDistanceGenerator(BaseDistanceGenerator):
    """Generator for metric distance units (meter prefixes)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="metric",
            package_name="com.measures.distance.metric"
        )
    
    def _get_units(self):
        """Get metric distance units."""
        return [
            ("Attometer", "Meter(value * Consts.ATTO)"),
            ("Decimeter", "Meter(value * Consts.DECI)"),
            ("Dekameter", "Meter(value * Consts.DEKA)"),
            ("Exameter", "Meter(value * Consts.EXA)"),
            ("Femtometer", "Meter(value * Consts.FEMTO)"),
            ("Gigameter", "Meter(value * Consts.GIGA)"),
            ("Hectometer", "Meter(value * Consts.HECTO)"),
            ("Megameter", "Meter(value * Consts.MEGA)"),
            ("Micrometer", "Meter(value * Consts.MICRO)"),
            ("Millimeter", "Meter(value * Consts.MILLI)"),
            ("Nanometer", "Meter(value * Consts.NANO)"),
            ("Petameter", "Meter(value * Consts.PETA)"),
            ("Picometer", "Meter(value * Consts.PICO)"),
            ("Terameter", "Meter(value * Consts.TERA)"),
            ("Yoctometer", "Meter(value * Consts.YOCTO)"),
            ("Yottameter", "Meter(value * Consts.YOTTA)"),
            ("Zeptometer", "Meter(value * Consts.ZEPTO)"),
            ("Zettameter", "Meter(value * Consts.ZETTA)")
        ]
    
    def get_template(self):
        """Get the template with Consts import for metric units."""
        return """package {package_name}

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
    override fun asBaseUnit() = {base_conversion}

    override operator fun plus(other: UnitDistance<*>) = UnitDistance.plusUnit(this, other)
    override operator fun minus(other: UnitDistance<*>) = UnitDistance.minusUnit(this, other)
    override operator fun times(other: UnitDistance<*>) = UnitDistance.timesUnit(this, other)
    override operator fun times(other: UnitArea<*>) = UnitDistance.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>): MetersPerSecond = UnitDistance.divUnit(this, other)
}}

fun UnitDistance<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""


def generate_metric_distance() -> int:
    """Generate metric distance units (meter prefixes)."""
    generator = MetricDistanceGenerator()
    return generator.generate()
