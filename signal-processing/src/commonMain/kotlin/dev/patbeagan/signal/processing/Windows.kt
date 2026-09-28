package dev.patbeagan.signal.processing

import dev.patbeagan.signal.domain.DiscreteSignal
import dev.patbeagan.signal.domain.SampleCount
import dev.patbeagan.signal.domain.WindowWeights
import kotlin.math.PI
import kotlin.math.cos

/**
 * Finite window functions for spectral analysis and tapering.
 */
object Windows {
    fun rectangular(length: SampleCount): WindowWeights =
        WindowWeights(DoubleArray(length.value) { 1.0 })

    fun hann(length: SampleCount): WindowWeights {
        val n = length.value
        return WindowWeights(
            DoubleArray(n) { i ->
                if (n == 1) {
                    1.0
                } else {
                    0.5 * (1.0 - cos(2.0 * PI * i / (n - 1)))
                }
            }
        )
    }

    fun hamming(length: SampleCount): WindowWeights {
        val n = length.value
        return WindowWeights(
            DoubleArray(n) { i ->
                if (n == 1) {
                    1.0
                } else {
                    0.54 - 0.46 * cos(2.0 * PI * i / (n - 1))
                }
            }
        )
    }

    /** Element-wise product; requires matching [SampleCount]. */
    fun apply(signal: DiscreteSignal, window: WindowWeights): DiscreteSignal {
        require(signal.sampleCount == window.length) {
            "window length (${window.length.value}) must match signal length (${signal.sampleCount.value})"
        }
        val w = window.values
        val out = DoubleArray(w.size) { i -> signal.samples[i] * w[i] }
        return DiscreteSignal(out, signal.schedule)
    }
}
