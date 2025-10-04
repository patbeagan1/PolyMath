package com.measures.area.us_survey_1959

import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import com.measures.volume.UnitVolume
import kotlin.jvm.JvmInline

@JvmInline
value class SurveySquareYard(override val value: Double) : UnitArea<SurveySquareYard> {
    override fun asType(d: Double) = SurveySquareYard(d)
    override fun asBaseUnit() = SurveySquareFoot(value * 9).asBaseUnit()

    override fun plus(other: UnitArea<*>): SquareMeter = UnitArea.plusUnit(this, other)
    override fun minus(other: UnitArea<*>): SquareMeter = UnitArea.minusUnit(this, other)
    override fun times(other: UnitDistance<*>): UnitVolume<*> = UnitArea.timesUnit(this, other)
}

fun UnitArea<*>.toSurveySquareYard() = toUnit(SurveySquareYard(1.0))
