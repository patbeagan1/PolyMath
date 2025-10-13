package com.measures.weight.uk_imperial

import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import com.measures.weight.Kilogram
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class UKHundredweight(override val value: Double) : UnitMass<UKHundredweight> {
    override fun asType(d: Double) = UKHundredweight(d)
    override fun asBaseUnit() = UKPound(value * 112).asBaseUnit()

    override fun plus(other: UnitMass<*>): Kilogram = UnitMass.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): Kilogram = UnitMass.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.timesUnit(this, other)
}

fun UnitMass<*>.toUKHundredweight() = toUnit(UKHundredweight(1.0))
