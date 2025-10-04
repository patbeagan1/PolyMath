#!/usr/bin/env python3
"""
Metric volume unit generation.
"""

from .base_volume_generator import BaseVolumeGenerator


class MetricVolumeGenerator(BaseVolumeGenerator):
    """Generator for metric volume units (liter prefixes)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="metric",
            package_name="com.measures.volume.metric"
        )
    
    def _get_units(self):
        """Get metric volume units."""
        return [
            ("Attoliter", "Liter(value * Consts.ATTO)"),
            ("Centiliter", "Liter(value * Consts.CENTI)"),
            ("CubicCentimeter", "Liter(value * Consts.MILLI)"),
            ("CubicMeter", "Liter(value * Consts.KILO)"),
            ("Deciliter", "Liter(value * Consts.DECI)"),
            ("Dekaliter", "Liter(value * Consts.DEKA)"),
            ("Exaliter", "Liter(value * Consts.EXA)"),
            ("Femtoliter", "Liter(value * Consts.FEMTO)"),
            ("Gigaliter", "Liter(value * Consts.GIGA)"),
            ("Hectoliter", "Liter(value * Consts.HECTO)"),
            ("Kiloliter", "Liter(value * Consts.KILO)"),
            ("Megaliter", "Liter(value * Consts.MEGA)"),
            ("Microliter", "Liter(value * Consts.MICRO)"),
            ("Milliliter", "Liter(value * Consts.MILLI)"),
            ("Nanoliter", "Liter(value * Consts.NANO)"),
            ("Petaliter", "Liter(value * Consts.PETA)"),
            ("Picoliter", "Liter(value * Consts.PICO)"),
            ("Teraliter", "Liter(value * Consts.TERA)"),
            ("Yoctoliter", "Liter(value * Consts.YOCTO)"),
            ("Yottaliter", "Liter(value * Consts.YOTTA)"),
            ("Zeptoliter", "Liter(value * Consts.ZEPTO)"),
            ("Zettaliter", "Liter(value * Consts.ZETTA)")
        ]
    
    def get_template(self):
        """Get the template with Consts import for metric units."""
        return """package {package_name}

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
    override fun asBaseUnit() = {base_conversion}

    override operator fun plus(other: UnitVolume<*>) = UnitVolume.plusUnit(this, other)
    override operator fun minus(other: UnitVolume<*>) = UnitVolume.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitVolume.divUnit(this, other)
    override operator fun div(other: UnitDistance<*>): SquareMeter = UnitVolume.divUnit(this, other)
}}

fun UnitVolume<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""


def generate_metric_volume() -> int:
    """Generate metric volume units (liter prefixes)."""
    generator = MetricVolumeGenerator()
    return generator.generate()
