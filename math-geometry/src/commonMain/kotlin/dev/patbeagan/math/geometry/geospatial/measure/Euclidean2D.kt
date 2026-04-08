package dev.patbeagan.math.geometry.geospatial.measure

import com.measures.distance.Meter
import dev.patbeagan.math.geometry.geospatial.model.LineString2D
import dev.patbeagan.math.geometry.geospatial.model.MultiLineString2D
import dev.patbeagan.math.geometry.geospatial.model.Point2D
import kotlin.math.hypot

/**
 * Turf-style measurements in a Euclidean plane with coordinates in meters.
 */
object Euclidean2D {
    fun distance(a: Point2D, b: Point2D): Meter =
        Meter(hypot(b.x - a.x, b.y - a.y))

    fun length(lineString: LineString2D): Meter =
        lineString.coordinates
            .asSequence()
            .zipWithNext()
            .map { (p, q) -> distance(p, q) }
            .fold(Meter(0.0)) { acc, d -> acc + d }

    fun length(multiLineString: MultiLineString2D): Meter =
        multiLineString.lineStrings
            .asSequence()
            .map { length(it) }
            .fold(Meter(0.0)) { acc, d -> acc + d }
}
