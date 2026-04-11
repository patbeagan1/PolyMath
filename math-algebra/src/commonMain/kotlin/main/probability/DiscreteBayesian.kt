package main.probability

/**
 * Discrete Bayesian updates: finite hypotheses **H_i** with prior **P(H_i)** and
 * likelihoods **P(E | H_i)** for a fixed observation **E**.
 */
object DiscreteBayesian {

    /**
     * Posterior **P(H_i | E)** ∝ **P(H_i) P(E | H_i)**, normalized.
     */
    fun posterior(prior: DoubleArray, likelihood: DoubleArray): DoubleArray {
        require(prior.size == likelihood.size) {
            "prior and likelihood must have same length (${prior.size} vs ${likelihood.size})"
        }
        requireProbabilityVector(prior)
        likelihood.forEachIndexed { i, l ->
            require(l >= 0.0 && !l.isNaN()) { "likelihood[$i]=$l must be non-negative" }
        }
        val unnorm = DoubleArray(prior.size) { i -> prior[i] * likelihood[i] }
        val z = unnorm.sum()
        require(z > 0.0 && !z.isNaN()) { "marginal P(E) is zero; cannot normalize posterior" }
        return DoubleArray(unnorm.size) { unnorm[it] / z }
    }

    /**
     * Sequential conditioning: given a prior and independent likelihood columns for each observation
     * (each column is **P(E_k | H_i)**), returns the posterior after all observations.
     */
    fun posteriorSequence(prior: DoubleArray, likelihoodColumns: List<DoubleArray>): DoubleArray {
        var p = prior
        for (col in likelihoodColumns) {
            p = posterior(p, col)
        }
        return p
    }

    /** Bayes factor **P(E|H_1)/P(E|H_0)** for binary hypotheses. */
    fun bayesFactor(likelihoodH1: Double, likelihoodH0: Double): Double {
        require(likelihoodH0 > 0.0 && likelihoodH1 >= 0.0)
        return likelihoodH1 / likelihoodH0
    }

    /** Log posterior odds from log prior odds and log Bayes factor. */
    fun logPosteriorOdds(logPriorOdds: Double, logBayesFactor: Double): Double =
        logPriorOdds + logBayesFactor

    /**
     * Normal-approximation diagnostic: z-score **(x - μ) / σ** for a Gaussian likelihood
     * **N(x; μ, σ²)** (not a full Bayesian treatment; useful for quick reasoning).
     */
    fun gaussianLikelihoodZScore(x: Double, mean: Double, stdDev: Double): Double {
        require(stdDev > 0.0)
        return (x - mean) / stdDev
    }
}
