package main.dictionary

import dev.patbeagan.math.base.NumberTheorySymbols


interface DictionaryOtherEuler {

    interface EulerNumberTheoryTheorems : NumberTheorySymbols {
        val eulersTotientFunction: String
            get() = "\\varphi(n) = n \\prod_{p|n, p \\text{ prime}} (1 - \\frac{1}{p})"
        val eulersProductFormula: String
            get() = "\\prod_{p \\text{ prime}} \\frac{1}{1 - p^{-s}} = \\sum_{n=1}^{\\infty} \\frac{1}{n^s}"
    }

    interface EulerGraphTheorySymbols {
        val symGraph get() = "G(V,E)"
    }

    interface EulerGraphTheoryTheorems : EulerGraphTheorySymbols {
        val eulersFormulaForPlanarGraphs: String
            get() = "V - E + F = 2 \\text{ for connected planar graphs } G(V,E) \\text{ with } F \\text{ faces}"
        val eulersCharacteristics: String
            get() = "\\chi(G) = V - E + F"
        val eulersSevenBridgeProblem: String
            get() = "\\text{No Eulerian circuit exists for } G(V,E) \\text{ if more than 2 vertices have odd degree.}"
    }


    interface EulerRealAnalysisTheorems {
        val eulersFormulaForComplexExponential: String
            get() = "e^{ix} = \\cos(x) + i\\sin(x)"
        val eulerMascheroniConstant: String
            get() = "\\gamma = \\lim_{n \\to \\infty} (1 + \\frac{1}{2} + \\frac{1}{3} + \\ldots + \\frac{1}{n} - \\ln(n))"
    }

    interface EulerDiffEqSymbols {
        val symDifferential get() = "y''"
        val symFunction get() = "y(x)"
    }

    interface EulerDiffEqTheorems : EulerDiffEqSymbols {
        val eulerCauchyEquation: String
            get() = "x^2y'' + axy' + by = 0"
    }

    interface EulerComplexNumbersSymbols {
        val symExponential get() = "e"
        val symComplexUnit get() = "i"
        val symAngle get() = "\\theta"
    }

    interface EulerComplexNumbersTheorems : EulerComplexNumbersSymbols {
        val eulerFormula: String
            get() = "e^{i\\theta} = \\cos(\\theta) + i\\sin(\\theta)"
    }

    interface EulerIdentitySymbols : EulerComplexNumbersSymbols

    interface EulerIdentityTheorems : EulerIdentitySymbols {
        val eulerIdentity: String
            get() = "e^{i\\pi} + 1 = 0"
    }

    interface EulerTotientSymbols {
        val symTotientFunction get() = "\\varphi(n)"
        val symInteger get() = "n"
    }

    interface EulerTotientTheorems : EulerTotientSymbols {
        val eulerTotientFunctionDefinition: String
            get() = "\\varphi(n) \\text{ is the number of integers between 1 and } n \\text{ that are coprime to } n"
    }

    interface EulerPolyhedronSymbols {
        val symFaces get() = "F"
        val symEdges get() = "E"
        val symVertices get() = "V"
    }

    interface EulerPolyhedronTheorems : EulerPolyhedronSymbols {
        val eulerPolyhedronFormula: String
            get() = "V - E + F = 2 \\text{ for any convex polyhedron}"
    }

    interface EulerHomogeneousFunctionSymbols {
        val symFunction get() = "f(\\mathbf{x})"
        val symDegree get() = "k"
    }

    interface EulerHomogeneousFunctionTheorems : EulerHomogeneousFunctionSymbols {
        val eulerHomogeneousFunctionTheorem: String
            get() = "\\sum x_i \\frac{\\partial f}{\\partial x_i} = k f(\\mathbf{x}) \\text{ if } f(\\mathbf{x}) \\text{ is a homogeneous function of degree } k"
    }
}