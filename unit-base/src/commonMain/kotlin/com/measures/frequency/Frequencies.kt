package com.measures.frequency

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitTypedFull
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

typealias UnitFrequency<T> = UnitFrequencyTypedFull<T>

interface UnitFrequencyTypedFull<T : DoubleBase> : UnitTypedFull<T, Hertz> {
    operator fun plus(other: UnitFrequencyTypedFull<*>) =
        Hertz(this.asBaseUnit().value + other.asBaseUnit().value)

    operator fun minus(other: UnitFrequencyTypedFull<*>) =
        Hertz(this.asBaseUnit().value - other.asBaseUnit().value)
}

@JvmInline
value class Hertz(override val value: Double) : UnitFrequency<Hertz>, BaseUnit {
    override fun asType(d: Double) = Hertz(d)
    override fun asBaseUnit() = this
    
    companion object {
        fun from(time: UnitTime<*>): Hertz {
            val timeBase = time.asBaseUnit()
            return Hertz(1.0 / timeBase.value)
        }
    }
}

fun UnitFrequency<*>.toHertz() = this.asBaseUnit()

