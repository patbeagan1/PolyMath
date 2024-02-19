package main.dictionary

interface DictionaryOtherLaplace{
    interface LaplaceEquationSymbols {
        val symLaplacian get() = "\\nabla^2"
        val symFunction get() = "f"
    }

    interface LaplaceTransformSymbols {
        val symFunctionTimeDomain get() = "f(t)"
        val symFunctionFrequencyDomain get() = "F(s)"
        val symComplexFrequency get() = "s"
    }

    interface CentralLimitSymbols {
        val symSumOfRandomVariables get() = "S_n"
        val symRandomVariable get() = "X_i"
    }


    interface LaplaceMethodSymbols {
        val symFunction get() = "f(x)"
        val symIntegral get() = "\\int e^{Nf(x)} dx"
    }

    interface LaplaceEquationTheorems : LaplaceEquationSymbols {
        val laplaceEquation: String
            get() = "\\nabla^2 f = 0"
    }

    interface LaplaceTransformTheorems : LaplaceTransformSymbols {
        val laplaceTransformDefinition: String
            get() = "F(s) = \\int_{0}^{\\infty} f(t)e^{-st} dt"
    }

        interface CentralLimitTheorems : CentralLimitSymbols {
        val centralLimitTheorem: String
            get() = "Z_n = \\frac{S_n - n\\mu}{\\sigma\\sqrt{n}} \\text{ converges in distribution to a standard normal distribution as } n \\to \\infty"
    }


    interface LaplaceMethodTheorems : LaplaceMethodSymbols {
        val laplaceMethodApproximation: String
            get() = "\\int e^{Nf(x)} dx \\approx \\sqrt{\\frac{2\\pi}{N|f''(x_0)|}} e^{Nf(x_0)} \\text{ as } N \\to \\infty"
    }
}