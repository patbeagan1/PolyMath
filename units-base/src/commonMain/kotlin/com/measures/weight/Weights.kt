package com.measures.weight

import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton

interface UnitMass<T : DoubleBase> : UnitType<T, Kilogram> {
    operator fun plus(other: UnitMass<*>): Kilogram
    operator fun minus(other: UnitMass<*>): Kilogram
    operator fun times(other: UnitAcceleration<*>): Newton

    companion object {
        fun plusUnit(mass: UnitMass<*>, other: UnitMass<*>): Kilogram =
            Kilogram(mass.asBaseUnit().value + other.asBaseUnit().value)

        fun minusUnit(mass: UnitMass<*>, other: UnitMass<*>): Kilogram =
            Kilogram(mass.asBaseUnit().value - other.asBaseUnit().value)

        // Weight × Acceleration = Force (mass × acceleration = force)
        fun timesUnit(mass: UnitMass<*>, other: UnitAcceleration<*>): Newton =
            Newton(mass.asBaseUnit().value * other.asBaseUnit().value)
    }
}
