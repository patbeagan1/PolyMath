package main.dictionary

interface DictionaryInformationTheory {

    interface InformationTheorySymbols {

        // Basic symbols
        val entropy get() = "H"
        val mutualInformation get() = "I"
        val conditionalEntropy get() = "H"
        val jointEntropy get() = "H"
        val kullbackLeiblerDivergence get() = "D_{KL}"
        val binaryLog get() = "\\log_2"
        val expectation get() = "\\mathbb{E}"

        // Variables often used
        val randomVariableX get() = "X"
        val randomVariableY get() = "Y"
        val probabilityX get() = "p(x)"
        val probabilityY get() = "p(y)"
        val jointProbabilityXY get() = "p(x, y)"
        val conditionalProbabilityXY get() = "p(x|y)"

    }

    interface InformationTheoryFormulas : InformationTheorySymbols {

        // Entropy: Expected value of the information content
        val entropyOfX: String
            get() = "$entropy($randomVariableX) = - \\sum_x $probabilityX $binaryLog $probabilityX"

        // Joint Entropy: Entropy of a joint distribution
        val jointEntropyOfXY: String
            get() = "$jointEntropy($randomVariableX, $randomVariableY) = - \\sum_{x, y} $jointProbabilityXY $binaryLog $jointProbabilityXY"

        // Conditional Entropy: Amount of uncertainty remaining in X given Y
        val conditionalEntropyOfXGivenY: String
            get() = "$conditionalEntropy($randomVariableX | $randomVariableY) = $jointEntropy($randomVariableX, $randomVariableY) - $entropy($randomVariableY)"

        // Mutual Information: Amount of information shared between X and Y
        val mutualInformationOfXY: String
            get() = "$mutualInformation($randomVariableX ; $randomVariableY) = \\sum_x \\sum_y $jointProbabilityXY $binaryLog \\frac{$jointProbabilityXY}{$probabilityX $probabilityY}"

        // Kullback-Leibler Divergence: Measures the difference between two probability distributions
        val kullbackLeiblerDivergenceOfPQ: String
            get() = "$kullbackLeiblerDivergence(p||q) = \\sum_x p(x) $binaryLog \\frac{p(x)}{q(x)}"

    }

}