package com.measures.luminous

import com.measures.luminous.Candela
import com.measures.luminous.UnitLuminous
import kotlin.jvm.JvmInline

@JvmInline
value class Millicandela(override val value: Double) : UnitLuminous<Millicandela> {
    override fun asType(d: Double) = Millicandela(d)
    override fun asBaseUnit() = Candela(this.value * 0.001)

    operator fun plus(other: UnitLuminous<*>) = (this as UnitLuminous<*>).plusUnit(other)
    operator fun minus(other: UnitLuminous<*>) = (this as UnitLuminous<*>).minusUnit(other)
}

@JvmInline
value class Kilocandela(override val value: Double) : UnitLuminous<Kilocandela> {
    override fun asType(d: Double) = Kilocandela(d)
    override fun asBaseUnit() = Candela(this.value * 1000.0)

    operator fun plus(other: UnitLuminous<*>) = (this as UnitLuminous<*>).plusUnit(other)
    operator fun minus(other: UnitLuminous<*>) = (this as UnitLuminous<*>).minusUnit(other)
}

@JvmInline
value class Megacandela(override val value: Double) : UnitLuminous<Megacandela> {
    override fun asType(d: Double) = Megacandela(d)
    override fun asBaseUnit() = Candela(this.value * 1E6)

    operator fun plus(other: UnitLuminous<*>) = (this as UnitLuminous<*>).plusUnit(other)
    operator fun minus(other: UnitLuminous<*>) = (this as UnitLuminous<*>).minusUnit(other)
}

// Conversion functions using toUnit
fun UnitLuminous<*>.toMillicandela() = toUnit(Millicandela(1.0))
fun UnitLuminous<*>.toKilocandela() = toUnit(Kilocandela(1.0))
fun UnitLuminous<*>.toMegacandela() = toUnit(Megacandela(1.0))
