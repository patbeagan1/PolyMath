#!/usr/bin/env python3
"""
Non-SI distance unit generation.
"""

from ..base.base_distance_generator import BaseDistanceGenerator
from ..base.base_area_generator import BaseAreaGenerator
from ..base.base_volume_generator import BaseVolumeGenerator


class NonSiDistanceGenerator(BaseDistanceGenerator):
    """Generator for non-SI distance units."""

    def __init__(self):
        super().__init__(
            subdirectory="non_si", package_name="com.measures.distance.non_si"
        )

    def _get_units(self):
        """Get non-SI distance units."""
        return [
            ("Angstroms", "Meter(value * 1e-10)"),
            ("Microns", "Meter(value * 1e-6)"),
            ("Mils", "Meter(value * 0.0000254)"),
        ]


class InternationalYardDistanceGenerator(BaseDistanceGenerator):
    """Generator for international yard distance units."""

    def __init__(self):
        super().__init__(
            subdirectory="international_yard",
            package_name="com.measures.distance.international_yard",
        )

    def _get_units(self):
        """Get international yard distance units."""
        return [
            ("Yard", "Meter(value * 0.9144)"),
            ("Inch", "Yard(value / 36).asBaseUnit()"),
            ("Foot", "Yard(value / 3).asBaseUnit()"),
            ("Mile", "Yard(value * 1760).asBaseUnit()"),
            ("Fathom", "Yard(value * 2).asBaseUnit()"),
            ("NauticalMile", "Yard(value * 2025.3718285).asBaseUnit()"),
        ]

class InternationalYardAreaGenerator(BaseAreaGenerator):
    """Generator for international yard area units."""

    def __init__(self):
        super().__init__(
            subdirectory="international_yard",
            package_name="com.measures.area.international_yard",
        )

    def _get_units(self):
        """Get international yard area units (squared distance units)."""
        return [
            ("SquareInch", "SquareYard(value / 1296).asBaseUnit()"),
            ("SquareFoot", "SquareYard(value / 9).asBaseUnit()"),
            ("SquareYard", "SquareMeter(0.83612736)"),
            ("SquareMile", "SquareYard(value * (1760*1760)).asBaseUnit()"),
        ]


class InternationalYardVolumeGenerator(BaseVolumeGenerator):
    """Generator for international yard volume units."""

    def __init__(self):
        super().__init__(
            subdirectory="international_yard",
            package_name="com.measures.volume.international_yard",
        )

    def _get_units(self):
        # Derived volume units (cube of distance units)
        """Get international yard volume units (cubed distance units)."""
        return [
            ("CubicInch", "CubicMeter(value / 1000 * 16.387064)"),  # value in yd^3
            ("CubicFoot", "CubicInch(value * 1728).asBaseUnit()"),
            ("CubicYard", "CubicFoot(value * 27).asBaseUnit()"),
        ]
