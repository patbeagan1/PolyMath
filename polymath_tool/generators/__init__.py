#!/usr/bin/env python3
"""
Unit generation modules for PolyMath.
"""

from .common import get_repo_root, get_measures_base
from .metric_distance import generate_metric_distance
from .metric_volume import generate_metric_volume
from .american_customary import (
    generate_american_customary_distance,
    generate_american_customary_area,
    generate_american_customary_fluid,
    generate_american_customary_dry
)
from .weight_systems import generate_avoirdupois, generate_troy
from .international_weight_systems_1959 import (
    generate_avoirdupois_1959,
    generate_troy_1959,
    generate_apothecaries
)
from .us_survey import (
    generate_us_survey_distance,
    generate_us_survey_area
)
from .international_1959 import (
    generate_us_international_distance,
    generate_us_international_area,
    generate_us_international_fluid_volume,
    generate_us_international_dry_volume
)
from .uk_imperial_pre1824 import (
    generate_uk_imperial_pre1824_distance,
    generate_uk_imperial_pre1824_volume
)
from .uk_imperial_1824 import (
    generate_uk_imperial_1824_distance,
    generate_uk_imperial_1824_volume,
    generate_english_imperial,
    generate_english_international_volume,
    generate_english_imperial_volume
)
from .international_1959 import (
    generate_international_1959_distance,
    generate_international_1959_nautical,
    generate_international_1959_volume,
    generate_us_international_distance,
    generate_us_international_area,
    generate_us_international_fluid_volume,
    generate_us_international_dry_volume
)
from .acceleration import generate_acceleration, generate_metric_acceleration
from .metric_area import generate_metric_area
from .metric_energy import generate_metric_energy
from .metric_force import generate_metric_force
from .metric_power import generate_metric_power
from .metric_pressure import generate_metric_pressure
from .metric_time import generate_metric_time
from .metric_velocity import generate_metric_velocity
from .metric_weight import generate_metric_weight
from .non_si import generate_non_si, generate_remaining

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
    
    
    # Weight systems
    'generate_avoirdupois',
    'generate_troy',
    
    # Weight systems (1959 agreement)
    'generate_avoirdupois_1959',
    'generate_troy_1959',
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
    
    # UK Imperial units (1824)
    'generate_uk_imperial_1824_distance',
    'generate_uk_imperial_1824_volume',
    
    # English Imperial units
    'generate_english_imperial',
    'generate_english_international_volume',
    'generate_english_imperial_volume',
    
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
