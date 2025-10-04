package com.measures.amount.metric

import com.measures.Consts
import com.measures.amount.UnitAmount
import com.measures.amount.Mole
import kotlin.jvm.JvmInline

@JvmInline
value class Examole(override val value: Double) : UnitAmount<Examole> {
    override fun asType(d: Double) = Examole(d)
    override fun asBaseUnit() = Mole(value * Consts.EXA)

    override operator fun plus(other: UnitAmount<*>) = UnitAmount.plusUnit(this, other)
    override operator fun minus(other: UnitAmount<*>) = UnitAmount.minusUnit(this, other)

}

fun UnitAmount<*>.toExamole() = toUnit(Examole(1.0))
