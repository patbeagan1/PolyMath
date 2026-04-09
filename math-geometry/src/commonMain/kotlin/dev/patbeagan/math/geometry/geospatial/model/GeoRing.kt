package dev.patbeagan.math.geometry.geospatial.model

/**
 * GeoJSON rings are often closed by repeating the first vertex; algorithms that expect unique
 * vertices use this to drop the duplicate closing point when present.
 */
internal fun List<GeoPosition>.dropClosingDuplicate(): List<GeoPosition> {
    if (size < 2) return this
    val l = first()
    val r = last()
    return if (l.longitude == r.longitude && l.latitude == r.latitude) dropLast(1) else this
}
