package com.measures.amount.metric

import com.measures.Consts
import com.measures.amount.UnitAmount
import com.measures.amount.Mole
import kotlin.jvm.JvmInline

@JvmInline
value class Yoctomole(override val value: Double) : UnitAmount<Yoctomole> {
    override fun asType(d: Double) = Yoctomole(d)
    override fun asBaseUnit() = Mole(value * Consts.YOCTO)

    override operator fun plus(other: UnitAmount<*>) = UnitAmount.plusUnit(this, other)
    override operator fun minus(other: UnitAmount<*>) = UnitAmount.minusUnit(this, other)

}

fun UnitAmount<*>.toYoctomole() = toUnit(Yoctomole(1.0))
