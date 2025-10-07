package com.measures.amount.metric

import com.measures.Consts
import com.measures.amount.UnitAmount
import com.measures.amount.Mole
import kotlin.jvm.JvmInline

@JvmInline
value class Hectomole(override val value: Double) : UnitAmount<Hectomole> {
    override fun asType(d: Double) = Hectomole(d)
    override fun asBaseUnit() = Mole(value * Consts.HECTO)

    override operator fun plus(other: UnitAmount<*>) = UnitAmount.plusUnit(this, other)
    override operator fun minus(other: UnitAmount<*>) = UnitAmount.minusUnit(this, other)

}

fun UnitAmount<*>.toHectomole() = toUnit(Hectomole(1.0))
