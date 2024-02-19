package dev.patbeagan.math.base


object AggregateOperators {
    val symInt get() = "\\int"
    val symOint get() = "\\oint"
    val symSum get() = "\\sum"
    val symProd get() = "\\prod"
}


// 3. Miscellaneous Symbols
interface MiscellaneousSymbols {
    val symInfinity get() = "\\infty"
    val symPartial get() = "\\partial"
    val symNabla get() = "\\nabla"
    val symSum get() = "\\sum"
    val symProduct get() = "\\prod"
    val symDelta get() = "\\Delta"
    val symLambda get() = "\\lambda"
}

// Chemistry Symbols
interface ChemistrySymbols {
    val symH2O get() = "H_2O"
    val symCO2 get() = "CO_2"
    val symElectron get() = "e^-"
    val symProton get() = "p^+"
    val symNeutron get() = "n^0"
    val symArrowReaction get() = "\\rightarrow"
    val symEquilibrium get() = "\\rightleftharpoons"
}

// Extended Chemistry Symbols
interface ExtendedChemistrySymbols {
    val symAcid get() = "H^+"
    val symBase get() = "OH^-"
    val symCatalyst get() = "\\text{cat.}"
    val symGas get() = "(g)"
    val symLiquid get() = "(l)"
    val symSolid get() = "(s)"
    val symAqueous get() = "(aq)"
}

// Extended Physics Symbols
interface ExtendedPhysicsSymbols {
    val symAlpha get() = "\\alpha"
    val symBeta get() = "\\beta"
    val symGamma get() = "\\gamma"
    val symOmega get() = "\\omega"
    val symPhi get() = "\\phi"
    val symTheta get() = "\\theta"
    val symSigma get() = "\\sigma"
    val symSpeedOfLight get() = "c"
    val symGravitationalConstant get() = "G"
    val symPlancksConstant get() = "h"
    val symReducedPlancksConstant get() = "\\hbar"
    val symMagneticField get() = "B"
    val symElectricField get() = "E"
    val symVoltage get() = "V"
    val symResistance get() = "R"
    val symCapacitance get() = "C"
    val symInductance get() = "L"
    val symCharge get() = "Q"
    val symForce get() = "F"
    val symEnergy get() = "E"
    val symWork get() = "W"
    val symPower get() = "P"
}

// Thermodynamics Symbols
interface ThermodynamicsSymbols {
    val symEntropy get() = "S"
    val symEnthalpy get() = "H"
    val symGibbsFreeEnergy get() = "G"
    val symHelmholtzFreeEnergy get() = "A"
    val symIsothermalCompressibility get() = "\\beta"
    val symCoefficientOfThermalExpansion get() = "\\alpha"
}

// Thermodynamics Equations
interface ThermodynamicsEquations : ThermodynamicsSymbols {
    val firstLawThermodynamics: String
        get() = "\\Delta U = q + w"
    val gibbsHelmholtzEquation: String
        get() = "\\Delta $symGibbsFreeEnergy = \\Delta $symEnthalpy - T \\Delta $symEntropy"
}


interface OrganicChemistrySymbols {
    val symNucleophile get() = "Nu"
    val symElectrophile get() = "E"
}

interface OrganicChemistryEquations : OrganicChemistrySymbols {
    val nucleophilicSubstitution: String
        get() = "$symNucleophile + $symElectrophile -> [Intermediate] -> Product"
}

interface EconomicsSymbols {
    val symSupply get() = "S"
    val symDemand get() = "D"
    val symPrice get() = "P"
    val symQuantity get() = "Q"
    val symIncome get() = "Y"
    val symConsumption get() = "C"
}

interface EconomicsEquations : EconomicsSymbols, GenericSymbols {
    val lawOfDemand: String
        get() = "Price $symArrowDown Quantity $symArrowDown"
    val lawOfSupply: String
        get() = "Price $symArrowUp Quantity $symArrowUp"
    val consumptionFunction: String
        get() = "$symConsumption = a + b $symIncome"
}


interface NumberTheorySymbols {
    val symPrime get() = "p"
    val symRelativelyPrime get() = "\\gcd(a, b) = 1"
}

interface NumberTheoryTheorems : NumberTheorySymbols {
    val fermatsLittleTheorem: String
        get() = "a^{p-1} \\equiv 1 \\mod p, \\forall a \\text{ where } p \\text{ is prime}"
    val eulerTotientTheorem: String
        get() = "a^{\\varphi(n)} \\equiv 1 \\mod n, \\forall a \\text{ s.t. } \\gcd(a, n) = 1"
}

interface SetTheorySymbols {
    val symUnion get() = "\\cup"
    val symIntersection get() = "\\cap"
    val symSubset get() = "\\subset"
}

interface SetTheoryTheorems : SetTheorySymbols {
    val deMorgansLaw: String
        get() = "(A \\cup B)^c = A^c \\cap B^c \\text{ and } (A \\cap B)^c = A^c \\cup B^c"
    val distributiveLaws: String
        get() = "A \\cap (B \\cup C) = (A \\cap B) \\cup (A \\cap C) \\text{ and } A \\cup (B \\cap C) = (A \\cup B) \\cap (A \\cup C)"
}

interface TopologySymbols {
    val symOpenSet get() = "U"
    val symClosedSet get() = "C^c"
    val symLimitPoint get() = "x_0"
}

interface TopologyTheorems : TopologySymbols {
    val bolzanoWeierstrassTheorem: String
        get() = "\\forall bounded sequences \\text{ in } \\mathbb{R}^n, \\exists \\text{ a convergent subsequence.}"
    val heineBorelTheorem: String
        get() = "\\text{A subset } E \\text{ of } \\mathbb{R}^n \\text{ is compact iff it is closed and bounded.}"
}


//    1. **Function:** A relation between two sets that associates each element of the first set with exactly one element of the second set. Often denoted as \(f: X \to Y\), where \(X\) is the domain and \(Y\) is the codomain.
//
//    2. **Limit:** Describes the behavior of a function as its input (or variable) approaches a certain value. For instance, the limit of \(f(x)\) as \(x\) approaches \(a\) is written as \(\lim_{x \to a} f(x)\).
//
//    3. **Derivative:** Measures how a function changes as its input changes. The derivative of \(f(x)\) is often denoted \(f'(x)\) or \(\frac{df}{dx}\).
//
//    4. **Integral:** The reverse operation of differentiation. It can be thought of as the area under a curve, and is divided into definite and indefinite integrals.
//
//    5. **Matrix:** A rectangular array of numbers, symbols, or expressions. Used in linear algebra to represent linear maps and transformations.
//
//    6. **Vector:** A quantity having direction and magnitude. In mathematics, especially in linear algebra, it's an element of a vector space.
//
//    7. **Set:** A collection of distinct objects, considered as an object in its own right. Sets are usually denoted with curly braces. For example, \(A = \{1, 2, 3\}\).
//
//    8. **Sequence:** An ordered list of elements. Can be finite or infinite. An example is the arithmetic sequence: \(2, 4, 6, 8, \dots\).
//
//    9. **Series:** The sum of the terms of a sequence. A common example is the geometric series: \(1 + \frac{1}{2} + \frac{1}{4} + \frac{1}{8} + \dots\).
//
//    10. **Equation:** A statement that asserts the equality of two expressions, denoted using the symbol \(=\).
//
//    11. **Inequality:** A relation that holds between two values when they are different. Common symbols include \(<, >, \leq, \geq\).
//
//    12. **Polynomial:** An expression consisting of variables and coefficients. For instance, \(f(x) = 2x^3 - 3x^2 + x - 5\).
//
//    13. **Logarithm:** The inverse operation to exponentiation. The logarithm of a number is the exponent by which another fixed value (the base) must be raised to produce that number.
//
//    14. **Complex Number:** A number that can be expressed in the form \(a + bi\), where \(a\) and \(b\) are real numbers, and \(i\) is the imaginary unit.
//
//    15. **Prime Number:** A natural number greater than 1 that is not the product of two smaller natural numbers.
//
//    16. **Parabola:** A U-shaped symmetrical curve. It's the graph of a quadratic function.
//
//    17. **Hyperbola:** A type of smooth curve, defined by its geometric properties or by equations for which it is the solution set.
//
//    18. **Ellipse:** A curve on a plane surrounding two focal points such that the sum of the distances to the two focal points is constant for every point on the curve.
//
//    19. **Asymptote:** A line that a curve approaches as it heads towards infinity.
//
//    20. **Topology:** The branch of mathematics involving concepts of space, continuity, and convergence. Deals with properties that are preserved under continuous deformations.
//
//    Certainly! Here's how these mathematical constructs can be represented using LaTeX notation:
//
//    1. **Function:**
//    \[ f: X \to Y \]
//
//    2. **Limit:**
//    \[ \lim_{x \to a} f(x) \]
//
//    3. **Derivative:**
//    \[ f'(x) \quad \text{or} \quad \frac{df}{dx} \]
//
//    4. **Integral:**
//    \[ \int f(x) \, dx \quad \text{(indefinite integral)} \]
//    \[ \int_a^b f(x) \, dx \quad \text{(definite integral from } a \text{ to } b) \]
//
//    5. **Matrix:**
//    \[
//    \begin{bmatrix}
//    a_{11} & a_{12} \\
//    a_{21} & a_{22} \\
//    \end{bmatrix}
//    \]
//
//    6. **Vector:**
//    \[ \begin{bmatrix} v_1 \\ v_2 \\ \vdots \\ v_n \end{bmatrix} \]
//
//    7. **Set:**
//    \[ A = \{1, 2, 3\} \]
//
//    8. **Sequence:**
//    \[ a_1, a_2, a_3, \dots \]
//
//    9. **Series:**
//    \[ \sum_{n=1}^{\infty} a_n \]
//
//    10. **Equation:**
//    \[ a + b = c \]
//
//    11. **Inequality:**
//    \[ a < b \quad \text{or} \quad a \leq b \]
//
//    12. **Polynomial:**
//    \[ f(x) = a_nx^n + a_{n-1}x^{n-1} + \dots + a_1x + a_0 \]
//
//    13. **Logarithm:**
//    \[ \log_b(a) \]
//
//    14. **Complex Number:**
//    \[ a + bi \]
//
//    15. **Prime Number:**
//    \[ p \quad \text{(where } p \text{ is prime)} \]
//
//    16. **Parabola:**
//    \[ y = ax^2 + bx + c \]
//
//    17. **Hyperbola:**
//    \[ \frac{x^2}{a^2} - \frac{y^2}{b^2} = 1 \quad \text{or} \quad \frac{y^2}{b^2} - \frac{x^2}{a^2} = 1 \]
//
//    18. **Ellipse:**
//    \[ \frac{x^2}{a^2} + \frac{y^2}{b^2} = 1 \]
//
//    19. **Asymptote:**
//    \[ y = mx + b \quad \text{(representing a linear asymptote)} \]
//
//    20. **Topology:** (Topology is a field and does not have a specific LaTeX notation. However, open sets in topology can be denoted as):
//    \[ U \quad \text{(where } U \text{ is an open set)} \]
//
//
//
//    1. **Mathematics:**
//
//    - **Pythagorean theorem:** \(a^2 + b^2 = c^2\)
//
//    - **Quadratic formula:** \(x = \frac{-b \pm \sqrt{b^2-4ac}}{2a}\)
//
//    - **Euler's identity:** \(e^{i\pi} + 1 = 0\)
//
//    - **Binomial theorem:** \((x+y)^n = \sum_{k=0}^{n} \binom{n}{k} x^{n-k} y^k\)
//
//    2. **Physics:**
//
//    - **Newton's second law:** \(F = ma\)
//
//    - **Einstein's theory of relativity:** \(E=mc^2\)
//
//    - **Schrodinger equation (quantum mechanics):** \(i\hbar \frac{\partial}{\partial t} \Psi = \hat{H} \Psi\)
//
//    - **Maxwell's equations (electrodynamics):** A set of four differential equations that describe how electric and magnetic fields interact.
//
//    3. **Chemistry:**
//
//    - **Ideal gas law:** \(PV = nRT\)
//
//    - **Arrhenius equation (reaction rate):** \(k = A e^{-\frac{E_a}{RT}}\)
//
//    4. **Biology:**
//
//    - **Hardy-Weinberg equilibrium:** \(p^2 + 2pq + q^2 = 1\)
//
//    5. **Economics/Finance:**
//
//    - **Compound interest:** \(A = P(1 + \frac{r}{n})^{nt}\)
//
//    6. **Astronomy:**
//
//    - **Drake equation:** An equation to estimate the number of active, communicative extraterrestrial civilizations in the Milky Way galaxy.
//
//    7. **Computer Science:**
//
//    - **Shannon's entropy (information theory):** \(H(X) = -\sum p(x) \log p(x)\)
//
//    - **Big O notation (algorithm efficiency):** Describes the worst-case, or upper bound, of an algorithm's running time.
//
//    8. **Engineering:**
//
//    - **Fourier transform:** Transforms a function of time (a signal) into a function of frequency.
//
//    - **Navier–Stokes equations (fluid dynamics):** Describe the motion of viscous fluid substances.
//    Of course! Here are the mentioned equations represented in LaTeX notation:
//
//    1. **Mathematics:**
//
//    - **Pythagorean theorem:**
//    \[ a^2 + b^2 = c^2 \]
//
//    - **Quadratic formula:**
//    \[ x = \frac{-b \pm \sqrt{b^2-4ac}}{2a} \]
//
//    - **Euler's identity:**
//    \[ e^{i\pi} + 1 = 0 \]
//
//    - **Binomial theorem:**
//    \[ (x+y)^n = \sum_{k=0}^{n} \binom{n}{k} x^{n-k} y^k \]
//
//    2. **Physics:**
//
//    - **Newton's second law:**
//    \[ F = ma \]
//
//    - **Einstein's theory of relativity:**
//    \[ E=mc^2 \]
//
//    - **Schrodinger equation:**
//    \[ i\hbar \frac{\partial}{\partial t} \Psi = \hat{H} \Psi \]
//
//    - **Maxwell's equations:** (For brevity, I'll just show one of the four equations, the Gauss's law for electricity)
//    \[ \nabla \cdot \mathbf{E} = \frac{\rho}{\varepsilon_0} \]
//
//    3. **Chemistry:**
//
//    - **Ideal gas law:**
//    \[ PV = nRT \]
//
//    - **Arrhenius equation:**
//    \[ k = A e^{-\frac{E_a}{RT}} \]
//
//    4. **Biology:**
//
//    - **Hardy-Weinberg equilibrium:**
//    \[ p^2 + 2pq + q^2 = 1 \]
//
//    5. **Economics/Finance:**
//
//    - **Compound interest:**
//    \[ A = P \left(1 + \frac{r}{n}\right)^{nt} \]
//
//    6. **Astronomy:**
//    (The Drake equation is complex and has multiple terms, so I'll just represent its general form.)
//
//    - **Drake equation:**
//    \[ N = R^* \times f_p \times n_e \times f_l \times f_i \times f_c \times L \]
//
//    7. **Computer Science:**
//
//    - **Shannon's entropy:**
//    \[ H(X) = -\sum p(x) \log p(x) \]
//
//    - **Big O notation:**
//    (A generic representation, since specific functions vary based on the context)
//    \[ O(f(n)) \]
//
//    8. **Engineering:**
//
//    - **Fourier transform:**
//    (For a continuous function, its general representation is)
//    \[ F(\omega) = \int_{-\infty}^{\infty} f(t) e^{-i\omega t} \, dt \]
//
//    - **Navier–Stokes equation:**
//    (Again, this is a complex set of equations, so I'll represent a general form for incompressible flow)
//    \[ \rho \left( \frac{\partial \mathbf{v}}{\partial t} + \mathbf{v} \cdot \nabla \mathbf{v} \right) = -\nabla p + \mu \nabla^2 \mathbf{v} \]
//
//    These LaTeX notations can be compiled in any LaTeX processor to view the beautifully formatted mathematical equations.
//
//    Absolutely! Here are some of the most frequently used equations in statistics represented in LaTeX:
//
//    1. **Mean (Arithmetic Average):**
//    \[ \bar{X} = \frac{1}{n} \sum_{i=1}^{n} x_i \]
//
//    2. **Population Variance:**
//    \[ \sigma^2 = \frac{1}{N} \sum_{i=1}^{N} (x_i - \mu)^2 \]
//
//    3. **Sample Variance:**
//    \[ s^2 = \frac{1}{n-1} \sum_{i=1}^{n} (x_i - \bar{x})^2 \]
//
//    4. **Population Standard Deviation:**
//    \[ \sigma = \sqrt{\frac{1}{N} \sum_{i=1}^{N} (x_i - \mu)^2} \]
//
//    5. **Sample Standard Deviation:**
//    \[ s = \sqrt{\frac{1}{n-1} \sum_{i=1}^{n} (x_i - \bar{x})^2} \]
//
//    6. **Coefficient of Variation (for sample data):**
//    \[ CV = \frac{s}{\bar{x}} \times 100\% \]
//
//    7. **Binomial Probability:**
//    \[ P(X=k) = \binom{n}{k} p^k (1-p)^{n-k} \]
//
//    8. **Normal Distribution Probability Density Function:**
//    \[ f(x|\mu, \sigma^2) = \frac{1}{\sqrt{2\pi\sigma^2}} e^{-\frac{(x-\mu)^2}{2\sigma^2}} \]
//
//    9. **Correlation Coefficient (Pearson's r for sample data):**
//    \[ r = \frac{\sum_{i=1}^{n} (x_i - \bar{x})(y_i - \bar{y})}{\sqrt{\sum_{i=1}^{n} (x_i - \bar{x})^2 \sum_{i=1}^{n} (y_i - \bar{y})^2}} \]
//
//    10. **Linear Regression (Least Squares Estimation):**
//    \[ y = \beta_0 + \beta_1 x \]
//    Where,
//    \[ \beta_1 = \frac{\sum_{i=1}^{n} (x_i - \bar{x})(y_i - \bar{y})}{\sum_{i=1}^{n} (x_i - \bar{x})^2} \]
//    \[ \beta_0 = \bar{y} - \beta_1 \bar{x} \]
//
//    11. **Chi-Squared Statistic:**
//    \[ \chi^2 = \sum \frac{(O_i - E_i)^2}{E_i} \]
//    Where \(O_i\) are the observed frequencies and \(E_i\) are the expected frequencies.
//
//    12. **T-statistic (for sample mean with known population variance):**
//    \[ t = \frac{\bar{x} - \mu}{\sigma / \sqrt{n}} \]
//
//    13. **Z-score:**
//    \[ z = \frac{x - \mu}{\sigma} \]
//
//    14. **Bayes' Theorem:**
//    \[ P(A|B) = \frac{P(B|A) \times P(A)}{P(B)} \]
//
//    These are just some of the many foundational formulas used in statistics. The specific formulae one might encounter will vary based on the topic and depth of statistical analysis being conducted.
