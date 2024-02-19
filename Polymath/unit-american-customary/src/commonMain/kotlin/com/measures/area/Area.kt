package com.measures.area

import kotlin.jvm.JvmInline

fun UnitArea<*>.toSquareSurveyFoot() = toUnit(SquareSurveyFoot(1.0))
fun UnitArea<*>.toSquareSurveyChain() = toUnit(SquareSurveyChain(1.0))
fun UnitArea<*>.toSurveyAcre() = toUnit(SurveyAcre(1.0))
fun UnitArea<*>.toSurveySection() = toUnit(SurveySection(1.0))
fun UnitArea<*>.toSurveyTownship() = toUnit(SurveyTownship(1.0))

@JvmInline
value class SquareSurveyFoot(override val value: Double) : UnitArea<SquareSurveyFoot> {
    override fun asType(d: Double) = SquareSurveyFoot(d)
    override fun asBaseUnit() = SquareMeter(value * 0.09290341)
}

@JvmInline
value class SquareSurveyChain(override val value: Double) : UnitArea<SquareSurveyChain> {
    override fun asType(d: Double) = SquareSurveyChain(d);
    override fun asBaseUnit() = SquareSurveyFoot(value * 4356.0).asBaseUnit()
}

@JvmInline
value class SurveyAcre(override val value: Double) : UnitArea<SurveyAcre> {
    override fun asType(d: Double) = SurveyAcre(d);
    override fun asBaseUnit() = SquareSurveyChain(value * 10.0).asBaseUnit()
}

@JvmInline
value class SurveySection(override val value: Double) : UnitArea<SurveySection> {
    override fun asType(d: Double): SurveySection = SurveySection(d)
    override fun asBaseUnit() = SurveyAcre(value * 640).asBaseUnit()
}

@JvmInline
value class SurveyTownship(override val value: Double) : UnitArea<SurveyTownship> {
    override fun asType(d: Double) = SurveyTownship(d)
    override fun asBaseUnit() = SurveySection(value * 36.0).asBaseUnit()
}
