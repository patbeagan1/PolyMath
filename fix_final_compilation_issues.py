#!/usr/bin/env python3
"""
Fix final compilation issues
"""

import os
from pathlib import Path

# Base directory
BASE_DIR = Path("/home/patrick/repo/incubator/PolyMath/units-common/src/commonMain/kotlin/com/measures")

def fix_current_units():
    """Fix current unit files"""
    
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
            
            # Fix Consts reference
            content = content.replace('Consts', 'Constants')
            
            with open(full_path, 'w') as f:
                f.write(content)
            
            print(f"Fixed Consts reference in: {full_path}")

def fix_force_units():
    """Fix force unit files"""
    
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
            
            # Add missing imports
            content = content.replace(
                'import com.measures.force.Newton',
                'import com.measures.force.Newton\nimport com.measures.mass.UnitMass\nimport com.measures.acceleration.UnitAcceleration\nimport com.measures.acceleration.MetersPerSecondPerSecond\nimport com.measures.weight.KiloGram'
            )
            
            # Fix div method signatures
            content = content.replace(
                'override operator fun div(other: UnitTime<*>) = UnitForce.Companion.divUnit(this, other)',
                'override operator fun div(other: UnitMass<*>) = UnitForce.Companion.divUnit(this, other)\n    override operator fun div(other: UnitAcceleration<*>) = UnitForce.Companion.divUnit(this, other)'
            )
            
            with open(full_path, 'w') as f:
                f.write(content)
            
            print(f"Fixed force unit: {full_path}")

def fix_weight_units():
    """Fix weight unit files"""
    
    weight_files = [
        "weight/avoirdupois/Pound.kt",
        "weight/troy/TroyOunce.kt"
    ]
    
    for file_path in weight_files:
        full_path = BASE_DIR / file_path
        if full_path.exists():
            with open(full_path, 'r') as f:
                content = f.read()
            
            # Add missing Gram import
            content = content.replace(
                'import com.measures.weight.KiloGram',
                'import com.measures.weight.KiloGram\nimport com.measures.weight.Gram'
            )
            
            with open(full_path, 'w') as f:
                f.write(content)
            
            print(f"Fixed weight unit: {full_path}")

def main():
    """Fix all final compilation issues"""
    print("Fixing current units...")
    fix_current_units()
    
    print("Fixing force units...")
    fix_force_units()
    
    print("Fixing weight units...")
    fix_weight_units()
    
    print("Final compilation fixes complete!")

if __name__ == "__main__":
    main()
