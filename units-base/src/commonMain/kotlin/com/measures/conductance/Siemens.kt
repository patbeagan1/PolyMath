package com.measures.conductance

import com.measures.BaseUnit
import kotlin.jvm.JvmInline

@JvmInline
value class Siemens(override val value: Double) : UnitConductance<Siemens>, BaseUnit {
    override fun asType(d: Double) = Siemens(d)
    override fun asBaseUnit() = this

    override operator fun plus(other: UnitConductance<*>) = UnitConductance.plusUnit(this, other)
    override operator fun minus(other: UnitConductance<*>) = UnitConductance.minusUnit(this, other)
}

fun UnitConductance<*>.toSiemens() = this.asBaseUnit()

