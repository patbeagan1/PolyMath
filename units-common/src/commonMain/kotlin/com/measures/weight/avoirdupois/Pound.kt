package com.measures.weight.avoirdupois

import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import com.measures.weight.UnitMass
import com.measures.weight.KiloGram
import com.measures.weight.Gram
import kotlin.jvm.JvmInline

@JvmInline
value class Pound(override val value: Double) : UnitMass<Pound> {
    override fun asType(d: Double) = Pound(d)
    override fun asBaseUnit() = Gram(value * 28.35).asBaseUnit()

    override fun plus(other: UnitMass<*>): KiloGram = UnitMass.Companion.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): KiloGram = UnitMass.Companion.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.Companion.timesUnit(this, other)
}

fun UnitMass<*>.toPound() = toUnit(Pound(1.0))
