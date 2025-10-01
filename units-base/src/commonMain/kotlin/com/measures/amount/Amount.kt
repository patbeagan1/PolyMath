package com.measures.amount

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitType
import kotlin.jvm.JvmInline

typealias UnitAmount<T> = UnitAmountType<T>

interface UnitAmountType<T : DoubleBase> : UnitType<T, Mole>

@JvmInline
value class Mole(override val value: Double) : UnitAmount<Mole>, BaseUnit {
    override fun asType(d: Double) = Mole(d)
    override fun asBaseUnit() = this

    operator fun plus(other: UnitAmount<*>) = (this as UnitAmount<*>).plusUnit(other)
    operator fun minus(other: UnitAmount<*>) = (this as UnitAmount<*>).minusUnit(other)
}

 fun UnitAmountType<*>.plusUnit(other: UnitAmountType<*>): Mole =
    Mole(this.asBaseUnit().value + other.asBaseUnit().value)

 fun UnitAmountType<*>.minusUnit(other: UnitAmountType<*>): Mole =
    Mole(this.asBaseUnit().value - other.asBaseUnit().value)

// Non-SI amount units have been moved to unit-common module

// Conversion functions using toUnit
fun UnitAmount<*>.toMole() = this.asBaseUnit()
