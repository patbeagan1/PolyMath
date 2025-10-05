#!/usr/bin/env python3
"""
Metric distance unit generation.
"""

from .base_distance_generator import BaseDistanceGenerator
from .base.base_metric import generate_metric_units


class MetricDistanceGenerator(BaseDistanceGenerator):
    """Generator for metric distance units."""
    
    def __init__(self):
        super().__init__(
            subdirectory="metric",
            package_name="com.measures.distance.metric"
        )
    
    def _get_units(self):
        """Get metric distance units."""
        return generate_metric_units("meter", "Meter")
    
    def get_additional_imports(self):
        """Get additional imports for metric units."""
        return ["com.measures.Consts"]
