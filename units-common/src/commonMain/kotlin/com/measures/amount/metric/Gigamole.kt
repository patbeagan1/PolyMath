package com.measures.amount.metric

import com.measures.Consts
import com.measures.amount.UnitAmount
import com.measures.amount.Mole
import kotlin.jvm.JvmInline

@JvmInline
value class Gigamole(override val value: Double) : UnitAmount<Gigamole> {
    override fun asType(d: Double) = Gigamole(d)
    override fun asBaseUnit() = Mole(value * Consts.GIGA)

    override operator fun plus(other: UnitAmount<*>) = UnitAmount.plusUnit(this, other)
    override operator fun minus(other: UnitAmount<*>) = UnitAmount.minusUnit(this, other)

}

fun UnitAmount<*>.toGigamole() = toUnit(Gigamole(1.0))
