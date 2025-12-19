package com.measures.conductance.metric

import com.measures.Consts
import com.measures.conductance.Siemens
import com.measures.conductance.UnitConductance
import kotlin.jvm.JvmInline

@JvmInline
value class Decisiemens(override val value: Double) : UnitConductance<Decisiemens> {
    override fun asType(d: Double) = Decisiemens(d)
    override fun asBaseUnit() = Siemens(value * Consts.DECI)

    override operator fun plus(other: UnitConductance<*>) = UnitConductance.plusUnit(this, other)
    override operator fun minus(other: UnitConductance<*>) = UnitConductance.minusUnit(this, other)
}

fun UnitConductance<*>.toDecisiemens() = toUnit(Decisiemens(1.0))
