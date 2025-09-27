package main.dictionary

import main.dsl.expressions.ScalarAlgebra.Negate
import main.dsl.expressions.ScalarAlgebra.Sqrt
import main.dsl.mathnum.Variable
import main.dsl.expressions.ScalarRelation
import main.dsl.mathnum.num

interface DictionaryAlgebra {
    interface AlgebraSymbols {
        val symX get() = Variable("x")
        val symY get() = Variable("y")
        val symZ get() = Variable("z")
        val symA get() = Variable("a")
        val symB get() = Variable("b")
        val symC get() = Variable("c")
        val symM get() = Variable("m")
        val symN get() = Variable("n")
        val symV get() = Variable("v")

        val symDeterminant get() = Variable("\\det(A)")
        val symEigenvalue get() = Variable("\\lambda")
        val symEigenvector get() = Variable("\\vec{v}")
        val symIdentityMatrix get() = Variable("I")
        val symInverseMatrix get() = Variable("A^{-1}")
    }

    interface AlgebraEquations : AlgebraSymbols {
        fun quadraticFormula(): Pair<ScalarRelation.Equation, ScalarRelation.Equation> {
            val b = symB
            val a = symA
            val c = symC
            val root = Sqrt(b.squared() - 4.num() * a * c)
            val denominator = 2.num() * a * c
            return Pair(
                Variable("x").standsFor((Negate(b) + root) / denominator),
                Variable("x").standsFor((Negate(b) - root) / denominator)
            )
        }

        fun linearEquation(
            x: Double = Double.NaN,
            b: Double = Double.NaN,
            m: Double = Double.NaN,
        ): ScalarRelation.Equation {
            val incoming = symX
            val translation = symB
            val slope = symM
            return symY.standsFor(slope * incoming + translation)
        }
    }


    interface AlgebraTheorems : AlgebraSymbols {
        val binomialTheorem: String
            get() = "(x+y)^n = \\sum_{k=0}^{n} {n\\choose k} x^{n-k} y^k"
    }

    interface AdvancedAlgebraEquations : AlgebraSymbols {
        val polynomialGeneralForm: String
            get() = "P(x) = a_n x^n + a_{n-1} x^{n-1} + ... + a_2 x^2 + a_1 x + a_0"
        val matrixMultiplication: String
            get() = "C_{ij} = \\sum_k A_{ik} B_{kj}"
        val matrixTranspose: String
            get() = "A^T_{ij} = A_{ji}"
        val dotProduct: String
            get() = "v \\cdot w = |v||w| \\cos(\\theta)"
    }


    interface LinearAlgebraEquations : AlgebraSymbols {
        val matrixDeterminant2x2: String
            get() = "|A| = ad - bc"
        val eigenvalueEquation: String
            get() = "A\\vec{v} = \\lambda\\vec{v}"
        val matrixInverseDefinition: String
            get() = "AA^{-1} = A^{-1}A = I"
        val determinant2x2Matrix: String
            get() = "\\det(A) = ad - bc, for A = [[a, b], [c, d]]"

        //        val rankNullityTheorem: String
//            get() = "For any m×n matrix, the rank of the matrix plus the nullity of the matrix equals n."
        val rankNullityTheorem: String
            get() = "rank(A) + nullity(A) = n"

    }
}