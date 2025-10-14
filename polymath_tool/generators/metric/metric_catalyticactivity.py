#!/usr/bin/env python3
"""
Metric catalytic activity unit generation.
"""

from ..base.base_catalyticactivity_generator import BaseCatalyticActivityGenerator
from ..base.base_metric import MetricUnitGenerator


class MetricCatalyticActivityGenerator(BaseCatalyticActivityGenerator):
    """Generator for metric catalytic activity units (Katal prefixes)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="metric",
            package_name="com.measures.catalyticactivity.metric"
        )
    
    def _get_units(self):
        """Get metric catalytic activity units."""
        generator = MetricUnitGenerator()
        return generator.generate_units("katal", "Katal")
    
    def get_additional_imports(self):
        """Get additional imports for metric units."""
        return ["com.measures.Consts"]

