package com.measures.weight.metric

import com.measures.Consts
import com.measures.weight.KiloGram
import com.measures.weight.UnitMass
import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import kotlin.jvm.JvmInline

@JvmInline
value class Microkilogram(override val value: Double) : UnitMass<Microkilogram> {
    override fun asType(d: Double) = Microkilogram(d)
    override fun asBaseUnit() = KiloGram(value * Consts.MICRO)

    override fun plus(other: UnitMass<*>): KiloGram = UnitMass.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): KiloGram = UnitMass.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.timesUnit(this, other)
}

fun UnitMass<*>.toMicrokilogram() = toUnit(Microkilogram(1.0))
