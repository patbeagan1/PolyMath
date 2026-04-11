package main.dictionary

/**
 * LaTeX fragments for Markov chains and basic stochastic-process notation.
 */
interface DictionaryStochasticProcesses {

    interface MarkovChainSymbols {
        val stateSpace get() = "S"
        val transitionMatrix get() = "P"
        val transitionEntry get() = "P_{ij}"
        val nStepTransition get() = "P^{(n)}"
        val initialDistribution get() = "\\mu^{(0)}"
        val distributionAtTimeN get() = "\\mu^{(n)}"
        val stationaryDistribution get() = "\\pi"
    }

    interface MarkovChainFormulas : MarkovChainSymbols {
        val oneStepEvolution: String
            get() = "$distributionAtTimeN = $initialDistribution $transitionMatrix^{n}"

        val chapmanKolmogorov: String
            get() = "$nStepTransition = $transitionMatrix^{n}"

        val stationaryBalance: String
            get() = "$stationaryDistribution $transitionMatrix = $stationaryDistribution, \\quad \\sum_{i} \\pi_i = 1"
    }
}
