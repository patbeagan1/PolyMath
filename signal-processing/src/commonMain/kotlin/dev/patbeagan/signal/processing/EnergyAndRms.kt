package dev.patbeagan.signal.processing

import dev.patbeagan.signal.domain.DiscreteSignal
import dev.patbeagan.signal.domain.MeanSquareAmplitude
import dev.patbeagan.signal.domain.RootMeanSquare
import dev.patbeagan.signal.domain.SignalEnergy
import kotlin.math.sqrt

object EnergyAndRms {
    fun totalEnergy(signal: DiscreteSignal): SignalEnergy = totalEnergy(signal.samples)

    fun totalEnergy(samples: DoubleArray): SignalEnergy {
        var e = 0.0
        for (v in samples) {
            e += v * v
        }
        return SignalEnergy(e)
    }

    fun meanSquareAmplitude(signal: DiscreteSignal): MeanSquareAmplitude =
        meanSquareAmplitude(signal.samples)

    fun meanSquareAmplitude(samples: DoubleArray): MeanSquareAmplitude {
        require(samples.isNotEmpty()) { "samples must be non-empty" }
        return MeanSquareAmplitude(totalEnergy(samples).value / samples.size)
    }

    fun rootMeanSquare(signal: DiscreteSignal): RootMeanSquare =
        RootMeanSquare(sqrt(meanSquareAmplitude(signal.samples).value))
}
