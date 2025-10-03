package com.measures.amount.non_si

import com.measures.amount.Mole
import com.measures.amount.UnitAmount
import kotlin.jvm.JvmInline

@JvmInline
value class Picomole(override val value: Double) : UnitAmount<Picomole> {
override fun asType(d: Double) = Picomole(d)
    override fun asBaseUnit() = Mole(this.value * 1E-12)

    override operator fun plus(other: UnitAmount<*>) = UnitAmount.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitAmount<*>) = UnitAmount.Companion.minusUnit(this, other)
}

fun UnitAmount<*>.toPicomole() = toUnit(Picomole(1.0))
