package dev.patbeagan.signal.processing

import dev.patbeagan.signal.domain.DiscreteSignal
import dev.patbeagan.signal.domain.SampleCount

/**
 * Simple causal moving-average along the time axis.
 *
 * Output has the same length and [DiscreteSignal.schedule] as the input. For early samples where a full
 * window is not available yet, the average uses the available prefix (1..window-1 samples).
 */
object MovingAverage {
    fun of(signal: DiscreteSignal, window: SampleCount): DiscreteSignal {
        require(window.value <= signal.samples.size) {
            "window (${window.value}) cannot exceed signal length (${signal.samples.size})"
        }
        val x = signal.samples
        val w = window.value
        val out = DoubleArray(x.size)
        var acc = 0.0
        for (i in x.indices) {
            acc += x[i]
            if (i >= w) {
                acc -= x[i - w]
            }
            val denom = minOf(i + 1, w)
            out[i] = acc / denom
        }
        return DiscreteSignal(out, signal.schedule)
    }
}
