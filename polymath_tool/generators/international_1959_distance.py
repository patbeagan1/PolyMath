#!/usr/bin/env python3
"""
International unit generation (1959 agreement).
Based on the 1959 International Yard and Pound Agreement.
"""

from .base_distance_generator import BaseDistanceGenerator
from .base_area_generator import BaseAreaGenerator
from .base_volume_generator import BaseVolumeGenerator


class USInternationalDistanceGenerator(BaseDistanceGenerator):
    """Generator for US International distance units (1959 standard)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="us_international_1959",
            package_name="com.measures.distance.us_international_1959"
        )
    
    def _get_units(self):
        """Get US International distance units (1959 standard)."""
        return [
            ("InternationalFoot", "Meter(value * 0.3048)"),  # 1959 agreement
            ("InternationalInch", "InternationalFoot(value / 12).asBaseUnit()"),
            ("InternationalYard", "InternationalFoot(value * 3).asBaseUnit()"),
            ("InternationalMile", "InternationalFoot(value * 5280).asBaseUnit()"),
        ]


def generate_us_international_distance() -> int:
    """Generate US International distance units (1959 standard)."""
    generator = USInternationalDistanceGenerator()
    return generator.generate()


class USInternationalAreaGenerator(BaseAreaGenerator):
    """Generator for US International area units (1959)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="us_international_1959",
            package_name="com.measures.area.us_international_1959"
        )
    
    def _get_units(self):
        """Get US International area units (1959)."""
        return [
            ("InternationalSquareFoot", "SquareMeter(value * 0.09290304)"),
            ("InternationalSquareYard", "InternationalSquareFoot(value * 9).asBaseUnit()"),
            ("InternationalAcre", "InternationalSquareFoot(value * 43560).asBaseUnit()"),
        ]


def generate_us_international_area() -> int:
    """Generate US International area units (1959)."""
    generator = USInternationalAreaGenerator()
    return generator.generate()


class USInternationalFluidVolumeGenerator(BaseVolumeGenerator):
    """Generator for US International fluid volume units (1959)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="us_international_fluid_1959",
            package_name="com.measures.volume.us_international_fluid_1959"
        )
    
    def _get_units(self):
        """Get US International fluid volume units (1959)."""
        return [
            ("USFluidOunce", "Liter(value * 0.0295735295625)"),  # 1/128 US gallon
            ("USCup", "USFluidOunce(value * 8).asBaseUnit()"),
            ("USPint", "USFluidOunce(value * 16).asBaseUnit()"),
            ("USQuart", "USPint(value * 2).asBaseUnit()"),
            ("USGallon", "USQuart(value * 4).asBaseUnit()"),
        ]


def generate_us_international_fluid_volume() -> int:
    """Generate US International fluid volume units (1959)."""
    generator = USInternationalFluidVolumeGenerator()
    return generator.generate()


class USInternationalDryVolumeGenerator(BaseVolumeGenerator):
    """Generator for US International dry volume units (1959)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="us_international_dry_1959",
            package_name="com.measures.volume.us_international_dry_1959"
        )
    
    def _get_units(self):
        """Get US International dry volume units (1959)."""
        return [
            ("USDryPint", "Liter(value * 0.5506104713575)"),  # 33.6003125 cu in
            ("USDryQuart", "USDryPint(value * 2).asBaseUnit()"),
            ("USDryGallon", "USDryQuart(value * 4).asBaseUnit()"),
            ("USPeck", "USDryGallon(value * 2).asBaseUnit()"),
            ("USBushel", "USPeck(value * 4).asBaseUnit()"),
        ]


def generate_us_international_dry_volume() -> int:
    """Generate US International dry volume units (1959)."""
    generator = USInternationalDryVolumeGenerator()
    return generator.generate()

class International1959DistanceGenerator(BaseDistanceGenerator):
    """Generator for International distance units (1959 agreement)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="international_1959",
            package_name="com.measures.distance.international_1959"
        )
    
    def _get_units(self):
        """Get International distance units (1959 agreement)."""
        return [
            ("InternationalFoot1959", "Meter(value * 0.3048)"),  # 1959 agreement
            ("InternationalInch1959", "InternationalFoot1959(value / 12).asBaseUnit()"),
            ("InternationalYard1959", "InternationalFoot1959(value * 3).asBaseUnit()"),
            ("InternationalMile1959", "InternationalFoot1959(value * 5280).asBaseUnit()"),
        ]


def generate_international_1959_distance() -> int:
    """Generate International distance units (1959 agreement)."""
    generator = International1959DistanceGenerator()
    return generator.generate()


class International1959NauticalDistanceGenerator(BaseDistanceGenerator):
    """Generator for International nautical distance units (1959 agreement)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="international_1959_nautical",
            package_name="com.measures.distance.international_1959_nautical"
        )
    
    def _get_units(self):
        """Get International nautical distance units (1959 agreement)."""
        return [
            ("Fathom", "Meter(value * 1.8288)"),  # 2 yards
            ("Cable", "Meter(value * 219.456)"),  # 120 fathoms
            ("NauticalMile", "Meter(value * 1852)"),  # 1.151 statute miles
        ]


def generate_international_1959_nautical() -> int:
    """Generate International nautical units (1959 agreement)."""
    generator = International1959NauticalDistanceGenerator()
    return generator.generate()


class International1959VolumeGenerator(BaseVolumeGenerator):
    """Generator for International volume units (1959 agreement)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="international_1959",
            package_name="com.measures.volume.international_1959"
        )
    
    def _get_units(self):
        """Get International volume units (1959 agreement)."""
        return [
            ("InternationalFluidOunce1959", "Liter(value * 0.0295735295625)"),  # US fluid ounce
            ("InternationalCup1959", "InternationalFluidOunce1959(value * 8).asBaseUnit()"),
            ("InternationalPint1959", "InternationalFluidOunce1959(value * 16).asBaseUnit()"),
            ("InternationalQuart1959", "InternationalPint1959(value * 2).asBaseUnit()"),
            ("InternationalGallon1959", "InternationalQuart1959(value * 4).asBaseUnit()"),
        ]


def generate_international_1959_volume() -> int:
    """Generate International volume units (1959 agreement)."""
    generator = International1959VolumeGenerator()
    return generator.generate()
