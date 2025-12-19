#!/usr/bin/env python3
"""
Metric activity unit generation.
"""

from ..base.base_activity_generator import BaseActivityGenerator
from ..base.base_metric import MetricUnitGenerator


class MetricActivityGenerator(BaseActivityGenerator):
    """Generator for metric activity units (Becquerel prefixes)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="metric",
            package_name="com.measures.activity.metric"
        )
    
    def _get_units(self):
        """Get metric activity units."""
        generator = MetricUnitGenerator()
        return generator.generate_units("becquerel", "Becquerel")
    
    def get_additional_imports(self):
        """Get additional imports for metric units."""
        return ["com.measures.Consts"]

