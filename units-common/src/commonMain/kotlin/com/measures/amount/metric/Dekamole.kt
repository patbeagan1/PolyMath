package com.measures.amount.metric

import com.measures.Consts
import com.measures.amount.UnitAmount
import com.measures.amount.Mole
import kotlin.jvm.JvmInline

@JvmInline
value class Dekamole(override val value: Double) : UnitAmount<Dekamole> {
    override fun asType(d: Double) = Dekamole(d)
    override fun asBaseUnit() = Mole(value * Consts.DEKA)

    override operator fun plus(other: UnitAmount<*>) = UnitAmount.plusUnit(this, other)
    override operator fun minus(other: UnitAmount<*>) = UnitAmount.minusUnit(this, other)

}

fun UnitAmount<*>.toDekamole() = toUnit(Dekamole(1.0))
