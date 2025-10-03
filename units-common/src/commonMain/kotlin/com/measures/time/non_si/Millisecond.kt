package com.measures.time.non_si

import com.measures.time.Second
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

@JvmInline
value class Millisecond(override val value: Double) : UnitTime<Millisecond> {
override fun asType(d: Double) = Millisecond(d)
    override fun asBaseUnit() = Second(this.value * 0.001)

    override operator fun plus(other: UnitTime<*>) = UnitTime.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitTime<*>) = UnitTime.Companion.minusUnit(this, other)
    override operator fun inv() = UnitTime.Companion.invUnit(this)
}

fun UnitTime<*>.toMillisecond() = toUnit(Millisecond(1.0))
