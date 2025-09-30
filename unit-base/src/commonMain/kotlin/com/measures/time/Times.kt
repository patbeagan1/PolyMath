package com.measures.time

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.frequency.Hertz
import kotlin.jvm.JvmInline

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

@JvmInline
value class Second(override val value: Double) : UnitTime<Second>, BaseUnit {
    override fun asType(d: Double) = Second(d)
    override fun asBaseUnit() = this

    override operator fun plus(other: UnitTime<*>) = (this as UnitTime<*>).plusUnit(other)
    override operator fun minus(other: UnitTime<*>) = (this as UnitTime<*>).minusUnit(other)
    override operator fun inv() = (this as UnitTime<*>).invUnit()
}

// Non-SI time units have been moved to unit-common module

// Conversion functions using toUnit
fun UnitTime<*>.toSecond() = this.asBaseUnit()
