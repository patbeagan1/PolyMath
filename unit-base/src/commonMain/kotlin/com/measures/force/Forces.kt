package com.measures.force

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitTypedFull
import com.measures.weight.UnitWeight
import com.measures.distance.UnitDistance
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

typealias UnitForce<T> = UnitForceTypedFull<T>

interface UnitForceTypedFull<T : DoubleBase> : UnitTypedFull<T, Newton> {
    operator fun plus(other: UnitForceTypedFull<*>) =
        Newton(this.asBaseUnit().value + other.asBaseUnit().value)

    operator fun minus(other: UnitForceTypedFull<*>) =
        Newton(this.asBaseUnit().value - other.asBaseUnit().value)
}

@JvmInline
value class Newton(override val value: Double) : UnitForce<Newton>, BaseUnit {
    override fun asType(d: Double) = Newton(d)
    override fun asBaseUnit() = this
    
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
}

@JvmInline
value class PoundForce(override val value: Double) : UnitForce<PoundForce> {
    override fun asType(d: Double) = PoundForce(d)
    override fun asBaseUnit() = Newton(this.value * 4.448222)
}

@JvmInline
value class KilogramForce(override val value: Double) : UnitForce<KilogramForce> {
    override fun asType(d: Double) = KilogramForce(d)
    override fun asBaseUnit() = Newton(this.value * 9.80665)
}

@JvmInline
value class Kilonewton(override val value: Double) : UnitForce<Kilonewton> {
    override fun asType(d: Double) = Kilonewton(d)
    override fun asBaseUnit() = Newton(this.value * 1000.0)
}

@JvmInline
value class Meganewton(override val value: Double) : UnitForce<Meganewton> {
    override fun asType(d: Double) = Meganewton(d)
    override fun asBaseUnit() = Newton(this.value * 1E6)
}

// Conversion functions using toUnit
fun UnitForce<*>.toNewton() = this.asBaseUnit()
fun UnitForce<*>.toDyne() = toUnit(Dyne(1.0))
fun UnitForce<*>.toPoundForce() = toUnit(PoundForce(1.0))
fun UnitForce<*>.toKilogramForce() = toUnit(KilogramForce(1.0))
fun UnitForce<*>.toKilonewton() = toUnit(Kilonewton(1.0))
fun UnitForce<*>.toMeganewton() = toUnit(Meganewton(1.0))

