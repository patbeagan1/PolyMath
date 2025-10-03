#!/usr/bin/env python3
"""
Add missing KiloGram imports to weight units
"""

import os
from pathlib import Path

# Base directory
BASE_DIR = Path("/home/patrick/repo/incubator/PolyMath/units-common/src/commonMain/kotlin/com/measures")

def fix_avoirdupois_weight_imports():
    """Add KiloGram imports to Avoirdupois weight units"""
    av_dir = BASE_DIR / "weight" / "avoirdupois"
    
    for file_path in av_dir.glob("*.kt"):
        with open(file_path, 'r') as f:
            content = f.read()
        
        # Add KiloGram import if not present
        if 'KiloGram' in content and 'import com.measures.weight.KiloGram' not in content:
            content = content.replace(
                'import com.measures.weight.UnitMass',
                'import com.measures.weight.UnitMass\nimport com.measures.weight.KiloGram'
            )
            
            with open(file_path, 'w') as f:
                f.write(content)
            
            print(f"Added KiloGram import to: {file_path}")

def fix_troy_weight_imports():
    """Add KiloGram imports to Troy weight units"""
    troy_dir = BASE_DIR / "weight" / "troy"
    
    for file_path in troy_dir.glob("*.kt"):
        with open(file_path, 'r') as f:
            content = f.read()
        
        # Add KiloGram import if not present
        if 'KiloGram' in content and 'import com.measures.weight.KiloGram' not in content:
            content = content.replace(
                'import com.measures.weight.UnitMass',
                'import com.measures.weight.UnitMass\nimport com.measures.weight.KiloGram'
            )
            
            with open(file_path, 'w') as f:
                f.write(content)
            
            print(f"Added KiloGram import to: {file_path}")

def main():
    """Add missing KiloGram imports"""
    print("Adding KiloGram imports to Avoirdupois weight units...")
    fix_avoirdupois_weight_imports()
    
    print("Adding KiloGram imports to Troy weight units...")
    fix_troy_weight_imports()
    
    print("KiloGram import fixes complete!")

if __name__ == "__main__":
    main()
