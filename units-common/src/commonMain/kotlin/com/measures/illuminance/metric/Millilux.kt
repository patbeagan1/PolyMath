package com.measures.illuminance.metric

import com.measures.Consts
import com.measures.illuminance.Lux
import com.measures.illuminance.UnitIlluminance
import kotlin.jvm.JvmInline

@JvmInline
value class Millilux(override val value: Double) : UnitIlluminance<Millilux> {
    override fun asType(d: Double) = Millilux(d)
    override fun asBaseUnit() = Lux(value * Consts.MILLI)

    override operator fun plus(other: UnitIlluminance<*>) = UnitIlluminance.plusUnit(this, other)
    override operator fun minus(other: UnitIlluminance<*>) = UnitIlluminance.minusUnit(this, other)
}

fun UnitIlluminance<*>.toMillilux() = toUnit(Millilux(1.0))
