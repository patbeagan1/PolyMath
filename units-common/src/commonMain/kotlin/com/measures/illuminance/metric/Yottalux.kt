package com.measures.illuminance.metric

import com.measures.Consts
import com.measures.illuminance.Lux
import com.measures.illuminance.UnitIlluminance
import kotlin.jvm.JvmInline

@JvmInline
value class Yottalux(override val value: Double) : UnitIlluminance<Yottalux> {
    override fun asType(d: Double) = Yottalux(d)
    override fun asBaseUnit() = Lux(value * Consts.YOTTA)

    override operator fun plus(other: UnitIlluminance<*>) = UnitIlluminance.plusUnit(this, other)
    override operator fun minus(other: UnitIlluminance<*>) = UnitIlluminance.minusUnit(this, other)
}

fun UnitIlluminance<*>.toYottalux() = toUnit(Yottalux(1.0))
