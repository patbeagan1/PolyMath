package com.measures.amount.metric

import com.measures.Consts
import com.measures.amount.UnitAmount
import com.measures.amount.Mole
import kotlin.jvm.JvmInline

@JvmInline
value class Zettamole(override val value: Double) : UnitAmount<Zettamole> {
    override fun asType(d: Double) = Zettamole(d)
    override fun asBaseUnit() = Mole(value * Consts.ZETTA)

    override operator fun plus(other: UnitAmount<*>) = UnitAmount.plusUnit(this, other)
    override operator fun minus(other: UnitAmount<*>) = UnitAmount.minusUnit(this, other)

}

fun UnitAmount<*>.toZettamole() = toUnit(Zettamole(1.0))
