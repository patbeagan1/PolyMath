package com.measures.potential

import com.measures.BaseUnit
import com.measures.current.UnitCurrent
import com.measures.distance.UnitDistance
import com.measures.time.UnitTime
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

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