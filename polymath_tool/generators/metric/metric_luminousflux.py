#!/usr/bin/env python3
"""
Metric luminous flux unit generation.
"""

from ..base.base_luminousflux_generator import BaseLuminousFluxGenerator
from ..base.base_metric import MetricUnitGenerator


class MetricLuminousFluxGenerator(BaseLuminousFluxGenerator):
    """Generator for metric luminous flux units (Lumen prefixes)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="metric",
            package_name="com.measures.luminousflux.metric"
        )
    
    def _get_units(self):
        """Get metric luminous flux units."""
        generator = MetricUnitGenerator()
        return generator.generate_units("lumen", "Lumen")
    
    def get_additional_imports(self):
        """Get additional imports for metric units."""
        return ["com.measures.Consts"]

