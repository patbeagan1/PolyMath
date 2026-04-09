package dev.patbeagan.math.geometry.geospatial.measure

import com.measures.area.SquareMeter
import com.measures.distance.Meter
import dev.patbeagan.math.geometry.geospatial.model.GeoLineString
import dev.patbeagan.math.geometry.geospatial.model.GeoPolygon
import dev.patbeagan.math.geometry.geospatial.model.GeoPosition
import dev.patbeagan.math.geometry.geospatial.model.MultiGeoLineString
import dev.patbeagan.math.geometry.geospatial.model.MultiGeoPolygon
import dev.patbeagan.math.geometry.geospatial.model.dropClosingDuplicate
import dev.patbeagan.math.geometry.geospatial.spaces.GeometryBasis
import kotlin.math.abs
import kotlin.math.asin
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.hypot
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

    /**
     * Travels [distance] meters along a great circle from [origin] at initial [bearingDegrees]
     * clockwise from north (Mapbox / Turf `@turf/destination`).
     */
    fun destination(origin: GeoPosition, bearingDegrees: Double, distance: Meter): GeoPosition {
        val δ = distance.value / earthRadiusMeters
        val θ = degToRad(bearingDegrees)
        val φ1 = degToRad(origin.latitude)
        val λ1 = degToRad(origin.longitude)
        val sinφ1 = sin(φ1)
        val cosφ1 = cos(φ1)
        val sinδ = sin(δ)
        val cosδ = cos(δ)
        val sinφ2 = sinφ1 * cosδ + cosφ1 * sinδ * cos(θ)
        val φ2 = asin(sinφ2.coerceIn(-1.0, 1.0))
        val y = sin(θ) * sinδ * cosφ1
        val x = cosδ - sinφ1 * sinφ2
        val λ2 = λ1 + atan2(y, x)
        return GeoPosition(longitude = radToDeg(λ2), latitude = radToDeg(φ2))
    }

    /**
     * Midpoint on the great-circle path between [a] and [b] (Turf `@turf/midpoint`).
     */
    fun midpoint(a: GeoPosition, b: GeoPosition): GeoPosition {
        val φ1 = degToRad(a.latitude)
        val λ1 = degToRad(a.longitude)
        val φ2 = degToRad(b.latitude)
        val λ2 = degToRad(b.longitude)
        val x1 = cos(φ1) * cos(λ1)
        val y1 = cos(φ1) * sin(λ1)
        val z1 = sin(φ1)
        val x2 = cos(φ2) * cos(λ2)
        val y2 = cos(φ2) * sin(λ2)
        val z2 = sin(φ2)
        var x = x1 + x2
        var y = y1 + y2
        var z = z1 + z2
        val h = hypot(hypot(x, y), z)
        if (h < 1e-12) {
            return GeoPosition((a.longitude + b.longitude) / 2.0, (a.latitude + b.latitude) / 2.0)
        }
        x /= h
        y /= h
        z /= h
        val φm = asin(z.coerceIn(-1.0, 1.0))
        val λm = atan2(y, x)
        return GeoPosition(longitude = radToDeg(λm), latitude = radToDeg(φm))
    }

    /**
     * Point at [distance] meters along the geodesic path of [lineString] from the start vertex.
     * Shorter than zero snaps to the first coordinate; longer than total length snaps to the last
     * (Turf `@turf/along`).
     */
    fun along(lineString: GeoLineString, distance: Meter): GeoPosition {
        val coords = lineString.coordinates
        require(coords.isNotEmpty()) { "LineString must have at least one coordinate" }
        if (coords.size < 2) return coords.first()
        var remaining = distance.value
        if (remaining <= 0.0) return coords.first()
        for (i in 0 until coords.size - 1) {
            val p = coords[i]
            val q = coords[i + 1]
            val segLen = distance(p, q).value
            if (segLen < 1e-9) continue
            if (remaining <= segLen) {
                val brg = bearingDegrees(p, q)
                return destination(p, brg, Meter(remaining))
            }
            remaining -= segLen
        }
        return coords.last()
    }

    /**
     * Spherical excess area on the WGS84 mean sphere (Chamberlain–Duquette type formula used by
     * common web-mapping stacks; matches Turf `@turf/area` for geographic polygons).
     */
    fun area(polygon: GeoPolygon): SquareMeter {
        var total = signedRingAreaSquareMeters(polygon.outerRing.dropClosingDuplicate())
        for (hole in polygon.holes) {
            total -= signedRingAreaSquareMeters(hole.dropClosingDuplicate())
        }
        return SquareMeter(abs(total))
    }

    fun area(multiPolygon: MultiGeoPolygon): SquareMeter =
        multiPolygon.polygons.fold(SquareMeter(0.0)) { acc, p -> acc + area(p) }

    private fun signedRingAreaSquareMeters(ring: List<GeoPosition>): Double {
        if (ring.size < 3) return 0.0
        var sum = 0.0
        val n = ring.size
        for (i in 0 until n) {
            val p1 = ring[i]
            val p2 = ring[(i + 1) % n]
            val λ1 = degToRad(p1.longitude)
            val φ1 = degToRad(p1.latitude)
            val λ2 = degToRad(p2.longitude)
            val φ2 = degToRad(p2.latitude)
            sum += (λ2 - λ1) * (2 + sin(φ1) + sin(φ2))
        }
        return earthRadiusMeters * earthRadiusMeters * 0.5 * sum
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
