package com.measures.time.non_si

import com.measures.time.Second
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

@JvmInline
value class Microsecond(override val value: Double) : UnitTime<Microsecond> {
override fun asType(d: Double) = Microsecond(d)
    override fun asBaseUnit() = Second(this.value * 1E-6)

    override operator fun plus(other: UnitTime<*>) = UnitTime.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitTime<*>) = UnitTime.Companion.minusUnit(this, other)
    override operator fun inv() = UnitTime.Companion.invUnit(this)
}

fun UnitTime<*>.toMicrosecond() = toUnit(Microsecond(1.0))
