package com.measures.amount.metric

import com.measures.Consts
import com.measures.amount.UnitAmount
import com.measures.amount.Mole
import kotlin.jvm.JvmInline

@JvmInline
value class Yottamole(override val value: Double) : UnitAmount<Yottamole> {
    override fun asType(d: Double) = Yottamole(d)
    override fun asBaseUnit() = Mole(value * Consts.YOTTA)

    override operator fun plus(other: UnitAmount<*>) = UnitAmount.plusUnit(this, other)
    override operator fun minus(other: UnitAmount<*>) = UnitAmount.minusUnit(this, other)

}

fun UnitAmount<*>.toYottamole() = toUnit(Yottamole(1.0))
