package dev.patbeagan.math.geometry

interface DictionaryTrigonometry {
    interface TrigonometrySymbols {
        // Basic trigonometric functions
        val sine get() = "\\sin"
        val cosine get() = "\\cos"
        val tangent get() = "\\tan"
        val cosecant get() = "\\csc"
        val secant get() = "\\sec"
        val cotangent get() = "\\cot"

        // Inverse trigonometric functions
        val arcsine get() = "\\sin^{-1}"
        val arccosine get() = "\\cos^{-1}"
        val arctangent get() = "\\tan^{-1}"

        // Variables often used
        val angleTheta get() = "\\theta"
        val oppositeSide get() = "o"
        val adjacentSide get() = "a"
        val hypotenuse get() = "h"
    }

    interface TrigonometryFormulas : TrigonometrySymbols {
        // Sine
        val sineFormula: String
            get() = "$sine($angleTheta) = \\frac{$oppositeSide}{$hypotenuse}"

        // Cosine
        val cosineFormula: String
            get() = "$cosine($angleTheta) = \\frac{$adjacentSide}{$hypotenuse}"

        // Tangent
        val tangentFormula: String
            get() = "$tangent($angleTheta) = \\frac{$oppositeSide}{$adjacentSide}"

        // Cosecant (reciprocal of sine)
        val cosecantFormula: String
            get() = "$cosecant($angleTheta) = \\frac{1}{$sine($angleTheta)}"

        // Secant (reciprocal of cosine)
        val secantFormula: String
            get() = "$secant($angleTheta) = \\frac{1}{$cosine($angleTheta)}"

        // Cotangent (reciprocal of tangent)
        val cotangentFormula: String
            get() = "$cotangent($angleTheta) = \\frac{1}{$tangent($angleTheta)}"

        // Pythagorean identities
        val sineSquaredPlusCosineSquared: String
            get() = "$sine^2($angleTheta) + $cosine^2($angleTheta) = 1"

        val tangentSquaredPlusOne: String
            get() = "$tangent^2($angleTheta) + 1 = $secant^2($angleTheta)"

        val cosecantSquaredMinusOne: String
            get() = "$cosecant^2($angleTheta) - 1 = $cotangent^2($angleTheta)"
    }


    interface AdvancedTrigonometrySymbols {
        // Sum and Difference Identities
        val sineSum get() = "\\sin(a+b)"
        val sineDifference get() = "\\sin(a-b)"
        val cosineSum get() = "\\cos(a+b)"
        val cosineDifference get() = "\\cos(a-b)"
        val tangentSum get() = "\\tan(a+b)"
        val tangentDifference get() = "\\tan(a-b)"

        // Double and Half Angle Identities
        val sineDoubleAngle get() = "\\sin(2a)"
        val cosineDoubleAngle get() = "\\cos(2a)"
        val tangentDoubleAngle get() = "\\tan(2a)"
        val sineHalfAngle get() = "\\sin\\left(\\frac{a}{2}\\right)"
        val cosineHalfAngle get() = "\\cos\\left(\\frac{a}{2}\\right)"
        val tangentHalfAngle get() = "\\tan\\left(\\frac{a}{2}\\right)"

        // Product-to-Sum and Sum-to-Product Formulas
        val sineProduct get() = "\\sin(a)\\sin(b)"
        val cosineProduct get() = "\\cos(a)\\cos(b)"
        val cosineSineProduct get() = "\\cos(a)\\sin(b)"

        // Law of Sines and Cosines
        val sideA get() = "a"
        val sideB get() = "b"
        val sideC get() = "c"
        val angleA get() = "A"
        val angleB get() = "B"
        val angleC get() = "C"
    }

    interface AdvancedTrigonometryFormulas : AdvancedTrigonometrySymbols {
        // Sum and Difference Identities
        val sineSumFormula: String
            get() = "$sineSum = \\sin(a)\\cos(b) + \\cos(a)\\sin(b)"

        val cosineSumFormula: String
            get() = "$cosineSum = \\cos(a)\\cos(b) - \\sin(a)\\sin(b)"

        val tangentSumFormula: String
            get() = "$tangentSum = \\frac{\\tan(a) + \\tan(b)}{1 - \\tan(a)\\tan(b)}"

        // Double and Half Angle Identities
        val sineDoubleAngleFormula: String
            get() = "$sineDoubleAngle = 2\\sin(a)\\cos(a)"

        val cosineDoubleAngleFormula: String
            get() = "$cosineDoubleAngle = \\cos^2(a) - \\sin^2(a)"

        val tangentDoubleAngleFormula: String
            get() = "$tangentDoubleAngle = \\frac{2\\tan(a)}{1-\\tan^2(a)}"

        // Product-to-Sum Formulas
        val sineProductToSum: String
            get() = "$sineProduct = \\frac{1}{2}[\\cos(a-b) - \\cos(a+b)]"

        val cosineProductToSum: String
            get() = "$cosineProduct = \\frac{1}{2}[\\cos(a-b) + \\cos(a+b)]"

        val cosineSineProductToSum: String
            get() = "$cosineSineProduct = \\frac{1}{2}[\\sin(a+b) + \\sin(a-b)]"

        // Law of Sines
        val lawOfSines: String
            get() = "\\frac{$sideA}{\\sin($angleA)} = \\frac{$sideB}{\\sin($angleB)} = \\frac{$sideC}{\\sin($angleC)}"

        // Law of Cosines
        val lawOfCosinesForSideA: String
            get() = "$sideA^2 = $sideB^2 + $sideC^2 - 2$sideB$sideC\\cos($angleA)"
    }


}