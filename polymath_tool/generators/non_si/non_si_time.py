#!/usr/bin/env python3
"""
Non-SI time unit generation.
"""

from .base_time_generator import BaseTimeGenerator


class NonSiTimeGenerator(BaseTimeGenerator):
    """Generator for non-SI time units."""
    
    def __init__(self):
        super().__init__(
            subdirectory="non_si",
            package_name="com.measures.time.non_si"
        )
    
    def _get_units(self):
        """Get non-SI time units."""
        return [
            ("Day", "Second(value * 86400.0)"),
            ("Hour", "Second(value * 3600.0)"),
            ("Minute", "Second(value * 60.0)"),
            ("Week", "Second(value * 604800.0)"),
            ("Year", "Second(value * 31557600.0)"),  # Earth year
            ("MercurianYear", "Second(value * 7600521.6)"),  # Mercury year
            ("VenusianYear", "Second(value * 19414149.12)"),  # Venus year
            ("MartianYear", "Second(value * 59355072.0)"),  # Mars year
            ("JovianYear", "Second(value * 374335776.0)"),  # Jupiter year
            ("SaturnianYear", "Second(value * 929596608.0)"),  # Saturn year
            ("UranianYear", "Second(value * 2651486400.0)"),  # Uranus year
            ("NeptunianYear", "Second(value * 5200416000.0)"),  # Neptune year
        ]


def generate_non_si_time() -> int:
    """Generate non-SI time units."""
    generator = NonSiTimeGenerator()
    return generator.generate()
