package com.measures.conductance.metric

import com.measures.Consts
import com.measures.conductance.Siemens
import com.measures.conductance.UnitConductance
import kotlin.jvm.JvmInline

@JvmInline
value class Microsiemens(override val value: Double) : UnitConductance<Microsiemens> {
    override fun asType(d: Double) = Microsiemens(d)
    override fun asBaseUnit() = Siemens(value * Consts.MICRO)

    override operator fun plus(other: UnitConductance<*>) = UnitConductance.plusUnit(this, other)
    override operator fun minus(other: UnitConductance<*>) = UnitConductance.minusUnit(this, other)
}

fun UnitConductance<*>.toMicrosiemens() = toUnit(Microsiemens(1.0))
