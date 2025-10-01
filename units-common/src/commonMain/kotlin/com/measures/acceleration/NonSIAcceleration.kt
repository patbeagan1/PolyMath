package com.measures.acceleration

import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.time.UnitTime
import com.measures.velocity.MetersPerSecond
import kotlin.jvm.JvmInline

// Non-SI Acceleration Units
// These units are not part of the SI system and are moved to units-common

@JvmInline
value class FeetPerSecondPerSecond(override val value: Double) : UnitAcceleration<FeetPerSecondPerSecond> {
    override fun asType(d: Double) = FeetPerSecondPerSecond(d)
    override fun asBaseUnit() = com.measures.acceleration.MetersPerSecondPerSecond(this.value * 0.3048)

    operator fun plus(other: UnitAcceleration<*>) = (this as UnitAcceleration<*>).plusUnit(other)
    operator fun minus(other: UnitAcceleration<*>) = (this as UnitAcceleration<*>).minusUnit(other)
    operator fun times(other: UnitTime<*>) = (this as UnitAcceleration<*>).timesUnit(other)
}

@JvmInline
value class Gal(override val value: Double) : UnitAcceleration<Gal> {
    override fun asType(d: Double) = Gal(d)
    override fun asBaseUnit() = com.measures.acceleration.MetersPerSecondPerSecond(this.value * 0.01)

    operator fun plus(other: UnitAcceleration<*>) = (this as UnitAcceleration<*>).plusUnit(other)
    operator fun minus(other: UnitAcceleration<*>) = (this as UnitAcceleration<*>).minusUnit(other)
    operator fun times(other: UnitTime<*>) = (this as UnitAcceleration<*>).timesUnit(other)
}

@JvmInline
value class GForce(override val value: Double) : UnitAcceleration<GForce> {
    override fun asType(d: Double) = GForce(d)
    override fun asBaseUnit() = com.measures.acceleration.MetersPerSecondPerSecond(this.value * 9.80665)

    operator fun plus(other: UnitAcceleration<*>) = (this as UnitAcceleration<*>).plusUnit(other)
    operator fun minus(other: UnitAcceleration<*>) = (this as UnitAcceleration<*>).minusUnit(other)
    operator fun times(other: UnitTime<*>) = (this as UnitAcceleration<*>).timesUnit(other)
}

@JvmInline
value class CentimetersPerSecondSquared(override val value: Double) : UnitAcceleration<CentimetersPerSecondSquared> {
    override fun asType(d: Double) = CentimetersPerSecondSquared(d)
    override fun asBaseUnit() = com.measures.acceleration.MetersPerSecondPerSecond(this.value * 0.01)

    operator fun plus(other: UnitAcceleration<*>) = (this as UnitAcceleration<*>).plusUnit(other)
    operator fun minus(other: UnitAcceleration<*>) = (this as UnitAcceleration<*>).minusUnit(other)
    operator fun times(other: UnitTime<*>) = (this as UnitAcceleration<*>).timesUnit(other)
}

@JvmInline
value class KilometersPerSecondPerSecond(override val value: Double) : UnitAcceleration<KilometersPerSecondPerSecond> {
    override fun asType(d: Double) = KilometersPerSecondPerSecond(d)
    override fun asBaseUnit() = com.measures.acceleration.MetersPerSecondPerSecond(this.value * 1000.0)

    operator fun plus(other: UnitAcceleration<*>) = (this as UnitAcceleration<*>).plusUnit(other)
    operator fun minus(other: UnitAcceleration<*>) = (this as UnitAcceleration<*>).minusUnit(other)
    operator fun times(other: UnitTime<*>) = (this as UnitAcceleration<*>).timesUnit(other)
}

// Conversion functions for non-SI acceleration units
fun UnitAcceleration<*>.toFeetPerSecondPerSecond() = toUnit(FeetPerSecondPerSecond(1.0))
fun UnitAcceleration<*>.toGal() = toUnit(Gal(1.0))
fun UnitAcceleration<*>.toGForce() = toUnit(GForce(1.0))
fun UnitAcceleration<*>.toCentimetersPerSecondSquared() = toUnit(CentimetersPerSecondSquared(1.0))
fun UnitAcceleration<*>.toKilometersPerSecondPerSecond() = toUnit(KilometersPerSecondPerSecond(1.0))
