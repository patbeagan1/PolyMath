package dev.patbeagan.math.geometry

import dev.patbeagan.math.geometry.geospatial.spaces.GeometryBasis
import dev.patbeagan.math.geometry.motion.rotation.EuclideanRotation2D
import dev.patbeagan.math.geometry.motion.rotation.EuclideanRotation3D
import dev.patbeagan.math.geometry.motion.rotation.SphericalRotation
import dev.patbeagan.math.geometry.motion.trajectory.EuclideanTrajectory2D
import dev.patbeagan.math.geometry.motion.trajectory.EuclideanTrajectory3D
import dev.patbeagan.math.geometry.motion.trajectory.SphericalTrajectory

/**
 * Trajectory and rotation systems for planar Euclidean, 3D Euclidean, and WGS84 mean-sphere geometry,
 * mirroring the facade style of [Geospatial].
 */
object GeometryMotion {
    val trajectoryEuclidean2D = EuclideanTrajectory2D
    val trajectoryEuclidean3D = EuclideanTrajectory3D
    val rotationEuclidean2D = EuclideanRotation2D
    val rotationEuclidean3D = EuclideanRotation3D

    fun trajectorySphericalMeanEarth(
        meanEarthRadiusMeters: Double = GeometryBasis.DEFAULT_WGS84_MEAN_RADIUS_METERS,
    ): SphericalTrajectory {
        return SphericalTrajectory(meanEarthRadiusMeters)
    }

    fun rotationSphericalMeanEarth(
        meanEarthRadiusMeters: Double = GeometryBasis.DEFAULT_WGS84_MEAN_RADIUS_METERS,
    ): SphericalRotation {
        return SphericalRotation(meanEarthRadiusMeters)
    }
}
