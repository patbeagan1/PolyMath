#!/usr/bin/env python3
"""
Astronomical area unit generation.
"""

from .base_acceleration_generator import BaseAccelerationGenerator
from .base_distance_generator import BaseDistanceGenerator


class AstronomicalDistanceGenerator(BaseDistanceGenerator):
    """Generator for astronomical distance units."""

    def __init__(self):
        super().__init__(
            subdirectory="astronomical",
            package_name="com.measures.distance.astronomical",
        )

    def _get_units(self):
        """Get astronomical distance units."""
        return [
            ("AstronomicalUnit", "Meter(value * 149597870700.0)"),
            ("LightYear", "Meter(value * 9460730472580800.0)"),
            ("Parsec", "Meter(value * 30856775814913673.0)"),
        ]


class GForceAccelerationGenerator(BaseAccelerationGenerator):
    """Generator for g-force acceleration units."""

    def __init__(self):
        super().__init__(
            subdirectory="g_force", package_name="com.measures.acceleration.g_force"
        )

    def _get_units(self):
        """Get g-force acceleration units."""
        return [
            ("GForce", "MetersPerSecondPerSecond(value * 9.80665)"),
            ("LunarG", "MetersPerSecondPerSecond(value * 1.62)"),
            ("MartianG", "MetersPerSecondPerSecond(value * 3.72076)"),
            ("MercurianG", "MetersPerSecondPerSecond(value * 3.7)"),
            ("VenusianG", "MetersPerSecondPerSecond(value * 8.87)"),
            ("JovianG", "MetersPerSecondPerSecond(value * 24.79)"),
            ("SaturnianG", "MetersPerSecondPerSecond(value * 10.44)"),
            ("UranianG", "MetersPerSecondPerSecond(value * 8.69)"),
            ("NeptunianG", "MetersPerSecondPerSecond(value * 11.15)"),
        ]
