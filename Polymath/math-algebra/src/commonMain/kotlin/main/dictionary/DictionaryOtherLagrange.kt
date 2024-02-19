package main.dictionary

interface DictionaryOtherLagrange {
    interface LagrangeInterpolationSymbols {
        val symPolynomial get() = "L(x)"
        val symDataPoints get() = "(x_i, y_i)"
    }

    interface LagrangeInterpolationTheorems : LagrangeInterpolationSymbols {
        val lagrangeInterpolationFormula: String
            get() = "L(x) = \\sum_{i=0}^{n} y_i \\prod_{j=0, j\\neq i}^{n} \\frac{x - x_j}{x_i - x_j}"
    }

    interface LagrangeNumberTheorySymbols {
        val symGroupOrder get() = "|G|"
        val symElementOrder get() = "o(a)"
    }

    interface LagrangeNumberTheoryTheorems : LagrangeNumberTheorySymbols {
        val lagrangesTheorem: String
            get() = "o(a) \\text{ divides } |G| \\text{ for any finite group } G \\text{ and any element } a \\in G"
    }

    interface LagrangianMechanicsSymbols {
        val symLagrangian get() = "\\mathcal{L}(q, \\dot{q})"
        val symGeneralizedCoordinate get() = "q"
        val symGeneralizedMomentum get() = "p"
        val symGeneralizedForce get() = "Q"
        val symKineticEnergy get() = "T"
        val symPotentialEnergy get() = "V"
    }

    interface LagrangianMechanicsTheorems : LagrangianMechanicsSymbols {
        val eulerLagrangeEquation: String
            get() = "\\frac{d}{dt} \\left( \\frac{\\partial \\mathcal{L}}{\\partial \\dot{q}} \\right) - \\frac{\\partial \\mathcal{L}}{\\partial q} = Q"
        val lagrangianFormula: String
            get() = "\\mathcal{L} = T - V"
    }

    interface LagrangeMultipliersSymbols {
        val symFunction get() = "f(x, y)"
        val symConstraint get() = "g(x, y) = c"
        val symMultiplier get() = "\\lambda"
    }

    interface LagrangeMultipliersTheorems : LagrangeMultipliersSymbols {
        val lagrangeMultiplierCondition: String
            get() = "\\nabla f = \\lambda \\nabla g \\text{ for optimization under the constraint } g(x, y) = c"
    }
}