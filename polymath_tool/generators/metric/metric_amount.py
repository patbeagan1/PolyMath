#!/usr/bin/env python3
"""
Metric amount unit generation.
"""

from ..base.base_amount_generator import BaseAmountGenerator
from ..base.base_metric import MetricUnitGenerator


class MetricAmountGenerator(BaseAmountGenerator):
    """Generator for metric amount units (mole)."""

    def __init__(self):
        super().__init__(
            subdirectory="metric", package_name="com.measures.amount.metric"
        )

    def _get_units(self):
        """Get metric amount units."""
        generator = MetricUnitGenerator()
        return generator.generate_units("mole", "Mole")

    def get_additional_imports(self):
        """Get additional imports for metric units."""
        return ["com.measures.Consts"]
