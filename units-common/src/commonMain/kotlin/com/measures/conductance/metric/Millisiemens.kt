package com.measures.conductance.metric

import com.measures.Consts
import com.measures.conductance.Siemens
import com.measures.conductance.UnitConductance
import kotlin.jvm.JvmInline

@JvmInline
value class Millisiemens(override val value: Double) : UnitConductance<Millisiemens> {
    override fun asType(d: Double) = Millisiemens(d)
    override fun asBaseUnit() = Siemens(value * Consts.MILLI)

    override operator fun plus(other: UnitConductance<*>) = UnitConductance.plusUnit(this, other)
    override operator fun minus(other: UnitConductance<*>) = UnitConductance.minusUnit(this, other)
}

fun UnitConductance<*>.toMillisiemens() = toUnit(Millisiemens(1.0))
