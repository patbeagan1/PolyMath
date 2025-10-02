package com.measures.weight.troy

import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import com.measures.weight.UnitMass
import com.measures.weight.KiloGram
import com.measures.weight.troy.TroyOunce
import kotlin.jvm.JvmInline

@JvmInline
value class TroyPennyweight(override val value: Double) : UnitMass<TroyPennyweight> {
    override fun asType(d: Double) = TroyPennyweight(d)
    override fun asBaseUnit() = TroyOunce(value / 20).asBaseUnit()

    override fun plus(other: UnitMass<*>): KiloGram = UnitMass.Companion.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): KiloGram = UnitMass.Companion.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.Companion.timesUnit(this, other)
}

fun UnitMass<*>.toTroyPennyweight() = toUnit(TroyPennyweight(1.0))
