package com.measures.weight.metric

import com.measures.Consts
import com.measures.weight.KiloGram
import com.measures.weight.UnitMass
import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import kotlin.jvm.JvmInline

@JvmInline
value class Yottakilogram(override val value: Double) : UnitMass<Yottakilogram> {
    override fun asType(d: Double) = Yottakilogram(d)
    override fun asBaseUnit() = KiloGram(value * Consts.YOTTA)

    override fun plus(other: UnitMass<*>): KiloGram = UnitMass.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): KiloGram = UnitMass.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.timesUnit(this, other)
}

fun UnitMass<*>.toYottakilogram() = toUnit(Yottakilogram(1.0))
