package com.measures.current.metric

import com.measures.Consts
import com.measures.current.UnitCurrent
import com.measures.current.Ampere
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

@JvmInline
value class Hectoampere(override val value: Double) : UnitCurrent<Hectoampere> {
    override fun asType(d: Double) = Hectoampere(d)
    override fun asBaseUnit() = Ampere(value * Consts.HECTO)

    override operator fun plus(other: UnitCurrent<*>) = UnitCurrent.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitCurrent<*>) = UnitCurrent.Companion.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitCurrent.Companion.timesUnit(this, other)
}

fun UnitCurrent<*>.toHectoampere() = toUnit(Hectoampere(1.0))
