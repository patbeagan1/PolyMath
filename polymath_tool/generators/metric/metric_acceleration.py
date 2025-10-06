#!/usr/bin/env python3
"""
Metric acceleration unit generation.
"""

from ..base.base_acceleration_generator import BaseAccelerationGenerator
from ..base.base_metric import MetricUnitGenerator


class MetricAccelerationGenerator(BaseAccelerationGenerator):
    """Generator for metric acceleration units (meters per second per second)."""

    def __init__(self):
        super().__init__(
            subdirectory="metric", package_name="com.measures.acceleration.metric"
        )

    def _get_units(self):
        """Get metric acceleration units."""
        generator = MetricUnitGenerator()
        return generator.generate_units("metersPerSecondPerSecond", "MetersPerSecondPerSecond")

    def get_additional_imports(self):
        """Get additional imports for metric units."""
        return ["com.measures.Consts"]
