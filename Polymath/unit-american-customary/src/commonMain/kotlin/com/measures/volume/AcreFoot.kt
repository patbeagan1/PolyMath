package com.measures.volume

import kotlin.jvm.JvmInline

@JvmInline
value class Acrefoot(override val value: Double) : UnitVolume<Acrefoot> {
    override fun asType(d: Double) = Acrefoot(d)
    override fun asBaseUnit() = Liter(value * 1233.481838 * 1000)
}

fun UnitVolume<*>.toAcrefoot() = toUnit(Acrefoot(1.0))