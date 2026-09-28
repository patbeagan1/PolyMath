package dev.patbeagan.math.geometry.geospatial.model

/**
 * Axis-aligned bounds in geographic coordinates: [west], [south], [east], [north] in decimal degrees.
 * Matches GeoJSON bbox order `[west, south, east, north]`.
 */
data class GeoBoundingBox(
    val west: Double,
    val south: Double,
    val east: Double,
    val north: Double,
) {
    init {
        require(south <= north) { "south ($south) must be <= north ($north)" }
    }

    /**
     * Builds a rectangular polygon from this bbox (counter-clockwise outer ring, closed).
     */
    fun toPolygon(): GeoPolygon {
        val ring =
            listOf(
                GeoPosition(west, south),
                GeoPosition(east, south),
                GeoPosition(east, north),
                GeoPosition(west, north),
                GeoPosition(west, south),
            )
        return GeoPolygon(outerRing = ring)
    }

    companion object {
        fun fromPositions(positions: Iterable<GeoPosition>): GeoBoundingBox {
            val it = positions.iterator()
            require(it.hasNext()) { "need at least one position" }
            val first = it.next()
            var w = first.longitude
            var s = first.latitude
            var e = first.longitude
            var n = first.latitude
            while (it.hasNext()) {
                val p = it.next()
                w = kotlin.math.min(w, p.longitude)
                e = kotlin.math.max(e, p.longitude)
                s = kotlin.math.min(s, p.latitude)
                n = kotlin.math.max(n, p.latitude)
            }
            return GeoBoundingBox(west = w, south = s, east = e, north = n)
        }
    }
}
