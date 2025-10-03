package com.measures.amount.non_si

import com.measures.amount.Mole
import com.measures.amount.UnitAmount
import kotlin.jvm.JvmInline

@JvmInline
value class Nanomole(override val value: Double) : UnitAmount<Nanomole> {
    override fun asType(d: Double) = Nanomole(d)
    override fun asBaseUnit() = Mole(this.value * 1E-9)

    override operator fun plus(other: UnitAmount<*>) = UnitAmount.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitAmount<*>) = UnitAmount.Companion.minusUnit(this, other)
}

fun UnitAmount<*>.toNanomole() = toUnit(Nanomole(1.0))
