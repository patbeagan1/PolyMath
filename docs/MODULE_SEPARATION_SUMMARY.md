# Module Separation Summary

This document summarizes the separation of units into `unit-base` (SI units and interfaces) and `unit-common` (non-SI units) modules.

## Overview

The units have been separated into two modules:
- **`unit-base`**: Contains SI base units, derived units, interfaces, and shared types
- **`unit-common`**: Contains non-SI units (American Customary, English Imperial, English International, Metric prefixes, Avoirdupois, Troy)

## Module Structure

### unit-base Module
Contains SI units, base units, interfaces, and shared types:

```
unit-base/src/commonMain/kotlin/com/measures/
├── Base.kt (shared interfaces and types)
├── distance/
│   ├── Meter.kt (SI base unit)
│   └── Distance.kt (interface)
├── area/
│   ├── SquareMeter.kt (SI base unit)
│   └── Area.kt (interface)
├── volume/
│   ├── Liter.kt (SI base unit)
│   └── Volumes.kt (interface)
├── weight/
│   └── Weights.kt (SI base unit - Gram)
├── time/
│   └── Times.kt (SI base unit - Second)
├── temperature/
│   └── Temperatures.kt (SI base unit - Kelvin)
├── current/
│   └── Currents.kt (SI base unit - Ampere)
├── luminous/
│   └── Luminous.kt (SI base unit - Candela)
├── amount/
│   └── Amount.kt (SI base unit - Mole)
├── angle/
│   └── Angles.kt (SI derived unit - Radian)
├── force/
│   └── Forces.kt (SI derived unit - Newton)
├── energy/
│   └── Energies.kt (SI derived unit - Joule)
├── power/
│   └── Powers.kt (SI derived unit - Watt)
├── pressure/
│   └── Pressures.kt (SI derived unit - Pascal)
├── frequency/
│   └── Frequencies.kt (SI derived unit - Hertz)
├── charge/
│   └── Charges.kt (SI derived unit - Coulomb)
├── potential/
│   └── Potentials.kt (SI derived unit - Volt)
├── resistance/
│   └── Resistances.kt (SI derived unit - Ohm)
├── capacitance/
│   └── Capacitances.kt (SI derived unit - Farad)
├── inductance/
│   └── Inductances.kt (SI derived unit - Henry)
├── flux/
│   └── Fluxes.kt (SI derived unit - Weber)
├── fluxdensity/
│   └── FluxDensities.kt (SI derived unit - Tesla)
└── solidangle/
    └── SolidAngles.kt (SI derived unit - Steradian)
```

### unit-common Module
Contains non-SI units and measurement systems:

```
unit-common/src/commonMain/kotlin/com/measures/
├── distance/
│   ├── AmericanCustomary.kt (US Survey system)
│   ├── EnglishImperial.kt (Traditional Imperial)
│   ├── EnglishInternational.kt (International + Nautical)
│   └── Metric.kt (SI prefixes)
├── area/
│   ├── AmericanCustomary.kt (US Survey area)
│   └── EnglishInternational.kt (International area)
├── volume/
│   ├── AmericanCustomaryFluid.kt (US fluid volumes)
│   ├── AmericanCustomaryDry.kt (US dry volumes)
│   ├── EnglishImperial.kt (Imperial volumes)
│   ├── EnglishInternational.kt (International cubic)
│   └── Metric.kt (SI prefixes)
└── weight/
    ├── Avoirdupois.kt (Traditional weights)
    └── Troy.kt (Precious metal weights)
```

## Dependencies

### unit-common Module
- **Depends on**: `unit-base` (as API dependency)
- **Exposes**: All non-SI units while providing access to SI units and interfaces
- **Gradle Configuration**: `api(project(":unit-base"))`

### unit-base Module
- **Independent**: Contains only SI units and shared interfaces
- **No dependencies**: Self-contained module
- **Exposes**: All SI base units, derived units, and shared interfaces

## Key Benefits

### 1. **Clear Separation of Concerns**
- **unit-base**: Pure SI system with interfaces and shared types
- **unit-common**: Non-SI units that depend on SI base units

### 2. **Modular Architecture**
- **unit-base**: Can be used independently for SI-only applications
- **unit-common**: Provides comprehensive unit support including non-SI systems

### 3. **Dependency Management**
- **unit-common** exposes **unit-base** as API dependency
- Users of **unit-common** automatically get access to **unit-base**
- Clean dependency hierarchy

### 4. **Build Optimization**
- **unit-base**: Fast builds for SI-only applications
- **unit-common**: Comprehensive unit support when needed
- Independent compilation and testing

## Usage Examples

### Using unit-base only (SI units)
```kotlin
// Only SI units available
val distance = Meter(100.0)
val area = SquareMeter(50.0)
val volume = Liter(25.0)
val weight = Gram(1000.0)
val force = Newton(10.0)
val energy = Joule(100.0)
```

### Using unit-common (SI + non-SI units)
```kotlin
// All units available
val distance1 = Meter(100.0)           // SI
val distance2 = Kilometer(1.0)           // Metric prefix
val distance3 = ImperialFoot(328.0)      // Imperial
val distance4 = SurveyFoot(328.0)       // US Survey
val distance5 = InternationalYard(109.0)  // International

val weight1 = Gram(1000.0)              // SI
val weight2 = Pound(2.2)                // Avoirdupois
val weight3 = TroyOunce(32.15)          // Troy

val volume1 = Liter(1.0)                // SI
val volume2 = USFluidGallon(0.26)       // US Customary
val volume3 = ImperialGallon(0.22)      // Imperial
```

## Build Status

✅ **Both modules build successfully**:
- `unit-base`: Independent SI units and interfaces
- `unit-common`: Non-SI units with unit-base dependency

## Migration Impact

### For SI-only applications:
- Use `unit-base` module directly
- No change in functionality
- Faster builds

### For comprehensive unit support:
- Use `unit-common` module
- Automatically includes `unit-base`
- All units available

### For existing code:
- Update imports to use appropriate module
- `unit-common` provides backward compatibility
- Gradual migration possible

## Summary

The module separation successfully creates a clean architecture where:
- **unit-base** provides the foundation with SI units and interfaces
- **unit-common** extends this with non-SI units
- Both modules build independently and together
- Clear dependency hierarchy with proper API exposure
- Optimized for different use cases (SI-only vs comprehensive)
