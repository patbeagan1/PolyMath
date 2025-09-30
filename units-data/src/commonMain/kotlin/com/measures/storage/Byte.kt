package com.measures.storage

import com.measures.BaseUnit
import kotlin.jvm.JvmInline

@JvmInline
value class Byte(override val value: Double) : UnitStorage<Byte>, BaseUnit {
    override fun asType(d: Double) = Byte(d)
    override fun asBaseUnit() = this

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

fun UnitStorage<*>.toByte() = this.asBaseUnit()
