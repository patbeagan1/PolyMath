package dev.patbeagan.math.geometry.geospatial.model

/**
 * Multiple disconnected [LineString2D] parts, analogous to GeoJSON `MultiLineString`.
 */
data class MultiLineString2D(
    val lineStrings: List<LineString2D>,
)
