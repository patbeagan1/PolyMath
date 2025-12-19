package com.measures.luminousflux.metric

import com.measures.Consts
import com.measures.luminousflux.Lumen
import com.measures.luminousflux.UnitLuminousFlux
import kotlin.jvm.JvmInline

@JvmInline
value class Kilolumen(override val value: Double) : UnitLuminousFlux<Kilolumen> {
    override fun asType(d: Double) = Kilolumen(d)
    override fun asBaseUnit() = Lumen(value * Consts.KILO)

    override operator fun plus(other: UnitLuminousFlux<*>) = UnitLuminousFlux.plusUnit(this, other)
    override operator fun minus(other: UnitLuminousFlux<*>) = UnitLuminousFlux.minusUnit(this, other)
}

fun UnitLuminousFlux<*>.toKilolumen() = toUnit(Kilolumen(1.0))
