package com.measures.illuminance.metric

import com.measures.Consts
import com.measures.illuminance.Lux
import com.measures.illuminance.UnitIlluminance
import kotlin.jvm.JvmInline

@JvmInline
value class Yoctolux(override val value: Double) : UnitIlluminance<Yoctolux> {
    override fun asType(d: Double) = Yoctolux(d)
    override fun asBaseUnit() = Lux(value * Consts.YOCTO)

    override operator fun plus(other: UnitIlluminance<*>) = UnitIlluminance.plusUnit(this, other)
    override operator fun minus(other: UnitIlluminance<*>) = UnitIlluminance.minusUnit(this, other)
}

fun UnitIlluminance<*>.toYoctolux() = toUnit(Yoctolux(1.0))
