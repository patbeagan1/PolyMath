package main.dictionary

interface DictionaryStatistics {

    interface ProbabilitySymbols {
        val symProbability get() = "P"
        val symEventA get() = "A"
        val symEventB get() = "B"
    }

    // Statistics Symbols
    interface StatisticsSymbols {
        val symMean get() = "\\mu"
        val symMedian get() = "\\text{Med}"
        val symMode get() = "\\text{Mode}"
        val symVariance get() = "\\sigma^2"
        val symStandardDeviation get() = "\\sigma"
        val symCorrelationCoefficient get() = "r"
        val symPopulationSize get() = "N"
        val symSampleSize get() = "n"
        val symProbability get() = "P"
        val symExpectedValue get() = "E"
        val symZScore get() = "z"
        val symTScore get() = "t"
        val symChiSquare get() = "\\chi^2"
        val symFStatistic get() = "F"
        val symPValue get() = "p"
        val symConfidenceInterval get() = "CI"
        val symStandardError get() = "SE"
        val symCoefficientOfVariation get() = "CV"
        val symCovariance get() = "COV"
        val symSkewness get() = "\\gamma_1"
        val symKurtosis get() = "\\gamma_2"
        val symDegreeOfFreedom get() = "df"
        val symBinomialCoefficient get() = "\\binom{n}{k}"
        val symFactorial get() = "n!"
        val symRegressionLine get() = "y = mx + c"
    }

    interface ProbabilityEquations : ProbabilitySymbols {
        val bayesTheorem: String
            get() = "$symProbability($symEventA|$symEventB) = \\frac{$symProbability($symEventB|$symEventA) $symProbability($symEventA)}{$symProbability($symEventB)}"
    }

    // Statistics Equations
    interface StatisticsEquations : StatisticsSymbols {
        val varianceFormula: String
            get() = "Var(X) = E[(X - $symMean)^2]"
        val standardDeviationFormula: String
            get() = "$symStandardDeviation = \\sqrt{Var(X)}"
        val covarianceFormula: String
            get() = "$symCovariance(X, Y) = E[(X - $symMean)_X(Y - $symMean)_Y]"
        val correlationFormula: String
            get() = "$symCorrelationCoefficient = \\frac{$symCovariance(X, Y)}{$symStandardDeviation}_X $symStandardDeviation}_Y"
    }

    interface StatisticalFormulas {

        val mean: String
            get() = "\\bar{X} = \\frac{1}{n} \\sum_{i=1}^{n} x_i"

        val populationVariance: String
            get() = "\\sigma^2 = \\frac{1}{N} \\sum_{i=1}^{N} (x_i - \\mu)^2"

        val sampleVariance: String
            get() = "s^2 = \\frac{1}{n-1} \\sum_{i=1}^{n} (x_i - \\bar{x})^2"

        val populationStdDev: String
            get() = "\\sigma = \\sqrt{\\frac{1}{N} \\sum_{i=1}^{N} (x_i - \\mu)^2}"

        val sampleStdDev: String
            get() = "s = \\sqrt{\\frac{1}{n-1} \\sum_{i=1}^{n} (x_i - \\bar{x})^2}"

        val coefficientOfVariation: String
            get() = "CV = \\frac{s}{\\bar{x}} \\times 100\\%"

        val binomialProbability: String
            get() = "P(X=k) = \\binom{n}{k} p^k (1-p)^{n-k}"

        val normalDistributionPDF: String
            get() = "f(x|\\mu, \\sigma^2) = \\frac{1}{\\sqrt{2\\pi\\sigma^2}} e^{-\\frac{(x-\\mu)^2}{2\\sigma^2}}"

        val correlationCoefficient: String
            get() = "r = \\frac{\\sum_{i=1}^{n} (x_i - \\bar{x})(y_i - \\bar{y})}{\\sqrt{\\sum_{i=1}^{n} (x_i - \\bar{x})^2 \\sum_{i=1}^{n} (y_i - \\bar{y})^2}}"

        val linearRegression: String
            get() = "y = \\beta_0 + \\beta_1 x"

        val beta1: String
            get() = "\\beta_1 = \\frac{\\sum_{i=1}^{n} (x_i - \\bar{x})(y_i - \\bar{y})}{\\sum_{i=1}^{n} (x_i - \\bar{x})^2}"

        val beta0: String
            get() = "\\beta_0 = \\bar{y} - \\beta_1 \\bar{x}"

        val chiSquaredStatistic: String
            get() = "\\chi^2 = \\sum \\frac{(O_i - E_i)^2}{E_i}"

        val tStatistic: String
            get() = "t = \\frac{\\bar{x} - \\mu}{\\sigma / \\sqrt{n}}"

        val zScore: String
            get() = "z = \\frac{x - \\mu}{\\sigma}"

        val bayesTheorem: String
            get() = "P(A|B) = \\frac{P(B|A) \\times P(A)}{P(B)}"
    }
}