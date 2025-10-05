package com.measures.weight.non_si

import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import com.measures.weight.KiloGram
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class Carat(override val value: Double) : UnitMass<Carat> {
    override fun asType(d: Double) = Carat(d)
    override fun asBaseUnit() = Kilogram(value * 0.0002)

    override fun plus(other: UnitMass<*>): KiloGram = UnitMass.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): KiloGram = UnitMass.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.timesUnit(this, other)
}

fun UnitMass<*>.toCarat() = toUnit(Carat(1.0))
