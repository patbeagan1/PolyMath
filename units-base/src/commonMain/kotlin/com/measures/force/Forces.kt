package com.measures.force

import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.acceleration.MetersPerSecondPerSecond
import com.measures.acceleration.UnitAcceleration
import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import com.measures.energy.Joule
import com.measures.pressure.Pascal
import com.measures.weight.KiloGram
import com.measures.weight.UnitMass

interface UnitForce<T : DoubleBase> : UnitType<T, Newton> {
    operator fun plus(other: UnitForce<*>): Newton
    operator fun minus(other: UnitForce<*>): Newton
    operator fun div(other: UnitArea<*>): Pascal
    operator fun times(other: UnitDistance<*>): Joule
    operator fun div(other: UnitMass<*>): MetersPerSecondPerSecond
    operator fun div(other: UnitAcceleration<*>): KiloGram
}

fun UnitForce<*>.plusUnit(other: UnitForce<*>): Newton =
    Newton(this.asBaseUnit().value + other.asBaseUnit().value)

fun UnitForce<*>.minusUnit(other: UnitForce<*>): Newton =
    Newton(this.asBaseUnit().value - other.asBaseUnit().value)

fun UnitForce<*>.divUnit(other: UnitArea<*>): Pascal =
    Pascal(this.asBaseUnit().value / other.asBaseUnit().value)

// Force × Distance = Energy
fun UnitForce<*>.timesUnit(other: UnitDistance<*>): Joule =
    Joule(this.asBaseUnit().value * other.asBaseUnit().value)

// Force ÷ Mass = Acceleration
fun UnitForce<*>.divUnit(other: UnitMass<*>): MetersPerSecondPerSecond =
    MetersPerSecondPerSecond(this.asBaseUnit().value / other.asBaseUnit().value)

// Force ÷ Acceleration = Mass
fun UnitForce<*>.divUnit(other: UnitAcceleration<*>): KiloGram =
    KiloGram(this.asBaseUnit().value / other.asBaseUnit().value)
