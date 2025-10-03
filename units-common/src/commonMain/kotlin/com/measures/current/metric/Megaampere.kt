package com.measures.current.metric

import com.measures.Consts
import com.measures.current.UnitCurrent
import com.measures.current.Ampere
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

@JvmInline
value class Megaampere(override val value: Double) : UnitCurrent<Megaampere> {
    override fun asType(d: Double) = Megaampere(d)
    override fun asBaseUnit() = Ampere(value * Consts.MEGA)

    override operator fun plus(other: UnitCurrent<*>) = UnitCurrent.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitCurrent<*>) = UnitCurrent.Companion.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitCurrent.Companion.timesUnit(this, other)
}

fun UnitCurrent<*>.toMegaampere() = toUnit(Megaampere(1.0))
