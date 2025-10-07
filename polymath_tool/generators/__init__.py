#!/usr/bin/env python3
"""
Unit generation modules for PolyMath.
"""

from .common import get_repo_root, get_measures_base
from .metric.metric_distance import MetricDistanceGenerator
from .metric.metric_volume import MetricVolumeGenerator
from .metric.metric_area import MetricAreaGenerator
from .metric.metric_energy import MetricEnergyGenerator
from .metric.metric_force import MetricForceGenerator
from .metric.metric_power import MetricPowerGenerator
from .metric.metric_pressure import MetricPressureGenerator
from .metric.metric_time import MetricTimeGenerator
from .metric.metric_velocity import MetricVelocityGenerator
from .metric.metric_weight import MetricWeightGenerator
from .metric.metric_amount import MetricAmountGenerator
from .metric.metric_acceleration import MetricAccelerationGenerator
from .metric.metric_charge import MetricChargeGenerator
from .metric.metric_current import MetricCurrentGenerator
# Non-SI generators
from .non_si.non_si_volume import NonSiVolumeGenerator
from .non_si.non_si_weight import NonSiWeightGenerator
from .non_si.non_si_distance import NonSiDistanceGenerator
from .non_si.non_si_force import NonSiForceGenerator
from .non_si.non_si_energy import NonSiEnergyGenerator
from .non_si.non_si_pressure import NonSiPressureGenerator
from .non_si.non_si_power import NonSiPowerGenerator
from .non_si.non_si_time import NonSiTimeGenerator
from .non_si.non_si_charge import NonSiChargeGenerator
from .non_si.non_si_acceleration import NonSiAccelerationGenerator

# International yard and pound generators
from .non_si.non_si_distance import InternationalYardDistanceGenerator
from .non_si.non_si_distance import InternationalYardAreaGenerator
from .non_si.non_si_distance import InternationalYardVolumeGenerator

# Astronomical generators
from .astronomical.astronomical import AstronomicalDistanceGenerator, GForceAccelerationGenerator

# US Customary generators
from .non_si.us_customary import (
    UsDistanceGenerator,
    UsAreaGenerator,
    UsFluidVolumeGenerator,
    UsDryVolumeGenerator
)

# UK Imperial generators
from .non_si.uk_imperial import (
    UkDistanceGenerator,
    UkAreaGenerator,
    UkVolumeGenerator
)

__all__ = [
    # Common utilities
    'get_repo_root',
    'get_measures_base',
    
    # Metric generators
    'MetricDistanceGenerator',
    'MetricAccelerationGenerator',
    'MetricVolumeGenerator',
    'MetricAreaGenerator',
    'MetricEnergyGenerator',
    'MetricForceGenerator',
    'MetricPowerGenerator',
    'MetricPressureGenerator',
    'MetricTimeGenerator',
    'MetricVelocityGenerator',
    'MetricWeightGenerator',
    'MetricAmountGenerator',
    'MetricChargeGenerator',
    'MetricCurrentGenerator',
    
    # Non-SI generators
    'NonSiVolumeGenerator',
    'NonSiWeightGenerator',
    'NonSiDistanceGenerator',
    'NonSiForceGenerator',
    'NonSiEnergyGenerator',
    'NonSiPressureGenerator',
    'NonSiPowerGenerator',
    'NonSiTimeGenerator',
    'NonSiChargeGenerator',
    'NonSiAccelerationGenerator',

    # International yard and pound generators
    'InternationalYardDistanceGenerator',
    'InternationalYardAreaGenerator',
    'InternationalYardVolumeGenerator',

    # Astronomical generators
    'AstronomicalDistanceGenerator',
    'GForceAccelerationGenerator',
    
    # US Customary generators
    'UsDistanceGenerator',
    'UsAreaGenerator',
    'UsFluidVolumeGenerator',
    'UsDryVolumeGenerator',
    
    # UK Imperial generators
    'UkDistanceGenerator',
    'UkAreaGenerator',
    'UkVolumeGenerator',
]
