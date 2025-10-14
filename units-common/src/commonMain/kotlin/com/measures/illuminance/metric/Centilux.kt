package com.measures.illuminance.metric

import com.measures.Consts
import com.measures.illuminance.Lux
import com.measures.illuminance.UnitIlluminance
import kotlin.jvm.JvmInline

@JvmInline
value class Centilux(override val value: Double) : UnitIlluminance<Centilux> {
    override fun asType(d: Double) = Centilux(d)
    override fun asBaseUnit() = Lux(value * Consts.CENTI)

    override operator fun plus(other: UnitIlluminance<*>) = UnitIlluminance.plusUnit(this, other)
    override operator fun minus(other: UnitIlluminance<*>) = UnitIlluminance.minusUnit(this, other)
}

fun UnitIlluminance<*>.toCentilux() = toUnit(Centilux(1.0))
