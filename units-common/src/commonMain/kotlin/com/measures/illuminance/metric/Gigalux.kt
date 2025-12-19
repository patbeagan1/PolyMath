package com.measures.illuminance.metric

import com.measures.Consts
import com.measures.illuminance.Lux
import com.measures.illuminance.UnitIlluminance
import kotlin.jvm.JvmInline

@JvmInline
value class Gigalux(override val value: Double) : UnitIlluminance<Gigalux> {
    override fun asType(d: Double) = Gigalux(d)
    override fun asBaseUnit() = Lux(value * Consts.GIGA)

    override operator fun plus(other: UnitIlluminance<*>) = UnitIlluminance.plusUnit(this, other)
    override operator fun minus(other: UnitIlluminance<*>) = UnitIlluminance.minusUnit(this, other)
}

fun UnitIlluminance<*>.toGigalux() = toUnit(Gigalux(1.0))
