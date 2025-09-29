package com.measures.frequency

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.time.UnitTime
import com.measures.time.Second
import kotlin.jvm.JvmInline

typealias UnitFrequency<T> = UnitFrequencyType<T>

interface UnitFrequencyType<T : DoubleBase> : UnitType<T, Hertz>

@JvmInline
value class Hertz(override val value: Double) : UnitFrequency<Hertz>, BaseUnit {
    override fun asType(d: Double) = Hertz(d)
    override fun asBaseUnit() = this

    operator fun plus(other: UnitFrequency<*>) = (this as UnitFrequency<*>).plus(other)
    operator fun minus(other: UnitFrequency<*>) = (this as UnitFrequency<*>).minus(other)
    operator fun inv() = (this as UnitFrequency<*>).inv()
    
    companion object {
        fun from(time: UnitTime<*>): Hertz {
            val timeBase = time.asBaseUnit()
            return Hertz(1.0 / timeBase.value)
        }
    }
}

operator fun UnitFrequencyType<*>.plus(other: UnitFrequencyType<*>): Hertz =
    Hertz(this.asBaseUnit().value + other.asBaseUnit().value)

operator fun UnitFrequencyType<*>.minus(other: UnitFrequencyType<*>): Hertz =
    Hertz(this.asBaseUnit().value - other.asBaseUnit().value)

// Frequency to Time conversion: 1/frequency = time
operator fun UnitFrequencyType<*>.inv(): Second =
    Second(1.0 / this.asBaseUnit().value)

fun UnitFrequency<*>.toHertz() = this.asBaseUnit()

