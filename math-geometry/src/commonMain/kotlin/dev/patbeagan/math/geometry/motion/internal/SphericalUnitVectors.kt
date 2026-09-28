package dev.patbeagan.math.geometry.motion.internal

import dev.patbeagan.math.geometry.geospatial.model.GeoPosition
import dev.patbeagan.math.geometry.geospatial.model.Point3D
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.hypot
import kotlin.math.sin

private fun degToRad(deg: Double): Double = deg * (PI / 180.0)

private fun radToDeg(rad: Double): Double = rad * (180.0 / PI)

/**
 * Unit vector in ECEF-style coordinates for the WGS84 mean sphere (x = east, y = north from equator prime meridian).
 */
internal fun geoPositionToUnitVector(p: GeoPosition): Point3D {
    val φ = degToRad(p.latitude)
    val λ = degToRad(p.longitude)
    val cosφ = cos(φ)
    return Point3D(
        x = cosφ * cos(λ),
        y = cosφ * sin(λ),
        z = sin(φ),
    )
}

internal fun unitVectorToGeoPosition(v: Point3D): GeoPosition {
    val x = v.x
    val y = v.y
    val z = v.z
    val h = hypot(x, y)
    val lat = atan2(z, h)
    val lon = atan2(y, x)
    return GeoPosition(longitude = radToDeg(lon), latitude = radToDeg(lat))
}

internal fun normalizeVector(v: Point3D): Point3D {
    val n = hypot(hypot(v.x, v.y), v.z)
    if (n < 1e-15) return Point3D(1.0, 0.0, 0.0)
    return Point3D(v.x / n, v.y / n, v.z / n)
}
