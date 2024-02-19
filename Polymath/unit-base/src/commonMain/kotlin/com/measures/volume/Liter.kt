package com.measures.volume

import com.measures.BaseUnit
import kotlin.jvm.JvmInline

@JvmInline
value class Liter(override val value: Double) : UnitVolume<Liter>, BaseUnit {
    override fun asType(d: Double) = Liter(d)
    override fun asBaseUnit() = this
}

fun UnitVolume<*>.toLiter() = this.asBaseUnit()