package dev.patbeagan.math.geometry.d1

import dev.patbeagan.math.base.GenericSymbols
import dev.patbeagan.math.geometry.d0.Point
import dev.patbeagan.math.geometry.d0.PointCartesian
import main.dsl.expressions.ScalarAlgebra.Abs
import main.dsl.mathnum.MathFunction
import main.dsl.mathnum.Variable
import main.dsl.mathnum.mathNum
import kotlin.jvm.JvmInline

@JvmInline
value class Vector(private val definition: LineSegment) {
    val magnitude: MathFunction get() = definition.length
}

@JvmInline
value class Ray(private val definition: LineSegment)

data class LineSegment(
    val p1: PointCartesian,
    val p2: PointCartesian,
) : GenericSymbols {

    constructor(p1: Point, p2: Point) : this(
        p1.toPointCartesian(),
        p2.toPointCartesian()
    )

    val length: MathFunction by lazy {
        p1.distanceTo(p2).toMathFunction("l", "length")
    }

    val slope: MathFunction by lazy {
        val numerator = p1.y.withInstance("1") - p2.y.withInstance("2")
        val denominator = p1.x.withInstance("1") - p2.x.withInstance("2")
        (Abs(numerator) / Abs(denominator)).toMathFunction("m", "slope")
    }

    val yIntercept: MathFunction by lazy {
        (p1.y - (slope * p1.x)).toMathFunction("b", "yIntercept")
    }

    val xIntercept: MathFunction by lazy {
        ((p1.y - yIntercept) / slope).toMathFunction("b_x", "xIntercept")
    }

    val midpoint: PointCartesian by lazy {
        PointCartesian.of(
            (p1.x.withInstance("1") + p2.x.withInstance("2")) / mathNum(2.0),
            (p1.y.withInstance("1") + p2.y.withInstance("2")) / mathNum(2.0)
        )
    }

    val slopeInterceptForm by lazy {
        val y = Variable("y")
        val m = slope
        val x = Variable("x")
        val b = yIntercept
        y.isEqualTo(m * x + b)
    }
}
