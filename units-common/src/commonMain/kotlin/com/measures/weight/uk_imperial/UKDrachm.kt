package com.measures.weight.uk_imperial

import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import com.measures.weight.Kilogram
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class UKDrachm(override val value: Double) : UnitMass<UKDrachm> {
    override fun asType(d: Double) = UKDrachm(d)
    override fun asBaseUnit() = UKPound(value / 256).asBaseUnit()

    override fun plus(other: UnitMass<*>): Kilogram = UnitMass.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): Kilogram = UnitMass.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.timesUnit(this, other)
}

fun UnitMass<*>.toUKDrachm() = toUnit(UKDrachm(1.0))
