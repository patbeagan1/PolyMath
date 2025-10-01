package com.measures.potential

import com.measures.potential.Volt
import com.measures.potential.UnitPotential
import com.measures.charge.UnitCharge
import kotlin.jvm.JvmInline

@JvmInline
value class Millivolt(override val value: Double) : UnitPotential<Millivolt> {
    override fun asType(d: Double) = Millivolt(d)
    override fun asBaseUnit() = Volt(this.value * 0.001)

    operator fun plus(other: UnitPotential<*>) = (this as UnitPotential<*>).plusUnit(other)
    operator fun minus(other: UnitPotential<*>) = (this as UnitPotential<*>).minusUnit(other)
    operator fun times(other: UnitCharge<*>) = (this as UnitPotential<*>).timesUnit(other)
}

@JvmInline
value class Microvolt(override val value: Double) : UnitPotential<Microvolt> {
    override fun asType(d: Double) = Microvolt(d)
    override fun asBaseUnit() = Volt(this.value * 1E-6)

    operator fun plus(other: UnitPotential<*>) = (this as UnitPotential<*>).plusUnit(other)
    operator fun minus(other: UnitPotential<*>) = (this as UnitPotential<*>).minusUnit(other)
    operator fun times(other: UnitCharge<*>) = (this as UnitPotential<*>).timesUnit(other)
}

@JvmInline
value class Kilovolt(override val value: Double) : UnitPotential<Kilovolt> {
    override fun asType(d: Double) = Kilovolt(d)
    override fun asBaseUnit() = Volt(this.value * 1000.0)

    operator fun plus(other: UnitPotential<*>) = (this as UnitPotential<*>).plusUnit(other)
    operator fun minus(other: UnitPotential<*>) = (this as UnitPotential<*>).minusUnit(other)
    operator fun times(other: UnitCharge<*>) = (this as UnitPotential<*>).timesUnit(other)
}

@JvmInline
value class Megavolt(override val value: Double) : UnitPotential<Megavolt> {
    override fun asType(d: Double) = Megavolt(d)
    override fun asBaseUnit() = Volt(this.value * 1E6)

    operator fun plus(other: UnitPotential<*>) = (this as UnitPotential<*>).plusUnit(other)
    operator fun minus(other: UnitPotential<*>) = (this as UnitPotential<*>).minusUnit(other)
    operator fun times(other: UnitCharge<*>) = (this as UnitPotential<*>).timesUnit(other)
}

@JvmInline
value class Gigavolt(override val value: Double) : UnitPotential<Gigavolt> {
    override fun asType(d: Double) = Gigavolt(d)
    override fun asBaseUnit() = Volt(this.value * 1E9)

    operator fun plus(other: UnitPotential<*>) = (this as UnitPotential<*>).plusUnit(other)
    operator fun minus(other: UnitPotential<*>) = (this as UnitPotential<*>).minusUnit(other)
    operator fun times(other: UnitCharge<*>) = (this as UnitPotential<*>).timesUnit(other)
}

// Conversion functions using toUnit
fun UnitPotential<*>.toMillivolt() = toUnit(Millivolt(1.0))
fun UnitPotential<*>.toMicrovolt() = toUnit(Microvolt(1.0))
fun UnitPotential<*>.toKilovolt() = toUnit(Kilovolt(1.0))
fun UnitPotential<*>.toMegavolt() = toUnit(Megavolt(1.0))
fun UnitPotential<*>.toGigavolt() = toUnit(Gigavolt(1.0))
