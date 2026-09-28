package dev.patbeagan.math.geometry.motion.rotation

import com.measures.angle.Radian
import dev.patbeagan.math.geometry.geospatial.model.Point3D
import kotlin.math.cos
import kotlin.math.hypot
import kotlin.math.sin

/**
 * Rotations in three-dimensional Euclidean space using Rodrigues' formula (axis through origin).
 */
object EuclideanRotation3D {
    fun rotate(point: Point3D, axis: Point3D, angle: Radian): Point3D {
        val (kx, ky, kz) = normalize(axis)
        val v = point
        val θ = angle.value
        val cosθ = cos(θ)
        val sinθ = sin(θ)
        val dot = kx * v.x + ky * v.y + kz * v.z
        val cx = ky * v.z - kz * v.y
        val cy = kz * v.x - kx * v.z
        val cz = kx * v.y - ky * v.x
        return Point3D(
            v.x * cosθ + cx * sinθ + kx * dot * (1.0 - cosθ),
            v.y * cosθ + cy * sinθ + ky * dot * (1.0 - cosθ),
            v.z * cosθ + cz * sinθ + kz * dot * (1.0 - cosθ),
        )
    }

    private fun normalize(v: Point3D): Triple<Double, Double, Double> {
        val n = hypot(hypot(v.x, v.y), v.z)
        if (n < 1e-15) return Triple(1.0, 0.0, 0.0)
        return Triple(v.x / n, v.y / n, v.z / n)
    }
}
