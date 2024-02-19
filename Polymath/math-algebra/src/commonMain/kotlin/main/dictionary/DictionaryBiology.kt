package main.dictionary

interface DictionaryBiology {
    interface GeneticsSymbols {
        val symDominant get() = "A"
        val symRecessive get() = "a"
    }

    interface GeneticsEquations : GeneticsSymbols {
        val punnettSquareMono: String
            get() = "$symDominant$symRecessive x $symDominant$symRecessive -> 1 $symDominant$symDominant : 2 $symDominant$symRecessive : 1 $symRecessive$symRecessive"
    }

    interface BiochemistrySymbols {
        val symSubstrate get() = "S"
        val symEnzyme get() = "E"
        val symProduct get() = "P"
        val symRateConstant get() = "k"
    }

    interface BiochemistryEquations : BiochemistrySymbols {
        val michaelisMenten: String
            get() = "v = \\frac{V_{max} [S]}{K_m + [S]}"
    }

    interface NeuroscienceSymbols {
        val symActionPotential get() = "AP"
        val symRestingPotential get() = "RP"
    }

    interface NeuroscienceEquations : NeuroscienceSymbols {
        val actionPotentialInitiation: String
            get() = "Threshold reached -> $symActionPotential generated"
    }
}