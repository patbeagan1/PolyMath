package dev.patbeagan.math.geometry.geospatial.model

/**
 * A geographic line string: an ordered sequence of [GeoPosition] values (GeoJSON `LineString` shape).
 */
data class GeoLineString(
    val coordinates: List<GeoPosition>,
)
