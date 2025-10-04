package com.measures.amount.metric

import com.measures.Consts
import com.measures.amount.UnitAmount
import com.measures.amount.Mole
import kotlin.jvm.JvmInline

@JvmInline
value class Zeptomole(override val value: Double) : UnitAmount<Zeptomole> {
    override fun asType(d: Double) = Zeptomole(d)
    override fun asBaseUnit() = Mole(value * Consts.ZEPTO)

    override operator fun plus(other: UnitAmount<*>) = UnitAmount.plusUnit(this, other)
    override operator fun minus(other: UnitAmount<*>) = UnitAmount.minusUnit(this, other)

}

fun UnitAmount<*>.toZeptomole() = toUnit(Zeptomole(1.0))
