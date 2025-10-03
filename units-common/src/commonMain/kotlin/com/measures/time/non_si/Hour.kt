package com.measures.time.non_si

import com.measures.time.Second
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

@JvmInline
value class Hour(override val value: Double) : UnitTime<Hour> {
override fun asType(d: Double) = Hour(d)
    override fun asBaseUnit() = Second(this.value * 3600.0)

    override operator fun plus(other: UnitTime<*>) = UnitTime.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitTime<*>) = UnitTime.Companion.minusUnit(this, other)
    override operator fun inv() = UnitTime.Companion.invUnit(this)
}

fun UnitTime<*>.toHour() = toUnit(Hour(1.0))
