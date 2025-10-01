package com.measures.amount

import com.measures.BaseUnit
import kotlin.jvm.JvmInline

@JvmInline
value class Mole(override val value: Double) : UnitAmount<Mole>, BaseUnit {
    override fun asType(d: Double) = Mole(d)
    override fun asBaseUnit() = this

    operator fun plus(other: UnitAmount<*>) = (this as UnitAmount<*>).plusUnit(other)
    operator fun minus(other: UnitAmount<*>) = (this as UnitAmount<*>).minusUnit(other)
}