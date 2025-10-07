package com.measures.amount.metric

import com.measures.Consts
import com.measures.amount.UnitAmount
import com.measures.amount.Mole
import kotlin.jvm.JvmInline

@JvmInline
value class Femtomole(override val value: Double) : UnitAmount<Femtomole> {
    override fun asType(d: Double) = Femtomole(d)
    override fun asBaseUnit() = Mole(value * Consts.FEMTO)

    override operator fun plus(other: UnitAmount<*>) = UnitAmount.plusUnit(this, other)
    override operator fun minus(other: UnitAmount<*>) = UnitAmount.minusUnit(this, other)

}

fun UnitAmount<*>.toFemtomole() = toUnit(Femtomole(1.0))
