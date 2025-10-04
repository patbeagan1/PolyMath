package com.measures.weight.metric

import com.measures.Consts
import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import com.measures.weight.KiloGram
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class Yoctogram(override val value: Double) : UnitMass<Yoctogram> {
    override fun asType(d: Double) = Yoctogram(d)
    override fun asBaseUnit() = KiloGram(value * Consts.YOCTO / 1000)

    override fun plus(other: UnitMass<*>): KiloGram = UnitMass.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): KiloGram = UnitMass.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.timesUnit(this, other)
}

fun UnitMass<*>.toYoctogram() = toUnit(Yoctogram(1.0))
