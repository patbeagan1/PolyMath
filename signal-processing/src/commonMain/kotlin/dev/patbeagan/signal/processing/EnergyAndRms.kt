package dev.patbeagan.signal.processing

import dev.patbeagan.signal.domain.DiscreteSignal
import kotlin.math.sqrt

object EnergyAndRms {
    fun totalEnergy(samples: DoubleArray): Double {
        var e = 0.0
        for (v in samples) {
            e += v * v
        }
        return e
    }

    fun meanEnergy(samples: DoubleArray): Double {
        require(samples.isNotEmpty()) { "samples must be non-empty" }
        return totalEnergy(samples) / samples.size
    }

    fun rootMeanSquare(signal: DiscreteSignal): Double =
        sqrt(meanEnergy(signal.samples))
}
