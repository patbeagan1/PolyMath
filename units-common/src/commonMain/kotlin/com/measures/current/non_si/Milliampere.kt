package com.measures.current.non_si

import com.measures.current.Ampere
import com.measures.current.UnitCurrent
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

@JvmInline
value class Milliampere(override val value: Double) : UnitCurrent<Milliampere> {
    override fun asType(d: Double) = Milliampere(d)
    override fun asBaseUnit() = Ampere(this.value * 0.001)

    override operator fun plus(other: UnitCurrent<*>) = UnitCurrent.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitCurrent<*>) = UnitCurrent.Companion.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitCurrent.Companion.timesUnit(this, other)
}

fun UnitCurrent<*>.toMilliampere() = toUnit(Milliampere(1.0))
