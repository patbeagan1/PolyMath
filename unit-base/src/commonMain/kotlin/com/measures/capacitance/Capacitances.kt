package com.measures.capacitance

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.current.UnitCurrent
import com.measures.distance.UnitDistance
import com.measures.time.UnitTime
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

typealias UnitCapacitance<T> = UnitCapacitanceType<T>

interface UnitCapacitanceType<T : DoubleBase> : UnitType<T, Farad>

@JvmInline
value class Farad(override val value: Double) : UnitCapacitance<Farad>, BaseUnit {
    override fun asType(d: Double) = Farad(d)
    override fun asBaseUnit() = this

    operator fun plus(other: UnitCapacitance<*>) = (this as UnitCapacitance<*>).plus(other)
    operator fun minus(other: UnitCapacitance<*>) = (this as UnitCapacitance<*>).minus(other)
    
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

operator fun UnitCapacitanceType<*>.plus(other: UnitCapacitanceType<*>): Farad =
    Farad(this.asBaseUnit().value + other.asBaseUnit().value)

operator fun UnitCapacitanceType<*>.minus(other: UnitCapacitanceType<*>): Farad =
    Farad(this.asBaseUnit().value - other.asBaseUnit().value)

fun UnitCapacitance<*>.toFarad() = this.asBaseUnit()
