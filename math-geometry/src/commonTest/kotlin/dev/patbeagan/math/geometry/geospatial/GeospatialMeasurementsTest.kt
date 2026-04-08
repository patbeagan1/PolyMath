package dev.patbeagan.math.geometry.geospatial

import dev.patbeagan.math.geometry.geospatial.measure.Euclidean2D
import dev.patbeagan.math.geometry.geospatial.measure.Euclidean3D
import dev.patbeagan.math.geometry.geospatial.measure.GeodesicWgs84
import dev.patbeagan.math.geometry.geospatial.measure.toGeodesicCalculator
import dev.patbeagan.math.geometry.geospatial.model.GeoLineString
import dev.patbeagan.math.geometry.geospatial.model.GeoPosition
import dev.patbeagan.math.geometry.geospatial.model.LineString2D
import dev.patbeagan.math.geometry.geospatial.model.LineString3D
import dev.patbeagan.math.geometry.geospatial.model.MultiGeoLineString
import dev.patbeagan.math.geometry.geospatial.model.MultiLineString2D
import dev.patbeagan.math.geometry.geospatial.model.MultiLineString3D
import dev.patbeagan.math.geometry.geospatial.model.Point2D
import dev.patbeagan.math.geometry.geospatial.model.Point3D
import dev.patbeagan.math.geometry.geospatial.spaces.GeometryBasis
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class GeospatialMeasurementsTest {
    @Test
    fun euclidean2D_distance_threeFourFive() {
        val d = Euclidean2D.distance(Point2D(0.0, 0.0), Point2D(3.0, 4.0))
        assertEquals(5.0, d.value, 1e-9)
    }

    @Test
    fun euclidean2D_lineString_length() {
        val line =
            LineString2D(
                listOf(
                    Point2D(0.0, 0.0),
                    Point2D(3.0, 4.0),
                    Point2D(3.0, 9.0),
                ),
            )
        val len = Euclidean2D.length(line)
        assertEquals(10.0, len.value, 1e-9)
    }

    @Test
    fun euclidean2D_multiLineString_sums_parts() {
        val multi =
            MultiLineString2D(
                listOf(
                    LineString2D(listOf(Point2D(0.0, 0.0), Point2D(3.0, 4.0))),
                    LineString2D(listOf(Point2D(0.0, 0.0), Point2D(0.0, 5.0))),
                ),
            )
        assertEquals(10.0, Euclidean2D.length(multi).value, 1e-9)
    }

    @Test
    fun euclidean3D_distance() {
        val d = Euclidean3D.distance(Point3D(0.0, 0.0, 0.0), Point3D(2.0, 3.0, 6.0))
        assertEquals(7.0, d.value, 1e-9)
    }

    @Test
    fun euclidean3D_lineString_length() {
        val line =
            LineString3D(
                listOf(
                    Point3D(0.0, 0.0, 0.0),
                    Point3D(1.0, 0.0, 0.0),
                    Point3D(1.0, 1.0, 0.0),
                ),
            )
        assertEquals(2.0, Euclidean3D.length(line).value, 1e-9)
    }

    @Test
    fun euclidean3D_multiLineString_sums_parts() {
        val multi =
            MultiLineString3D(
                listOf(
                    LineString3D(listOf(Point3D(0.0, 0.0, 0.0), Point3D(3.0, 0.0, 4.0))),
                    LineString3D(listOf(Point3D(0.0, 0.0, 0.0), Point3D(0.0, 0.0, 5.0))),
                ),
            )
        assertEquals(10.0, Euclidean3D.length(multi).value, 1e-9)
    }

    @Test
    fun haversine_one_degree_along_equator() {
        val geo = GeodesicWgs84()
        val a = GeoPosition(0.0, 0.0)
        val b = GeoPosition(1.0, 0.0)
        val m = geo.distance(a, b)
        assertTrue(m.value in 111_190.0..111_200.0, "expected ~111.2 km, got ${m.value}")
    }

    @Test
    fun haversine_lineString_length() {
        val geo = GeodesicWgs84()
        val line =
            GeoLineString(
                listOf(
                    GeoPosition(0.0, 0.0),
                    GeoPosition(1.0, 0.0),
                    GeoPosition(2.0, 0.0),
                ),
            )
        val m = geo.length(line)
        assertTrue(m.value in 222_380.0..222_400.0, "expected ~222.4 km, got ${m.value}")
    }

    @Test
    fun haversine_multiGeoLineString_sums() {
        val geo = GeodesicWgs84()
        val multi =
            MultiGeoLineString(
                listOf(
                    GeoLineString(listOf(GeoPosition(0.0, 0.0), GeoPosition(1.0, 0.0))),
                    GeoLineString(listOf(GeoPosition(0.0, 0.0), GeoPosition(0.0, 1.0))),
                ),
            )
        val m = geo.length(multi)
        assertTrue(m.value > 222_000.0 && m.value < 223_000.0, "got ${m.value}")
    }

    @Test
    fun geospatial_facade_wires_defaults() {
        val g = Geospatial.wgs84Geodesic()
        assertEquals(
            GeodesicWgs84().distance(GeoPosition(0.0, 0.0), GeoPosition(0.0, 1.0)).value,
            g.distance(GeoPosition(0.0, 0.0), GeoPosition(0.0, 1.0)).value,
            0.01,
        )
    }

    @Test
    fun geodesic_bearing_north_and_east() {
        val geo = GeodesicWgs84()
        val north = geo.bearingDegrees(GeoPosition(0.0, 0.0), GeoPosition(0.0, 1.0))
        assertTrue(north in 0.0..0.01, "north bearing, got $north")
        val east = geo.bearingDegrees(GeoPosition(0.0, 0.0), GeoPosition(1.0, 0.0))
        assertTrue(east in 89.99..90.01, "east bearing, got $east")
    }

    @Test
    fun geometry_basis_to_geodesic_calculator() {
        val globe = GeometryBasis.GlobeWgs84MeanSphere()
        val calc = globe.toGeodesicCalculator()!!
        val expected = GeodesicWgs84().distance(GeoPosition(0.0, 0.0), GeoPosition(1.0, 0.0)).value
        assertEquals(expected, calc.distance(GeoPosition(0.0, 0.0), GeoPosition(1.0, 0.0)).value, 0.01)
        assertNull(GeometryBasis.EuclideanPlaneMeters.toGeodesicCalculator())
    }
}
