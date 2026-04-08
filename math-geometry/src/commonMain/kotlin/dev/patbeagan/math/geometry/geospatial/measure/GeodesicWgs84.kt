package dev.patbeagan.math.geometry.geospatial.measure

import com.measures.distance.Meter
import dev.patbeagan.math.geometry.geospatial.model.GeoLineString
import dev.patbeagan.math.geometry.geospatial.model.GeoPosition
import dev.patbeagan.math.geometry.geospatial.model.MultiGeoLineString
import dev.patbeagan.math.geometry.geospatial.spaces.GeometryBasis
import kotlin.math.asin
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.PI

private fun degToRad(deg: Double): Double = deg * (PI / 180.0)

private fun radToDeg(rad: Double): Double = rad * (180.0 / PI)

/**
 * Great-circle (haversine) distance and path length on a sphere, using the mean Earth radius
 * from [GeometryBasis.GlobeWgs84MeanSphere] by default (Mapbox / Turf compatible).
 */
class GeodesicWgs84(
    private val earthRadiusMeters: Double = GeometryBasis.DEFAULT_WGS84_MEAN_RADIUS_METERS,
) {
    init {
        require(earthRadiusMeters > 0.0) { "Earth radius must be positive" }
    }

    fun distance(a: GeoPosition, b: GeoPosition): Meter =
        Meter(haversineMeters(a.longitude, a.latitude, b.longitude, b.latitude, earthRadiusMeters))

    fun length(lineString: GeoLineString): Meter =
        lineString.coordinates
            .asSequence()
            .zipWithNext()
            .map { (p, q) -> distance(p, q) }
            .fold(Meter(0.0)) { acc, d -> acc + d }

    fun length(multiLineString: MultiGeoLineString): Meter =
        multiLineString.lineStrings
            .asSequence()
            .map { length(it) }
            .fold(Meter(0.0)) { acc, d -> acc + d }

    /**
     * Initial bearing from [from] to [to] in degrees clockwise from true north (0–360),
     * matching common geodesic-bearing conventions used in web mapping.
     */
    fun bearingDegrees(from: GeoPosition, to: GeoPosition): Double {
        val φ1 = degToRad(from.latitude)
        val φ2 = degToRad(to.latitude)
        val Δλ = degToRad(to.longitude - from.longitude)
        val y = sin(Δλ) * cos(φ2)
        val x = cos(φ1) * sin(φ2) - sin(φ1) * cos(φ2) * cos(Δλ)
        val θ = atan2(y, x)
        val deg = (radToDeg(θ) + 360.0) % 360.0
        return deg
    }

    companion object {
        internal fun haversineMeters(
            lon1: Double,
            lat1: Double,
            lon2: Double,
            lat2: Double,
            radiusMeters: Double,
        ): Double {
            val φ1 = degToRad(lat1)
            val φ2 = degToRad(lat2)
            val Δφ = degToRad(lat2 - lat1)
            val Δλ = degToRad(lon2 - lon1)
            val sinΔφ2 = sin(Δφ / 2.0)
            val sinΔλ2 = sin(Δλ / 2.0)
            val a = sinΔφ2 * sinΔφ2 + cos(φ1) * cos(φ2) * sinΔλ2 * sinΔλ2
            val c = 2.0 * asin(sqrt(a.coerceIn(0.0, 1.0)))
            return radiusMeters * c
        }
    }
}

/**
 * When this basis describes a WGS84 mean sphere, returns the matching [GeodesicWgs84] calculator;
 * Euclidean bases return null.
 */
fun GeometryBasis.toGeodesicCalculator(): GeodesicWgs84? =
    when (this) {
        is GeometryBasis.GlobeWgs84MeanSphere -> GeodesicWgs84(meanEarthRadiusMeters)
        GeometryBasis.EuclideanPlaneMeters,
        GeometryBasis.EuclideanSpaceMeters,
        -> null
    }
