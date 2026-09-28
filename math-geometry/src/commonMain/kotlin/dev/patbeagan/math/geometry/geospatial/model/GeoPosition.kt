package dev.patbeagan.math.geometry.geospatial.model

/**
 * A geographic position on the globe using GeoJSON axis order: [longitude] then [latitude],
 * both in decimal degrees (EPSG:4326-style). Altitude is not modeled; distances use the WGS84
 * mean spherical Earth approximation used by [dev.patbeagan.math.geometry.geospatial.measure.GeodesicWgs84].
 */
data class GeoPosition(
    val longitude: Double,
    val latitude: Double,
)
