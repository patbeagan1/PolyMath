package dev.patbeagan.math.geometry.d0

import dev.patbeagan.math.base.GenericSymbols
import dev.patbeagan.math.geometry.d1.LineSegment
import main.dsl.expressions.ScalarExpression

sealed interface Point : GenericSymbols {
    operator fun plus(point: Point) = apply { translateBy(point) }
    infix fun distanceTo(point: Point): ScalarExpression
    fun translateBy(point: Point)
    fun toPointSpherical(): PointSpherical
    fun toPointCylindrical(): PointCylindrical
    fun toPointCartesian(): PointCartesian
    infix fun lineTo(other: Point) = LineSegment(this, other)
}