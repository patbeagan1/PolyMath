#!/usr/bin/env python3
"""
Fix circular import issues in unit files
"""

import os
from pathlib import Path

# Base directory
BASE_DIR = Path("/home/patrick/repo/incubator/PolyMath/units-common/src/commonMain/kotlin/com/measures")

def fix_american_customary_distance_imports():
    """Fix imports in American Customary distance units"""
    ac_dir = BASE_DIR / "distance" / "american_customary"
    
    for file_path in ac_dir.glob("*.kt"):
        with open(file_path, 'r') as f:
            content = f.read()
        
        # Remove self-imports and other unnecessary imports
        lines = content.split('\n')
        new_lines = []
        
        for line in lines:
            # Skip self-imports and other unit imports that cause circular dependencies
            if any(skip in line for skip in [
                'import com.measures.distance.SurveyRod',
                'import com.measures.distance.SurveyLink', 
                'import com.measures.distance.SurveyChain',
                'import com.measures.distance.SurveyMile',
                'import com.measures.distance.SurveyFurlong',
                'import com.measures.distance.SurveyFoot'
            ]):
                continue
            new_lines.append(line)
        
        content = '\n'.join(new_lines)
        
        with open(file_path, 'w') as f:
            f.write(content)
        
        print(f"Fixed imports in: {file_path}")

def fix_english_imperial_distance_imports():
    """Fix imports in English Imperial distance units"""
    ei_dir = BASE_DIR / "distance" / "english_imperial"
    
    for file_path in ei_dir.glob("*.kt"):
        with open(file_path, 'r') as f:
            content = f.read()
        
        # Remove self-imports
        lines = content.split('\n')
        new_lines = []
        
        for line in lines:
            # Skip self-imports
            if 'import com.measures.distance.ImperialFoot' in line and 'ImperialFoot.kt' in str(file_path):
                continue
            new_lines.append(line)
        
        content = '\n'.join(new_lines)
        
        with open(file_path, 'w') as f:
            f.write(content)
        
        print(f"Fixed imports in: {file_path}")

def fix_avoirdupois_weight_imports():
    """Fix imports in Avoirdupois weight units"""
    av_dir = BASE_DIR / "weight" / "avoirdupois"
    
    for file_path in av_dir.glob("*.kt"):
        with open(file_path, 'r') as f:
            content = f.read()
        
        # Remove self-imports and circular imports
        lines = content.split('\n')
        new_lines = []
        
        for line in lines:
            # Skip self-imports and circular imports
            if any(skip in line for skip in [
                'import com.measures.weight.Pound',
                'import com.measures.weight.KiloGram',
                'import com.measures.weight.Gram'
            ]):
                continue
            new_lines.append(line)
        
        content = '\n'.join(new_lines)
        
        with open(file_path, 'w') as f:
            f.write(content)
        
        print(f"Fixed imports in: {file_path}")

def fix_troy_weight_imports():
    """Fix imports in Troy weight units"""
    troy_dir = BASE_DIR / "weight" / "troy"
    
    for file_path in troy_dir.glob("*.kt"):
        with open(file_path, 'r') as f:
            content = f.read()
        
        # Remove self-imports and circular imports
        lines = content.split('\n')
        new_lines = []
        
        for line in lines:
            # Skip self-imports and circular imports
            if any(skip in line for skip in [
                'import com.measures.weight.TroyPennyweight',
                'import com.measures.weight.TroyOunce',
                'import com.measures.weight.KiloGram',
                'import com.measures.weight.Gram'
            ]):
                continue
            new_lines.append(line)
        
        content = '\n'.join(new_lines)
        
        with open(file_path, 'w') as f:
            f.write(content)
        
        print(f"Fixed imports in: {file_path}")

def fix_area_imports():
    """Fix imports in area units"""
    area_dir = BASE_DIR / "area" / "american_customary"
    
    for file_path in area_dir.glob("*.kt"):
        with open(file_path, 'r') as f:
            content = f.read()
        
        # Remove self-imports and circular imports
        lines = content.split('\n')
        new_lines = []
        
        for line in lines:
            # Skip self-imports and circular imports
            if any(skip in line for skip in [
                'import com.measures.area.SurveyAcre',
                'import com.measures.area.SquareSurveyChain',
                'import com.measures.area.SquareMeter'
            ]):
                continue
            new_lines.append(line)
        
        content = '\n'.join(new_lines)
        
        with open(file_path, 'w') as f:
            f.write(content)
        
        print(f"Fixed imports in: {file_path}")

def fix_volume_imports():
    """Fix imports in volume units"""
    vol_dir = BASE_DIR / "volume" / "metric"
    
    for file_path in vol_dir.glob("*.kt"):
        with open(file_path, 'r') as f:
            content = f.read()
        
        # Remove self-imports
        lines = content.split('\n')
        new_lines = []
        
        for line in lines:
            # Skip self-imports
            if 'import com.measures.volume.Liter' in line and 'Liter.kt' in str(file_path):
                continue
            new_lines.append(line)
        
        content = '\n'.join(new_lines)
        
        with open(file_path, 'w') as f:
            f.write(content)
        
        print(f"Fixed imports in: {file_path}")

def main():
    """Fix all circular import issues"""
    print("Fixing American Customary distance imports...")
    fix_american_customary_distance_imports()
    
    print("Fixing English Imperial distance imports...")
    fix_english_imperial_distance_imports()
    
    print("Fixing Avoirdupois weight imports...")
    fix_avoirdupois_weight_imports()
    
    print("Fixing Troy weight imports...")
    fix_troy_weight_imports()
    
    print("Fixing area imports...")
    fix_area_imports()
    
    print("Fixing volume imports...")
    fix_volume_imports()
    
    print("Circular import fixes complete!")

if __name__ == "__main__":
    main()
