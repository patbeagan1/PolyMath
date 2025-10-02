package com.measures.amount

import kotlin.jvm.JvmInline

@JvmInline
value class Millimole(override val value: Double) : UnitAmount<Millimole> {
    override fun asType(d: Double) = Millimole(d)
    override fun asBaseUnit() = Mole(this.value * 0.001)

    override operator fun plus(other: UnitAmount<*>) = UnitAmount.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitAmount<*>) = UnitAmount.Companion.minusUnit(this, other)
}

@JvmInline
value class Micromole(override val value: Double) : UnitAmount<Micromole> {
    override fun asType(d: Double) = Micromole(d)
    override fun asBaseUnit() = Mole(this.value * 1E-6)

    override operator fun plus(other: UnitAmount<*>) = UnitAmount.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitAmount<*>) = UnitAmount.Companion.minusUnit(this, other)
}

@JvmInline
value class Nanomole(override val value: Double) : UnitAmount<Nanomole> {
    override fun asType(d: Double) = Nanomole(d)
    override fun asBaseUnit() = Mole(this.value * 1E-9)

    override operator fun plus(other: UnitAmount<*>) = UnitAmount.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitAmount<*>) = UnitAmount.Companion.minusUnit(this, other)
}

@JvmInline
value class Picomole(override val value: Double) : UnitAmount<Picomole> {
    override fun asType(d: Double) = Picomole(d)
    override fun asBaseUnit() = Mole(this.value * 1E-12)

    override operator fun plus(other: UnitAmount<*>) = UnitAmount.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitAmount<*>) = UnitAmount.Companion.minusUnit(this, other)
}

@JvmInline
value class Kilomole(override val value: Double) : UnitAmount<Kilomole> {
    override fun asType(d: Double) = Kilomole(d)
    override fun asBaseUnit() = Mole(this.value * 1000.0)

    override operator fun plus(other: UnitAmount<*>) = UnitAmount.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitAmount<*>) = UnitAmount.Companion.minusUnit(this, other)
}

// Conversion functions using toUnit
fun UnitAmount<*>.toMillimole() = toUnit(Millimole(1.0))
fun UnitAmount<*>.toMicromole() = toUnit(Micromole(1.0))
fun UnitAmount<*>.toNanomole() = toUnit(Nanomole(1.0))
fun UnitAmount<*>.toPicomole() = toUnit(Picomole(1.0))
fun UnitAmount<*>.toKilomole() = toUnit(Kilomole(1.0))
