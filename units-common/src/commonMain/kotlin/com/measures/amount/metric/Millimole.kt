package com.measures.amount.metric

import com.measures.Consts
import com.measures.amount.UnitAmount
import com.measures.amount.Mole
import kotlin.jvm.JvmInline

@JvmInline
value class Millimole(override val value: Double) : UnitAmount<Millimole> {
    override fun asType(d: Double) = Millimole(d)
    override fun asBaseUnit() = Mole(value * Consts.MILLI)

    override operator fun plus(other: UnitAmount<*>) = UnitAmount.plusUnit(this, other)
    override operator fun minus(other: UnitAmount<*>) = UnitAmount.minusUnit(this, other)

}

fun UnitAmount<*>.toMillimole() = toUnit(Millimole(1.0))
