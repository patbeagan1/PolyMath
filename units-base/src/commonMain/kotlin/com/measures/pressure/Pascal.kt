package com.measures.pressure

import com.measures.BaseUnit
import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import com.measures.time.UnitTime
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class Pascal(override val value: Double) : UnitPressure<Pascal>, BaseUnit {
    override fun asType(d: Double) = Pascal(d)
    override fun asBaseUnit() = this

    operator fun plus(other: UnitPressure<*>) = (this as UnitPressure<*>).plusUnit(other)
    operator fun minus(other: UnitPressure<*>) = (this as UnitPressure<*>).minusUnit(other)
    operator fun times(other: UnitArea<*>) = (this as UnitPressure<*>).timesUnit(other)

    companion object {
        fun from(mass: UnitMass<*>, distance: UnitDistance<*>, time: UnitTime<*>): Pascal {
            // Pressure = Force / Area
            // Force = mass × acceleration = mass × distance / time²
            // Area = distance²
            // Pressure = (mass × distance / time²) / distance² = mass / (distance × time²)
            val massValue = mass.asBaseUnit().value
            val distanceValue = distance.asBaseUnit().value
            val timeValue = time.asBaseUnit().value
            return Pascal(massValue / (distanceValue * timeValue * timeValue))
        }
    }
}