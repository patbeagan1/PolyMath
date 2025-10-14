package com.measures.doseequivalent.metric

import com.measures.Consts
import com.measures.doseequivalent.Sievert
import com.measures.doseequivalent.UnitDoseEquivalent
import kotlin.jvm.JvmInline

@JvmInline
value class Centisievert(override val value: Double) : UnitDoseEquivalent<Centisievert> {
    override fun asType(d: Double) = Centisievert(d)
    override fun asBaseUnit() = Sievert(value * Consts.CENTI)

    override operator fun plus(other: UnitDoseEquivalent<*>) = UnitDoseEquivalent.plusUnit(this, other)
    override operator fun minus(other: UnitDoseEquivalent<*>) = UnitDoseEquivalent.minusUnit(this, other)
}

fun UnitDoseEquivalent<*>.toCentisievert() = toUnit(Centisievert(1.0))
