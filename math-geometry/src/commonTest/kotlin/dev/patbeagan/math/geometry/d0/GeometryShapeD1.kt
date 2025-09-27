package dev.patbeagan.math.geometry.d0

import dev.patbeagan.math.geometry.d1.LineSegment
import dev.patbeagan.math.geometry.d2.Triangle
import kotlin.test.Test

class GeometryShapeD1 {
    @Test
    fun line() {
        val segment = LineSegment(
            PointCartesian.Companion.of(5, 5),
            PointCartesian.Companion.of(3, 2)
        )

        println(segment.midpoint)
        println(segment.slope.toLatex())
        println(segment.slope.display())
        println(segment.slope.evaluate())
        println(segment.yIntercept.toLatex())
        println(segment.yIntercept.display())
        println(segment.yIntercept.evaluate())
        println(segment.xIntercept.toLatex())
        println(segment.xIntercept.display())
        println(segment.xIntercept.evaluate())
        println()
        println(segment.slopeInterceptForm.explain())
        println()
        println(segment.midpoint)
        println(segment.length.display())
    }

    @Test
    fun triangle() {
        val sub = Triangle(
            PointCartesian.of(0.0, 0.0, 0.0),
            PointCartesian.of(2.0, 0.0, 0.0),
            PointCartesian.of(0.0, 1.0, 0.0)
        )
        println(sub.base.display())
        println(sub.base.evaluate())
        println(sub.height.display())
        println(sub.height.evaluate())
        println(sub.area.display())
        println(sub.area.evaluate())
        println(sub.perimeter.display())
        println(sub.perimeter.evaluate())

        println(sub.area.equate("V").explain())
    }
}