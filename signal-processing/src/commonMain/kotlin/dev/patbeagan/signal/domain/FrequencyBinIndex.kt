package dev.patbeagan.signal.domain

import kotlin.jvm.JvmInline

/**
 * Index of a frequency bin produced by a DFT/FFT of length [transformSize].
 * Valid range is `0 ..< transformSize`.
 */
@JvmInline
value class FrequencyBinIndex(val value: Int) {
    init {
        require(value >= 0) { "FrequencyBinIndex must be non-negative, was $value" }
    }
}
