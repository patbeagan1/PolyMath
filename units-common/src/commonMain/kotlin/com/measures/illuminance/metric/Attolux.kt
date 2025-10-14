package com.measures.illuminance.metric

import com.measures.Consts
import com.measures.illuminance.Lux
import com.measures.illuminance.UnitIlluminance
import kotlin.jvm.JvmInline

@JvmInline
value class Attolux(override val value: Double) : UnitIlluminance<Attolux> {
    override fun asType(d: Double) = Attolux(d)
    override fun asBaseUnit() = Lux(value * Consts.ATTO)

    override operator fun plus(other: UnitIlluminance<*>) = UnitIlluminance.plusUnit(this, other)
    override operator fun minus(other: UnitIlluminance<*>) = UnitIlluminance.minusUnit(this, other)
}

fun UnitIlluminance<*>.toAttolux() = toUnit(Attolux(1.0))
