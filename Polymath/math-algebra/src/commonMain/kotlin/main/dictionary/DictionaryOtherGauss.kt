package main.dictionary

interface DictionaryOtherGauss
{
     interface GaussArithmeticSymbols {
         val symSum get() = "S"
         val symNaturalNumber get() = "n"
     }

     interface GaussArithmeticTheorems : GaussArithmeticSymbols {
         val gaussSumOfFirstNNaturalNumbers: String
             get() = "S = \\frac{n(n + 1)}{2}"
     }

     interface GaussianIntegerSymbols {
         val symGaussianInteger get() = "a + bi"
         val symComplexUnit get() = "i"
     }

     interface GaussianIntegerTheorems : GaussianIntegerSymbols {
         val normOfGaussianInteger: String
             get() = "|a + bi|^2 = a^2 + b^2"
     }

     interface GaussCurvatureSymbols {
         val symGaussianCurvature get() = "K"
     }

     interface GaussCurvatureTheorems : GaussCurvatureSymbols {
         val theoremaEgregiumStatement: String
             get() = "K \\text{ is invariant under isometric deformations of a surface.}"
     }


     interface GaussNumberTheorySymbols {
         val symModularArithmetic get() = "\\equiv"
         val symModulus get() = "\\mod"
     }

     interface GaussNumberTheoryTheorems : GaussNumberTheorySymbols {
         val quadraticReciprocity: String
             get() = "\\left(\\frac{p}{q}\\right) \\left(\\frac{q}{p}\\right) = (-1)^{(p-1)(q-1)/4} \\text{ for distinct odd primes } p \\text{ and } q"
     }


     interface GaussDivergenceSymbols {
         val symVectorField get() = "\\mathbf{F}"
         val symVolume get() = "V"
         val symSurface get() = "S"
         val symDivergence get() = "\\nabla \\cdot \\mathbf{F}"
         val symNormal get() = "\\mathbf{n}"
     }

     interface GaussDivergenceTheorems : GaussDivergenceSymbols {
         val gaussDivergenceTheorem: String
             get() = "\\iiint_V (\\nabla \\cdot \\mathbf{F}) dV = \\iint_S \\mathbf{F} \\cdot \\mathbf{n} dS"
     }
 }