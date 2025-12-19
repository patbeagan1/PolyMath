package com.measures.illuminance.metric

import com.measures.Consts
import com.measures.illuminance.Lux
import com.measures.illuminance.UnitIlluminance
import kotlin.jvm.JvmInline

@JvmInline
value class Zettalux(override val value: Double) : UnitIlluminance<Zettalux> {
    override fun asType(d: Double) = Zettalux(d)
    override fun asBaseUnit() = Lux(value * Consts.ZETTA)

    override operator fun plus(other: UnitIlluminance<*>) = UnitIlluminance.plusUnit(this, other)
    override operator fun minus(other: UnitIlluminance<*>) = UnitIlluminance.minusUnit(this, other)
}

fun UnitIlluminance<*>.toZettalux() = toUnit(Zettalux(1.0))
