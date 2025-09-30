package com.measures.velocity

import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.time.UnitTime
import com.measures.distance.Meter
import com.measures.acceleration.MetersPerSecondPerSecond
import kotlin.jvm.JvmInline

// Non-SI Velocity Units
// These units are not part of the SI system and are moved to units-common

@JvmInline
value class KilometersPerHour(override val value: Double) : UnitVelocity<KilometersPerHour> {
    override fun asType(d: Double) = KilometersPerHour(d)
    override fun asBaseUnit() = com.measures.velocity.MetersPerSecond(this.value * 0.27778)

    override operator fun plus(other: UnitVelocity<*>) = (this as UnitVelocity<*>).plusUnit(other)
    override operator fun minus(other: UnitVelocity<*>) = (this as UnitVelocity<*>).minusUnit(other)
    override operator fun times(other: UnitTime<*>) = (this as UnitVelocity<*>).timesUnit(other)
    override operator fun div(other: UnitTime<*>) = (this as UnitVelocity<*>).divUnit(other)
}

@JvmInline
value class MilesPerHour(override val value: Double) : UnitVelocity<MilesPerHour> {
    override fun asType(d: Double) = MilesPerHour(d)
    override fun asBaseUnit() = com.measures.velocity.MetersPerSecond(this.value * 0.44704)

    override operator fun plus(other: UnitVelocity<*>) = (this as UnitVelocity<*>).plusUnit(other)
    override operator fun minus(other: UnitVelocity<*>) = (this as UnitVelocity<*>).minusUnit(other)
    override operator fun times(other: UnitTime<*>) = (this as UnitVelocity<*>).timesUnit(other)
    override operator fun div(other: UnitTime<*>) = (this as UnitVelocity<*>).divUnit(other)
}

@JvmInline
value class FeetPerSecond(override val value: Double) : UnitVelocity<FeetPerSecond> {
    override fun asType(d: Double) = FeetPerSecond(d)
    override fun asBaseUnit() = com.measures.velocity.MetersPerSecond(this.value * 0.3048)

    override operator fun plus(other: UnitVelocity<*>) = (this as UnitVelocity<*>).plusUnit(other)
    override operator fun minus(other: UnitVelocity<*>) = (this as UnitVelocity<*>).minusUnit(other)
    override operator fun times(other: UnitTime<*>) = (this as UnitVelocity<*>).timesUnit(other)
    override operator fun div(other: UnitTime<*>) = (this as UnitVelocity<*>).divUnit(other)
}

@JvmInline
value class Knots(override val value: Double) : UnitVelocity<Knots> {
    override fun asType(d: Double) = Knots(d)
    override fun asBaseUnit() = com.measures.velocity.MetersPerSecond(this.value * 0.51444)

    override operator fun plus(other: UnitVelocity<*>) = (this as UnitVelocity<*>).plusUnit(other)
    override operator fun minus(other: UnitVelocity<*>) = (this as UnitVelocity<*>).minusUnit(other)
    override operator fun times(other: UnitTime<*>) = (this as UnitVelocity<*>).timesUnit(other)
    override operator fun div(other: UnitTime<*>) = (this as UnitVelocity<*>).divUnit(other)
}

// Conversion functions for non-SI velocity units
fun UnitVelocity<*>.toKilometersPerHour() = toUnit(KilometersPerHour(1.0))
fun UnitVelocity<*>.toMilesPerHour() = toUnit(MilesPerHour(1.0))
fun UnitVelocity<*>.toFeetPerSecond() = toUnit(FeetPerSecond(1.0))
fun UnitVelocity<*>.toKnots() = toUnit(Knots(1.0))
