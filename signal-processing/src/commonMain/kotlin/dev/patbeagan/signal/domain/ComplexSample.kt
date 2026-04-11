package dev.patbeagan.signal.domain

import kotlin.math.hypot

/**
 * One complex-valued sample (e.g. a DFT coefficient).
 */
data class ComplexSample(
    val real: Double,
    val imaginary: Double
) {
    val magnitude: Double
        get() = hypot(real, imaginary)

    val phase: Double
        get() = kotlin.math.atan2(imaginary, real)

    operator fun plus(other: ComplexSample) =
        ComplexSample(real + other.real, imaginary + other.imaginary)

    operator fun minus(other: ComplexSample) =
        ComplexSample(real - other.real, imaginary - other.imaginary)

    operator fun times(other: ComplexSample) =
        ComplexSample(
            real * other.real - imaginary * other.imaginary,
            real * other.imaginary + imaginary * other.real
        )

    operator fun times(scalar: Double) =
        ComplexSample(real * scalar, imaginary * scalar)

    companion object {
        val ZERO = ComplexSample(0.0, 0.0)
    }
}
