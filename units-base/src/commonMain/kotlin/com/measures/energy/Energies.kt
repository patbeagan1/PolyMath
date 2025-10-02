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

    companion object {
        fun plusUnit(energy: UnitEnergy<*>, other: UnitEnergy<*>): Joule =
            Joule(energy.asBaseUnit().value + other.asBaseUnit().value)

        fun minusUnit(energy: UnitEnergy<*>, other: UnitEnergy<*>): Joule =
            Joule(energy.asBaseUnit().value - other.asBaseUnit().value)

        fun divUnit(energy: UnitEnergy<*>, other: UnitTime<*>): Watt =
            Watt(energy.asBaseUnit().value / other.asBaseUnit().value)

        // Energy ÷ Charge = Potential
        fun divUnit(energy: UnitEnergy<*>, other: UnitCharge<*>): Volt =
            Volt(energy.asBaseUnit().value / other.asBaseUnit().value)
    }
}
