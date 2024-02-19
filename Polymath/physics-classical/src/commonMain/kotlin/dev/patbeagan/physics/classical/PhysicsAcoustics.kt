package dev.patbeagan.physics.classical

import main.dsl.expressions.ScalarAlgebra.*
import main.dsl.expressions.ScalarRelation
import main.dsl.mathnum.Variable
import main.dsl.mathnum.num

object PhysicsAcoustics {
    val symSpeedOfSound get() = Variable("v")
    val symFrequency get() = Variable("f")
    val symWavelength get() = Variable("\\lambda")
    val symIntensity get() = Variable("I")
    val symAmplitude get() = Variable("A")
    val symPressure get() = Variable("P")
    val symChangeInPressure get() = Variable("\\Delta P")
    val symDensity get() = Variable("\\rho")
    val symBulkModulus get() = Variable("B")
    val symDecibelLevel get() = Variable("\\Beta")
    val symReferenceIntensity get() = Variable("I_0")

    fun waveEquation(
        frequency: Double = Double.NaN,
        wavelength: Double = Double.NaN
    ): ScalarRelation.Equation {
        val f = symFrequency.setTo(frequency)
        val w = symWavelength.setTo(wavelength)
        return symSpeedOfSound.standsFor(f * w)
    }

    fun intensityFormula(
        density: Double = Double.NaN,
        speedOfSound: Double = Double.NaN,
        amplitude: Double = Double.NaN,
        frequency: Double = Double.NaN
    ): ScalarRelation.Equation {
        val d = symDensity.setTo(density)
        val s = symSpeedOfSound.setTo(speedOfSound)
        val a = symAmplitude.setTo(amplitude)
        val f = symFrequency.setTo(frequency)
        return symIntensity.standsFor((1.num() / 2.num()) * d * s * a.squared() * f.squared())
    }

    fun speedOfSoundInMedium(
        bulkModulus: Double = Double.NaN,
        density: Double = Double.NaN
    ): ScalarRelation.Equation {
        val b = symBulkModulus.setTo(bulkModulus)
        val d = symDensity.setTo(density)
        return symSpeedOfSound.standsFor(Sqrt(b / d))
    }

    fun decibelLevelFormula(
        intensity: Double = Double.NaN,
        referenceIntensity: Double = Double.NaN
    ): ScalarRelation.Equation {
        val i = symIntensity.setTo(intensity)
        val r = symReferenceIntensity.setTo(referenceIntensity)
        return symDecibelLevel.standsFor(10.num() * Log(10.num(), i / r))
    }

    fun pressureVariation(
        density: Double = Double.NaN,
        speedOfSound: Double = Double.NaN,
        amplitude: Double = Double.NaN
    ): ScalarRelation.Equation {
        val d = symDensity.setTo(density)
        val s = symSpeedOfSound.setTo(speedOfSound)
        val a = symAmplitude.setTo(amplitude)
        return symChangeInPressure.standsFor(d * s * a)
    }
}