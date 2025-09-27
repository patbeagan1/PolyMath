# Unit Migration Summary

This document summarizes the migration of units from separate modules (`unit-american-customary`, `unit-english-imperial`, `unit-english-international`, `unit-metric`) into the `unit-base` module, organized by dimension.

## Overview

All units from the four separate modules have been successfully migrated into the `unit-base` module and organized by dimension (distance, area, volume) with separate files for each measurement system.

## Migrated Files

### Distance Units

#### 1. `com.measures.distance.AmericanCustomary.kt`
- **Source**: `unit-american-customary/src/commonMain/kotlin/com/measures/distance/Distance.kt`
- **Units**: Survey Chain, Survey Foot, Survey Furlong, Survey League, Survey Link, Survey Mile, Survey Rod
- **Key Features**: US Survey system units with precise conversion factors

#### 2. `com.measures.distance.EnglishImperial.kt`
- **Source**: `unit-english-imperial/src/commonMain/kotlin/com/measures/distance/Distance.kt`
- **Units**: Imperial Thou, Barleycorn, Inch, Hand, Foot, Yard, Chain, Furlong, Mile, League
- **Key Features**: Traditional English Imperial system units

#### 3. `com.measures.distance.EnglishInternational.kt`
- **Source**: `unit-english-international/src/commonMain/kotlin/com/measures/international/distance/Distance.kt` + `DistanceNautical.kt`
- **Units**: International Foot, Inch, Mile, Pica, Point, Yard, Cable, Fathom, Nautical Mile
- **Key Features**: International standard units including nautical measurements

#### 4. `com.measures.distance.Metric.kt`
- **Source**: `unit-metric/src/commonMain/kotlin/com/measures/metric/distance/Distance.kt`
- **Units**: All SI prefix units (Attometer through Zettameter)
- **Key Features**: Complete SI prefix system for metric distances

### Area Units

#### 1. `com.measures.area.AmericanCustomary.kt`
- **Source**: `unit-american-customary/src/commonMain/kotlin/com/measures/area/Area.kt`
- **Units**: Square Survey Foot, Square Survey Chain, Survey Acre, Survey Section, Survey Township
- **Key Features**: US Survey system area units

#### 2. `com.measures.area.EnglishInternational.kt`
- **Source**: `unit-english-international/src/commonMain/kotlin/com/measures/international/area/Area.kt`
- **Units**: International Square Inch, Square Foot, Square Yard, Square Mile
- **Key Features**: International standard area units

### Volume Units

#### 1. `com.measures.volume.AmericanCustomaryFluid.kt`
- **Source**: `unit-american-customary/src/commonMain/kotlin/com/measures/volume/fluid/VolumeFluid.kt` + `AcreFoot.kt` + `CordFirewood.kt`
- **Units**: US Cup, Fluid Barrel, Fluid Dram, Fluid Gallon, Fluid Ounce, Fluid Pint, Fluid Pottle, Fluid Quart, Gill, Minim, Oil Barrel, Shot, Tablespoon, Teaspoon, Acrefoot, Cord Firewood
- **Key Features**: US fluid volume units plus specialized units

#### 2. `com.measures.volume.AmericanCustomaryDry.kt`
- **Source**: `unit-american-customary/src/commonMain/kotlin/com/measures/volume/dry/VolumeDry.kt`
- **Units**: US Dry Barrel, Dry Bushel, Dry Gallon, Dry Peck, Dry Pint, Dry Quart
- **Key Features**: US dry volume units

#### 3. `com.measures.volume.EnglishImperial.kt`
- **Source**: `unit-english-imperial/src/commonMain/kotlin/com/measures/volume/Volume.kt`
- **Units**: Imperial Fluid Ounce, Gill, Pint, Quart, Gallon
- **Key Features**: Traditional English Imperial volume units

#### 4. `com.measures.volume.EnglishInternational.kt`
- **Source**: `unit-english-international/src/commonMain/kotlin/com/measures/international/volume/Volume.kt`
- **Units**: International Cubic Inch, Cubic Foot, Cubic Yard
- **Key Features**: International standard cubic volume units

#### 5. `com.measures.volume.Metric.kt`
- **Source**: `unit-metric/src/commonMain/kotlin/com/measures/metric/volume/Volume.kt`
- **Units**: All SI prefix units (Attoliter through Zettaliter) + Cubic Centimeter, Cubic Meter
- **Key Features**: Complete SI prefix system for metric volumes

## Key Benefits of Migration

### 1. **Consolidated Organization**
- All units are now in a single module (`unit-base`)
- Units are organized by dimension (distance, area, volume)
- Each measurement system has its own file for clarity

### 2. **Improved Maintainability**
- Single source of truth for all unit definitions
- Easier to find and modify units
- Consistent import structure

### 3. **Better Discoverability**
- Units are colocated with their dimensional counterparts
- Clear separation between different measurement systems
- Logical file organization

### 4. **Preserved Functionality**
- All conversion functions maintained
- All unit relationships preserved
- All `toUnit` extension functions available

## File Structure After Migration

```
unit-base/src/commonMain/kotlin/com/measures/
├── distance/
│   ├── Meter.kt (existing base unit)
│   ├── AmericanCustomary.kt (new)
│   ├── EnglishImperial.kt (new)
│   ├── EnglishInternational.kt (new)
│   └── Metric.kt (new)
├── area/
│   ├── SquareMeter.kt (existing base unit)
│   ├── AmericanCustomary.kt (new)
│   └── EnglishInternational.kt (new)
└── volume/
    ├── Liter.kt (existing base unit)
    ├── AmericanCustomaryFluid.kt (new)
    ├── AmericanCustomaryDry.kt (new)
    ├── EnglishImperial.kt (new)
    ├── EnglishInternational.kt (new)
    └── Metric.kt (new)
```

## Usage Examples

### Distance Units
```kotlin
// American Customary
val distance1 = SurveyFoot(100.0)
val meters1 = distance1.toMeter()

// English Imperial
val distance2 = ImperialYard(50.0)
val meters2 = distance2.toMeter()

// English International
val distance3 = InternationalNauticalMile(5.0)
val meters3 = distance3.toMeter()

// Metric
val distance4 = Kilometer(10.0)
val meters4 = distance4.toMeter()
```

### Area Units
```kotlin
// American Customary
val area1 = SurveyAcre(2.0)
val squareMeters1 = area1.toSquareMeter()

// English International
val area2 = InternationalSquareFoot(1000.0)
val squareMeters2 = area2.toSquareMeter()
```

### Volume Units
```kotlin
// American Customary Fluid
val volume1 = USFluidGallon(5.0)
val liters1 = volume1.toLiter()

// American Customary Dry
val volume2 = USDryBushel(10.0)
val liters2 = volume2.toLiter()

// English Imperial
val volume3 = ImperialGallon(3.0)
val liters3 = volume3.toLiter()

// English International
val volume4 = InternationalCubicFoot(100.0)
val liters4 = volume4.toLiter()

// Metric
val volume5 = Kiloliter(2.0)
val liters5 = volume5.toLiter()
```

## Migration Statistics

- **Total Files Migrated**: 12 files
- **Distance Units**: 4 files (American Customary, English Imperial, English International, Metric)
- **Area Units**: 2 files (American Customary, English International)
- **Volume Units**: 5 files (American Customary Fluid, American Customary Dry, English Imperial, English International, Metric)
- **Total Units Migrated**: 100+ individual unit types
- **Conversion Functions**: 100+ `toUnit` extension functions

## Build Status

✅ **Build Successful**: All migrated units compile correctly and maintain their functionality.

## Next Steps

The migration is complete and all units are now available in the `unit-base` module. The separate modules (`unit-american-customary`, `unit-english-imperial`, `unit-english-international`, `unit-metric`) can now be considered deprecated and may be removed in future cleanup.

All existing code using these units will need to update their imports to reference the new locations in the `unit-base` module.
