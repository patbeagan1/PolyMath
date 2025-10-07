package com.measures.potential

import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.charge.UnitCharge
import com.measures.energy.Joule

interface UnitPotential<T : DoubleBase> : UnitType<T, Volt> {
    operator fun plus(other: UnitPotential<*>): Volt
    operator fun minus(other: UnitPotential<*>): Volt
    operator fun times(other: UnitCharge<*>): Joule

    companion object {
        fun plusUnit(potential: UnitPotential<*>, other: UnitPotential<*>): Volt =
            Volt(potential.asBaseUnit().value + other.asBaseUnit().value)

        fun minusUnit(potential: UnitPotential<*>, other: UnitPotential<*>): Volt =
            Volt(potential.asBaseUnit().value - other.asBaseUnit().value)

        // Potential × Charge = Energy
        fun timesUnit(potential: UnitPotential<*>, other: UnitCharge<*>): Joule =
            Joule(potential.asBaseUnit().value * other.asBaseUnit().value)
    }
}
