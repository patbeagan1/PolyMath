package com.measures.area.american_customary

import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import kotlin.jvm.JvmInline

@JvmInline
value class SquareSurveyChain(override val value: Double) : UnitArea<SquareSurveyChain> {
    override fun asType(d: Double) = SquareSurveyChain(d)
    override fun asBaseUnit() = SquareSurveyFoot(value * 4356.0).asBaseUnit()

    override operator fun plus(other: UnitArea<*>) = UnitArea.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitArea<*>) = UnitArea.Companion.minusUnit(this, other)
    override operator fun times(other: UnitDistance<*>) = UnitArea.Companion.timesUnit(this, other)
    override operator fun div(other: UnitDistance<*>) = UnitArea.Companion.divUnit(this, other)
}

fun UnitArea<*>.toSquareSurveyChain() = toUnit(SquareSurveyChain(1.0))
