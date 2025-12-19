package com.measures.doseequivalent.metric

import com.measures.Consts
import com.measures.doseequivalent.Sievert
import com.measures.doseequivalent.UnitDoseEquivalent
import kotlin.jvm.JvmInline

@JvmInline
value class Picosievert(override val value: Double) : UnitDoseEquivalent<Picosievert> {
    override fun asType(d: Double) = Picosievert(d)
    override fun asBaseUnit() = Sievert(value * Consts.PICO)

    override operator fun plus(other: UnitDoseEquivalent<*>) = UnitDoseEquivalent.plusUnit(this, other)
    override operator fun minus(other: UnitDoseEquivalent<*>) = UnitDoseEquivalent.minusUnit(this, other)
}

fun UnitDoseEquivalent<*>.toPicosievert() = toUnit(Picosievert(1.0))
