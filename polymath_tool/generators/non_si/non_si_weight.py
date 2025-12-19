#!/usr/bin/env python3
"""
Non-SI weight unit generation.
"""

from ..base.base_mass_generator import BaseMassGenerator


class NonSiWeightGenerator(BaseMassGenerator):
    """Generator for non-SI weight units."""
    
    def __init__(self):
        super().__init__(
            subdirectory="non_si",
            package_name="com.measures.weight.non_si"
        )
    
    
    def _get_units(self):
        """Get non-SI weight units."""
        return [
            ("Carat", "Kilogram(value * 0.0002)"),
        ]

