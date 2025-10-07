package com.measures.area.us_customary

import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.distance.Meter
import com.measures.distance.UnitDistance
import com.measures.volume.Liter
import kotlin.jvm.JvmInline

@JvmInline
value class USSquareSurveyChain(override val value: Double) : UnitArea<USSquareSurveyChain> {
    override fun asType(d: Double) = USSquareSurveyChain(d)
    override fun asBaseUnit() = USSquareFoot(value * 4356).asBaseUnit()

    override fun plus(other: UnitArea<*>): SquareMeter = UnitArea.plusUnit(this, other)
    override fun minus(other: UnitArea<*>): SquareMeter = UnitArea.minusUnit(this, other)
    override fun times(other: UnitDistance<*>): Liter = UnitArea.timesUnit(this, other)
    override fun div(other: UnitDistance<*>): Meter = UnitArea.divUnit(this, other)
}

fun UnitArea<*>.toUSSquareSurveyChain() = toUnit(USSquareSurveyChain(1.0))
