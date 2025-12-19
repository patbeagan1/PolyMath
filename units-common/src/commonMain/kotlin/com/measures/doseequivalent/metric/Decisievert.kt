package com.measures.doseequivalent.metric

import com.measures.Consts
import com.measures.doseequivalent.Sievert
import com.measures.doseequivalent.UnitDoseEquivalent
import kotlin.jvm.JvmInline

@JvmInline
value class Decisievert(override val value: Double) : UnitDoseEquivalent<Decisievert> {
    override fun asType(d: Double) = Decisievert(d)
    override fun asBaseUnit() = Sievert(value * Consts.DECI)

    override operator fun plus(other: UnitDoseEquivalent<*>) = UnitDoseEquivalent.plusUnit(this, other)
    override operator fun minus(other: UnitDoseEquivalent<*>) = UnitDoseEquivalent.minusUnit(this, other)
}

fun UnitDoseEquivalent<*>.toDecisievert() = toUnit(Decisievert(1.0))
