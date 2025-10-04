#!/usr/bin/env python3
"""
Base class for distance unit generators.
"""

from abc import ABC, abstractmethod
from typing import List, Tuple
from .common import get_measures_base


class BaseDistanceGenerator(ABC):
    """Base class for distance unit generators."""
    
    def __init__(self, subdirectory: str, package_name: str):
        """
        Initialize the base distance generator.
        
        Args:
            subdirectory: The subdirectory within distance/ (e.g., "metric", "american_customary")
            package_name: The Kotlin package name (e.g., "com.measures.distance.metric")
        """
        self.subdirectory = subdirectory
        self.package_name = package_name
        self.base_dir = get_measures_base() / "distance" / subdirectory
    
    def get_units(self) -> List[Tuple[str, str]]:
        """
        Get the list of units to generate.
        
        Returns:
            List of tuples containing (unit_name, base_conversion)
        """
        return self._get_units()
    
    @abstractmethod
    def _get_units(self) -> List[Tuple[str, str]]:
        """
        Abstract method to define the units for this generator.
        
        Returns:
            List of tuples containing (unit_name, base_conversion)
        """
    
    def get_template(self) -> str:
        """
        Get the Kotlin template for distance units.
        
        Returns:
            The template string for generating Kotlin files
        """
        return """package {package_name}

import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import com.measures.distance.Meter
import com.measures.time.UnitTime
import com.measures.velocity.MetersPerSecond
import kotlin.jvm.JvmInline

@JvmInline
value class {unit_name}(override val value: Double) : UnitDistance<{unit_name}> {{
    override fun asType(d: Double) = {unit_name}(d)
    override fun asBaseUnit() = {base_conversion}

    override operator fun plus(other: UnitDistance<*>) = UnitDistance.plusUnit(this, other)
    override operator fun minus(other: UnitDistance<*>) = UnitDistance.minusUnit(this, other)
    override operator fun times(other: UnitDistance<*>) = UnitDistance.timesUnit(this, other)
    override operator fun times(other: UnitArea<*>) = UnitDistance.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>): MetersPerSecond = UnitDistance.divUnit(this, other)
}}

fun UnitDistance<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""
    
    def generate(self) -> int:
        """
        Generate the distance units.
        
        Returns:
            0 on success
        """
        # Create the directory if it doesn't exist
        self.base_dir.mkdir(parents=True, exist_ok=True)
        
        # Get the units and template
        units = self.get_units()
        template = self.get_template()
        
        # Generate each unit file
        for unit_name, base_conversion in units:
            file_path = self.base_dir / f"{unit_name}.kt"
            content = template.format(
                package_name=self.package_name,
                unit_name=unit_name,
                base_conversion=base_conversion
            )
            file_path.write_text(content)
            print(f"Created: {file_path}")
        
        return 0
