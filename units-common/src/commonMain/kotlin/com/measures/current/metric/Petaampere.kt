package com.measures.current.metric

import com.measures.Consts
import com.measures.current.UnitCurrent
import com.measures.current.Ampere
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

@JvmInline
value class Petaampere(override val value: Double) : UnitCurrent<Petaampere> {
    override fun asType(d: Double) = Petaampere(d)
    override fun asBaseUnit() = Ampere(value * Consts.PETA)

    override operator fun plus(other: UnitCurrent<*>) = UnitCurrent.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitCurrent<*>) = UnitCurrent.Companion.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitCurrent.Companion.timesUnit(this, other)
}

fun UnitCurrent<*>.toPetaampere() = toUnit(Petaampere(1.0))
