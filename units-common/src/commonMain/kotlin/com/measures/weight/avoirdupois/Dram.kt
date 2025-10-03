package com.measures.weight.avoirdupois

import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class Dram(override val value: Double) : UnitMass<Dram> {
    override fun asType(d: Double) = Dram(d)
    override fun asBaseUnit() = Pound(value / 256).asBaseUnit()

    override fun plus(other: UnitMass<*>): KiloGram = UnitMass.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): KiloGram = UnitMass.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.timesUnit(this, other)
}

fun UnitMass<*>.toDram() = toUnit(Dram(1.0))
