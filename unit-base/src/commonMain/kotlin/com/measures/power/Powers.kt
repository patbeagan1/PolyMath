package com.measures.power

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.distance.UnitDistance
import com.measures.weight.UnitWeight
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

typealias UnitPower<T> = UnitPowerType<T>

interface UnitPowerType<T : DoubleBase> : UnitType<T, Watt>

@JvmInline
value class Watt(override val value: Double) : UnitPower<Watt>, BaseUnit {
    override fun asType(d: Double) = Watt(d)
    override fun asBaseUnit() = this

    operator fun plus(other: UnitPower<*>) = (this as UnitPower<*>).plus(other)
    operator fun minus(other: UnitPower<*>) = (this as UnitPower<*>).minus(other)
    
    companion object {
        fun from(mass: UnitWeight<*>, distance: UnitDistance<*>, time: UnitTime<*>): Watt {
            val massBase = mass.asBaseUnit()
            val distanceBase = distance.asBaseUnit()
            val timeBase = time.asBaseUnit()
            return Watt(massBase.value * distanceBase.value * distanceBase.value / (timeBase.value * timeBase.value * timeBase.value))
        }
    }
}

@JvmInline
value class Milliwatt(override val value: Double) : UnitPower<Milliwatt> {
    override fun asType(d: Double) = Milliwatt(d)
    override fun asBaseUnit() = Watt(this.value * 0.001)

    operator fun plus(other: UnitPower<*>) = (this as UnitPower<*>).plus(other)
    operator fun minus(other: UnitPower<*>) = (this as UnitPower<*>).minus(other)
}

@JvmInline
value class Kilowatt(override val value: Double) : UnitPower<Kilowatt> {
    override fun asType(d: Double) = Kilowatt(d)
    override fun asBaseUnit() = Watt(this.value * 1000.0)

    operator fun plus(other: UnitPower<*>) = (this as UnitPower<*>).plus(other)
    operator fun minus(other: UnitPower<*>) = (this as UnitPower<*>).minus(other)
}

@JvmInline
value class Megawatt(override val value: Double) : UnitPower<Megawatt> {
    override fun asType(d: Double) = Megawatt(d)
    override fun asBaseUnit() = Watt(this.value * 1E6)

    operator fun plus(other: UnitPower<*>) = (this as UnitPower<*>).plus(other)
    operator fun minus(other: UnitPower<*>) = (this as UnitPower<*>).minus(other)
}

@JvmInline
value class Gigawatt(override val value: Double) : UnitPower<Gigawatt> {
    override fun asType(d: Double) = Gigawatt(d)
    override fun asBaseUnit() = Watt(this.value * 1E9)

    operator fun plus(other: UnitPower<*>) = (this as UnitPower<*>).plus(other)
    operator fun minus(other: UnitPower<*>) = (this as UnitPower<*>).minus(other)
}

operator fun UnitPowerType<*>.plus(other: UnitPowerType<*>): Watt =
    Watt(this.asBaseUnit().value + other.asBaseUnit().value)

operator fun UnitPowerType<*>.minus(other: UnitPowerType<*>): Watt =
    Watt(this.asBaseUnit().value - other.asBaseUnit().value)

@JvmInline
value class Horsepower(override val value: Double) : UnitPower<Horsepower> {
    override fun asType(d: Double) = Horsepower(d)
    override fun asBaseUnit() = Watt(this.value * 745.7)

    operator fun plus(other: UnitPower<*>) = (this as UnitPower<*>).plus(other)
    operator fun minus(other: UnitPower<*>) = (this as UnitPower<*>).minus(other)
}

@JvmInline
value class ErgPerSecond(override val value: Double) : UnitPower<ErgPerSecond> {
    override fun asType(d: Double) = ErgPerSecond(d)
    override fun asBaseUnit() = Watt(this.value * 1E-7)

    operator fun plus(other: UnitPower<*>) = (this as UnitPower<*>).plus(other)
    operator fun minus(other: UnitPower<*>) = (this as UnitPower<*>).minus(other)
}

@JvmInline
value class FootPoundPerSecond(override val value: Double) : UnitPower<FootPoundPerSecond> {
    override fun asType(d: Double) = FootPoundPerSecond(d)
    override fun asBaseUnit() = Watt(this.value * 1.355818)

    operator fun plus(other: UnitPower<*>) = (this as UnitPower<*>).plus(other)
    operator fun minus(other: UnitPower<*>) = (this as UnitPower<*>).minus(other)
}

// Conversion functions using toUnit
fun UnitPower<*>.toWatt() = this.asBaseUnit()
fun UnitPower<*>.toMilliwatt() = toUnit(Milliwatt(1.0))
fun UnitPower<*>.toKilowatt() = toUnit(Kilowatt(1.0))
fun UnitPower<*>.toMegawatt() = toUnit(Megawatt(1.0))
fun UnitPower<*>.toGigawatt() = toUnit(Gigawatt(1.0))
fun UnitPower<*>.toHorsepower() = toUnit(Horsepower(1.0))
fun UnitPower<*>.toErgPerSecond() = toUnit(ErgPerSecond(1.0))
fun UnitPower<*>.toFootPoundPerSecond() = toUnit(FootPoundPerSecond(1.0))

