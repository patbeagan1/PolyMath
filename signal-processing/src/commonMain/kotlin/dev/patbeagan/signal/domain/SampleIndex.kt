package dev.patbeagan.signal.domain

import kotlin.jvm.JvmInline

/**
 * Zero-based index into a discrete-time sequence.
 */
@JvmInline
value class SampleIndex(val value: Int) {
    init {
        require(value >= 0) { "SampleIndex must be non-negative, was $value" }
    }

    operator fun plus(offset: Int): SampleIndex = SampleIndex(value + offset)
    operator fun inc(): SampleIndex = SampleIndex(value + 1)
}
