package com.measures.doseequivalent.metric

import com.measures.Consts
import com.measures.doseequivalent.Sievert
import com.measures.doseequivalent.UnitDoseEquivalent
import kotlin.jvm.JvmInline

@JvmInline
value class Dekasievert(override val value: Double) : UnitDoseEquivalent<Dekasievert> {
    override fun asType(d: Double) = Dekasievert(d)
    override fun asBaseUnit() = Sievert(value * Consts.DEKA)

    override operator fun plus(other: UnitDoseEquivalent<*>) = UnitDoseEquivalent.plusUnit(this, other)
    override operator fun minus(other: UnitDoseEquivalent<*>) = UnitDoseEquivalent.minusUnit(this, other)
}

fun UnitDoseEquivalent<*>.toDekasievert() = toUnit(Dekasievert(1.0))
