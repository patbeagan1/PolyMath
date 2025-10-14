package com.measures.conductance.metric

import com.measures.Consts
import com.measures.conductance.Siemens
import com.measures.conductance.UnitConductance
import kotlin.jvm.JvmInline

@JvmInline
value class Centisiemens(override val value: Double) : UnitConductance<Centisiemens> {
    override fun asType(d: Double) = Centisiemens(d)
    override fun asBaseUnit() = Siemens(value * Consts.CENTI)

    override operator fun plus(other: UnitConductance<*>) = UnitConductance.plusUnit(this, other)
    override operator fun minus(other: UnitConductance<*>) = UnitConductance.minusUnit(this, other)
}

fun UnitConductance<*>.toCentisiemens() = toUnit(Centisiemens(1.0))
