package com.measures.weight.uk_imperial

import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import com.measures.mass.Kilogram
import com.measures.mass.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class UKOunce(override val value: Double) : UnitMass<UKOunce> {
    override fun asType(d: Double) = UKOunce(d)
    override fun asBaseUnit() = UKPound(value / 16).asBaseUnit()

    override fun plus(other: UnitMass<*>): Kilogram = UnitMass.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): Kilogram = UnitMass.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.timesUnit(this, other)
}

fun UnitMass<*>.toUKOunce() = toUnit(UKOunce(1.0))
