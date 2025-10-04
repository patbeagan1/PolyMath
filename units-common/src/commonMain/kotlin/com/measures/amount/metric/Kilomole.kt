package com.measures.amount.metric

import com.measures.Consts
import com.measures.amount.UnitAmount
import com.measures.amount.Mole
import kotlin.jvm.JvmInline

@JvmInline
value class Kilomole(override val value: Double) : UnitAmount<Kilomole> {
    override fun asType(d: Double) = Kilomole(d)
    override fun asBaseUnit() = Mole(value * Consts.KILO)

    override operator fun plus(other: UnitAmount<*>) = UnitAmount.plusUnit(this, other)
    override operator fun minus(other: UnitAmount<*>) = UnitAmount.minusUnit(this, other)

}

fun UnitAmount<*>.toKilomole() = toUnit(Kilomole(1.0))
