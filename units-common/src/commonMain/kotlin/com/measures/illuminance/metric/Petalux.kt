package com.measures.illuminance.metric

import com.measures.Consts
import com.measures.illuminance.Lux
import com.measures.illuminance.UnitIlluminance
import kotlin.jvm.JvmInline

@JvmInline
value class Petalux(override val value: Double) : UnitIlluminance<Petalux> {
    override fun asType(d: Double) = Petalux(d)
    override fun asBaseUnit() = Lux(value * Consts.PETA)

    override operator fun plus(other: UnitIlluminance<*>) = UnitIlluminance.plusUnit(this, other)
    override operator fun minus(other: UnitIlluminance<*>) = UnitIlluminance.minusUnit(this, other)
}

fun UnitIlluminance<*>.toPetalux() = toUnit(Petalux(1.0))
