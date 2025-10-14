package com.measures.illuminance.metric

import com.measures.Consts
import com.measures.illuminance.Lux
import com.measures.illuminance.UnitIlluminance
import kotlin.jvm.JvmInline

@JvmInline
value class Teralux(override val value: Double) : UnitIlluminance<Teralux> {
    override fun asType(d: Double) = Teralux(d)
    override fun asBaseUnit() = Lux(value * Consts.TERA)

    override operator fun plus(other: UnitIlluminance<*>) = UnitIlluminance.plusUnit(this, other)
    override operator fun minus(other: UnitIlluminance<*>) = UnitIlluminance.minusUnit(this, other)
}

fun UnitIlluminance<*>.toTeralux() = toUnit(Teralux(1.0))
