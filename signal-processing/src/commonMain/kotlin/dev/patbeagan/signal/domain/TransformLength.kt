package dev.patbeagan.signal.domain

import kotlin.jvm.JvmInline

/**
 * Length N of a DFT/FFT (number of frequency bins equals N).
 */
@JvmInline
value class TransformLength(val value: Int) {
    init {
        require(value > 0) { "TransformLength must be positive, was $value" }
    }

    val isPowerOfTwo: Boolean
        get() = value > 0 && (value and (value - 1)) == 0
}
