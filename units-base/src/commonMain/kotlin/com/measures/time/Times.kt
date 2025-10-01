package com.measures.time

import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.frequency.Hertz

interface UnitTime<T : DoubleBase> : UnitType<T, Second> {
    operator fun plus(other: UnitTime<*>): Second
    operator fun minus(other: UnitTime<*>): Second
    operator fun inv(): Hertz
}

fun UnitTime<*>.plusUnit(other: UnitTime<*>): Second =
    Second(this.asBaseUnit().value + other.asBaseUnit().value)

fun UnitTime<*>.minusUnit(other: UnitTime<*>): Second =
    Second(this.asBaseUnit().value - other.asBaseUnit().value)

// Time to Frequency conversion: 1/time = frequency
fun UnitTime<*>.invUnit(): Hertz =
    Hertz(1.0 / this.asBaseUnit().value)
