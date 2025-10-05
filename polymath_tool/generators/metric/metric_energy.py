#!/usr/bin/env python3
"""
Metric energy unit generation.
"""

from ..base.base_energy_generator import BaseEnergyGenerator
from ..base.base_metric import MetricUnitGenerator


class MetricEnergyGenerator(BaseEnergyGenerator):
    """Generator for metric energy units (Joule prefixes)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="metric",
            package_name="com.measures.energy.metric"
        )
    
    def _get_units(self):
        """Get metric energy units."""
        generator = MetricUnitGenerator()
        return generator.generate_units("joule", "Joule")
    
    def get_additional_imports(self):
        """Get additional imports for metric units."""
        return ["com.measures.Consts"]
