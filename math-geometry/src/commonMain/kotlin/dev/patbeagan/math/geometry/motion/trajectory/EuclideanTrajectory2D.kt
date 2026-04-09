package dev.patbeagan.math.geometry.motion.trajectory

import com.measures.distance.Meter
import dev.patbeagan.math.geometry.geospatial.measure.Euclidean2D
import dev.patbeagan.math.geometry.geospatial.model.LineString2D
import dev.patbeagan.math.geometry.geospatial.model.MultiLineString2D
import dev.patbeagan.math.geometry.geospatial.model.Point2D
import kotlin.math.hypot

/**
 * Polyline traversal and constant-speed interpolation on segments in the Euclidean plane (meters).
 */
object EuclideanTrajectory2D {
    fun positionOnSegment(start: Point2D, end: Point2D, t: Double): Point2D {
        val u = t.coerceIn(0.0, 1.0)
        return Point2D(
            start.x + u * (end.x - start.x),
            start.y + u * (end.y - start.y),
        )
    }

    fun unitTangentOnSegment(start: Point2D, end: Point2D): Point2D {
        val dx = end.x - start.x
        val dy = end.y - start.y
        val len = hypot(dx, dy)
        if (len < 1e-15) return Point2D(1.0, 0.0)
        return Point2D(dx / len, dy / len)
    }

    /**
     * Point at [distance] meters along [lineString] from the first vertex (Turf `@turf/along`).
     */
    fun along(lineString: LineString2D, distance: Meter): Point2D {
        val coords = lineString.coordinates
        require(coords.isNotEmpty()) { "LineString must have at least one coordinate" }
        if (coords.size < 2) return coords.first()
        var remaining = distance.value
        if (remaining <= 0.0) return coords.first()
        for (i in 0 until coords.size - 1) {
            val p = coords[i]
            val q = coords[i + 1]
            val segLen = Euclidean2D.distance(p, q).value
            if (segLen < 1e-9) continue
            if (remaining <= segLen) {
                val t = remaining / segLen
                return positionOnSegment(p, q, t)
            }
            remaining -= segLen
        }
        return coords.last()
    }

    /**
     * Point at [distance] meters along the concatenated [multiLineString] parts (each part is traversed in order).
     */
    fun along(multiLineString: MultiLineString2D, distance: Meter): Point2D {
        val parts = multiLineString.lineStrings
        require(parts.isNotEmpty()) { "MultiLineString must have at least one LineString" }
        var remaining = distance.value
        if (remaining <= 0.0) {
            return parts.first().coordinates.first()
        }
        for (ls in parts) {
            val len = Euclidean2D.length(ls).value
            if (remaining <= len) {
                return along(ls, Meter(remaining))
            }
            remaining -= len
        }
        return parts.last().coordinates.last()
    }
}
