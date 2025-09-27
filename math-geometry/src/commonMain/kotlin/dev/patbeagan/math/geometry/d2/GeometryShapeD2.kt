package dev.patbeagan.math.geometry.d2

import dev.patbeagan.math.geometry.Geometry
import dev.patbeagan.math.geometry.Geometry.constPi
import dev.patbeagan.math.geometry.d0.Point
import dev.patbeagan.math.geometry.d1.LineSegment
import main.dsl.expressions.ScalarAlgebra.*
import main.dsl.expressions.ScalarRelation
import main.dsl.mathnum.MathFunction
import main.dsl.mathnum.Variable
import main.dsl.mathnum.num

/**
 * digraph G {
 *     node [shape=box];
 *
 *     Shape -> Polygon;
 *     Shape -> Ellipse;
 *     Polygon -> Quadrilateral;
 *     Polygon -> Triangle;
 *     Ellipse -> Circle;
 *
 *     Quadrilateral -> Parallelogram;
 *     Quadrilateral -> Trapezoid;
 *     Quadrilateral -> Kite;
 *     Parallelogram -> Rectangle;
 *     Parallelogram -> Rhombus;
 *     Rectangle -> Square;
 *
 *     Triangle -> EquilateralTriangle;
 *     Triangle -> IsoscelesTriangle;
 *     Triangle -> RightTriangle;
 *     Triangle -> ScaleneTriangle;
 * }
 */
sealed interface GeometryShapeD2 {
    val area: MathFunction
    val perimeter: MathFunction

//    @JvmInline
//    value class Perimeter(val value: Expression)
//
//    @JvmInline
//    value class Area(val value: Expression)
}

sealed interface Polygon : GeometryShapeD2
sealed class Ellipse : GeometryShapeD2
sealed class Quadrilateral : Polygon

class Triangle(
    p1: Point,
    p2: Point,
    p3: Point,
) : Polygon {

    val baseSide: LineSegment by lazy { p1 lineTo p2 }

    val base: MathFunction by lazy {
        baseSide.length
            .toMathFunction("b", "base")
    }

    val height: MathFunction by lazy {
        baseSide.midpoint.distanceTo(p3)
            .toMathFunction("h", "height")
    }

    val areaByBaseHeight by lazy {
        ((1.num() / 2.num()) * base * height)
            .toMathFunction("A", "area")
    }

    val lengthOfSideA = (p1 distanceTo p2).toMathFunction("l_a", "lengthOfSideA")
    val lengthOfSideB = (p2 distanceTo p3).toMathFunction("l_b", "lengthOfSideB")
    val lengthOfSideC = (p3 distanceTo p1).toMathFunction("l_c", "lengthOfSideC")

    val areaByHeronFormula by lazy {
        val s = halfPerimeter
        // heron's formula
        Sqrt(s * (s - lengthOfSideA) * (s - lengthOfSideB) * (s - lengthOfSideC))
            .toMathFunction("A", "area")
    }

    // math functions in an object off of triangle
    // "with" syntax, but chained, for namespaces

    override val area: MathFunction get() = areaByHeronFormula

    override val perimeter: MathFunction by lazy {
        val p = lengthOfSideA + lengthOfSideB + lengthOfSideC
        p.toMathFunction("p", "perimeter")
    }

    val halfPerimeter: MathFunction by lazy {
        (perimeter / 2.0.num()).toMathFunction("s", "halfPerimeter")
    }

    enum class Type {
        EquilateralTriangle,
        IsoscelesTriangle,
        RightTriangle,
        ScaleneTriangle
    }
}

sealed class Circle : Ellipse() {
    fun circleArea(
        radius: Double = Double.NaN
    ): ScalarRelation.Equation {
        val pi = constPi
        val r = Geometry.symRadius.setTo(radius)
        return Geometry.symArea.standsFor(pi * r.squared())
    }

    fun circumference(
        radius: Double = Double.NaN
    ): ScalarRelation.Equation {
        val pi = constPi
        val r = Geometry.symRadius.setTo(radius)
        return Geometry.symCircumference.standsFor(2.num() * pi * r)
    }

    fun arcLength(
        radius: Double = Double.NaN,
        centralAngle: Double = Double.NaN // in radians
    ): ScalarRelation.Equation {
        val r = Geometry.symRadius.setTo(radius)
        val a = Geometry.symCentralAngle.setTo(centralAngle)
        return Geometry.symLength.standsFor(r * a)
    }

    fun sectorArea(
        radius: Double = Double.NaN,
        centralAngle: Double = Double.NaN // in radians
    ): ScalarRelation.Equation {
        val r = Geometry.symRadius.setTo(radius)
        val a = Geometry.symCentralAngle.setTo(centralAngle)
        return Geometry.symArea.standsFor((1.num() / 2.num()) * r.squared() * a)
    }

    fun chordLength(
        radius: Double = Double.NaN,
        centralAngle: Double = Double.NaN // in radians
    ): ScalarRelation.Equation {
        val r = Geometry.symRadius.setTo(radius)
        val a = Geometry.symCentralAngle.setTo(centralAngle)
        return Geometry.symArea.standsFor(2.num() * r * Sin(a / 2.num()))
    }
}

sealed class Parallelogram : Quadrilateral() {
    fun parallelogramArea(
        base: Double = Double.NaN,
        height: Double = Double.NaN
    ): ScalarRelation.Equation {
        val b = Geometry.symBase.setTo(base)
        val h = Geometry.symHeight.setTo(height)
        return Geometry.symArea.standsFor(b * h)
    }
}

//class Trapezoid : Quadrilateral() {
//    fun trapezoidArea(
//        base1: Double = Double.NaN,
//        base2: Double = Double.NaN,
//        height: Double = Double.NaN
//    ): Relation.Equation {
//        val b1 = Variable("b1").setTo(base1)
//        val b2 = Variable("b2").setTo(base2)
//        val h = Geometry.symHeight.setTo(height)
//        return Geometry.symArea.standsFor((b1 + b2) * h / 2.num())
//    }
//}
//
//class Kite : Quadrilateral()
//open class Rectangle : Parallelogram() {
//    // Area of a rectangle
//    val rectangleArea: String
//        get() = "${Geometry.symArea} = ${Geometry.symBase} \\times ${Geometry.symHeight}"
//
//
//    fun rectangleArea(
//        base: Double = Double.NaN,
//        height: Double = Double.NaN
//    ): Relation.Equation {
//        val b = Geometry.symBase.setTo(base)
//        val h = Geometry.symHeight.setTo(height)
//        return Geometry.symArea.standsFor(b * h)
//    }
//
//    fun rectanglePerimeter(
//        base: Double = Double.NaN,
//        height: Double = Double.NaN
//    ): Relation.Equation {
//        val b = Geometry.symBase.setTo(base)
//        val h = Geometry.symHeight.setTo(height)
//        return Geometry.symPerimeter.standsFor(2.num() * (b + h))
//    }
//}

sealed class Rhombus : Parallelogram() {
    fun rhombusArea(
        diagonal1: Double = Double.NaN,
        diagonal2: Double = Double.NaN
    ): ScalarRelation.Equation {
        val d1 = Variable("d1").setTo(diagonal1)
        val d2 = Variable("d2").setTo(diagonal2)
        return Geometry.symArea.standsFor((d1 * d2) / 2.num())
    }
}

//class Square : Rectangle() {
//    fun squareArea(
//        side: Double = Double.NaN
//    ): Relation.Equation {
//        val s = Geometry.symSide.setTo(side)
//        return Geometry.symArea.standsFor(s.squared())
//    }
//}
