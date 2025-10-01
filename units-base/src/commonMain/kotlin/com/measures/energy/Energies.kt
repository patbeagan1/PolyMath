package com.measures.energy

import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.charge.UnitCharge
import com.measures.potential.Volt
import com.measures.power.Watt
import com.measures.time.UnitTime

interface UnitEnergy<T : DoubleBase> : UnitType<T, Joule> {
    operator fun plus(other: UnitEnergy<*>): Joule
    operator fun minus(other: UnitEnergy<*>): Joule
    operator fun div(other: UnitTime<*>): Watt
    operator fun div(other: UnitCharge<*>): Volt
}

fun UnitEnergy<*>.plusUnit(other: UnitEnergy<*>): Joule =
    Joule(this.asBaseUnit().value + other.asBaseUnit().value)

 fun UnitEnergy<*>.minusUnit(other: UnitEnergy<*>): Joule =
    Joule(this.asBaseUnit().value - other.asBaseUnit().value)

 fun UnitEnergy<*>.divUnit(other: UnitTime<*>): Watt =
    Watt(this.asBaseUnit().value / other.asBaseUnit().value)

// Energy ÷ Charge = Potential
 fun UnitEnergy<*>.divUnit(other: UnitCharge<*>): Volt =
    Volt(this.asBaseUnit().value / other.asBaseUnit().value)
