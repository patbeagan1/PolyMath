package com.measures.weight.apothecaries

import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import com.measures.weight.KiloGram
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class ApothecariesPound(override val value: Double) : UnitMass<ApothecariesPound> {
    override fun asType(d: Double) = ApothecariesPound(d)
    override fun asBaseUnit() = ApothecariesOunce(value * 12).asBaseUnit()

    override fun plus(other: UnitMass<*>): KiloGram = UnitMass.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): KiloGram = UnitMass.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.timesUnit(this, other)
}

fun UnitMass<*>.toApothecariesPound() = toUnit(ApothecariesPound(1.0))
