package com.measures.amount.metric

import com.measures.Consts
import com.measures.amount.UnitAmount
import com.measures.amount.Mole
import kotlin.jvm.JvmInline

@JvmInline
value class Micromole(override val value: Double) : UnitAmount<Micromole> {
    override fun asType(d: Double) = Micromole(d)
    override fun asBaseUnit() = Mole(value * Consts.MICRO)

    override operator fun plus(other: UnitAmount<*>) = UnitAmount.plusUnit(this, other)
    override operator fun minus(other: UnitAmount<*>) = UnitAmount.minusUnit(this, other)

}

fun UnitAmount<*>.toMicromole() = toUnit(Micromole(1.0))
