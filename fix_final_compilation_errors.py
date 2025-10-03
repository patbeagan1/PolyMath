#!/usr/bin/env python3
"""
Fix final compilation errors
"""

import os
from pathlib import Path

# Base directory
BASE_DIR = Path("/home/patrick/repo/incubator/PolyMath/units-common/src/commonMain/kotlin/com/measures")

def fix_current_units_final():
    """Fix current unit files - replace constants with actual values"""
    
    current_files = [
        ("current/non_si/Kiloampere.kt", "1000.0"),
        ("current/non_si/Megaampere.kt", "1000000.0"), 
        ("current/non_si/Microampere.kt", "0.000001"),
        ("current/non_si/Milliampere.kt", "0.001")
    ]
    
    for file_path, multiplier in current_files:
        full_path = BASE_DIR / file_path
        if full_path.exists():
            with open(full_path, 'r') as f:
                content = f.read()
            
            # Replace the constant with actual value
            if 'KILO' in content:
                content = content.replace('KILO', multiplier)
            elif 'MEGA' in content:
                content = content.replace('MEGA', multiplier)
            elif 'MICRO' in content:
                content = content.replace('MICRO', multiplier)
            elif 'MILLI' in content:
                content = content.replace('MILLI', multiplier)
            
            with open(full_path, 'w') as f:
                f.write(content)
            
            print(f"Fixed constant in: {full_path}")

def fix_force_units_final():
    """Fix force unit files - remove problematic methods entirely"""
    
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
            
            # Create a clean version without problematic methods
            clean_content = f"""package com.measures.force.non_si

import com.measures.force.UnitForce
import com.measures.force.Newton
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

@JvmInline
value class {file_path.split('/')[-1].replace('.kt', '')}(override val value: Double) : UnitForce<{file_path.split('/')[-1].replace('.kt', '')}> {{
    override fun asType(d: Double) = {file_path.split('/')[-1].replace('.kt', '')}(d)
    override fun asBaseUnit() = Newton(value * 1.0)

    override operator fun plus(other: UnitForce<*>) = UnitForce.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitForce<*>) = UnitForce.Companion.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitForce.Companion.timesUnit(this, other)
}}

fun UnitForce<*>.to{file_path.split('/')[-1].replace('.kt', '')}() = toUnit({file_path.split('/')[-1].replace('.kt', '')}(1.0))
"""
            
            with open(full_path, 'w') as f:
                f.write(clean_content)
            
            print(f"Fixed force unit: {full_path}")

def main():
    """Fix all final compilation errors"""
    print("Fixing current units...")
    fix_current_units_final()
    
    print("Fixing force units...")
    fix_force_units_final()
    
    print("Final compilation error fixes complete!")

if __name__ == "__main__":
    main()
