package com.measures.time.non_si

import com.measures.time.Second
import com.measures.time.UnitTime
import com.measures.frequency.Hertz
import kotlin.jvm.JvmInline

@JvmInline
value class NeptunianYear(override val value: Double) : UnitTime<NeptunianYear> {
    override fun asType(d: Double) = NeptunianYear(d)
    override fun asBaseUnit() = Second(value * 5200416000.0)

    override operator fun plus(other: UnitTime<*>) = UnitTime.plusUnit(this, other)
    override operator fun minus(other: UnitTime<*>) = UnitTime.minusUnit(this, other)
    override fun inv(): Hertz = UnitTime.invUnit(this)
}

fun UnitTime<*>.toNeptunianYear() = toUnit(NeptunianYear(1.0))
