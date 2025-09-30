package com.measures.frequency

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.time.Second
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

typealias UnitFrequency<T> = UnitFrequencyType<T>

interface UnitFrequencyType<T : DoubleBase> : UnitType<T, Hertz>

@JvmInline
value class Hertz(override val value: Double) : UnitFrequency<Hertz>, BaseUnit {
    override fun asType(d: Double) = Hertz(d)
    override fun asBaseUnit() = this

    operator fun plus(other: UnitFrequency<*>) = (this as UnitFrequency<*>).minusUnit(other)
    operator fun minus(other: UnitFrequency<*>) = (this as UnitFrequency<*>).minusUnit(other)
    operator fun inv() = (this as UnitFrequency<*>).inv()

    companion object {
        fun from(time: UnitTime<*>): Hertz = Hertz(1.0 / time.asBaseUnit().value)
    }
}

fun UnitFrequencyType<*>.plusUnit(other: UnitFrequencyType<*>): Hertz =
    Hertz(this.asBaseUnit().value + other.asBaseUnit().value)

fun UnitFrequencyType<*>.minusUnit(other: UnitFrequencyType<*>): Hertz =
    Hertz(this.asBaseUnit().value - other.asBaseUnit().value)

// Frequency to Time conversion: 1/frequency = time
operator fun UnitFrequencyType<*>.inv(): Second =
    Second(1.0 / this.asBaseUnit().value)

fun UnitFrequency<*>.toHertz() = this.asBaseUnit()

