package com.measures.weight.us_customary

import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import com.measures.weight.Kilogram
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class USShortTon(override val value: Double) : UnitMass<USShortTon> {
    override fun asType(d: Double) = USShortTon(d)
    override fun asBaseUnit() = USPound(value * 2000).asBaseUnit()

    override fun plus(other: UnitMass<*>): Kilogram = UnitMass.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): Kilogram = UnitMass.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.timesUnit(this, other)
}

fun UnitMass<*>.toUSShortTon() = toUnit(USShortTon(1.0))
