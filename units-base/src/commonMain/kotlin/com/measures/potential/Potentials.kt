package com.measures.potential

import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.charge.UnitCharge
import com.measures.energy.Joule

interface UnitPotential<T : DoubleBase> : UnitType<T, Volt> {
    operator fun plus(other: UnitPotential<*>): Volt
    operator fun minus(other: UnitPotential<*>): Volt
    operator fun times(other: UnitCharge<*>): Joule
}

fun UnitPotential<*>.plusUnit(other: UnitPotential<*>): Volt =
    Volt(this.asBaseUnit().value + other.asBaseUnit().value)

fun UnitPotential<*>.minusUnit(other: UnitPotential<*>): Volt =
    Volt(this.asBaseUnit().value - other.asBaseUnit().value)

// Potential × Charge = Energy
fun UnitPotential<*>.timesUnit(other: UnitCharge<*>): Joule =
    Joule(this.asBaseUnit().value * other.asBaseUnit().value)
