package main.dictionary

interface DictionaryCombinatorics {
    interface CombinatoricsSymbols {
        // Basic symbols
        val factorial get() = "!"
        val permutations get() = "P"
        val combinations get() = "C"
        val binomialCoefficient get() = "\\binom"

        // Variables often used
        val totalItems get() = "n"
        val chosenItems get() = "k"
    }

    interface CombinatoricsFormulas : CombinatoricsSymbols {
        // Factorial: n!
        val factorialFormula: String
            get() = "$totalItems$factorial = $totalItems \\times ($totalItems - 1) \\times \\dots \\times 2 \\times 1"

        // Permutations: number of ways to arrange k items out of n
        val permutationFormula: String
            get() = "$permutations($totalItems, $chosenItems) = \\frac{$totalItems$factorial}{($totalItems - $chosenItems)$factorial}"

        // Combinations: number of ways to choose k items out of n without considering the order
        val combinationFormula: String
            get() = "$combinations($totalItems, $chosenItems) = \\frac{$totalItems$factorial}{($chosenItems$factorial) \\times ($totalItems - $chosenItems)$factorial}"

        // Binomial Coefficient: number of ways to choose k items out of n, also the coefficients in the expansion of (x+y)^n
        val binomialCoefficientFormula: String
            get() = "$binomialCoefficient{$totalItems}{$chosenItems} = \\frac{$totalItems$factorial}{($chosenItems$factorial) \\times ($totalItems - $chosenItems)$factorial}"

        // Inclusion-Exclusion Principle
        val inclusionExclusion: String
            get() = "|A \\cup B| = |A| + |B| - |A \\cap B|"
    }
}