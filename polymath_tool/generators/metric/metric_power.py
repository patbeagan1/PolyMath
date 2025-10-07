#!/usr/bin/env python3
"""
Metric power unit generation.
"""

from ..base.base_power_generator import BasePowerGenerator
from ..base.base_metric import MetricUnitGenerator


class MetricPowerGenerator(BasePowerGenerator):
    """Generator for metric power units (Watt prefixes)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="metric",
            package_name="com.measures.power.metric"
        )
    
    def _get_units(self):
        """Get metric power units."""
        generator = MetricUnitGenerator()
        return generator.generate_units("watt", "Watt")
    
    def get_additional_imports(self):
        """Get additional imports for metric units."""
        return ["com.measures.Consts"]
