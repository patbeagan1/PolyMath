#!/usr/bin/env python3
"""
Fix Liter imports in volume units
"""

import os
from pathlib import Path

# Base directory
BASE_DIR = Path("/home/patrick/repo/incubator/PolyMath/units-common/src/commonMain/kotlin/com/measures/volume/metric")

def fix_liter_imports():
    """Fix Liter imports in volume units"""
    
    for file_path in BASE_DIR.glob("*.kt"):
        with open(file_path, 'r') as f:
            content = f.read()
        
        # Replace incorrect Liter import with correct one
        content = content.replace(
            'import com.measures.volume.Liter',
            'import com.measures.volume.Liter'
        )
        
        with open(file_path, 'w') as f:
            f.write(content)
        
        print(f"Fixed Liter import in: {file_path}")

def main():
    """Fix Liter imports"""
    print("Fixing Liter imports in volume units...")
    fix_liter_imports()
    
    print("Liter import fixes complete!")

if __name__ == "__main__":
    main()
