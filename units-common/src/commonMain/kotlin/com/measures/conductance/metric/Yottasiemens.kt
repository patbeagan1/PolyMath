package com.measures.conductance.metric

import com.measures.Consts
import com.measures.conductance.Siemens
import com.measures.conductance.UnitConductance
import kotlin.jvm.JvmInline

@JvmInline
value class Yottasiemens(override val value: Double) : UnitConductance<Yottasiemens> {
    override fun asType(d: Double) = Yottasiemens(d)
    override fun asBaseUnit() = Siemens(value * Consts.YOTTA)

    override operator fun plus(other: UnitConductance<*>) = UnitConductance.plusUnit(this, other)
    override operator fun minus(other: UnitConductance<*>) = UnitConductance.minusUnit(this, other)
}

fun UnitConductance<*>.toYottasiemens() = toUnit(Yottasiemens(1.0))
