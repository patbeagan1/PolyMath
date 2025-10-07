package com.measures.time.non_si

import com.measures.time.Second
import com.measures.time.UnitTime
import com.measures.frequency.Hertz
import kotlin.jvm.JvmInline

@JvmInline
value class Year(override val value: Double) : UnitTime<Year> {
    override fun asType(d: Double) = Year(d)
    override fun asBaseUnit() = Second(value * 31557600.0)

    override operator fun plus(other: UnitTime<*>) = UnitTime.plusUnit(this, other)
    override operator fun minus(other: UnitTime<*>) = UnitTime.minusUnit(this, other)
    override fun inv(): Hertz = UnitTime.invUnit(this)
}

fun UnitTime<*>.toYear() = toUnit(Year(1.0))
