#!/usr/bin/env python3
"""
Fix all remaining import issues in the newly generated files
"""

import os
from pathlib import Path

# Base directory
BASE_DIR = Path("/home/patrick/repo/incubator/PolyMath/units-common/src/commonMain/kotlin/com/measures")

def fix_imports_in_file(file_path):
    """Fix imports in a single file"""
    
    with open(file_path, 'r') as f:
        content = f.read()
    
    # Determine what imports are needed based on the file content
    imports_to_add = []
    
    # Check for specific unit types and add appropriate imports
    if 'UnitArea<' in content:
        imports_to_add.append('import com.measures.area.UnitArea')
        imports_to_add.append('import com.measures.area.SquareMeter')
    
    if 'UnitDistance<' in content:
        imports_to_add.append('import com.measures.distance.UnitDistance')
        imports_to_add.append('import com.measures.distance.Meter')
    
    if 'UnitVolume<' in content:
        imports_to_add.append('import com.measures.volume.UnitVolume')
        imports_to_add.append('import com.measures.volume.Liter')
    
    if 'UnitTime<' in content:
        imports_to_add.append('import com.measures.time.UnitTime')
        imports_to_add.append('import com.measures.time.Second')
    
    if 'UnitVelocity<' in content:
        imports_to_add.append('import com.measures.velocity.UnitVelocity')
        imports_to_add.append('import com.measures.velocity.MetersPerSecond')
    
    if 'UnitEnergy<' in content:
        imports_to_add.append('import com.measures.energy.UnitEnergy')
        imports_to_add.append('import com.measures.energy.Joule')
    
    if 'UnitForce<' in content:
        imports_to_add.append('import com.measures.force.UnitForce')
        imports_to_add.append('import com.measures.force.Newton')
    
    if 'UnitCurrent<' in content:
        imports_to_add.append('import com.measures.current.UnitCurrent')
        imports_to_add.append('import com.measures.current.Ampere')
    
    if 'UnitCharge<' in content:
        imports_to_add.append('import com.measures.charge.UnitCharge')
        imports_to_add.append('import com.measures.charge.Coulomb')
    
    if 'UnitPotential<' in content:
        imports_to_add.append('import com.measures.potential.UnitPotential')
        imports_to_add.append('import com.measures.potential.Volt')
    
    if 'UnitPower<' in content:
        imports_to_add.append('import com.measures.power.UnitPower')
        imports_to_add.append('import com.measures.power.Watt')
    
    if 'UnitPressure<' in content:
        imports_to_add.append('import com.measures.pressure.UnitPressure')
        imports_to_add.append('import com.measures.pressure.Pascal')
    
    if 'UnitTemperature<' in content:
        imports_to_add.append('import com.measures.temperature.UnitTemperature')
        imports_to_add.append('import com.measures.temperature.Kelvin')
    
    if 'UnitLuminous<' in content:
        imports_to_add.append('import com.measures.luminous.UnitLuminous')
        imports_to_add.append('import com.measures.luminous.Candela')
    
    if 'UnitAmount<' in content:
        imports_to_add.append('import com.measures.amount.UnitAmount')
        imports_to_add.append('import com.measures.amount.Mole')
    
    if 'UnitAngle<' in content:
        imports_to_add.append('import com.measures.angle.UnitAngle')
        imports_to_add.append('import com.measures.angle.Radian')
    
    # Add common imports
    imports_to_add.extend([
        'import com.measures.BaseUnit',
        'import com.measures.UnitType',
        'import com.measures.DoubleBase',
        'import kotlin.jvm.JvmInline'
    ])
    
    # Add PI import if needed
    if 'PI' in content:
        imports_to_add.append('import kotlin.math.PI')
    
    # Remove duplicate imports
    imports_to_add = list(set(imports_to_add))
    
    # Find the package line and add imports after it
    package_line = content.find('package ')
    if package_line != -1:
        end_of_package = content.find('\n', package_line)
        if end_of_package != -1:
            # Insert imports after package
            new_content = content[:end_of_package + 1] + '\n'.join(imports_to_add) + '\n' + content[end_of_package + 1:]
            
            with open(file_path, 'w') as f:
                f.write(new_content)
            
            print(f"Fixed imports in: {file_path}")
            return True
    
    return False

def fix_all_imports():
    """Fix imports in all newly generated files"""
    
    # Get all .kt files in the measures directory
    for kt_file in BASE_DIR.rglob("*.kt"):
        if kt_file.is_file():
            try:
                fix_imports_in_file(kt_file)
            except Exception as e:
                print(f"Error fixing {kt_file}: {e}")

def main():
    """Fix all remaining import issues"""
    print("Fixing all remaining import issues...")
    fix_all_imports()
    print("Import fixes complete!")

if __name__ == "__main__":
    main()
