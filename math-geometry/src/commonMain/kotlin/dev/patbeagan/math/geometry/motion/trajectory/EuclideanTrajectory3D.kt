package dev.patbeagan.math.geometry.motion.trajectory

import com.measures.distance.Meter
import dev.patbeagan.math.geometry.geospatial.measure.Euclidean3D
import dev.patbeagan.math.geometry.geospatial.model.LineString3D
import dev.patbeagan.math.geometry.geospatial.model.MultiLineString3D
import dev.patbeagan.math.geometry.geospatial.model.Point3D
import kotlin.math.sqrt

/**
 * Polyline traversal and constant-speed interpolation on segments in three-dimensional Euclidean space (meters).
 */
object EuclideanTrajectory3D {
    fun positionOnSegment(start: Point3D, end: Point3D, t: Double): Point3D {
        val u = t.coerceIn(0.0, 1.0)
        return Point3D(
            start.x + u * (end.x - start.x),
            start.y + u * (end.y - start.y),
            start.z + u * (end.z - start.z),
        )
    }

    fun unitTangentOnSegment(start: Point3D, end: Point3D): Point3D {
        val dx = end.x - start.x
        val dy = end.y - start.y
        val dz = end.z - start.z
        val len = sqrt(dx * dx + dy * dy + dz * dz)
        if (len < 1e-15) return Point3D(1.0, 0.0, 0.0)
        return Point3D(dx / len, dy / len, dz / len)
    }

    /**
     * Point at [distance] meters along [lineString] from the first vertex (Turf `@turf/along`).
     */
    fun along(lineString: LineString3D, distance: Meter): Point3D {
        val coords = lineString.coordinates
        require(coords.isNotEmpty()) { "LineString must have at least one coordinate" }
        if (coords.size < 2) return coords.first()
        var remaining = distance.value
        if (remaining <= 0.0) return coords.first()
        for (i in 0 until coords.size - 1) {
            val p = coords[i]
            val q = coords[i + 1]
            val segLen = Euclidean3D.distance(p, q).value
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
    fun along(multiLineString: MultiLineString3D, distance: Meter): Point3D {
        val parts = multiLineString.lineStrings
        require(parts.isNotEmpty()) { "MultiLineString must have at least one LineString" }
        var remaining = distance.value
        if (remaining <= 0.0) {
            return parts.first().coordinates.first()
        }
        for (ls in parts) {
            val len = Euclidean3D.length(ls).value
            if (remaining <= len) {
                return along(ls, Meter(remaining))
            }
            remaining -= len
        }
        return parts.last().coordinates.last()
    }
}
