package com.measures.weight.apothecaries

import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import com.measures.weight.Gram
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class ApothecariesScruple(override val value: Double) : UnitMass<ApothecariesScruple> {
    override fun asType(d: Double) = ApothecariesScruple(d)
    override fun asBaseUnit() = ApothecariesGrain(value * 20).asBaseUnit()

    override fun plus(other: UnitMass<*>): KiloGram = UnitMass.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): KiloGram = UnitMass.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.timesUnit(this, other)
}

fun UnitMass<*>.toApothecariesScruple() = toUnit(ApothecariesScruple(1.0))
