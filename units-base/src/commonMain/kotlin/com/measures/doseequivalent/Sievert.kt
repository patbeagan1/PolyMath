package com.measures.doseequivalent

import com.measures.BaseUnit
import kotlin.jvm.JvmInline

@JvmInline
value class Sievert(override val value: Double) : UnitDoseEquivalent<Sievert>, BaseUnit {
    override fun asType(d: Double) = Sievert(d)
    override fun asBaseUnit() = this

    override operator fun plus(other: UnitDoseEquivalent<*>) = UnitDoseEquivalent.plusUnit(this, other)
    override operator fun minus(other: UnitDoseEquivalent<*>) = UnitDoseEquivalent.minusUnit(this, other)
}

fun UnitDoseEquivalent<*>.toSievert() = this.asBaseUnit()

