package com.measures.amount.metric

import com.measures.Consts
import com.measures.amount.UnitAmount
import com.measures.amount.Mole
import kotlin.jvm.JvmInline

@JvmInline
value class Attomole(override val value: Double) : UnitAmount<Attomole> {
    override fun asType(d: Double) = Attomole(d)
    override fun asBaseUnit() = Mole(value * Consts.ATTO)

    override operator fun plus(other: UnitAmount<*>) = UnitAmount.plusUnit(this, other)
    override operator fun minus(other: UnitAmount<*>) = UnitAmount.minusUnit(this, other)

}

fun UnitAmount<*>.toAttomole() = toUnit(Attomole(1.0))
