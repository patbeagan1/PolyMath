package dev.patbeagan.math.geometry.motion.rotation

import com.measures.angle.Radian
import com.measures.distance.Meter
import dev.patbeagan.math.geometry.geospatial.model.GeoPosition
import dev.patbeagan.math.geometry.geospatial.model.Point3D
import dev.patbeagan.math.geometry.geospatial.spaces.GeometryBasis
import dev.patbeagan.math.geometry.motion.internal.geoPositionToUnitVector
import dev.patbeagan.math.geometry.motion.internal.normalizeVector
import dev.patbeagan.math.geometry.motion.internal.unitVectorToGeoPosition
import kotlin.math.cos
import kotlin.math.hypot
import kotlin.math.sin

/**
 * Rotations on the WGS84 mean sphere: positions are represented as [GeoPosition] and the rotation axis
 * is the axis through the Earth center and [axisPole] (e.g. North Pole for longitude-preserving rotations).
 */
class SphericalRotation(
    private val earthRadiusMeters: Double = GeometryBasis.DEFAULT_WGS84_MEAN_RADIUS_METERS,
) {
    init {
        require(earthRadiusMeters > 0.0) { "Earth radius must be positive" }
    }

    /**
     * Great-circle distance between [a] and [b] on this sphere (same model as [dev.patbeagan.math.geometry.geospatial.measure.GeodesicWgs84]).
     */
    fun geodesicDistance(a: GeoPosition, b: GeoPosition): Meter {
        return Meter(earthRadiusMeters * angularDistanceRadians(a, b))
    }

    /**
     * Rotates [point] around the axis through the sphere center and [axisPole] by [angle] (right-hand rule).
     */
    fun rotateAroundAxis(point: GeoPosition, axisPole: GeoPosition, angle: Radian): GeoPosition {
        val p = geoPositionToUnitVector(point)
        val k = normalizeVector(geoPositionToUnitVector(axisPole))
        val rotated = rodrigues(p, k, angle.value)
        return unitVectorToGeoPosition(rotated)
    }

    private fun rodrigues(v: Point3D, k: Point3D, θ: Double): Point3D {
        val cosθ = cos(θ)
        val sinθ = sin(θ)
        val dot = k.x * v.x + k.y * v.y + k.z * v.z
        val cx = k.y * v.z - k.z * v.y
        val cy = k.z * v.x - k.x * v.z
        val cz = k.x * v.y - k.y * v.x
        return Point3D(
            v.x * cosθ + cx * sinθ + k.x * dot * (1.0 - cosθ),
            v.y * cosθ + cy * sinθ + k.y * dot * (1.0 - cosθ),
            v.z * cosθ + cz * sinθ + k.z * dot * (1.0 - cosθ),
        )
    }

    companion object {
        /**
         * Angular distance in radians on a unit sphere between two geographic positions.
         */
        fun angularDistanceRadians(a: GeoPosition, b: GeoPosition): Double {
            val v0 = geoPositionToUnitVector(a)
            val v1 = geoPositionToUnitVector(b)
            val dot = (v0.x * v1.x + v0.y * v1.y + v0.z * v1.z).coerceIn(-1.0, 1.0)
            val n0 = hypot(hypot(v0.x, v0.y), v0.z)
            val n1 = hypot(hypot(v1.x, v1.y), v1.z)
            if (n0 < 1e-15 || n1 < 1e-15) return 0.0
            val c = dot / (n0 * n1)
            return kotlin.math.acos(c.coerceIn(-1.0, 1.0))
        }
    }
}
