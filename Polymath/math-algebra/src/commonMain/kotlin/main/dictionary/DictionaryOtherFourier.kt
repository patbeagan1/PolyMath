package main.dictionary

interface DictionaryOtherFourier{
    interface FourierSeriesSymbols {
        val symPeriodicFunction get() = "f(x)"
        val symFourierCoefficientAn get() = "a_n"
        val symFourierCoefficientBn get() = "b_n"
        val symFrequency get() = "n"
    }

    interface FourierSeriesTheorems : FourierSeriesSymbols {
        val fourierSeriesExpansion: String
            get() = "f(x) = a_0 + \\sum_{n=1}^{\\infty} (a_n\\cos(2\\pi nx) + b_n\\sin(2\\pi nx))"
        val fourierCoefficientAnFormula: String
            get() = "a_n = \\frac{1}{T} \\int_{-T/2}^{T/2} f(x)\\cos(2\\pi nx) dx"
        val fourierCoefficientBnFormula: String
            get() = "b_n = \\frac{1}{T} \\int_{-T/2}^{T/2} f(x)\\sin(2\\pi nx) dx"
    }

    interface FourierTransformSymbols {
        val symFunctionTimeDomain get() = "f(t)"
        val symFunctionFrequencyDomain get() = "F(\\omega)"
        val symAngularFrequency get() = "\\omega"
    }

    interface FourierTransformTheorems : FourierTransformSymbols {
        val fourierTransformDefinition: String
            get() = "F(\\omega) = \\int_{-\\infty}^{\\infty} f(t)e^{-j\\omega t} dt"
        val inverseFourierTransformDefinition: String
            get() = "f(t) = \\frac{1}{2\\pi} \\int_{-\\infty}^{\\infty} F(\\omega)e^{j\\omega t} d\\omega"
    }

    interface ComplexFourierSymbols {
        val symComplexFourierCoefficient get() = "c_n"
    }

    interface ComplexFourierTheorems : ComplexFourierSymbols, FourierSeriesSymbols {
        val complexFourierSeries: String
            get() = "f(x) = \\sum_{n=-\\infty}^{\\infty} c_n e^{j2\\pi nx}"
        val complexFourierCoefficientFormula: String
            get() = "c_n = \\frac{1}{T} \\int_{-T/2}^{T/2} f(x)e^{-j2\\pi nx} dx"
    }
}