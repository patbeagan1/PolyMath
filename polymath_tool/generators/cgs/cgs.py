#!/usr/bin/env python3
"""
Non-SI power unit generation.
"""
from polymath_tool.generators.base.base_force_generator import BaseForceGenerator
from polymath_tool.generators.base.base_volume_generator import BaseVolumeGenerator

class CGSVolumeGenerator(BaseVolumeGenerator):
    def __init__(self):
        super().__init__(
            subdirectory="cgs",
            package_name="com.measures.Volume.cgs"
        )

    def _get_units(self):
        """Get CGS Volume units"""
        return [
            ("CubicCentimeters", "Liters(value / 1000)")
        ]

class CGSForceGenerator(BaseForceGenerator):
    def __init__(self):
        super().__init__(
            subdirectory="cgs",
            package_name="com.measures.force.cgs"
        )

    def _get_units(self):
        """Get CGS Force units"""
        return [
            ("Dynes", "Newton(value / 100_000)")
        ]