package com.measures.amount.metric

import com.measures.Consts
import com.measures.amount.UnitAmount
import com.measures.amount.Mole
import kotlin.jvm.JvmInline

@JvmInline
value class Megamole(override val value: Double) : UnitAmount<Megamole> {
    override fun asType(d: Double) = Megamole(d)
    override fun asBaseUnit() = Mole(value * Consts.MEGA)

    override operator fun plus(other: UnitAmount<*>) = UnitAmount.plusUnit(this, other)
    override operator fun minus(other: UnitAmount<*>) = UnitAmount.minusUnit(this, other)

}

fun UnitAmount<*>.toMegamole() = toUnit(Megamole(1.0))
