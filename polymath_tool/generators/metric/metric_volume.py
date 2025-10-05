#!/usr/bin/env python3
"""
Metric volume unit generation.
"""

from .base_volume_generator import BaseVolumeGenerator
from .base.base_metric import generate_metric_units


class MetricVolumeGenerator(BaseVolumeGenerator):
    """Generator for metric volume units."""
    
    def __init__(self):
        super().__init__(
            subdirectory="metric",
            package_name="com.measures.volume.metric"
        )
    
    def _get_units(self):
        """Get metric volume units."""
        return generate_metric_units("liter", "Liter")
    
    def get_additional_imports(self):
        """Get additional imports for metric units."""
        return ["com.measures.Consts"]