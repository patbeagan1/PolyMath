package dev.patbeagan.math.geometry.geospatial

import dev.patbeagan.math.geometry.geospatial.measure.Euclidean2D
import dev.patbeagan.math.geometry.geospatial.measure.Euclidean3D
import dev.patbeagan.math.geometry.geospatial.measure.GeodesicWgs84
import dev.patbeagan.math.geometry.geospatial.spaces.GeometryBasis

/**
 * Entry point for Mapbox / Turf-style geospatial work: planar Euclidean measurements, WGS84 geodesic
 * distance and path operations (`destination`, `midpoint`, `along`, polygon `area`), plus supporting
 * types under `dev.patbeagan.math.geometry.geospatial.model` and `.predicate`.
 */
object Geospatial {
    val euclidean2D = Euclidean2D
    val euclidean3D = Euclidean3D

    fun wgs84Geodesic(
        meanEarthRadiusMeters: Double = GeometryBasis.DEFAULT_WGS84_MEAN_RADIUS_METERS,
    ): GeodesicWgs84 = GeodesicWgs84(meanEarthRadiusMeters)
}
