package dev.patbeagan.physics.classical

interface DictionaryPhysicsOptics {

    interface OpticsSymbols {
        val symAngleIncidence get() = "\\theta_i"
        val symAngleRefraction get() = "\\theta_r"
        val symAngularResolution get() = "\\theta"
        val symFocalLength get() = "f"
        val symFrequency get() = "\\nu"
        val symHeightImage get() = "h_i"
        val symHeightObject get() = "h_o"
        val symImageDistance get() = "d_i"
        val symIndexRefraction get() = "n"
        val symLensPower get() = "P"
        val symMagnification get() = "M"
        val symObjectDistance get() = "d_o"
        val symOpticalPathLength get() = "OPL"
        val symRefractiveIndex get() = "n"
        val symSpeedOfLight get() = "c"
        val symWavelength get() = "\\lambda"
    }

    interface OpticsFormulas : OpticsSymbols {

        // Speed of light in a medium
        val lightSpeedInMedium: String
            get() = "v = \\frac{$symSpeedOfLight}{$symIndexRefraction}"

        // Relationship between speed, frequency, and wavelength
        val waveEquation: String
            get() = "c = $symFrequency \\times $symWavelength"

        // Snell's Law
        val snellsLaw: String
            get() = "$symIndexRefraction \\times \\sin($symAngleIncidence) = n' \\times \\sin($symAngleRefraction)"

        // Lens formula (Thin lens formula)
        val lensFormula: String
            get() = "\\frac{1}{$symFocalLength} = \\frac{1}{$symObjectDistance} + \\frac{1}{$symImageDistance}"

        // Magnification formula for lenses
        val magnificationLens: String
            get() = "$symMagnification = -\\frac{$symImageDistance}{$symObjectDistance} = \\frac{$symHeightImage}{$symHeightObject}"

        // Power of a lens
        val lensPowerFormula: String
            get() = "$symLensPower = \\frac{1}{$symFocalLength}"
    }
}