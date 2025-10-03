package com.measures.amount.non_si

import com.measures.amount.Mole
import com.measures.amount.UnitAmount
import kotlin.jvm.JvmInline

@JvmInline
value class Kilomole(override val value: Double) : UnitAmount<Kilomole> {
    override fun asType(d: Double) = Kilomole(d)
    override fun asBaseUnit() = Mole(this.value * 1000.0)

    override operator fun plus(other: UnitAmount<*>) = UnitAmount.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitAmount<*>) = UnitAmount.Companion.minusUnit(this, other)
}

fun UnitAmount<*>.toKilomole() = toUnit(Kilomole(1.0))
