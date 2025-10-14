package com.measures.luminousflux.metric

import com.measures.Consts
import com.measures.luminousflux.Lumen
import com.measures.luminousflux.UnitLuminousFlux
import kotlin.jvm.JvmInline

@JvmInline
value class Hectolumen(override val value: Double) : UnitLuminousFlux<Hectolumen> {
    override fun asType(d: Double) = Hectolumen(d)
    override fun asBaseUnit() = Lumen(value * Consts.HECTO)

    override operator fun plus(other: UnitLuminousFlux<*>) = UnitLuminousFlux.plusUnit(this, other)
    override operator fun minus(other: UnitLuminousFlux<*>) = UnitLuminousFlux.minusUnit(this, other)
}

fun UnitLuminousFlux<*>.toHectolumen() = toUnit(Hectolumen(1.0))
