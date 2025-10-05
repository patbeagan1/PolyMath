#!/usr/bin/env python3
"""
Charge unit generation (Coulomb).
"""

from .base_charge_generator import BaseChargeGenerator
from .base.base_metric import generate_metric_units


class MetricChargeGenerator(BaseChargeGenerator):
    """Generator for metric charge units (Coulomb prefixes)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="metric",
            package_name="com.measures.charge.metric"
        )
    
    def _get_units(self):
        """Get metric charge units."""
        return generate_metric_units("coulomb", "Coulomb")
    
    def get_additional_imports(self):
        """Get additional imports for metric units."""
        return ["com.measures.Consts"]