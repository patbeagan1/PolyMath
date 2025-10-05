"""
Base metric unit generation utilities for area calculations.
"""


def generate_metric_units(base_type, result_type):
    """
    Generate metric units using a predefined list of metric prefixes and a base type.
    Each entry is a tuple: (UnitName, ConversionString)
    """
    prefixes = [
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
    return [
        (
            f"{prefix}{base_type}",
            f"{result_type}(value * Consts.{prefix.upper()} * Consts.{prefix.upper()})",
        )
        for prefix in prefixes
    ]
