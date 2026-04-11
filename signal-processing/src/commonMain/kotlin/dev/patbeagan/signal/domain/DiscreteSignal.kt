package dev.patbeagan.signal.domain

import com.measures.frequency.Hertz

/**
 * A uniformly sampled real-valued signal with an explicit [SamplingSchedule].
 */
data class DiscreteSignal(
    val samples: DoubleArray,
    val schedule: SamplingSchedule
) {
    init {
        require(samples.isNotEmpty()) { "samples must be non-empty" }
    }

    val sampleCount: SampleCount
        get() = SampleCount(samples.size)

    operator fun get(index: SampleIndex): Double = samples[index.value]

    fun copySamples(transform: (DoubleArray) -> DoubleArray): DiscreteSignal =
        DiscreteSignal(transform(samples.copyOf()), schedule)

    fun mapSamples(transform: (Double) -> Double): DiscreteSignal =
        copySamples { a -> DoubleArray(a.size) { i -> transform(a[i]) } }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false
        other as DiscreteSignal
        return samples.contentEquals(other.samples) && schedule == other.schedule
    }

    override fun hashCode(): Int {
        var result = samples.contentHashCode()
        result = 31 * result + schedule.hashCode()
        return result
    }

    companion object {
        fun of(samples: DoubleArray, sampleRateHz: Double): DiscreteSignal =
            DiscreteSignal(samples, SamplingSchedule(Hertz(sampleRateHz)))
    }
}
