package dev.patbeagan.math.geometry.geospatial.predicate

import dev.patbeagan.math.geometry.geospatial.model.GeoPolygon
import dev.patbeagan.math.geometry.geospatial.model.GeoPosition
import dev.patbeagan.math.geometry.geospatial.model.dropClosingDuplicate
import kotlin.math.abs

/**
 * Planar point-in-polygon test on longitude/latitude (same approach as Turf `@turf/boolean-point-in-polygon`
 * for EPSG:4326 geometries): ray casting with an even-odd rule on the outer ring and subtracting holes.
 */
object BooleanPointInPolygon {
    fun contains(point: GeoPosition, polygon: GeoPolygon): Boolean {
        val outer = polygon.outerRing.dropClosingDuplicate()
        if (outer.size < 3 || !isInsideRingRayCast(point, outer)) return false
        for (hole in polygon.holes) {
            val h = hole.dropClosingDuplicate()
            if (h.size >= 3 && isInsideRingRayCast(point, h)) return false
        }
        return true
    }

    private fun isInsideRingRayCast(point: GeoPosition, ring: List<GeoPosition>): Boolean {
        val x = point.longitude
        val y = point.latitude
        var inside = false
        var j = ring.size - 1
        for (i in ring.indices) {
            val xi = ring[i].longitude
            val yi = ring[i].latitude
            val xj = ring[j].longitude
            val yj = ring[j].latitude
            val crossesLat = (yi > y) != (yj > y)
            if (crossesLat) {
                val dy = yj - yi
                if (abs(dy) > 1e-15) {
                    val xIntersect = xi + (y - yi) * (xj - xi) / dy
                    if (x < xIntersect) inside = !inside
                }
            }
            j = i
        }
        return inside
    }
}
