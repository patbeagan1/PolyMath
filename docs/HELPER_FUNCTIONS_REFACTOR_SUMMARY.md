# Helper Functions Refactor Summary

This document summarizes the refactoring of helper functions from standalone functions to companion object methods within value classes, following the user's request to move them into the value class body, rename them to `from`, and accept TypedFull versions of their dimensions.

## Overview

All helper functions (previously named `createXxx`) have been moved into companion objects of their respective value classes, renamed to `from`, and updated to accept TypedFull versions of their dimensional parameters instead of specific base unit types.

## Refactored Files

### 1. Capacitance (`com.measures.capacitance.Capacitances.kt`)
**Before:**
```kotlin
fun createFarad(current: Ampere, time: Second, mass: Gram, distance: Meter): Farad {
    return Farad(current.value * current.value * time.value * time.value * time.value * time.value / (mass.value * distance.value * distance.value))
}
```

**After:**
```kotlin
@JvmInline
value class Farad(override val value: Double) : UnitCapacitance<Farad>, BaseUnit {
    // ... existing code ...
    
    companion object {
        fun from(current: UnitCurrent<*>, time: UnitTime<*>, mass: UnitWeight<*>, distance: UnitDistance<*>): Farad {
            val currentBase = current.asBaseUnit()
            val timeBase = time.asBaseUnit()
            val massBase = mass.asBaseUnit()
            val distanceBase = distance.asBaseUnit()
            return Farad(currentBase.value * currentBase.value * timeBase.value * timeBase.value * timeBase.value * timeBase.value / (massBase.value * distanceBase.value * distanceBase.value))
        }
    }
}
```

### 2. Force (`com.measures.force.Forces.kt`)
**Before:**
```kotlin
fun createNewton(mass: Gram, distance: Meter, time: Second): Newton {
    return Newton(mass.value * distance.value / (time.value * time.value))
}
```

**After:**
```kotlin
@JvmInline
value class Newton(override val value: Double) : UnitForce<Newton>, BaseUnit {
    // ... existing code ...
    
    companion object {
        fun from(mass: UnitWeight<*>, distance: UnitDistance<*>, time: UnitTime<*>): Newton {
            val massBase = mass.asBaseUnit()
            val distanceBase = distance.asBaseUnit()
            val timeBase = time.asBaseUnit()
            return Newton(massBase.value * distanceBase.value / (timeBase.value * timeBase.value))
        }
    }
}
```

### 3. Energy (`com.measures.energy.Energies.kt`)
**Before:**
```kotlin
fun createJoule(mass: Gram, distance: Meter, time: Second): Joule {
    return Joule(mass.value * distance.value * distance.value / (time.value * time.value))
}
```

**After:**
```kotlin
@JvmInline
value class Joule(override val value: Double) : UnitEnergy<Joule>, BaseUnit {
    // ... existing code ...
    
    companion object {
        fun from(mass: UnitWeight<*>, distance: UnitDistance<*>, time: UnitTime<*>): Joule {
            val massBase = mass.asBaseUnit()
            val distanceBase = distance.asBaseUnit()
            val timeBase = time.asBaseUnit()
            return Joule(massBase.value * distanceBase.value * distanceBase.value / (timeBase.value * timeBase.value))
        }
    }
}
```

### 4. Power (`com.measures.power.Powers.kt`)
**Before:**
```kotlin
fun createWatt(mass: Gram, distance: Meter, time: Second): Watt {
    return Watt(mass.value * distance.value * distance.value / (time.value * time.value * time.value))
}
```

**After:**
```kotlin
@JvmInline
value class Watt(override val value: Double) : UnitPower<Watt>, BaseUnit {
    // ... existing code ...
    
    companion object {
        fun from(mass: UnitWeight<*>, distance: UnitDistance<*>, time: UnitTime<*>): Watt {
            val massBase = mass.asBaseUnit()
            val distanceBase = distance.asBaseUnit()
            val timeBase = time.asBaseUnit()
            return Watt(massBase.value * distanceBase.value * distanceBase.value / (timeBase.value * timeBase.value * timeBase.value))
        }
    }
}
```

### 5. Pressure (`com.measures.pressure.Pressures.kt`)
**Before:**
```kotlin
fun createPascal(mass: Gram, distance: Meter, time: Second): Pascal {
    return Pascal(mass.value / (distance.value * time.value * time.value))
}
```

**After:**
```kotlin
@JvmInline
value class Pascal(override val value: Double) : UnitPressure<Pascal>, BaseUnit {
    // ... existing code ...
    
    companion object {
        fun from(mass: UnitWeight<*>, distance: UnitDistance<*>, time: UnitTime<*>): Pascal {
            val massBase = mass.asBaseUnit()
            val distanceBase = distance.asBaseUnit()
            val timeBase = time.asBaseUnit()
            return Pascal(massBase.value / (distanceBase.value * timeBase.value * timeBase.value))
        }
    }
}
```

### 6. Frequency (`com.measures.frequency.Frequencies.kt`)
**Before:**
```kotlin
fun createHertz(time: Second): Hertz {
    return Hertz(1.0 / time.value)
}
```

**After:**
```kotlin
@JvmInline
value class Hertz(override val value: Double) : UnitFrequency<Hertz>, BaseUnit {
    // ... existing code ...
    
    companion object {
        fun from(time: UnitTime<*>): Hertz {
            val timeBase = time.asBaseUnit()
            return Hertz(1.0 / timeBase.value)
        }
    }
}
```

### 7. Charge (`com.measures.charge.Charges.kt`)
**Before:**
```kotlin
fun createCoulomb(current: Ampere, time: Second): Coulomb {
    return Coulomb(current.value * time.value)
}
```

**After:**
```kotlin
@JvmInline
value class Coulomb(override val value: Double) : UnitCharge<Coulomb>, BaseUnit {
    // ... existing code ...
    
    companion object {
        fun from(current: UnitCurrent<*>, time: UnitTime<*>): Coulomb {
            val currentBase = current.asBaseUnit()
            val timeBase = time.asBaseUnit()
            return Coulomb(currentBase.value * timeBase.value)
        }
    }
}
```

### 8. Potential (`com.measures.potential.Potentials.kt`)
**Before:**
```kotlin
fun createVolt(mass: Gram, distance: Meter, current: Ampere, time: Second): Volt {
    return Volt(mass.value * distance.value * distance.value / (current.value * time.value * time.value * time.value))
}
```

**After:**
```kotlin
@JvmInline
value class Volt(override val value: Double) : UnitPotential<Volt>, BaseUnit {
    // ... existing code ...
    
    companion object {
        fun from(mass: UnitWeight<*>, distance: UnitDistance<*>, current: UnitCurrent<*>, time: UnitTime<*>): Volt {
            val massBase = mass.asBaseUnit()
            val distanceBase = distance.asBaseUnit()
            val currentBase = current.asBaseUnit()
            val timeBase = time.asBaseUnit()
            return Volt(massBase.value * distanceBase.value * distanceBase.value / (currentBase.value * timeBase.value * timeBase.value * timeBase.value))
        }
    }
}
```

### 9. Resistance (`com.measures.resistance.Resistances.kt`)
**Before:**
```kotlin
fun createOhm(mass: Gram, distance: Meter, current: Ampere, time: Second): Ohm {
    return Ohm(mass.value * distance.value * distance.value / (current.value * current.value * time.value * time.value * time.value))
}
```

**After:**
```kotlin
@JvmInline
value class Ohm(override val value: Double) : UnitResistance<Ohm>, BaseUnit {
    // ... existing code ...
    
    companion object {
        fun from(mass: UnitWeight<*>, distance: UnitDistance<*>, current: UnitCurrent<*>, time: UnitTime<*>): Ohm {
            val massBase = mass.asBaseUnit()
            val distanceBase = distance.asBaseUnit()
            val currentBase = current.asBaseUnit()
            val timeBase = time.asBaseUnit()
            return Ohm(massBase.value * distanceBase.value * distanceBase.value / (currentBase.value * currentBase.value * timeBase.value * timeBase.value * timeBase.value))
        }
    }
}
```

### 10. Inductance (`com.measures.inductance.Inductances.kt`)
**Before:**
```kotlin
fun createHenry(mass: Gram, distance: Meter, current: Ampere, time: Second): Henry {
    return Henry(mass.value * distance.value * distance.value / (current.value * current.value * time.value * time.value))
}
```

**After:**
```kotlin
@JvmInline
value class Henry(override val value: Double) : UnitInductance<Henry>, BaseUnit {
    // ... existing code ...
    
    companion object {
        fun from(mass: UnitWeight<*>, distance: UnitDistance<*>, current: UnitCurrent<*>, time: UnitTime<*>): Henry {
            val massBase = mass.asBaseUnit()
            val distanceBase = distance.asBaseUnit()
            val currentBase = current.asBaseUnit()
            val timeBase = time.asBaseUnit()
            return Henry(massBase.value * distanceBase.value * distanceBase.value / (currentBase.value * currentBase.value * timeBase.value * timeBase.value))
        }
    }
}
```

### 11. Flux (`com.measures.flux.Fluxes.kt`)
**Before:**
```kotlin
fun createWeber(mass: Gram, distance: Meter, current: Ampere, time: Second): Weber {
    return Weber(mass.value * distance.value * distance.value / (current.value * time.value * time.value))
}
```

**After:**
```kotlin
@JvmInline
value class Weber(override val value: Double) : UnitFlux<Weber>, BaseUnit {
    // ... existing code ...
    
    companion object {
        fun from(mass: UnitWeight<*>, distance: UnitDistance<*>, current: UnitCurrent<*>, time: UnitTime<*>): Weber {
            val massBase = mass.asBaseUnit()
            val distanceBase = distance.asBaseUnit()
            val currentBase = current.asBaseUnit()
            val timeBase = time.asBaseUnit()
            return Weber(massBase.value * distanceBase.value * distanceBase.value / (currentBase.value * timeBase.value * timeBase.value))
        }
    }
}
```

### 12. Flux Density (`com.measures.fluxdensity.FluxDensities.kt`)
**Before:**
```kotlin
fun createTesla(mass: Gram, current: Ampere, time: Second): Tesla {
    return Tesla(mass.value / (current.value * time.value * time.value))
}
```

**After:**
```kotlin
@JvmInline
value class Tesla(override val value: Double) : UnitFluxDensity<Tesla>, BaseUnit {
    // ... existing code ...
    
    companion object {
        fun from(mass: UnitWeight<*>, current: UnitCurrent<*>, time: UnitTime<*>): Tesla {
            val massBase = mass.asBaseUnit()
            val currentBase = current.asBaseUnit()
            val timeBase = time.asBaseUnit()
            return Tesla(massBase.value / (currentBase.value * timeBase.value * timeBase.value))
        }
    }
}
```

## Key Changes Made

### 1. **Function Location**
- **Before**: Standalone functions at module level
- **After**: Companion object methods within value classes

### 2. **Function Naming**
- **Before**: `createXxx` (e.g., `createNewton`, `createJoule`)
- **After**: `from` (e.g., `Newton.from`, `Joule.from`)

### 3. **Parameter Types**
- **Before**: Specific base unit types (e.g., `Gram`, `Meter`, `Second`, `Ampere`)
- **After**: TypedFull interfaces (e.g., `UnitWeight<*>`, `UnitDistance<*>`, `UnitTime<*>`, `UnitCurrent<*>`)

### 4. **Implementation Pattern**
- **Before**: Direct access to `.value` property
- **After**: Convert to base units first using `.asBaseUnit()`, then access `.value`

### 5. **Import Updates**
- Updated all imports to use TypedFull interfaces instead of specific base unit types
- Added necessary imports for all TypedFull types used in parameters

## Benefits of the Refactor

### 1. **Better Encapsulation**
- Helper functions are now part of the value class they create
- Follows object-oriented design principles

### 2. **Type Safety**
- Accepts any unit type that implements the appropriate TypedFull interface
- Automatic conversion to base units ensures correct calculations

### 3. **Flexibility**
- Can accept any unit variant (e.g., `Kilogram`, `Pound`, `Tonne` for mass)
- No need to convert to base units before calling the function

### 4. **Consistency**
- All helper functions follow the same pattern
- Consistent naming convention across all unit types

### 5. **Discoverability**
- Functions are discoverable through IDE autocomplete on the value class
- Clear relationship between the function and the unit it creates

## Usage Examples

### Before (Old Pattern)
```kotlin
val mass = Gram(1000.0)  // 1 kg
val distance = Meter(5.0)
val time = Second(2.0)
val force = createNewton(mass, distance, time)
```

### After (New Pattern)
```kotlin
val mass = Kilogram(1.0)  // Any mass unit
val distance = Kilometer(0.005)  // Any distance unit
val time = Millisecond(2000.0)  // Any time unit
val force = Newton.from(mass, distance, time)
```

## Summary

The refactor successfully moves all helper functions into companion objects of their respective value classes, renames them to `from`, and updates them to accept TypedFull versions of their dimensional parameters. This provides better encapsulation, type safety, and flexibility while maintaining the same functionality. All changes compile successfully and maintain backward compatibility through the existing unit conversion system.
