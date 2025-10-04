package com.measures.distance.us_survey_1959

import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import com.measures.distance.Meter
import com.measures.time.UnitTime
import com.measures.velocity.MetersPerSecond
import kotlin.jvm.JvmInline

@JvmInline
value class SurveyChain(override val value: Double) : UnitDistance<SurveyChain> {
    override fun asType(d: Double) = SurveyChain(d)
    override fun asBaseUnit() = SurveyFoot(value * 66).asBaseUnit()

    override operator fun plus(other: UnitDistance<*>) = UnitDistance.plusUnit(this, other)
    override operator fun minus(other: UnitDistance<*>) = UnitDistance.minusUnit(this, other)
    override operator fun times(other: UnitDistance<*>) = UnitDistance.timesUnit(this, other)
    override operator fun times(other: UnitArea<*>) = UnitDistance.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>): MetersPerSecond = UnitDistance.divUnit(this, other)
}

fun UnitDistance<*>.toSurveyChain() = toUnit(SurveyChain(1.0))
