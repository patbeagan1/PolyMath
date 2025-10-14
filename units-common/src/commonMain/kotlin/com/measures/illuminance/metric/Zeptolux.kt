package com.measures.illuminance.metric

import com.measures.Consts
import com.measures.illuminance.Lux
import com.measures.illuminance.UnitIlluminance
import kotlin.jvm.JvmInline

@JvmInline
value class Zeptolux(override val value: Double) : UnitIlluminance<Zeptolux> {
    override fun asType(d: Double) = Zeptolux(d)
    override fun asBaseUnit() = Lux(value * Consts.ZEPTO)

    override operator fun plus(other: UnitIlluminance<*>) = UnitIlluminance.plusUnit(this, other)
    override operator fun minus(other: UnitIlluminance<*>) = UnitIlluminance.minusUnit(this, other)
}

fun UnitIlluminance<*>.toZeptolux() = toUnit(Zeptolux(1.0))
