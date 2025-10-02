package com.measures.capacitance

import com.measures.BaseUnit
import com.measures.current.UnitCurrent
import com.measures.distance.UnitDistance
import com.measures.time.UnitTime
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class Farad(override val value: Double) : UnitCapacitance<Farad>, BaseUnit {
    override fun asType(d: Double) = Farad(d)
    override fun asBaseUnit() = this

    override operator fun plus(other: UnitCapacitance<*>) = UnitCapacitance.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitCapacitance<*>) = UnitCapacitance.Companion.minusUnit(this, other)

    companion object {
        fun from(current: UnitCurrent<*>, time: UnitTime<*>, mass: UnitMass<*>, distance: UnitDistance<*>): Farad {
            // Capacitance = Charge / Potential
            // Charge = current × time
            // Potential = Energy / Charge = (mass × distance² / time²) / (current × time) = mass × distance² / (current × time³)
            // Capacitance = (current × time) / (mass × distance² / (current × time³)) = current² × time⁴ / (mass × distance²)
            val currentValue = current.asBaseUnit().value
            val timeValue = time.asBaseUnit().value
            val massValue = mass.asBaseUnit().value
            val distanceValue = distance.asBaseUnit().value
            return Farad(currentValue * currentValue * timeValue * timeValue * timeValue * timeValue / (massValue * distanceValue * distanceValue))
        }
    }
}

fun UnitCapacitance<*>.toFarad() = this.asBaseUnit()
