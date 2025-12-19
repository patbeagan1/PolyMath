package com.measures.conductance.metric

import com.measures.Consts
import com.measures.conductance.Siemens
import com.measures.conductance.UnitConductance
import kotlin.jvm.JvmInline

@JvmInline
value class Gigasiemens(override val value: Double) : UnitConductance<Gigasiemens> {
    override fun asType(d: Double) = Gigasiemens(d)
    override fun asBaseUnit() = Siemens(value * Consts.GIGA)

    override operator fun plus(other: UnitConductance<*>) = UnitConductance.plusUnit(this, other)
    override operator fun minus(other: UnitConductance<*>) = UnitConductance.minusUnit(this, other)
}

fun UnitConductance<*>.toGigasiemens() = toUnit(Gigasiemens(1.0))
