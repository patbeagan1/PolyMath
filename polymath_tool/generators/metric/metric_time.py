#!/usr/bin/env python3
"""
Metric time unit generation.
"""

from ..base.base_time_generator import BaseTimeGenerator
from ..base.base_metric import MetricUnitGenerator


class MetricTimeGenerator(BaseTimeGenerator):
    """Generator for metric time units (Second prefixes)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="metric",
            package_name="com.measures.time.metric"
        )
    
    def _get_units(self):
        """Get metric time units."""
        generator = MetricUnitGenerator()
        return generator.generate_units("second", "Second")
    
    def get_additional_imports(self):
        """Get additional imports for metric units."""
        return ["com.measures.Consts"]