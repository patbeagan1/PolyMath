#!/usr/bin/env python3
"""
UK Imperial unit generation (1824 agreement).
Based on the 1824 British Imperial system.
"""

from .base_distance_generator import BaseDistanceGenerator
from .base_volume_generator import BaseVolumeGenerator


class UKImperial1824DistanceGenerator(BaseDistanceGenerator):
    """Generator for UK Imperial distance units (1824 agreement)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="uk_imperial_1824",
            package_name="com.measures.distance.uk_imp"
        )
    
    def _get_units(self):
        """Get UK Imperial distance units (1824 agreement)."""
        return [
            ("ImperialFoot", "Meter(value * 0.3048)"),  # Same as international
            ("ImperialInch", "ImperialFoot(value / 12).asBaseUnit()"),
            ("ImperialYard", "ImperialFoot(value * 3).asBaseUnit()"),
            ("ImperialMile", "ImperialFoot(value * 5280).asBaseUnit()"),
        ]


def generate_uk_imperial_1824_distance() -> int:
    """Generate UK Imperial distance units (1824 agreement)."""
    generator = UKImperial1824DistanceGenerator()
    return generator.generate()


class UKImperial1824VolumeGenerator(BaseVolumeGenerator):
    """Generator for UK Imperial volume units (1824 agreement)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="uk_imp",
            package_name="com.measures.volume.uk_imp"
        )
    
    def _get_units(self):
        """Get UK Imperial volume units (1824 agreement)."""
        return [
            ("ImperialFluidOunce", "Liter(value * 0.0284130625)"),  # 1/160 imperial gallon
            ("ImperialGill", "ImperialFluidOunce(value * 5).asBaseUnit()"),
            ("ImperialPint", "ImperialFluidOunce(value * 20).asBaseUnit()"),
            ("ImperialQuart", "ImperialPint(value * 2).asBaseUnit()"),
            ("ImperialGallon", "ImperialQuart(value * 4).asBaseUnit()"),
        ]


def generate_uk_imperial_1824_volume() -> int:
    """Generate UK Imperial volume units (1824 agreement)."""
    generator = UKImperial1824VolumeGenerator()
    return generator.generate()

class EnglishImperialDistanceGenerator(BaseDistanceGenerator):
    """Generator for English Imperial distance units (1824 agreement)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="uk_imp",
            package_name="com.measures.distance.uk_imp"
        )
    
    def _get_units(self):
        """Get English Imperial distance units (1824 agreement)."""
        return [
            ("ImperialFoot", "Meter(value * 0.3048)"),
            ("ImperialInch", "ImperialFoot(value / 12).asBaseUnit()"),
            ("ImperialYard", "ImperialFoot(value * 3).asBaseUnit()"),
            ("ImperialMile", "ImperialFoot(value * 5280).asBaseUnit()"),
        ]


def generate_english_imperial() -> int:
    """Generate English Imperial units (1824 agreement)."""
    generator = EnglishImperialDistanceGenerator()
    return generator.generate()


class EnglishInternationalVolumeGenerator(BaseVolumeGenerator):
    """Generator for English International volume units (1824 agreement)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="uk_imp",
            package_name="com.measures.volume.uk_imp"
        )
    
    def _get_units(self):
        """Get English International volume units (1824 agreement)."""
        return [
            ("InternationalGallon", "Liter(value * 4.54609)"),
            ("InternationalQuart", "InternationalGallon(value / 4).asBaseUnit()"),
            ("InternationalPint", "InternationalQuart(value / 2).asBaseUnit()"),
            ("InternationalFluidOunce", "InternationalPint(value / 20).asBaseUnit()"),
        ]


def generate_english_international_volume() -> int:
    """Generate English International volume units (1824 agreement)."""
    generator = EnglishInternationalVolumeGenerator()
    return generator.generate()


class EnglishImperialVolumeGenerator(BaseVolumeGenerator):
    """Generator for English Imperial volume units (1824 agreement)."""
    
    def __init__(self):
        super().__init__(
            subdirectory="uk_imp",
            package_name="com.measures.volume.uk_imp"
        )
    
    def _get_units(self):
        """Get English Imperial volume units (1824 agreement)."""
        return [
            ("ImperialGallon", "Liter(value * 4.54609)"),
            ("ImperialQuart", "ImperialGallon(value / 4).asBaseUnit()"),
            ("ImperialPint", "ImperialQuart(value / 2).asBaseUnit()"),
            ("ImperialFluidOunce", "ImperialPint(value / 20).asBaseUnit()"),
        ]


def generate_english_imperial_volume() -> int:
    """Generate English Imperial volume units (1824 agreement)."""
    generator = EnglishImperialVolumeGenerator()
    return generator.generate()
