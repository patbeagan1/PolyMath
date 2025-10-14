package com.measures.doseequivalent.metric

import com.measures.Consts
import com.measures.doseequivalent.Sievert
import com.measures.doseequivalent.UnitDoseEquivalent
import kotlin.jvm.JvmInline

@JvmInline
value class Yottasievert(override val value: Double) : UnitDoseEquivalent<Yottasievert> {
    override fun asType(d: Double) = Yottasievert(d)
    override fun asBaseUnit() = Sievert(value * Consts.YOTTA)

    override operator fun plus(other: UnitDoseEquivalent<*>) = UnitDoseEquivalent.plusUnit(this, other)
    override operator fun minus(other: UnitDoseEquivalent<*>) = UnitDoseEquivalent.minusUnit(this, other)
}

fun UnitDoseEquivalent<*>.toYottasievert() = toUnit(Yottasievert(1.0))
