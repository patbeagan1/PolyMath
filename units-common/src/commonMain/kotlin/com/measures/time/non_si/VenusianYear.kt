package com.measures.time.non_si

import com.measures.time.Second
import com.measures.time.UnitTime
import com.measures.frequency.Hertz
import kotlin.jvm.JvmInline

@JvmInline
value class VenusianYear(override val value: Double) : UnitTime<VenusianYear> {
    override fun asType(d: Double) = VenusianYear(d)
    override fun asBaseUnit() = Second(value * 19414149.12)

    override operator fun plus(other: UnitTime<*>) = UnitTime.plusUnit(this, other)
    override operator fun minus(other: UnitTime<*>) = UnitTime.minusUnit(this, other)
    override fun inv(): Hertz = UnitTime.invUnit(this)
}

fun UnitTime<*>.toVenusianYear() = toUnit(VenusianYear(1.0))
