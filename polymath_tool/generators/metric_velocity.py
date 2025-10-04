#!/usr/bin/env python3
"""
Metric velocity unit generation.
"""

from .base_velocity_generator import BaseVelocityGenerator


class MetricVelocityGenerator(BaseVelocityGenerator):
    """Generator for metric velocity units (m/s prefixes)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="metric",
            package_name="com.measures.velocity.metric"
        )
    
    def _get_units(self):
        """Get metric velocity units."""
        return [
            ("AttometerPerSecond", "MetersPerSecond(value * Consts.ATTO)"),
            ("DecimeterPerSecond", "MetersPerSecond(value * Consts.DECI)"),
            ("DekameterPerSecond", "MetersPerSecond(value * Consts.DEKA)"),
            ("ExameterPerSecond", "MetersPerSecond(value * Consts.EXA)"),
            ("FemtometerPerSecond", "MetersPerSecond(value * Consts.FEMTO)"),
            ("GigameterPerSecond", "MetersPerSecond(value * Consts.GIGA)"),
            ("HectometerPerSecond", "MetersPerSecond(value * Consts.HECTO)"),
            ("MegameterPerSecond", "MetersPerSecond(value * Consts.MEGA)"),
            ("MicrometerPerSecond", "MetersPerSecond(value * Consts.MICRO)"),
            ("MillimeterPerSecond", "MetersPerSecond(value * Consts.MILLI)"),
            ("NanometerPerSecond", "MetersPerSecond(value * Consts.NANO)"),
            ("PetameterPerSecond", "MetersPerSecond(value * Consts.PETA)"),
            ("PicometerPerSecond", "MetersPerSecond(value * Consts.PICO)"),
            ("TerameterPerSecond", "MetersPerSecond(value * Consts.TERA)"),
            ("YoctometerPerSecond", "MetersPerSecond(value * Consts.YOCTO)"),
            ("YottameterPerSecond", "MetersPerSecond(value * Consts.YOTTA)"),
            ("ZeptometerPerSecond", "MetersPerSecond(value * Consts.ZEPTO)"),
            ("ZettameterPerSecond", "MetersPerSecond(value * Consts.ZETTA)")
        ]
    
    def get_additional_imports(self):
        """Get additional imports for metric units."""
        return ["com.measures.Consts"]


def generate_metric_velocity() -> int:
    """Generate metric velocity units (m/s prefixes)."""
    generator = MetricVelocityGenerator()
    return generator.generate()
