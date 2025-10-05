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
from .us_customary import (
    generate_us_customary_area
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
from .international_1959_distance import (
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
from .international_1959_distance import (
    generate_international_1959_distance,
    generate_international_1959_nautical,
    generate_international_1959_volume,
    generate_us_international_distance,
    generate_us_international_area,
    generate_us_international_fluid_volume,
    generate_us_international_dry_volume
)
from .international_1959_area import (
    generate_international_square_foot
)
from .metric_charge import (
    generate_charge
)
from .metric_current import (
    generate_current
)
from .planetary_acceleration import generate_acceleration, generate_metric_acceleration, generate_g_force
from .g_force_acceleration import generate_g_force_acceleration
from .non_si_acceleration import generate_non_si_acceleration
from .uk_imp_distance import generate_uk_imp_distance
from .us_international_1959_distance import generate_us_international_1959_distance
from .us_survey_1959_distance import generate_us_survey_1959_distance
from .uk_imp_volume import generate_uk_imp_volume
from .us_international_dry_1959_volume import generate_us_international_dry_1959_volume
from .us_international_fluid_1959_volume import generate_us_international_fluid_1959_volume
from .us_international_1959_area import generate_us_international_1959_area
from .us_survey_1959_area import generate_us_survey_1959_area
from .non_si_charge import generate_non_si_charge
from .international_area import generate_international_area
from .non_si_energy import generate_non_si_energy
from .non_si_force import generate_non_si_force
from .non_si_pressure import generate_non_si_pressure
from .non_si_distance import generate_non_si_distance
from .non_si_volume import generate_non_si_volume
from .non_si_power import generate_non_si_power
from .non_si_time import generate_non_si_time
from .astronomical_distance import generate_astronomical_distance
from .non_si_volume_system import generate_non_si_volume_system
from .non_si_weight import generate_non_si_weight
from .international_1959_distance import generate_international_1959_distance
from .international_1959_area import generate_international_1959_area
from .international_1959_volume import generate_international_1959_volume
from .uk_imperial_1824_distance import generate_uk_imperial_1824_distance
from .uk_imperial_1824_area import generate_uk_imperial_1824_area
from .uk_imperial_1824_volume import generate_uk_imperial_1824_volume
from .us_customary_1832_distance import generate_us_customary_1832_distance
from .us_customary_1832_area import generate_us_customary_1832_area
from .us_customary_1832_volume import generate_us_customary_1832_volume
from .us_survey_1893_distance import generate_us_survey_1893_distance
from .us_survey_1893_area import generate_us_survey_1893_area
from .us_survey_1893_volume import generate_us_survey_1893_volume
from .astronomical_distance import generate_astronomical_distance
from .astronomical_area import generate_astronomical_area
from .astronomical_volume import generate_astronomical_volume
from .other_distance import generate_other_distance
from .other_area import generate_other_area
from .other_volume import generate_other_volume
from .metric_area import generate_metric_area
from .metric_energy import generate_metric_energy
from .metric_force import generate_metric_force
from .metric_power import generate_metric_power
from .metric_pressure import generate_metric_pressure
from .metric_time import generate_metric_time
from .metric_velocity import generate_metric_velocity
from .metric_weight import generate_metric_weight
from .metric_amount import generate_metric_amount
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
    'generate_metric_amount',
    'generate_metric_acceleration',
    'generate_g_force',
    
    # American Customary units
    'generate_american_customary_distance',
    'generate_american_customary_area',
    'generate_american_customary_fluid',
    'generate_american_customary_dry',
    'generate_us_customary_area',
    
    
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
    'generate_international_square_foot',
    'generate_charge',
    'generate_current',
    
    
    # Acceleration
    'generate_acceleration',
    'generate_g_force_acceleration',
    'generate_non_si_acceleration',
    'generate_uk_imp_distance',
    'generate_us_international_1959_distance',
    'generate_us_survey_1959_distance',
    'generate_uk_imp_volume',
    'generate_us_international_dry_1959_volume',
    'generate_us_international_fluid_1959_volume',
    'generate_us_international_1959_area',
    'generate_us_survey_1959_area',
    'generate_non_si_charge',
    'generate_international_area',
    'generate_non_si_energy',
    'generate_non_si_force',
    'generate_non_si_pressure',
    'generate_non_si_distance',
    'generate_non_si_volume',
    'generate_non_si_power',
    'generate_non_si_time',
    'generate_astronomical_distance',
    'generate_non_si_volume_system',
    'generate_non_si_weight',
    'generate_international_1959_distance',
    'generate_international_1959_area',
    'generate_international_1959_volume',
    'generate_uk_imperial_1824_distance',
    'generate_uk_imperial_1824_area',
    'generate_uk_imperial_1824_volume',
    'generate_us_customary_1832_distance',
    'generate_us_customary_1832_area',
    'generate_us_customary_1832_volume',
    'generate_us_survey_1893_distance',
    'generate_us_survey_1893_area',
    'generate_us_survey_1893_volume',
    'generate_astronomical_distance',
    'generate_astronomical_area',
    'generate_astronomical_volume',
    'generate_other_distance',
    'generate_other_area',
    'generate_other_volume',
    
    # Non-SI and remaining
    'generate_non_si',
    'generate_remaining'
]
