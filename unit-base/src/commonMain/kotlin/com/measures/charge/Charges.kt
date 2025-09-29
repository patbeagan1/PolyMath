package com.measures.charge

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.current.UnitCurrent
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

typealias UnitCharge<T> = UnitChargeType<T>

interface UnitChargeType<T : DoubleBase> : UnitType<T, Coulomb>

@JvmInline
value class Coulomb(override val value: Double) : UnitCharge<Coulomb>, BaseUnit {
    override fun asType(d: Double) = Coulomb(d)
    override fun asBaseUnit() = this
    
    companion object {
        fun from(current: UnitCurrent<*>, time: UnitTime<*>): Coulomb {
            val currentBase = current.asBaseUnit()
            val timeBase = time.asBaseUnit()
            return Coulomb(currentBase.value * timeBase.value)
        }
    }
}

@JvmInline
value class Millicoulomb(override val value: Double) : UnitCharge<Millicoulomb> {
    override fun asType(d: Double) = Millicoulomb(d)
    override fun asBaseUnit() = Coulomb(this.value * 0.001)
}

@JvmInline
value class Microcoulomb(override val value: Double) : UnitCharge<Microcoulomb> {
    override fun asType(d: Double) = Microcoulomb(d)
    override fun asBaseUnit() = Coulomb(this.value * 1E-6)
}

@JvmInline
value class Nanocoulomb(override val value: Double) : UnitCharge<Nanocoulomb> {
    override fun asType(d: Double) = Nanocoulomb(d)
    override fun asBaseUnit() = Coulomb(this.value * 1E-9)
}

@JvmInline
value class Picocoulomb(override val value: Double) : UnitCharge<Picocoulomb> {
    override fun asType(d: Double) = Picocoulomb(d)
    override fun asBaseUnit() = Coulomb(this.value * 1E-12)
}

@JvmInline
value class Kilocoulomb(override val value: Double) : UnitCharge<Kilocoulomb> {
    override fun asType(d: Double) = Kilocoulomb(d)
    override fun asBaseUnit() = Coulomb(this.value * 1000.0)
}

@JvmInline
value class AmpereHour(override val value: Double) : UnitCharge<AmpereHour> {
    override fun asType(d: Double) = AmpereHour(d)
    override fun asBaseUnit() = Coulomb(this.value * 3600.0)
}

@JvmInline
value class MilliampereHour(override val value: Double) : UnitCharge<MilliampereHour> {
    override fun asType(d: Double) = MilliampereHour(d)
    override fun asBaseUnit() = Coulomb(this.value * 3.6)
}

operator fun UnitChargeType<*>.plus(other: UnitChargeType<*>): Coulomb =
    Coulomb(this.asBaseUnit().value + other.asBaseUnit().value)

operator fun UnitChargeType<*>.minus(other: UnitChargeType<*>): Coulomb =
    Coulomb(this.asBaseUnit().value - other.asBaseUnit().value)

// Conversion functions using toUnit
fun UnitCharge<*>.toCoulomb() = this.asBaseUnit()
fun UnitCharge<*>.toMillicoulomb() = toUnit(Millicoulomb(1.0))
fun UnitCharge<*>.toMicrocoulomb() = toUnit(Microcoulomb(1.0))
fun UnitCharge<*>.toNanocoulomb() = toUnit(Nanocoulomb(1.0))
fun UnitCharge<*>.toPicocoulomb() = toUnit(Picocoulomb(1.0))
fun UnitCharge<*>.toKilocoulomb() = toUnit(Kilocoulomb(1.0))
fun UnitCharge<*>.toAmpereHour() = toUnit(AmpereHour(1.0))
fun UnitCharge<*>.toMilliampereHour() = toUnit(MilliampereHour(1.0))

