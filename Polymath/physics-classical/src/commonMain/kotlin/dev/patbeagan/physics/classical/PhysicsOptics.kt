package dev.patbeagan.physics.classical

import main.dsl.expressions.ScalarAlgebra.*
import main.dsl.expressions.ScalarRelation
import main.dsl.mathnum.Variable
import main.dsl.mathnum.num
import main.formulas.PhysicsConstants

object PhysicsOptics : PhysicsConstants {
    val symAngleIncidence get() = Variable("\\theta_i")
    val symAngleRefraction get() = Variable("\\theta_r")
    val symAngularResolution get() = Variable("\\theta")
    val symFocalLength get() = Variable("f")
    val symFrequency get() = Variable("\\nu")
    val symHeightImage get() = Variable("h_i")
    val symHeightObject get() = Variable("h_o")
    val symImageDistance get() = Variable("d_i")
    val symIndexRefraction get() = Variable("n")
    val symLensPower get() = Variable("P")
    val symMagnification get() = Variable("M")
    val symObjectDistance get() = Variable("d_o")
    val symOpticalPathLength get() = Variable("OPL")
    val symRefractiveIndex get() = Variable("n")
    val symWavelength get() = Variable("\\lambda")

    // Speed of light in a medium
    fun lightSpeedInMedium(
        indexRefraction: Double = Double.NaN
    ): ScalarRelation.Equation {
        val r = symIndexRefraction.setTo(indexRefraction)
        return Variable("v").standsFor(symSpeedOfLight / r)
    }

    /**
     * Calculates the wave equation relationship between speed, frequency, and wavelength.
     *
     * The wave equation relates the speed of a wave to its frequency and wavelength.
     * In this context, it's represented by the formula `c = f * w`, where:
     * - `c` is the speed of the wave (in meters per second, m/s),
     * - `f` is the frequency of the wave (in hertz, Hz),
     * - `w` is the wavelength of the wave (in meters, m).
     *
     * This function creates and returns an Equation object representing this relationship.
     *
     * @param frequency The frequency of the wave in hertz (Hz). Default is Double.NaN.
     * @param wavelength The wavelength of the wave in meters (m). Default is Double.NaN.
     * @return An Equation object representing the wave equation `c = f * w`.
     *
     * Usage:
     * 1. To create an equation with known frequency and wavelength:
     *    `val equation = waveEquation(frequency = 500.0, wavelength = 0.002)`
     *
     * 2. To create an equation with either frequency or wavelength missing (NaN):
     *    `val equation = waveEquation(frequency = 500.0)` or
     *    `val equation = waveEquation(wavelength = 0.002)`
     *
     * Note:
     * - The function defaults to Double.NaN for both frequency and wavelength parameters.
     *   This allows creating an Equation object even when one or both of these values are unknown.
     * - The function uses predefined symbolic variables (`symFrequency` and `symWavelength`)
     *   for the frequency (f) and wavelength (w) in the equation. Ensure these symbols are
     *   correctly defined and imported in your context.
     * - `Variable("c")` is a symbolic representation of the speed of the wave. It's assumed
     *   to be a part of the provided Equation class or framework.
     */
    fun waveEquation(
        frequency: Double = Double.NaN,
        wavelength: Double = Double.NaN,
    ): ScalarRelation.Equation {
        val f = symFrequency
        val w = symWavelength
        return Variable("c").standsFor(f * w)
    }

    // Snell's Law
    fun snellsLaw(
        indexRefraction: Double = Double.NaN,
        angleIncidence: Double = Double.NaN,
        angleRefraction: Double = Double.NaN,
    ): ScalarRelation.Equation {
        val ir = symIndexRefraction.setTo(indexRefraction)
        val ai = symAngleIncidence.setTo(angleIncidence)
        val n = Variable("n'")
        val ar = symAngleRefraction.setTo(angleRefraction)
        return (ir * Sin(ai)) isEqualTo (n * Sin(ar))
    }

    // Lens formula (Thin lens formula)
    fun lensFormula(
        focalLength: Double = Double.NaN,
        objectDistance: Double = Double.NaN,
        imageDistance: Double = Double.NaN,
    ): ScalarRelation.Equation {
        val fl = symFocalLength.setTo(focalLength)
        val od = symObjectDistance.setTo(objectDistance)
        val id = symImageDistance.setTo(imageDistance)
        return (1.num() / fl) isEqualTo 1.num() / od + 1.num() / id
    }

    // Power of a lens
    fun lensPowerFormula(
        focalLength: Double = Double.NaN,
    ): ScalarRelation.Equation {
        val fl = symFocalLength.setTo(focalLength)
        return symLensPower.standsFor(1.num() / fl)
    }
}