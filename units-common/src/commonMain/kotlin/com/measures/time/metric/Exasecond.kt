package com.measures.time.metric

import com.measures.Consts
import com.measures.time.Second
import com.measures.time.UnitTime
import com.measures.frequency.Hertz
import kotlin.jvm.JvmInline

@JvmInline
value class Exasecond(override val value: Double) : UnitTime<Exasecond> {
    override fun asType(d: Double) = Exasecond(d)
    override fun asBaseUnit() = Second(value * Consts.EXA)

    override operator fun plus(other: UnitTime<*>) = UnitTime.plusUnit(this, other)
    override operator fun minus(other: UnitTime<*>) = UnitTime.minusUnit(this, other)
    override fun inv(): Hertz = UnitTime.invUnit(this)
}

fun UnitTime<*>.toExasecond() = toUnit(Exasecond(1.0))
