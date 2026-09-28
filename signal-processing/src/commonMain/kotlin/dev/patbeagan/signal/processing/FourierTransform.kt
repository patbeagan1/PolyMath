package dev.patbeagan.signal.processing

import dev.patbeagan.signal.domain.ComplexSample
import dev.patbeagan.signal.domain.DiscreteSignal
import dev.patbeagan.signal.domain.DiscreteSpectrum
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/**
 * Discrete Fourier transform (direct DFT) and radix-2 Cooley–Tukey FFT for power-of-two lengths.
 */
object FourierTransform {
    fun discreteFourierTransform(signal: DiscreteSignal): DiscreteSpectrum {
        val n = signal.samples.size
        val re = DoubleArray(n)
        val im = DoubleArray(n)
        dftForward(signal.samples, re, im)
        val coeffs = Array(n) { i -> ComplexSample(re[i], im[i]) }
        return DiscreteSpectrum(coefficients = coeffs, schedule = signal.schedule)
    }

    fun fastFourierTransform(signal: DiscreteSignal): DiscreteSpectrum {
        val n = signal.samples.size
        require(n > 0 && (n and (n - 1)) == 0) {
            "FFT length must be a positive power of two, was $n"
        }
        val re = signal.samples.copyOf()
        val im = DoubleArray(n)
        fftRadix2InPlace(re, im)
        val coeffs = Array(n) { i -> ComplexSample(re[i], im[i]) }
        return DiscreteSpectrum(coefficients = coeffs, schedule = signal.schedule)
    }

    /**
     * Inverse DFT; for a spectrum produced from a real signal, the imaginary parts of time samples
     * should be negligible up to numerical error.
     */
    fun inverseDiscreteFourierTransform(spectrum: DiscreteSpectrum): DiscreteSignal {
        val n = spectrum.transformLength.value
        val re = DoubleArray(n) { spectrum.coefficients[it].real }
        val im = DoubleArray(n) { spectrum.coefficients[it].imaginary }
        val time = inverseDft(re, im)
        return DiscreteSignal(time, spectrum.schedule)
    }

    /** Right-pad with zeros to the next power of two (no-op if length already is one). */
    fun zeroPadToNextPowerOfTwo(signal: DiscreteSignal): DiscreteSignal {
        val n = signal.samples.size
        val target = nextPowerOfTwoAtLeast(n)
        if (target == n) return signal
        val padded = DoubleArray(target)
        signal.samples.copyInto(padded, endIndex = n)
        return DiscreteSignal(padded, signal.schedule)
    }

    private fun nextPowerOfTwoAtLeast(n: Int): Int {
        require(n > 0) { "signal must be non-empty" }
        var p = 1
        while (p < n) {
            p = p shl 1
        }
        return p
    }

    private fun dftForward(samples: DoubleArray, re: DoubleArray, im: DoubleArray) {
        val n = samples.size
        val twoPiOverN = 2 * PI / n
        for (k in 0 until n) {
            var accR = 0.0
            var accI = 0.0
            for (i in 0 until n) {
                val angle = twoPiOverN * k * i
                accR += samples[i] * cos(angle)
                accI -= samples[i] * sin(angle)
            }
            re[k] = accR
            im[k] = accI
        }
    }

    private fun inverseDft(coeffRe: DoubleArray, coeffIm: DoubleArray): DoubleArray {
        val n = coeffRe.size
        require(coeffIm.size == n)
        val x = DoubleArray(n)
        val twoPiOverN = 2 * PI / n
        for (t in 0 until n) {
            var sr = 0.0
            for (k in 0 until n) {
                val angle = twoPiOverN * k * t
                val c = cos(angle)
                val s = sin(angle)
                val xr = coeffRe[k]
                val xi = coeffIm[k]
                sr += xr * c - xi * s
            }
            x[t] = sr / n
        }
        return x
    }

    private fun fftRadix2InPlace(re: DoubleArray, im: DoubleArray) {
        val n = re.size
        var j = 0
        for (i in 1 until n) {
            var bit = n shr 1
            while (j >= bit) {
                j -= bit
                bit = bit shr 1
            }
            j += bit
            if (i < j) {
                val tr = re[i]
                re[i] = re[j]
                re[j] = tr
                val ti = im[i]
                im[i] = im[j]
                im[j] = ti
            }
        }
        var len = 2
        while (len <= n) {
            val ang = -2 * PI / len
            val wlenR = cos(ang)
            val wlenI = sin(ang)
            var i = 0
            while (i < n) {
                var wr = 1.0
                var wi = 0.0
                for (k in 0 until len / 2) {
                    val i1 = i + k
                    val i2 = i1 + len / 2
                    val ur = re[i1]
                    val ui = im[i1]
                    val vr = re[i2] * wr - im[i2] * wi
                    val vi = re[i2] * wi + im[i2] * wr
                    re[i1] = ur + vr
                    im[i1] = ui + vi
                    re[i2] = ur - vr
                    im[i2] = ui - vi
                    val nwr = wr * wlenR - wi * wlenI
                    wi = wr * wlenI + wi * wlenR
                    wr = nwr
                }
                i += len
            }
            len *= 2
        }
    }
}
