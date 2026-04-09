package dev.patbeagan.physics.classical.orbital

import dev.patbeagan.math.geometry.geospatial.model.Point3D
import dev.patbeagan.physics.classical.orbital.OrbitalMechanics3D.Body
import dev.patbeagan.physics.classical.orbital.OrbitalMechanics3D.State
import dev.patbeagan.physics.classical.orbital.OrbitalMechanics3D.Vector3
import dev.patbeagan.physics.classical.orbital.OrbitalMechanics3D.altitudeMeters
import dev.patbeagan.physics.classical.orbital.OrbitalMechanics3D.angleBetweenRad
import dev.patbeagan.physics.classical.orbital.OrbitalMechanics3D.orbitalSpeedForCircularOrbitMps
import dev.patbeagan.physics.classical.orbital.OrbitalMechanics3D.propagateRK4
import dev.patbeagan.physics.classical.orbital.OrbitalMechanics3D.toVector
import kotlin.math.PI
import kotlin.math.abs
import kotlin.test.Test
import kotlin.test.assertTrue

class OrbitalRocketLaunchTest {

    @Test
    fun rocket_launch_then_coast_reaches_low_earth_orbit_like_motion() {
        // Earth (simplified, spherical, two-body).
        val earth = Body(
            mu = 3.986004418e14, // m^3/s^2
            radiusMeters = 6_371_000.0,
        )

        // Launch from equator on +X axis, initially at rest in inertial frame.
        val r0 = earth.radiusMeters
        val initial = State(
            positionMeters = Point3D(r0, 0.0, 0.0),
            velocityMps = Vector3(0.0, 0.0, 0.0),
        )

        // A toy "gravity turn" guidance:
        // - Start thrusting mostly radial-out to gain altitude.
        // - Gradually rotate toward prograde tangential direction.
        val burnDuration = 420.0 // seconds
        val dt = 0.25 // seconds
        val aThrust = 32.0 // m/s^2 (constant magnitude during burn)
        val coastDuration = 2_400.0 // seconds (~40 minutes)

        fun thrustDirection(state: State): Vector3 {
            val r = toVector(state.positionMeters).unitOr(Vector3(1.0, 0.0, 0.0))
            // Choose a consistent tangential direction in the equatorial plane.
            val k = Vector3(0.0, 0.0, 1.0)
            val tHat = k.cross(r).unitOr(Vector3(0.0, 1.0, 0.0))
            // Blend between radial and tangential based on altitude.
            val alt = altitudeMeters(earth, state.positionMeters)
            val u = ((alt - 0.0) / 120_000.0).coerceIn(0.0, 1.0)
            return (r * (1.0 - u) + tHat * u).unitOr(tHat)
        }

        val burn = propagateRK4(
            body = earth,
            initial = initial,
            t0Seconds = 0.0,
            t1Seconds = burnDuration,
            dtSeconds = dt,
            thrustAccelerationMps2 = { _, state -> thrustDirection(state) * aThrust },
        )

        val stateAfterBurn = burn.last().second
        val altAfterBurn = altitudeMeters(earth, stateAfterBurn.positionMeters)
        assertTrue(altAfterBurn > 120_000.0, "Expected to be above 120km after burn, got ${"%.1f".format(altAfterBurn)} m")

        // Coast in two-body gravity only.
        val coast = propagateRK4(
            body = earth,
            initial = stateAfterBurn,
            t0Seconds = burnDuration,
            t1Seconds = burnDuration + coastDuration,
            dtSeconds = dt,
        )

        val final = coast.last().second
        val altFinal = altitudeMeters(earth, final.positionMeters)
        assertTrue(altFinal > 150_000.0, "Expected to still be above 150km during coast, got ${"%.1f".format(altFinal)} m")

        // Check "orbital-like" characteristics:
        // - Near-circular-ish: radius variation in the coast window is not extreme.
        val radii = coast.map { (_, s) -> toVector(s.positionMeters).norm() }
        val rMin = radii.minOrNull() ?: error("no radii")
        val rMax = radii.maxOrNull() ?: error("no radii")
        val variation = (rMax - rMin) / rMin
        assertTrue(variation < 0.20, "Expected <20% radius variation, got ${"%.3f".format(variation)}")

        // - Speed is in the ballpark of a circular orbit at the current radius.
        val rFinal = toVector(final.positionMeters).norm()
        val vCircular = orbitalSpeedForCircularOrbitMps(earth, rFinal)
        val vFinal = final.velocityMps.norm()
        val speedError = abs(vFinal - vCircular) / vCircular
        assertTrue(speedError < 0.25, "Expected speed within 25% of circular; got v=${"%.1f".format(vFinal)} vs vcirc=${"%.1f".format(vCircular)}")

        // - It has moved substantially around Earth (true anomaly change > ~60 degrees).
        val angle = angleBetweenRad(toVector(stateAfterBurn.positionMeters), toVector(final.positionMeters))
        assertTrue(angle > (PI / 3.0), "Expected >60deg around Earth during coast, got ${"%.2f".format(angle * 180.0 / PI)} deg")

        // - Not escaping: specific orbital energy should be negative.
        val mu = earth.mu
        val eps = (vFinal * vFinal) / 2.0 - mu / rFinal
        assertTrue(eps < 0.0, "Expected bound orbit (negative specific energy), got eps=${"%.3e".format(eps)}")

        // Sanity: ensure we're not intersecting the planet.
        assertTrue(rMin > earth.radiusMeters * 1.02, "Perigee too low; min radius ${"%.1f".format(rMin)}")

        // Extra check: angular momentum magnitude should be significant (not radial ballistic).
        val h = toVector(final.positionMeters).cross(final.velocityMps)
        assertTrue(h.norm() > 1e10, "Expected substantial angular momentum, got |h|=${"%.3e".format(h.norm())}")
    }
}

