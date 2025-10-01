package com.measures.frequency

import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.time.Second

interface UnitFrequency<T : DoubleBase> : UnitType<T, Hertz> {
    operator fun plus(other: UnitFrequency<*>): Hertz
    operator fun minus(other: UnitFrequency<*>): Hertz
    operator fun inv(): Second
}

fun UnitFrequency<*>.plusUnit(other: UnitFrequency<*>): Hertz =
    Hertz(this.asBaseUnit().value + other.asBaseUnit().value)

fun UnitFrequency<*>.minusUnit(other: UnitFrequency<*>): Hertz =
    Hertz(this.asBaseUnit().value - other.asBaseUnit().value)

fun UnitFrequency<*>.invUnit(): Second =
    Second(1.0 / this.asBaseUnit().value)
