package com.measures.potential

import com.measures.BaseUnit
import com.measures.charge.UnitCharge
import com.measures.current.UnitCurrent
import com.measures.distance.UnitDistance
import com.measures.energy.Joule
import com.measures.time.UnitTime
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class Volt(override val value: Double) : UnitPotential<Volt>, BaseUnit {
    override fun asType(d: Double) = Volt(d)
    override fun asBaseUnit() = this

    override operator fun plus(other: UnitPotential<*>) = (this as UnitPotential<*>).plusUnit(other)
    override operator fun minus(other: UnitPotential<*>) = (this as UnitPotential<*>).minusUnit(other)
    override operator  fun times(other: UnitCharge<*>): Joule = (this as UnitPotential<*>).timesUnit(other)

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

fun UnitPotential<*>.toVolt() = this.asBaseUnit()
