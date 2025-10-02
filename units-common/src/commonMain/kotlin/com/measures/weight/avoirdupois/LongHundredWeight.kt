package com.measures.weight.avoirdupois

import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import com.measures.weight.UnitMass
import com.measures.weight.KiloGram
import com.measures.weight.avoirdupois.Pound
import kotlin.jvm.JvmInline

@JvmInline
value class LongHundredWeight(override val value: Double) : UnitMass<LongHundredWeight> {
    override fun asType(d: Double) = LongHundredWeight(d)
    override fun asBaseUnit() = Pound(value * 112).asBaseUnit()

    override fun plus(other: UnitMass<*>): KiloGram = UnitMass.Companion.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): KiloGram = UnitMass.Companion.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.Companion.timesUnit(this, other)
}

fun UnitMass<*>.toLongHundredWeight() = toUnit(LongHundredWeight(1.0))
