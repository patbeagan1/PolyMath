package com.measures.charge

import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

// Non-SI Charge Units
// These units are not part of the SI system and are moved to units-common

@JvmInline
value class AmpereHour(override val value: Double) : UnitCharge<AmpereHour> {
    override fun asType(d: Double) = AmpereHour(d)
    override fun asBaseUnit() = Coulomb(this.value * 3600.0)

    override operator fun plus(other: UnitCharge<*>) = UnitCharge.plusUnit(this, other)
    override operator fun minus(other: UnitCharge<*>) = UnitCharge.minusUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitCharge.divUnit(this, other)
}

@JvmInline
value class MilliampereHour(override val value: Double) : UnitCharge<MilliampereHour> {
    override fun asType(d: Double) = MilliampereHour(d)
    override fun asBaseUnit() = Coulomb(this.value * 3.6)

    override operator fun plus(other: UnitCharge<*>) = UnitCharge.plusUnit(this, other)
    override operator fun minus(other: UnitCharge<*>) = UnitCharge.minusUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitCharge.divUnit(this, other)
}

@JvmInline
value class Millicoulomb(override val value: Double) : UnitCharge<Millicoulomb> {
    override fun asType(d: Double) = Millicoulomb(d)
    override fun asBaseUnit() = Coulomb(this.value * 0.001)

    override operator fun plus(other: UnitCharge<*>) = UnitCharge.plusUnit(this, other)
    override operator fun minus(other: UnitCharge<*>) = UnitCharge.minusUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitCharge.divUnit(this, other)
}

@JvmInline
value class Microcoulomb(override val value: Double) : UnitCharge<Microcoulomb> {
    override fun asType(d: Double) = Microcoulomb(d)
    override fun asBaseUnit() = Coulomb(this.value * 1E-6)

    override operator fun plus(other: UnitCharge<*>) = UnitCharge.plusUnit(this, other)
    override operator fun minus(other: UnitCharge<*>) = UnitCharge.minusUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitCharge.divUnit(this, other)
}

@JvmInline
value class Nanocoulomb(override val value: Double) : UnitCharge<Nanocoulomb> {
    override fun asType(d: Double) = Nanocoulomb(d)
    override fun asBaseUnit() = Coulomb(this.value * 1E-9)

    override operator fun plus(other: UnitCharge<*>) = UnitCharge.plusUnit(this, other)
    override operator fun minus(other: UnitCharge<*>) = UnitCharge.minusUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitCharge.divUnit(this, other)
}

@JvmInline
value class Picocoulomb(override val value: Double) : UnitCharge<Picocoulomb> {
    override fun asType(d: Double) = Picocoulomb(d)
    override fun asBaseUnit() = Coulomb(this.value * 1E-12)

    override operator fun plus(other: UnitCharge<*>) = UnitCharge.plusUnit(this, other)
    override operator fun minus(other: UnitCharge<*>) = UnitCharge.minusUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitCharge.divUnit(this, other)
}

@JvmInline
value class Kilocoulomb(override val value: Double) : UnitCharge<Kilocoulomb> {
    override fun asType(d: Double) = Kilocoulomb(d)
    override fun asBaseUnit() = Coulomb(this.value * 1000.0)

    override operator fun plus(other: UnitCharge<*>) = UnitCharge.plusUnit(this, other)
    override operator fun minus(other: UnitCharge<*>) = UnitCharge.minusUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitCharge.divUnit(this, other)
}

// Conversion functions for non-SI charge units
fun UnitCharge<*>.toAmpereHour() = toUnit(AmpereHour(1.0))
fun UnitCharge<*>.toMilliampereHour() = toUnit(MilliampereHour(1.0))
fun UnitCharge<*>.toMillicoulomb() = toUnit(Millicoulomb(1.0))
fun UnitCharge<*>.toMicrocoulomb() = toUnit(Microcoulomb(1.0))
fun UnitCharge<*>.toNanocoulomb() = toUnit(Nanocoulomb(1.0))
fun UnitCharge<*>.toPicocoulomb() = toUnit(Picocoulomb(1.0))
fun UnitCharge<*>.toKilocoulomb() = toUnit(Kilocoulomb(1.0))
