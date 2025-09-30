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

    operator fun plus(other: UnitAcceleration<*>) = (this as UnitAcceleration<*>).plusUnit(other)
    operator fun minus(other: UnitAcceleration<*>) = (this as UnitAcceleration<*>).minusUnit(other)
    operator fun times(other: UnitTime<*>) = (this as UnitAcceleration<*>).timesUnit(other)

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

    operator fun plus(other: UnitAcceleration<*>) = (this as UnitAcceleration<*>).plusUnit(other)
    operator fun minus(other: UnitAcceleration<*>) = (this as UnitAcceleration<*>).minusUnit(other)
    operator fun times(other: UnitTime<*>) = (this as UnitAcceleration<*>).timesUnit(other)
}

fun UnitAcceleration<*>.toMetersPerSecondPerSecond() = this.asBaseUnit()

fun UnitAcceleration<*>.toCentimetersPerSecondSquared() =
    CentimetersPerSecondSquared(this.asBaseUnit().value / 0.01)


@JvmInline
value class FeetPerSecondPerSecond(override val value: Double) : UnitAcceleration<FeetPerSecondPerSecond> {
    override fun asType(d: Double) = FeetPerSecondPerSecond(d)
    override fun asBaseUnit() = MetersPerSecondPerSecond(this.value * 0.3048)

    operator fun plus(other: UnitAcceleration<*>) = (this as UnitAcceleration<*>).plusUnit(other)
    operator fun minus(other: UnitAcceleration<*>) = (this as UnitAcceleration<*>).minusUnit(other)
    operator fun times(other: UnitTime<*>) = (this as UnitAcceleration<*>).timesUnit(other)
}

@JvmInline
value class Gal(override val value: Double) : UnitAcceleration<Gal> {
    override fun asType(d: Double) = Gal(d)
    override fun asBaseUnit() = MetersPerSecondPerSecond(this.value * 0.01)

    operator fun plus(other: UnitAcceleration<*>) = (this as UnitAcceleration<*>).plusUnit(other)
    operator fun minus(other: UnitAcceleration<*>) = (this as UnitAcceleration<*>).minusUnit(other)
    operator fun times(other: UnitTime<*>) = (this as UnitAcceleration<*>).timesUnit(other)
}

@JvmInline
value class KilometersPerSecondPerSecond(override val value: Double) : UnitAcceleration<KilometersPerSecondPerSecond> {
    override fun asType(d: Double) = KilometersPerSecondPerSecond(d)
    override fun asBaseUnit() = MetersPerSecondPerSecond(this.value * 1000.0)

    operator fun plus(other: UnitAcceleration<*>) = (this as UnitAcceleration<*>).plusUnit(other)
    operator fun minus(other: UnitAcceleration<*>) = (this as UnitAcceleration<*>).minusUnit(other)
    operator fun times(other: UnitTime<*>) = (this as UnitAcceleration<*>).timesUnit(other)
}

@JvmInline
value class GForce(override val value: Double) : UnitAcceleration<GForce> {
    override fun asType(d: Double) = GForce(d)
    override fun asBaseUnit() = MetersPerSecondPerSecond(this.value * 9.80665)

    operator fun plus(other: UnitAcceleration<*>) = (this as UnitAcceleration<*>).plusUnit(other)
    operator fun minus(other: UnitAcceleration<*>) = (this as UnitAcceleration<*>).minusUnit(other)
    operator fun times(other: UnitTime<*>) = (this as UnitAcceleration<*>).timesUnit(other)
}

fun UnitAcceleration<*>.toFeetPerSecondPerSecond() = toUnit(FeetPerSecondPerSecond(1.0))
fun UnitAcceleration<*>.toGal() = toUnit(Gal(1.0))
fun UnitAcceleration<*>.toKilometersPerSecondPerSecond() = toUnit(KilometersPerSecondPerSecond(1.0))
fun UnitAcceleration<*>.toGForce() = toUnit(GForce(1.0))



