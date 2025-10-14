package com.measures.illuminance.metric

import com.measures.Consts
import com.measures.illuminance.Lux
import com.measures.illuminance.UnitIlluminance
import kotlin.jvm.JvmInline

@JvmInline
value class Kilolux(override val value: Double) : UnitIlluminance<Kilolux> {
    override fun asType(d: Double) = Kilolux(d)
    override fun asBaseUnit() = Lux(value * Consts.KILO)

    override operator fun plus(other: UnitIlluminance<*>) = UnitIlluminance.plusUnit(this, other)
    override operator fun minus(other: UnitIlluminance<*>) = UnitIlluminance.minusUnit(this, other)
}

fun UnitIlluminance<*>.toKilolux() = toUnit(Kilolux(1.0))
