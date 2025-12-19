package com.measures.conductance.metric

import com.measures.Consts
import com.measures.conductance.Siemens
import com.measures.conductance.UnitConductance
import kotlin.jvm.JvmInline

@JvmInline
value class Dekasiemens(override val value: Double) : UnitConductance<Dekasiemens> {
    override fun asType(d: Double) = Dekasiemens(d)
    override fun asBaseUnit() = Siemens(value * Consts.DEKA)

    override operator fun plus(other: UnitConductance<*>) = UnitConductance.plusUnit(this, other)
    override operator fun minus(other: UnitConductance<*>) = UnitConductance.minusUnit(this, other)
}

fun UnitConductance<*>.toDekasiemens() = toUnit(Dekasiemens(1.0))
