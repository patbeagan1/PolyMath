package com.measures.storage

import com.measures.Consts
import kotlin.jvm.JvmInline

// Binary Storage Units (1024-based)
// These use binary prefixes where 1 KiB = 1024 bytes, 1 MiB = 1024 KiB, etc.

// Binary conversion constants
object BinaryConsts {
    const val KIBIBYTE = 1024.0
    const val MEBIBYTE = KIBIBYTE * KIBIBYTE
    const val GIBIBYTE = MEBIBYTE * KIBIBYTE
    const val TEBIBYTE = GIBIBYTE * KIBIBYTE
    const val PEBIBYTE = TEBIBYTE * KIBIBYTE
    const val EXBIBYTE = PEBIBYTE * KIBIBYTE
    const val ZEBIBYTE = EXBIBYTE * KIBIBYTE
    const val YOBIBYTE = ZEBIBYTE * KIBIBYTE
}

// Binary storage unit conversion functions
fun UnitStorage<*>.toKibibyte() = toUnit(Kibibyte(1.0))
fun UnitStorage<*>.toMebibyte() = toUnit(Mebibyte(1.0))
fun UnitStorage<*>.toGibibyte() = toUnit(Gibibyte(1.0))
fun UnitStorage<*>.toTebibyte() = toUnit(Tebibyte(1.0))
fun UnitStorage<*>.toPebibyte() = toUnit(Pebibyte(1.0))
fun UnitStorage<*>.toExbibyte() = toUnit(Exbibyte(1.0))
fun UnitStorage<*>.toZebibyte() = toUnit(Zebibyte(1.0))
fun UnitStorage<*>.toYobibyte() = toUnit(Yobibyte(1.0))

@JvmInline
value class Kibibyte(override val value: Double) : UnitStorage<Kibibyte> {
    override fun asType(d: Double) = Kibibyte(d)
    override fun asBaseUnit() = Byte(value * BinaryConsts.KIBIBYTE)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Mebibyte(override val value: Double) : UnitStorage<Mebibyte> {
    override fun asType(d: Double) = Mebibyte(d)
    override fun asBaseUnit() = Byte(value * BinaryConsts.MEBIBYTE)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Gibibyte(override val value: Double) : UnitStorage<Gibibyte> {
    override fun asType(d: Double) = Gibibyte(d)
    override fun asBaseUnit() = Byte(value * BinaryConsts.GIBIBYTE)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Tebibyte(override val value: Double) : UnitStorage<Tebibyte> {
    override fun asType(d: Double) = Tebibyte(d)
    override fun asBaseUnit() = Byte(value * BinaryConsts.TEBIBYTE)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Pebibyte(override val value: Double) : UnitStorage<Pebibyte> {
    override fun asType(d: Double) = Pebibyte(d)
    override fun asBaseUnit() = Byte(value * BinaryConsts.PEBIBYTE)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Exbibyte(override val value: Double) : UnitStorage<Exbibyte> {
    override fun asType(d: Double) = Exbibyte(d)
    override fun asBaseUnit() = Byte(value * BinaryConsts.EXBIBYTE)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Zebibyte(override val value: Double) : UnitStorage<Zebibyte> {
    override fun asType(d: Double) = Zebibyte(d)
    override fun asBaseUnit() = Byte(value * BinaryConsts.ZEBIBYTE)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Yobibyte(override val value: Double) : UnitStorage<Yobibyte> {
    override fun asType(d: Double) = Yobibyte(d)
    override fun asBaseUnit() = Byte(value * BinaryConsts.YOBIBYTE)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}
