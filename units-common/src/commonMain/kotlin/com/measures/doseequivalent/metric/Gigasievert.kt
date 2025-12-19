package com.measures.doseequivalent.metric

import com.measures.Consts
import com.measures.doseequivalent.Sievert
import com.measures.doseequivalent.UnitDoseEquivalent
import kotlin.jvm.JvmInline

@JvmInline
value class Gigasievert(override val value: Double) : UnitDoseEquivalent<Gigasievert> {
    override fun asType(d: Double) = Gigasievert(d)
    override fun asBaseUnit() = Sievert(value * Consts.GIGA)

    override operator fun plus(other: UnitDoseEquivalent<*>) = UnitDoseEquivalent.plusUnit(this, other)
    override operator fun minus(other: UnitDoseEquivalent<*>) = UnitDoseEquivalent.minusUnit(this, other)
}

fun UnitDoseEquivalent<*>.toGigasievert() = toUnit(Gigasievert(1.0))
