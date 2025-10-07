package com.measures.time.metric

import com.measures.Consts
import com.measures.time.Second
import com.measures.time.UnitTime
import com.measures.frequency.Hertz
import kotlin.jvm.JvmInline

@JvmInline
value class Zettasecond(override val value: Double) : UnitTime<Zettasecond> {
    override fun asType(d: Double) = Zettasecond(d)
    override fun asBaseUnit() = Second(value * Consts.ZETTA)

    override operator fun plus(other: UnitTime<*>) = UnitTime.plusUnit(this, other)
    override operator fun minus(other: UnitTime<*>) = UnitTime.minusUnit(this, other)
    override fun inv(): Hertz = UnitTime.invUnit(this)
}

fun UnitTime<*>.toZettasecond() = toUnit(Zettasecond(1.0))
