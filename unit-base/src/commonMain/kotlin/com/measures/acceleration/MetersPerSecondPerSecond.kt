package com.measures.acceleration

import com.measures.BaseUnit
import com.measures.distance.UnitDistance
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

@JvmInline
value class MetersPerSecondPerSecond(override val value: Double) : UnitAcceleration<MetersPerSecondPerSecond>,
    BaseUnit {
    override fun asType(d: Double) = MetersPerSecondPerSecond(d)
    override fun asBaseUnit() = this

    operator fun plus(other: UnitAcceleration<*>) = (this as UnitAcceleration<*>).plus(other)
    operator fun minus(other: UnitAcceleration<*>) = (this as UnitAcceleration<*>).minus(other)
    operator fun times(other: UnitTime<*>) = (this as UnitAcceleration<*>).times(other)

    companion object {
        fun from(distance: UnitDistance<*>, time: UnitTime<*>): MetersPerSecondPerSecond {
            // Acceleration = distance / (time * time)
            return MetersPerSecondPerSecond(distance.asBaseUnit().value / (time.asBaseUnit().value * time.asBaseUnit().value))
        }
    }
}

@JvmInline
value class CentimetersPerSecondSquared(override val value: Double) : UnitAcceleration<CentimetersPerSecondSquared> {
    override fun asType(d: Double) = CentimetersPerSecondSquared(d)
    override fun asBaseUnit() = MetersPerSecondPerSecond(this.value * 0.01)

    operator fun plus(other: UnitAcceleration<*>) = (this as UnitAcceleration<*>).plus(other)
    operator fun minus(other: UnitAcceleration<*>) = (this as UnitAcceleration<*>).minus(other)
    operator fun times(other: UnitTime<*>) = (this as UnitAcceleration<*>).times(other)
}

fun UnitAcceleration<*>.toMetersPerSecondPerSecond() = this.asBaseUnit()

fun UnitAcceleration<*>.toCentimetersPerSecondSquared() =
    CentimetersPerSecondSquared(this.asBaseUnit().value / 0.01)


@JvmInline
value class FeetPerSecondPerSecond(override val value: Double) : UnitAcceleration<FeetPerSecondPerSecond> {
    override fun asType(d: Double) = FeetPerSecondPerSecond(d)
    override fun asBaseUnit() = MetersPerSecondPerSecond(this.value * 0.3048)

    operator fun plus(other: UnitAcceleration<*>) = (this as UnitAcceleration<*>).plus(other)
    operator fun minus(other: UnitAcceleration<*>) = (this as UnitAcceleration<*>).minus(other)
    operator fun times(other: UnitTime<*>) = (this as UnitAcceleration<*>).times(other)
}

@JvmInline
value class Gal(override val value: Double) : UnitAcceleration<Gal> {
    override fun asType(d: Double) = Gal(d)
    override fun asBaseUnit() = MetersPerSecondPerSecond(this.value * 0.01)

    operator fun plus(other: UnitAcceleration<*>) = (this as UnitAcceleration<*>).plus(other)
    operator fun minus(other: UnitAcceleration<*>) = (this as UnitAcceleration<*>).minus(other)
    operator fun times(other: UnitTime<*>) = (this as UnitAcceleration<*>).times(other)
}

@JvmInline
value class KilometersPerSecondPerSecond(override val value: Double) : UnitAcceleration<KilometersPerSecondPerSecond> {
    override fun asType(d: Double) = KilometersPerSecondPerSecond(d)
    override fun asBaseUnit() = MetersPerSecondPerSecond(this.value * 1000.0)

    operator fun plus(other: UnitAcceleration<*>) = (this as UnitAcceleration<*>).plus(other)
    operator fun minus(other: UnitAcceleration<*>) = (this as UnitAcceleration<*>).minus(other)
    operator fun times(other: UnitTime<*>) = (this as UnitAcceleration<*>).times(other)
}

@JvmInline
value class GForce(override val value: Double) : UnitAcceleration<GForce> {
    override fun asType(d: Double) = GForce(d)
    override fun asBaseUnit() = MetersPerSecondPerSecond(this.value * 9.80665)

    operator fun plus(other: UnitAcceleration<*>) = (this as UnitAcceleration<*>).plus(other)
    operator fun minus(other: UnitAcceleration<*>) = (this as UnitAcceleration<*>).minus(other)
    operator fun times(other: UnitTime<*>) = (this as UnitAcceleration<*>).times(other)
}

// Extension functions for conversion

fun UnitAcceleration<*>.toFeetPerSecondPerSecond() =
    FeetPerSecondPerSecond(this.asBaseUnit().value / 0.3048)

fun UnitAcceleration<*>.toGal() =
    Gal(this.asBaseUnit().value / 0.01)

fun UnitAcceleration<*>.toKilometersPerSecondPerSecond() =
    KilometersPerSecondPerSecond(this.asBaseUnit().value / 1000.0)

fun UnitAcceleration<*>.toGForce() =
    GForce(this.asBaseUnit().value / 9.80665)



