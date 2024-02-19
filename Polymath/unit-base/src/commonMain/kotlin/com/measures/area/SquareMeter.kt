package com.measures.area

import com.measures.BaseUnit
import kotlin.jvm.JvmInline

@JvmInline
value class SquareMeter(override val value: Double) : UnitArea<SquareMeter>, BaseUnit {
    override fun asType(d: Double): SquareMeter = SquareMeter(d)
    override fun asBaseUnit(): SquareMeter = this
}