package com.measures.doseequivalent.metric

import com.measures.Consts
import com.measures.doseequivalent.Sievert
import com.measures.doseequivalent.UnitDoseEquivalent
import kotlin.jvm.JvmInline

@JvmInline
value class Zettasievert(override val value: Double) : UnitDoseEquivalent<Zettasievert> {
    override fun asType(d: Double) = Zettasievert(d)
    override fun asBaseUnit() = Sievert(value * Consts.ZETTA)

    override operator fun plus(other: UnitDoseEquivalent<*>) = UnitDoseEquivalent.plusUnit(this, other)
    override operator fun minus(other: UnitDoseEquivalent<*>) = UnitDoseEquivalent.minusUnit(this, other)
}

fun UnitDoseEquivalent<*>.toZettasievert() = toUnit(Zettasievert(1.0))
