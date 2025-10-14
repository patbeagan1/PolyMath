package com.measures.doseequivalent.metric

import com.measures.Consts
import com.measures.doseequivalent.Sievert
import com.measures.doseequivalent.UnitDoseEquivalent
import kotlin.jvm.JvmInline

@JvmInline
value class Microsievert(override val value: Double) : UnitDoseEquivalent<Microsievert> {
    override fun asType(d: Double) = Microsievert(d)
    override fun asBaseUnit() = Sievert(value * Consts.MICRO)

    override operator fun plus(other: UnitDoseEquivalent<*>) = UnitDoseEquivalent.plusUnit(this, other)
    override operator fun minus(other: UnitDoseEquivalent<*>) = UnitDoseEquivalent.minusUnit(this, other)
}

fun UnitDoseEquivalent<*>.toMicrosievert() = toUnit(Microsievert(1.0))
