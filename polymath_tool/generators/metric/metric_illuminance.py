#!/usr/bin/env python3
"""
Metric illuminance unit generation.
"""

from ..base.base_illuminance_generator import BaseIlluminanceGenerator
from ..base.base_metric import MetricUnitGenerator


class MetricIlluminanceGenerator(BaseIlluminanceGenerator):
    """Generator for metric illuminance units (Lux prefixes)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="metric",
            package_name="com.measures.illuminance.metric"
        )
    
    def _get_units(self):
        """Get metric illuminance units."""
        generator = MetricUnitGenerator()
        return generator.generate_units("lux", "Lux")
    
    def get_additional_imports(self):
        """Get additional imports for metric units."""
        return ["com.measures.Consts"]

