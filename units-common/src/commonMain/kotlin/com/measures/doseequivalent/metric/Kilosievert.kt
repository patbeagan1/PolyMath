package com.measures.doseequivalent.metric

import com.measures.Consts
import com.measures.doseequivalent.Sievert
import com.measures.doseequivalent.UnitDoseEquivalent
import kotlin.jvm.JvmInline

@JvmInline
value class Kilosievert(override val value: Double) : UnitDoseEquivalent<Kilosievert> {
    override fun asType(d: Double) = Kilosievert(d)
    override fun asBaseUnit() = Sievert(value * Consts.KILO)

    override operator fun plus(other: UnitDoseEquivalent<*>) = UnitDoseEquivalent.plusUnit(this, other)
    override operator fun minus(other: UnitDoseEquivalent<*>) = UnitDoseEquivalent.minusUnit(this, other)
}

fun UnitDoseEquivalent<*>.toKilosievert() = toUnit(Kilosievert(1.0))
