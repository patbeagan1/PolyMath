package com.measures.weight.avoirdupois_1959

import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import com.measures.weight.KiloGram
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class ShortTon(override val value: Double) : UnitMass<ShortTon> {
    override fun asType(d: Double) = ShortTon(d)
    override fun asBaseUnit() = Pound(value * 2000).asBaseUnit()

    override fun plus(other: UnitMass<*>): KiloGram = UnitMass.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): KiloGram = UnitMass.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.timesUnit(this, other)
}

fun UnitMass<*>.toShortTon() = toUnit(ShortTon(1.0))
