package dev.patbeagan.math.base

interface GenericSymbols : GreekLetters, RelationOperators, BinaryOperators

interface GreekLetters {
    val symA get() = "A"
    val symB get() = "b"
    val symC get() = "c"
    val symD get() = "d"
    val symE get() = "e"
    val symF get() = "f"
    val symG get() = "g"
    val symH get() = "h"
    val symI get() = "i"
    val symJ get() = "j"
    val symK get() = "k"
    val symL get() = "l"
    val symM get() = "m"
    val symN get() = "n"
    val symO get() = "o"
    val symP get() = "p"
    val symQ get() = "q"
    val symR get() = "r"
    val symS get() = "s"
    val symT get() = "t"
    val symU get() = "u"
    val symV get() = "v"
    val symW get() = "w"
    val symX get() = "x"
    val symY get() = "y"
    val symZ get() = "z"
    val symAlpha get() = "\\alpha"
    val symBeta get() = "\\beta"
    val symGamma get() = "\\gamma"
    val symRho get() = "\\rho"
    val symSigma get() = "\\sigma"
    val symDelta get() = "\\delta"
    val symEpsilon get() = "\\epsilon"
    val symZeta get() = "\\zeta"
    val symEta get() = "\\eta"
    val symTheta get() = "\\theta"
    val symIota get() = "\\iota"
    val symKappa get() = "\\kappa"
    val symLambda get() = "\\lambda"
    val symMu get() = "\\mu"
    val symNu get() = "\\nu"
    val symXi get() = "\\xi"
    val symOmicron get() = "o" // Omicron is same as the Latin letter 'o'
    val symPi get() = "\\pi"
    val symTau get() = "\\tau"
    val symUpsilon get() = "\\upsilon"
    val symPhi get() = "\\phi"
    val symChi get() = "\\chi"
    val symPsi get() = "\\psi"
    val symOmega get() = "\\omega"
}

interface RelationOperators {
    val symGreaterThan get() = ">"
    val symLessThan get() = "<"
    val symSubset get() = "\\subset"
    val symSupset get() = "\\supset"
    val symSubseteq get() = "\\subseteq"
    val symSupseteq get() = "\\supseteq"
}

interface BinaryOperators {
    val symTimes get() = "\\times"
    val symMultiply get() = symTimes
    val symPlus get() = "+"
    val symAdd get() = symPlus
    val symMinus get() = "-"
    val symSubtract get() = symMinus
    val symDiv get() = "\\div"
    val symDivide get() = symDiv

    val symEqual get() = "="
    val symNotEqual get() = "\\neq"

    val symArrowUp get() = "\\uparrow"
    val symArrowDown get() = "\\downarrow"
    val symArrowLeft get() = "\\leftarrow"
    val symArrowRight get() = "\\rightarrow"

    val symOtimes get() = "\\otimes"
    val symOplus get() = "\\oplus"
    val symCup get() = "\\cup"
    val symCap get() = "\\cap"
    val symCdot get() = "\\cdot"
    val symWedge get() = "\\wedge"
    val symVee get() = "\\vee"
    val symSetMinus get() = "\\setminus"
    val symStar get() = "\\star"
    val symDiamond get() = "\\diamond"
    val symBox get() = "\\Box"
    val symCircle get() = "\\circ"
    val symTriangle get() = "\\triangle"
}
