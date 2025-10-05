#!/usr/bin/env python3
"""
Metric force unit generation.
"""

from .base_force_generator import BaseForceGenerator
from .base.base_metric import generate_metric_units


class MetricForceGenerator(BaseForceGenerator):
    """Generator for metric force units (Newton prefixes)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="metric",
            package_name="com.measures.force.metric"
        )
    
    def _get_units(self):
        """Get metric force units."""
        return generate_metric_units("newton", "Newton")
    
    def get_additional_imports(self):
        """Get additional imports for metric units."""
        return ["com.measures.Consts"]