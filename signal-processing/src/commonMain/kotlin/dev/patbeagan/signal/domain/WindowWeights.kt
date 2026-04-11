package dev.patbeagan.signal.domain

import kotlin.jvm.JvmInline

/**
 * Per-sample multiplicative weights for tapering or gating (e.g. Hann, Hamming).
 */
@JvmInline
value class WindowWeights(val values: DoubleArray) {
    init {
        require(values.isNotEmpty()) { "window weights must be non-empty" }
    }

    val length: SampleCount
        get() = SampleCount(values.size)
}
