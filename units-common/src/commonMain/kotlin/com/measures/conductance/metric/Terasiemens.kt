package com.measures.conductance.metric

import com.measures.Consts
import com.measures.conductance.Siemens
import com.measures.conductance.UnitConductance
import kotlin.jvm.JvmInline

@JvmInline
value class Terasiemens(override val value: Double) : UnitConductance<Terasiemens> {
    override fun asType(d: Double) = Terasiemens(d)
    override fun asBaseUnit() = Siemens(value * Consts.TERA)

    override operator fun plus(other: UnitConductance<*>) = UnitConductance.plusUnit(this, other)
    override operator fun minus(other: UnitConductance<*>) = UnitConductance.minusUnit(this, other)
}

fun UnitConductance<*>.toTerasiemens() = toUnit(Terasiemens(1.0))
