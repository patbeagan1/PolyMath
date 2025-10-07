package com.measures.amount.metric

import com.measures.Consts
import com.measures.amount.UnitAmount
import com.measures.amount.Mole
import kotlin.jvm.JvmInline

@JvmInline
value class Petamole(override val value: Double) : UnitAmount<Petamole> {
    override fun asType(d: Double) = Petamole(d)
    override fun asBaseUnit() = Mole(value * Consts.PETA)

    override operator fun plus(other: UnitAmount<*>) = UnitAmount.plusUnit(this, other)
    override operator fun minus(other: UnitAmount<*>) = UnitAmount.minusUnit(this, other)

}

fun UnitAmount<*>.toPetamole() = toUnit(Petamole(1.0))
