package com.measures.distance.us_survey_1959

import com.measures.area.UnitArea
import com.measures.distance.UnitDistance
import com.measures.distance.Meter
import com.measures.time.UnitTime
import com.measures.velocity.MetersPerSecond
import kotlin.jvm.JvmInline

@JvmInline
value class SurveyLeague(override val value: Double) : UnitDistance<SurveyLeague> {
    override fun asType(d: Double) = SurveyLeague(d)
    override fun asBaseUnit() = Meter(value * 19008000/3937)

    override operator fun plus(other: UnitDistance<*>) = UnitDistance.plusUnit(this, other)
    override operator fun minus(other: UnitDistance<*>) = UnitDistance.minusUnit(this, other)
    override operator fun times(other: UnitDistance<*>) = UnitDistance.timesUnit(this, other)
    override operator fun times(other: UnitArea<*>) = UnitDistance.timesUnit(this, other)
    override operator fun div(other: UnitTime<*>): MetersPerSecond = UnitDistance.divUnit(this, other)
}

fun UnitDistance<*>.toSurveyLeague() = toUnit(SurveyLeague(1.0))
