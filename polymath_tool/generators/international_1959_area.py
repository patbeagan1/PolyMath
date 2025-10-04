#!/usr/bin/env python3
"""
International area unit generation.
"""

from .base_area_generator import BaseAreaGenerator


class InternationalAreaGenerator(BaseAreaGenerator):
    """Generator for International area units."""
    
    def __init__(self):
        super().__init__(
            subdirectory="international",
            package_name="com.measures.area.international"
        )
    
    def _get_units(self):
        """Get International area units."""
        return [
            ("InternationalSquareFoot", "SquareMeter(value * 0.09290304)"),
            ("InternationalSquareYard", "InternationalSquareFoot(value * 9).asBaseUnit()"),
            ("InternationalSquareMile", "InternationalSquareFoot(value * 27878400).asBaseUnit()"),
            ("InternationalAcre", "InternationalSquareFoot(value * 43560).asBaseUnit()"),
        ]


def generate_international_square_foot() -> int:
    """Generate International square foot and related area units."""
    generator = InternationalAreaGenerator()
    return generator.generate()
