package com.measures.luminous

import com.measures.BaseUnit
import com.measures.DoubleBase
import com.measures.UnitTypedFull
import kotlin.jvm.JvmInline

typealias UnitLuminous<T> = UnitLuminousTypedFull<T>

interface UnitLuminousTypedFull<T : DoubleBase> : UnitTypedFull<T, Candela> {
    operator fun plus(other: UnitLuminousTypedFull<*>) =
        Candela(this.asBaseUnit().value + other.asBaseUnit().value)

    operator fun minus(other: UnitLuminousTypedFull<*>) =
        Candela(this.asBaseUnit().value - other.asBaseUnit().value)
}

@JvmInline
value class Candela(override val value: Double) : UnitLuminous<Candela>, BaseUnit {
    override fun asType(d: Double) = Candela(d)
    override fun asBaseUnit() = this
}

@JvmInline
value class Millicandela(override val value: Double) : UnitLuminous<Millicandela> {
    override fun asType(d: Double) = Millicandela(d)
    override fun asBaseUnit() = Candela(this.value * 0.001)
}

@JvmInline
value class Kilocandela(override val value: Double) : UnitLuminous<Kilocandela> {
    override fun asType(d: Double) = Kilocandela(d)
    override fun asBaseUnit() = Candela(this.value * 1000.0)
}

@JvmInline
value class Megacandela(override val value: Double) : UnitLuminous<Megacandela> {
    override fun asType(d: Double) = Megacandela(d)
    override fun asBaseUnit() = Candela(this.value * 1E6)
}

// Conversion functions using toUnit
fun UnitLuminous<*>.toCandela() = this.asBaseUnit()
fun UnitLuminous<*>.toMillicandela() = toUnit(Millicandela(1.0))
fun UnitLuminous<*>.toKilocandela() = toUnit(Kilocandela(1.0))
fun UnitLuminous<*>.toMegacandela() = toUnit(Megacandela(1.0))
