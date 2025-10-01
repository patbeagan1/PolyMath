package com.measures.amount

import com.measures.BaseUnit
import kotlin.jvm.JvmInline

@JvmInline
value class Mole(override val value: Double) : UnitAmount<Mole>, BaseUnit {
    override fun asType(d: Double) = Mole(d)
    override fun asBaseUnit() = this

    override operator fun plus(other: UnitAmount<*>) = (this as UnitAmount<*>).plusUnit(other)
    override operator fun minus(other: UnitAmount<*>) = (this as UnitAmount<*>).minusUnit(other)
}

fun UnitAmount<*>.toMole() = this.asBaseUnit()
