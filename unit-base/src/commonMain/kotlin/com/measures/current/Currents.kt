package com.measures.current

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitTypedFull
import kotlin.jvm.JvmInline

typealias UnitCurrent<T> = UnitCurrentTypedFull<T>

interface UnitCurrentTypedFull<T : DoubleBase> : UnitTypedFull<T, Ampere> {
    operator fun plus(other: UnitCurrentTypedFull<*>) =
        Ampere(this.asBaseUnit().value + other.asBaseUnit().value)

    operator fun minus(other: UnitCurrentTypedFull<*>) =
        Ampere(this.asBaseUnit().value - other.asBaseUnit().value)
}

@JvmInline
value class Ampere(override val value: Double) : UnitCurrent<Ampere>, BaseUnit {
    override fun asType(d: Double) = Ampere(d)
    override fun asBaseUnit() = this
}

@JvmInline
value class Milliampere(override val value: Double) : UnitCurrent<Milliampere> {
    override fun asType(d: Double) = Milliampere(d)
    override fun asBaseUnit() = Ampere(this.value * 0.001)
}

@JvmInline
value class Microampere(override val value: Double) : UnitCurrent<Microampere> {
    override fun asType(d: Double) = Microampere(d)
    override fun asBaseUnit() = Ampere(this.value * 1E-6)
}

@JvmInline
value class Kiloampere(override val value: Double) : UnitCurrent<Kiloampere> {
    override fun asType(d: Double) = Kiloampere(d)
    override fun asBaseUnit() = Ampere(this.value * 1000.0)
}

@JvmInline
value class Megaampere(override val value: Double) : UnitCurrent<Megaampere> {
    override fun asType(d: Double) = Megaampere(d)
    override fun asBaseUnit() = Ampere(this.value * 1E6)
}

// Conversion functions using toUnit
fun UnitCurrent<*>.toAmpere() = this.asBaseUnit()
fun UnitCurrent<*>.toMilliampere() = toUnit(Milliampere(1.0))
fun UnitCurrent<*>.toMicroampere() = toUnit(Microampere(1.0))
fun UnitCurrent<*>.toKiloampere() = toUnit(Kiloampere(1.0))
fun UnitCurrent<*>.toMegaampere() = toUnit(Megaampere(1.0))
