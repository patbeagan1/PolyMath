package dev.patbeagan.math.geometry.geospatial.model

/**
 * GeoJSON `MultiPolygon` shape: multiple [GeoPolygon] parts.
 */
data class MultiGeoPolygon(
    val polygons: List<GeoPolygon>,
)
