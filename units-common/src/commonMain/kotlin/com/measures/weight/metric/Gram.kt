package com.measures.weight.metric

import com.measures.Consts
import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import com.measures.weight.Kilogram
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class Gram(override val value: Double) : UnitMass<Gram> {
    override fun asType(d: Double) = Gram(d)
    override fun asBaseUnit() = Kilogram(value / 1000)

    override fun plus(other: UnitMass<*>): Kilogram = UnitMass.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): Kilogram = UnitMass.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.timesUnit(this, other)
}

fun UnitMass<*>.toGram() = toUnit(Gram(1.0))
