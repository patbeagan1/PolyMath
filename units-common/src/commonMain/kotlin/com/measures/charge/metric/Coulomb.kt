package com.measures.charge.metric

import com.measures.charge.UnitCharge
import com.measures.charge.Coulomb
import kotlin.jvm.JvmInline

@JvmInline
value class Coulomb(override val value: Double) : UnitCharge<Coulomb> {
    override fun asType(d: Double) = Coulomb(d)
    override fun asBaseUnit() = Coulomb(value * Const.ONE)
}

fun UnitCharge<*>.toCoulomb() = toUnit(Coulomb(1.0))
