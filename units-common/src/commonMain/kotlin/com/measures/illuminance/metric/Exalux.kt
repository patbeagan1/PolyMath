package com.measures.illuminance.metric

import com.measures.Consts
import com.measures.illuminance.Lux
import com.measures.illuminance.UnitIlluminance
import kotlin.jvm.JvmInline

@JvmInline
value class Exalux(override val value: Double) : UnitIlluminance<Exalux> {
    override fun asType(d: Double) = Exalux(d)
    override fun asBaseUnit() = Lux(value * Consts.EXA)

    override operator fun plus(other: UnitIlluminance<*>) = UnitIlluminance.plusUnit(this, other)
    override operator fun minus(other: UnitIlluminance<*>) = UnitIlluminance.minusUnit(this, other)
}

fun UnitIlluminance<*>.toExalux() = toUnit(Exalux(1.0))
