package com.measures.doseequivalent.metric

import com.measures.Consts
import com.measures.doseequivalent.Sievert
import com.measures.doseequivalent.UnitDoseEquivalent
import kotlin.jvm.JvmInline

@JvmInline
value class Attosievert(override val value: Double) : UnitDoseEquivalent<Attosievert> {
    override fun asType(d: Double) = Attosievert(d)
    override fun asBaseUnit() = Sievert(value * Consts.ATTO)

    override operator fun plus(other: UnitDoseEquivalent<*>) = UnitDoseEquivalent.plusUnit(this, other)
    override operator fun minus(other: UnitDoseEquivalent<*>) = UnitDoseEquivalent.minusUnit(this, other)
}

fun UnitDoseEquivalent<*>.toAttosievert() = toUnit(Attosievert(1.0))
