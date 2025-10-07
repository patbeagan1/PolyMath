package com.measures.current

import com.measures.BaseUnit
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

@JvmInline
value class Ampere(override val value: Double) : UnitCurrent<Ampere>, BaseUnit {
    override fun asType(d: Double) = Ampere(d)
    override fun asBaseUnit() = this

    override operator fun plus(other: UnitCurrent<*>) = UnitCurrent.plusUnit(this, other)
    override operator fun minus(other: UnitCurrent<*>) = UnitCurrent.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitCurrent.timesUnit(this, other)
}

fun UnitCurrent<*>.toAmpere() = this.asBaseUnit()
