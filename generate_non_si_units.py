#!/usr/bin/env python3
"""
Generate individual files for NonSI units
"""

import os
import re
from pathlib import Path

# Base directory
BASE_DIR = Path("/home/patrick/repo/incubator/PolyMath/units-common/src/commonMain/kotlin/com/measures")

def parse_non_si_units(file_path, unit_type):
    """Parse NonSI units from a consolidated file"""
    
    with open(file_path, 'r') as f:
        content = f.read()
    
    # Extract package and imports
    lines = content.split('\n')
    package_line = lines[0]
    import_lines = []
    for line in lines[1:20]:  # Look for imports in first 20 lines
        if line.startswith('import '):
            import_lines.append(line)
    
    # Find all unit classes
    units = []
    
    # Pattern to match unit classes
    class_pattern = r'@JvmInline\s+value class (\w+)\(override val value: Double\) : Unit\w+<(\w+)> \{(.*?)\}'
    class_matches = re.findall(class_pattern, content, re.DOTALL)
    
    for class_name, type_param, class_body in class_matches:
        units.append({
            'name': class_name,
            'type_param': type_param,
            'body': class_body.strip()
        })
    
    return units, package_line, import_lines, unit_type

def generate_individual_files(units, package_line, import_lines, unit_type):
    """Generate individual files for each unit"""
    
    # Create directory
    output_dir = BASE_DIR / unit_type / "non_si"
    output_dir.mkdir(parents=True, exist_ok=True)
    
    for unit in units:
        class_name = unit['name']
        type_param = unit['type_param']
        body = unit['body']
        
        # Determine the base unit import based on unit type
        base_unit_import = ""
        if unit_type == "time":
            base_unit_import = "import com.measures.time.Second"
        elif unit_type == "energy":
            base_unit_import = "import com.measures.energy.Joule"
        elif unit_type == "force":
            base_unit_import = "import com.measures.force.Newton"
        elif unit_type == "current":
            base_unit_import = "import com.measures.current.Ampere"
        elif unit_type == "charge":
            base_unit_import = "import com.measures.charge.Coulomb"
        elif unit_type == "potential":
            base_unit_import = "import com.measures.potential.Volt"
        elif unit_type == "power":
            base_unit_import = "import com.measures.power.Watt"
        elif unit_type == "pressure":
            base_unit_import = "import com.measures.pressure.Pascal"
        elif unit_type == "temperature":
            base_unit_import = "import com.measures.temperature.Kelvin"
        elif unit_type == "velocity":
            base_unit_import = "import com.measures.velocity.MetersPerSecond"
        elif unit_type == "luminous":
            base_unit_import = "import com.measures.luminous.Candela"
        elif unit_type == "amount":
            base_unit_import = "import com.measures.amount.Mole"
        elif unit_type == "weight":
            base_unit_import = "import com.measures.weight.Gram"
        
        # Create file content
        file_content = f"""package com.measures.{unit_type}.non_si

{chr(10).join(import_lines)}
{base_unit_import}

@JvmInline
value class {class_name}(override val value: Double) : Unit{unit_type.capitalize()}<{type_param}> {{
{body}
}}

fun Unit{unit_type.capitalize()}<*>.to{class_name}() = toUnit({class_name}(1.0))
"""
        
        # Write file
        file_path = output_dir / f"{class_name}.kt"
        with open(file_path, 'w') as f:
            f.write(file_content)
        
        print(f"Generated: {file_path}")

def main():
    """Generate individual files for all NonSI units"""
    
    # Define NonSI files to process
    non_si_files = [
        ("time", "NonSITime.kt"),
        ("energy", "NonSIEnergy.kt"),
        ("force", "NonSIForce.kt"),
        ("current", "NonSICurrent.kt"),
        ("charge", "NonSICharge.kt"),
        ("potential", "NonSIPotential.kt"),
        ("power", "NonSIPower.kt"),
        ("pressure", "NonSIPressure.kt"),
        ("temperature", "NonSITemperature.kt"),
        ("velocity", "NonSIVelocity.kt"),
        ("luminous", "NonSILuminous.kt"),
        ("amount", "NonSIAmount.kt"),
        ("weight", "NonSIWeight.kt")
    ]
    
    for unit_type, filename in non_si_files:
        file_path = BASE_DIR / unit_type / filename
        if file_path.exists():
            print(f"Processing {unit_type} units from {filename}...")
            units, package_line, import_lines, unit_type = parse_non_si_units(file_path, unit_type)
            
            print(f"Found {len(units)} units")
            for unit in units:
                print(f"  - {unit['name']}")
            
            print(f"Generating individual files for {unit_type}...")
            generate_individual_files(units, package_line, import_lines, unit_type)
            
            print(f"{unit_type} unit generation complete!\n")
        else:
            print(f"File not found: {file_path}")

if __name__ == "__main__":
    main()
