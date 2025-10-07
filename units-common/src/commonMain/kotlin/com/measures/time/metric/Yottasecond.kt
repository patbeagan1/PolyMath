package com.measures.time.metric

import com.measures.Consts
import com.measures.time.Second
import com.measures.time.UnitTime
import com.measures.frequency.Hertz
import kotlin.jvm.JvmInline

@JvmInline
value class Yottasecond(override val value: Double) : UnitTime<Yottasecond> {
    override fun asType(d: Double) = Yottasecond(d)
    override fun asBaseUnit() = Second(value * Consts.YOTTA)

    override operator fun plus(other: UnitTime<*>) = UnitTime.plusUnit(this, other)
    override operator fun minus(other: UnitTime<*>) = UnitTime.minusUnit(this, other)
    override fun inv(): Hertz = UnitTime.invUnit(this)
}

fun UnitTime<*>.toYottasecond() = toUnit(Yottasecond(1.0))
