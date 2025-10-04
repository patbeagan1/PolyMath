package com.measures.amount.metric

import com.measures.Consts
import com.measures.amount.UnitAmount
import com.measures.amount.Mole
import kotlin.jvm.JvmInline

@JvmInline
value class Teramole(override val value: Double) : UnitAmount<Teramole> {
    override fun asType(d: Double) = Teramole(d)
    override fun asBaseUnit() = Mole(value * Consts.TERA)

    override operator fun plus(other: UnitAmount<*>) = UnitAmount.plusUnit(this, other)
    override operator fun minus(other: UnitAmount<*>) = UnitAmount.minusUnit(this, other)

}

fun UnitAmount<*>.toTeramole() = toUnit(Teramole(1.0))
