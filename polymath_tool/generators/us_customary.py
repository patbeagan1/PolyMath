#!/usr/bin/env python3
"""
US Customary unit generation.
"""

from .base_area_generator import BaseAreaGenerator


class USCustomaryAreaGenerator(BaseAreaGenerator):
    """Generator for US Customary area units."""
    
    def __init__(self):
        super().__init__(
            subdirectory="us_customary",
            package_name="com.measures.area.us_customary"
        )
    
    def _get_units(self):
        """Get US Customary area units."""
        return [
            ("USSquareFoot", "SquareMeter(value * 0.09290304)"),
            ("USSquareYard", "USSquareFoot(value * 9).asBaseUnit()"),
            ("USSquareMile", "USSquareFoot(value * 27878400).asBaseUnit()"),
            ("USAcre", "USSquareFoot(value * 43560).asBaseUnit()"),
        ]


def generate_us_customary_area() -> int:
    """Generate US Customary area units."""
    generator = USCustomaryAreaGenerator()
    return generator.generate()
