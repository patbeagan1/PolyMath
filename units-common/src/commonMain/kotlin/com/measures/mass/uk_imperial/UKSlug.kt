package com.measures.weight.uk_imperial

import com.measures.acceleration.UnitAcceleration
import com.measures.force.Newton
import com.measures.mass.Kilogram
import com.measures.mass.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class UKSlug(override val value: Double) : UnitMass<UKSlug> {
    override fun asType(d: Double) = UKSlug(d)
    override fun asBaseUnit() = Kilogram(value * 14.59390294).asBaseUnit()

    override fun plus(other: UnitMass<*>): Kilogram = UnitMass.plusUnit(this, other)
    override fun minus(other: UnitMass<*>): Kilogram = UnitMass.minusUnit(this, other)
    override fun times(other: UnitAcceleration<*>): Newton = UnitMass.timesUnit(this, other)
}

fun UnitMass<*>.toUKSlug() = toUnit(UKSlug(1.0))
