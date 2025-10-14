package com.measures.illuminance.metric

import com.measures.Consts
import com.measures.illuminance.Lux
import com.measures.illuminance.UnitIlluminance
import kotlin.jvm.JvmInline

@JvmInline
value class Picolux(override val value: Double) : UnitIlluminance<Picolux> {
    override fun asType(d: Double) = Picolux(d)
    override fun asBaseUnit() = Lux(value * Consts.PICO)

    override operator fun plus(other: UnitIlluminance<*>) = UnitIlluminance.plusUnit(this, other)
    override operator fun minus(other: UnitIlluminance<*>) = UnitIlluminance.minusUnit(this, other)
}

fun UnitIlluminance<*>.toPicolux() = toUnit(Picolux(1.0))
