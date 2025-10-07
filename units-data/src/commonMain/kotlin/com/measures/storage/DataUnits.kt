package com.measures.storage

import kotlin.jvm.JvmInline

// Data Storage Units
// These represent fundamental data storage units used in computing

// Data unit conversion constants
object DataConsts {
    const val BITS_PER_NIBBLE = 4.0
    const val BITS_PER_BYTE = 8.0
    const val BITS_PER_WORD = 16.0
    const val BITS_PER_DWORD = 32.0
    const val BITS_PER_QWORD = 64.0
    const val BITS_PER_OWORD = 128.0
    const val BITS_PER_YWORD = 256.0
    const val BITS_PER_ZWORD = 512.0
    
    // Alternative names
    const val BITS_PER_NYBBLE = BITS_PER_NIBBLE
    const val BITS_PER_HALF_WORD = BITS_PER_WORD / 2.0
    const val BITS_PER_DOUBLE_WORD = BITS_PER_DWORD
    const val BITS_PER_QUAD_WORD = BITS_PER_QWORD
    const val BITS_PER_OCT_WORD = BITS_PER_OWORD
}

// Data storage unit conversion functions
fun UnitStorage<*>.toNibble() = toUnit(Nibble(1.0))
fun UnitStorage<*>.toNybble() = toUnit(Nybble(1.0))
fun UnitStorage<*>.toWord() = toUnit(Word(1.0))
fun UnitStorage<*>.toDoubleWord() = toUnit(DoubleWord(1.0))
fun UnitStorage<*>.toQuadWord() = toUnit(QuadWord(1.0))
fun UnitStorage<*>.toOctWord() = toUnit(OctWord(1.0))
fun UnitStorage<*>.toYWord() = toUnit(YWord(1.0))
fun UnitStorage<*>.toZWord() = toUnit(ZWord(1.0))

// Alternative naming
fun UnitStorage<*>.toDWord() = toUnit(DWord(1.0))
fun UnitStorage<*>.toQWord() = toUnit(QWord(1.0))
fun UnitStorage<*>.toOWord() = toUnit(OWord(1.0))
fun UnitStorage<*>.toHalfWord() = toUnit(HalfWord(1.0))

@JvmInline
value class Nibble(override val value: Double) : UnitStorage<Nibble> {
    override fun asType(d: Double) = Nibble(d)
    override fun asBaseUnit() = Byte(value * DataConsts.BITS_PER_NIBBLE / DataConsts.BITS_PER_BYTE)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Nybble(override val value: Double) : UnitStorage<Nybble> {
    override fun asType(d: Double) = Nybble(d)
    override fun asBaseUnit() = Byte(value * DataConsts.BITS_PER_NYBBLE / DataConsts.BITS_PER_BYTE)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class HalfWord(override val value: Double) : UnitStorage<HalfWord> {
    override fun asType(d: Double) = HalfWord(d)
    override fun asBaseUnit() = Byte(value * DataConsts.BITS_PER_HALF_WORD / DataConsts.BITS_PER_BYTE)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Word(override val value: Double) : UnitStorage<Word> {
    override fun asType(d: Double) = Word(d)
    override fun asBaseUnit() = Byte(value * DataConsts.BITS_PER_WORD / DataConsts.BITS_PER_BYTE)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class DoubleWord(override val value: Double) : UnitStorage<DoubleWord> {
    override fun asType(d: Double) = DoubleWord(d)
    override fun asBaseUnit() = Byte(value * DataConsts.BITS_PER_DWORD / DataConsts.BITS_PER_BYTE)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class DWord(override val value: Double) : UnitStorage<DWord> {
    override fun asType(d: Double) = DWord(d)
    override fun asBaseUnit() = Byte(value * DataConsts.BITS_PER_DWORD / DataConsts.BITS_PER_BYTE)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class QuadWord(override val value: Double) : UnitStorage<QuadWord> {
    override fun asType(d: Double) = QuadWord(d)
    override fun asBaseUnit() = Byte(value * DataConsts.BITS_PER_QWORD / DataConsts.BITS_PER_BYTE)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class QWord(override val value: Double) : UnitStorage<QWord> {
    override fun asType(d: Double) = QWord(d)
    override fun asBaseUnit() = Byte(value * DataConsts.BITS_PER_QWORD / DataConsts.BITS_PER_BYTE)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class OctWord(override val value: Double) : UnitStorage<OctWord> {
    override fun asType(d: Double) = OctWord(d)
    override fun asBaseUnit() = Byte(value * DataConsts.BITS_PER_OWORD / DataConsts.BITS_PER_BYTE)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class OWord(override val value: Double) : UnitStorage<OWord> {
    override fun asType(d: Double) = OWord(d)
    override fun asBaseUnit() = Byte(value * DataConsts.BITS_PER_OWORD / DataConsts.BITS_PER_BYTE)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class YWord(override val value: Double) : UnitStorage<YWord> {
    override fun asType(d: Double) = YWord(d)
    override fun asBaseUnit() = Byte(value * DataConsts.BITS_PER_YWORD / DataConsts.BITS_PER_BYTE)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class ZWord(override val value: Double) : UnitStorage<ZWord> {
    override fun asType(d: Double) = ZWord(d)
    override fun asBaseUnit() = Byte(value * DataConsts.BITS_PER_ZWORD / DataConsts.BITS_PER_BYTE)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}
