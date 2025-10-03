package com.measures.weight.troy

import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import com.measures.weight.Gram
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class TroyOunce(override val value: Double) : UnitMass<TroyOunce> {
    override fun asType(d: Double) = TroyOunce(d)
    override fun asBaseUnit() = Gram(value * 31.1034768)

    override fun plus(other: UnitMass<*>): KiloGram = UnitMass.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): KiloGram = UnitMass.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.timesUnit(this, other)
}

fun UnitMass<*>.toTroyOunce() = toUnit(TroyOunce(1.0))
