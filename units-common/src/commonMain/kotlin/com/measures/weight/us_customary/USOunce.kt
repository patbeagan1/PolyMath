package com.measures.weight.us_customary

import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import com.measures.weight.Kilogram
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class USOunce(override val value: Double) : UnitMass<USOunce> {
    override fun asType(d: Double) = USOunce(d)
    override fun asBaseUnit() = USPound(value / 16).asBaseUnit()

    override fun plus(other: UnitMass<*>): Kilogram = UnitMass.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): Kilogram = UnitMass.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.timesUnit(this, other)
}

fun UnitMass<*>.toUSOunce() = toUnit(USOunce(1.0))
