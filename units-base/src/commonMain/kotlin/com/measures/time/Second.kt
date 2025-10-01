package com.measures.time

import com.measures.BaseUnit
import kotlin.jvm.JvmInline

@JvmInline
value class Second(override val value: Double) : UnitTime<Second>, BaseUnit {
    override fun asType(d: Double) = Second(d)
    override fun asBaseUnit() = this

    override operator fun plus(other: UnitTime<*>) = (this as UnitTime<*>).plusUnit(other)
    override operator fun minus(other: UnitTime<*>) = (this as UnitTime<*>).minusUnit(other)
    override operator fun inv() = (this as UnitTime<*>).invUnit()
}

fun UnitTime<*>.toSecond() = this.asBaseUnit()
