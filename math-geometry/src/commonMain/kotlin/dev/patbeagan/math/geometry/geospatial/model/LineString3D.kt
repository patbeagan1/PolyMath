package dev.patbeagan.math.geometry.geospatial.model

/**
 * A connected path of [Point3D] vertices in order. Empty or single-point strings have zero path length.
 */
data class LineString3D(
    val coordinates: List<Point3D>,
)
