package com.measures.luminousflux.metric

import com.measures.Consts
import com.measures.luminousflux.Lumen
import com.measures.luminousflux.UnitLuminousFlux
import kotlin.jvm.JvmInline

@JvmInline
value class Gigalumen(override val value: Double) : UnitLuminousFlux<Gigalumen> {
    override fun asType(d: Double) = Gigalumen(d)
    override fun asBaseUnit() = Lumen(value * Consts.GIGA)

    override operator fun plus(other: UnitLuminousFlux<*>) = UnitLuminousFlux.plusUnit(this, other)
    override operator fun minus(other: UnitLuminousFlux<*>) = UnitLuminousFlux.minusUnit(this, other)
}

fun UnitLuminousFlux<*>.toGigalumen() = toUnit(Gigalumen(1.0))
