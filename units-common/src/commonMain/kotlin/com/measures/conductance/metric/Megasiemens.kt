package com.measures.conductance.metric

import com.measures.Consts
import com.measures.conductance.Siemens
import com.measures.conductance.UnitConductance
import kotlin.jvm.JvmInline

@JvmInline
value class Megasiemens(override val value: Double) : UnitConductance<Megasiemens> {
    override fun asType(d: Double) = Megasiemens(d)
    override fun asBaseUnit() = Siemens(value * Consts.MEGA)

    override operator fun plus(other: UnitConductance<*>) = UnitConductance.plusUnit(this, other)
    override operator fun minus(other: UnitConductance<*>) = UnitConductance.minusUnit(this, other)
}

fun UnitConductance<*>.toMegasiemens() = toUnit(Megasiemens(1.0))
