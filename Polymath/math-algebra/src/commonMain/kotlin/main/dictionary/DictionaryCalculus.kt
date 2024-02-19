package main.dictionary

interface DictionaryCalculus {
    interface CalculusSymbols {
        val symFunctionF get() = "f(x)"
        val symFunctionG get() = "g(x)"
        val symDerivativeF get() = "f'(x)"
        val symDerivativeG get() = "g'(x)"
        val symIntegralF get() = "\\int f(x) dx"
        val symIntegralG get() = "\\int g(x) dx"
        val symLimit get() = "\\lim_{x \\to a}"
    }

    interface CalculusEquations : CalculusSymbols {
        val differentiationProductRule: String
            get() = "(f(x)g(x))' = f'(x)g(x) + f(x)g'(x)"
        val differentiationQuotientRule: String
            get() = "\\left(\\frac{f(x)}{g(x)}\\right)' = \\frac{f'(x)g(x) - f(x)g'(x)}{g(x)^2}"
        val integrationByParts: String
            get() = "\\int u dv = uv - \\int v du"
        val lHopitalsRule: String
            get() = "If \\lim_{x \\to a} f(x) = 0 and \\lim_{x \\to a} g(x) = 0 or \\infty, then \\lim_{x \\to a} \\frac{f(x)}{g(x)} = \\lim_{x \\to a} \\frac{f'(x)}{g'(x)}"
    }

    interface DifferentialCalculusSymbols {
        val symSecondDerivative get() = "f''(x)"
        val symNthDerivative get() = "f^{(n)}(x)"
    }

    interface DifferentialCalculusEquations : DifferentialCalculusSymbols, CalculusSymbols {
        val chainRule: String
            get() = "(f(g(x)))' = f'(g(x)) g'(x)"
        val powerRule: String
            get() = "d/dx [x^n] = nx^{n-1}"
        val exponentialRule: String
            get() = "d/dx [e^x] = e^x"
        val logRule: String
            get() = "d/dx [\\ln(x)] = 1/x"
    }

    interface IntegralCalculusSymbols {
        val symDefiniteIntegral get() = "\\int_a^b"
        val symIndefiniteIntegral get() = "\\int"
    }

    interface IntegralCalculusEquations : IntegralCalculusSymbols, CalculusSymbols {
        val integrationPowerRule: String
            get() = "\\int x^n dx = (x^{n+1})/(n+1) + C, n \\neq -1"
        val exponentialIntegration: String
            get() = "\\int e^x dx = e^x + C"
        val logIntegration: String
            get() = "\\int \\frac{1}{x} dx = \\ln(|x|) + C"
        val trigIntegration: String
            get() = "\\int \\sin(x) dx = -\\cos(x) + C and \\int \\cos(x) dx = \\sin(x) + C"
    }

//    interface CalculusTheorems : DifferentialCalculusSymbols, IntegralCalculusSymbols {
//        val meanValueTheorem: String
//            get() = "If f is continuous on [a, b] and differentiable on (a, b), then there exists c in (a, b) such that f'(c) = (f(b) - f(a)) / (b - a)"
//        val fundamentalTheoremOfCalculus: String
//            get() = "If f is continuous on [a, b], then F(x) = \\int_a^x f(t) dt has a derivative at every x in (a, b) and F'(x) = f(x)"
//    }
//
//
//    interface MultivariableCalculusSymbols : CalculusSymbols {
//        val symFunctionFXY get() = "f(x, y)"
//        val symPartialX get() = "\\partial x"
//        val symPartialY get() = "\\partial y"
//        val symGradient get() = "\\nabla"
//    }
//
//    interface MultivariableCalculusEquations : MultivariableCalculusSymbols {
//        val partialDerivative: String
//            get() = "\\frac{\\partial f}{\\partial x}, \\frac{\\partial f}{\\partial y}"
//        val gradientVector: String
//            get() = "\\nabla f = <\\frac{\\partial f}{\\partial x}, \\frac{\\partial f}{\\partial y}>"
//        val divergence: String
//            get() = "\\nabla \\cdot \\vec{F} = \\partial P/\\partial x + \\partial Q/\\partial y"
//        val curl: String
//            get() = "\\nabla \\times \\vec{F}"
//    }
//
//    interface CalculusTheorems : DifferentialCalculusSymbols, IntegralCalculusSymbols {
//        val meanValueTheorem: String
//            get() = "\\exists c \\in (a, b) : f'(c) = \\frac{f(b) - f(a)}{b - a}"
//        val fundamentalTheoremOfCalculus: String
//            get() = "\\forall x \\in (a, b) : \\frac{d}{dx} \\int_a^x f(t) dt = f(x)"
//    }
//
//
//    interface MultivariableCalculusTheorems : MultivariableCalculusSymbols {
//        val stokesTheorem: String
//            get() = "\\oint_C F \\cdot dr = \\iint_S \\nabla \\times F \\cdot dS"
//        val divergenceTheorem: String
//            get() = "\\iiint_V (\\nabla \\cdot F) dV = \\oint_S F \\cdot dS"
//    }
//
//
//    interface MultivariableCalculusTheorems : MultivariableCalculusSymbols {
//        val stokesTheorem: String
//            get() = "\\oint_C F \\cdot dr = \\iint_S \\nabla \\times F \\cdot dS"
//        val divergenceTheorem: String
//            get() = "\\iiint_V (\\nabla \\cdot F) dV = \\oint_S F \\cdot dS"
//    }
//
//
//    interface ComplexAnalysisTheorems : ComplexAnalysisSymbols {
//        val cauchysIntegralTheorem: String
//            get() = "If f is analytic in a simply connected domain D, then \\oint_C f(z)dz = 0 for any closed contour C within D."
//        val liouvillesTheorem: String
//            get() = "Every bounded entire function is constant."
//
//        val cauchysIntegralTheorem: String
//            get() = "f \\text{ analytic in } D \\implies \\oint_C f(z)dz = 0"
//        val liouvillesTheorem: String
//            get() = "f \\text{ bounded and entire } \\implies f = constant"
//    }
//
//
//    interface DiffEqEquations : Symbols.DiffEqSymbols {
//        val secondOrderHomogeneous: String
//            get() = "y'' + p(x)y' + q(x)y = 0"
//        val generalSolution: String
//            get() = "y(x) = c_1 y_1(x) + c_2 y_2(x)"
//    }
//
//    interface DifferentialEquationSymbols {
//        // Basic differential symbols
//        val d get() = "\\frac{d}{dt}"  // Ordinary differential
//        val partial get() = "\\partial"  // Partial differential
//
//        // Common functions and variables
//        val y get() = "y"
//        val x get() = "x"
//        val f get() = "f"
//        val g get() = "g"
//
//        // Higher order derivatives
//        val d2y get() = "\\frac{d^2y}{dt^2}"  // Second order ordinary differential
//        val partial2x get() = "\\partial^2_x"  // Second order partial differential w.r.t x
//    }
//
//    interface ODEFormulas :DifferentialEquationSymbols{
//        // First order ordinary differential equation
//        val firstOrderODE: String
//            get() = "$d $y = $f($x, $y)"
//
//        // Second order ordinary differential equation
//        val secondOrderODE: String
//            get() = "$d2y + $g($x) $d $y = $f($x, $y)"
//
//        // Homogeneous ordinary differential equation
//        val homogeneousODE: String
//            get() = "$d $y = $g($x) $y"
//    }
//
//    interface PDEFormulas :DifferentialEquationSymbols{
//        // First order partial differential equation
//        val firstOrderPDE: String
//            get() = "$partial $y / $partial $x = $f($x, $y)"
//
//        // Second order partial differential equation (e.g., wave equation)
//        val waveEquation: String
//            get() = "$partial2x $y - c^2 $partial^2 $y / $partial t^2 = 0"
//    }
//
//    interface DifferentialEquationSymbols {
//        val d get() = "\\frac{d}{dt}"  // Ordinary differential
//        val partial get() = "\\partial"  // Partial differential
//
//        val y get() = "y"
//        val x get() = "x"
//        val f get() = "f"
//        val g get() = "g"
//        val c get() = "c"  // Constant
//
//        val d2y get() = "\\frac{d^2y}{dt^2}"  // Second order ordinary differential
//        val partial2x get() = "\\partial^2_x"  // Second order partial differential w.r.t x
//    }
//
//    interface ODEFormulas {
//        val firstOrderODE: String
//            get() = "$d $y = $f($x, $y)"
//
//        val secondOrderODE: String
//            get() = "$d2y + $g($x) $d $y = $f($x, $y)"
//
//        val homogeneousODE: String
//            get() = "$d $y = $g($x) $y"
//
//        val bernoulliODE: String
//            get() = "$d $y + P(x) y = Q(x) y^n"
//
//        val exactODE: String
//            get() = "M(x,y)dx + N(x,y)dy = 0"
//
//        val separableODE: String
//            get() = "h(y) dy = g(x) dx"
//    }
//
//    interface PDEFormulas {
//        val firstOrderPDE: String
//            get() = "$partial $y / $partial $x = $f($x, $y, $partial $y)"
//
//        val waveEquation: String
//            get() = "$partial2x $y - $c^2 $partial^2 $y / $partial t^2 = 0"
//
//        val heatEquation: String
//            get() = "$partial $y / $partial t = $c^2 $partial2x $y"
//
//        val laplaceEquation: String
//            get() = "$partial2x $y + $partial^2 $y / $partial y^2 = 0"
//    }
//
//    interface SystemsOfODEs {
//        val linearSystem: String
//            get() = "X' = AX + B"
//
//        val nonlinearSystem: String
//            get() = "X' = f(X, t)"
//    }
//
//    interface BoundaryValueProblems {
//        val secondOrderODEWithBoundary: String
//            get() = "$d2y + $g($x) $d $y = $f($x, $y), y(a) = y_a, y(b) = y_b"
//    }
//
//
//
//    interface ComplexAnalysisSymbols {
//        val symComplexFunction get() = "f(z)"
//        val symComplexVariable get() = "z"
//        val symComplexConjugate get() = "\\bar{z}"
//    }
//
//    interface ComplexAnalysisEquations : ComplexAnalysisSymbols {
//        val cauchyRiemann: String
//            get() = "\\frac{\\partial u}{\\partial x} = \\frac{\\partial v}{\\partial y}, \\frac{\\partial u}{\\partial y} = -\\frac{\\partial v}{\\partial x}"
//        val residueTheorem: String
//            get() = "\\oint_C f(z)dz = 2\\pi i \\sum Res(f, a_i)"
//    }
//
//
//    interface DiffEqSymbols {
//        val symDiffEq get() = "y'' + p(x)y' + q(x)y = g(x)"
//        val symHomogeneous get() = "y_h"
//        val symParticular get() = "y_p"
//    }
//
//
//    interface DiffEqTheorems : DiffEqSymbols {
//        val existenceUniquenessTheorem: String
//            get() = "If f and \\partial f/\\partial y are continuous in a region containing the point (x_0, y_0), then there exists a unique solution y(x) of the differential equation that passes through the point (x_0, y_0)"
//        val existenceUniquenessTheorem: String
//            get() = "\\forall (x_0, y_0) : \\exists! y(x)"
//    }
//
//
//    interface AnalysisSymbols {
//        // Limits
//        val limit get() = "\\lim"
//        val approachFromLeft get() = "^{-}"
//        val approachFromRight get() = "^{+}"
//        val infinity get() = "\\infty"
//
//        // Differentiation
//        val derivative get() = "\\frac{d}{dx}"
//        val partialDerivative get() = "\\frac{\\partial}{\\partial x}"
//
//        // Integration
//        val integral get() = "\\int"
//        val differential get() = "dx"
//
//        // Series
//        val summation get() = "\\sum"
//        val fromNToOne get() = "n=1"
//        val toInfinity get() = "^{\\infty}"
//
//        // Common functions
//        val functionF get() = "f(x)"
//        val functionG get() = "g(x)"
//    }
//
//    interface AnalysisFormulas : AnalysisSymbols {
//        // Limits
//        val generalLimitFormula: String
//            get() = "$limit_{x \\to a} $functionF = L"
//
//        val limitAtInfinity: String
//            get() = "$limit_{x \\to $infinity} $functionF"
//
//        // Derivative
//        val firstDerivative: String
//            get() = "$derivative $functionF"
//
//        val partialDerivativeWRTx: String
//            get() = "$partialDerivative $functionF"
//
//        // Integration
//        val definiteIntegral: String
//            get() = "$integral_a^b $functionF $differential"
//
//        val indefiniteIntegral: String
//            get() = "$integral $functionF $differential"
//
//        // Series
//        val infiniteSeries: String
//            get() = "$summation_{$fromNToOne}^{$toInfinity} a_n"
//
//        // Fundamental theorem of calculus
//        val fundamentalTheorem: String
//            get() = "$integral_a^b $derivative $functionF $differential = f(b) - f(a)"
//
//        // Mean value theorem
//        val meanValueTheorem: String
//            get() = "f'(c) = \\frac{f(b) - f(a)}{b - a} \\text{ for some } c \\in (a,b)"
//    }


}

