package com.measures.weight.apothecaries

import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import com.measures.weight.KiloGram
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class ApothecariesOunce(override val value: Double) : UnitMass<ApothecariesOunce> {
    override fun asType(d: Double) = ApothecariesOunce(d)
    override fun asBaseUnit() = ApothecariesDram(value * 8).asBaseUnit()

    override fun plus(other: UnitMass<*>): KiloGram = UnitMass.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): KiloGram = UnitMass.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.timesUnit(this, other)
}

fun UnitMass<*>.toApothecariesOunce() = toUnit(ApothecariesOunce(1.0))
