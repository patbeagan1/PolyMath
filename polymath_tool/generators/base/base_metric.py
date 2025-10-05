"""
Base metric unit generation utilities.
"""

from typing import List, Tuple


class MetricUnitGenerator:
    """Generator for metric units using SI prefixes."""

    def __init__(self):
        """Initialize the metric unit generator."""
        self.prefixes = [
            "Atto",  # 10^-18
            "Femto",  # 10^-15
            "Pico",  # 10^-12
            "Nano",  # 10^-9
            "Micro",  # 10^-6
            "Milli",  # 10^-3
            "Centi",  # 10^-2
            "Deci",  # 10^-1
            "Deka",  # 10^1
            "Hecto",  # 10^2
            "Kilo",  # 10^3
            "Mega",  # 10^6
            "Giga",  # 10^9
            "Tera",  # 10^12
            "Peta",  # 10^15
            "Exa",  # 10^18
            "Zetta",  # 10^21
            "Yotta",  # 10^24
        ]

    def generate_units(self, base_type: str, result_type: str) -> List[Tuple[str, str]]:
        """
        Generate metric units using a predefined list of metric prefixes and a base type.

        Args:
            base_type: The base unit type (e.g., "meterSquared")
            result_type: The result type for conversion (e.g., "SquareMeter")

        Returns:
            List of tuples containing (UnitName, ConversionString)
        """
        return [
            (
                f"{prefix}{base_type}",
                f"{result_type}(value * Consts.{prefix.upper()})",
            )
            for prefix in self.prefixes
        ]
