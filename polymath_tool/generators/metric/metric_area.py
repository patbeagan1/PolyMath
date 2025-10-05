#!/usr/bin/env python3
"""
Metric area unit generation.
"""

from .base.base_area_generator import BaseAreaGenerator
from .base.base_metric import generate_metric_units


class MetricAreaGenerator(BaseAreaGenerator):
    """Generator for metric area units."""

    def __init__(self):
        super().__init__(subdirectory="metric", package_name="com.measures.area.metric")

    def _get_units(self):
        """Get metric area units."""
        return generate_metric_units("meterSquared", "SquareMeter")

    def get_additional_imports(self):
        """Get additional imports for metric units."""
        return ["com.measures.Consts"]
