# PolyMath

A comprehensive mathematical DSL (Domain Specific Language) for Kotlin that lets you write mathematical expressions naturally and evaluate them or render them as LaTeX. PolyMath supports multiple fields of mathematics with an intuitive, type-safe API.

## 🚀 Quick Start

```kotlin
import main.dsl.mathnum.*
import main.dsl.expressions.ScalarAlgebra.*

// Basic arithmetic
val result = (mathNum(5) + mathNum(3)) * mathNum(2)
println(result.evaluate()) // 16.0
println(result.toLatex())  // "5 + 3 \cdot 2"

// Variables and equations
val x = Variable("x").setTo(5.0)
val equation = x.squared() + mathNum(3) * x + mathNum(2)
println(equation.evaluate()) // 42.0
println(equation.toLatex())  // "x^{2} + 3 \cdot x + 2"
```

## 📚 Features

### 🔢 Basic Arithmetic
- **Order of Operations**: Automatic handling of PEMDAS/BODMAS
- **Variables**: Dynamic and static variable support
- **Rational Numbers**: Exact arithmetic with fractions
- **Equations**: Create and manipulate mathematical equations

```kotlin
// Order of operations
val expr = mathNum(5) * mathNum(3) + mathNum(2) - mathNum(1)
println(expr.evaluate()) // 16.0
println(expr.toLatex())  // "5 \cdot 3 + 2 - 1"

// Rational arithmetic
val fraction = mathNum(1) / mathNum(3) + mathNum(1) / mathNum(4)
println(fraction.toLatex()) // "\frac{1}{3} + \frac{1}{4}"
println(fraction.evaluate()) // 0.5833333333333333
```

### 📐 Algebraic Expressions
- **Polynomial Operations**: Addition, multiplication, exponentiation
- **Simplification**: Automatic expression simplification
- **Quadratic Formula**: Built-in quadratic equation solver
- **Binomial Theorem**: Support for binomial expansions

```kotlin
// Quadratic formula
val a = Variable("a").setTo(1.0)
val b = Variable("b").setTo(-5.0)
val c = Variable("c").setTo(6.0)
val quadratic = a * Variable("x").squared() + b * Variable("x") + c
println(quadratic.toLatex()) // "a \cdot x^{2} + b \cdot x + c"

// Simplification
val complex = mathNum(1) + mathNum(5) + mathNum(5) * Variable("x") + mathNum(2) - mathNum(1)
val simplified = simplify(complex)
println(simplified.toLatex()) // "6 + 5 \cdot x + 1"
```

### 🧮 Calculus
- **Derivatives**: Symbolic differentiation
- **Integrals**: Definite and indefinite integration
- **Limits**: Limit calculations
- **Chain Rule**: Automatic chain rule application

```kotlin
// Trigonometric derivatives
val sinExpr = Sin(Variable("x"))
println(sinExpr.toLatex()) // "\sin(x)"

// Exponential functions
val expExpr = Exp(mathNum(2) * Variable("x"))
println(expExpr.toLatex()) // "e^{2 \cdot x}"
```

### 📊 Summations and Products
- **Sigma Notation**: Summation with proper bounds
- **Pi Notation**: Product notation
- **Series**: Infinite and finite series support

```kotlin
// Summation
val sum = Sum(Variable("k"), mathNum(1), mathNum(10)) { k -> k }
println(sum.evaluate()) // 55.0
println(sum.toLatex())   // "\sum_{k=1}^{10}{k}"

// Product
val product = Product(Variable("j"), mathNum(1), mathNum(5)) { j -> j }
println(product.evaluate()) // 120.0 (5!)
println(product.toLatex())  // "\prod_{j=1}^{5}{j}"
```

### 🔺 Trigonometry
- **Basic Functions**: sin, cos, tan
- **Inverse Functions**: arcsin, arccos, arctan
- **Hyperbolic Functions**: sinh, cosh, tanh
- **Identities**: Trigonometric identity support

```kotlin
// Trigonometric expressions
val trigExpr = Sin(mathNum(90)) + Cos(mathNum(0))
println(trigExpr.evaluate()) // 1.0
println(trigExpr.toLatex())  // "\sin(90) + \cos(0)"

// Inverse functions
val arcExpr = ArcSin(Sin(mathNum(3)))
println(arcExpr.evaluate()) // 3.0
```

### 🧠 Boolean Logic
- **Propositional Calculus**: Logical operations
- **Boolean Algebra**: AND, OR, NOT, XOR operations
- **Logical Implication**: If-then statements
- **Truth Tables**: Boolean evaluation

```kotlin
// Boolean logic
val p = PropositionalCalculus.Fact("p", name = "It is raining")
val q = PropositionalCalculus.Fact("q", name = "It is cloudy")
val implication = p implies q
println(implication.toLatex()) // "p \implies q"

// Logical operations
val andExpr = p and q
val orExpr = p or q
println(andExpr.toLatex()) // "p \land q"
println(orExpr.toLatex())  // "p \lor q"
```

### 🔢 Combinatorics
- **Permutations**: nPr calculations
- **Combinations**: nCr calculations  
- **Factorials**: Factorial operations
- **Binomial Coefficients**: Pascal's triangle support

```kotlin
// Permutations and combinations
val n = Variable("n").setTo(5.0)
val k = Variable("k").setTo(3.0)
val perm = Permutation(n, k)
val comb = Combination(n, k)
println(perm.toLatex()) // "{}^{5}P_{3}"
println(comb.toLatex()) // "{}^{5}C_{3}"
```

### 📈 Advanced Mathematics
- **Linear Algebra**: Matrix operations, eigenvalues
- **Statistics**: Statistical functions and distributions
- **Physics**: Physical constants and formulas
- **Graph Theory**: Graph operations and algorithms

```kotlin
// Statistical functions
val mean = Sum(Variable("i"), mathNum(1), Variable("n")) { i -> i } / Variable("n")
println(mean.toLatex()) // "\frac{\sum_{i=1}^{n}{i}}{n}"

// Physical constants
val c = Constant("c", 299792458.0) // Speed of light
val e = Constant("e", 2.718281828459045)
```

### 📏 Unit Conversions
- **Metric System**: Complete SI unit system with prefixes
- **Imperial Units**: US Customary and English Imperial systems
- **Area & Volume**: Square and cubic unit conversions
- **Weight Systems**: Avoirdupois, Troy, and metric weights
- **Type Safety**: Compile-time unit checking and conversion

```kotlin
import com.measures.distance.*
import com.measures.volume.*
import com.measures.area.*

// Distance conversions
val distance = Kilometer(5.0)
val inMiles = distance.toMile()
val inFeet = distance.toFoot()
println("5 km = ${inMiles.value} miles = ${inFeet.value} feet")

// Metric prefixes
val length = Meter(1.0)
val inKm = length.toKilometer()    // 0.001 km
val inCm = length.toCentimeter()  // 100.0 cm
val inMm = length.toMillimeter()  // 1000.0 mm

// Volume conversions
val volume = Liter(1.0)
val inGallons = volume.toUSFluidGallon()
val inCups = volume.toUSCup()
println("1 liter = ${inGallons.value} gallons = ${inCups.value} cups")

// Area conversions
val area = SquareMeter(100.0)
val inAcres = area.toAcre()
val inSquareFeet = area.toSquareFoot()
println("100 m² = ${inAcres.value} acres = ${inSquareFeet.value} ft²")
```

#### 🏗️ Unit System Architecture

PolyMath's unit system is built on a robust foundation:

- **Base Units**: Fundamental units (meter, liter, kilogram, etc.)
- **Derived Units**: Calculated from base units (area, volume, speed)
- **Prefixes**: Full SI prefix system (kilo, mega, giga, micro, nano, etc.)
- **Type Safety**: Compile-time checking prevents unit mismatches
- **Performance**: Value classes ensure zero-overhead abstractions

```kotlin
// Type-safe unit arithmetic
val speed = Kilometer(100.0) / Hour(1.0)  // 100 km/h
val acceleration = speed / Second(10.0)   // 10 km/h/s

// Automatic unit conversion
val distance1 = Mile(1.0)
val distance2 = Kilometer(1.0)
val total = distance1 + distance2  // Automatically converts to common unit
```

#### 🌍 Supported Unit Systems

**Metric System (SI)**
- Distance: meter, kilometer, centimeter, millimeter, etc.
- Volume: liter, milliliter, cubic meter
- Area: square meter, hectare, square kilometer
- Weight: gram, kilogram, tonne

**US Customary Units**
- Distance: foot, inch, yard, mile
- Volume: gallon, quart, pint, cup, fluid ounce
- Area: square foot, acre, square mile
- Weight: pound, ounce, ton

**English Imperial Units**
- Distance: imperial foot, inch, yard, mile
- Volume: imperial gallon, quart, pint
- Area: square yard, acre
- Weight: stone, pound, ounce

**Specialized Systems**
- Survey units: survey foot, chain, rod, link
- Troy weights: troy ounce, troy pound
- Avoirdupois weights: avoirdupois pound, ounce
- Nautical units: nautical mile, fathom, cable

```kotlin
// Survey measurements
val surveyDistance = SurveyMile(1.0)
val inMeters = surveyDistance.toMeter()
println("1 survey mile = ${inMeters.value} meters")

// Troy weights for precious metals
val gold = TroyOunce(1.0)
val inGrams = gold.toGram()
println("1 troy ounce = ${inGrams.value} grams")

// Nautical measurements
val nauticalMile = NauticalMile(1.0)
val inKilometers = nauticalMile.toKilometer()
println("1 nautical mile = ${inKilometers.value} km")
```

## 🎯 Mathematical Domains

PolyMath includes specialized modules for various mathematical fields:

- **Algebra**: Linear equations, polynomials, factoring
- **Calculus**: Derivatives, integrals, limits, series
- **Statistics**: Probability, distributions, hypothesis testing
- **Physics**: Classical mechanics, thermodynamics, electromagnetism
- **Graph Theory**: Networks, algorithms, optimization
- **Logic**: Propositional calculus, predicate logic
- **Number Theory**: Prime numbers, modular arithmetic
- **Geometry**: Euclidean geometry, coordinate systems

## 🔧 LaTeX Rendering

All mathematical expressions can be rendered as LaTeX for publication:

```kotlin
val complexExpr = (Variable("x") + mathNum(2)).squared() / (Variable("y") - mathNum(1))
println(complexExpr.toLatex())
// Output: "\frac{(x + 2)^{2}}{y - 1}"
```

## 📦 Project Structure

```
PolyMath/
├── math-algebra/          # Core algebraic operations
├── math-base/            # Base mathematical types
├── math-geometry/        # Geometric calculations
├── physics-classical/    # Classical physics formulas
├── unit-base/            # Base unit system and interfaces
├── unit-metric/          # Metric (SI) unit system
├── unit-american-customary/  # US Customary units
├── unit-english-imperial/    # English Imperial units
├── unit-english-international/ # International English units
├── unit-weight-avoirdupois/   # Avoirdupois weight system
├── unit-weight-troy/         # Troy weight system
├── Measures/             # Unit conversion runtime
└── latex-builder/        # LaTeX rendering engine
```

## 🚀 Getting Started

1. **Add to your project**: Include the PolyMath modules in your build
2. **Import the DSL**: Use the main DSL imports for full functionality
3. **Start with basics**: Try simple arithmetic and variable operations
4. **Explore domains**: Dive into specific mathematical areas
5. **Render LaTeX**: Use `.toLatex()` for mathematical notation

## 💡 Examples by Use Case

### Educational Applications
```kotlin
// Step-by-step equation solving
val equation = Variable("x").squared() - mathNum(4) * Variable("x") + mathNum(3)
val explanation = equation.explain()
println(explanation) // Shows step-by-step solution
```

### Scientific Computing
```kotlin
// Complex scientific calculations
val e = Constant("e", 2.718281828459045)
val pi = Constant("π", 3.141592653589793)
val result = e.pow(pi * mathNum(1).num())
println(result.toLatex()) // "e^{\pi \cdot 1}"
```

### Research and Publication
```kotlin
// Generate LaTeX for papers
val formula = Sqrt(Variable("a").squared() + Variable("b").squared())
println(formula.toLatex()) // "\sqrt{a^{2} + b^{2}}"
```

### Engineering and Construction
```kotlin
// Unit conversions for engineering calculations
val beamLength = Foot(20.0)
val beamWidth = Inch(12.0)
val beamHeight = Inch(8.0)
val volume = beamLength * beamWidth * beamHeight
val volumeInCubicMeters = volume.toCubicMeter()
println("Beam volume: ${volumeInCubicMeters.value} m³")

// Metric conversions for international projects
val distance = Kilometer(5.0)
val inMiles = distance.toMile()
val inFeet = distance.toFoot()
println("5 km = ${inMiles.value} miles = ${inFeet.value} feet")
```

### Scientific Research
```kotlin
// Precise measurements with appropriate units
val wavelength = Nanometer(500.0)  // Green light wavelength
val frequency = wavelength.toMeter().let { 
    Constant("c", 299792458.0) / it  // c / λ
}
println("Green light frequency: ${frequency.evaluate()} Hz")

// Temperature conversions for experiments
val celsius = Celsius(25.0)
val fahrenheit = celsius.toFahrenheit()
val kelvin = celsius.toKelvin()
println("25°C = ${fahrenheit.value}°F = ${kelvin.value}K")
```

## 🤝 Contributing

PolyMath is designed to be extensible. You can:
- Add new mathematical domains
- Implement additional functions
- Create specialized DSLs for specific fields
- Contribute unit systems and constants

## 📄 License

This project is part of a larger mathematical computing ecosystem. See individual module licenses for details.

---

**PolyMath**: Making mathematics accessible, type-safe, and beautiful in Kotlin. 🧮✨
