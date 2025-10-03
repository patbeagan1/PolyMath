package com.measures.velocity

import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

// Non-SI Velocity Units
// These units are not part of the SI system and are moved to units-common

@JvmInline
value class KilometersPerHour(override val value: Double) : UnitVelocity<KilometersPerHour> {
    override fun asType(d: Double) = KilometersPerHour(d)
    override fun asBaseUnit() = com.measures.velocity.MetersPerSecond(this.value * 0.27778)

    override operator fun plus(other: UnitVelocity<*>) = UnitVelocity.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitVelocity<*>) = UnitVelocity.Companion.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitVelocity.Companion.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitVelocity.Companion.divUnit(this, other)
}

@JvmInline
value class MilesPerHour(override val value: Double) : UnitVelocity<MilesPerHour> {
    override fun asType(d: Double) = MilesPerHour(d)
    override fun asBaseUnit() = com.measures.velocity.MetersPerSecond(this.value * 0.44704)

    override operator fun plus(other: UnitVelocity<*>) = UnitVelocity.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitVelocity<*>) = UnitVelocity.Companion.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitVelocity.Companion.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitVelocity.Companion.divUnit(this, other)
}

@JvmInline
value class FeetPerSecond(override val value: Double) : UnitVelocity<FeetPerSecond> {
    override fun asType(d: Double) = FeetPerSecond(d)
    override fun asBaseUnit() = com.measures.velocity.MetersPerSecond(this.value * 0.3048)

    override operator fun plus(other: UnitVelocity<*>) = UnitVelocity.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitVelocity<*>) = UnitVelocity.Companion.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitVelocity.Companion.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitVelocity.Companion.divUnit(this, other)
}

@JvmInline
value class Knots(override val value: Double) : UnitVelocity<Knots> {
    override fun asType(d: Double) = Knots(d)
    override fun asBaseUnit() = com.measures.velocity.MetersPerSecond(this.value * 0.51444)

    override operator fun plus(other: UnitVelocity<*>) = UnitVelocity.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitVelocity<*>) = UnitVelocity.Companion.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitVelocity.Companion.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitVelocity.Companion.divUnit(this, other)
}

@JvmInline
value class CentimetersPerSecond(override val value: Double) : UnitVelocity<CentimetersPerSecond> {
    override fun asType(d: Double) = CentimetersPerSecond(d)
    override fun asBaseUnit() = com.measures.velocity.MetersPerSecond(this.value * 0.01)

    override operator fun plus(other: UnitVelocity<*>) = UnitVelocity.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitVelocity<*>) = UnitVelocity.Companion.minusUnit(this, other)
    override operator fun times(other: UnitTime<*>) = UnitVelocity.Companion.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitVelocity.Companion.divUnit(this, other)
}

// Conversion functions for non-SI velocity units
fun UnitVelocity<*>.toKilometersPerHour() = toUnit(KilometersPerHour(1.0))
fun UnitVelocity<*>.toMilesPerHour() = toUnit(MilesPerHour(1.0))
fun UnitVelocity<*>.toFeetPerSecond() = toUnit(FeetPerSecond(1.0))
fun UnitVelocity<*>.toKnots() = toUnit(Knots(1.0))
fun UnitVelocity<*>.toCentimetersPerSecond() = toUnit(CentimetersPerSecond(1.0))
