package dev.patbeagan.physics.classical

import main.dsl.expressions.ScalarExpression
import main.dsl.expressions.ScalarRelation
import main.dsl.mathnum.Variable
import main.dsl.mathnum.mathNum
import main.dsl.mathnum.num
import main.formulas.PhysicsConstants

object PhysicsElectricity : PhysicsConstants {
    val symAngularFrequency get() = Variable("\\omega", name = "AngularFrequency")
    val symCapacitance get() = Variable("C", name = "Capacitance")
    val symCharge get() = Variable("Q", name = "Charge")
    val symCurrent get() = Variable("I", name = "Current")
    val symElectricField get() = Variable("E", name = "ElectricField")
    val symEmf get() = Variable("\\mathcal{E}", name = "Emf")
    val symFrequency get() = Variable("f", name = "Frequency")
    val symInductance get() = Variable("L", name = "Inductance")
    val symMagneticField get() = Variable("B", name = "MagneticField")
    val symMagneticFlux get() = Variable("\\Phi", name = "MagneticFlux")
    val symPower get() = Variable("P", name = "Power")
    val symResistance get() = Variable("R", name = "Resistance")
    val symTime get() = Variable("t", name = "Time")
    val symVoltage get() = Variable("V", name = "Voltage")

    val lorentzForce: String
        get() = "F = q($symElectricField + v $symMagneticField)"
    val faradaysLaw: String
        get() = "\\text{EMF} = -\\frac{d\\Phi_B}{dt}"

    // Ohm's Law
    val ohmsLaw: String
        get() = "$symVoltage = $symCurrent \\times $symResistance"

    // Power in terms of voltage and current
    val powerLaw: String
        get() = "$symPower = $symVoltage \\times $symCurrent"

    // Power in terms of resistance and current
    fun powerResistiveLoad(
        current: Double = Double.NaN,
        resistance: Double = Double.NaN
    ): ScalarExpression {
        val c = symCurrent.apply { value = mathNum(current) }
        val r = symResistance.apply { value = mathNum(resistance) }
        return (c pow 2.0.num()) * r
    }

    // Relationship between charge and capacitance
    fun capacitiveCharge(
        capacitance: Double = Double.NaN,
        voltage: Double = Double.NaN
    ): ScalarRelation.Equation {
        val c = symCapacitance.setTo(capacitance)
        val v = symVoltage.setTo(voltage)
        return symCharge.standsFor(c * v)
    }

    // Voltage across a capacitor in terms of charge
    fun voltageAcrossCapacitor(
        capacitance: Double = Double.NaN,
        charge: Double = Double.NaN
    ): ScalarRelation.Equation {
        val cap = symCapacitance.setTo(capacitance)
        val ch = symCharge.setTo(charge)
        return symVoltage.standsFor(ch / cap)
    }

    // Induced emf in an inductor
    val inducedEmf: String
        get() = "$symEmf = -L \\frac{d$symCurrent}{d$symTime}"

    // Impedance in terms of resistance, inductance, and capacitance (For AC circuits)
    val impedance: String
        get() = "Z = \\sqrt{$symResistance^2 + (\\omega $symInductance - \\frac{1}{\\omega $symCapacitance})^2}"

    // Resonant frequency for LC circuits
    val resonantFrequency: String
        get() = "$symAngularFrequency = \\frac{1}{\\sqrt{$symInductance \\times $symCapacitance}}"
}