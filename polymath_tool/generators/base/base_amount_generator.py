#!/usr/bin/env python3
"""
Base class for amount unit generators.
"""

from abc import ABC, abstractmethod
from typing import List, Tuple
from .common import get_measures_base


class BaseAmountGenerator(ABC):
    """Base class for amount unit generators."""
    
    def __init__(self, subdirectory: str, package_name: str):
        """
        Initialize the base amount generator.
        
        Args:
            subdirectory: The subdirectory within amount/ (e.g., "metric", "customary")
            package_name: The Kotlin package name (e.g., "com.measures.amount.metric")
        """
        self.subdirectory = subdirectory
        self.package_name = package_name
        self.base_dir = get_measures_base() / "amount" / subdirectory
    
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
    
    def get_additional_imports(self) -> List[str]:
        """
        Get additional imports for this generator.
        
        Returns:
            List of additional import statements
        """
        return []
    
    def get_template(self) -> str:
        """
        Get the Kotlin template for amount units.
        
        Returns:
            The template string for generating Kotlin files
        """
        return """package {package_name}

{imports_section}import com.measures.Consts
import com.measures.amount.UnitAmount
import com.measures.amount.Mole
import kotlin.jvm.JvmInline

@JvmInline
value class {unit_name}(override val value: Double) : UnitAmount<{unit_name}> {{
    override fun asType(d: Double) = {unit_name}(d)
    override fun asBaseUnit() = {base_conversion}

    override operator fun plus(other: UnitAmount<*>) = UnitAmount.plusUnit(this, other)
    override operator fun minus(other: UnitAmount<*>) = UnitAmount.minusUnit(this, other)

}}

fun UnitAmount<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""
    
    def generate(self) -> int:
        """
        Generate the amount units.
        
        Returns:
            0 on success
        """
        # Create the directory if it doesn't exist
        self.base_dir.mkdir(parents=True, exist_ok=True)
        
        # Get the units and template
        units = self.get_units()
        template = self.get_template()
        
        # Get additional imports
        additional_imports = self.get_additional_imports()
        if additional_imports:
            imports_section = "\n".join(f"import {imp}" for imp in additional_imports) + "\n"
        else:
            imports_section = ""
        
        # Generate each unit file
        for unit_name, base_conversion in units:
            file_path = self.base_dir / f"{unit_name}.kt"
            content = template.format(
                package_name=self.package_name,
                unit_name=unit_name,
                base_conversion=base_conversion,
                imports_section=imports_section
            )
            file_path.write_text(content)
            print(f"Created: {file_path}")
        
        return 0
