package dev.patbeagan.math.geometry.geospatial.model

/**
 * Multiple disconnected [LineString3D] parts.
 */
data class MultiLineString3D(
    val lineStrings: List<LineString3D>,
)
