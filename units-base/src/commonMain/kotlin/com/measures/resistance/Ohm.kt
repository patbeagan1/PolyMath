package com.measures.resistance

import com.measures.BaseUnit
import com.measures.current.UnitCurrent
import com.measures.distance.UnitDistance
import com.measures.time.UnitTime
import com.measures.weight.UnitMass
import kotlin.jvm.JvmInline

@JvmInline
value class Ohm(override val value: Double) : UnitResistance<Ohm>, BaseUnit {
    override fun asType(d: Double) = Ohm(d)
    override fun asBaseUnit() = this

    operator fun plus(other: UnitResistance<*>) = (this as UnitResistance<*>).plusUnit(other)
    operator fun minus(other: UnitResistance<*>) = (this as UnitResistance<*>).minusUnit(other)

    companion object {
        fun from(mass: UnitMass<*>, distance: UnitDistance<*>, current: UnitCurrent<*>, time: UnitTime<*>): Ohm {
            // Resistance = Potential / Current
            // Potential = Energy / Charge = (mass × distance² / time²) / (current × time) = mass × distance² / (current × time³)
            // Resistance = (mass × distance² / (current × time³)) / current = mass × distance² / (current² × time³)
            val massValue = mass.asBaseUnit().value
            val distanceValue = distance.asBaseUnit().value
            val currentValue = current.asBaseUnit().value
            val timeValue = time.asBaseUnit().value
            return Ohm(massValue * distanceValue * distanceValue / (currentValue * currentValue * timeValue * timeValue * timeValue))
        }
    }
}