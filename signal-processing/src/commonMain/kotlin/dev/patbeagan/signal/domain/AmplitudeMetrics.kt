package dev.patbeagan.signal.domain

import kotlin.jvm.JvmInline

/** Total energy \(\sum_n x[n]^2\) for a real sequence (dimensionless unless samples carry units). */
@JvmInline
value class SignalEnergy(val value: Double) {
    init {
        require(value >= 0.0) { "SignalEnergy must be non-negative, was $value" }
    }
}

/** Mean square \(\frac{1}{N}\sum_n x[n]^2\). */
@JvmInline
value class MeanSquareAmplitude(val value: Double) {
    init {
        require(value >= 0.0) { "MeanSquareAmplitude must be non-negative, was $value" }
    }
}

/** Root mean square \(\sqrt{\text{mean square}}\). */
@JvmInline
value class RootMeanSquare(val value: Double) {
    init {
        require(value >= 0.0) { "RootMeanSquare must be non-negative, was $value" }
    }
}

/** \(\max x[n] - \min x[n]\) over the sequence. */
@JvmInline
value class PeakToPeakSpan(val value: Double) {
    init {
        require(value >= 0.0) { "PeakToPeakSpan must be non-negative, was $value" }
    }
}
