package com.measures.area

import com.measures.distance.UnitDistance
import com.measures.volume.UnitVolume
import kotlin.jvm.JvmInline

// English International Area Units
fun UnitArea<*>.toInternationalSquareInch() = toUnit(InternationalSquareInch(1.0))
fun UnitArea<*>.toInternationalSquareFoot() = toUnit(InternationalSquareFoot(1.0))
fun UnitArea<*>.toInternationalSquareMile() = toUnit(InternationalSquareMile(1.0))
fun UnitArea<*>.toInternationalSquareYard() = toUnit(InternationalSquareYard(1.0))

@JvmInline
value class InternationalSquareInch(override val value: Double) : UnitArea<InternationalSquareInch> {
    override fun asType(d: Double) = InternationalSquareInch(d)
    override fun asBaseUnit() = InternationalSquareFoot(value / 144.0).asBaseUnit()

    override operator fun plus(other: UnitArea<*>) = (this as UnitArea<*>).plusUnit(other)
    override operator fun minus(other: UnitArea<*>) = (this as UnitArea<*>).minusUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitArea<*>).timesUnit(other)
    override operator fun div(other: UnitDistance<*>) = (this as UnitArea<*>).divUnit(other)
}

@JvmInline
value class InternationalSquareFoot(override val value: Double) : UnitArea<InternationalSquareFoot> {
    override fun asType(d: Double) = InternationalSquareFoot(d)
    override fun asBaseUnit() = SquareMeter(value * 0.09290)

    override operator fun plus(other: UnitArea<*>) = (this as UnitArea<*>).plusUnit(other)
    override operator fun minus(other: UnitArea<*>) = (this as UnitArea<*>).minusUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitArea<*>).timesUnit(other)
    override operator fun div(other: UnitDistance<*>) = (this as UnitArea<*>).divUnit(other)
}

@JvmInline
value class InternationalSquareYard(override val value: Double) : UnitArea<InternationalSquareYard> {
    override fun asType(d: Double) = InternationalSquareYard(d)
    override fun asBaseUnit() = InternationalSquareFoot(value * 3 * 3).asBaseUnit()

    override operator fun plus(other: UnitArea<*>) = (this as UnitArea<*>).plusUnit(other)
    override operator fun minus(other: UnitArea<*>) = (this as UnitArea<*>).minusUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitArea<*>).timesUnit(other)
    override operator fun div(other: UnitDistance<*>) = (this as UnitArea<*>).divUnit(other)
}

@JvmInline
value class InternationalSquareMile(override val value: Double) : UnitArea<InternationalSquareMile> {
    override fun asType(d: Double) = InternationalSquareMile(d)
    override fun asBaseUnit() = InternationalSquareYard(value * 1760 * 1760).asBaseUnit()

    override operator fun plus(other: UnitArea<*>) = (this as UnitArea<*>).plusUnit(other)
    override operator fun minus(other: UnitArea<*>) = (this as UnitArea<*>).minusUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitArea<*>).timesUnit(other)
    override operator fun div(other: UnitDistance<*>) = (this as UnitArea<*>).divUnit(other)
}
