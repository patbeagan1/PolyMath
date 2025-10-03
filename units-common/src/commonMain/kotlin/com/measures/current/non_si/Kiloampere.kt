package com.measures.current.non_si

import com.measures.Consts
import com.measures.current.Ampere
import com.measures.current.UnitCurrent
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

@JvmInline
value class Kiloampere(override val value: Double) : UnitCurrent<Kiloampere> {
override fun asType(d: Double) = Kiloampere(d)
    override fun asBaseUnit() = Ampere(this.value * Consts.KILO)

    override operator fun plus(other: UnitCurrent<*>) = UnitCurrent.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitCurrent<*>) = UnitCurrent.Companion.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitCurrent.Companion.timesUnit(this, other)
}

fun UnitCurrent<*>.toKiloampere() = toUnit(Kiloampere(1.0))
