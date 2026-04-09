package dev.patbeagan.math.geometry.geospatial.model

/**
 * GeoJSON-style polygon: an ordered [outerRing] (exterior) plus optional [holes].
 * Rings are typically closed (first position equals the last); algorithms normalize that form.
 */
data class GeoPolygon(
    val outerRing: List<GeoPosition>,
    val holes: List<List<GeoPosition>> = emptyList(),
)
