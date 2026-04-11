package dev.patbeagan.signal.processing

import dev.patbeagan.signal.domain.DiscreteSignal
import dev.patbeagan.signal.domain.PeakToPeakSpan

object SignalStatistics {
    fun peakToPeak(signal: DiscreteSignal): PeakToPeakSpan {
        val a = signal.samples
        var min = a[0]
        var max = a[0]
        for (i in 1 until a.size) {
            val v = a[i]
            if (v < min) min = v
            if (v > max) max = v
        }
        return PeakToPeakSpan(max - min)
    }
}
