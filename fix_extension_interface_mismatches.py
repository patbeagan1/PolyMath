#!/usr/bin/env python3
"""
Fix extension interface mismatches
"""

import os
import re
from pathlib import Path

# Base directory
BASE_DIR = Path("/home/patrick/repo/incubator/PolyMath/units-common/src/commonMain/kotlin/com/measures")

def fix_extension_mismatches():
    """Fix extension function mismatches"""
    
    # Define the correct mappings
    unit_type_mappings = {
        # Current units
        "Microampere": "Current",
        "Megaampere": "Current", 
        "Kiloampere": "Current",
        "Milliampere": "Current",
        
        # Area units
        "InternationalSquareInch": "Area",
        "InternationalSquareYard": "Area",
        "InternationalSquareMile": "Area", 
        "InternationalSquareFoot": "Area",
        
        # Charge units
        "Kilocoulomb": "Charge",
        "MilliampereHour": "Charge",
        "Millicoulomb": "Charge",
        "Nanocoulomb": "Charge",
        "AmpereHour": "Charge",
        "Picocoulomb": "Charge",
        "Microcoulomb": "Charge",
        
        # Distance units
        "InternationalFoot": "Distance",
        "InternationalPica": "Distance",
        "InternationalNauticalMile": "Distance",
        "InternationalYard": "Distance",
        "InternationalCable": "Distance",
        "InternationalMile": "Distance",
        "InternationalFathom": "Distance",
        "InternationalInch": "Distance",
        "InternationalPoint": "Distance",
        "Capefeet": "Distance",
        "Mils": "Distance",
        "Angstroms": "Distance",
        "Microns": "Distance",
        
        # Power units
        "Milliwatt": "Power",
        "Kilowatt": "Power",
        "Horsepower": "Power",
        "Megawatt": "Power",
        "Gigawatt": "Power",
        "ErgPerSecond": "Power",
        "FootPoundPerSecond": "Power",
        
        # Temperature units
        "Fahrenheit": "Temperature",
        "Rankine": "Temperature",
        "Celsius": "Temperature",
        
        # Amount units
        "Kilomole": "Amount",
        "Picomole": "Amount",
        "Nanomole": "Amount",
        "Millimole": "Amount",
        "Micromole": "Amount",
        
        # Velocity units
        "FeetPerSecond": "Velocity",
        "CentimetersPerSecond": "Velocity",
        "MilesPerHour": "Velocity",
        "KilometersPerHour": "Velocity",
        "Knots": "Velocity",
        
        # Luminous units
        "Millicandela": "Luminous",
        "Kilocandela": "Luminous",
        
        # Energy units
        "Erg": "Energy",
        "Calorie": "Energy",
        "Kilocalorie": "Energy",
        "KilowattHour": "Energy",
        "BritishThermalUnit": "Energy",
        "Megajoule": "Energy",
        "ElectronVolt": "Energy",
        "Kilojoule": "Energy",
        
        # Pressure units
        "Megapascal": "Pressure",
        "Millibar": "Pressure",
        "Torr": "Pressure",
        "Atmosphere": "Pressure",
        "Kilopascal": "Pressure",
        "PoundPerSquareInch": "Pressure",
        "MillimeterOfMercury": "Pressure",
        "Bar": "Pressure",
        
        # Potential units
        "Microvolt": "Potential",
        "Gigavolt": "Potential",
        "Millivolt": "Potential",
        "Kilovolt": "Potential",
        "Megavolt": "Potential",
        
        # Time units
        "Hour": "Time",
        "Microsecond": "Time",
        "Day": "Time",
        "Week": "Time",
        "Minute": "Time",
        "Millisecond": "Time",
        "Nanosecond": "Time"
    }
    
    print("🔧 Fixing extension interface mismatches...")
    print("=" * 60)
    
    fixed_count = 0
    
    for unit_name, correct_unit_type in unit_type_mappings.items():
        # Find the file
        file_path = None
        for kt_file in BASE_DIR.rglob(f"{unit_name}.kt"):
            if kt_file.is_file():
                file_path = kt_file
                break
        
        if not file_path:
            print(f"❌ File not found for {unit_name}")
            continue
        
        try:
            with open(file_path, 'r', encoding='utf-8') as f:
                content = f.read()
            
            # Fix the extension function
            old_extension = f"fun UnitVolume<*>.to{unit_name}()"
            new_extension = f"fun Unit{correct_unit_type}<*>.to{unit_name}()"
            
            if old_extension in content:
                content = content.replace(old_extension, new_extension)
                
                with open(file_path, 'w', encoding='utf-8') as f:
                    f.write(content)
                
                print(f"✅ Fixed {unit_name}: {old_extension} → {new_extension}")
                fixed_count += 1
            else:
                print(f"⚠️  Extension not found in {unit_name}")
                
        except Exception as e:
            print(f"❌ Error fixing {unit_name}: {e}")
    
    print(f"\n🎉 Fixed {fixed_count} extension interface mismatches!")
    return fixed_count

def verify_fixes():
    """Verify that the fixes worked"""
    
    print("\n🔍 Verifying fixes...")
    print("=" * 60)
    
    # Run the verification script again
    import subprocess
    result = subprocess.run(['python3', 'verify_extension_interfaces.py'], 
                          capture_output=True, text=True)
    
    if result.returncode == 0:
        print("🎉 All extension interfaces now match!")
        return True
    else:
        print("❌ Some issues remain:")
        print(result.stdout)
        return False

def main():
    """Main function"""
    print("🔧 EXTENSION INTERFACE MISMATCH FIXER")
    print("=" * 60)
    
    # Fix the mismatches
    fixed_count = fix_extension_mismatches()
    
    if fixed_count > 0:
        # Verify the fixes
        if verify_fixes():
            print("\n🎉 ALL EXTENSION INTERFACES ARE NOW CORRECT!")
        else:
            print("\n⚠️  Some issues may remain. Please review the output above.")
    else:
        print("\n⚠️  No mismatches were fixed. Please check the unit names.")

if __name__ == "__main__":
    main()
