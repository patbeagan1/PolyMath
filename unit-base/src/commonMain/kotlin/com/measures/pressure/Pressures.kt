package com.measures.pressure

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.distance.UnitDistance
import com.measures.weight.UnitMass
import com.measures.time.UnitTime
import com.measures.area.UnitArea
import com.measures.force.Newton
import kotlin.jvm.JvmInline

typealias UnitPressure<T> = UnitPressureType<T>

interface UnitPressureType<T : DoubleBase> : UnitType<T, Pascal>

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

@JvmInline
value class Kilopascal(override val value: Double) : UnitPressure<Kilopascal> {
    override fun asType(d: Double) = Kilopascal(d)
    override fun asBaseUnit() = Pascal(this.value * 1000.0)

    operator fun plus(other: UnitPressure<*>) = (this as UnitPressure<*>).plusUnit(other)
    operator fun minus(other: UnitPressure<*>) = (this as UnitPressure<*>).minusUnit(other)
    operator fun times(other: UnitArea<*>) = (this as UnitPressure<*>).timesUnit(other)
}

@JvmInline
value class Megapascal(override val value: Double) : UnitPressure<Megapascal> {
    override fun asType(d: Double) = Megapascal(d)
    override fun asBaseUnit() = Pascal(this.value * 1E6)

    operator fun plus(other: UnitPressure<*>) = (this as UnitPressure<*>).plusUnit(other)
    operator fun minus(other: UnitPressure<*>) = (this as UnitPressure<*>).minusUnit(other)
    operator fun times(other: UnitArea<*>) = (this as UnitPressure<*>).timesUnit(other)
}


 fun UnitPressureType<*>.plusUnit(other: UnitPressureType<*>): Pascal =
    Pascal(this.asBaseUnit().value + other.asBaseUnit().value)

 fun UnitPressureType<*>.minusUnit(other: UnitPressureType<*>): Pascal =
    Pascal(this.asBaseUnit().value - other.asBaseUnit().value)

// Pressure × Area = Force
 fun UnitPressureType<*>.timesUnit(other: UnitArea<*>): Newton =
    Newton(this.asBaseUnit().value * other.asBaseUnit().value)

// Conversion functions using toUnit
fun UnitPressure<*>.toPascal() = this.asBaseUnit()
fun UnitPressure<*>.toKilopascal() = toUnit(Kilopascal(1.0))
fun UnitPressure<*>.toMegapascal() = toUnit(Megapascal(1.0))

