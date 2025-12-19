package com.measures.conductance.metric

import com.measures.Consts
import com.measures.conductance.Siemens
import com.measures.conductance.UnitConductance
import kotlin.jvm.JvmInline

@JvmInline
value class Hectosiemens(override val value: Double) : UnitConductance<Hectosiemens> {
    override fun asType(d: Double) = Hectosiemens(d)
    override fun asBaseUnit() = Siemens(value * Consts.HECTO)

    override operator fun plus(other: UnitConductance<*>) = UnitConductance.plusUnit(this, other)
    override operator fun minus(other: UnitConductance<*>) = UnitConductance.minusUnit(this, other)
}

fun UnitConductance<*>.toHectosiemens() = toUnit(Hectosiemens(1.0))
