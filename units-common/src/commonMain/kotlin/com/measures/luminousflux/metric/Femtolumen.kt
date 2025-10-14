package com.measures.luminousflux.metric

import com.measures.Consts
import com.measures.luminousflux.Lumen
import com.measures.luminousflux.UnitLuminousFlux
import kotlin.jvm.JvmInline

@JvmInline
value class Femtolumen(override val value: Double) : UnitLuminousFlux<Femtolumen> {
    override fun asType(d: Double) = Femtolumen(d)
    override fun asBaseUnit() = Lumen(value * Consts.FEMTO)

    override operator fun plus(other: UnitLuminousFlux<*>) = UnitLuminousFlux.plusUnit(this, other)
    override operator fun minus(other: UnitLuminousFlux<*>) = UnitLuminousFlux.minusUnit(this, other)
}

fun UnitLuminousFlux<*>.toFemtolumen() = toUnit(Femtolumen(1.0))
