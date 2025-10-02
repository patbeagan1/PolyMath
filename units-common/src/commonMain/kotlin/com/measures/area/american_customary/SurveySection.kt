package com.measures.area.american_customary

import com.measures.area.UnitArea
import com.measures.area.american_customary.SurveyAcre
import com.measures.distance.UnitDistance
import kotlin.jvm.JvmInline

@JvmInline
value class SurveySection(override val value: Double) : UnitArea<SurveySection> {
    override fun asType(d: Double) = SurveySection(d)
    override fun asBaseUnit() = SurveyAcre(value * 640).asBaseUnit()

    override operator fun plus(other: UnitArea<*>) = UnitArea.Companion.plusUnit(this, other)
    override operator fun minus(other: UnitArea<*>) = UnitArea.Companion.minusUnit(this, other)
    override operator fun times(other: UnitDistance<*>) = UnitArea.Companion.timesUnit(this, other)
    override operator fun div(other: UnitDistance<*>) = UnitArea.Companion.divUnit(this, other)
}

fun UnitArea<*>.toSurveySection() = toUnit(SurveySection(1.0))
