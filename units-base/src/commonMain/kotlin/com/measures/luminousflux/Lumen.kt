package com.measures.luminousflux

import com.measures.BaseUnit
import kotlin.jvm.JvmInline

@JvmInline
value class Lumen(override val value: Double) : UnitLuminousFlux<Lumen>, BaseUnit {
    override fun asType(d: Double) = Lumen(d)
    override fun asBaseUnit() = this

    override operator fun plus(other: UnitLuminousFlux<*>) = UnitLuminousFlux.plusUnit(this, other)
    override operator fun minus(other: UnitLuminousFlux<*>) = UnitLuminousFlux.minusUnit(this, other)
}

fun UnitLuminousFlux<*>.toLumen() = this.asBaseUnit()

