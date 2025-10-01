package com.measures.area

import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.distance.Meter
import com.measures.distance.UnitDistance
import com.measures.volume.Liter

interface UnitArea<T : DoubleBase> : UnitType<T, SquareMeter>{
    operator fun plus(other: UnitArea<*>): SquareMeter
    operator fun minus(other: UnitArea<*>): SquareMeter
    operator fun times(other: UnitDistance<*>): Liter
    operator fun div(other: UnitDistance<*>): Meter
}

fun UnitArea<*>.divUnit(other: UnitDistance<*>): Meter =
    Meter(this.asBaseUnit().value / other.asBaseUnit().value)

fun UnitArea<*>.plusUnit(other: UnitArea<*>): SquareMeter =
    SquareMeter(this.asBaseUnit().value + other.asBaseUnit().value)

fun UnitArea<*>.minusUnit(other: UnitArea<*>): SquareMeter =
    SquareMeter(this.asBaseUnit().value - other.asBaseUnit().value)

fun UnitArea<*>.timesUnit(other: UnitDistance<*>): Liter =
    Liter(this.asBaseUnit().value * other.asBaseUnit().value * 1000)
