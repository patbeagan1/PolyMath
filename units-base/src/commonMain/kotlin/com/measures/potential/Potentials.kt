package com.measures.potential

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.weight.UnitMass
import com.measures.current.UnitCurrent
import com.measures.distance.UnitDistance
import com.measures.time.UnitTime
import com.measures.charge.UnitCharge
import com.measures.energy.Joule
import kotlin.jvm.JvmInline

typealias UnitPotential<T> = UnitPotentialType<T>

interface UnitPotentialType<T : DoubleBase> : UnitType<T, Volt>

@JvmInline
value class Volt(override val value: Double) : UnitPotential<Volt>, BaseUnit {
    override fun asType(d: Double) = Volt(d)
    override fun asBaseUnit() = this

    operator fun plus(other: UnitPotential<*>) = (this as UnitPotential<*>).plusUnit(other)
    operator fun minus(other: UnitPotential<*>) = (this as UnitPotential<*>).minusUnit(other)
    
    companion object {
        fun from(mass: UnitMass<*>, distance: UnitDistance<*>, current: UnitCurrent<*>, time: UnitTime<*>): Volt {
            // Potential = Energy / Charge
            // Energy = mass × distance² / time²
            // Charge = current × time
            // Potential = (mass × distance² / time²) / (current × time) = mass × distance² / (current × time³)
            val massValue = mass.asBaseUnit().value
            val distanceValue = distance.asBaseUnit().value
            val currentValue = current.asBaseUnit().value
            val timeValue = time.asBaseUnit().value
            return Volt(massValue * distanceValue * distanceValue / (currentValue * timeValue * timeValue * timeValue))
        }
    }
}

fun UnitPotentialType<*>.plusUnit(other: UnitPotentialType<*>): Volt =
    Volt(this.asBaseUnit().value + other.asBaseUnit().value)

fun UnitPotentialType<*>.minusUnit(other: UnitPotentialType<*>): Volt =
    Volt(this.asBaseUnit().value - other.asBaseUnit().value)

// Potential × Charge = Energy
fun UnitPotentialType<*>.timesUnit(other: UnitCharge<*>): Joule =
    Joule(this.asBaseUnit().value * other.asBaseUnit().value)

// Non-SI potential units have been moved to unit-common module

// Conversion functions using toUnit
fun UnitPotential<*>.toVolt() = this.asBaseUnit()

