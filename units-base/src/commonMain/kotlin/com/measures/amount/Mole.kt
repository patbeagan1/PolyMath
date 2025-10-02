package com.measures.amount

import com.measures.BaseUnit
import kotlin.jvm.JvmInline

@JvmInline
value class Mole(override val value: Double) : UnitAmount<Mole>, BaseUnit {
    override fun asType(d: Double) = Mole(d)
    override fun asBaseUnit() = this

    override operator fun plus(other: UnitAmount<*>) = UnitAmount.plusUnit(this, other)
    override operator fun minus(other: UnitAmount<*>) = UnitAmount.minusUnit(this, other)
}

fun UnitAmount<*>.toMole() = this.asBaseUnit()
