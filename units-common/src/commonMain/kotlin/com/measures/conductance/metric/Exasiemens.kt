package com.measures.conductance.metric

import com.measures.Consts
import com.measures.conductance.Siemens
import com.measures.conductance.UnitConductance
import kotlin.jvm.JvmInline

@JvmInline
value class Exasiemens(override val value: Double) : UnitConductance<Exasiemens> {
    override fun asType(d: Double) = Exasiemens(d)
    override fun asBaseUnit() = Siemens(value * Consts.EXA)

    override operator fun plus(other: UnitConductance<*>) = UnitConductance.plusUnit(this, other)
    override operator fun minus(other: UnitConductance<*>) = UnitConductance.minusUnit(this, other)
}

fun UnitConductance<*>.toExasiemens() = toUnit(Exasiemens(1.0))
