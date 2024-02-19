package dev.patbeagan.physics.classical

interface DictionaryPhysicsNuclear {
    // Nuclear Physics Symbols
    interface NuclearPhysicsSymbols {
        val symNeutrino get() = "\\nu"
        val symAntiNeutrino get() = "\\bar{\\nu}"
        val symBetaPlus get() = "\\beta^+"
        val symBetaMinus get() = "\\beta^-"
        val symGammaRay get() = "\\gamma"
        val symHalfLife get() = "T_{\\frac{1}{2}}"
        val symBindingEnergy get() = "B.E."
    }

    // Quantum Mechanics Symbols
    interface QuantumMechanicsSymbols {
        val symWaveFunction get() = "\\Psi"
        val symHamiltonian get() = "\\hat{H}"
        val symPositionOperator get() = "\\hat{x}"
        val symMomentumOperator get() = "\\hat{p}"
        val symSpinOperator get() = "\\hat{S}"
        val symEigenvalue get() = "\\lambda"
        val symEigenstate get() = "| \\phi \\rangle"
        val symBra get() = "\\langle"
        val symKet get() = "\\rangle"
    }

    // Quantum Mechanics Equations
    interface QuantumMechanicsEquations : QuantumMechanicsSymbols {
        val timeDependentSchrodinger: String
            get() = "${symHamiltonian} $symWaveFunction = i \\hbar \\frac{\\partial $symWaveFunction}{\\partial t}"
    }

    // Nuclear Physics Equations
    interface NuclearPhysicsEquations : NuclearPhysicsSymbols {
        val radioactiveDecay: String
            get() = "N(t) = N_0 e^{-\\lambda t}"
    }

}