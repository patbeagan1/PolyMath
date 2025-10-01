package com.measures.acceleration

import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.force.Newton
import com.measures.velocity.UnitVelocity
import com.measures.velocity.MetersPerSecond
import com.measures.time.UnitTime
import com.measures.weight.UnitMass

interface UnitAcceleration<T : DoubleBase> : UnitType<T, MetersPerSecondPerSecond> {
    operator fun plus(other: UnitAcceleration<*>): MetersPerSecondPerSecond
    operator fun minus(other: UnitAcceleration<*>): MetersPerSecondPerSecond
    operator fun times(other: UnitTime<*>): MetersPerSecond
    operator fun times(other: UnitMass<*>): Newton
}

 fun UnitAcceleration<*>.plusUnit(other: UnitAcceleration<*>): MetersPerSecondPerSecond =
    MetersPerSecondPerSecond(this.asBaseUnit().value + other.asBaseUnit().value)

 fun UnitAcceleration<*>.minusUnit(other: UnitAcceleration<*>): MetersPerSecondPerSecond =
    MetersPerSecondPerSecond(this.asBaseUnit().value - other.asBaseUnit().value)

 fun UnitAcceleration<*>.timesUnit(other: UnitTime<*>): MetersPerSecond =
    MetersPerSecond(this.asBaseUnit().value * other.asBaseUnit().value)

 fun UnitAcceleration<*>.timesUnit(other: UnitMass<*>): Newton =
    Newton(this.asBaseUnit().value * other.asBaseUnit().value)
