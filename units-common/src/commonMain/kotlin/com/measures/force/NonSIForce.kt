package com.measures.force

import com.measures.acceleration.UnitAcceleration
import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

// Non-SI Force Units
// These units are not part of the SI system and are moved to units-common

@JvmInline
value class Dyne(override val value: Double) : UnitForce<Dyne> {
    override fun asType(d: Double) = Dyne(d)
    override fun asBaseUnit() = Newton(this.value * 1E-5)

    override operator fun plus(other: UnitForce<*>) = (this as UnitForce<*>).plusUnit(other)
    override operator fun minus(other: UnitForce<*>) = (this as UnitForce<*>).minusUnit(other)
    override operator fun div(other: UnitArea<*>) = (this as UnitForce<*>).divUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitForce<*>).timesUnit(other)
    override operator fun div(other: UnitMass<*>) = (this as UnitForce<*>).divUnit(other)
    override operator fun div(other: UnitAcceleration<*>) = (this as UnitForce<*>).divUnit(other)
}

@JvmInline
value class PoundForce(override val value: Double) : UnitForce<PoundForce> {
    override fun asType(d: Double) = PoundForce(d)
    override fun asBaseUnit() = Newton(this.value * 4.448222)

    override operator fun plus(other: UnitForce<*>) = (this as UnitForce<*>).plusUnit(other)
    override operator fun minus(other: UnitForce<*>) = (this as UnitForce<*>).minusUnit(other)
    override operator fun div(other: UnitArea<*>) = (this as UnitForce<*>).divUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitForce<*>).timesUnit(other)
    override operator fun div(other: UnitMass<*>) = (this as UnitForce<*>).divUnit(other)
    override operator fun div(other: UnitAcceleration<*>) = (this as UnitForce<*>).divUnit(other)
}

@JvmInline
value class KilogramForce(override val value: Double) : UnitForce<KilogramForce> {
    override fun asType(d: Double) = KilogramForce(d)
    override fun asBaseUnit() = Newton(this.value * 9.80665)

    override operator fun plus(other: UnitForce<*>) = (this as UnitForce<*>).plusUnit(other)
    override operator fun minus(other: UnitForce<*>) = (this as UnitForce<*>).minusUnit(other)
    override operator fun div(other: UnitArea<*>) = (this as UnitForce<*>).divUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitForce<*>).timesUnit(other)
    override operator fun div(other: UnitMass<*>) = (this as UnitForce<*>).divUnit(other)
    override operator fun div(other: UnitAcceleration<*>) = (this as UnitForce<*>).divUnit(other)
}

@JvmInline
value class Kilonewton(override val value: Double) : UnitForce<Kilonewton> {
    override fun asType(d: Double) = Kilonewton(d)
    override fun asBaseUnit() = Newton(this.value * 1000.0)

    override operator fun plus(other: UnitForce<*>) = (this as UnitForce<*>).plusUnit(other)
    override operator fun minus(other: UnitForce<*>) = (this as UnitForce<*>).minusUnit(other)
    override operator fun div(other: UnitArea<*>) = (this as UnitForce<*>).divUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitForce<*>).timesUnit(other)
    override operator fun div(other: UnitMass<*>) = (this as UnitForce<*>).divUnit(other)
    override operator fun div(other: UnitAcceleration<*>) = (this as UnitForce<*>).divUnit(other)
}

@JvmInline
value class Meganewton(override val value: Double) : UnitForce<Meganewton> {
    override fun asType(d: Double) = Meganewton(d)
    override fun asBaseUnit() = Newton(this.value * 1E6)

    override operator fun plus(other: UnitForce<*>) = (this as UnitForce<*>).plusUnit(other)
    override operator fun minus(other: UnitForce<*>) = (this as UnitForce<*>).minusUnit(other)
    override operator fun div(other: UnitArea<*>) = (this as UnitForce<*>).divUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitForce<*>).timesUnit(other)
    override operator fun div(other: UnitMass<*>) = (this as UnitForce<*>).divUnit(other)
    override operator fun div(other: UnitAcceleration<*>) = (this as UnitForce<*>).divUnit(other)
}

// Conversion functions for non-SI force units
fun UnitForce<*>.toDyne() = toUnit(Dyne(1.0))
fun UnitForce<*>.toPoundForce() = toUnit(PoundForce(1.0))
fun UnitForce<*>.toKilogramForce() = toUnit(KilogramForce(1.0))
fun UnitForce<*>.toKilonewton() = toUnit(Kilonewton(1.0))
fun UnitForce<*>.toMeganewton() = toUnit(Meganewton(1.0))
