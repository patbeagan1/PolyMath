package com.measures.illuminance.metric

import com.measures.Consts
import com.measures.illuminance.Lux
import com.measures.illuminance.UnitIlluminance
import kotlin.jvm.JvmInline

@JvmInline
value class Dekalux(override val value: Double) : UnitIlluminance<Dekalux> {
    override fun asType(d: Double) = Dekalux(d)
    override fun asBaseUnit() = Lux(value * Consts.DEKA)

    override operator fun plus(other: UnitIlluminance<*>) = UnitIlluminance.plusUnit(this, other)
    override operator fun minus(other: UnitIlluminance<*>) = UnitIlluminance.minusUnit(this, other)
}

fun UnitIlluminance<*>.toDekalux() = toUnit(Dekalux(1.0))
