package com.measures.weight

import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton

interface UnitMass<T : DoubleBase> : UnitType<T, KiloGram> {
    operator fun plus(other: UnitMass<*>): KiloGram
    operator fun minus(other: UnitMass<*>): KiloGram
    operator fun times(other: UnitAcceleration<*>): Newton
}

fun UnitMass<*>.plusUnit(other: UnitMass<*>): KiloGram =
    KiloGram(this.asBaseUnit().value + other.asBaseUnit().value)

fun UnitMass<*>.minusUnit(other: UnitMass<*>): KiloGram =
    KiloGram(this.asBaseUnit().value - other.asBaseUnit().value)

// Weight × Acceleration = Force (mass × acceleration = force)
fun UnitMass<*>.timesUnit(other: UnitAcceleration<*>): Newton =
    Newton(this.asBaseUnit().value * other.asBaseUnit().value)
