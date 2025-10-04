package com.measures.area.us_survey_1959

import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import com.measures.volume.UnitVolume
import kotlin.jvm.JvmInline

@JvmInline
value class SurveySquareChain(override val value: Double) : UnitArea<SurveySquareChain> {
    override fun asType(d: Double) = SurveySquareChain(d)
    override fun asBaseUnit() = SurveySquareFoot(value * 4356).asBaseUnit()

    override fun plus(other: UnitArea<*>): SquareMeter = UnitArea.plusUnit(this, other)
    override fun minus(other: UnitArea<*>): SquareMeter = UnitArea.minusUnit(this, other)
    override fun times(other: UnitDistance<*>): UnitVolume<*> = UnitArea.timesUnit(this, other)
}

fun UnitArea<*>.toSurveySquareChain() = toUnit(SurveySquareChain(1.0))
