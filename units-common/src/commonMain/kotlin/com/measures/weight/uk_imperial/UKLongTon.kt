package com.measures.weight.uk_imperial

import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import com.measures.weight.Kilogram
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class UKLongTon(override val value: Double) : UnitMass<UKLongTon> {
    override fun asType(d: Double) = UKLongTon(d)
    override fun asBaseUnit() = UKPound(value * 2240).asBaseUnit()

    override fun plus(other: UnitMass<*>): Kilogram = UnitMass.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): Kilogram = UnitMass.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.timesUnit(this, other)
}

fun UnitMass<*>.toUKLongTon() = toUnit(UKLongTon(1.0))
