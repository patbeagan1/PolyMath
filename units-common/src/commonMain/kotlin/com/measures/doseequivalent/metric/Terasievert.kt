package com.measures.doseequivalent.metric

import com.measures.Consts
import com.measures.doseequivalent.Sievert
import com.measures.doseequivalent.UnitDoseEquivalent
import kotlin.jvm.JvmInline

@JvmInline
value class Terasievert(override val value: Double) : UnitDoseEquivalent<Terasievert> {
    override fun asType(d: Double) = Terasievert(d)
    override fun asBaseUnit() = Sievert(value * Consts.TERA)

    override operator fun plus(other: UnitDoseEquivalent<*>) = UnitDoseEquivalent.plusUnit(this, other)
    override operator fun minus(other: UnitDoseEquivalent<*>) = UnitDoseEquivalent.minusUnit(this, other)
}

fun UnitDoseEquivalent<*>.toTerasievert() = toUnit(Terasievert(1.0))
