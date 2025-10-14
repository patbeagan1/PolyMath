package com.measures.luminousflux.metric

import com.measures.Consts
import com.measures.luminousflux.Lumen
import com.measures.luminousflux.UnitLuminousFlux
import kotlin.jvm.JvmInline

@JvmInline
value class Nanolumen(override val value: Double) : UnitLuminousFlux<Nanolumen> {
    override fun asType(d: Double) = Nanolumen(d)
    override fun asBaseUnit() = Lumen(value * Consts.NANO)

    override operator fun plus(other: UnitLuminousFlux<*>) = UnitLuminousFlux.plusUnit(this, other)
    override operator fun minus(other: UnitLuminousFlux<*>) = UnitLuminousFlux.minusUnit(this, other)
}

fun UnitLuminousFlux<*>.toNanolumen() = toUnit(Nanolumen(1.0))
