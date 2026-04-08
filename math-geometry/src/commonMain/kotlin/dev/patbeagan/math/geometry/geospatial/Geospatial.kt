package dev.patbeagan.math.geometry.geospatial

import dev.patbeagan.math.geometry.geospatial.measure.Euclidean2D
import dev.patbeagan.math.geometry.geospatial.measure.Euclidean3D
import dev.patbeagan.math.geometry.geospatial.measure.GeodesicWgs84
import dev.patbeagan.math.geometry.geospatial.spaces.GeometryBasis

/**
 * Entry point for mapbox-turf-style geospatial measurements: planar 2D/3D Euclidean paths and
 * great-circle paths on the WGS84 mean sphere, all returning [com.measures.distance.Meter].
 */
object Geospatial {
    val euclidean2D = Euclidean2D
    val euclidean3D = Euclidean3D

    fun wgs84Geodesic(
        meanEarthRadiusMeters: Double = GeometryBasis.DEFAULT_WGS84_MEAN_RADIUS_METERS,
    ): GeodesicWgs84 = GeodesicWgs84(meanEarthRadiusMeters)
}
