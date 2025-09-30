package com.measures.storage

import com.measures.Consts
import kotlin.jvm.JvmInline

// Bit-based Storage Units
// These represent data in bits rather than bytes (1 byte = 8 bits)

// Bit conversion constants
object BitConsts {
    const val BITS_PER_BYTE = 8.0
    const val KILOBIT = Consts.KILO
    const val MEGABIT = Consts.MEGA
    const val GIGABIT = Consts.GIGA
    const val TERABIT = Consts.TERA
    const val PETABIT = Consts.PETA
    const val EXABIT = Consts.EXA
    const val ZETTABIT = Consts.ZETTA
    const val YOTTABIT = Consts.YOTTA
}

// Bit storage unit conversion functions
fun UnitStorage<*>.toBit() = toUnit(Bit(1.0))
fun UnitStorage<*>.toKilobit() = toUnit(Kilobit(1.0))
fun UnitStorage<*>.toMegabit() = toUnit(Megabit(1.0))
fun UnitStorage<*>.toGigabit() = toUnit(Gigabit(1.0))
fun UnitStorage<*>.toTerabit() = toUnit(Terabit(1.0))
fun UnitStorage<*>.toPetabit() = toUnit(Petabit(1.0))
fun UnitStorage<*>.toExabit() = toUnit(Exabit(1.0))
fun UnitStorage<*>.toZettabit() = toUnit(Zettabit(1.0))
fun UnitStorage<*>.toYottabit() = toUnit(Yottabit(1.0))

// Binary bit units
fun UnitStorage<*>.toKibibit() = toUnit(Kibibit(1.0))
fun UnitStorage<*>.toMebibit() = toUnit(Mebibit(1.0))
fun UnitStorage<*>.toGibibit() = toUnit(Gibibit(1.0))
fun UnitStorage<*>.toTebibit() = toUnit(Tebibit(1.0))
fun UnitStorage<*>.toPebibit() = toUnit(Pebibit(1.0))
fun UnitStorage<*>.toExbibit() = toUnit(Exbibit(1.0))
fun UnitStorage<*>.toZebibit() = toUnit(Zebibit(1.0))
fun UnitStorage<*>.toYobibit() = toUnit(Yobibit(1.0))

@JvmInline
value class Bit(override val value: Double) : UnitStorage<Bit> {
    override fun asType(d: Double) = Bit(d)
    override fun asBaseUnit() = Byte(value / BitConsts.BITS_PER_BYTE)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Kilobit(override val value: Double) : UnitStorage<Kilobit> {
    override fun asType(d: Double) = Kilobit(d)
    override fun asBaseUnit() = Byte(value * BitConsts.KILOBIT / BitConsts.BITS_PER_BYTE)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Megabit(override val value: Double) : UnitStorage<Megabit> {
    override fun asType(d: Double) = Megabit(d)
    override fun asBaseUnit() = Byte(value * BitConsts.MEGABIT / BitConsts.BITS_PER_BYTE)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Gigabit(override val value: Double) : UnitStorage<Gigabit> {
    override fun asType(d: Double) = Gigabit(d)
    override fun asBaseUnit() = Byte(value * BitConsts.GIGABIT / BitConsts.BITS_PER_BYTE)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Terabit(override val value: Double) : UnitStorage<Terabit> {
    override fun asType(d: Double) = Terabit(d)
    override fun asBaseUnit() = Byte(value * BitConsts.TERABIT / BitConsts.BITS_PER_BYTE)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Petabit(override val value: Double) : UnitStorage<Petabit> {
    override fun asType(d: Double) = Petabit(d)
    override fun asBaseUnit() = Byte(value * BitConsts.PETABIT / BitConsts.BITS_PER_BYTE)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Exabit(override val value: Double) : UnitStorage<Exabit> {
    override fun asType(d: Double) = Exabit(d)
    override fun asBaseUnit() = Byte(value * BitConsts.EXABIT / BitConsts.BITS_PER_BYTE)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Zettabit(override val value: Double) : UnitStorage<Zettabit> {
    override fun asType(d: Double) = Zettabit(d)
    override fun asBaseUnit() = Byte(value * BitConsts.ZETTABIT / BitConsts.BITS_PER_BYTE)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Yottabit(override val value: Double) : UnitStorage<Yottabit> {
    override fun asType(d: Double) = Yottabit(d)
    override fun asBaseUnit() = Byte(value * BitConsts.YOTTABIT / BitConsts.BITS_PER_BYTE)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

// Binary bit units (1024-based)
@JvmInline
value class Kibibit(override val value: Double) : UnitStorage<Kibibit> {
    override fun asType(d: Double) = Kibibit(d)
    override fun asBaseUnit() = Byte(value * 1024.0 / BitConsts.BITS_PER_BYTE)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Mebibit(override val value: Double) : UnitStorage<Mebibit> {
    override fun asType(d: Double) = Mebibit(d)
    override fun asBaseUnit() = Byte(value * 1024.0 * 1024.0 / BitConsts.BITS_PER_BYTE)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Gibibit(override val value: Double) : UnitStorage<Gibibit> {
    override fun asType(d: Double) = Gibibit(d)
    override fun asBaseUnit() = Byte(value * 1024.0 * 1024.0 * 1024.0 / BitConsts.BITS_PER_BYTE)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Tebibit(override val value: Double) : UnitStorage<Tebibit> {
    override fun asType(d: Double) = Tebibit(d)
    override fun asBaseUnit() = Byte(value * 1024.0 * 1024.0 * 1024.0 * 1024.0 / BitConsts.BITS_PER_BYTE)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Pebibit(override val value: Double) : UnitStorage<Pebibit> {
    override fun asType(d: Double) = Pebibit(d)
    override fun asBaseUnit() = Byte(value * 1024.0 * 1024.0 * 1024.0 * 1024.0 * 1024.0 / BitConsts.BITS_PER_BYTE)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Exbibit(override val value: Double) : UnitStorage<Exbibit> {
    override fun asType(d: Double) = Exbibit(d)
    override fun asBaseUnit() = Byte(value * 1024.0 * 1024.0 * 1024.0 * 1024.0 * 1024.0 * 1024.0 / BitConsts.BITS_PER_BYTE)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Zebibit(override val value: Double) : UnitStorage<Zebibit> {
    override fun asType(d: Double) = Zebibit(d)
    override fun asBaseUnit() = Byte(value * 1024.0 * 1024.0 * 1024.0 * 1024.0 * 1024.0 * 1024.0 * 1024.0 / BitConsts.BITS_PER_BYTE)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Yobibit(override val value: Double) : UnitStorage<Yobibit> {
    override fun asType(d: Double) = Yobibit(d)
    override fun asBaseUnit() = Byte(value * 1024.0 * 1024.0 * 1024.0 * 1024.0 * 1024.0 * 1024.0 * 1024.0 * 1024.0 / BitConsts.BITS_PER_BYTE)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}
