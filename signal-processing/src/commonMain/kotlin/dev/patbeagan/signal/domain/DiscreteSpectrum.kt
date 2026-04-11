package dev.patbeagan.signal.domain

import com.measures.frequency.Hertz

/**
 * Complex spectrum from a DFT/FFT: one coefficient per frequency bin.
 * Coefficient index [k] corresponds to normalized angular frequency 2πk/N for transform length N.
 */
data class DiscreteSpectrum(
    val coefficients: Array<ComplexSample>,
    val schedule: SamplingSchedule
) {
    init {
        require(coefficients.isNotEmpty()) { "coefficients must be non-empty" }
    }

    val transformLength: Int get() = coefficients.size

    operator fun get(bin: FrequencyBinIndex): ComplexSample = coefficients[bin.value]

    fun magnitudeAt(bin: FrequencyBinIndex): Double = this[bin].magnitude

    fun binCenterFrequency(bin: FrequencyBinIndex): Hertz =
        schedule.binCenterFrequency(bin, transformLength)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false
        other as DiscreteSpectrum
        return coefficients.contentEquals(other.coefficients) && schedule == other.schedule
    }

    override fun hashCode(): Int {
        var result = coefficients.contentHashCode()
        result = 31 * result + schedule.hashCode()
        return result
    }
}
