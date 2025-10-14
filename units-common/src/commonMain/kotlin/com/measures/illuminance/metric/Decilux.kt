package com.measures.illuminance.metric

import com.measures.Consts
import com.measures.illuminance.Lux
import com.measures.illuminance.UnitIlluminance
import kotlin.jvm.JvmInline

@JvmInline
value class Decilux(override val value: Double) : UnitIlluminance<Decilux> {
    override fun asType(d: Double) = Decilux(d)
    override fun asBaseUnit() = Lux(value * Consts.DECI)

    override operator fun plus(other: UnitIlluminance<*>) = UnitIlluminance.plusUnit(this, other)
    override operator fun minus(other: UnitIlluminance<*>) = UnitIlluminance.minusUnit(this, other)
}

fun UnitIlluminance<*>.toDecilux() = toUnit(Decilux(1.0))
