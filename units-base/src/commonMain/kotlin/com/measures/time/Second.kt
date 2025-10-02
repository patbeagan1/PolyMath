package com.measures.time

import com.measures.BaseUnit
import kotlin.jvm.JvmInline

@JvmInline
value class Second(override val value: Double) : UnitTime<Second>, BaseUnit {
    override fun asType(d: Double) = Second(d)
    override fun asBaseUnit() = this

    override operator fun plus(other: UnitTime<*>) = UnitTime.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitTime<*>) = UnitTime.Companion.minusUnit(this, other)
    override operator fun inv() = UnitTime.Companion.invUnit(this)
}

fun UnitTime<*>.toSecond() = this.asBaseUnit()
