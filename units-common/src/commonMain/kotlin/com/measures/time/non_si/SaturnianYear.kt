package com.measures.time.non_si

import com.measures.time.Second
import com.measures.time.UnitTime
import com.measures.frequency.Hertz
import kotlin.jvm.JvmInline

@JvmInline
value class SaturnianYear(override val value: Double) : UnitTime<SaturnianYear> {
    override fun asType(d: Double) = SaturnianYear(d)
    override fun asBaseUnit() = Second(value * 929596608.0)

    override operator fun plus(other: UnitTime<*>) = UnitTime.plusUnit(this, other)
    override operator fun minus(other: UnitTime<*>) = UnitTime.minusUnit(this, other)
    override fun inv(): Hertz = UnitTime.invUnit(this)
}

fun UnitTime<*>.toSaturnianYear() = toUnit(SaturnianYear(1.0))
