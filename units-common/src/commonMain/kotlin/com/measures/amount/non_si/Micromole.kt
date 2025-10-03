package com.measures.amount.non_si

import com.measures.amount.Mole
import com.measures.amount.UnitAmount
import kotlin.jvm.JvmInline

@JvmInline
value class Micromole(override val value: Double) : UnitAmount<Micromole> {
override fun asType(d: Double) = Micromole(d)
    override fun asBaseUnit() = Mole(this.value * 1E-6)

    override operator fun plus(other: UnitAmount<*>) = UnitAmount.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitAmount<*>) = UnitAmount.Companion.minusUnit(this, other)
}

fun UnitAmount<*>.toMicromole() = toUnit(Micromole(1.0))
