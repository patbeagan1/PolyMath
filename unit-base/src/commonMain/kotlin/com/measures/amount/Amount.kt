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
}

@JvmInline
value class Millimole(override val value: Double) : UnitAmount<Millimole> {
    override fun asType(d: Double) = Millimole(d)
    override fun asBaseUnit() = Mole(this.value * 0.001)
}

@JvmInline
value class Micromole(override val value: Double) : UnitAmount<Micromole> {
    override fun asType(d: Double) = Micromole(d)
    override fun asBaseUnit() = Mole(this.value * 1E-6)
}

@JvmInline
value class Nanomole(override val value: Double) : UnitAmount<Nanomole> {
    override fun asType(d: Double) = Nanomole(d)
    override fun asBaseUnit() = Mole(this.value * 1E-9)
}

@JvmInline
value class Picomole(override val value: Double) : UnitAmount<Picomole> {
    override fun asType(d: Double) = Picomole(d)
    override fun asBaseUnit() = Mole(this.value * 1E-12)
}

@JvmInline
value class Kilomole(override val value: Double) : UnitAmount<Kilomole> {
    override fun asType(d: Double) = Kilomole(d)
    override fun asBaseUnit() = Mole(this.value * 1000.0)
}

operator fun UnitAmountType<*>.plus(other: UnitAmountType<*>): Mole =
    Mole(this.asBaseUnit().value + other.asBaseUnit().value)

operator fun UnitAmountType<*>.minus(other: UnitAmountType<*>): Mole =
    Mole(this.asBaseUnit().value - other.asBaseUnit().value)

// Conversion functions using toUnit
fun UnitAmount<*>.toMole() = this.asBaseUnit()
fun UnitAmount<*>.toMillimole() = toUnit(Millimole(1.0))
fun UnitAmount<*>.toMicromole() = toUnit(Micromole(1.0))
fun UnitAmount<*>.toNanomole() = toUnit(Nanomole(1.0))
fun UnitAmount<*>.toPicomole() = toUnit(Picomole(1.0))
fun UnitAmount<*>.toKilomole() = toUnit(Kilomole(1.0))
