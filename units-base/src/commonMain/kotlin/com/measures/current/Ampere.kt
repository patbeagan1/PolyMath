package com.measures.current

import com.measures.BaseUnit
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

@JvmInline
value class Ampere(override val value: Double) : UnitCurrent<Ampere>, BaseUnit {
    override fun asType(d: Double) = Ampere(d)
    override fun asBaseUnit() = this

    operator fun plus(other: UnitCurrent<*>) = (this as UnitCurrent<*>).plusUnit(other)
    operator fun minus(other: UnitCurrent<*>) = (this as UnitCurrent<*>).minusUnit(other)
    operator fun times(other: UnitTime<*>) = (this as UnitCurrent<*>).timesUnit(other)
}