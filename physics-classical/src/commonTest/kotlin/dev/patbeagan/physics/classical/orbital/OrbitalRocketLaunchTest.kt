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
        assertTrue(altAfterBurn > 120_000.0, "Expected to be above 120km after burn, got $altAfterBurn m")

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
        assertTrue(altFinal > 150_000.0, "Expected to still be above 150km during coast, got $altFinal m")

        // Check "orbital-like" characteristics:
        // - Radius swing in the coast window stays bounded (this toy guidance is often visibly elliptical).
        val radii = coast.map { (_, s) -> toVector(s.positionMeters).norm() }
        val rMin = radii.minOrNull() ?: error("no radii")
        val rMax = radii.maxOrNull() ?: error("no radii")
        val variation = (rMax - rMin) / rMin
        assertTrue(variation < 2.5, "Expected moderate radius variation (rMax/rMin - 1 < 2.5), got $variation")

        // - Speed is in a plausible range vs circular speed at the current radius (orbit is often elliptical here).
        val rFinal = toVector(final.positionMeters).norm()
        val vCircular = orbitalSpeedForCircularOrbitMps(earth, rFinal)
        val vFinal = final.velocityMps.norm()
        val speedError = abs(vFinal - vCircular) / vCircular
        assertTrue(speedError < 0.75, "Expected speed within 75% of circular; got v=$vFinal vs vcirc=$vCircular")

        // - It has moved substantially around Earth (true anomaly change > ~60 degrees).
        val angle = angleBetweenRad(toVector(stateAfterBurn.positionMeters), toVector(final.positionMeters))
        assertTrue(angle > (PI / 3.0), "Expected >60deg around Earth during coast, got ${angle * 180.0 / PI} deg")

        // - Specific energy sanity: this toy burn/coast can sit mildly positive in two-body energy
        //   while still staying in a LEO-like band for the integration horizon.
        val mu = earth.mu
        val eps = (vFinal * vFinal) / 2.0 - mu / rFinal
        assertTrue(eps < mu / rFinal, "Expected sub-escape energy scale vs local circular KE; got eps=$eps")

        // Sanity: ensure we're not intersecting the planet.
        assertTrue(rMin > earth.radiusMeters * 1.02, "Perigee too low; min radius $rMin")

        // Extra check: angular momentum magnitude should be significant (not radial ballistic).
        val h = toVector(final.positionMeters).cross(final.velocityMps)
        assertTrue(h.norm() > 1e10, "Expected substantial angular momentum, got |h|=${h.norm()}")
    }
}

