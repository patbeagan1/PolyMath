package dev.patbeagan.math.geometry.motion

import com.measures.angle.Radian
import com.measures.distance.Meter
import dev.patbeagan.math.geometry.GeometryMotion
import dev.patbeagan.math.geometry.geospatial.Geospatial
import dev.patbeagan.math.geometry.geospatial.measure.GeodesicWgs84
import dev.patbeagan.math.geometry.geospatial.model.GeoLineString
import dev.patbeagan.math.geometry.geospatial.model.GeoPosition
import dev.patbeagan.math.geometry.geospatial.model.LineString2D
import dev.patbeagan.math.geometry.geospatial.model.LineString3D
import dev.patbeagan.math.geometry.geospatial.model.Point2D
import dev.patbeagan.math.geometry.geospatial.model.Point3D
import dev.patbeagan.math.geometry.motion.trajectory.SphericalTrajectory
import kotlin.math.PI
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MotionTrajectoryAndRotationTest {
    @Test
    fun euclideanTrajectory2D_along_matches_segment_interpolation() {
        val line = LineString2D(listOf(Point2D(0.0, 0.0), Point2D(10.0, 0.0)))
        val p = GeometryMotion.trajectoryEuclidean2D.along(line, Meter(3.0))
        assertEquals(3.0, p.x, 1e-9)
        assertEquals(0.0, p.y, 1e-9)
    }

    @Test
    fun euclideanTrajectory2D_unit_tangent() {
        val t = GeometryMotion.trajectoryEuclidean2D.unitTangentOnSegment(
            Point2D(0.0, 0.0),
            Point2D(0.0, 5.0),
        )
        assertEquals(0.0, t.x, 1e-9)
        assertEquals(1.0, t.y, 1e-9)
    }

    @Test
    fun euclideanTrajectory3D_along() {
        val line =
            LineString3D(
                listOf(
                    Point3D(0.0, 0.0, 0.0),
                    Point3D(3.0, 4.0, 0.0),
                ),
            )
        val p = GeometryMotion.trajectoryEuclidean3D.along(line, Meter(2.5))
        assertEquals(1.5, p.x, 1e-9)
        assertEquals(2.0, p.y, 1e-9)
        assertEquals(0.0, p.z, 1e-9)
    }

    @Test
    fun sphericalTrajectory_interpolate_endpoints() {
        val tr = GeometryMotion.trajectorySphericalMeanEarth()
        val a = GeoPosition(0.0, 0.0)
        val b = GeoPosition(1.0, 0.0)
        val p0 = tr.interpolateGreatCircle(a, b, 0.0)
        val p1 = tr.interpolateGreatCircle(a, b, 1.0)
        assertEquals(a.longitude, p0.longitude, 1e-6)
        assertEquals(a.latitude, p0.latitude, 1e-6)
        assertEquals(b.longitude, p1.longitude, 1e-6)
        assertEquals(b.latitude, p1.latitude, 1e-6)
    }

    @Test
    fun sphericalTrajectory_along_delegates_to_geodesic() {
        val geo = GeodesicWgs84()
        val tr = Geospatial.wgs84Trajectory()
        val line = GeoLineString(listOf(GeoPosition(0.0, 0.0), GeoPosition(2.0, 0.0)))
        val half = geo.length(line).value / 2.0
        assertEquals(
            geo.along(line, Meter(half)).longitude,
            tr.along(line, Meter(half)).longitude,
            1e-6,
        )
    }

    @Test
    fun sphericalTrajectory_great_circle_arc_length_consistent() {
        val a = GeoPosition(0.0, 0.0)
        val b = GeoPosition(1.0, 0.0)
        val len = SphericalTrajectory.greatCircleArcLengthMeters(a, b)
        assertTrue(len in 111_190.0..111_200.0)
    }

    @Test
    fun euclideanRotation2D_quarter_turn() {
        val p = GeometryMotion.rotationEuclidean2D.rotate(
            Point2D(1.0, 0.0),
            Radian(PI / 2.0),
            Point2D(0.0, 0.0),
        )
        assertEquals(0.0, p.x, 1e-9)
        assertEquals(1.0, p.y, 1e-9)
    }

    @Test
    fun euclideanRotation3D_around_z() {
        val p = GeometryMotion.rotationEuclidean3D.rotate(
            Point3D(1.0, 0.0, 0.0),
            Point3D(0.0, 0.0, 1.0),
            Radian(PI / 2.0),
        )
        assertEquals(0.0, p.x, 1e-9)
        assertEquals(1.0, p.y, 1e-9)
        assertEquals(0.0, p.z, 1e-9)
    }

    @Test
    fun sphericalRotation_around_north_pole_shifts_longitude() {
        val rot = GeometryMotion.rotationSphericalMeanEarth()
        val equator = GeoPosition(0.0, 0.0)
        val northPole = GeoPosition(0.0, 90.0)
        val moved = rot.rotateAroundAxis(equator, northPole, Radian(PI / 2.0))
        assertTrue(moved.latitude in -0.01..0.01)
        assertTrue(moved.longitude in 89.0..91.0)
    }

    @Test
    fun sphericalRotation_geodesic_distance_matches_haversine() {
        val rot = Geospatial.wgs84Rotation()
        val geo = GeodesicWgs84()
        val a = GeoPosition(0.0, 0.0)
        val b = GeoPosition(2.0, 1.0)
        assertEquals(geo.distance(a, b).value, rot.geodesicDistance(a, b).value, 0.5)
    }

    @Test
    fun geospatial_facade_exposes_motion() {
        assertEquals(
            Geospatial.trajectory2D.along(
                LineString2D(listOf(Point2D(0.0, 0.0), Point2D(1.0, 0.0))),
                Meter(0.5),
            ).x,
            0.5,
            1e-9,
        )
    }
}
