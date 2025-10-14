package com.measures.conductance.metric

import com.measures.Consts
import com.measures.conductance.Siemens
import com.measures.conductance.UnitConductance
import kotlin.jvm.JvmInline

@JvmInline
value class Nanosiemens(override val value: Double) : UnitConductance<Nanosiemens> {
    override fun asType(d: Double) = Nanosiemens(d)
    override fun asBaseUnit() = Siemens(value * Consts.NANO)

    override operator fun plus(other: UnitConductance<*>) = UnitConductance.plusUnit(this, other)
    override operator fun minus(other: UnitConductance<*>) = UnitConductance.minusUnit(this, other)
}

fun UnitConductance<*>.toNanosiemens() = toUnit(Nanosiemens(1.0))
