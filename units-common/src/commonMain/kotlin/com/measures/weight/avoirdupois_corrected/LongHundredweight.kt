package com.measures.weight.avoirdupois_corrected

import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import com.measures.weight.KiloGram
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class LongHundredweight(override val value: Double) : UnitMass<LongHundredweight> {
    override fun asType(d: Double) = LongHundredweight(d)
    override fun asBaseUnit() = Pound(value * 112).asBaseUnit()

    override fun plus(other: UnitMass<*>): KiloGram = UnitMass.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): KiloGram = UnitMass.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.timesUnit(this, other)
}

fun UnitMass<*>.toLongHundredweight() = toUnit(LongHundredweight(1.0))
