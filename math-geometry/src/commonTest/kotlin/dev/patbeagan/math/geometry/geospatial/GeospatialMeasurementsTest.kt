package dev.patbeagan.math.geometry.geospatial

import com.measures.distance.Meter
import dev.patbeagan.math.geometry.geospatial.measure.Euclidean2D
import dev.patbeagan.math.geometry.geospatial.measure.Euclidean3D
import dev.patbeagan.math.geometry.geospatial.measure.GeodesicWgs84
import dev.patbeagan.math.geometry.geospatial.measure.toGeodesicCalculator
import dev.patbeagan.math.geometry.geospatial.model.GeoBoundingBox
import dev.patbeagan.math.geometry.geospatial.model.GeoLineString
import dev.patbeagan.math.geometry.geospatial.model.GeoPolygon
import dev.patbeagan.math.geometry.geospatial.model.GeoPosition
import dev.patbeagan.math.geometry.geospatial.model.boundingBox
import dev.patbeagan.math.geometry.geospatial.model.centroidVertexMean
import dev.patbeagan.math.geometry.geospatial.model.LineString2D
import dev.patbeagan.math.geometry.geospatial.model.LineString3D
import dev.patbeagan.math.geometry.geospatial.model.MultiGeoLineString
import dev.patbeagan.math.geometry.geospatial.model.MultiLineString2D
import dev.patbeagan.math.geometry.geospatial.model.MultiLineString3D
import dev.patbeagan.math.geometry.geospatial.model.Point2D
import dev.patbeagan.math.geometry.geospatial.model.Point3D
import dev.patbeagan.math.geometry.geospatial.predicate.BooleanPointInPolygon
import dev.patbeagan.math.geometry.geospatial.spaces.GeometryBasis
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
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

    @Test
    fun geodesic_destination_north() {
        val geo = GeodesicWgs84()
        val p = geo.destination(GeoPosition(0.0, 0.0), bearingDegrees = 0.0, distance = Meter(1000.0))
        assertTrue(p.longitude in -0.01..0.01)
        assertTrue(p.latitude in 0.0089..0.0091, "lat deg ~ ${p.latitude}")
    }

    @Test
    fun geodesic_midpoint_on_meridian() {
        val geo = GeodesicWgs84()
        val m = geo.midpoint(GeoPosition(0.0, 0.0), GeoPosition(0.0, 2.0))
        assertTrue(m.longitude in -0.01..0.01)
        assertTrue(m.latitude in 0.99..1.01)
    }

    @Test
    fun geodesic_along_half_segment_on_equator() {
        val geo = GeodesicWgs84()
        val line = GeoLineString(listOf(GeoPosition(0.0, 0.0), GeoPosition(2.0, 0.0)))
        val halfLen = geo.length(line).value / 2.0
        val p = geo.along(line, Meter(halfLen))
        assertTrue(p.latitude in -0.01..0.01)
        assertTrue(p.longitude in 0.99..1.01)
    }

    @Test
    fun geodesic_polygon_area_one_degree_square_at_equator() {
        val geo = GeodesicWgs84()
        val poly =
            GeoPolygon(
                outerRing =
                    listOf(
                        GeoPosition(0.0, 0.0),
                        GeoPosition(1.0, 0.0),
                        GeoPosition(1.0, 1.0),
                        GeoPosition(0.0, 1.0),
                        GeoPosition(0.0, 0.0),
                    ),
            )
        val a = geo.area(poly)
        assertTrue(a.value in 1.20e10..1.28e10, "area m² ~ ${a.value}")
    }

    @Test
    fun geo_polygon_centroid_vertex_mean() {
        val poly =
            GeoPolygon(
                listOf(
                    GeoPosition(0.0, 0.0),
                    GeoPosition(2.0, 0.0),
                    GeoPosition(2.0, 2.0),
                    GeoPosition(0.0, 2.0),
                ),
            )
        val c = poly.centroidVertexMean()
        assertEquals(1.0, c.longitude, 1e-9)
        assertEquals(1.0, c.latitude, 1e-9)
    }

    @Test
    fun boolean_point_in_polygon_with_hole() {
        val outer =
            listOf(
                GeoPosition(0.0, 0.0),
                GeoPosition(4.0, 0.0),
                GeoPosition(4.0, 4.0),
                GeoPosition(0.0, 4.0),
                GeoPosition(0.0, 0.0),
            )
        val hole =
            listOf(
                GeoPosition(1.0, 1.0),
                GeoPosition(3.0, 1.0),
                GeoPosition(3.0, 3.0),
                GeoPosition(1.0, 3.0),
                GeoPosition(1.0, 1.0),
            )
        val poly = GeoPolygon(outerRing = outer, holes = listOf(hole))
        assertTrue(BooleanPointInPolygon.contains(GeoPosition(0.5, 0.5), poly))
        assertTrue(BooleanPointInPolygon.contains(GeoPosition(3.5, 3.5), poly))
        assertFalse(BooleanPointInPolygon.contains(GeoPosition(2.0, 2.0), poly))
    }

    @Test
    fun bounding_box_from_line_and_polygon_round_trip() {
        val line = GeoLineString(listOf(GeoPosition(-10.0, -5.0), GeoPosition(3.0, 8.0)))
        val bb = line.boundingBox()
        assertEquals(-10.0, bb.west, 1e-9)
        assertEquals(3.0, bb.east, 1e-9)
        assertEquals(-5.0, bb.south, 1e-9)
        assertEquals(8.0, bb.north, 1e-9)
        val back = bb.toPolygon().boundingBox()
        assertEquals(bb.west, back.west, 1e-9)
        assertEquals(bb.east, back.east, 1e-9)
        assertEquals(bb.south, back.south, 1e-9)
        assertEquals(bb.north, back.north, 1e-9)
    }

    @Test
    fun geo_bounding_box_from_positions() {
        val bb = GeoBoundingBox.fromPositions(listOf(GeoPosition(1.0, 2.0), GeoPosition(-1.0, 3.0)))
        assertEquals(-1.0, bb.west, 1e-9)
        assertEquals(1.0, bb.east, 1e-9)
        assertEquals(2.0, bb.south, 1e-9)
        assertEquals(3.0, bb.north, 1e-9)
    }
}
