package com.measures.energy

import com.measures.BaseUnit
import com.measures.charge.UnitCharge
import com.measures.distance.UnitDistance
import com.measures.potential.Volt
import com.measures.time.UnitTime
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class Joule(override val value: Double) : UnitEnergy<Joule>, BaseUnit {
    override fun asType(d: Double) = Joule(d)
    override fun asBaseUnit() = this

    override operator fun plus(other: UnitEnergy<*>) = UnitEnergy.plusUnit(this, other)
    override operator fun minus(other: UnitEnergy<*>) = UnitEnergy.minusUnit(this, other)
    override operator fun div(other: UnitTime<*>) = UnitEnergy.divUnit(this, other)
    override operator fun div(other: UnitCharge<*>): Volt = UnitEnergy.divUnit(this, other)

    companion object {
        fun from(mass: UnitMass<*>, distance: UnitDistance<*>, time: UnitTime<*>): Joule {
            // Energy = mass × distance² / time²
            // Using the operations available: we can multiply by Double values
            val distanceSquared = distance.times(distance).asBaseUnit().value
            val timeSquared = time.asBaseUnit().value * time.asBaseUnit().value

            val ratio = distanceSquared / timeSquared
            return Joule(mass.asBaseUnit().value * ratio)
        }
    }
}

fun UnitEnergy<*>.toJoule() = this.asBaseUnit()
