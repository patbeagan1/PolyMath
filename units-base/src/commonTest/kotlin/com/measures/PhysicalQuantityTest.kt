package com.measures

import com.measures.PhysicalQuantity.Companion.distance
import com.measures.PhysicalQuantity.Companion.force
import com.measures.PhysicalQuantity.Companion.length
import com.measures.PhysicalQuantity.Companion.mass
import com.measures.PhysicalQuantity.Companion.time
import com.measures.time.Second
import kotlin.test.Test

class PhysicalQuantityTest {

    @Test
    fun `basic addition`() {
        val time1 = time(1.0)
        val time2 = time(1.0)

        println(time1 + time2)
    }

    @Test
    fun `basic multiplication`() {
        val distance1 = distance(1.0)
        val distance2 = distance(1.0)

        println("area: " + distance1 * distance2)
    }

    @Test
    fun `unit conversion`() {
        val velocity = distance(1.0) / time(1.0)

        println("velocity: " + velocity)

        println("power:  " + velocity * mass(1.0))
    }

    @Test
    fun `force is mass times acceleration`() {
        val acceleration = distance(1.0) / time(1.0) / time(1.0)
        println("force:  " + mass(1.0) * acceleration)
    }

    @Test
    fun `pressure is force per area`() {
        val acceleration = distance(1.0) / time(1.0) / time(1.0)
        val force = mass(1.0) * acceleration
        println("force:  " + force)
        val area = distance(1.0) * distance(1.0)
        println("pressure:  " + force / area)
    }

    @Test
    fun `toUnit works with velocity`() {
        val velocity = distance(1.0) / time(1.0)

        val message = (velocity.inMetersPerSecond / Second(10.0))
        println(message)
    }

    @Test
    fun `force times distance equals energy`() {
        val energy = force(1.0) * distance(1.0)
        println(energy)
    }

    @Test
    fun `density is mass over volume`() {
        val density = mass(1.0) / length(1.0) / length(1.0) / length(1.0)
        println(density)
    }
}