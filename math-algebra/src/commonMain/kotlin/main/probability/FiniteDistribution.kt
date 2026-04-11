package main.probability

import kotlin.math.ln
import kotlin.math.log2
import kotlin.random.Random

/**
 * Probability mass function on labels, always kept normalized (sums to 1).
 */
class FiniteDistribution private constructor(
    val labels: List<String>,
    private val probs: DoubleArray,
) {
    init {
        require(labels.size == probs.size && labels.isNotEmpty())
        requireProbabilityVector(probs)
    }

    operator fun get(label: String): Double {
        val i = labels.indexOf(label)
        require(i >= 0) { "Unknown label: $label" }
        return probs[i]
    }

    fun probabilityAt(index: Int): Double = probs[index]

    fun toMap(): Map<String, Double> = labels.indices.associate { labels[it] to probs[it] }

    /** Shannon entropy; [base2] true → bits, false → nats (natural log). */
    fun entropy(base2: Boolean = false): Double {
        var h = 0.0
        for (p in probs) {
            if (p > 0.0) {
                h += if (base2) -p * log2(p) else -p * ln(p)
            }
        }
        return h
    }

    fun expectation(valueByLabel: Map<String, Double>): Double {
        var e = 0.0
        labels.indices.forEach { i ->
            val v = valueByLabel[labels[i]]
                ?: error("Missing value for label ${labels[i]}")
            e += probs[i] * v
        }
        return e
    }

    fun sample(rng: Random = Random.Default): String {
        val u = rng.nextDouble()
        var c = 0.0
        for (i in probs.indices) {
            c += probs[i]
            if (u <= c) return labels[i]
        }
        return labels.last()
    }

    companion object {
        fun fromMap(weights: Map<String, Double>): FiniteDistribution {
            require(weights.isNotEmpty())
            val labels = weights.keys.toList()
            val sum = weights.values.sum()
            require(sum > 0.0) { "weights must sum to a positive value" }
            val probs = DoubleArray(labels.size) { i -> weights[labels[i]]!! / sum }
            return FiniteDistribution(labels, probs)
        }

        fun fromLabeledProbabilities(pairs: List<Pair<String, Double>>): FiniteDistribution {
            require(pairs.isNotEmpty())
            return fromMap(pairs.toMap())
        }
    }
}
