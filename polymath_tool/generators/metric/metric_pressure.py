#!/usr/bin/env python3
"""
Metric pressure unit generation.
"""

from ..base.base_pressure_generator import BasePressureGenerator
from ..base.base_metric import MetricUnitGenerator


class MetricPressureGenerator(BasePressureGenerator):
    """Generator for metric pressure units (Pascal prefixes)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="metric",
            package_name="com.measures.pressure.metric"
        )
    
    def _get_units(self):
        """Get metric pressure units."""
        generator = MetricUnitGenerator()
        return generator.generate_units("pascal", "Pascal")
    
    def get_additional_imports(self):
        """Get additional imports for metric units."""
        return ["com.measures.Consts"]
