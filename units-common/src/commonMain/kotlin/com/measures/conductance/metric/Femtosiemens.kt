package com.measures.conductance.metric

import com.measures.Consts
import com.measures.conductance.Siemens
import com.measures.conductance.UnitConductance
import kotlin.jvm.JvmInline

@JvmInline
value class Femtosiemens(override val value: Double) : UnitConductance<Femtosiemens> {
    override fun asType(d: Double) = Femtosiemens(d)
    override fun asBaseUnit() = Siemens(value * Consts.FEMTO)

    override operator fun plus(other: UnitConductance<*>) = UnitConductance.plusUnit(this, other)
    override operator fun minus(other: UnitConductance<*>) = UnitConductance.minusUnit(this, other)
}

fun UnitConductance<*>.toFemtosiemens() = toUnit(Femtosiemens(1.0))
