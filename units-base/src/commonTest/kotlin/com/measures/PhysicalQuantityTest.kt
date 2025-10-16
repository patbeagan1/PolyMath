package com.measures

import com.measures.PhysicalQuantity.Companion.distance
import com.measures.PhysicalQuantity.Companion.force
import com.measures.PhysicalQuantity.Companion.length
import com.measures.PhysicalQuantity.Companion.mass
import com.measures.PhysicalQuantity.Companion.time
import com.measures.distance.metric.Kilometer
import com.measures.solidangle.Steradian
import com.measures.time.Second
import com.measures.volume.Liters
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

        val message = (velocity.asVelocity / Second(10.0))
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

    @Test
    fun `liters to physical quantity`() {
        val a = PhysicalQuantity.from(Liters(3.0)) / distance(1.0)
        println(a.asArea)
    }

    @Test
    fun `radians work`() {
        val a = PhysicalQuantity.from(Steradian(1.0))
        println(a)
    }

    @Test
    fun `angular velocity works`() {
        val a = PhysicalQuantity.angularVelocity(2.0)
        println(a)
    }

    @Test
    fun `angular acceleration works`() {
        val a = PhysicalQuantity.angularAcceleration(2.0)
        println(a)
        val hertz = a / PhysicalQuantity.angularVelocity(1.0)
        println(hertz)
    }

    @Test
    fun `people per square kilometer`() {
        val person = PhysicalQuantity.fromEntity("People")
        val squareKilometer = (Kilometer(1.0) * Kilometer(1.0)).let { PhysicalQuantity.from(it) }

        val peoplePerSquareKilometer = person / squareKilometer

        println(peoplePerSquareKilometer * 5)
    }

    @Test
    fun `parts per million`() {
        val parts = PhysicalQuantity.fromEntity("Parts")
        val ppm = parts / PhysicalQuantity.Million

        println(ppm * 1000)
    }
}