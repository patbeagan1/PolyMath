package com.measures.weight.uk_imperial

import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import com.measures.weight.Kilogram
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class UKQuarter(override val value: Double) : UnitMass<UKQuarter> {
    override fun asType(d: Double) = UKQuarter(d)
    override fun asBaseUnit() = UKPound(value * 28).asBaseUnit()

    override fun plus(other: UnitMass<*>): Kilogram = UnitMass.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): Kilogram = UnitMass.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.timesUnit(this, other)
}

fun UnitMass<*>.toUKQuarter() = toUnit(UKQuarter(1.0))
