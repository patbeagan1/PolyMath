package com.measures.conductance.metric

import com.measures.Consts
import com.measures.conductance.Siemens
import com.measures.conductance.UnitConductance
import kotlin.jvm.JvmInline

@JvmInline
value class Yoctosiemens(override val value: Double) : UnitConductance<Yoctosiemens> {
    override fun asType(d: Double) = Yoctosiemens(d)
    override fun asBaseUnit() = Siemens(value * Consts.YOCTO)

    override operator fun plus(other: UnitConductance<*>) = UnitConductance.plusUnit(this, other)
    override operator fun minus(other: UnitConductance<*>) = UnitConductance.minusUnit(this, other)
}

fun UnitConductance<*>.toYoctosiemens() = toUnit(Yoctosiemens(1.0))
