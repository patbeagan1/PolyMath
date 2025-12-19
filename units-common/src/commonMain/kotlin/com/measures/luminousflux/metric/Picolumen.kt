package com.measures.luminousflux.metric

import com.measures.Consts
import com.measures.luminousflux.Lumen
import com.measures.luminousflux.UnitLuminousFlux
import kotlin.jvm.JvmInline

@JvmInline
value class Picolumen(override val value: Double) : UnitLuminousFlux<Picolumen> {
    override fun asType(d: Double) = Picolumen(d)
    override fun asBaseUnit() = Lumen(value * Consts.PICO)

    override operator fun plus(other: UnitLuminousFlux<*>) = UnitLuminousFlux.plusUnit(this, other)
    override operator fun minus(other: UnitLuminousFlux<*>) = UnitLuminousFlux.minusUnit(this, other)
}

fun UnitLuminousFlux<*>.toPicolumen() = toUnit(Picolumen(1.0))
