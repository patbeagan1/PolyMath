package com.measures.distance

import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.time.UnitTime
import com.measures.velocity.MetersPerSecond
import com.measures.volume.Liter

interface UnitDistance<T : DoubleBase> : UnitType<T, Meter> {
    operator fun plus(other: UnitDistance<*>): Meter
    operator fun minus(other: UnitDistance<*>): Meter
    operator fun times(other: UnitDistance<*>): SquareMeter
    operator fun times(other: UnitArea<*>): Liter
    operator fun div(other: UnitTime<*>): MetersPerSecond
}

fun UnitDistance<*>.plusUnit(other: UnitDistance<*>): Meter =
    Meter(this.asBaseUnit().value + other.asBaseUnit().value)

fun UnitDistance<*>.minusUnit(other: UnitDistance<*>): Meter =
    Meter(this.asBaseUnit().value - other.asBaseUnit().value)

fun UnitDistance<*>.timesUnit(other: UnitDistance<*>): SquareMeter =
    SquareMeter(this.asBaseUnit().value * other.asBaseUnit().value)

fun UnitDistance<*>.timesUnit(other: UnitArea<*>): Liter =
    Liter(this.asBaseUnit().value * other.asBaseUnit().value * 1000)

// Distance ÷ Time = Velocity
fun UnitDistance<*>.divUnit(other: UnitTime<*>): MetersPerSecond =
    MetersPerSecond(this.asBaseUnit().value / other.asBaseUnit().value)
