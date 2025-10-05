package com.measures.area.us_survey_1959

import com.measures.area.SquareMeter
import com.measures.area.UnitArea
import com.measures.distance.Meter
import com.measures.distance.UnitDistance
import com.measures.volume.Liter
import kotlin.jvm.JvmInline

@JvmInline
value class SurveySquareChain(override val value: Double) : UnitArea<SurveySquareChain> {
    override fun asType(d: Double) = SurveySquareChain(d)
    override fun asBaseUnit() = SquareMeter(value * 404.68564224)

    override fun plus(other: UnitArea<*>): SquareMeter = UnitArea.plusUnit(this, other)
    override fun minus(other: UnitArea<*>): SquareMeter = UnitArea.minusUnit(this, other)
    override fun times(other: UnitDistance<*>): Liter = UnitArea.timesUnit(this, other)
    override fun div(other: UnitDistance<*>): Meter = UnitArea.divUnit(this, other)
}

fun UnitArea<*>.toSurveySquareChain() = toUnit(SurveySquareChain(1.0))
