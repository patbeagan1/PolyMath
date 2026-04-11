package main.probability

/**
 * Finite discrete-time Markov chain with a row-stochastic transition matrix **P**:
 * `P[i][j]` is the probability of moving from state **i** to state **j** in one step.
 *
 * If **μ** is a row distribution over states, the distribution after one step is **μ P**.
 */
class MarkovChain(
    val stateLabels: List<String>,
    val transition: Array<DoubleArray>,
) {
    init {
        require(stateLabels.size == transition.size) {
            "stateLabels.size (${stateLabels.size}) must match matrix size (${transition.size})"
        }
        require(stateLabels.toSet().size == stateLabels.size) { "state labels must be unique" }
        requireSquareStochastic(transition)
    }

    val size: Int get() = stateLabels.size

    fun indexOf(label: String): Int {
        val i = stateLabels.indexOf(label)
        require(i >= 0) { "Unknown state label: $label" }
        return i
    }

    /** Distribution after [steps] steps starting from row distribution [initial]. */
    fun distributionAfterSteps(initial: DoubleArray, steps: Int): DoubleArray {
        requireProbabilityVector(initial)
        require(initial.size == size) { "initial length ${initial.size} != $size" }
        require(steps >= 0) { "steps must be non-negative" }
        var mu = initial.clone()
        repeat(steps) {
            mu = rowTimesMatrix(mu, transition)
        }
        return mu
    }

    /** Convenience: start from a one-hot state identified by label. */
    fun distributionAfterSteps(startLabel: String, steps: Int): DoubleArray {
        val v = DoubleArray(size) { 0.0 }
        v[indexOf(startLabel)] = 1.0
        return distributionAfterSteps(v, steps)
    }

    /** [n]-step transition matrix **P^n** (same convention as [transition]). */
    fun nStepTransitionMatrix(n: Int): Array<DoubleArray> = matrixPower(transition, n)

    /**
     * Approximate stationary distribution **π** with **π P ≈ π** via power iteration,
     * starting from a uniform prior. Suitable for ergodic (irreducible, aperiodic) chains;
     * behaviour on reducible chains depends on structure.
     */
    fun stationaryDistribution(
        epsilon: Double = 1e-10,
        maxIterations: Int = 500_000,
    ): DoubleArray {
        require(epsilon > 0.0)
        require(maxIterations > 0)
        var mu = DoubleArray(size) { 1.0 / size }
        repeat(maxIterations) {
            val next = rowTimesMatrix(mu, transition)
            if (l1Distance(mu, next) < epsilon) {
                return next
            }
            mu = next
        }
        error("stationaryDistribution did not converge in $maxIterations iterations")
    }
}
