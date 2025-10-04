package com.measures.weight.troy_1959

import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import com.measures.weight.KiloGram
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class TroyGrain(override val value: Double) : UnitMass<TroyGrain> {
    override fun asType(d: Double) = TroyGrain(d)
    override fun asBaseUnit() = KiloGram(value * 0.06479891 / 1000)

    override fun plus(other: UnitMass<*>): KiloGram = UnitMass.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): KiloGram = UnitMass.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.timesUnit(this, other)
}

fun UnitMass<*>.toTroyGrain() = toUnit(TroyGrain(1.0))
