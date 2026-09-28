package dev.patbeagan.signal.processing

import dev.patbeagan.signal.domain.DiscreteSignal

/**
 * Discrete-time derivative along the sample axis using forward/backward differences at edges
 * and central differences in the interior.
 */
object NumericalDerivative {
    fun centralDifference(signal: DiscreteSignal): DiscreteSignal {
        val x = signal.samples
        val n = x.size
        if (n == 1) {
            return DiscreteSignal(doubleArrayOf(0.0), signal.schedule)
        }
        val out = DoubleArray(n)
        out[0] = x[1] - x[0]
        out[n - 1] = x[n - 1] - x[n - 2]
        for (i in 1 until n - 1) {
            out[i] = (x[i + 1] - x[i - 1]) / 2.0
        }
        return DiscreteSignal(out, signal.schedule)
    }
}
