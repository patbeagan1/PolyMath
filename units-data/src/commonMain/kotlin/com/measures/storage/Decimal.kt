package com.measures.storage

import com.measures.Consts
import kotlin.jvm.JvmInline

// Decimal Storage Units (1000-based)
// These use decimal prefixes where 1 KB = 1000 bytes, 1 MB = 1000 KB, etc.

// Decimal storage unit conversion functions using SI prefixes
fun UnitStorage<*>.toKilobyte() = toUnit(Kilobyte(1.0))
fun UnitStorage<*>.toMegabyte() = toUnit(Megabyte(1.0))
fun UnitStorage<*>.toGigabyte() = toUnit(Gigabyte(1.0))
fun UnitStorage<*>.toTerabyte() = toUnit(Terabyte(1.0))
fun UnitStorage<*>.toPetabyte() = toUnit(Petabyte(1.0))
fun UnitStorage<*>.toExabyte() = toUnit(Exabyte(1.0))
fun UnitStorage<*>.toZettabyte() = toUnit(Zettabyte(1.0))
fun UnitStorage<*>.toYottabyte() = toUnit(Yottabyte(1.0))

// Smaller decimal units
fun UnitStorage<*>.toDecabyte() = toUnit(Decabyte(1.0))
fun UnitStorage<*>.toHectobyte() = toUnit(Hectobyte(1.0))

@JvmInline
value class Decabyte(override val value: Double) : UnitStorage<Decabyte> {
    override fun asType(d: Double) = Decabyte(d)
    override fun asBaseUnit() = Byte(value * Consts.DEKA)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Hectobyte(override val value: Double) : UnitStorage<Hectobyte> {
    override fun asType(d: Double) = Hectobyte(d)
    override fun asBaseUnit() = Byte(value * Consts.HECTO)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Kilobyte(override val value: Double) : UnitStorage<Kilobyte> {
    override fun asType(d: Double) = Kilobyte(d)
    override fun asBaseUnit() = Byte(value * Consts.KILO)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Megabyte(override val value: Double) : UnitStorage<Megabyte> {
    override fun asType(d: Double) = Megabyte(d)
    override fun asBaseUnit() = Byte(value * Consts.MEGA)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Gigabyte(override val value: Double) : UnitStorage<Gigabyte> {
    override fun asType(d: Double) = Gigabyte(d)
    override fun asBaseUnit() = Byte(value * Consts.GIGA)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Terabyte(override val value: Double) : UnitStorage<Terabyte> {
    override fun asType(d: Double) = Terabyte(d)
    override fun asBaseUnit() = Byte(value * Consts.TERA)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Petabyte(override val value: Double) : UnitStorage<Petabyte> {
    override fun asType(d: Double) = Petabyte(d)
    override fun asBaseUnit() = Byte(value * Consts.PETA)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Exabyte(override val value: Double) : UnitStorage<Exabyte> {
    override fun asType(d: Double) = Exabyte(d)
    override fun asBaseUnit() = Byte(value * Consts.EXA)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Zettabyte(override val value: Double) : UnitStorage<Zettabyte> {
    override fun asType(d: Double) = Zettabyte(d)
    override fun asBaseUnit() = Byte(value * Consts.ZETTA)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Yottabyte(override val value: Double) : UnitStorage<Yottabyte> {
    override fun asType(d: Double) = Yottabyte(d)
    override fun asBaseUnit() = Byte(value * Consts.YOTTA)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}
