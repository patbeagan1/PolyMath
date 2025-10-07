#!/usr/bin/env python3
"""
Consolidated unit generation logic for PolyMath.
All generation families are defined here with their templates and unit data.
"""

# Import all generation functions from the individual modules
from .generators import (
    # Common utilities
    get_repo_root,
    get_measures_base,
    
    # Metric units
    generate_metric_distance,
    generate_metric_volume,
    generate_metric_area,
    generate_metric_energy,
    generate_metric_force,
    generate_metric_power,
    generate_metric_pressure,
    generate_metric_time,
    generate_metric_velocity,
    generate_metric_weight,
    generate_metric_acceleration,
    
    # American Customary units
    generate_american_customary_distance,
    generate_american_customary_area,
    generate_american_customary_fluid,
    generate_american_customary_dry,
    
    # English Imperial units
    generate_english_imperial,
    generate_english_international_volume,
    generate_english_imperial_volume,
    
    # Weight systems
    generate_avoirdupois,
    generate_troy,
    
    # Corrected weight systems
    generate_avoirdupois_corrected,
    generate_troy_corrected,
    generate_apothecaries,
    
    # US Survey units (deprecated)
    generate_us_survey_distance,
    generate_us_survey_area,
    
    # US International units (current)
    generate_us_international_distance,
    generate_us_international_area,
    generate_us_international_fluid_volume,
    generate_us_international_dry_volume,
    
    # UK Imperial units (pre-1824)
    generate_uk_imperial_pre1824_distance,
    generate_uk_imperial_pre1824_volume,
    
    # UK Imperial units (post-1824)
    generate_uk_imperial_post1824_distance,
    generate_uk_imperial_post1824_volume,
    
    # International 1959 agreement
    generate_international_1959_distance,
    generate_international_1959_nautical,
    generate_international_1959_volume,
    
    # Acceleration
    generate_acceleration,
    
    # Non-SI and remaining
    generate_non_si,
    generate_remaining
)

# Re-export all functions for backward compatibility
__all__ = [
    # Common utilities
    'get_repo_root',
    'get_measures_base',
    
    # Metric units
    'generate_metric_distance',
    'generate_metric_volume',
    'generate_metric_area',
    'generate_metric_energy',
    'generate_metric_force',
    'generate_metric_power',
    'generate_metric_pressure',
    'generate_metric_time',
    'generate_metric_velocity',
    'generate_metric_weight',
    'generate_metric_acceleration',
    
    # American Customary units
    'generate_american_customary_distance',
    'generate_american_customary_area',
    'generate_american_customary_fluid',
    'generate_american_customary_dry',
    
    # English Imperial units
    'generate_english_imperial',
    'generate_english_international_volume',
    'generate_english_imperial_volume',
    
    # Weight systems
    'generate_avoirdupois',
    'generate_troy',
    
    # Corrected weight systems
    'generate_avoirdupois_corrected',
    'generate_troy_corrected',
    'generate_apothecaries',
    
    # US Survey units (deprecated)
    'generate_us_survey_distance',
    'generate_us_survey_area',
    
    # US International units (current)
    'generate_us_international_distance',
    'generate_us_international_area',
    'generate_us_international_fluid_volume',
    'generate_us_international_dry_volume',
    
    # UK Imperial units (pre-1824)
    'generate_uk_imperial_pre1824_distance',
    'generate_uk_imperial_pre1824_volume',
    
    # UK Imperial units (post-1824)
    'generate_uk_imperial_post1824_distance',
    'generate_uk_imperial_post1824_volume',
    
    # International 1959 agreement
    'generate_international_1959_distance',
    'generate_international_1959_nautical',
    'generate_international_1959_volume',
    
    # Acceleration
    'generate_acceleration',
    
    # Non-SI and remaining
    'generate_non_si',
    'generate_remaining'
]
