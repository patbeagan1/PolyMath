#!/usr/bin/env python3
"""
Metric absorbed dose unit generation.
"""

from ..base.base_absorbeddose_generator import BaseAbsorbedDoseGenerator
from ..base.base_metric import MetricUnitGenerator


class MetricAbsorbedDoseGenerator(BaseAbsorbedDoseGenerator):
    """Generator for metric absorbed dose units (Gray prefixes)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="metric",
            package_name="com.measures.absorbeddose.metric"
        )
    
    def _get_units(self):
        """Get metric absorbed dose units."""
        generator = MetricUnitGenerator()
        return generator.generate_units("gray", "Gray")
    
    def get_additional_imports(self):
        """Get additional imports for metric units."""
        return ["com.measures.Consts"]

