package com.measures.weight.us_customary

import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import com.measures.mass.Kilogram
import com.measures.mass.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class USGrain(override val value: Double) : UnitMass<USGrain> {
    override fun asType(d: Double) = USGrain(d)
    override fun asBaseUnit() = USPound(value / 7000).asBaseUnit()

    override fun plus(other: UnitMass<*>): Kilogram = UnitMass.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): Kilogram = UnitMass.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.timesUnit(this, other)
}

fun UnitMass<*>.toUSGrain() = toUnit(USGrain(1.0))
