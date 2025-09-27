package dev.patbeagan.physics.classical

interface DictionaryPhysicsMechanics {

    interface ClassicalMechanicsSymbols {
        val symForce get() = "F"
        val symMass get() = "m"
        val symAcceleration get() = "a"
        val symKineticEnergy get() = "\\text{KE}"
        val symPotentialEnergy get() = "\\text{PE}"
        val symVelocity get() = "v"
        val symGravity get() = "g"
    }

    interface ClassicalMechanicsEquations : ClassicalMechanicsSymbols {
        val newtonSecondLaw: String
            get() = "$symForce = $symMass $symAcceleration"
        val kineticEnergy: String
            get() = "$symKineticEnergy = \\frac{1}{2} $symMass $symVelocity^2"
        val gravitationalPotentialEnergy: String
            get() = "$symPotentialEnergy = $symMass $symGravity h"
    }

    interface MechanicsSymbols {

        // Basic mechanics symbols
        val force get() = "F"
        val mass get() = "m"
        val acceleration get() = "a"
        val velocity get() = "v"
        val position get() = "x"
        val time get() = "t"
        val gravitationalConstant get() = "G"
        val gravitationalForce get() = "F_g"
        val gravitationalField get() = "g"
        val potentialEnergy get() = "U"
        val kineticEnergy get() = "K"
        val work get() = "W"
        val displacement get() = "\\Delta x"
        val frictionalForce get() = "F_f"
        val coefficientOfFriction get() = "\\mu"
        val normalForce get() = "F_n"
        val momentum get() = "p"
    }

    interface MechanicsFormulas : MechanicsSymbols {

        // Newton's second law
        val newtonsSecondLaw: String
            get() = "$force = $mass \\times $acceleration"

        // Kinematic equation (1st)
        val kinematic1: String
            get() = "$velocity = \\frac{d$position}{d$time}"

        // Kinematic equation (2nd)
        val kinematic2: String
            get() = "$position = $position + $velocity \\times $time + \\frac{1}{2} \\times $acceleration \\times $time^2"

        // Gravitational force (Newton's law of gravitation)
        val gravitationalForceLaw: String
            get() = "$gravitationalForce = \\frac{$gravitationalConstant \\times m_1 \\times m_2}{r^2}"

        // Weight of an object
        val weight: String
            get() = "$gravitationalForce = $mass \\times $gravitationalField"

        // Potential energy due to gravity
        val gravitationalPotentialEnergy: String
            get() = "$potentialEnergy = $mass \\times $gravitationalField \\times h"

        // Kinetic energy
        val kineticEnergyFormula: String
            get() = "$kineticEnergy = \\frac{1}{2} \\times $mass \\times $velocity^2"

        // Work done by a force
        val workDone: String
            get() = "$work = $force \\times $displacement"

        // Frictional force
        val friction: String
            get() = "$frictionalForce = $coefficientOfFriction \\times $normalForce"

        // Momentum
        val momentumFormula: String
            get() = "$momentum = $mass \\times $velocity"
    }
}