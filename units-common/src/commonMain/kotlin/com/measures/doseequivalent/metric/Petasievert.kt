package com.measures.doseequivalent.metric

import com.measures.Consts
import com.measures.doseequivalent.Sievert
import com.measures.doseequivalent.UnitDoseEquivalent
import kotlin.jvm.JvmInline

@JvmInline
value class Petasievert(override val value: Double) : UnitDoseEquivalent<Petasievert> {
    override fun asType(d: Double) = Petasievert(d)
    override fun asBaseUnit() = Sievert(value * Consts.PETA)

    override operator fun plus(other: UnitDoseEquivalent<*>) = UnitDoseEquivalent.plusUnit(this, other)
    override operator fun minus(other: UnitDoseEquivalent<*>) = UnitDoseEquivalent.minusUnit(this, other)
}

fun UnitDoseEquivalent<*>.toPetasievert() = toUnit(Petasievert(1.0))
