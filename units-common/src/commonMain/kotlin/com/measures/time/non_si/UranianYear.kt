package com.measures.time.non_si

import com.measures.time.Second
import com.measures.time.UnitTime
import com.measures.frequency.Hertz
import kotlin.jvm.JvmInline

@JvmInline
value class UranianYear(override val value: Double) : UnitTime<UranianYear> {
    override fun asType(d: Double) = UranianYear(d)
    override fun asBaseUnit() = Second(value * 2651486400.0)

    override operator fun plus(other: UnitTime<*>) = UnitTime.plusUnit(this, other)
    override operator fun minus(other: UnitTime<*>) = UnitTime.minusUnit(this, other)
    override fun inv(): Hertz = UnitTime.invUnit(this)
}

fun UnitTime<*>.toUranianYear() = toUnit(UranianYear(1.0))
