package com.measures.amount.metric

import com.measures.Consts
import com.measures.amount.UnitAmount
import com.measures.amount.Mole
import kotlin.jvm.JvmInline

@JvmInline
value class Decimole(override val value: Double) : UnitAmount<Decimole> {
    override fun asType(d: Double) = Decimole(d)
    override fun asBaseUnit() = Mole(value * Consts.DECI)

    override operator fun plus(other: UnitAmount<*>) = UnitAmount.plusUnit(this, other)
    override operator fun minus(other: UnitAmount<*>) = UnitAmount.minusUnit(this, other)

}

fun UnitAmount<*>.toDecimole() = toUnit(Decimole(1.0))
