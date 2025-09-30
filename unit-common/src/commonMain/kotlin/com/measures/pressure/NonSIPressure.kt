package com.measures.pressure

import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.area.UnitArea
import com.measures.force.Newton
import kotlin.jvm.JvmInline

// Non-SI Pressure Units
// These units are not part of the SI system and are moved to units-common

@JvmInline
value class Bar(override val value: Double) : UnitPressure<Bar> {
    override fun asType(d: Double) = Bar(d)
    override fun asBaseUnit() = com.measures.pressure.Pascal(this.value * 100000.0)

    operator fun plus(other: UnitPressure<*>) = (this as UnitPressure<*>).plusUnit(other)
    operator fun minus(other: UnitPressure<*>) = (this as UnitPressure<*>).minusUnit(other)
    operator fun times(other: UnitArea<*>) = (this as UnitPressure<*>).timesUnit(other)
}

@JvmInline
value class Atmosphere(override val value: Double) : UnitPressure<Atmosphere> {
    override fun asType(d: Double) = Atmosphere(d)
    override fun asBaseUnit() = com.measures.pressure.Pascal(this.value * 101325.0)

    operator fun plus(other: UnitPressure<*>) = (this as UnitPressure<*>).plusUnit(other)
    operator fun minus(other: UnitPressure<*>) = (this as UnitPressure<*>).minusUnit(other)
    operator fun times(other: UnitArea<*>) = (this as UnitPressure<*>).timesUnit(other)
}

@JvmInline
value class Torr(override val value: Double) : UnitPressure<Torr> {
    override fun asType(d: Double) = Torr(d)
    override fun asBaseUnit() = com.measures.pressure.Pascal(this.value * 133.322)

    operator fun plus(other: UnitPressure<*>) = (this as UnitPressure<*>).plusUnit(other)
    operator fun minus(other: UnitPressure<*>) = (this as UnitPressure<*>).minusUnit(other)
    operator fun times(other: UnitArea<*>) = (this as UnitPressure<*>).timesUnit(other)
}

@JvmInline
value class MillimeterOfMercury(override val value: Double) : UnitPressure<MillimeterOfMercury> {
    override fun asType(d: Double) = MillimeterOfMercury(d)
    override fun asBaseUnit() = com.measures.pressure.Pascal(this.value * 133.322)

    operator fun plus(other: UnitPressure<*>) = (this as UnitPressure<*>).plusUnit(other)
    operator fun minus(other: UnitPressure<*>) = (this as UnitPressure<*>).minusUnit(other)
    operator fun times(other: UnitArea<*>) = (this as UnitPressure<*>).timesUnit(other)
}

@JvmInline
value class PoundPerSquareInch(override val value: Double) : UnitPressure<PoundPerSquareInch> {
    override fun asType(d: Double) = PoundPerSquareInch(d)
    override fun asBaseUnit() = com.measures.pressure.Pascal(this.value * 6894.757)

    operator fun plus(other: UnitPressure<*>) = (this as UnitPressure<*>).plusUnit(other)
    operator fun minus(other: UnitPressure<*>) = (this as UnitPressure<*>).minusUnit(other)
    operator fun times(other: UnitArea<*>) = (this as UnitPressure<*>).timesUnit(other)
}

@JvmInline
value class Millibar(override val value: Double) : UnitPressure<Millibar> {
    override fun asType(d: Double) = Millibar(d)
    override fun asBaseUnit() = com.measures.pressure.Pascal(this.value * 100.0)

    operator fun plus(other: UnitPressure<*>) = (this as UnitPressure<*>).plusUnit(other)
    operator fun minus(other: UnitPressure<*>) = (this as UnitPressure<*>).minusUnit(other)
    operator fun times(other: UnitArea<*>) = (this as UnitPressure<*>).timesUnit(other)
}

@JvmInline
value class Kilopascal(override val value: Double) : UnitPressure<Kilopascal> {
    override fun asType(d: Double) = Kilopascal(d)
    override fun asBaseUnit() = com.measures.pressure.Pascal(this.value * 1000.0)

    operator fun plus(other: UnitPressure<*>) = (this as UnitPressure<*>).plusUnit(other)
    operator fun minus(other: UnitPressure<*>) = (this as UnitPressure<*>).minusUnit(other)
    operator fun times(other: UnitArea<*>) = (this as UnitPressure<*>).timesUnit(other)
}

@JvmInline
value class Megapascal(override val value: Double) : UnitPressure<Megapascal> {
    override fun asType(d: Double) = Megapascal(d)
    override fun asBaseUnit() = com.measures.pressure.Pascal(this.value * 1E6)

    operator fun plus(other: UnitPressure<*>) = (this as UnitPressure<*>).plusUnit(other)
    operator fun minus(other: UnitPressure<*>) = (this as UnitPressure<*>).minusUnit(other)
    operator fun times(other: UnitArea<*>) = (this as UnitPressure<*>).timesUnit(other)
}

// Conversion functions for non-SI pressure units
fun UnitPressure<*>.toBar() = toUnit(Bar(1.0))
fun UnitPressure<*>.toAtmosphere() = toUnit(Atmosphere(1.0))
fun UnitPressure<*>.toTorr() = toUnit(Torr(1.0))
fun UnitPressure<*>.toMillimeterOfMercury() = toUnit(MillimeterOfMercury(1.0))
fun UnitPressure<*>.toPoundPerSquareInch() = toUnit(PoundPerSquareInch(1.0))
fun UnitPressure<*>.toMillibar() = toUnit(Millibar(1.0))
fun UnitPressure<*>.toKilopascal() = toUnit(Kilopascal(1.0))
fun UnitPressure<*>.toMegapascal() = toUnit(Megapascal(1.0))
