package com.measures

import InternationalCable
import InternationalFathom
import InternationalNauticalMile
import com.measures.area.SquareMeter
import com.measures.distance.SurveyChain
import com.measures.distance.SurveyFoot
import com.measures.distance.SurveyFurlong
import com.measures.distance.SurveyLeague
import com.measures.distance.SurveyLink
import com.measures.distance.SurveyMile
import com.measures.distance.SurveyRod
import com.measures.international.distance.InternationalFoot
import com.measures.international.distance.InternationalInch
import com.measures.international.distance.InternationalMile
import com.measures.international.distance.InternationalPica
import com.measures.international.distance.InternationalPoint
import com.measures.international.distance.InternationalYard
import com.measures.international.distance.toInternationalFoot
import com.measures.international.distance.toInternationalInch
import com.measures.international.distance.toInternationalMile
import com.measures.international.distance.toInternationalYard
import com.measures.metric.distance.Centimeter
import com.measures.metric.distance.Kilometer
import com.measures.metric.distance.toKilometer
import com.measures.metric.distance.toMillimeter
import com.measures.metric.volume.Attoliter
import com.measures.metric.volume.Milliliter
import com.measures.metric.volume.toMegaliter
import com.measures.metric.volume.toMilliliter
import com.measures.other.Capefeet
import com.measures.other.Microns
import com.measures.other.toCapefeet
import com.measures.volume.ImperialGallon
import com.measures.volume.Liter
import com.measures.volume.fluid.USFluidGallon
import com.measures.volume.fluid.USFluidOunce
import com.measures.volume.fluid.toUSFluidGallon
import com.measures.volume.toImperialFluidOunce
import com.measures.volume.toLiter
import com.measures.weight.Gram
import com.measures.weight.Pound
import com.measures.weight.TroyPennyweight
import com.measures.weight.TroyPound
import com.measures.weight.toGrain
import com.measures.weight.toGram
import com.measures.weight.toOunce
import com.measures.weight.toPound

class Runtime {
    private val distances = listOf(
        com.measures.distance.Meter(1.0) + com.measures.distance.Meter(2.0),
//    Meters(1.0) / Meters(2.0),
        Kilometer(3.0).asBaseUnit().toInternationalInch(),
        Centimeter(200.0) - Centimeter(1.0),
        Kilometer(1.0) + com.measures.distance.Meter(1.0),
        (Kilometer(1.0) + com.measures.distance.Meter(1.0)).toKilometer(),
        com.measures.distance.Meter(2.5).toUnit(Centimeter(1.0)),
        InternationalInch(4.0).asBaseUnit().toInternationalInch(),
        Centimeter(4.0).toInternationalInch(),
        Centimeter(2.54).toInternationalInch(),
        InternationalInch(400.0).asBaseUnit().toKilometer(),
        InternationalFoot(1.0).toInternationalInch(),
        InternationalFoot(3.0).toInternationalYard(),
        (SquareMeter(1.0) / InternationalFoot(1.0)).toInternationalYard(),
        InternationalYard(1.0).toCapefeet(),
        (Capefeet(2.0) + com.measures.distance.Meter(6.0) - InternationalInch(5.0)).toInternationalFoot(),
        Microns(20.0).toMillimeter(),
        SurveyMile(1.0).toInternationalMile(),
        SurveyLeague(1.0).toInternationalMile(),
//        ImperialInch(2.0).toImperialHand()
    ).map { it to it }
    private val volumes = listOf(
        Liter(4.0).toMilliliter() + Milliliter(6.0),
        USFluidOunce(1.0).toImperialFluidOunce(),
        USFluidGallon(1.0).toLiter(),
        Attoliter(10000.0).toUSFluidGallon(),
        ImperialGallon(1.0).toUSFluidGallon(),
        Liter(1.0).toUSFluidGallon()
    ).map { it to it }
    private val other = listOf(
        com.measures.distance.Meter(2.0) * com.measures.distance.Meter(2.0),
        SquareMeter(4.0) / com.measures.distance.Meter(2.0),
        SquareMeter(4.0) * com.measures.distance.Meter(2.0),
        (SurveyFurlong(1.0) * SurveyFurlong(1.0) * SurveyFurlong(1.0)).toMegaliter()
    ).map { it to it }

    fun run() {
        distances.printNumbered()
        volumes.printNumbered()
        other.printNumbered()

        listOf(
            InternationalPoint(1.0),
            InternationalPica(1.0),
            InternationalInch(1.0),
            InternationalFoot(1.0),
            InternationalYard(1.0),
            InternationalMile(1.0),
            SurveyLink(1.0),
            SurveyFoot(1.0),
            SurveyRod(1.0),
            SurveyChain(1.0),
            SurveyFurlong(1.0),
            SurveyMile(1.0),
            SurveyLeague(1.0),
            InternationalFathom(1.0),
            InternationalCable(1.0),
            InternationalNauticalMile(1.0)
        ).map {
            it to it.asBaseUnit()
        }.sortedBy {
            it.second.value
        }.printNumbered()

        listOf(
            Gram(1.0).toGrain(),
            Pound(1.0).toOunce(),
            TroyPound(1.0).toPound(),
            TroyPennyweight(1.0)
        ).map {
            it to it
        }.sortedBy {
            it.second.toGram().value
        }.printNumbered()
    }

    private fun List<Pair<UnitTypedFull<*, *>, UnitTypedFull<*, *>>>.printNumbered() {
        forEachIndexed { index, it ->
            println("$index\t${it.first::class.java.simpleName}\t${it.second}")
        }
        println()
    }
}