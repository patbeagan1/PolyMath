#!/usr/bin/env python3
"""
Metric velocity unit generation.
"""

from ..base.base_velocity_generator import BaseVelocityGenerator
from ..base.base_metric import MetricUnitGenerator


class MetricVelocityGenerator(BaseVelocityGenerator):
    """Generator for metric velocity units (m/s prefixes)."""

    def __init__(self):
        super().__init__(
            subdirectory="metric", package_name="com.measures.velocity.metric"
        )

    def _get_units(self):
        """Get metric velocity units."""
        generator = MetricUnitGenerator()
        return generator.generate_units("meterPerSecond", "MetersPerSecond")

    def get_additional_imports(self):
        """Get additional imports for metric units."""
        return ["com.measures.Consts"]
