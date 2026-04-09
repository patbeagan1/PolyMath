package dev.patbeagan.math.geometry.motion.rotation

import com.measures.angle.Radian
import dev.patbeagan.math.geometry.geospatial.model.Point2D
import kotlin.math.cos
import kotlin.math.sin

/**
 * Rotations in the Euclidean plane (meters), positive angle is counter-clockwise.
 */
object EuclideanRotation2D {
    fun rotate(point: Point2D, angle: Radian, center: Point2D = Point2D(0.0, 0.0)): Point2D {
        val θ = angle.value
        val c = cos(θ)
        val s = sin(θ)
        val x = point.x - center.x
        val y = point.y - center.y
        return Point2D(
            x = center.x + c * x - s * y,
            y = center.y + s * x + c * y,
        )
    }

    fun compose(a: Radian, b: Radian): Radian {
        return Radian(a.value + b.value)
    }
}
