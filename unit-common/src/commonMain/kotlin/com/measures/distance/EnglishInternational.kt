package com.measures.distance

import com.measures.area.UnitArea
import kotlin.jvm.JvmInline

// English International Distance Units
fun UnitDistance<*>.toInternationalFoot() = toUnit(InternationalFoot(1.0))
fun UnitDistance<*>.toInternationalInch() = toUnit(InternationalInch(1.0))
fun UnitDistance<*>.toInternationalMile() = toUnit(InternationalMile(1.0))
fun UnitDistance<*>.toInternationalPica() = toUnit(InternationalPica(1.0))
fun UnitDistance<*>.toInternationalPoint() = toUnit(InternationalPoint(1.0))
fun UnitDistance<*>.toInternationalYard() = toUnit(InternationalYard(1.0))

@JvmInline
value class InternationalFoot(override val value: Double) : UnitDistance<InternationalFoot> {
    override fun asType(d: Double) = InternationalFoot(d)
    override fun asBaseUnit() = InternationalYard(value / 3.0).asBaseUnit()

    override operator fun plus(other: UnitDistance<*>) = (this as UnitDistance<*>).plusUnit(other)
    override operator fun minus(other: UnitDistance<*>) = (this as UnitDistance<*>).minusUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun times(other: UnitArea<*>) = (this as UnitDistance<*>).timesUnit(other)
}

@JvmInline
value class InternationalInch(override val value: Double) : UnitDistance<InternationalInch> {
    override fun asType(d: Double) = InternationalInch(d)
    override fun asBaseUnit() = InternationalFoot(value / 12.0).asBaseUnit()

    override operator fun plus(other: UnitDistance<*>) = (this as UnitDistance<*>).plusUnit(other)
    override operator fun minus(other: UnitDistance<*>) = (this as UnitDistance<*>).minusUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun times(other: UnitArea<*>) = (this as UnitDistance<*>).timesUnit(other)
}

@JvmInline
value class InternationalMile(override val value: Double) : UnitDistance<InternationalMile> {
    override fun asType(d: Double) = InternationalMile(d)
    override fun asBaseUnit() = InternationalYard(value * 1760.0).asBaseUnit()

    override operator fun plus(other: UnitDistance<*>) = (this as UnitDistance<*>).plusUnit(other)
    override operator fun minus(other: UnitDistance<*>) = (this as UnitDistance<*>).minusUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun times(other: UnitArea<*>) = (this as UnitDistance<*>).timesUnit(other)
}

@JvmInline
value class InternationalPica(override val value: Double) : UnitDistance<InternationalPica> {
    override fun asType(d: Double) = InternationalPica(d)
    override fun asBaseUnit() = InternationalInch(value / 6.0).asBaseUnit()

    override operator fun plus(other: UnitDistance<*>) = (this as UnitDistance<*>).plusUnit(other)
    override operator fun minus(other: UnitDistance<*>) = (this as UnitDistance<*>).minusUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun times(other: UnitArea<*>) = (this as UnitDistance<*>).timesUnit(other)
}

@JvmInline
value class InternationalPoint(override val value: Double) : UnitDistance<InternationalPoint> {
    override fun asType(d: Double) = InternationalPoint(d)
    override fun asBaseUnit() = InternationalPica(value / 12.0).asBaseUnit()

    override operator fun plus(other: UnitDistance<*>) = (this as UnitDistance<*>).plusUnit(other)
    override operator fun minus(other: UnitDistance<*>) = (this as UnitDistance<*>).minusUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun times(other: UnitArea<*>) = (this as UnitDistance<*>).timesUnit(other)
}

@JvmInline
value class InternationalYard(override val value: Double) : UnitDistance<InternationalYard> {
    override fun asType(d: Double) = InternationalYard(d)
    override fun asBaseUnit() = Meter(0.9144 * value)

    override operator fun plus(other: UnitDistance<*>) = (this as UnitDistance<*>).plusUnit(other)
    override operator fun minus(other: UnitDistance<*>) = (this as UnitDistance<*>).minusUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun times(other: UnitArea<*>) = (this as UnitDistance<*>).timesUnit(other)
}

// Nautical Distance Units
fun UnitDistance<*>.toInternationalCable() = toUnit(InternationalCable(1.0))
fun UnitDistance<*>.toInternationalFathom() = toUnit(InternationalFathom(1.0))
fun UnitDistance<*>.toInternationalNauticalMile() = toUnit(InternationalNauticalMile(1.0))

@JvmInline
value class InternationalCable(override val value: Double) : UnitDistance<InternationalCable> {
    override fun asType(d: Double) = InternationalCable(d)
    override fun asBaseUnit() = InternationalFathom(value * 120.0).asBaseUnit()

    override operator fun plus(other: UnitDistance<*>) = (this as UnitDistance<*>).plusUnit(other)
    override operator fun minus(other: UnitDistance<*>) = (this as UnitDistance<*>).minusUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun times(other: UnitArea<*>) = (this as UnitDistance<*>).timesUnit(other)
}

@JvmInline
value class InternationalFathom(override val value: Double) : UnitDistance<InternationalFathom> {
    override fun asType(d: Double) = InternationalFathom(d)
    override fun asBaseUnit() = InternationalYard(value * 2.0).asBaseUnit()

    override operator fun plus(other: UnitDistance<*>) = (this as UnitDistance<*>).plusUnit(other)
    override operator fun minus(other: UnitDistance<*>) = (this as UnitDistance<*>).minusUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun times(other: UnitArea<*>) = (this as UnitDistance<*>).timesUnit(other)
}

@JvmInline
value class InternationalNauticalMile(override val value: Double) : UnitDistance<InternationalNauticalMile> {
    override fun asType(d: Double) = InternationalNauticalMile(d)
    override fun asBaseUnit() = InternationalCable(value * 8.439).asBaseUnit()

    override operator fun plus(other: UnitDistance<*>) = (this as UnitDistance<*>).plusUnit(other)
    override operator fun minus(other: UnitDistance<*>) = (this as UnitDistance<*>).minusUnit(other)
    override operator fun times(other: UnitDistance<*>) = (this as UnitDistance<*>).timesUnit(other)
    override operator fun times(other: UnitArea<*>) = (this as UnitDistance<*>).timesUnit(other)
}
