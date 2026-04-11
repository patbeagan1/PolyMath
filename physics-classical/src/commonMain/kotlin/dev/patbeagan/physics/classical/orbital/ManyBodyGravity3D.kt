package dev.patbeagan.physics.classical.orbital

import dev.patbeagan.math.geometry.geospatial.model.Point3D
import kotlin.math.sqrt

/**
 * Newtonian N-body gravitation in 3D: each body has a standard gravitational parameter
 * \( \mu = G M \) (m³/s²). Pairwise acceleration on body *i* from body *j* is
 *
 * \( \mathbf{a}_{ij} = \mu_j \frac{\mathbf{r}_j - \mathbf{r}_i}{|\mathbf{r}_j - \mathbf{r}_i|^3} \).
 *
 * Optional [softeningMeters] adds Plummer-style softening \( (r^2 + \varepsilon^2)^{3/2} \) in the
 * denominator to limit force spikes when distances approach zero.
 *
 * Integration uses the same fixed-step RK4 pattern as [OrbitalMechanics3D.propagateRK4].
 */
object ManyBodyGravity3D {

    data class Body(
        /** Standard gravitational parameter \( \mu = G M \) in m³/s². */
        val mu: Double,
        val positionMeters: Point3D,
        val velocityMps: OrbitalMechanics3D.Vector3,
    )

    data class State(
        val bodies: List<Body>,
    ) {
        init {
            require(bodies.isNotEmpty()) { "State must contain at least one body" }
        }
    }

    /**
     * Gravitational acceleration (m/s²) on each body in the same order as [state].bodies.
     */
    fun gravityAccelerations(
        state: State,
        softeningMeters: Double = 0.0,
    ): List<OrbitalMechanics3D.Vector3> {
        require(softeningMeters >= 0.0) { "softeningMeters must be >= 0" }
        val n = state.bodies.size
        val positions = state.bodies.map { OrbitalMechanics3D.toVector(it.positionMeters) }
        val mus = state.bodies.map { it.mu }
        val acc = List(n) { OrbitalMechanics3D.Vector3(0.0, 0.0, 0.0) }.toMutableList()
        val eps2 = softeningMeters * softeningMeters

        for (i in 0 until n) {
            for (j in 0 until n) {
                if (i == j) continue
                val delta = positions[j] - positions[i]
                val r2 = delta.norm2() + eps2
                val r1 = sqrt(r2)
                val denom = r2 * r1
                if (denom < 1e-18) continue
                acc[i] = acc[i] + delta * (mus[j] / denom)
            }
        }
        return acc
    }

    private fun applyDerivativeKick(
        state: State,
        velocityRates: List<OrbitalMechanics3D.Vector3>,
        acceleration: List<OrbitalMechanics3D.Vector3>,
        scale: Double,
    ): State =
        State(
            state.bodies.mapIndexed { idx, b ->
                b.copy(
                    positionMeters = OrbitalMechanics3D.add(
                        b.positionMeters,
                        velocityRates[idx] * scale,
                    ),
                    velocityMps = b.velocityMps + acceleration[idx] * scale,
                )
            },
        )

    private fun sumVec(
        a: List<OrbitalMechanics3D.Vector3>,
        b: List<OrbitalMechanics3D.Vector3>,
        bScale: Double,
    ): List<OrbitalMechanics3D.Vector3> =
        a.mapIndexed { i, v -> v + b[i] * bScale }

    /**
     * Propagate an N-body state with fixed-step RK4.
     *
     * @param softeningMeters optional softening length (m); 0 disables softening.
     */
    fun propagateRK4(
        initial: State,
        t0Seconds: Double,
        t1Seconds: Double,
        dtSeconds: Double,
        softeningMeters: Double = 0.0,
    ): List<Pair<Double, State>> {
        require(dtSeconds > 0.0) { "dtSeconds must be > 0" }
        require(softeningMeters >= 0.0) { "softeningMeters must be >= 0" }

        val out = ArrayList<Pair<Double, State>>()
        var t = t0Seconds
        var s = initial
        out.add(t to s)

        fun accel(si: State): List<OrbitalMechanics3D.Vector3> =
            gravityAccelerations(si, softeningMeters)

        while (t < t1Seconds - 1e-12) {
            val h = minOf(dtSeconds, t1Seconds - t)

            val k1r = s.bodies.map { it.velocityMps }
            val k1v = accel(s)

            val s2 = applyDerivativeKick(s, k1r, k1v, h * 0.5)
            val k2r = s2.bodies.map { it.velocityMps }
            val k2v = accel(s2)

            val s3 = applyDerivativeKick(s, k2r, k2v, h * 0.5)
            val k3r = s3.bodies.map { it.velocityMps }
            val k3v = accel(s3)

            val s4 = applyDerivativeKick(s, k3r, k3v, h)
            val k4r = s4.bodies.map { it.velocityMps }
            val k4v = accel(s4)

            val dr = sumVec(
                sumVec(
                    sumVec(k1r, k2r, 2.0),
                    k3r,
                    2.0,
                ),
                k4r,
                1.0,
            ).map { it * (h / 6.0) }

            val dv = sumVec(
                sumVec(
                    sumVec(k1v, k2v, 2.0),
                    k3v,
                    2.0,
                ),
                k4v,
                1.0,
            ).map { it * (h / 6.0) }

            s = State(
                s.bodies.mapIndexed { i, b ->
                    b.copy(
                        positionMeters = OrbitalMechanics3D.add(b.positionMeters, dr[i]),
                        velocityMps = b.velocityMps + dv[i],
                    )
                },
            )
            t += h
            out.add(t to s)
        }

        return out
    }

    /**
     * Kinetic energy \( \sum_i \frac{1}{2} m_i v_i^2 = \sum_i \frac{\mu_i}{2G} v_i^2 \).
     * [gravityConstant] is \( G \) in SI (m³ kg⁻¹ s⁻²); kinetic energy is returned in joules when
     * \( \mu \) and masses are consistent.
     */
    fun kineticEnergyJoules(state: State, gravityConstant: Double): Double {
        require(gravityConstant > 0.0) { "gravityConstant must be > 0" }
        return state.bodies.sumOf { body ->
            val m = body.mu / gravityConstant
            0.5 * m * body.velocityMps.norm2()
        }
    }

    /**
     * Total mechanical energy in joules when [gravityConstant] is \( G \) and each [Body.mu] is
     * \( G M_i \). Uses softened distances for potential if [softeningMeters] > 0.
     */
    fun totalMechanicalEnergyJoules(
        state: State,
        gravityConstant: Double,
        softeningMeters: Double = 0.0,
    ): Double {
        require(gravityConstant > 0.0) { "gravityConstant must be > 0" }
        val positions = state.bodies.map { OrbitalMechanics3D.toVector(it.positionMeters) }
        val mus = state.bodies.map { it.mu }
        val n = positions.size
        val eps2 = softeningMeters * softeningMeters
        var potential = 0.0
        for (i in 0 until n) {
            for (j in i + 1 until n) {
                val delta = positions[j] - positions[i]
                val r = sqrt(delta.norm2() + eps2)
                if (r < 1e-18) continue
                val mi = mus[i] / gravityConstant
                val mj = mus[j] / gravityConstant
                potential -= gravityConstant * mi * mj / r
            }
        }
        return kineticEnergyJoules(state, gravityConstant) + potential
    }
}
