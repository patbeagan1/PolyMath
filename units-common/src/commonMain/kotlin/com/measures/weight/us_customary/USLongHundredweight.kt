package com.measures.weight.us_customary

import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import com.measures.weight.KiloGram
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class USLongHundredweight(override val value: Double) : UnitMass<USLongHundredweight> {
    override fun asType(d: Double) = USLongHundredweight(d)
    override fun asBaseUnit() = USPound(value * 112).asBaseUnit()

    override fun plus(other: UnitMass<*>): KiloGram = UnitMass.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): KiloGram = UnitMass.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.timesUnit(this, other)
}

fun UnitMass<*>.toUSLongHundredweight() = toUnit(USLongHundredweight(1.0))
