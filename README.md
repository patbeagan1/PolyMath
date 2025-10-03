# PolyMath

A comprehensive mathematical DSL (Domain Specific Language) for Kotlin that lets you write mathematical expressions naturally and evaluate them or render them as LaTeX. PolyMath supports multiple fields of mathematics with an intuitive, type-safe API.

## 🚀 Quick Start

```kotlin
// Basic arithmetic with proper order of operations
val result = (5.num() + 3.num()) * 2.num()
println(result.evaluate()) // 16.0
println(result.toLatex())  // "(5 + 3) \cdot 2"

// Variables and equations with cached variables
val x = Variable("x").setTo(5.0)
val three = 3.num()
val two = 2.num()
val equation = x.squared() + three * x + two
println(equation.evaluate()) // 42.0 (5² + 3×5 + 2 = 25 + 15 + 2 = 42)
println(equation.toLatex())  // "x^{2} + 3 \cdot x + 2"
```

## 📚 Features

### 🔢 Basic Arithmetic
- **Order of Operations**: Automatic handling of PEMDAS/BODMAS
- **Variables**: Dynamic and static variable support
- **Rational Numbers**: Exact arithmetic with fractions
- **Equations**: Create and manipulate mathematical equations

```kotlin
// Order of operations (multiplication before addition)
val five = 5.num()
val three = 3.num()
val two = 2.num()
val one = 1.num()
val expr = five * three + two - one
println(expr.evaluate()) // 16.0 (5×3 + 2 - 1 = 15 + 2 - 1 = 16)
println(expr.toLatex())  // "5 \cdot 3 + 2 - 1"

// Rational arithmetic with proper fractions
val oneThird = 1.num() / 3.num()
val oneFourth = 1.num() / 4.num()
val fraction = oneThird + oneFourth
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
val x = Variable("x")
val quadratic = a * x.squared() + b * x + c
println(quadratic.toLatex()) // "a \cdot x^{2} + b \cdot x + c"

// Simplification with proper order of operations
val one = 1.num()
val five = 5.num()
val two = 2.num()
val complex = one + five + five * x + two - one
val simplified = simplify(complex)
println(simplified.toLatex()) // "6 + 5 \cdot x + 1"
```

### 🧮 Calculus
- **Derivatives**: Symbolic differentiation
- **Integrals**: Definite and indefinite integration
- **Limits**: Limit calculations
- **Chain Rule**: Automatic chain rule application

```kotlin
// Trigonometric derivatives with cached variables
val x = Variable("x")
val sinExpr = Sin(x)
println(sinExpr.toLatex()) // "\sin(x)"

// Exponential functions with proper multiplication
val two = 2.num()
val expExpr = Exp(two * x)
println(expExpr.toLatex()) // "e^{2 \cdot x}"
```

### 📊 Summations and Products
- **Sigma Notation**: Summation with proper bounds
- **Pi Notation**: Product notation
- **Series**: Infinite and finite series support

```kotlin
// Summation with cached variables
val k = Variable("k").setTo(1)
val one = 1.num()
val ten = 10.num()
val sum = Sum(k, one, ten) { k -> k }
println(sum.evaluate()) // 55.0 (1+2+3+...+10 = 55)
println(sum.toLatex())   // "\sum_{k=1}^{10}{k}"

// Product with cached variables
val j = Variable("j")
val five = 5.num()
val product = Product(j, one, five) { j -> j }
println(product.evaluate()) // 120.0 (1×2×3×4×5 = 120 = 5!)
println(product.toLatex())  // "\prod_{j=1}^{5}{j}"
```

### 🔺 Trigonometry
- **Basic Functions**: sin, cos, tan
- **Inverse Functions**: arcsin, arccos, arctan
- **Hyperbolic Functions**: sinh, cosh, tanh
- **Identities**: Trigonometric identity support

```kotlin
// Trigonometric expressions with cached values
val ninety = 90.num()
val zero = 0.num()
val trigExpr = Sin(ninety) + Cos(zero)
println(trigExpr.evaluate()) // ≈ 1.893 (sin(90) + cos(0) ≈ 0.893 + 1 = 1.893 in radians)
println(trigExpr.toLatex())  // "\sin(90) + \cos(0)"

// Inverse functions with cached values
val three = 3.num()
val arcExpr = ArcSin(Sin(three))
println(arcExpr.evaluate()) // 3.0 (arcsin(sin(3)) = 3)
```

### 🧠 Boolean Logic
- **Propositional Calculus**: Logical operations
- **Boolean Algebra**: AND, OR, NOT, XOR operations
- **Logical Implication**: If-then statements
- **Truth Tables**: Boolean evaluation

```kotlin
// Boolean logic with cached propositions
val p = PropositionalCalculus.Fact("p", name = "It is raining")
val q = PropositionalCalculus.Fact("q", name = "It is cloudy")
val implication = p implies q
println(implication.toLatex()) // "p \implies q"

// Logical operations with cached variables
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
// Permutations and combinations with cached variables
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
// Statistical functions with cached variables
val i = Variable("i")
val n = Variable("n")
val one = 1.num()
val mean = Sum(i, one, n) { i -> i } / n
println(mean.toLatex()) // "\frac{\sum_{i=1}^{n}{i}}{n}"

// Physical constants with cached values
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
// Complex expressions with cached variables
val x = Variable("x")
val y = Variable("y")
val two = 2.num()
val one = 1.num()
val complexExpr = (x + two).squared() / (y - one)
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
// Step-by-step equation solving with cached variables
val x = Variable("x")
val four = 4.num()
val three = 3.num()
val equation = x.squared() - four * x + three
val explanation = equation.explain()
println(explanation) // Shows step-by-step solution
```

### Scientific Computing
```kotlin
// Complex scientific calculations with cached constants
val e = Constant("e", 2.718281828459045)
val pi = Constant("π", 3.141592653589793)
val one = 1.num()
val result = e.pow(pi * one)
println(result.toLatex()) // "e^{\pi \cdot 1}"
```

### Research and Publication
```kotlin
// Generate LaTeX for papers with cached variables
val a = Variable("a")
val b = Variable("b")
val formula = Sqrt(a.squared() + b.squared())
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

### PolyMath CLI Tool

A unified Python CLI to generate unit families, run fix utilities, and search Kotlin sources.

#### Quick Start (Recommended)

```bash
# From the PolyMath directory
cd PolyMath

# List available generation families
./pm generate --list

# Generate metric families
./pm generate metric
./pm generate metric-distance metric-volume american-customary

# Generate all families
./pm generate all

# Run fix utilities
./pm fix imports      # Fix import issues
./pm fix force        # Fix force unit issues
./pm fix all          # Run all fixes

# Search Kotlin sources (regex)
./pm search --pattern "UnitDistance"
./pm search --pattern "class.*Meter" --ignore-case --limit 10
```

#### Alternative Usage

```bash
# Using uv directly
uv run python -m polymath_tool generate --list
uv run python -m polymath_tool fix all

# Using standard Python
python -m polymath_tool generate metric
python polymath.py search --pattern "UnitVolume"
```

#### Available Commands

- **generate** - Generate unit families (metric, american-customary, english-imperial, avoirdupois, troy, acceleration, etc.)
- **fix** - Run fix utilities (imports, force, types, units, circular, interfaces, final, all)
- **search** - Search Kotlin sources with regex patterns

#### Features

- ✅ **No hardcoded paths** - Works from any directory
- ✅ **Zero dependencies** - Pure stdlib Python
- ✅ **uv compatible** - Uses shebang for seamless execution
- ✅ **Consolidated logic** - All generation and fix utilities in one tool

---

**PolyMath**: Making mathematics accessible, type-safe, and beautiful in Kotlin. 🧮✨
