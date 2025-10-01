package com.measures.amount

import com.measures.amount.Mole
import com.measures.amount.UnitAmount
import kotlin.jvm.JvmInline

@JvmInline
value class Millimole(override val value: Double) : UnitAmount<Millimole> {
    override fun asType(d: Double) = Millimole(d)
    override fun asBaseUnit() = Mole(this.value * 0.001)

    operator fun plus(other: UnitAmount<*>) = (this as UnitAmount<*>).plusUnit(other)
    operator fun minus(other: UnitAmount<*>) = (this as UnitAmount<*>).minusUnit(other)
}

@JvmInline
value class Micromole(override val value: Double) : UnitAmount<Micromole> {
    override fun asType(d: Double) = Micromole(d)
    override fun asBaseUnit() = Mole(this.value * 1E-6)

    operator fun plus(other: UnitAmount<*>) = (this as UnitAmount<*>).plusUnit(other)
    operator fun minus(other: UnitAmount<*>) = (this as UnitAmount<*>).minusUnit(other)
}

@JvmInline
value class Nanomole(override val value: Double) : UnitAmount<Nanomole> {
    override fun asType(d: Double) = Nanomole(d)
    override fun asBaseUnit() = Mole(this.value * 1E-9)

    operator fun plus(other: UnitAmount<*>) = (this as UnitAmount<*>).plusUnit(other)
    operator fun minus(other: UnitAmount<*>) = (this as UnitAmount<*>).minusUnit(other)
}

@JvmInline
value class Picomole(override val value: Double) : UnitAmount<Picomole> {
    override fun asType(d: Double) = Picomole(d)
    override fun asBaseUnit() = Mole(this.value * 1E-12)

    operator fun plus(other: UnitAmount<*>) = (this as UnitAmount<*>).plusUnit(other)
    operator fun minus(other: UnitAmount<*>) = (this as UnitAmount<*>).minusUnit(other)
}

@JvmInline
value class Kilomole(override val value: Double) : UnitAmount<Kilomole> {
    override fun asType(d: Double) = Kilomole(d)
    override fun asBaseUnit() = Mole(this.value * 1000.0)

    operator fun plus(other: UnitAmount<*>) = (this as UnitAmount<*>).plusUnit(other)
    operator fun minus(other: UnitAmount<*>) = (this as UnitAmount<*>).minusUnit(other)
}

// Conversion functions using toUnit
fun UnitAmount<*>.toMillimole() = toUnit(Millimole(1.0))
fun UnitAmount<*>.toMicromole() = toUnit(Micromole(1.0))
fun UnitAmount<*>.toNanomole() = toUnit(Nanomole(1.0))
fun UnitAmount<*>.toPicomole() = toUnit(Picomole(1.0))
fun UnitAmount<*>.toKilomole() = toUnit(Kilomole(1.0))
