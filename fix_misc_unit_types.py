#!/usr/bin/env python3
"""
Fix misc unit types - they should be UnitVolume, not UnitDistance
"""

import os
from pathlib import Path

# Base directory
BASE_DIR = Path("/home/patrick/repo/incubator/PolyMath/units-common/src/commonMain/kotlin/com/measures/misc/other")

def fix_misc_unit_types():
    """Fix misc unit types to use correct UnitVolume interface"""
    
    # Volume units that should be UnitVolume
    volume_units = [
        "CupBreakfast.kt",
        "CupCanadian.kt", 
        "TablespoonCanadian.kt",
        "TeaspoonCanadian.kt"
    ]
    
    for filename in volume_units:
        file_path = BASE_DIR / filename
        if file_path.exists():
            with open(file_path, 'r') as f:
                content = f.read()
            
            # Fix the unit type from UnitDistance to UnitVolume
            content = content.replace(
                'UnitDistance<',
                'UnitVolume<'
            )
            content = content.replace(
                'UnitDistance.Companion',
                'UnitVolume.Companion'
            )
            content = content.replace(
                'import com.measures.distance.UnitDistance',
                'import com.measures.volume.UnitVolume'
            )
            content = content.replace(
                'import com.measures.distance.Meter',
                'import com.measures.volume.Liter'
            )
            content = content.replace(
                'import com.measures.area.UnitArea',
                'import com.measures.area.UnitArea'
            )
            content = content.replace(
                'import com.measures.time.UnitTime',
                'import com.measures.distance.UnitDistance'
            )
            content = content.replace(
                'import com.measures.velocity.MetersPerSecond',
                'import com.measures.area.SquareMeter'
            )
            
            # Fix the asBaseUnit return type
            content = content.replace(
                'asBaseUnit(): Meter',
                'asBaseUnit(): Liter'
            )
            
            # Fix operator signatures
            content = content.replace(
                'fun plus(other: UnitDistance<*>): Meter',
                'fun plus(other: UnitVolume<*>): Liter'
            )
            content = content.replace(
                'fun minus(other: UnitDistance<*>): Meter',
                'fun minus(other: UnitVolume<*>): Liter'
            )
            content = content.replace(
                'fun div(other: UnitTime<*>): MetersPerSecond',
                'fun div(other: UnitArea<*>): Meter'
            )
            content = content.replace(
                'fun div(other: UnitDistance<*>): SquareMeter',
                'fun div(other: UnitDistance<*>): SquareMeter'
            )
            
            # Fix the extension function
            content = content.replace(
                'fun UnitDistance<*>.to',
                'fun UnitVolume<*>.to'
            )
            
            with open(file_path, 'w') as f:
                f.write(content)
            
            print(f"Fixed unit type in: {file_path}")

def fix_missing_references():
    """Fix missing unit references"""
    
    # Fix USShot and USTeaspoon references
    us_shot_file = Path("/home/patrick/repo/incubator/PolyMath/units-common/src/commonMain/kotlin/com/measures/volume/american_customary_fluid/USShot.kt")
    if us_shot_file.exists():
        with open(us_shot_file, 'r') as f:
            content = f.read()
        
        # Add missing USTablespoon import
        if 'USTablespoon' in content and 'import com.measures.volume.american_customary_fluid.USTablespoon' not in content:
            content = content.replace(
                'import com.measures.volume.Liter',
                'import com.measures.volume.Liter\nimport com.measures.volume.american_customary_fluid.USTablespoon'
            )
        
        with open(us_shot_file, 'w') as f:
            f.write(content)
        
        print(f"Fixed missing reference in: {us_shot_file}")
    
    # Fix USTeaspoon references
    us_teaspoon_file = Path("/home/patrick/repo/incubator/PolyMath/units-common/src/commonMain/kotlin/com/measures/volume/american_customary_fluid/USTeaspoon.kt")
    if us_teaspoon_file.exists():
        with open(us_teaspoon_file, 'r') as f:
            content = f.read()
        
        # Add missing USTablespoon import
        if 'USTablespoon' in content and 'import com.measures.volume.american_customary_fluid.USTablespoon' not in content:
            content = content.replace(
                'import com.measures.volume.Liter',
                'import com.measures.volume.Liter\nimport com.measures.volume.american_customary_fluid.USTablespoon'
            )
        
        with open(us_teaspoon_file, 'w') as f:
            f.write(content)
        
        print(f"Fixed missing reference in: {us_teaspoon_file}")

def main():
    """Fix misc unit types and missing references"""
    print("Fixing misc unit types...")
    fix_misc_unit_types()
    
    print("Fixing missing references...")
    fix_missing_references()
    
    print("Misc unit fixes complete!")

if __name__ == "__main__":
    main()
