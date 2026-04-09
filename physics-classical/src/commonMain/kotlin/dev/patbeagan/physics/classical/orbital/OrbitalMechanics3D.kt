package dev.patbeagan.physics.classical.orbital

import dev.patbeagan.math.geometry.geospatial.model.Point3D
import kotlin.math.acos
import kotlin.math.sqrt

/**
 * Minimal 3D orbital mechanics primitives (two-body gravity + RK4 integration).
 *
 * This intentionally keeps "geometry" as the source of positions ([Point3D]) and provides a small
 * vector type for velocities/accelerations.
 */
object OrbitalMechanics3D {

    data class Vector3(
        val x: Double,
        val y: Double,
        val z: Double,
    ) {
        operator fun plus(o: Vector3) = Vector3(x + o.x, y + o.y, z + o.z)
        operator fun minus(o: Vector3) = Vector3(x - o.x, y - o.y, z - o.z)
        operator fun times(k: Double) = Vector3(x * k, y * k, z * k)
        operator fun div(k: Double) = Vector3(x / k, y / k, z / k)

        fun dot(o: Vector3): Double = x * o.x + y * o.y + z * o.z
        fun cross(o: Vector3): Vector3 =
            Vector3(
                y * o.z - z * o.y,
                z * o.x - x * o.z,
                x * o.y - y * o.x,
            )

        fun norm2(): Double = dot(this)
        fun norm(): Double = sqrt(norm2())
        fun unitOr(fallback: Vector3 = Vector3(1.0, 0.0, 0.0)): Vector3 {
            val n = norm()
            return if (n < 1e-15) fallback else this / n
        }
    }

    data class State(
        val positionMeters: Point3D,
        val velocityMps: Vector3,
    )

    data class Body(
        /** Standard gravitational parameter \( \mu = GM \) in m^3/s^2. */
        val mu: Double,
        /** Reference radius in meters (used for altitude computations). */
        val radiusMeters: Double,
    )

    fun toVector(p: Point3D): Vector3 = Vector3(p.x, p.y, p.z)
    fun toPoint(v: Vector3): Point3D = Point3D(v.x, v.y, v.z)

    fun add(p: Point3D, v: Vector3): Point3D = Point3D(p.x + v.x, p.y + v.y, p.z + v.z)
    fun subtract(a: Point3D, b: Point3D): Vector3 = Vector3(a.x - b.x, a.y - b.y, a.z - b.z)

    /** Two-body gravitational acceleration in inertial frame: \( a = -\mu r / |r|^3 \). */
    fun gravityAcceleration(body: Body, positionMeters: Point3D): Vector3 {
        val r = toVector(positionMeters)
        val r2 = r.norm2()
        val r1 = sqrt(r2)
        val denom = r2 * r1
        if (denom < 1e-9) return Vector3(0.0, 0.0, 0.0)
        return r * (-body.mu / denom)
    }

    fun altitudeMeters(body: Body, positionMeters: Point3D): Double =
        toVector(positionMeters).norm() - body.radiusMeters

    fun orbitalSpeedForCircularOrbitMps(body: Body, radiusMeters: Double): Double =
        sqrt(body.mu / radiusMeters)

    /**
     * Propagate state forward using fixed-step RK4.
     *
     * @param thrustAccelerationMps2 thrust acceleration in m/s^2, in inertial frame.
     */
    fun propagateRK4(
        body: Body,
        initial: State,
        t0Seconds: Double,
        t1Seconds: Double,
        dtSeconds: Double,
        thrustAccelerationMps2: (tSeconds: Double, state: State) -> Vector3 = { _, _ -> Vector3(0.0, 0.0, 0.0) },
    ): List<Pair<Double, State>> {
        require(dtSeconds > 0.0) { "dtSeconds must be > 0" }
        val out = ArrayList<Pair<Double, State>>()
        var t = t0Seconds
        var s = initial
        out.add(t to s)

        fun accel(ti: Double, si: State): Vector3 =
            gravityAcceleration(body, si.positionMeters) + thrustAccelerationMps2(ti, si)

        while (t < t1Seconds - 1e-12) {
            val h = minOf(dtSeconds, t1Seconds - t)

            val k1_r = s.velocityMps
            val k1_v = accel(t, s)

            val s2 = State(
                positionMeters = add(s.positionMeters, k1_r * (h * 0.5)),
                velocityMps = s.velocityMps + (k1_v * (h * 0.5)),
            )
            val k2_r = s2.velocityMps
            val k2_v = accel(t + h * 0.5, s2)

            val s3 = State(
                positionMeters = add(s.positionMeters, k2_r * (h * 0.5)),
                velocityMps = s.velocityMps + (k2_v * (h * 0.5)),
            )
            val k3_r = s3.velocityMps
            val k3_v = accel(t + h * 0.5, s3)

            val s4 = State(
                positionMeters = add(s.positionMeters, k3_r * h),
                velocityMps = s.velocityMps + (k3_v * h),
            )
            val k4_r = s4.velocityMps
            val k4_v = accel(t + h, s4)

            val dr = (k1_r + (k2_r * 2.0) + (k3_r * 2.0) + k4_r) * (h / 6.0)
            val dv = (k1_v + (k2_v * 2.0) + (k3_v * 2.0) + k4_v) * (h / 6.0)

            s = State(
                positionMeters = add(s.positionMeters, dr),
                velocityMps = s.velocityMps + dv,
            )
            t += h
            out.add(t to s)
        }

        return out
    }

    /**
     * Angle between two vectors in radians, in \([0,\pi]\).
     */
    fun angleBetweenRad(a: Vector3, b: Vector3): Double {
        val na = a.norm()
        val nb = b.norm()
        if (na < 1e-15 || nb < 1e-15) return 0.0
        val c = (a.dot(b) / (na * nb)).coerceIn(-1.0, 1.0)
        return acos(c)
    }
}

