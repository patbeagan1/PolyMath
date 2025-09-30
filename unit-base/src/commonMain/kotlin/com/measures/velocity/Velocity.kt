package com.measures.velocity

import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.distance.UnitDistance
import com.measures.distance.Meter
import com.measures.time.UnitTime

interface UnitVelocity<T : DoubleBase> : UnitType<T, MetersPerSecond> 

operator fun UnitVelocity<*>.plus(other: UnitVelocity<*>): MetersPerSecond =
    MetersPerSecond(this.asBaseUnit().value + other.asBaseUnit().value)

operator fun UnitVelocity<*>.minus(other: UnitVelocity<*>): MetersPerSecond =
    MetersPerSecond(this.asBaseUnit().value - other.asBaseUnit().value)

// Velocity × Time = Distance
operator fun UnitVelocity<*>.times(other: UnitTime<*>): Meter =
    Meter(this.asBaseUnit().value * other.asBaseUnit().value)

// Distance ÷ Time = Velocity
operator fun UnitDistance<*>.div(other: UnitTime<*>): MetersPerSecond =
    MetersPerSecond(this.asBaseUnit().value / other.asBaseUnit().value)
