package dev.patbeagan.signal.processing

import dev.patbeagan.signal.domain.DiscreteSignal

/** Running sum \(y[n] = \sum_{k \leq n} x[k]\) with the same [DiscreteSignal.schedule]. */
object CumulativeSum {
    fun of(signal: DiscreteSignal): DiscreteSignal {
        val x = signal.samples
        val out = DoubleArray(x.size)
        var acc = 0.0
        for (i in x.indices) {
            acc += x[i]
            out[i] = acc
        }
        return DiscreteSignal(out, signal.schedule)
    }
}
