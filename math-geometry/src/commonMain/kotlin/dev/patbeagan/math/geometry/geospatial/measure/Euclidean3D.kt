package dev.patbeagan.math.geometry.geospatial.measure

import com.measures.distance.Meter
import dev.patbeagan.math.geometry.geospatial.model.LineString3D
import dev.patbeagan.math.geometry.geospatial.model.MultiLineString3D
import dev.patbeagan.math.geometry.geospatial.model.Point3D
import kotlin.math.sqrt

/**
 * Turf-style measurements in three-dimensional Euclidean space with coordinates in meters.
 */
object Euclidean3D {
    fun distance(a: Point3D, b: Point3D): Meter {
        val dx = b.x - a.x
        val dy = b.y - a.y
        val dz = b.z - a.z
        return Meter(sqrt(dx * dx + dy * dy + dz * dz))
    }

    fun length(lineString: LineString3D): Meter =
        lineString.coordinates
            .asSequence()
            .zipWithNext()
            .map { (p, q) -> distance(p, q) }
            .fold(Meter(0.0)) { acc, d -> acc + d }

    fun length(multiLineString: MultiLineString3D): Meter =
        multiLineString.lineStrings
            .asSequence()
            .map { length(it) }
            .fold(Meter(0.0)) { acc, d -> acc + d }
}
