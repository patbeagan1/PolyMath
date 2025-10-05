#!/usr/bin/env python3
"""
Non-SI charge unit generation.
"""

from ..base.base_charge_generator import BaseChargeGenerator


class NonSiChargeGenerator(BaseChargeGenerator):
    """Generator for non-SI charge units."""
    
    def __init__(self):
        super().__init__(
            subdirectory="non_si",
            package_name="com.measures.charge.non_si"
        )
    
    def _get_units(self):
        """Get non-SI charge units."""
        return [
            ("Amperehour", "Coulomb(value * 3600.0)"),
        ]


def generate_non_si_charge() -> int:
    """Generate non-SI charge units."""
    generator = NonSiChargeGenerator()
    return generator.generate()
