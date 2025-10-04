package com.measures.weight.avoirdupois_1959

import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import com.measures.weight.KiloGram
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class Ounce(override val value: Double) : UnitMass<Ounce> {
    override fun asType(d: Double) = Ounce(d)
    override fun asBaseUnit() = Pound(value / 16).asBaseUnit()

    override fun plus(other: UnitMass<*>): KiloGram = UnitMass.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): KiloGram = UnitMass.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.timesUnit(this, other)
}

fun UnitMass<*>.toOunce() = toUnit(Ounce(1.0))
