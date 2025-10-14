package com.measures.luminousflux.metric

import com.measures.Consts
import com.measures.luminousflux.Lumen
import com.measures.luminousflux.UnitLuminousFlux
import kotlin.jvm.JvmInline

@JvmInline
value class Dekalumen(override val value: Double) : UnitLuminousFlux<Dekalumen> {
    override fun asType(d: Double) = Dekalumen(d)
    override fun asBaseUnit() = Lumen(value * Consts.DEKA)

    override operator fun plus(other: UnitLuminousFlux<*>) = UnitLuminousFlux.plusUnit(this, other)
    override operator fun minus(other: UnitLuminousFlux<*>) = UnitLuminousFlux.minusUnit(this, other)
}

fun UnitLuminousFlux<*>.toDekalumen() = toUnit(Dekalumen(1.0))
