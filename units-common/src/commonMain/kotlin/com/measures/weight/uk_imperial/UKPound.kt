package com.measures.weight.uk_imperial

import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import com.measures.weight.Kilogram
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class UKPound(override val value: Double) : UnitMass<UKPound> {
    override fun asType(d: Double) = UKPound(d)
    override fun asBaseUnit() = Kilogram(value * 0.45359237)

    override fun plus(other: UnitMass<*>): Kilogram = UnitMass.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): Kilogram = UnitMass.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.timesUnit(this, other)
}

fun UnitMass<*>.toUKPound() = toUnit(UKPound(1.0))
