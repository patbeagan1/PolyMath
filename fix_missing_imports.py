#!/usr/bin/env python3
"""
Add missing imports for units that reference other units
"""

import os
from pathlib import Path

# Base directory
BASE_DIR = Path("/home/patrick/repo/incubator/PolyMath/units-common/src/commonMain/kotlin/com/measures")

def fix_american_customary_distance_imports():
    """Add necessary imports for American Customary distance units"""
    ac_dir = BASE_DIR / "distance" / "american_customary"
    
    # SurveyChain needs SurveyRod
    survey_chain_file = ac_dir / "SurveyChain.kt"
    if survey_chain_file.exists():
        with open(survey_chain_file, 'r') as f:
            content = f.read()
        
        if 'SurveyRod' in content and 'import com.measures.distance.SurveyRod' not in content:
            content = content.replace(
                'import com.measures.distance.Meter',
                'import com.measures.distance.Meter\nimport com.measures.distance.SurveyRod'
            )
            
            with open(survey_chain_file, 'w') as f:
                f.write(content)
            
            print(f"Added SurveyRod import to: {survey_chain_file}")
    
    # SurveyFurlong needs SurveyChain
    survey_furlong_file = ac_dir / "SurveyFurlong.kt"
    if survey_furlong_file.exists():
        with open(survey_furlong_file, 'r') as f:
            content = f.read()
        
        if 'SurveyChain' in content and 'import com.measures.distance.SurveyChain' not in content:
            content = content.replace(
                'import com.measures.distance.Meter',
                'import com.measures.distance.Meter\nimport com.measures.distance.SurveyChain'
            )
            
            with open(survey_furlong_file, 'w') as f:
                f.write(content)
            
            print(f"Added SurveyChain import to: {survey_furlong_file}")
    
    # SurveyMile needs SurveyFurlong
    survey_mile_file = ac_dir / "SurveyMile.kt"
    if survey_mile_file.exists():
        with open(survey_mile_file, 'r') as f:
            content = f.read()
        
        if 'SurveyFurlong' in content and 'import com.measures.distance.SurveyFurlong' not in content:
            content = content.replace(
                'import com.measures.distance.Meter',
                'import com.measures.distance.Meter\nimport com.measures.distance.SurveyFurlong'
            )
            
            with open(survey_mile_file, 'w') as f:
                f.write(content)
            
            print(f"Added SurveyFurlong import to: {survey_mile_file}")
    
    # SurveyLeague needs SurveyMile
    survey_league_file = ac_dir / "SurveyLeague.kt"
    if survey_league_file.exists():
        with open(survey_league_file, 'r') as f:
            content = f.read()
        
        if 'SurveyMile' in content and 'import com.measures.distance.SurveyMile' not in content:
            content = content.replace(
                'import com.measures.distance.Meter',
                'import com.measures.distance.Meter\nimport com.measures.distance.SurveyMile'
            )
            
            with open(survey_league_file, 'w') as f:
                f.write(content)
            
            print(f"Added SurveyMile import to: {survey_league_file}")
    
    # SurveyLink needs SurveyFoot
    survey_link_file = ac_dir / "SurveyLink.kt"
    if survey_link_file.exists():
        with open(survey_link_file, 'r') as f:
            content = f.read()
        
        if 'SurveyFoot' in content and 'import com.measures.distance.SurveyFoot' not in content:
            content = content.replace(
                'import com.measures.distance.Meter',
                'import com.measures.distance.Meter\nimport com.measures.distance.SurveyFoot'
            )
            
            with open(survey_link_file, 'w') as f:
                f.write(content)
            
            print(f"Added SurveyFoot import to: {survey_link_file}")
    
    # SurveyRod needs SurveyLink
    survey_rod_file = ac_dir / "SurveyRod.kt"
    if survey_rod_file.exists():
        with open(survey_rod_file, 'r') as f:
            content = f.read()
        
        if 'SurveyLink' in content and 'import com.measures.distance.SurveyLink' not in content:
            content = content.replace(
                'import com.measures.distance.Meter',
                'import com.measures.distance.Meter\nimport com.measures.distance.SurveyLink'
            )
            
            with open(survey_rod_file, 'w') as f:
                f.write(content)
            
            print(f"Added SurveyLink import to: {survey_rod_file}")

def fix_english_imperial_distance_imports():
    """Add necessary imports for English Imperial distance units"""
    ei_dir = BASE_DIR / "distance" / "english_imperial"
    
    # Most Imperial units need ImperialFoot
    imperial_units = [
        "ImperialThou", "ImperialBarleycorn", "ImperialInch", "ImperialHand",
        "ImperialYard", "ImperialChain", "ImperialFurlong", "ImperialMile", "ImperialLeague"
    ]
    
    for unit_name in imperial_units:
        unit_file = ei_dir / f"{unit_name}.kt"
        if unit_file.exists():
            with open(unit_file, 'r') as f:
                content = f.read()
            
            if 'ImperialFoot' in content and 'import com.measures.distance.ImperialFoot' not in content:
                content = content.replace(
                    'import com.measures.distance.Meter',
                    'import com.measures.distance.Meter\nimport com.measures.distance.ImperialFoot'
                )
                
                with open(unit_file, 'w') as f:
                    f.write(content)
                
                print(f"Added ImperialFoot import to: {unit_file}")

def fix_avoirdupois_weight_imports():
    """Add necessary imports for Avoirdupois weight units"""
    av_dir = BASE_DIR / "weight" / "avoirdupois"
    
    # Most Avoirdupois units need Pound
    avoirdupois_units = [
        "Dram", "Grain", "LongHundredWeight", "LongTon", "Ounce",
        "ShortQuarter", "LongQuarter", "ShortHundredWeight", "Stone", "ShortTon"
    ]
    
    for unit_name in avoirdupois_units:
        unit_file = av_dir / f"{unit_name}.kt"
        if unit_file.exists():
            with open(unit_file, 'r') as f:
                content = f.read()
            
            if 'Pound' in content and 'import com.measures.weight.Pound' not in content:
                content = content.replace(
                    'import com.measures.weight.UnitMass',
                    'import com.measures.weight.UnitMass\nimport com.measures.weight.Pound'
                )
                
                with open(unit_file, 'w') as f:
                    f.write(content)
                
                print(f"Added Pound import to: {unit_file}")
    
    # Pound needs Gram
    pound_file = av_dir / "Pound.kt"
    if pound_file.exists():
        with open(pound_file, 'r') as f:
            content = f.read()
        
        if 'Gram' in content and 'import com.measures.weight.Gram' not in content:
            content = content.replace(
                'import com.measures.weight.UnitMass',
                'import com.measures.weight.UnitMass\nimport com.measures.weight.Gram'
            )
            
            with open(pound_file, 'w') as f:
                f.write(content)
            
            print(f"Added Gram import to: {pound_file}")

def fix_troy_weight_imports():
    """Add necessary imports for Troy weight units"""
    troy_dir = BASE_DIR / "weight" / "troy"
    
    # TroyGrain needs TroyPennyweight
    troy_grain_file = troy_dir / "TroyGrain.kt"
    if troy_grain_file.exists():
        with open(troy_grain_file, 'r') as f:
            content = f.read()
        
        if 'TroyPennyweight' in content and 'import com.measures.weight.TroyPennyweight' not in content:
            content = content.replace(
                'import com.measures.weight.UnitMass',
                'import com.measures.weight.UnitMass\nimport com.measures.weight.TroyPennyweight'
            )
            
            with open(troy_grain_file, 'w') as f:
                f.write(content)
            
            print(f"Added TroyPennyweight import to: {troy_grain_file}")
    
    # TroyPennyweight needs TroyOunce
    troy_pennyweight_file = troy_dir / "TroyPennyweight.kt"
    if troy_pennyweight_file.exists():
        with open(troy_pennyweight_file, 'r') as f:
            content = f.read()
        
        if 'TroyOunce' in content and 'import com.measures.weight.TroyOunce' not in content:
            content = content.replace(
                'import com.measures.weight.UnitMass',
                'import com.measures.weight.UnitMass\nimport com.measures.weight.TroyOunce'
            )
            
            with open(troy_pennyweight_file, 'w') as f:
                f.write(content)
            
            print(f"Added TroyOunce import to: {troy_pennyweight_file}")
    
    # TroyOunce needs Gram
    troy_ounce_file = troy_dir / "TroyOunce.kt"
    if troy_ounce_file.exists():
        with open(troy_ounce_file, 'r') as f:
            content = f.read()
        
        if 'Gram' in content and 'import com.measures.weight.Gram' not in content:
            content = content.replace(
                'import com.measures.weight.UnitMass',
                'import com.measures.weight.UnitMass\nimport com.measures.weight.Gram'
            )
            
            with open(troy_ounce_file, 'w') as f:
                f.write(content)
            
            print(f"Added Gram import to: {troy_ounce_file}")
    
    # TroyPound needs TroyOunce
    troy_pound_file = troy_dir / "TroyPound.kt"
    if troy_pound_file.exists():
        with open(troy_pound_file, 'r') as f:
            content = f.read()
        
        if 'TroyOunce' in content and 'import com.measures.weight.TroyOunce' not in content:
            content = content.replace(
                'import com.measures.weight.UnitMass',
                'import com.measures.weight.UnitMass\nimport com.measures.weight.TroyOunce'
            )
            
            with open(troy_pound_file, 'w') as f:
                f.write(content)
            
            print(f"Added TroyOunce import to: {troy_pound_file}")

def fix_area_imports():
    """Add necessary imports for area units"""
    area_dir = BASE_DIR / "area" / "american_customary"
    
    # SurveyAcre needs SquareSurveyChain
    survey_acre_file = area_dir / "SurveyAcre.kt"
    if survey_acre_file.exists():
        with open(survey_acre_file, 'r') as f:
            content = f.read()
        
        if 'SquareSurveyChain' in content and 'import com.measures.area.SquareSurveyChain' not in content:
            content = content.replace(
                'import com.measures.area.UnitArea',
                'import com.measures.area.UnitArea\nimport com.measures.area.SquareSurveyChain'
            )
            
            with open(survey_acre_file, 'w') as f:
                f.write(content)
            
            print(f"Added SquareSurveyChain import to: {survey_acre_file}")
    
    # SquareSurveyChain needs SquareSurveyFoot
    square_survey_chain_file = area_dir / "SquareSurveyChain.kt"
    if square_survey_chain_file.exists():
        with open(square_survey_chain_file, 'r') as f:
            content = f.read()
        
        if 'SquareSurveyFoot' in content and 'import com.measures.area.SquareSurveyFoot' not in content:
            content = content.replace(
                'import com.measures.area.UnitArea',
                'import com.measures.area.UnitArea\nimport com.measures.area.SquareSurveyFoot'
            )
            
            with open(square_survey_chain_file, 'w') as f:
                f.write(content)
            
            print(f"Added SquareSurveyFoot import to: {square_survey_chain_file}")
    
    # SquareSurveyFoot needs SquareMeter
    square_survey_foot_file = area_dir / "SquareSurveyFoot.kt"
    if square_survey_foot_file.exists():
        with open(square_survey_foot_file, 'r') as f:
            content = f.read()
        
        if 'SquareMeter' in content and 'import com.measures.area.SquareMeter' not in content:
            content = content.replace(
                'import com.measures.area.UnitArea',
                'import com.measures.area.UnitArea\nimport com.measures.area.SquareMeter'
            )
            
            with open(square_survey_foot_file, 'w') as f:
                f.write(content)
            
            print(f"Added SquareMeter import to: {square_survey_foot_file}")
    
    # SurveySection needs SurveyAcre
    survey_section_file = area_dir / "SurveySection.kt"
    if survey_section_file.exists():
        with open(survey_section_file, 'r') as f:
            content = f.read()
        
        if 'SurveyAcre' in content and 'import com.measures.area.SurveyAcre' not in content:
            content = content.replace(
                'import com.measures.area.UnitArea',
                'import com.measures.area.UnitArea\nimport com.measures.area.SurveyAcre'
            )
            
            with open(survey_section_file, 'w') as f:
                f.write(content)
            
            print(f"Added SurveyAcre import to: {survey_section_file}")
    
    # SurveyTownship needs SurveySection
    survey_township_file = area_dir / "SurveyTownship.kt"
    if survey_township_file.exists():
        with open(survey_township_file, 'r') as f:
            content = f.read()
        
        if 'SurveySection' in content and 'import com.measures.area.SurveySection' not in content:
            content = content.replace(
                'import com.measures.area.UnitArea',
                'import com.measures.area.UnitArea\nimport com.measures.area.SurveySection'
            )
            
            with open(survey_township_file, 'w') as f:
                f.write(content)
            
            print(f"Added SurveySection import to: {survey_township_file}")

def main():
    """Add missing imports for units that reference other units"""
    print("Adding missing imports for American Customary distance units...")
    fix_american_customary_distance_imports()
    
    print("Adding missing imports for English Imperial distance units...")
    fix_english_imperial_distance_imports()
    
    print("Adding missing imports for Avoirdupois weight units...")
    fix_avoirdupois_weight_imports()
    
    print("Adding missing imports for Troy weight units...")
    fix_troy_weight_imports()
    
    print("Adding missing imports for area units...")
    fix_area_imports()
    
    print("Missing import fixes complete!")

if __name__ == "__main__":
    main()
