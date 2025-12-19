package com.measures.doseequivalent.metric

import com.measures.Consts
import com.measures.doseequivalent.Sievert
import com.measures.doseequivalent.UnitDoseEquivalent
import kotlin.jvm.JvmInline

@JvmInline
value class Exasievert(override val value: Double) : UnitDoseEquivalent<Exasievert> {
    override fun asType(d: Double) = Exasievert(d)
    override fun asBaseUnit() = Sievert(value * Consts.EXA)

    override operator fun plus(other: UnitDoseEquivalent<*>) = UnitDoseEquivalent.plusUnit(this, other)
    override operator fun minus(other: UnitDoseEquivalent<*>) = UnitDoseEquivalent.minusUnit(this, other)
}

fun UnitDoseEquivalent<*>.toExasievert() = toUnit(Exasievert(1.0))
