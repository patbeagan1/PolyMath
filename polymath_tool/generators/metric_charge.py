#!/usr/bin/env python3
"""
Charge unit generation (Coulomb).
"""

from .base_charge_generator import BaseChargeGenerator


class MetricChargeGenerator(BaseChargeGenerator):
    """Generator for metric charge units (Coulomb prefixes)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="metric",
            package_name="com.measures.charge.metric"
        )
    
    def _get_units(self):
        """Get metric charge units."""
        return [
            # Complete metric charge units using Consts values for full SI greek set from Base.kt
            ("Yottacoulomb", "Coulomb(value * Consts.YOTTA)"),
            ("Zettacoulomb", "Coulomb(value * Consts.ZETTA)"),
            ("Exacoulomb", "Coulomb(value * Consts.EXA)"),
            ("Petacoulomb", "Coulomb(value * Consts.PETA)"),
            ("Teracoulomb", "Coulomb(value * Consts.TERA)"),
            ("Gigacoulomb", "Coulomb(value * Consts.GIGA)"),
            ("Megacoulomb", "Coulomb(value * Consts.MEGA)"),
            ("Kilocoulomb", "Coulomb(value * Consts.KILO)"),
            ("Hectocoulomb", "Coulomb(value * Consts.HECTO)"),
            ("Decacoulomb", "Coulomb(value * Consts.DEKA)"),
            ("Decicoulomb", "Coulomb(value * Consts.DECI)"),
            ("Centicoulomb", "Coulomb(value * Consts.CENTI)"),
            ("Millicoulomb", "Coulomb(value * Consts.MILLI)"),
            ("Microcoulomb", "Coulomb(value * Consts.MICRO)"),
            ("Nanocoulomb", "Coulomb(value * Consts.NANO)"),
            ("Picocoulomb", "Coulomb(value * Consts.PICO)"),
            ("Femtocoulomb", "Coulomb(value * Consts.FEMTO)"),
            ("Attocoulomb", "Coulomb(value * Consts.ATTO)"),
            ("Zeptocoulomb", "Coulomb(value * Consts.ZEPTO)"),
            ("Yoctocoulomb", "Coulomb(value * Consts.YOCTO)"),
        ]
    
    def get_additional_imports(self):
        """Get additional imports for metric units."""
        return ["com.measures.Consts"]


def generate_charge() -> int:
    """Generate charge units (Coulomb)."""
    generator = MetricChargeGenerator()
    return generator.generate()
