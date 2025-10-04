package com.measures.amount.metric

import com.measures.Consts
import com.measures.amount.UnitAmount
import com.measures.amount.Mole
import kotlin.jvm.JvmInline

@JvmInline
value class Picomole(override val value: Double) : UnitAmount<Picomole> {
    override fun asType(d: Double) = Picomole(d)
    override fun asBaseUnit() = Mole(value * Consts.PICO)

    override operator fun plus(other: UnitAmount<*>) = UnitAmount.plusUnit(this, other)
    override operator fun minus(other: UnitAmount<*>) = UnitAmount.minusUnit(this, other)

}

fun UnitAmount<*>.toPicomole() = toUnit(Picomole(1.0))
