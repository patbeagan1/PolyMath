package com.measures.storage

import kotlin.jvm.JvmInline

// Specialized Storage Units
// These represent specialized data storage units used in specific computing contexts

// Specialized unit conversion constants
object SpecializedConsts {
    // Character encoding units
    const val BITS_PER_ASCII_CHAR = 7.0
    const val BITS_PER_UTF8_CHAR = 8.0
    const val BITS_PER_UTF16_CHAR = 16.0
    const val BITS_PER_UTF32_CHAR = 32.0
    
    // Floating point units
    const val BITS_PER_FLOAT = 32.0
    const val BITS_PER_DOUBLE = 64.0
    const val BITS_PER_LONG_DOUBLE = 128.0
    
    // Integer units
    const val BITS_PER_SHORT = 16.0
    const val BITS_PER_INT = 32.0
    const val BITS_PER_LONG = 64.0
    const val BITS_PER_LONG_LONG = 128.0
    
    // Boolean units
    const val BITS_PER_BOOLEAN = 1.0
    const val BITS_PER_BYTE_BOOLEAN = 8.0
    
    // Color units
    const val BITS_PER_RGB_PIXEL = 24.0
    const val BITS_PER_RGBA_PIXEL = 32.0
    const val BITS_PER_ARGB_PIXEL = 32.0
    
    // Audio units
    const val BITS_PER_AUDIO_SAMPLE_8 = 8.0
    const val BITS_PER_AUDIO_SAMPLE_16 = 16.0
    const val BITS_PER_AUDIO_SAMPLE_24 = 24.0
    const val BITS_PER_AUDIO_SAMPLE_32 = 32.0
    
    // Video units
    const val BITS_PER_VIDEO_FRAME_720P = 1280 * 720 * 24.0
    const val BITS_PER_VIDEO_FRAME_1080P = 1920 * 1080 * 24.0
    const val BITS_PER_VIDEO_FRAME_4K = 3840 * 2160 * 24.0
}

// Specialized storage unit conversion functions
fun UnitStorage<*>.toAsciiChar() = toUnit(AsciiChar(1.0))
fun UnitStorage<*>.toUtf8Char() = toUnit(Utf8Char(1.0))
fun UnitStorage<*>.toUtf16Char() = toUnit(Utf16Char(1.0))
fun UnitStorage<*>.toUtf32Char() = toUnit(Utf32Char(1.0))
fun UnitStorage<*>.toFloat32() = toUnit(Float32(1.0))
fun UnitStorage<*>.toFloat64() = toUnit(Float64(1.0))
fun UnitStorage<*>.toFloat128() = toUnit(Float128(1.0))
fun UnitStorage<*>.toInt16() = toUnit(Int16(1.0))
fun UnitStorage<*>.toInt32() = toUnit(Int32(1.0))
fun UnitStorage<*>.toInt64() = toUnit(Int64(1.0))
fun UnitStorage<*>.toInt128() = toUnit(Int128(1.0))
fun UnitStorage<*>.toBool() = toUnit(Bool(1.0))
fun UnitStorage<*>.toByteBoolean() = toUnit(ByteBoolean(1.0))
fun UnitStorage<*>.toRgbPixel() = toUnit(RgbPixel(1.0))
fun UnitStorage<*>.toRgbaPixel() = toUnit(RgbaPixel(1.0))
fun UnitStorage<*>.toArgbPixel() = toUnit(ArgbPixel(1.0))
fun UnitStorage<*>.toAudioSample8() = toUnit(AudioSample8(1.0))
fun UnitStorage<*>.toAudioSample16() = toUnit(AudioSample16(1.0))
fun UnitStorage<*>.toAudioSample24() = toUnit(AudioSample24(1.0))
fun UnitStorage<*>.toAudioSample32() = toUnit(AudioSample32(1.0))
fun UnitStorage<*>.toVideoFrame720P() = toUnit(VideoFrame720P(1.0))
fun UnitStorage<*>.toVideoFrame1080P() = toUnit(VideoFrame1080P(1.0))
fun UnitStorage<*>.toVideoFrame4K() = toUnit(VideoFrame4K(1.0))

@JvmInline
value class AsciiChar(override val value: Double) : UnitStorage<AsciiChar> {
    override fun asType(d: Double) = AsciiChar(d)
    override fun asBaseUnit() = Byte(value * SpecializedConsts.BITS_PER_ASCII_CHAR / 8.0)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Utf8Char(override val value: Double) : UnitStorage<Utf8Char> {
    override fun asType(d: Double) = Utf8Char(d)
    override fun asBaseUnit() = Byte(value * SpecializedConsts.BITS_PER_UTF8_CHAR / 8.0)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Utf16Char(override val value: Double) : UnitStorage<Utf16Char> {
    override fun asType(d: Double) = Utf16Char(d)
    override fun asBaseUnit() = Byte(value * SpecializedConsts.BITS_PER_UTF16_CHAR / 8.0)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Utf32Char(override val value: Double) : UnitStorage<Utf32Char> {
    override fun asType(d: Double) = Utf32Char(d)
    override fun asBaseUnit() = Byte(value * SpecializedConsts.BITS_PER_UTF32_CHAR / 8.0)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Float32(override val value: Double) : UnitStorage<Float32> {
    override fun asType(d: Double) = Float32(d)
    override fun asBaseUnit() = Byte(value * SpecializedConsts.BITS_PER_FLOAT / 8.0)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Float64(override val value: Double) : UnitStorage<Float64> {
    override fun asType(d: Double) = Float64(d)
    override fun asBaseUnit() = Byte(value * SpecializedConsts.BITS_PER_DOUBLE / 8.0)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Float128(override val value: Double) : UnitStorage<Float128> {
    override fun asType(d: Double) = Float128(d)
    override fun asBaseUnit() = Byte(value * SpecializedConsts.BITS_PER_LONG_DOUBLE / 8.0)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Int16(override val value: Double) : UnitStorage<Int16> {
    override fun asType(d: Double) = Int16(d)
    override fun asBaseUnit() = Byte(value * SpecializedConsts.BITS_PER_SHORT / 8.0)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Int32(override val value: Double) : UnitStorage<Int32> {
    override fun asType(d: Double) = Int32(d)
    override fun asBaseUnit() = Byte(value * SpecializedConsts.BITS_PER_INT / 8.0)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Int64(override val value: Double) : UnitStorage<Int64> {
    override fun asType(d: Double) = Int64(d)
    override fun asBaseUnit() = Byte(value * SpecializedConsts.BITS_PER_LONG / 8.0)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Int128(override val value: Double) : UnitStorage<Int128> {
    override fun asType(d: Double) = Int128(d)
    override fun asBaseUnit() = Byte(value * SpecializedConsts.BITS_PER_LONG_LONG / 8.0)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class Bool(override val value: Double) : UnitStorage<Bool> {
    override fun asType(d: Double) = Bool(d)
    override fun asBaseUnit() = Byte(value * SpecializedConsts.BITS_PER_BOOLEAN / 8.0)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class ByteBoolean(override val value: Double) : UnitStorage<ByteBoolean> {
    override fun asType(d: Double) = ByteBoolean(d)
    override fun asBaseUnit() = Byte(value * SpecializedConsts.BITS_PER_BYTE_BOOLEAN / 8.0)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class RgbPixel(override val value: Double) : UnitStorage<RgbPixel> {
    override fun asType(d: Double) = RgbPixel(d)
    override fun asBaseUnit() = Byte(value * SpecializedConsts.BITS_PER_RGB_PIXEL / 8.0)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class RgbaPixel(override val value: Double) : UnitStorage<RgbaPixel> {
    override fun asType(d: Double) = RgbaPixel(d)
    override fun asBaseUnit() = Byte(value * SpecializedConsts.BITS_PER_RGBA_PIXEL / 8.0)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class ArgbPixel(override val value: Double) : UnitStorage<ArgbPixel> {
    override fun asType(d: Double) = ArgbPixel(d)
    override fun asBaseUnit() = Byte(value * SpecializedConsts.BITS_PER_ARGB_PIXEL / 8.0)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class AudioSample8(override val value: Double) : UnitStorage<AudioSample8> {
    override fun asType(d: Double) = AudioSample8(d)
    override fun asBaseUnit() = Byte(value * SpecializedConsts.BITS_PER_AUDIO_SAMPLE_8 / 8.0)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class AudioSample16(override val value: Double) : UnitStorage<AudioSample16> {
    override fun asType(d: Double) = AudioSample16(d)
    override fun asBaseUnit() = Byte(value * SpecializedConsts.BITS_PER_AUDIO_SAMPLE_16 / 8.0)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class AudioSample24(override val value: Double) : UnitStorage<AudioSample24> {
    override fun asType(d: Double) = AudioSample24(d)
    override fun asBaseUnit() = Byte(value * SpecializedConsts.BITS_PER_AUDIO_SAMPLE_24 / 8.0)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class AudioSample32(override val value: Double) : UnitStorage<AudioSample32> {
    override fun asType(d: Double) = AudioSample32(d)
    override fun asBaseUnit() = Byte(value * SpecializedConsts.BITS_PER_AUDIO_SAMPLE_32 / 8.0)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class VideoFrame720P(override val value: Double) : UnitStorage<VideoFrame720P> {
    override fun asType(d: Double) = VideoFrame720P(d)
    override fun asBaseUnit() = Byte(value * SpecializedConsts.BITS_PER_VIDEO_FRAME_720P / 8.0)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class VideoFrame1080P(override val value: Double) : UnitStorage<VideoFrame1080P> {
    override fun asType(d: Double) = VideoFrame1080P(d)
    override fun asBaseUnit() = Byte(value * SpecializedConsts.BITS_PER_VIDEO_FRAME_1080P / 8.0)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}

@JvmInline
value class VideoFrame4K(override val value: Double) : UnitStorage<VideoFrame4K> {
    override fun asType(d: Double) = VideoFrame4K(d)
    override fun asBaseUnit() = Byte(value * SpecializedConsts.BITS_PER_VIDEO_FRAME_4K / 8.0)

    override operator fun plus(other: UnitStorage<*>) = (this as UnitStorage<*>).plusUnit(other)
    override operator fun minus(other: UnitStorage<*>) = (this as UnitStorage<*>).minusUnit(other)
}
