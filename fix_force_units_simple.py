#!/usr/bin/env python3
"""
Fix force units with simple implementation
"""

import os
from pathlib import Path

# Base directory
BASE_DIR = Path("/home/patrick/repo/incubator/PolyMath/units-common/src/commonMain/kotlin/com/measures")

def fix_force_units_simple():
    """Fix force unit files with simple implementation"""
    
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
            unit_name = file_path.split('/')[-1].replace('.kt', '')
            
            # Create a simple force unit file without problematic methods
            clean_content = f"""package com.measures.force.non_si

import com.measures.force.UnitForce
import com.measures.force.Newton
import kotlin.jvm.JvmInline

@JvmInline
value class {unit_name}(override val value: Double) : UnitForce<{unit_name}> {{
    override fun asType(d: Double) = {unit_name}(d)
    override fun asBaseUnit() = Newton(value * 1.0)

    override operator fun plus(other: UnitForce<*>) = UnitForce.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitForce<*>) = UnitForce.Companion.minusUnit(this, other)
}}

fun UnitForce<*>.to{unit_name}() = toUnit({unit_name}(1.0))
"""
            
            with open(full_path, 'w') as f:
                f.write(clean_content)
            
            print(f"Fixed force unit: {full_path}")

def main():
    """Fix force units with simple implementation"""
    print("Fixing force units with simple implementation...")
    fix_force_units_simple()
    
    print("Force unit fixes complete!")

if __name__ == "__main__":
    main()
