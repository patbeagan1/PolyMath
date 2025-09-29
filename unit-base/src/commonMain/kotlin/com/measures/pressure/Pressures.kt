package com.measures.pressure

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.weight.UnitWeight
import com.measures.distance.UnitDistance
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

typealias UnitPressure<T> = UnitPressureType<T>

interface UnitPressureType<T : DoubleBase> : UnitType<T, Pascal>

@JvmInline
value class Pascal(override val value: Double) : UnitPressure<Pascal>, BaseUnit {
    override fun asType(d: Double) = Pascal(d)
    override fun asBaseUnit() = this
    
    companion object {
        fun from(mass: UnitWeight<*>, distance: UnitDistance<*>, time: UnitTime<*>): Pascal {
            val massBase = mass.asBaseUnit()
            val distanceBase = distance.asBaseUnit()
            val timeBase = time.asBaseUnit()
            return Pascal(massBase.value / (distanceBase.value * timeBase.value * timeBase.value))
        }
    }
}

@JvmInline
value class Bar(override val value: Double) : UnitPressure<Bar> {
    override fun asType(d: Double) = Bar(d)
    override fun asBaseUnit() = Pascal(this.value * 100000.0)
}

@JvmInline
value class Atmosphere(override val value: Double) : UnitPressure<Atmosphere> {
    override fun asType(d: Double) = Atmosphere(d)
    override fun asBaseUnit() = Pascal(this.value * 101325.0)
}

@JvmInline
value class Torr(override val value: Double) : UnitPressure<Torr> {
    override fun asType(d: Double) = Torr(d)
    override fun asBaseUnit() = Pascal(this.value * 133.322)
}

@JvmInline
value class MillimeterOfMercury(override val value: Double) : UnitPressure<MillimeterOfMercury> {
    override fun asType(d: Double) = MillimeterOfMercury(d)
    override fun asBaseUnit() = Pascal(this.value * 133.322)
}

@JvmInline
value class PoundPerSquareInch(override val value: Double) : UnitPressure<PoundPerSquareInch> {
    override fun asType(d: Double) = PoundPerSquareInch(d)
    override fun asBaseUnit() = Pascal(this.value * 6894.757)
}

@JvmInline
value class Kilopascal(override val value: Double) : UnitPressure<Kilopascal> {
    override fun asType(d: Double) = Kilopascal(d)
    override fun asBaseUnit() = Pascal(this.value * 1000.0)
}

@JvmInline
value class Megapascal(override val value: Double) : UnitPressure<Megapascal> {
    override fun asType(d: Double) = Megapascal(d)
    override fun asBaseUnit() = Pascal(this.value * 1E6)
}

@JvmInline
value class Millibar(override val value: Double) : UnitPressure<Millibar> {
    override fun asType(d: Double) = Millibar(d)
    override fun asBaseUnit() = Pascal(this.value * 100.0)
}

operator fun UnitPressureType<*>.plus(other: UnitPressureType<*>): Pascal =
    Pascal(this.asBaseUnit().value + other.asBaseUnit().value)

operator fun UnitPressureType<*>.minus(other: UnitPressureType<*>): Pascal =
    Pascal(this.asBaseUnit().value - other.asBaseUnit().value)

// Conversion functions using toUnit
fun UnitPressure<*>.toPascal() = this.asBaseUnit()
fun UnitPressure<*>.toBar() = toUnit(Bar(1.0))
fun UnitPressure<*>.toAtmosphere() = toUnit(Atmosphere(1.0))
fun UnitPressure<*>.toTorr() = toUnit(Torr(1.0))
fun UnitPressure<*>.toMillimeterOfMercury() = toUnit(MillimeterOfMercury(1.0))
fun UnitPressure<*>.toPoundPerSquareInch() = toUnit(PoundPerSquareInch(1.0))
fun UnitPressure<*>.toKilopascal() = toUnit(Kilopascal(1.0))
fun UnitPressure<*>.toMegapascal() = toUnit(Megapascal(1.0))
fun UnitPressure<*>.toMillibar() = toUnit(Millibar(1.0))

