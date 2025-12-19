package com.measures.luminousflux.metric

import com.measures.Consts
import com.measures.luminousflux.Lumen
import com.measures.luminousflux.UnitLuminousFlux
import kotlin.jvm.JvmInline

@JvmInline
value class Microlumen(override val value: Double) : UnitLuminousFlux<Microlumen> {
    override fun asType(d: Double) = Microlumen(d)
    override fun asBaseUnit() = Lumen(value * Consts.MICRO)

    override operator fun plus(other: UnitLuminousFlux<*>) = UnitLuminousFlux.plusUnit(this, other)
    override operator fun minus(other: UnitLuminousFlux<*>) = UnitLuminousFlux.minusUnit(this, other)
}

fun UnitLuminousFlux<*>.toMicrolumen() = toUnit(Microlumen(1.0))
