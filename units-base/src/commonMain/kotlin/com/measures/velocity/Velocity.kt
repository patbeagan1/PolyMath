package com.measures.velocity

import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.acceleration.MetersPerSecondPerSecond
import com.measures.distance.Meter
import com.measures.time.UnitTime

interface UnitVelocity<T : DoubleBase> : UnitType<T, MetersPerSecond> {
    operator fun plus(other: UnitVelocity<*>): MetersPerSecond
    operator fun minus(other: UnitVelocity<*>): MetersPerSecond
    operator fun times(other: UnitTime<*>): Meter
    operator fun div(other: UnitTime<*>): MetersPerSecondPerSecond
}

fun UnitVelocity<*>.plusUnit(other: UnitVelocity<*>): MetersPerSecond =
    MetersPerSecond(this.asBaseUnit().value + other.asBaseUnit().value)

fun UnitVelocity<*>.minusUnit(other: UnitVelocity<*>): MetersPerSecond =
    MetersPerSecond(this.asBaseUnit().value - other.asBaseUnit().value)

fun UnitVelocity<*>.timesUnit(other: UnitTime<*>): Meter =
    Meter(this.asBaseUnit().value * other.asBaseUnit().value)

fun UnitVelocity<*>.divUnit(other: UnitTime<*>): MetersPerSecondPerSecond =
    MetersPerSecondPerSecond(this.asBaseUnit().value / other.asBaseUnit().value)
