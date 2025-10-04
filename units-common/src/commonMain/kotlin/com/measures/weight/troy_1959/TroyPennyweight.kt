package com.measures.weight.troy_1959

import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import com.measures.weight.KiloGram
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class TroyPennyweight(override val value: Double) : UnitMass<TroyPennyweight> {
    override fun asType(d: Double) = TroyPennyweight(d)
    override fun asBaseUnit() = TroyGrain(value * 24).asBaseUnit()

    override fun plus(other: UnitMass<*>): KiloGram = UnitMass.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): KiloGram = UnitMass.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.timesUnit(this, other)
}

fun UnitMass<*>.toTroyPennyweight() = toUnit(TroyPennyweight(1.0))
