package com.measures.conductance.metric

import com.measures.Consts
import com.measures.conductance.Siemens
import com.measures.conductance.UnitConductance
import kotlin.jvm.JvmInline

@JvmInline
value class Kilosiemens(override val value: Double) : UnitConductance<Kilosiemens> {
    override fun asType(d: Double) = Kilosiemens(d)
    override fun asBaseUnit() = Siemens(value * Consts.KILO)

    override operator fun plus(other: UnitConductance<*>) = UnitConductance.plusUnit(this, other)
    override operator fun minus(other: UnitConductance<*>) = UnitConductance.minusUnit(this, other)
}

fun UnitConductance<*>.toKilosiemens() = toUnit(Kilosiemens(1.0))
