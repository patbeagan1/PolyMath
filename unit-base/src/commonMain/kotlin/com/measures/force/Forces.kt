package com.measures.force

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.distance.UnitDistance
import com.measures.weight.UnitWeight
import com.measures.time.UnitTime
import com.measures.area.UnitArea
import com.measures.pressure.Pascal
import kotlin.jvm.JvmInline

typealias UnitForce<T> = UnitForceType<T>

interface UnitForceType<T : DoubleBase> : UnitType<T, Newton>

@JvmInline
value class Newton(override val value: Double) : UnitForce<Newton>, BaseUnit {
    override fun asType(d: Double) = Newton(d)
    override fun asBaseUnit() = this

    operator fun plus(other: UnitForce<*>) = (this as UnitForce<*>).plus(other)
    operator fun minus(other: UnitForce<*>) = (this as UnitForce<*>).minus(other)
    operator fun div(other: UnitArea<*>) = (this as UnitForce<*>).div(other)
    
    companion object {
        fun from(mass: UnitWeight<*>, distance: UnitDistance<*>, time: UnitTime<*>): Newton {
            val massBase = mass.asBaseUnit()
            val distanceBase = distance.asBaseUnit()
            val timeBase = time.asBaseUnit()
            return Newton(massBase.value * distanceBase.value / (timeBase.value * timeBase.value))
        }
    }
}

@JvmInline
value class Dyne(override val value: Double) : UnitForce<Dyne> {
    override fun asType(d: Double) = Dyne(d)
    override fun asBaseUnit() = Newton(this.value * 1E-5)

    operator fun plus(other: UnitForce<*>) = (this as UnitForce<*>).plus(other)
    operator fun minus(other: UnitForce<*>) = (this as UnitForce<*>).minus(other)
    operator fun div(other: UnitArea<*>) = (this as UnitForce<*>).div(other)
}

@JvmInline
value class PoundForce(override val value: Double) : UnitForce<PoundForce> {
    override fun asType(d: Double) = PoundForce(d)
    override fun asBaseUnit() = Newton(this.value * 4.448222)

    operator fun plus(other: UnitForce<*>) = (this as UnitForce<*>).plus(other)
    operator fun minus(other: UnitForce<*>) = (this as UnitForce<*>).minus(other)
    operator fun div(other: UnitArea<*>) = (this as UnitForce<*>).div(other)
}

@JvmInline
value class KilogramForce(override val value: Double) : UnitForce<KilogramForce> {
    override fun asType(d: Double) = KilogramForce(d)
    override fun asBaseUnit() = Newton(this.value * 9.80665)

    operator fun plus(other: UnitForce<*>) = (this as UnitForce<*>).plus(other)
    operator fun minus(other: UnitForce<*>) = (this as UnitForce<*>).minus(other)
    operator fun div(other: UnitArea<*>) = (this as UnitForce<*>).div(other)
}

@JvmInline
value class Kilonewton(override val value: Double) : UnitForce<Kilonewton> {
    override fun asType(d: Double) = Kilonewton(d)
    override fun asBaseUnit() = Newton(this.value * 1000.0)

    operator fun plus(other: UnitForce<*>) = (this as UnitForce<*>).plus(other)
    operator fun minus(other: UnitForce<*>) = (this as UnitForce<*>).minus(other)
    operator fun div(other: UnitArea<*>) = (this as UnitForce<*>).div(other)
}

@JvmInline
value class Meganewton(override val value: Double) : UnitForce<Meganewton> {
    override fun asType(d: Double) = Meganewton(d)
    override fun asBaseUnit() = Newton(this.value * 1E6)

    operator fun plus(other: UnitForce<*>) = (this as UnitForce<*>).plus(other)
    operator fun minus(other: UnitForce<*>) = (this as UnitForce<*>).minus(other)
    operator fun div(other: UnitArea<*>) = (this as UnitForce<*>).div(other)
}

operator fun UnitForceType<*>.plus(other: UnitForceType<*>): Newton =
    Newton(this.asBaseUnit().value + other.asBaseUnit().value)

operator fun UnitForceType<*>.minus(other: UnitForceType<*>): Newton =
    Newton(this.asBaseUnit().value - other.asBaseUnit().value)

operator fun UnitForceType<*>.div(other: UnitArea<*>): Pascal =
    Pascal(this.asBaseUnit().value / other.asBaseUnit().value)

// Conversion functions using toUnit
fun UnitForce<*>.toNewton() = this.asBaseUnit()
fun UnitForce<*>.toDyne() = toUnit(Dyne(1.0))
fun UnitForce<*>.toPoundForce() = toUnit(PoundForce(1.0))
fun UnitForce<*>.toKilogramForce() = toUnit(KilogramForce(1.0))
fun UnitForce<*>.toKilonewton() = toUnit(Kilonewton(1.0))
fun UnitForce<*>.toMeganewton() = toUnit(Meganewton(1.0))

