package com.measures.time.non_si

import com.measures.time.Second
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

@JvmInline
value class Nanosecond(override val value: Double) : UnitTime<Nanosecond> {
    override fun asType(d: Double) = Nanosecond(d)
    override fun asBaseUnit() = Second(this.value * 1E-9)

    override operator fun plus(other: UnitTime<*>) = UnitTime.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitTime<*>) = UnitTime.Companion.minusUnit(this, other)
    override operator fun inv() = UnitTime.Companion.invUnit(this)
}

fun UnitTime<*>.toNanosecond() = toUnit(Nanosecond(1.0))
