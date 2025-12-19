#!/usr/bin/env python3
"""
Metric conductance unit generation.
"""

from ..base.base_conductance_generator import BaseConductanceGenerator
from ..base.base_metric import MetricUnitGenerator


class MetricConductanceGenerator(BaseConductanceGenerator):
    """Generator for metric conductance units (Siemens prefixes)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="metric",
            package_name="com.measures.conductance.metric"
        )
    
    def _get_units(self):
        """Get metric conductance units."""
        generator = MetricUnitGenerator()
        return generator.generate_units("siemens", "Siemens")
    
    def get_additional_imports(self):
        """Get additional imports for metric units."""
        return ["com.measures.Consts"]

