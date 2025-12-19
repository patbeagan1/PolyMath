#!/usr/bin/env python3
"""
Metric dose equivalent unit generation.
"""

from ..base.base_doseequivalent_generator import BaseDoseEquivalentGenerator
from ..base.base_metric import MetricUnitGenerator


class MetricDoseEquivalentGenerator(BaseDoseEquivalentGenerator):
    """Generator for metric dose equivalent units (Sievert prefixes)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="metric",
            package_name="com.measures.doseequivalent.metric"
        )
    
    def _get_units(self):
        """Get metric dose equivalent units."""
        generator = MetricUnitGenerator()
        return generator.generate_units("sievert", "Sievert")
    
    def get_additional_imports(self):
        """Get additional imports for metric units."""
        return ["com.measures.Consts"]

