package main.dictionary

interface DictionaryGraphTheory {
    interface GraphTheorySymbols {

        // Basic graph symbols
        val vertex get() = "V"
        val edge get() = "E"
        val graph get() = "G"
        val weight get() = "w"
        val degree get() = "d"
        val adjacencyMatrix get() = "A"
        val incidenceMatrix get() = "I"
        val path get() = "P"
        val cycle get() = "C"
        val subgraph get() = "H"

        // Special graphs
        val completeGraph get() = "K_n"
        val cycleGraph get() = "C_n"
        val pathGraph get() = "P_n"
    }

    interface GraphTheoryFormulas : GraphTheorySymbols {

        // Basic properties of graphs
        val orderOfGraph: String
            get() = "|$vertex|"

        val sizeOfGraph: String
            get() = "|$edge|"

        val sumOfDegrees: String
            get() = "\\sum_{$vertex \\in G} $degree($vertex) = 2 \\times |$edge|"

        val handshakingLemma: String
            get() = "\\sum_{$vertex \\in G} $degree($vertex) \\text{ is even }"

        // Formulas for special graphs
        val edgesInCompleteGraph: String
            get() = "|$edge| = \\frac{n(n-1)}{2} for $completeGraph"

        val edgesInCycleGraph: String
            get() = "|$edge| = n for $cycleGraph"

        val edgesInPathGraph: String
            get() = "|$edge| = n - 1 for $pathGraph"

        // Connectivity and paths
        val eulerianPath: String
            get() = "A path in $graph that visits every $edge exactly once"

        val eulerianCycle: String
            get() = "A $cycle in $graph that visits every $edge exactly once"

        val hamiltonianPath: String
            get() = "A path in $graph that visits every $vertex exactly once"

        val hamiltonianCycle: String
            get() = "A $cycle in $graph that visits every $vertex exactly once"

    }
}