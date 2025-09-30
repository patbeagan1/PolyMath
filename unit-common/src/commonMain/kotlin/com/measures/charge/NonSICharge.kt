package com.measures.charge

import com.measures.DoubleBase
import com.measures.UnitType
import com.measures.current.Ampere
import com.measures.time.UnitTime
import kotlin.jvm.JvmInline

// Non-SI Charge Units
// These units are not part of the SI system and are moved to units-common

@JvmInline
value class AmpereHour(override val value: Double) : UnitCharge<AmpereHour> {
    override fun asType(d: Double) = AmpereHour(d)
    override fun asBaseUnit() = com.measures.charge.Coulomb(this.value * 3600.0)

    operator fun plus(other: UnitCharge<*>) = (this as UnitCharge<*>).plusUnit(other)
    operator fun minus(other: UnitCharge<*>) = (this as UnitCharge<*>).minusUnit(other)
    operator fun div(other: UnitTime<*>) = (this as UnitCharge<*>).divUnit(other)
}

@JvmInline
value class MilliampereHour(override val value: Double) : UnitCharge<MilliampereHour> {
    override fun asType(d: Double) = MilliampereHour(d)
    override fun asBaseUnit() = com.measures.charge.Coulomb(this.value * 3.6)

    operator fun plus(other: UnitCharge<*>) = (this as UnitCharge<*>).plusUnit(other)
    operator fun minus(other: UnitCharge<*>) = (this as UnitCharge<*>).minusUnit(other)
    operator fun div(other: UnitTime<*>) = (this as UnitCharge<*>).divUnit(other)
}

// Conversion functions for non-SI charge units
fun UnitCharge<*>.toAmpereHour() = toUnit(AmpereHour(1.0))
fun UnitCharge<*>.toMilliampereHour() = toUnit(MilliampereHour(1.0))
