package com.measures.luminousflux.metric

import com.measures.Consts
import com.measures.luminousflux.Lumen
import com.measures.luminousflux.UnitLuminousFlux
import kotlin.jvm.JvmInline

@JvmInline
value class Megalumen(override val value: Double) : UnitLuminousFlux<Megalumen> {
    override fun asType(d: Double) = Megalumen(d)
    override fun asBaseUnit() = Lumen(value * Consts.MEGA)

    override operator fun plus(other: UnitLuminousFlux<*>) = UnitLuminousFlux.plusUnit(this, other)
    override operator fun minus(other: UnitLuminousFlux<*>) = UnitLuminousFlux.minusUnit(this, other)
}

fun UnitLuminousFlux<*>.toMegalumen() = toUnit(Megalumen(1.0))
