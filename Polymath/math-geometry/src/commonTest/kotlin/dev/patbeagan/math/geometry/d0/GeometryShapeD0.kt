package dev.patbeagan.math.geometry.d0

import kotlin.math.PI
import kotlin.math.sqrt
import kotlin.test.Test
import kotlin.test.assertEquals

class GeometryShapeD0Test {

    @Test
    fun cartesian_distance() {
        val origin = PointCartesian()
        val sub = origin.distanceTo(PointCartesian.of(x = 1.0))
        println(sub.toLatex())
        println(sub)

        assertEquals(sqrt(2.0), PointCartesian.of(x = 1.0, y = 1.0).distanceTo(origin).evaluate())
        assertEquals(sqrt(2.0), PointCartesian.of(y = 1.0, z = 1.0).distanceTo(origin).evaluate())
        assertEquals(5.0, PointCartesian.of(y = 5.0).distanceTo(origin).evaluate())
    }

    @Test
    fun cylindrical_distance() {
        val p1 = PointCylindrical.of(radius = 2.0)
        val p2 = PointCylindrical.of(radius = 2.0, angle = PI)
        val dist = p1.distanceTo(p2).evaluate()
        assertEquals(4.0, dist)
    }

    @Test
    fun spherical_distance() {
        val p1 = PointSpherical.of(radius = sqrt(2.0), angle = 0.0, polar = PI / 4)
        val p2 = PointCartesian.of(1, 0, 0)
        val dist = p1.distanceTo(p2).evaluate()
        assertEquals(1.0000000000000002, dist)
    }

    @Test
    fun cylindrical_to_cartesian_conversion() {
        val actual = PointCylindrical.of(4.0, PI / 6.0, 3.0).toPointCartesian()
        val expected = PointCartesian.of(3.464101615137755, 1.9999999999999998, 3.0)
        assertEquals(expected, actual)
    }

    @Test
    fun spherical_to_cylindrical_conversion() {
        val actual = PointSpherical.of(2.0, -PI / 4.0, PI / 2)
        val expected = PointCylindrical.of(2.0, -PI / 4.0, 1.2246467991473532E-16)
        assertEquals(expected, actual.toPointCylindrical())
    }

    @Test
    fun cartesian_translation() {
        val origin = PointCartesian()
        val p1 = PointCartesian.of(1, 2, 3)
        val p2 = PointCartesian.of(4, 3, 2)

        val expected = PointCartesian.of(5, 5, 5)
        val actual = origin + p1 + p2
        assertEquals(expected, actual)
    }

    @Test
    fun cylindrical_translation() {
        val origin = PointCartesian()
        val p1 = PointCylindrical.of(2.0, PI, 3.0)
        val p2 = PointCartesian.of(1, 1, -1)

        val expected = PointCartesian.of(-1.0, 1.0000000000000002, 2.0)
        val actual = origin + p1 + p2
        assertEquals(expected, actual)
    }

    @Test
    fun spherical_translation() {
        val origin = PointCartesian()
        val p1 = PointSpherical.of(radius = 1.0, angle = PI / 2.0, PI / 2.0)

        val expected = p1
        val actual = (origin + p1).toPointSpherical()
        assertEquals(expected, actual)
    }
}
