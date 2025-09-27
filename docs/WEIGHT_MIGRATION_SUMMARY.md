# Weight Unit Migration Summary

This document summarizes the migration of weight units from separate modules (`unit-weight-avoirdupois`, `unit-weight-troy`) into the `unit-base` module, organized by weight system.

## Overview

All weight units from the two separate modules have been successfully migrated into the `unit-base` module and organized by weight system (Avoirdupois, Troy) with separate files for each system.

## Migrated Files

### Weight Units

#### 1. `com.measures.weight.Avoirdupois.kt`
- **Source**: `unit-weight-avoirdupois/src/commonMain/kotlin/com/measures/weight/Weight.kt`
- **Units**: Dram, Grain, Long Hundred Weight, Long Ton, Ounce, Pound, Short Quarter, Long Quarter, Short Hundred Weight, Stone, Short Ton
- **Key Features**: Traditional avoirdupois weight system units used in the US and UK

#### 2. `com.measures.weight.Troy.kt`
- **Source**: `unit-weight-troy/src/commonMain/kotlin/com/measures/weight/Weights.kt`
- **Units**: Troy Grain, Troy Pennyweight, Troy Ounce, Troy Pound
- **Key Features**: Troy weight system units used for precious metals

## Detailed Unit Information

### Avoirdupois Weight Units

| Unit | Conversion to Base (Gram) | Notes |
|------|---------------------------|-------|
| **Dram** | Pound / 256 | 1/16 ounce |
| **Grain** | Pound / 7000 | Smallest unit |
| **Long Hundred Weight** | Pound × 112 | 112 pounds |
| **Long Ton** | Pound × 2240 | 2240 pounds |
| **Ounce** | Pound / 16 | 16 ounces = 1 pound |
| **Pound** | Gram × 28.35 | Base avoirdupois unit |
| **Short Quarter** | Pound × 25 | 25 pounds |
| **Long Quarter** | Pound × 28 | 28 pounds |
| **Short Hundred Weight** | Pound × 100 | 100 pounds |
| **Stone** | Pound × 14 | 14 pounds |
| **Short Ton** | Pound × 2000 | 2000 pounds |

### Troy Weight Units

| Unit | Conversion to Base (Gram) | Notes |
|------|---------------------------|-------|
| **Troy Grain** | Troy Pennyweight / 24 | 24 grains = 1 pennyweight |
| **Troy Pennyweight** | Troy Ounce / 20 | 20 pennyweights = 1 troy ounce |
| **Troy Ounce** | Gram × 31.1034768 | Base troy unit |
| **Troy Pound** | Troy Ounce × 12 | 12 troy ounces = 1 troy pound |

## Key Benefits of Migration

### 1. **Consolidated Organization**
- All weight units are now in a single module (`unit-base`)
- Units are organized by weight system (Avoirdupois, Troy)
- Each weight system has its own file for clarity

### 2. **Improved Maintainability**
- Single source of truth for all weight unit definitions
- Easier to find and modify weight units
- Consistent import structure

### 3. **Better Discoverability**
- Weight units are colocated with their dimensional counterparts
- Clear separation between different weight systems
- Logical file organization

### 4. **Preserved Functionality**
- All conversion functions maintained
- All unit relationships preserved
- All `toUnit` extension functions available

## File Structure After Migration

```
unit-base/src/commonMain/kotlin/com/measures/weight/
├── Weights.kt (existing base unit - Gram)
├── Avoirdupois.kt (new - 11 units)
└── Troy.kt (new - 4 units)
```

## Usage Examples

### Avoirdupois Weight Units
```kotlin
// Basic avoirdupois units
val weight1 = Pound(10.0)
val grams1 = weight1.toGram()

val weight2 = Ounce(16.0)
val pounds2 = weight2.toPound()

// Larger avoirdupois units
val weight3 = Stone(2.0)
val pounds3 = weight3.toPound()

val weight4 = ShortTon(1.0)
val pounds4 = weight4.toPound()

// Conversion between avoirdupois units
val dram = weight1.toDram()
val grain = weight1.toGrain()
```

### Troy Weight Units
```kotlin
// Basic troy units
val troyWeight1 = TroyOunce(12.0)
val grams1 = troyWeight1.toGram()

val troyWeight2 = TroyPound(1.0)
val troyOunces2 = troyWeight2.toTroyOunce()

// Smaller troy units
val troyWeight3 = TroyPennyweight(20.0)
val troyOunces3 = troyWeight3.toTroyOunce()

val troyWeight4 = TroyGrain(480.0)
val troyPennyweights4 = troyWeight4.toTroyPennyweight()

// Conversion between troy units
val troyGrain = troyWeight1.toTroyGrain()
val troyPennyweight = troyWeight1.toTroyPennyweight()
```

### Cross-System Conversions
```kotlin
// Convert between avoirdupois and troy systems
val avoirdupoisPound = Pound(1.0)
val troyOunce = avoirdupoisPound.toTroyOunce()

val troyPound = TroyPound(1.0)
val avoirdupoisOunce = troyPound.toOunce()

// All units can be converted to base gram
val gram1 = avoirdupoisPound.toGram()
val gram2 = troyPound.toGram()
```

## Migration Statistics

- **Total Files Migrated**: 2 files
- **Avoirdupois Units**: 11 unit types
- **Troy Units**: 4 unit types
- **Total Units Migrated**: 15 individual unit types
- **Conversion Functions**: 15 `toUnit` extension functions
- **Original Modules Deleted**: 2 modules removed

## Build Status

✅ **Build Successful**: All migrated weight units compile correctly and maintain their functionality.

## Original Modules Status

✅ **Deleted**: The original modules have been successfully removed:
- `unit-weight-avoirdupois/` - Deleted
- `unit-weight-troy/` - Deleted

## Summary

The weight unit migration is complete and successful. All weight units are now available in the `unit-base` module, organized by weight system (Avoirdupois, Troy). The original separate modules have been deleted, and all functionality has been preserved.

### Key Achievements:
- **15 weight units** successfully migrated
- **2 weight systems** organized (Avoirdupois, Troy)
- **All conversion functions** preserved
- **Build successful** after migration and deletion
- **Original modules deleted** to complete the consolidation

All existing code using these weight units will need to update their imports to reference the new locations in the `unit-base` module.
