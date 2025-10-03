#!/usr/bin/env python3
"""
Fix all import issues in the newly generated unit files
"""

import os
from pathlib import Path

# Base directory
BASE_DIR = Path("/home/patrick/repo/incubator/PolyMath/units-common/src/commonMain/kotlin/com/measures")

def fix_volume_imports():
    """Fix imports for volume units"""
    
    # American Customary Fluid
    acf_dir = BASE_DIR / "volume" / "american_customary_fluid"
    for file_path in acf_dir.glob("*.kt"):
        with open(file_path, 'r') as f:
            content = f.read()
        
        # Add missing imports
        if 'UnitVolume' in content and 'import com.measures.volume.UnitVolume' not in content:
            content = content.replace(
                'package com.measures.volume.american_customary_fluid',
                'package com.measures.volume.american_customary_fluid\n\nimport com.measures.volume.UnitVolume\nimport com.measures.volume.Liter\nimport com.measures.area.UnitArea\nimport com.measures.distance.UnitDistance'
            )
        
        with open(file_path, 'w') as f:
            f.write(content)
        
        print(f"Fixed imports in: {file_path}")
    
    # American Customary Dry
    acd_dir = BASE_DIR / "volume" / "american_customary_dry"
    for file_path in acd_dir.glob("*.kt"):
        with open(file_path, 'r') as f:
            content = f.read()
        
        # Add missing imports
        if 'UnitVolume' in content and 'import com.measures.volume.UnitVolume' not in content:
            content = content.replace(
                'package com.measures.volume.american_customary_dry',
                'package com.measures.volume.american_customary_dry\n\nimport com.measures.volume.UnitVolume\nimport com.measures.volume.Liter\nimport com.measures.area.UnitArea\nimport com.measures.distance.UnitDistance'
            )
        
        with open(file_path, 'w') as f:
            f.write(content)
        
        print(f"Fixed imports in: {file_path}")
    
    # English Imperial
    ei_dir = BASE_DIR / "volume" / "english_imperial"
    for file_path in ei_dir.glob("*.kt"):
        with open(file_path, 'r') as f:
            content = f.read()
        
        # Add missing imports
        if 'UnitVolume' in content and 'import com.measures.volume.UnitVolume' not in content:
            content = content.replace(
                'package com.measures.volume.english_imperial',
                'package com.measures.volume.english_imperial\n\nimport com.measures.volume.UnitVolume\nimport com.measures.volume.Liter\nimport com.measures.area.UnitArea\nimport com.measures.distance.UnitDistance'
            )
        
        with open(file_path, 'w') as f:
            f.write(content)
        
        print(f"Fixed imports in: {file_path}")
    
    # English International
    eint_dir = BASE_DIR / "volume" / "english_international"
    for file_path in eint_dir.glob("*.kt"):
        with open(file_path, 'r') as f:
            content = f.read()
        
        # Add missing imports
        if 'UnitVolume' in content and 'import com.measures.volume.UnitVolume' not in content:
            content = content.replace(
                'package com.measures.volume.english_international',
                'package com.measures.volume.english_international\n\nimport com.measures.volume.UnitVolume\nimport com.measures.volume.Liter\nimport com.measures.area.UnitArea\nimport com.measures.distance.UnitDistance'
            )
        
        with open(file_path, 'w') as f:
            f.write(content)
        
        print(f"Fixed imports in: {file_path}")

def fix_non_si_imports():
    """Fix imports for NonSI units"""
    
    # Time
    time_dir = BASE_DIR / "time" / "non_si"
    for file_path in time_dir.glob("*.kt"):
        with open(file_path, 'r') as f:
            content = f.read()
        
        # Add missing imports
        if 'UnitTime' in content and 'import com.measures.time.UnitTime' not in content:
            content = content.replace(
                'package com.measures.time.non_si',
                'package com.measures.time.non_si\n\nimport com.measures.time.UnitTime\nimport com.measures.time.Second'
            )
        
        with open(file_path, 'w') as f:
            f.write(content)
        
        print(f"Fixed imports in: {file_path}")
    
    # Energy
    energy_dir = BASE_DIR / "energy" / "non_si"
    for file_path in energy_dir.glob("*.kt"):
        with open(file_path, 'r') as f:
            content = f.read()
        
        # Add missing imports
        if 'UnitEnergy' in content and 'import com.measures.energy.UnitEnergy' not in content:
            content = content.replace(
                'package com.measures.energy.non_si',
                'package com.measures.energy.non_si\n\nimport com.measures.energy.UnitEnergy\nimport com.measures.energy.Joule'
            )
        
        with open(file_path, 'w') as f:
            f.write(content)
        
        print(f"Fixed imports in: {file_path}")
    
    # Force
    force_dir = BASE_DIR / "force" / "non_si"
    for file_path in force_dir.glob("*.kt"):
        with open(file_path, 'r') as f:
            content = f.read()
        
        # Add missing imports
        if 'UnitForce' in content and 'import com.measures.force.UnitForce' not in content:
            content = content.replace(
                'package com.measures.force.non_si',
                'package com.measures.force.non_si\n\nimport com.measures.force.UnitForce\nimport com.measures.force.Newton'
            )
        
        with open(file_path, 'w') as f:
            f.write(content)
        
        print(f"Fixed imports in: {file_path}")
    
    # Current
    current_dir = BASE_DIR / "current" / "non_si"
    for file_path in current_dir.glob("*.kt"):
        with open(file_path, 'r') as f:
            content = f.read()
        
        # Add missing imports
        if 'UnitCurrent' in content and 'import com.measures.current.UnitCurrent' not in content:
            content = content.replace(
                'package com.measures.current.non_si',
                'package com.measures.current.non_si\n\nimport com.measures.current.UnitCurrent\nimport com.measures.current.Ampere'
            )
        
        with open(file_path, 'w') as f:
            f.write(content)
        
        print(f"Fixed imports in: {file_path}")
    
    # Charge
    charge_dir = BASE_DIR / "charge" / "non_si"
    for file_path in charge_dir.glob("*.kt"):
        with open(file_path, 'r') as f:
            content = f.read()
        
        # Add missing imports
        if 'UnitCharge' in content and 'import com.measures.charge.UnitCharge' not in content:
            content = content.replace(
                'package com.measures.charge.non_si',
                'package com.measures.charge.non_si\n\nimport com.measures.charge.UnitCharge\nimport com.measures.charge.Coulomb'
            )
        
        with open(file_path, 'w') as f:
            f.write(content)
        
        print(f"Fixed imports in: {file_path}")
    
    # Potential
    potential_dir = BASE_DIR / "potential" / "non_si"
    for file_path in potential_dir.glob("*.kt"):
        with open(file_path, 'r') as f:
            content = f.read()
        
        # Add missing imports
        if 'UnitPotential' in content and 'import com.measures.potential.UnitPotential' not in content:
            content = content.replace(
                'package com.measures.potential.non_si',
                'package com.measures.potential.non_si\n\nimport com.measures.potential.UnitPotential\nimport com.measures.potential.Volt'
            )
        
        with open(file_path, 'w') as f:
            f.write(content)
        
        print(f"Fixed imports in: {file_path}")
    
    # Power
    power_dir = BASE_DIR / "power" / "non_si"
    for file_path in power_dir.glob("*.kt"):
        with open(file_path, 'r') as f:
            content = f.read()
        
        # Add missing imports
        if 'UnitPower' in content and 'import com.measures.power.UnitPower' not in content:
            content = content.replace(
                'package com.measures.power.non_si',
                'package com.measures.power.non_si\n\nimport com.measures.power.UnitPower\nimport com.measures.power.Watt'
            )
        
        with open(file_path, 'w') as f:
            f.write(content)
        
        print(f"Fixed imports in: {file_path}")
    
    # Pressure
    pressure_dir = BASE_DIR / "pressure" / "non_si"
    for file_path in pressure_dir.glob("*.kt"):
        with open(file_path, 'r') as f:
            content = f.read()
        
        # Add missing imports
        if 'UnitPressure' in content and 'import com.measures.pressure.UnitPressure' not in content:
            content = content.replace(
                'package com.measures.pressure.non_si',
                'package com.measures.pressure.non_si\n\nimport com.measures.pressure.UnitPressure\nimport com.measures.pressure.Pascal'
            )
        
        with open(file_path, 'w') as f:
            f.write(content)
        
        print(f"Fixed imports in: {file_path}")
    
    # Temperature
    temp_dir = BASE_DIR / "temperature" / "non_si"
    for file_path in temp_dir.glob("*.kt"):
        with open(file_path, 'r') as f:
            content = f.read()
        
        # Add missing imports
        if 'UnitTemperature' in content and 'import com.measures.temperature.UnitTemperature' not in content:
            content = content.replace(
                'package com.measures.temperature.non_si',
                'package com.measures.temperature.non_si\n\nimport com.measures.temperature.UnitTemperature\nimport com.measures.temperature.Kelvin'
            )
        
        with open(file_path, 'w') as f:
            f.write(content)
        
        print(f"Fixed imports in: {file_path}")
    
    # Velocity
    velocity_dir = BASE_DIR / "velocity" / "non_si"
    for file_path in velocity_dir.glob("*.kt"):
        with open(file_path, 'r') as f:
            content = f.read()
        
        # Add missing imports
        if 'UnitVelocity' in content and 'import com.measures.velocity.UnitVelocity' not in content:
            content = content.replace(
                'package com.measures.velocity.non_si',
                'package com.measures.velocity.non_si\n\nimport com.measures.velocity.UnitVelocity\nimport com.measures.velocity.MetersPerSecond'
            )
        
        with open(file_path, 'w') as f:
            f.write(content)
        
        print(f"Fixed imports in: {file_path}")
    
    # Luminous
    luminous_dir = BASE_DIR / "luminous" / "non_si"
    for file_path in luminous_dir.glob("*.kt"):
        with open(file_path, 'r') as f:
            content = f.read()
        
        # Add missing imports
        if 'UnitLuminous' in content and 'import com.measures.luminous.UnitLuminous' not in content:
            content = content.replace(
                'package com.measures.luminous.non_si',
                'package com.measures.luminous.non_si\n\nimport com.measures.luminous.UnitLuminous\nimport com.measures.luminous.Candela'
            )
        
        with open(file_path, 'w') as f:
            f.write(content)
        
        print(f"Fixed imports in: {file_path}")
    
    # Amount
    amount_dir = BASE_DIR / "amount" / "non_si"
    for file_path in amount_dir.glob("*.kt"):
        with open(file_path, 'r') as f:
            content = f.read()
        
        # Add missing imports
        if 'UnitAmount' in content and 'import com.measures.amount.UnitAmount' not in content:
            content = content.replace(
                'package com.measures.amount.non_si',
                'package com.measures.amount.non_si\n\nimport com.measures.amount.UnitAmount\nimport com.measures.amount.Mole'
            )
        
        with open(file_path, 'w') as f:
            f.write(content)
        
        print(f"Fixed imports in: {file_path}")

def fix_angle_imports():
    """Fix imports for angle units"""
    
    angle_dir = BASE_DIR / "angle" / "non_si"
    for file_path in angle_dir.glob("*.kt"):
        with open(file_path, 'r') as f:
            content = f.read()
        
        # Add missing imports
        if 'UnitAngle' in content and 'import com.measures.angle.UnitAngle' not in content:
            content = content.replace(
                'package com.measures.angle.non_si',
                'package com.measures.angle.non_si\n\nimport com.measures.angle.UnitAngle\nimport com.measures.angle.Radian'
            )
        
        with open(file_path, 'w') as f:
            f.write(content)
        
        print(f"Fixed imports in: {file_path}")

def fix_misc_imports():
    """Fix imports for misc units"""
    
    misc_dir = BASE_DIR / "misc" / "other"
    for file_path in misc_dir.glob("*.kt"):
        with open(file_path, 'r') as f:
            content = f.read()
        
        # Add missing imports based on unit type
        if 'UnitDistance' in content:
            content = content.replace(
                'package com.measures.misc.other',
                'package com.measures.misc.other\n\nimport com.measures.distance.UnitDistance\nimport com.measures.distance.Meter\nimport com.measures.area.UnitArea\nimport com.measures.time.UnitTime\nimport com.measures.velocity.MetersPerSecond'
            )
        elif 'UnitVolume' in content:
            content = content.replace(
                'package com.measures.misc.other',
                'package com.measures.misc.other\n\nimport com.measures.volume.UnitVolume\nimport com.measures.volume.Liter\nimport com.measures.area.UnitArea\nimport com.measures.distance.UnitDistance'
            )
        
        with open(file_path, 'w') as f:
            f.write(content)
        
        print(f"Fixed imports in: {file_path}")

def main():
    """Fix all import issues"""
    print("Fixing volume imports...")
    fix_volume_imports()
    
    print("Fixing NonSI imports...")
    fix_non_si_imports()
    
    print("Fixing angle imports...")
    fix_angle_imports()
    
    print("Fixing misc imports...")
    fix_misc_imports()
    
    print("All import fixes complete!")

if __name__ == "__main__":
    main()
