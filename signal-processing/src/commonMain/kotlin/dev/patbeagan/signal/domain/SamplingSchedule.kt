package dev.patbeagan.signal.domain

import com.measures.frequency.Hertz
import com.measures.time.Second

/**
 * Describes how a discrete-time signal was (or will be) sampled: uniform spacing at [sampleRate].
 */
data class SamplingSchedule(
    val sampleRate: Hertz
) {
    init {
        require(sampleRate.value > 0.0) { "sample rate must be positive, was ${sampleRate.value}" }
    }

    /** Time between consecutive samples. */
    val samplePeriod: Second
        get() = sampleRate.inv()

    /** Duration spanned by [count] consecutive samples. */
    fun durationFor(count: SampleCount): Second =
        Second(samplePeriod.value * count.value)

    /** Sample index of the first sample with start time ≥ [time] (clamped to non-negative). */
    fun indexAtOrAfter(time: Second): SampleIndex {
        val idx = kotlin.math.ceil(time.value / samplePeriod.value).toInt()
        return SampleIndex(idx.coerceAtLeast(0))
    }

    /** Start time of the sample at [index]. */
    fun timeAt(index: SampleIndex): Second =
        Second(index.value * samplePeriod.value)

    /**
     * Center frequency (Hz) of DFT bin [bin] for a transform of length [transformSize].
     */
    fun binCenterFrequency(bin: FrequencyBinIndex, transformSize: Int): Hertz {
        require(transformSize > 0) { "transformSize must be positive" }
        require(bin.value < transformSize) {
            "bin ${bin.value} out of range for transform size $transformSize"
        }
        return Hertz(bin.value * sampleRate.value / transformSize)
    }

    companion object {
        fun fromPeriod(period: Second): SamplingSchedule {
            require(period.value > 0.0) { "period must be positive" }
            return SamplingSchedule(Hertz(1.0 / period.value))
        }
    }
}
