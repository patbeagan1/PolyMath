package dev.patbeagan.math.geometry.geospatial.spaces

/**
 * Identifies which geometric model applies when interpreting coordinates and computing distances.
 * This is a closed set of bases supported by the turf-style measurement API.
 */
sealed interface GeometryBasis {
    /** Planar Euclidean geometry in meters (XY). */
    data object EuclideanPlaneMeters : GeometryBasis

    /** Three-dimensional Euclidean geometry in meters (XYZ). */
    data object EuclideanSpaceMeters : GeometryBasis

    /**
     * Great-circle (haversine) distances on a sphere with [meanEarthRadiusMeters],
     * consistent with common web-mapping stacks (e.g. Mapbox / Turf WGS84 mean radius).
     */
    data class GlobeWgs84MeanSphere(
        val meanEarthRadiusMeters: Double = DEFAULT_WGS84_MEAN_RADIUS_METERS,
    ) : GeometryBasis {
        init {
            require(meanEarthRadiusMeters > 0.0) { "Earth radius must be positive" }
        }
    }

    companion object {
        /** WGS84 mean Earth radius used by Turf / Mapbox-style calculations (meters). */
        const val DEFAULT_WGS84_MEAN_RADIUS_METERS: Double = 6371008.8
    }
}
