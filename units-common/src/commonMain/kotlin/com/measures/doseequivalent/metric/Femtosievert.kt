package com.measures.doseequivalent.metric

import com.measures.Consts
import com.measures.doseequivalent.Sievert
import com.measures.doseequivalent.UnitDoseEquivalent
import kotlin.jvm.JvmInline

@JvmInline
value class Femtosievert(override val value: Double) : UnitDoseEquivalent<Femtosievert> {
    override fun asType(d: Double) = Femtosievert(d)
    override fun asBaseUnit() = Sievert(value * Consts.FEMTO)

    override operator fun plus(other: UnitDoseEquivalent<*>) = UnitDoseEquivalent.plusUnit(this, other)
    override operator fun minus(other: UnitDoseEquivalent<*>) = UnitDoseEquivalent.minusUnit(this, other)
}

fun UnitDoseEquivalent<*>.toFemtosievert() = toUnit(Femtosievert(1.0))
