package dev.patbeagan.signal.processing

import dev.patbeagan.signal.domain.DiscreteSignal
import dev.patbeagan.signal.domain.SampleCount
import kotlin.math.abs

/**
 * Envelope-style smoothing of instantaneous magnitude.
 */
object EnvelopeDetection {
    /** Moving average of `|x[n]|`, same length and schedule as [signal]. */
    fun movingAverageMagnitude(signal: DiscreteSignal, window: SampleCount): DiscreteSignal {
        val mag = signal.mapSamples { abs(it) }
        return MovingAverage.of(mag, window)
    }
}
