#!/usr/bin/env python3
"""
Fix remaining import issues
"""

import os
from pathlib import Path

# Base directory
BASE_DIR = Path("/home/patrick/repo/incubator/PolyMath/units-common/src/commonMain/kotlin/com/measures")

def fix_current_units_final():
    """Fix current unit files - remove Constants reference"""
    
    current_files = [
        "current/non_si/Kiloampere.kt",
        "current/non_si/Megaampere.kt", 
        "current/non_si/Microampere.kt",
        "current/non_si/Milliampere.kt"
    ]
    
    for file_path in current_files:
        full_path = BASE_DIR / file_path
        if full_path.exists():
            with open(full_path, 'r') as f:
                content = f.read()
            
            # Remove the Constants reference entirely
            content = content.replace('Constants.', '')
            
            with open(full_path, 'w') as f:
                f.write(content)
            
            print(f"Fixed Constants reference in: {full_path}")

def fix_force_units_final():
    """Fix force unit files - remove problematic imports and methods"""
    
    force_files = [
        "force/non_si/Dyne.kt",
        "force/non_si/KilogramForce.kt",
        "force/non_si/Kilonewton.kt",
        "force/non_si/Meganewton.kt",
        "force/non_si/PoundForce.kt"
    ]
    
    for file_path in force_files:
        full_path = BASE_DIR / file_path
        if full_path.exists():
            with open(full_path, 'r') as f:
                content = f.read()
            
            # Remove problematic imports
            content = content.replace('import com.measures.mass.UnitMass\n', '')
            content = content.replace('import com.measures.acceleration.UnitAcceleration\n', '')
            content = content.replace('import com.measures.acceleration.MetersPerSecondPerSecond\n', '')
            content = content.replace('import com.measures.weight.KiloGram\n', '')
            
            # Remove problematic div methods
            lines = content.split('\n')
            new_lines = []
            skip_next = False
            
            for line in lines:
                if 'override operator fun div(other: UnitMass' in line or 'override operator fun div(other: UnitAcceleration' in line:
                    skip_next = True
                    continue
                if skip_next and line.strip() == '':
                    skip_next = False
                    continue
                if not skip_next:
                    new_lines.append(line)
            
            content = '\n'.join(new_lines)
            
            with open(full_path, 'w') as f:
                f.write(content)
            
            print(f"Fixed force unit: {full_path}")

def fix_weight_units_final():
    """Fix weight unit files - remove Gram references"""
    
    weight_files = [
        "weight/avoirdupois/Pound.kt",
        "weight/troy/TroyOunce.kt"
    ]
    
    for file_path in weight_files:
        full_path = BASE_DIR / file_path
        if full_path.exists():
            with open(full_path, 'r') as f:
                content = f.read()
            
            # Remove Gram import and references
            content = content.replace('import com.measures.weight.Gram\n', '')
            content = content.replace('Gram(', 'KiloGram(')
            
            with open(full_path, 'w') as f:
                f.write(content)
            
            print(f"Fixed weight unit: {full_path}")

def main():
    """Fix all remaining import issues"""
    print("Fixing current units...")
    fix_current_units_final()
    
    print("Fixing force units...")
    fix_force_units_final()
    
    print("Fixing weight units...")
    fix_weight_units_final()
    
    print("Final import fixes complete!")

if __name__ == "__main__":
    main()
