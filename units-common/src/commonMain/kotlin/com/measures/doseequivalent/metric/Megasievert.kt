package com.measures.doseequivalent.metric

import com.measures.Consts
import com.measures.doseequivalent.Sievert
import com.measures.doseequivalent.UnitDoseEquivalent
import kotlin.jvm.JvmInline

@JvmInline
value class Megasievert(override val value: Double) : UnitDoseEquivalent<Megasievert> {
    override fun asType(d: Double) = Megasievert(d)
    override fun asBaseUnit() = Sievert(value * Consts.MEGA)

    override operator fun plus(other: UnitDoseEquivalent<*>) = UnitDoseEquivalent.plusUnit(this, other)
    override operator fun minus(other: UnitDoseEquivalent<*>) = UnitDoseEquivalent.minusUnit(this, other)
}

fun UnitDoseEquivalent<*>.toMegasievert() = toUnit(Megasievert(1.0))
