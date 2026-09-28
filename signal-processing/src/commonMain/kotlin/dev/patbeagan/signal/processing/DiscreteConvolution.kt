package dev.patbeagan.signal.processing

import dev.patbeagan.signal.domain.DiscreteSignal
import dev.patbeagan.signal.domain.SamplingSchedule

/**
 * Linear convolution of finite real sequences (full output length n + m - 1).
 */
object DiscreteConvolution {
    fun full(a: DoubleArray, b: DoubleArray): DoubleArray {
        if (a.isEmpty() || b.isEmpty()) return doubleArrayOf()
        val n = a.size
        val m = b.size
        val y = DoubleArray(n + m - 1)
        for (k in y.indices) {
            var sum = 0.0
            val jMin = maxOf(0, k - (m - 1))
            val jMax = minOf(n - 1, k)
            for (j in jMin..jMax) {
                sum += a[j] * b[k - j]
            }
            y[k] = sum
        }
        return y
    }

    /**
     * Convolves two signals that share the same [SamplingSchedule]; the result keeps that schedule.
     * Output length is `x.sampleCount + h.sampleCount - 1` (full linear convolution).
     */
    fun of(x: DiscreteSignal, h: DiscreteSignal): DiscreteSignal {
        require(x.schedule == h.schedule) {
            "Signals must share the same sampling schedule for typed convolution"
        }
        return DiscreteSignal(full(x.samples, h.samples), x.schedule)
    }

    /**
     * Same as [of] but allows passing an explicit schedule for the result (must equal both inputs).
     */
    fun of(x: DiscreteSignal, h: DiscreteSignal, schedule: SamplingSchedule): DiscreteSignal {
        require(x.schedule == schedule && h.schedule == schedule) {
            "Explicit schedule must match both operands"
        }
        return of(x, h)
    }
}
