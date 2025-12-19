package com.measures.doseequivalent.metric

import com.measures.Consts
import com.measures.doseequivalent.Sievert
import com.measures.doseequivalent.UnitDoseEquivalent
import kotlin.jvm.JvmInline

@JvmInline
value class Millisievert(override val value: Double) : UnitDoseEquivalent<Millisievert> {
    override fun asType(d: Double) = Millisievert(d)
    override fun asBaseUnit() = Sievert(value * Consts.MILLI)

    override operator fun plus(other: UnitDoseEquivalent<*>) = UnitDoseEquivalent.plusUnit(this, other)
    override operator fun minus(other: UnitDoseEquivalent<*>) = UnitDoseEquivalent.minusUnit(this, other)
}

fun UnitDoseEquivalent<*>.toMillisievert() = toUnit(Millisievert(1.0))
