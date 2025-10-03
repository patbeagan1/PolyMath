package com.measures.weight.metric

import com.measures.Consts
import com.measures.weight.KiloGram
import com.measures.weight.UnitMass
import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import kotlin.jvm.JvmInline

@JvmInline
value class Dekakilogram(override val value: Double) : UnitMass<Dekakilogram> {
    override fun asType(d: Double) = Dekakilogram(d)
    override fun asBaseUnit() = KiloGram(value * Consts.DEKA)

    override fun plus(other: UnitMass<*>): KiloGram = UnitMass.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): KiloGram = UnitMass.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.timesUnit(this, other)
}

fun UnitMass<*>.toDekakilogram() = toUnit(Dekakilogram(1.0))
