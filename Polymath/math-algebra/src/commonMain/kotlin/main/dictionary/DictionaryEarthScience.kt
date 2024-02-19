package main.dictionary

import dev.patbeagan.math.base.GenericSymbols


interface DictionaryEarthScience {

    interface EnvironmentalScienceSymbols {
        val symCarbonDioxide get() = "CO_2"
        val symMethane get() = "CH_4"
        val symGreenhouseEffect get() = "GE"
    }

    interface EnvironmentalScienceEquations : EnvironmentalScienceSymbols, GenericSymbols {
        val greenhouseGasEffect: String
            get() = "$symCarbonDioxide and $symMethane increase -> $symGreenhouseEffect $symArrowUp"
    }

    interface GeologySymbols {
        val symPlateTectonicSpeed get() = "v"
        val symTime get() = "t"
        val symDistance get() = "d"
    }

    interface GeologyEquations : GeologySymbols {
        val plateMovementOverTime: String
            get() = "$symDistance = $symPlateTectonicSpeed x $symTime"
    }
}