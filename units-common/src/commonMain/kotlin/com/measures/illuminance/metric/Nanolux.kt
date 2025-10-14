package com.measures.illuminance.metric

import com.measures.Consts
import com.measures.illuminance.Lux
import com.measures.illuminance.UnitIlluminance
import kotlin.jvm.JvmInline

@JvmInline
value class Nanolux(override val value: Double) : UnitIlluminance<Nanolux> {
    override fun asType(d: Double) = Nanolux(d)
    override fun asBaseUnit() = Lux(value * Consts.NANO)

    override operator fun plus(other: UnitIlluminance<*>) = UnitIlluminance.plusUnit(this, other)
    override operator fun minus(other: UnitIlluminance<*>) = UnitIlluminance.minusUnit(this, other)
}

fun UnitIlluminance<*>.toNanolux() = toUnit(Nanolux(1.0))
