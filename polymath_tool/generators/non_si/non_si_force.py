#!/usr/bin/env python3
"""
Non-SI force unit generation.
"""

from .base_force_generator import BaseForceGenerator


class NonSiForceGenerator(BaseForceGenerator):
    """Generator for non-SI force units."""
    
    def __init__(self):
        super().__init__(
            subdirectory="non_si",
            package_name="com.measures.force.non_si"
        )
    
    def _get_units(self):
        """Get non-SI force units."""
        return [
            ("Dyne", "Newton(value * 1e-5)"),
            ("KilogramForce", "Newton(value * 9.80665)"),
            ("PoundForce", "Newton(value * 4.4482216152605)"),
        ]


def generate_non_si_force() -> int:
    """Generate non-SI force units."""
    generator = NonSiForceGenerator()
    return generator.generate()
