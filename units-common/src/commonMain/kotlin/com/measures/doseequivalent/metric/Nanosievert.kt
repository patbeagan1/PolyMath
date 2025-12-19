package com.measures.doseequivalent.metric

import com.measures.Consts
import com.measures.doseequivalent.Sievert
import com.measures.doseequivalent.UnitDoseEquivalent
import kotlin.jvm.JvmInline

@JvmInline
value class Nanosievert(override val value: Double) : UnitDoseEquivalent<Nanosievert> {
    override fun asType(d: Double) = Nanosievert(d)
    override fun asBaseUnit() = Sievert(value * Consts.NANO)

    override operator fun plus(other: UnitDoseEquivalent<*>) = UnitDoseEquivalent.plusUnit(this, other)
    override operator fun minus(other: UnitDoseEquivalent<*>) = UnitDoseEquivalent.minusUnit(this, other)
}

fun UnitDoseEquivalent<*>.toNanosievert() = toUnit(Nanosievert(1.0))
