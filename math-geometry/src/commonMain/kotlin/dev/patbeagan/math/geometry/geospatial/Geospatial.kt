package dev.patbeagan.math.geometry.geospatial

import dev.patbeagan.math.geometry.geospatial.measure.Euclidean2D
import dev.patbeagan.math.geometry.geospatial.measure.Euclidean3D
import dev.patbeagan.math.geometry.geospatial.measure.GeodesicWgs84
import dev.patbeagan.math.geometry.geospatial.spaces.GeometryBasis
import dev.patbeagan.math.geometry.motion.rotation.EuclideanRotation2D
import dev.patbeagan.math.geometry.motion.rotation.EuclideanRotation3D
import dev.patbeagan.math.geometry.motion.rotation.SphericalRotation
import dev.patbeagan.math.geometry.motion.trajectory.EuclideanTrajectory2D
import dev.patbeagan.math.geometry.motion.trajectory.EuclideanTrajectory3D
import dev.patbeagan.math.geometry.motion.trajectory.SphericalTrajectory

/**
 * Entry point for Mapbox / Turf-style geospatial work: planar Euclidean measurements, WGS84 geodesic
 * distance and path operations (`destination`, `midpoint`, `along`, polygon `area`), plus supporting
 * types under `dev.patbeagan.math.geometry.geospatial.model` and `.predicate`.
 *
 * Trajectory and rotation systems for 2D, 3D, and the WGS84 mean sphere are exposed alongside
 * measurement calculators; see also [dev.patbeagan.math.geometry.GeometryMotion].
 */
object Geospatial {
    val euclidean2D = Euclidean2D
    val euclidean3D = Euclidean3D

    val trajectory2D = EuclideanTrajectory2D
    val trajectory3D = EuclideanTrajectory3D
    val rotation2D = EuclideanRotation2D
    val rotation3D = EuclideanRotation3D

    fun wgs84Geodesic(
        meanEarthRadiusMeters: Double = GeometryBasis.DEFAULT_WGS84_MEAN_RADIUS_METERS,
    ): GeodesicWgs84 = GeodesicWgs84(meanEarthRadiusMeters)

    fun wgs84Trajectory(
        meanEarthRadiusMeters: Double = GeometryBasis.DEFAULT_WGS84_MEAN_RADIUS_METERS,
    ): SphericalTrajectory = SphericalTrajectory(meanEarthRadiusMeters)

    fun wgs84Rotation(
        meanEarthRadiusMeters: Double = GeometryBasis.DEFAULT_WGS84_MEAN_RADIUS_METERS,
    ): SphericalRotation = SphericalRotation(meanEarthRadiusMeters)
}
