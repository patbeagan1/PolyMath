package com.measures.time.non_si

import com.measures.time.Second
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

@JvmInline
value class Minute(override val value: Double) : UnitTime<Minute> {
override fun asType(d: Double) = Minute(d)
    override fun asBaseUnit() = Second(this.value * 60.0)

    override operator fun plus(other: UnitTime<*>) = UnitTime.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitTime<*>) = UnitTime.Companion.minusUnit(this, other)
    override operator fun inv() = UnitTime.Companion.invUnit(this)
}

fun UnitTime<*>.toMinute() = toUnit(Minute(1.0))
