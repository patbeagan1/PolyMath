package com.measures.velocity

import com.measures.BaseUnit
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

@JvmInline
value class MetersPerSecond(override val value: Double) : UnitVelocity<MetersPerSecond>, BaseUnit {
    override fun asType(d: Double) = MetersPerSecond(d)
    override fun asBaseUnit() = this

    override operator fun plus(other: UnitVelocity<*>) = (this as UnitVelocity<*>).plusUnit(other)
    override operator fun minus(other: UnitVelocity<*>) = (this as UnitVelocity<*>).minusUnit(other)
    override operator fun times(other: UnitTime<*>) = (this as UnitVelocity<*>).timesUnit(other)
    override operator fun div(other: UnitTime<*>) = (this as UnitVelocity<*>).divUnit(other)
}

fun UnitVelocity<*>.toMetersPerSecond() = this.asBaseUnit()

@JvmInline
value class KilometersPerHour(override val value: Double) : UnitVelocity<KilometersPerHour> {
    override fun asType(d: Double) = KilometersPerHour(d)
    override fun asBaseUnit() = MetersPerSecond(this.value * 0.27778)

    override operator fun plus(other: UnitVelocity<*>) = (this as UnitVelocity<*>).plusUnit(other)
    override operator fun minus(other: UnitVelocity<*>) = (this as UnitVelocity<*>).minusUnit(other)
    override operator fun times(other: UnitTime<*>) = (this as UnitVelocity<*>).timesUnit(other)
    override operator fun div(other: UnitTime<*>) = (this as UnitVelocity<*>).divUnit(other)
}

@JvmInline
value class MilesPerHour(override val value: Double) : UnitVelocity<MilesPerHour> {
    override fun asType(d: Double) = MilesPerHour(d)
    override fun asBaseUnit() = MetersPerSecond(this.value * 0.44704)

    override operator fun plus(other: UnitVelocity<*>) = (this as UnitVelocity<*>).plusUnit(other)
    override operator fun minus(other: UnitVelocity<*>) = (this as UnitVelocity<*>).minusUnit(other)
    override operator fun times(other: UnitTime<*>) = (this as UnitVelocity<*>).timesUnit(other)
    override operator fun div(other: UnitTime<*>) = (this as UnitVelocity<*>).divUnit(other)
}

@JvmInline
value class FeetPerSecond(override val value: Double) : UnitVelocity<FeetPerSecond> {
    override fun asType(d: Double) = FeetPerSecond(d)
    override fun asBaseUnit() = MetersPerSecond(this.value * 0.3048)

    override operator fun plus(other: UnitVelocity<*>) = (this as UnitVelocity<*>).plusUnit(other)
    override operator fun minus(other: UnitVelocity<*>) = (this as UnitVelocity<*>).minusUnit(other)
    override operator fun times(other: UnitTime<*>) = (this as UnitVelocity<*>).timesUnit(other)
    override operator fun div(other: UnitTime<*>) = (this as UnitVelocity<*>).divUnit(other)
}

@JvmInline
value class CentimetersPerSecond(override val value: Double) : UnitVelocity<CentimetersPerSecond> {
    override fun asType(d: Double) = CentimetersPerSecond(d)
    override fun asBaseUnit() = MetersPerSecond(this.value * 0.01)

    override operator fun plus(other: UnitVelocity<*>) = (this as UnitVelocity<*>).plusUnit(other)
    override operator fun minus(other: UnitVelocity<*>) = (this as UnitVelocity<*>).minusUnit(other)
    override operator fun times(other: UnitTime<*>) = (this as UnitVelocity<*>).timesUnit(other)
    override operator fun div(other: UnitTime<*>) = (this as UnitVelocity<*>).divUnit(other)
}

@JvmInline
value class Knots(override val value: Double) : UnitVelocity<Knots> {
    override fun asType(d: Double) = Knots(d)
    override fun asBaseUnit() = MetersPerSecond(this.value * 0.51444)

    override operator fun plus(other: UnitVelocity<*>) = (this as UnitVelocity<*>).plusUnit(other)
    override operator fun minus(other: UnitVelocity<*>) = (this as UnitVelocity<*>).minusUnit(other)
    override operator fun times(other: UnitTime<*>) = (this as UnitVelocity<*>).timesUnit(other)
    override operator fun div(other: UnitTime<*>) = (this as UnitVelocity<*>).divUnit(other)
}

// Extension functions for conversion

fun UnitVelocity<*>.toKilometersPerHour() =
    KilometersPerHour(this.asBaseUnit().value / 0.27778)

fun UnitVelocity<*>.toMilesPerHour() =
    MilesPerHour(this.asBaseUnit().value / 0.44704)

fun UnitVelocity<*>.toFeetPerSecond() =
    FeetPerSecond(this.asBaseUnit().value / 0.3048)

fun UnitVelocity<*>.toCentimetersPerSecond() =
    CentimetersPerSecond(this.asBaseUnit().value / 0.01)

fun UnitVelocity<*>.toKnots() =
    Knots(this.asBaseUnit().value / 0.51444)
