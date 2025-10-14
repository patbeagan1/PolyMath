package com.measures.conductance.metric

import com.measures.Consts
import com.measures.conductance.Siemens
import com.measures.conductance.UnitConductance
import kotlin.jvm.JvmInline

@JvmInline
value class Petasiemens(override val value: Double) : UnitConductance<Petasiemens> {
    override fun asType(d: Double) = Petasiemens(d)
    override fun asBaseUnit() = Siemens(value * Consts.PETA)

    override operator fun plus(other: UnitConductance<*>) = UnitConductance.plusUnit(this, other)
    override operator fun minus(other: UnitConductance<*>) = UnitConductance.minusUnit(this, other)
}

fun UnitConductance<*>.toPetasiemens() = toUnit(Petasiemens(1.0))
