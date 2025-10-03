package com.measures.weight.avoirdupois_corrected

import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import com.measures.weight.KiloGram
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class Pound(override val value: Double) : UnitMass<Pound> {
    override fun asType(d: Double) = Pound(d)
    override fun asBaseUnit() = KiloGram(value * 0.45359237)

    override fun plus(other: UnitMass<*>): KiloGram = UnitMass.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): KiloGram = UnitMass.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.timesUnit(this, other)
}

fun UnitMass<*>.toPound() = toUnit(Pound(1.0))
