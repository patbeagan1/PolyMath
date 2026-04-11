package dev.patbeagan.signal.processing

import dev.patbeagan.signal.domain.DiscreteSignal
import dev.patbeagan.signal.domain.SampleCount
import kotlin.math.PI
import kotlin.math.cos

/**
 * Finite window functions for spectral analysis and tapering.
 */
object Windows {
    fun rectangular(length: SampleCount): DoubleArray =
        DoubleArray(length.value) { 1.0 }

    fun hann(length: SampleCount): DoubleArray {
        val n = length.value
        return DoubleArray(n) { i ->
            if (n == 1) {
                1.0
            } else {
                0.5 * (1.0 - cos(2.0 * PI * i / (n - 1)))
            }
        }
    }

    fun hamming(length: SampleCount): DoubleArray {
        val n = length.value
        return DoubleArray(n) { i ->
            if (n == 1) {
                1.0
            } else {
                0.54 - 0.46 * cos(2.0 * PI * i / (n - 1))
            }
        }
    }

    /** Element-wise product; requires equal lengths. */
    fun apply(signal: DiscreteSignal, window: DoubleArray): DiscreteSignal {
        require(signal.samples.size == window.size) {
            "window length (${window.size}) must match signal length (${signal.samples.size})"
        }
        val out = DoubleArray(window.size) { i -> signal.samples[i] * window[i] }
        return DiscreteSignal(out, signal.schedule)
    }
}
