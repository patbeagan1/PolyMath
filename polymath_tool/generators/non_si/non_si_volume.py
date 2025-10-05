#!/usr/bin/env python3
"""
Non-SI volume unit generation.
"""

from .base_volume_generator import BaseVolumeGenerator


class NonSiVolumeGenerator(BaseVolumeGenerator):
    """Generator for non-SI volume units."""
    
    def __init__(self):
        super().__init__(
            subdirectory="non_si",
            package_name="com.measures.volume.non_si"
        )
    
    def _get_units(self):
        """Get non-SI volume units."""
        return [
            ("CupBreakfast", "Liter(value * 0.284130625)"),
            ("CupCanadian", "Liter(value * 0.2273045)"),
            ("CoffeeMug", "Liter(value * 0.35488235475)"),  # 12 oz
            ("TablespoonCanadian", "Liter(value * 0.01420653125)"),
            ("TeaspoonCanadian", "Liter(value * 0.004735510416666667)"),
        ]


def generate_non_si_volume() -> int:
    """Generate non-SI volume units."""
    generator = NonSiVolumeGenerator()
    return generator.generate()
