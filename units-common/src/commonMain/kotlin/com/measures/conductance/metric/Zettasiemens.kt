package com.measures.conductance.metric

import com.measures.Consts
import com.measures.conductance.Siemens
import com.measures.conductance.UnitConductance
import kotlin.jvm.JvmInline

@JvmInline
value class Zettasiemens(override val value: Double) : UnitConductance<Zettasiemens> {
    override fun asType(d: Double) = Zettasiemens(d)
    override fun asBaseUnit() = Siemens(value * Consts.ZETTA)

    override operator fun plus(other: UnitConductance<*>) = UnitConductance.plusUnit(this, other)
    override operator fun minus(other: UnitConductance<*>) = UnitConductance.minusUnit(this, other)
}

fun UnitConductance<*>.toZettasiemens() = toUnit(Zettasiemens(1.0))
