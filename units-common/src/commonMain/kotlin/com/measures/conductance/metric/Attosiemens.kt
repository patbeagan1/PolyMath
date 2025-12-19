package com.measures.conductance.metric

import com.measures.Consts
import com.measures.conductance.Siemens
import com.measures.conductance.UnitConductance
import kotlin.jvm.JvmInline

@JvmInline
value class Attosiemens(override val value: Double) : UnitConductance<Attosiemens> {
    override fun asType(d: Double) = Attosiemens(d)
    override fun asBaseUnit() = Siemens(value * Consts.ATTO)

    override operator fun plus(other: UnitConductance<*>) = UnitConductance.plusUnit(this, other)
    override operator fun minus(other: UnitConductance<*>) = UnitConductance.minusUnit(this, other)
}

fun UnitConductance<*>.toAttosiemens() = toUnit(Attosiemens(1.0))
