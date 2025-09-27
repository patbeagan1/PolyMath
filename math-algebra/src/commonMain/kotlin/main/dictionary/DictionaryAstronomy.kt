package main.dictionary

interface DictionaryAstronomy {

    // Cosmology Equations
    interface CosmologyEquations : CosmologySymbols {
        val friedmannEquation: String
            get() = "\\left( \\frac{\\dot{a(t)}}{a(t)} \\right)^2 = \\frac{8 \\pi G}{3} \\rho - \\frac{k c^2}{a(t)^2}"
        val criticalDensityEquation: String
            get() = "\\rho_c = \\frac{3 H_0^2}{8 \\pi G}"
    }

    // General Relativity Equations
    interface GeneralRelativityEquations : GeneralRelativitySymbols {
        val einsteinFieldEquations: String
            get() = "$symEinsteinTensor = \\frac{8 \\pi G}{c^4} T_{\\mu \\nu}"
    }


    interface AstrophysicsSymbols {
        val symLuminosity get() = "L"
        val symRadius get() = "R"
        val symTemperature get() = "T"
    }

    interface AstrophysicsEquations : AstrophysicsSymbols {
        val stefanBoltzmannLaw: String
            get() = "$symLuminosity = 4 \\pi $symRadius^2 \\sigma $symTemperature^4"
    }

    // Cosmology Symbols
    interface CosmologySymbols {
        val symHubbleConstant get() = "H_0"
        val symCriticalDensity get() = "\\rho_c"
        val symDarkMatterDensity get() = "\\rho_{DM}"
        val symDarkEnergyDensity get() = "\\rho_{DE}"
        val symCosmologicalConstant get() = "\\Lambda"
        val symScaleFactor get() = "a(t)"
        val symCurvature get() = "k"
    }

    // Astronomy Symbols
    interface AstronomySymbols {
        val symSun get() = "\\odot"
        val symMoon get() = "\\leftmoon"
        val symEarth get() = "\\oplus"
        val symMars get() = "\\textbf{\\mars}"
        val symVenus get() = "\\textbf{\\venus}"
        val symJupiter get() = "\\textbf{\\jupiter}"
        val symSaturn get() = "\\textbf{\\saturn}"
        val symUranus get() = "\\textbf{\\uranus}"
        val symNeptune get() = "\\textbf{\\neptune}"
        val symAsteroid get() = "\\textbf{\\ast}"
        val symStar get() = "\\star"
        val symGalacticCenter get() = "\\textbf{\\milkyway}"
    }

    // Extended Astronomy Symbols
    interface ExtendedAstronomySymbols {
        val symBlackHole get() = "\\textbf{\\blackhole}"
        val symComet get() = "\\textbf{\\comet}"
        val symGalaxy get() = "\\textbf{\\galaxy}"
        val symNebula get() = "\\textbf{\\nebula}"
        val symPulsar get() = "\\textbf{\\pulsar}"
        val symQuasar get() = "\\textbf{\\quasar}"
        val symRedGiant get() = "\\textbf{\\redgiant}"
        val symWhiteDwarf get() = "\\textbf{\\whitedwarf}"
        val symSupernova get() = "\\textbf{\\supernova}"
        val symTelescope get() = "\\textbf{\\telescope}"
    }

    // General Relativity Symbols
    interface GeneralRelativitySymbols {
        val symSchwarzschildRadius get() = "r_s"
        val symEventHorizon get() = "\\Delta"
        val symMetricTensor get() = "g_{\\mu \\nu}"
        val symChristoffelSymbol get() = "\\Gamma^{\\lambda}_{\\mu \\nu}"
        val symRiemannCurvature get() = "R^{\\lambda}_{\\phantom{\\lambda}\\mu \\nu \\sigma}"
        val symRicciCurvature get() = "R_{\\mu \\nu}"
        val symEinsteinTensor get() = "G_{\\mu \\nu}"
    }
}