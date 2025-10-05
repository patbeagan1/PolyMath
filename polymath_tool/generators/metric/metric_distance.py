#!/usr/bin/env python3
"""
Metric distance unit generation.
"""

from ..base.base_distance_generator import BaseDistanceGenerator
from ..base.base_metric import MetricUnitGenerator


class MetricDistanceGenerator(BaseDistanceGenerator):
    """Generator for metric distance units."""
    
    def __init__(self):
        super().__init__(
            subdirectory="metric",
            package_name="com.measures.distance.metric"
        )
    
    def _get_units(self):
        """Get metric distance units."""
        generator = MetricUnitGenerator()
        return generator.generate_units("meter", "Meter")
    
    def get_additional_imports(self):
        """Get additional imports for metric units."""
        return ["com.measures.Consts"]


