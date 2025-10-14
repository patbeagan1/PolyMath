package com.measures.doseequivalent.metric

import com.measures.Consts
import com.measures.doseequivalent.Sievert
import com.measures.doseequivalent.UnitDoseEquivalent
import kotlin.jvm.JvmInline

@JvmInline
value class Hectosievert(override val value: Double) : UnitDoseEquivalent<Hectosievert> {
    override fun asType(d: Double) = Hectosievert(d)
    override fun asBaseUnit() = Sievert(value * Consts.HECTO)

    override operator fun plus(other: UnitDoseEquivalent<*>) = UnitDoseEquivalent.plusUnit(this, other)
    override operator fun minus(other: UnitDoseEquivalent<*>) = UnitDoseEquivalent.minusUnit(this, other)
}

fun UnitDoseEquivalent<*>.toHectosievert() = toUnit(Hectosievert(1.0))
