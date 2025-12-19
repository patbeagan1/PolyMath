package com.measures.weight.us_customary

import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import com.measures.mass.Kilogram
import com.measures.mass.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class USPound(override val value: Double) : UnitMass<USPound> {
    override fun asType(d: Double) = USPound(d)
    override fun asBaseUnit() = Kilogram(value * 0.45359237)

    override fun plus(other: UnitMass<*>): Kilogram = UnitMass.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): Kilogram = UnitMass.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.timesUnit(this, other)
}

fun UnitMass<*>.toUSPound() = toUnit(USPound(1.0))
