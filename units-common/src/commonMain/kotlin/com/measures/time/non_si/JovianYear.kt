package com.measures.time.non_si

import com.measures.time.Second
import com.measures.time.UnitTime
import com.measures.frequency.Hertz
import kotlin.jvm.JvmInline

@JvmInline
value class JovianYear(override val value: Double) : UnitTime<JovianYear> {
    override fun asType(d: Double) = JovianYear(d)
    override fun asBaseUnit() = Second(value * 374335776.0)

    override operator fun plus(other: UnitTime<*>) = UnitTime.plusUnit(this, other)
    override operator fun minus(other: UnitTime<*>) = UnitTime.minusUnit(this, other)
    override fun inv(): Hertz = UnitTime.invUnit(this)
}

fun UnitTime<*>.toJovianYear() = toUnit(JovianYear(1.0))
