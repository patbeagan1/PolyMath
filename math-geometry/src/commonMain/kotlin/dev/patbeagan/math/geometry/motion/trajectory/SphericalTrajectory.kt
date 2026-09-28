package dev.patbeagan.math.geometry.motion.trajectory

import com.measures.distance.Meter
import dev.patbeagan.math.geometry.geospatial.measure.GeodesicWgs84
import dev.patbeagan.math.geometry.geospatial.model.GeoLineString
import dev.patbeagan.math.geometry.geospatial.model.GeoPosition
import dev.patbeagan.math.geometry.geospatial.model.MultiGeoLineString
import dev.patbeagan.math.geometry.geospatial.model.Point3D
import dev.patbeagan.math.geometry.geospatial.spaces.GeometryBasis
import dev.patbeagan.math.geometry.motion.internal.geoPositionToUnitVector
import dev.patbeagan.math.geometry.motion.internal.normalizeVector
import dev.patbeagan.math.geometry.motion.internal.unitVectorToGeoPosition
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin

/**
 * Great-circle trajectories on the WGS84 mean sphere: arc-length travel along polylines and
 * spherical linear interpolation between two positions.
 */
class SphericalTrajectory(
    private val earthRadiusMeters: Double = GeometryBasis.DEFAULT_WGS84_MEAN_RADIUS_METERS,
) {
    init {
        require(earthRadiusMeters > 0.0) { "Earth radius must be positive" }
    }

    private val geodesic = GeodesicWgs84(earthRadiusMeters)

    fun along(lineString: GeoLineString, distance: Meter): GeoPosition {
        return geodesic.along(lineString, distance)
    }

    /**
     * Point at [distance] meters along the concatenated [multiLineString] parts (each part is traversed in order).
     */
    fun along(multiLineString: MultiGeoLineString, distance: Meter): GeoPosition {
        val parts = multiLineString.lineStrings
        require(parts.isNotEmpty()) { "MultiLineString must have at least one LineString" }
        var remaining = distance.value
        if (remaining <= 0.0) {
            return parts.first().coordinates.first()
        }
        for (ls in parts) {
            val len = geodesic.length(ls).value
            if (remaining <= len) {
                return geodesic.along(ls, Meter(remaining))
            }
            remaining -= len
        }
        return parts.last().coordinates.last()
    }

    /**
     * Point at fraction [t] in `[0, 1]` along the shortest great-circle arc from [a] to [b].
     * When [a] and [b] are antipodal, any great circle applies; this returns [a] for `t < 0.5` and [b] otherwise.
     */
    fun interpolateGreatCircle(a: GeoPosition, b: GeoPosition, t: Double): GeoPosition {
        val u = t.coerceIn(0.0, 1.0)
        val v0 = geoPositionToUnitVector(a)
        val v1 = geoPositionToUnitVector(b)
        val dot = (v0.x * v1.x + v0.y * v1.y + v0.z * v1.z).coerceIn(-1.0, 1.0)
        return if (1.0 + dot < 1e-12) {
            if (u < 0.5) a else b
        } else if (1.0 - dot < 1e-14) {
            a
        } else {
            val omega = acos(dot)
            if (omega < 1e-12) {
                a
            } else {
                val s0 = sin((1.0 - u) * omega) / sin(omega)
                val s1 = sin(u * omega) / sin(omega)
                val x = s0 * v0.x + s1 * v1.x
                val y = s0 * v0.y + s1 * v1.y
                val z = s0 * v0.z + s1 * v1.z
                unitVectorToGeoPosition(normalizeVector(Point3D(x, y, z)))
            }
        }
    }

    /**
     * Unit tangent direction (in ECEF unit-vector form) on the great-circle arc from [a] to [b].
     */
    fun unitTangentEcef(a: GeoPosition, b: GeoPosition, t: Double): Point3D {
        val u = t.coerceIn(0.0, 1.0)
        val v0 = geoPositionToUnitVector(a)
        val v1 = geoPositionToUnitVector(b)
        val dot = (v0.x * v1.x + v0.y * v1.y + v0.z * v1.z).coerceIn(-1.0, 1.0)
        if (1.0 + dot < 1e-12) return Point3D(0.0, 1.0, 0.0)
        if (1.0 - dot < 1e-14) return Point3D(1.0, 0.0, 0.0)
        val omega = acos(dot)
        if (omega < 1e-12) return Point3D(1.0, 0.0, 0.0)
        val ds0 = -omega * cos((1.0 - u) * omega) / sin(omega)
        val ds1 = omega * cos(u * omega) / sin(omega)
        val tx = ds0 * v0.x + ds1 * v1.x
        val ty = ds0 * v0.y + ds1 * v1.y
        val tz = ds0 * v0.z + ds1 * v1.z
        return normalizeVector(Point3D(tx, ty, tz))
    }

    /**
     * Initial bearing from [from] to [to] in degrees clockwise from north (delegates to [GeodesicWgs84]).
     */
    fun bearingDegrees(from: GeoPosition, to: GeoPosition): Double {
        return geodesic.bearingDegrees(from, to)
    }

    companion object {
        /** Arc length in meters of the great-circle segment between [a] and [b] on a sphere of radius [earthRadiusMeters]. */
        fun greatCircleArcLengthMeters(
            a: GeoPosition,
            b: GeoPosition,
            earthRadiusMeters: Double = GeometryBasis.DEFAULT_WGS84_MEAN_RADIUS_METERS,
        ): Double {
            val v0 = geoPositionToUnitVector(a)
            val v1 = geoPositionToUnitVector(b)
            val dot = (v0.x * v1.x + v0.y * v1.y + v0.z * v1.z).coerceIn(-1.0, 1.0)
            val omega = acos(dot)
            return earthRadiusMeters * omega
        }
    }
}
