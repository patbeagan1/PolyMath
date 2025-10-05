#!/usr/bin/env python3
"""
Current unit generation (Ampere).
"""

from .base_current_generator import BaseCurrentGenerator
from .base.base_metric import generate_metric_units


class MetricCurrentGenerator(BaseCurrentGenerator):
    """Generator for metric current units (Ampere prefixes)."""

    def __init__(self):
        super().__init__(
            subdirectory="metric", package_name="com.measures.current.metric"
        )

    def _get_units(self):
        """Get metric current units."""
        return generate_metric_units("ampere", "Ampere")

    def get_additional_imports(self):
        """Get additional imports for metric units."""
        return ["com.measures.Consts"]
