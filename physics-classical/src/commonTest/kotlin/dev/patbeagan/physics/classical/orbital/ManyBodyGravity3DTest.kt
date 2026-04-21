package dev.patbeagan.physics.classical.orbital

import dev.patbeagan.math.geometry.geospatial.model.Point3D
import dev.patbeagan.physics.classical.orbital.ManyBodyGravity3D.Body
import dev.patbeagan.physics.classical.orbital.ManyBodyGravity3D.State
import dev.patbeagan.physics.classical.orbital.ManyBodyGravity3D.gravityAccelerations
import dev.patbeagan.physics.classical.orbital.ManyBodyGravity3D.propagateRK4
import dev.patbeagan.physics.classical.orbital.ManyBodyGravity3D.totalMechanicalEnergyJoules
import dev.patbeagan.physics.classical.orbital.OrbitalMechanics3D.Vector3
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.sqrt
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ManyBodyGravity3DTest {

    private val gSi = 6.67430e-11

    @Test
    fun single_body_has_zero_acceleration() {
        val s = State(
            listOf(
                Body(
                    mu = 1e12,
                    positionMeters = Point3D(1.0, 2.0, 3.0),
                    velocityMps = Vector3(0.1, -0.2, 0.3),
                ),
            ),
        )
        val a = gravityAccelerations(s)
        assertEquals(1, a.size)
        assertTrue(a[0].norm() < 1e-20)
    }

    @Test
    fun equal_mass_binary_maintains_separation_over_two_orbits() {
        val mu = 5.0e15
        val r = 5.0e8
        val omega = sqrt(mu / (4.0 * r * r * r))
        val v = omega * r

        val initial = State(
            listOf(
                Body(
                    mu = mu,
                    positionMeters = Point3D(-r, 0.0, 0.0),
                    velocityMps = Vector3(0.0, -v, 0.0),
                ),
                Body(
                    mu = mu,
                    positionMeters = Point3D(r, 0.0, 0.0),
                    velocityMps = Vector3(0.0, v, 0.0),
                ),
            ),
        )

        val separation0 = 2.0 * r
        val period = 2.0 * PI / omega
        val dt = 60.0
        val history = propagateRK4(
            initial = initial,
            t0Seconds = 0.0,
            t1Seconds = 2.2 * period,
            dtSeconds = dt,
        )

        val separations = history.map { (_, st) ->
            val p0 = st.bodies[0].positionMeters
            val p1 = st.bodies[1].positionMeters
            val dx = p1.x - p0.x
            val dy = p1.y - p0.y
            val dz = p1.z - p0.z
            sqrt(dx * dx + dy * dy + dz * dz)
        }
        val relErr = separations.maxOf { abs(it - separation0) / separation0 }
        assertTrue(relErr < 0.02, "Expected separation within 2% of initial; max rel err=$relErr")
    }

    @Test
    fun total_energy_nearly_conserved_for_equal_binary() {
        val mu = 5.0e15
        val r = 5.0e8
        val omega = sqrt(mu / (4.0 * r * r * r))
        val v = omega * r

        val initial = State(
            listOf(
                Body(mu, Point3D(-r, 0.0, 0.0), Vector3(0.0, -v, 0.0)),
                Body(mu, Point3D(r, 0.0, 0.0), Vector3(0.0, v, 0.0)),
            ),
        )

        val e0 = totalMechanicalEnergyJoules(initial, gSi, softeningMeters = 0.0)
        val period = 2.0 * PI / omega
        val end = propagateRK4(
            initial = initial,
            t0Seconds = 0.0,
            t1Seconds = 1.5 * period,
            dtSeconds = 20.0,
            softeningMeters = 0.0,
        ).last().second
        val e1 = totalMechanicalEnergyJoules(end, gSi, softeningMeters = 0.0)
        val rel = abs(e1 - e0) / maxOf(abs(e0), 1.0)
        assertTrue(rel < 0.02, "Expected small fractional energy drift for RK4; rel=$rel")
    }

    @Test
    fun tiny_satellite_tracks_two_body_reference_over_one_orbit() {
        val muEarth = 3.986004418e14
        val r0 = 7.0e6
        val vCirc = sqrt(muEarth / r0)
        val orbitPeriod = 2.0 * PI * r0 / vCirc

        val twoBody = OrbitalMechanics3D.Body(mu = muEarth, radiusMeters = 6_371_000.0)
        val ref = OrbitalMechanics3D.propagateRK4(
            body = twoBody,
            initial = OrbitalMechanics3D.State(
                positionMeters = Point3D(r0, 0.0, 0.0),
                velocityMps = Vector3(0.0, vCirc, 0.0),
            ),
            t0Seconds = 0.0,
            t1Seconds = orbitPeriod,
            dtSeconds = 5.0,
        ).last().second

        // Massless test particle: no pull on Earth, same dynamics as fixed two-body.
        val many = propagateRK4(
            initial = State(
                listOf(
                    Body(
                        mu = muEarth,
                        positionMeters = Point3D(0.0, 0.0, 0.0),
                        velocityMps = Vector3(0.0, 0.0, 0.0),
                    ),
                    Body(
                        mu = 0.0,
                        positionMeters = Point3D(r0, 0.0, 0.0),
                        velocityMps = Vector3(0.0, vCirc, 0.0),
                    ),
                ),
            ),
            t0Seconds = 0.0,
            t1Seconds = orbitPeriod,
            dtSeconds = 5.0,
        ).last().second

        val sat = many.bodies[1]
        val dx = sat.positionMeters.x - ref.positionMeters.x
        val dy = sat.positionMeters.y - ref.positionMeters.y
        val dz = sat.positionMeters.z - ref.positionMeters.z
        val posErr = sqrt(dx * dx + dy * dy + dz * dz)

        val dvx = sat.velocityMps.x - ref.velocityMps.x
        val dvy = sat.velocityMps.y - ref.velocityMps.y
        val dvz = sat.velocityMps.z - ref.velocityMps.z
        val velErr = sqrt(dvx * dvx + dvy * dvy + dvz * dvz)

        assertTrue(posErr < 1_000.0, "Position vs two-body ref should match RK4; err=$posErr m")
        assertTrue(velErr < 1.0, "Velocity vs two-body ref should match RK4; err=$velErr m/s")
    }
}
