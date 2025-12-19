package com.measures.weight.us_customary

import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import com.measures.mass.Kilogram
import com.measures.mass.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class USLongTon(override val value: Double) : UnitMass<USLongTon> {
    override fun asType(d: Double) = USLongTon(d)
    override fun asBaseUnit() = USPound(value * 2240).asBaseUnit()

    override fun plus(other: UnitMass<*>): Kilogram = UnitMass.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): Kilogram = UnitMass.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.timesUnit(this, other)
}

fun UnitMass<*>.toUSLongTon() = toUnit(USLongTon(1.0))
