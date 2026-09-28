package dev.patbeagan.math.geometry.geospatial.model

/**
 * Multiple disconnected [GeoLineString] parts (GeoJSON `MultiLineString` shape).
 */
data class MultiGeoLineString(
    val lineStrings: List<GeoLineString>,
)
