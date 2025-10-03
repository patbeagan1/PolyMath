#!/usr/bin/env python3
"""
Generate individual files for remaining units (angle, misc)
"""

import os
import re
from pathlib import Path

# Base directory
BASE_DIR = Path("/home/patrick/repo/incubator/PolyMath/units-common/src/commonMain/kotlin/com/measures")

def parse_angle_units():
    """Parse angle units from Degree.kt"""
    
    with open(BASE_DIR / "angle" / "Degree.kt", 'r') as f:
        content = f.read()
    
    # Extract package and imports
    lines = content.split('\n')
    package_line = lines[0]
    import_lines = []
    for line in lines[1:10]:  # Look for imports in first 10 lines
        if line.startswith('import '):
            import_lines.append(line)
    
    # Find unit class
    units = []
    
    # Pattern to match unit class
    class_pattern = r'@JvmInline\s+value class (\w+)\(override val value: Double\) : Unit\w+<(\w+)> \{(.*?)\}'
    class_matches = re.findall(class_pattern, content, re.DOTALL)
    
    for class_name, type_param, class_body in class_matches:
        units.append({
            'name': class_name,
            'type_param': type_param,
            'body': class_body.strip()
        })
    
    return units, package_line, import_lines

def parse_misc_units():
    """Parse misc units from Other.kt"""
    
    with open(BASE_DIR / "misc" / "Other.kt", 'r') as f:
        content = f.read()
    
    # Extract package and imports
    lines = content.split('\n')
    package_line = lines[0]
    import_lines = []
    for line in lines[1:15]:  # Look for imports in first 15 lines
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
    
    return units, package_line, import_lines

def generate_angle_files(units, package_line, import_lines):
    """Generate individual files for angle units"""
    
    # Create directory
    output_dir = BASE_DIR / "angle" / "non_si"
    output_dir.mkdir(parents=True, exist_ok=True)
    
    for unit in units:
        class_name = unit['name']
        type_param = unit['type_param']
        body = unit['body']
        
        # Create file content
        file_content = f"""package com.measures.angle.non_si

{chr(10).join(import_lines)}
import com.measures.angle.Radian

@JvmInline
value class {class_name}(override val value: Double) : UnitAngle<{type_param}> {{
{body}
}}

fun UnitAngle<*>.to{class_name}() = toUnit({class_name}(1.0))
"""
        
        # Write file
        file_path = output_dir / f"{class_name}.kt"
        with open(file_path, 'w') as f:
            f.write(file_content)
        
        print(f"Generated: {file_path}")

def generate_misc_files(units, package_line, import_lines):
    """Generate individual files for misc units"""
    
    # Create directory
    output_dir = BASE_DIR / "misc" / "other"
    output_dir.mkdir(parents=True, exist_ok=True)
    
    for unit in units:
        class_name = unit['name']
        type_param = unit['type_param']
        body = unit['body']
        
        # Determine unit type and base unit
        if 'UnitDistance' in body:
            base_unit_import = "import com.measures.distance.Meter"
            unit_type = "Distance"
        elif 'UnitVolume' in body:
            base_unit_import = "import com.measures.volume.Liter"
            unit_type = "Volume"
        else:
            base_unit_import = ""
            unit_type = "Unknown"
        
        # Create file content
        file_content = f"""package com.measures.misc.other

{chr(10).join(import_lines)}
{base_unit_import}

@JvmInline
value class {class_name}(override val value: Double) : Unit{unit_type}<{type_param}> {{
{body}
}}

fun Unit{unit_type}<*>.to{class_name}() = toUnit({class_name}(1.0))
"""
        
        # Write file
        file_path = output_dir / f"{class_name}.kt"
        with open(file_path, 'w') as f:
            f.write(file_content)
        
        print(f"Generated: {file_path}")

def main():
    """Generate individual files for remaining units"""
    
    print("Processing angle units...")
    units, package_line, import_lines = parse_angle_units()
    
    print(f"Found {len(units)} angle units")
    for unit in units:
        print(f"  - {unit['name']}")
    
    print("Generating individual files for angle units...")
    generate_angle_files(units, package_line, import_lines)
    
    print("Angle unit generation complete!\n")
    
    print("Processing misc units...")
    units, package_line, import_lines = parse_misc_units()
    
    print(f"Found {len(units)} misc units")
    for unit in units:
        print(f"  - {unit['name']}")
    
    print("Generating individual files for misc units...")
    generate_misc_files(units, package_line, import_lines)
    
    print("Misc unit generation complete!")

if __name__ == "__main__":
    main()
