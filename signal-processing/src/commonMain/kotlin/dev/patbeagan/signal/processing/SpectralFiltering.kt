package dev.patbeagan.signal.processing

import dev.patbeagan.signal.domain.ComplexSample
import dev.patbeagan.signal.domain.DiscreteSignal
import dev.patbeagan.signal.domain.DiscreteSpectrum
import dev.patbeagan.signal.domain.FrequencyBinIndex

/**
 * Ideal brick-wall filters in the DFT domain (zeroing bins). Preserves conjugate symmetry so that
 * the inverse DFT is (approximately) real for real inputs.
 */
object SpectralFiltering {
    /**
     * Low-pass: keeps bins with index `i <= maxPassBin` or `i >= N - maxPassBin`.
     * Requires `2 * maxPassBin < N` so at least one bin is attenuated when `N > 1`.
     */
    fun idealLowPass(signal: DiscreteSignal, maxPassBin: FrequencyBinIndex): DiscreteSignal {
        val n = signal.samples.size
        val k = maxPassBin.value
        require(2 * k < n) {
            "maxPassBin ($k) must satisfy 2*K < N ($n)"
        }
        val spec = FourierTransform.discreteFourierTransform(signal)
        val masked = maskLowPass(spec, k)
        return FourierTransform.inverseDiscreteFourierTransform(masked)
    }

    /**
     * High-pass: zeros bins with `i <= minStopBin` or `i >= N - minStopBin` (including DC when `minStopBin >= 0`).
     * Requires `2 * minStopBin < N`.
     */
    fun idealHighPass(signal: DiscreteSignal, minStopBin: FrequencyBinIndex): DiscreteSignal {
        val n = signal.samples.size
        val k = minStopBin.value
        require(2 * k < n) {
            "minStopBin ($k) must satisfy 2*K < N ($n)"
        }
        val spec = FourierTransform.discreteFourierTransform(signal)
        val masked = maskHighPass(spec, k)
        return FourierTransform.inverseDiscreteFourierTransform(masked)
    }

    private fun maskLowPass(spec: DiscreteSpectrum, k: Int): DiscreteSpectrum {
        val n = spec.transformLength.value
        val coeffs = Array(n) { i ->
            if (i <= k || i >= n - k) spec.coefficients[i]
            else ComplexSample.ZERO
        }
        return DiscreteSpectrum(coeffs, spec.schedule)
    }

    private fun maskHighPass(spec: DiscreteSpectrum, k: Int): DiscreteSpectrum {
        val n = spec.transformLength.value
        val coeffs = Array(n) { i ->
            if (i <= k || i >= n - k) ComplexSample.ZERO
            else spec.coefficients[i]
        }
        return DiscreteSpectrum(coeffs, spec.schedule)
    }
}
