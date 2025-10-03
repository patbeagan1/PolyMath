#!/usr/bin/env python3
"""
Generate individual files for English Imperial volume units
"""

import os
import re
from pathlib import Path

# Base directory
BASE_DIR = Path("/home/patrick/repo/incubator/PolyMath/units-common/src/commonMain/kotlin/com/measures/volume")

def parse_english_imperial_volume_units():
    """Parse English Imperial volume units from the consolidated file"""
    
    with open(BASE_DIR / "EnglishImperial.kt", 'r') as f:
        content = f.read()
    
    # Extract package and imports
    lines = content.split('\n')
    package_line = lines[0]
    import_lines = []
    for line in lines[1:10]:  # Look for imports in first 10 lines
        if line.startswith('import '):
            import_lines.append(line)
    
    # Find all unit classes and their extension functions
    units = []
    
    # Pattern to match extension functions
    extension_pattern = r'fun UnitVolume<.*?>\.to(\w+)\(\) = .*?'
    extension_matches = re.findall(extension_pattern, content)
    
    # Pattern to match unit classes
    class_pattern = r'@JvmInline\s+value class (\w+)\(override val value: Double\) : UnitVolume<(\w+)> \{(.*?)\}'
    class_matches = re.findall(class_pattern, content, re.DOTALL)
    
    for class_name, type_param, class_body in class_matches:
        # Find the corresponding extension function
        extension_func = None
        for ext in extension_matches:
            if ext == class_name:
                extension_func = ext
                break
        
        if extension_func:
            units.append({
                'name': class_name,
                'type_param': type_param,
                'body': class_body.strip(),
                'extension_func': extension_func
            })
    
    return units, package_line, import_lines

def generate_individual_files(units, package_line, import_lines):
    """Generate individual files for each unit"""
    
    # Create directory
    output_dir = BASE_DIR / "english_imperial"
    output_dir.mkdir(exist_ok=True)
    
    for unit in units:
        class_name = unit['name']
        type_param = unit['type_param']
        body = unit['body']
        extension_func = unit['extension_func']
        
        # Create file content
        file_content = f"""package com.measures.volume.english_imperial

{chr(10).join(import_lines)}

@JvmInline
value class {class_name}(override val value: Double) : UnitVolume<{type_param}> {{
{body}
}}

fun UnitVolume<*>.to{class_name}() = toUnit({class_name}(1.0))
"""
        
        # Write file
        file_path = output_dir / f"{class_name}.kt"
        with open(file_path, 'w') as f:
            f.write(file_content)
        
        print(f"Generated: {file_path}")

def main():
    """Generate individual files for English Imperial volume units"""
    print("Parsing English Imperial volume units...")
    units, package_line, import_lines = parse_english_imperial_volume_units()
    
    print(f"Found {len(units)} units")
    for unit in units:
        print(f"  - {unit['name']}")
    
    print("Generating individual files...")
    generate_individual_files(units, package_line, import_lines)
    
    print("English Imperial volume unit generation complete!")

if __name__ == "__main__":
    main()
