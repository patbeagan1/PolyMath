#!/usr/bin/env python3
"""
American Customary unit generation.
"""

from .base_distance_generator import BaseDistanceGenerator
from .base_area_generator import BaseAreaGenerator
from .base_volume_generator import BaseVolumeGenerator


class AmericanCustomaryDistanceGenerator(BaseDistanceGenerator):
    """Generator for American Customary distance units."""
    
    def __init__(self):
        super().__init__(
            subdirectory="american_customary",
            package_name="com.measures.distance.american_customary"
        )
    
    def _get_units(self):
        """Get American Customary distance units."""
        return [
            ("Foot", "Meter(value * 0.3048)"),
            ("Inch", "Foot(value / 12).asBaseUnit()"),
            ("Yard", "Foot(value * 3).asBaseUnit()"),
            ("Mile", "Foot(value * 5280).asBaseUnit()"),
        ]


def generate_american_customary_distance() -> int:
    """Generate American Customary distance units."""
    generator = AmericanCustomaryDistanceGenerator()
    return generator.generate()


class AmericanCustomaryAreaGenerator(BaseAreaGenerator):
    """Generator for American Customary area units."""
    
    def __init__(self):
        super().__init__(
            subdirectory="american_customary",
            package_name="com.measures.area.american_customary"
        )
    
    def _get_units(self):
        """Get American Customary area units."""
        return [
            ("Acre", "SquareFoot(value * 43560).asBaseUnit()"),
            ("SquareFoot", "SquareMeter(value * 0.09290304)"),
        ]


def generate_american_customary_area() -> int:
    """Generate American Customary area units."""
    generator = AmericanCustomaryAreaGenerator()
    return generator.generate()


class AmericanCustomaryFluidVolumeGenerator(BaseVolumeGenerator):
    """Generator for American Customary fluid volume units."""
    
    def __init__(self):
        super().__init__(
            subdirectory="american_customary_fluid",
            package_name="com.measures.volume.american_customary_fluid"
        )
    
    def _get_units(self):
        """Get American Customary fluid volume units."""
        return [
            ("USFluidGallon", "Liter(value * 3.785411784)"),
            ("USFluidQuart", "USFluidGallon(value / 4).asBaseUnit()"),
            ("USFluidPint", "USFluidQuart(value / 2).asBaseUnit()"),
            ("USCup", "USFluidPint(value / 2).asBaseUnit()"),
            ("USFluidOunce", "USCup(value / 8).asBaseUnit()"),
        ]


def generate_american_customary_fluid() -> int:
    """Generate American Customary fluid volume units."""
    generator = AmericanCustomaryFluidVolumeGenerator()
    return generator.generate()


class AmericanCustomaryDryVolumeGenerator(BaseVolumeGenerator):
    """Generator for American Customary dry volume units."""
    
    def __init__(self):
        super().__init__(
            subdirectory="american_customary_dry",
            package_name="com.measures.volume.american_customary_dry"
        )
    
    def _get_units(self):
        """Get American Customary dry volume units."""
        return [
            ("USDryGallon", "Liter(value * 4.40488377086)"),
            ("USDryQuart", "USDryGallon(value / 4).asBaseUnit()"),
            ("USDryPint", "USDryQuart(value / 2).asBaseUnit()"),
            ("USDryBarrel", "USDryGallon(value * 26.25).asBaseUnit()"),
        ]


def generate_american_customary_dry() -> int:
    """Generate American Customary dry volume units."""
    generator = AmericanCustomaryDryVolumeGenerator()
    return generator.generate()
