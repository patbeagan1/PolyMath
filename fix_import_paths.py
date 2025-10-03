#!/usr/bin/env python3
"""
Fix import paths to use correct package structure
"""

import os
from pathlib import Path

# Base directory
BASE_DIR = Path("/home/patrick/repo/incubator/PolyMath/units-common/src/commonMain/kotlin/com/measures")

def fix_american_customary_distance_imports():
    """Fix import paths for American Customary distance units"""
    ac_dir = BASE_DIR / "distance" / "american_customary"
    
    for file_path in ac_dir.glob("*.kt"):
        with open(file_path, 'r') as f:
            content = f.read()
        
        # Fix import paths to use correct package structure
        content = content.replace(
            'import com.measures.distance.SurveyRod',
            'import com.measures.distance.american_customary.SurveyRod'
        )
        content = content.replace(
            'import com.measures.distance.SurveyLink',
            'import com.measures.distance.american_customary.SurveyLink'
        )
        content = content.replace(
            'import com.measures.distance.SurveyChain',
            'import com.measures.distance.american_customary.SurveyChain'
        )
        content = content.replace(
            'import com.measures.distance.SurveyMile',
            'import com.measures.distance.american_customary.SurveyMile'
        )
        content = content.replace(
            'import com.measures.distance.SurveyFurlong',
            'import com.measures.distance.american_customary.SurveyFurlong'
        )
        content = content.replace(
            'import com.measures.distance.SurveyFoot',
            'import com.measures.distance.american_customary.SurveyFoot'
        )
        
        with open(file_path, 'w') as f:
            f.write(content)
        
        print(f"Fixed import paths in: {file_path}")

def fix_english_imperial_distance_imports():
    """Fix import paths for English Imperial distance units"""
    ei_dir = BASE_DIR / "distance" / "english_imperial"
    
    for file_path in ei_dir.glob("*.kt"):
        with open(file_path, 'r') as f:
            content = f.read()
        
        # Fix import paths to use correct package structure
        content = content.replace(
            'import com.measures.distance.ImperialFoot',
            'import com.measures.distance.english_imperial.ImperialFoot'
        )
        
        with open(file_path, 'w') as f:
            f.write(content)
        
        print(f"Fixed import paths in: {file_path}")

def fix_avoirdupois_weight_imports():
    """Fix import paths for Avoirdupois weight units"""
    av_dir = BASE_DIR / "weight" / "avoirdupois"
    
    for file_path in av_dir.glob("*.kt"):
        with open(file_path, 'r') as f:
            content = f.read()
        
        # Fix import paths to use correct package structure
        content = content.replace(
            'import com.measures.weight.Pound',
            'import com.measures.weight.avoirdupois.Pound'
        )
        content = content.replace(
            'import com.measures.weight.Gram',
            'import com.measures.weight.Gram'
        )
        content = content.replace(
            'import com.measures.weight.KiloGram',
            'import com.measures.weight.KiloGram'
        )
        
        with open(file_path, 'w') as f:
            f.write(content)
        
        print(f"Fixed import paths in: {file_path}")

def fix_troy_weight_imports():
    """Fix import paths for Troy weight units"""
    troy_dir = BASE_DIR / "weight" / "troy"
    
    for file_path in troy_dir.glob("*.kt"):
        with open(file_path, 'r') as f:
            content = f.read()
        
        # Fix import paths to use correct package structure
        content = content.replace(
            'import com.measures.weight.TroyPennyweight',
            'import com.measures.weight.troy.TroyPennyweight'
        )
        content = content.replace(
            'import com.measures.weight.TroyOunce',
            'import com.measures.weight.troy.TroyOunce'
        )
        content = content.replace(
            'import com.measures.weight.Gram',
            'import com.measures.weight.Gram'
        )
        content = content.replace(
            'import com.measures.weight.KiloGram',
            'import com.measures.weight.KiloGram'
        )
        
        with open(file_path, 'w') as f:
            f.write(content)
        
        print(f"Fixed import paths in: {file_path}")

def fix_area_imports():
    """Fix import paths for area units"""
    area_dir = BASE_DIR / "area" / "american_customary"
    
    for file_path in area_dir.glob("*.kt"):
        with open(file_path, 'r') as f:
            content = f.read()
        
        # Fix import paths to use correct package structure
        content = content.replace(
            'import com.measures.area.SurveyAcre',
            'import com.measures.area.american_customary.SurveyAcre'
        )
        content = content.replace(
            'import com.measures.area.SquareSurveyChain',
            'import com.measures.area.american_customary.SquareSurveyChain'
        )
        content = content.replace(
            'import com.measures.area.SquareSurveyFoot',
            'import com.measures.area.american_customary.SquareSurveyFoot'
        )
        content = content.replace(
            'import com.measures.area.SurveySection',
            'import com.measures.area.american_customary.SurveySection'
        )
        content = content.replace(
            'import com.measures.area.SurveyTownship',
            'import com.measures.area.american_customary.SurveyTownship'
        )
        
        with open(file_path, 'w') as f:
            f.write(content)
        
        print(f"Fixed import paths in: {file_path}")

def main():
    """Fix all import paths"""
    print("Fixing American Customary distance import paths...")
    fix_american_customary_distance_imports()
    
    print("Fixing English Imperial distance import paths...")
    fix_english_imperial_distance_imports()
    
    print("Fixing Avoirdupois weight import paths...")
    fix_avoirdupois_weight_imports()
    
    print("Fixing Troy weight import paths...")
    fix_troy_weight_imports()
    
    print("Fixing area import paths...")
    fix_area_imports()
    
    print("Import path fixes complete!")

if __name__ == "__main__":
    main()
