package dev.patbeagan.signal.domain

import kotlin.jvm.JvmInline

/**
 * Number of samples in a discrete-time sequence (dimensionless count in the sampling domain).
 */
@JvmInline
value class SampleCount(val value: Int) {
    init {
        require(value > 0) { "SampleCount must be positive, was $value" }
    }

    operator fun plus(other: SampleCount): SampleCount = SampleCount(value + other.value)

    operator fun minus(other: SampleCount): SampleCount {
        val d = value - other.value
        require(d > 0) { "Resulting sample count must be positive" }
        return SampleCount(d)
    }

    operator fun compareTo(other: SampleCount): Int = value.compareTo(other.value)
}
