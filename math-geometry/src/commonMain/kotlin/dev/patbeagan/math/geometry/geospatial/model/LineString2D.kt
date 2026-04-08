package dev.patbeagan.math.geometry.geospatial.model

/**
 * A connected path of [Point2D] vertices in order, analogous to a GeoJSON `LineString`
 * (without CRS metadata). An empty or single-point string has zero path length.
 */
data class LineString2D(
    val coordinates: List<Point2D>,
)
