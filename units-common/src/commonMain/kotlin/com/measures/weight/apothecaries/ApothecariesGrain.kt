package com.measures.weight.apothecaries

import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import com.measures.weight.Gram
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class ApothecariesGrain(override val value: Double) : UnitMass<ApothecariesGrain> {
    override fun asType(d: Double) = ApothecariesGrain(d)
    override fun asBaseUnit() = Gram(value * 0.06479891)

    override fun plus(other: UnitMass<*>): KiloGram = UnitMass.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): KiloGram = UnitMass.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.timesUnit(this, other)
}

fun UnitMass<*>.toApothecariesGrain() = toUnit(ApothecariesGrain(1.0))
