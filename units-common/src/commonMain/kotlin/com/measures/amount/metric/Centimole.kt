package com.measures.amount.metric

import com.measures.Consts
import com.measures.amount.UnitAmount
import com.measures.amount.Mole
import kotlin.jvm.JvmInline

@JvmInline
value class Centimole(override val value: Double) : UnitAmount<Centimole> {
    override fun asType(d: Double) = Centimole(d)
    override fun asBaseUnit() = Mole(value * Consts.CENTI)

    override operator fun plus(other: UnitAmount<*>) = UnitAmount.plusUnit(this, other)
    override operator fun minus(other: UnitAmount<*>) = UnitAmount.minusUnit(this, other)

}

fun UnitAmount<*>.toCentimole() = toUnit(Centimole(1.0))
