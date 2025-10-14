package com.measures.illuminance.metric

import com.measures.Consts
import com.measures.illuminance.Lux
import com.measures.illuminance.UnitIlluminance
import kotlin.jvm.JvmInline

@JvmInline
value class Femtolux(override val value: Double) : UnitIlluminance<Femtolux> {
    override fun asType(d: Double) = Femtolux(d)
    override fun asBaseUnit() = Lux(value * Consts.FEMTO)

    override operator fun plus(other: UnitIlluminance<*>) = UnitIlluminance.plusUnit(this, other)
    override operator fun minus(other: UnitIlluminance<*>) = UnitIlluminance.minusUnit(this, other)
}

fun UnitIlluminance<*>.toFemtolux() = toUnit(Femtolux(1.0))
