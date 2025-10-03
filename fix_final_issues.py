#!/usr/bin/env python3
"""
Fix final compilation issues
"""

import os
from pathlib import Path

# Base directory
BASE_DIR = Path("/home/patrick/repo/incubator/PolyMath/units-common/src/commonMain/kotlin/com/measures")

def fix_misc_imports():
    """Fix conflicting imports in misc units"""
    
    misc_dir = BASE_DIR / "misc" / "other"
    volume_units = [
        "CupBreakfast.kt",
        "CupCanadian.kt", 
        "TablespoonCanadian.kt",
        "TeaspoonCanadian.kt"
    ]
    
    for filename in volume_units:
        file_path = misc_dir / filename
        if file_path.exists():
            with open(file_path, 'r') as f:
                content = f.read()
            
            # Clean up conflicting imports and fix the content
            content = f"""package com.measures.misc.other

import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import com.measures.volume.UnitVolume
import com.measures.volume.Liter
import kotlin.jvm.JvmInline

@JvmInline
value class {filename.replace('.kt', '')}(override val value: Double) : UnitVolume<{filename.replace('.kt', '')}> {{
    override fun asType(d: Double) = {filename.replace('.kt', '')}(d)
    override fun asBaseUnit() = Liter(value * 0.000284131 * 1000)

    override operator fun plus(other: UnitVolume<*>) = UnitVolume.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitVolume<*>) = UnitVolume.Companion.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitVolume.Companion.divUnit(this, other)
    override operator fun div(other: UnitDistance<*>) = UnitVolume.Companion.divUnit(this, other)
}}

fun UnitVolume<*>.to{filename.replace('.kt', '')}() = toUnit({filename.replace('.kt', '')}(1.0))
"""
            
            with open(file_path, 'w') as f:
                f.write(content)
            
            print(f"Fixed imports in: {file_path}")

def fix_missing_ustablespoon():
    """Fix missing USTablespoon references"""
    
    # Check if USTablespoon exists
    ustablespoon_file = BASE_DIR / "volume" / "american_customary_fluid" / "USTablespoon.kt"
    if not ustablespoon_file.exists():
        # Create USTablespoon.kt
        content = """package com.measures.volume.american_customary_fluid

import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import com.measures.volume.UnitVolume
import com.measures.volume.Liter
import kotlin.jvm.JvmInline

@JvmInline
value class USTablespoon(override val value: Double) : UnitVolume<USTablespoon> {
    override fun asType(d: Double) = USTablespoon(d)
    override fun asBaseUnit() = Liter(value * 0.0000147868 * 1000)

    override operator fun plus(other: UnitVolume<*>) = UnitVolume.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitVolume<*>) = UnitVolume.Companion.minusUnit(this, other)
    override operator fun div(other: UnitArea<*>) = UnitVolume.Companion.divUnit(this, other)
    override operator fun div(other: UnitDistance<*>) = UnitVolume.Companion.divUnit(this, other)
}

fun UnitVolume<*>.toUSTablespoon() = toUnit(USTablespoon(1.0))
"""
        
        with open(ustablespoon_file, 'w') as f:
            f.write(content)
        
        print(f"Created missing file: {ustablespoon_file}")

def main():
    """Fix final compilation issues"""
    print("Fixing misc imports...")
    fix_misc_imports()
    
    print("Fixing missing USTablespoon...")
    fix_missing_ustablespoon()
    
    print("Final fixes complete!")

if __name__ == "__main__":
    main()
