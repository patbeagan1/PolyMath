package main.probability

import kotlin.math.sqrt
import kotlin.random.Random

data class MonteCarloSummary(
    val mean: Double,
    val variance: Double,
    val sampleSize: Int,
) {
    val standardError: Double
        get() = if (sampleSize > 1) sqrt(variance / sampleSize) else Double.NaN
}

/**
 * Simple Monte Carlo: sample [draw] repeatedly and return mean + unbiased sample variance.
 */
object MonteCarlo {

    fun estimate(
        samples: Int,
        rng: Random = Random.Default,
        draw: () -> Double,
    ): MonteCarloSummary {
        require(samples > 0) { "samples must be positive" }
        var sum = 0.0
        var sumSq = 0.0
        repeat(samples) {
            val x = draw()
            sum += x
            sumSq += x * x
        }
        val mean = sum / samples
        val variance = if (samples > 1) {
            (sumSq - samples * mean * mean) / (samples - 1)
        } else {
            Double.NaN
        }
        return MonteCarloSummary(mean, variance.coerceAtLeast(0.0), samples)
    }

    /**
     * Estimate **E[f(U)]** for **U ~ Uniform(0,1)** via transformation [draw] that maps uniform draws.
     */
    fun estimateUniformExpectation(
        samples: Int,
        rng: Random = Random.Default,
        f: (Double) -> Double,
    ): MonteCarloSummary = estimate(samples, rng) { f(rng.nextDouble()) }
}
