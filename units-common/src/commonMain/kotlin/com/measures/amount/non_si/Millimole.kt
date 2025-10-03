package com.measures.amount.non_si

import com.measures.amount.Mole
import com.measures.amount.UnitAmount
import kotlin.jvm.JvmInline

@JvmInline
value class Millimole(override val value: Double) : UnitAmount<Millimole> {
    override fun asType(d: Double) = Millimole(d)
    override fun asBaseUnit() = Mole(this.value * 0.001)

    override operator fun plus(other: UnitAmount<*>) = UnitAmount.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitAmount<*>) = UnitAmount.Companion.minusUnit(this, other)
}

fun UnitAmount<*>.toMillimole() = toUnit(Millimole(1.0))
