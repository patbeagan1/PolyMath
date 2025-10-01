package com.measures.frequency

import com.measures.BaseUnit
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

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