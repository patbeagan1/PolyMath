package dev.patbeagan.physics.classical

interface DictionaryPhysicsFluids {
    interface FluidDynamicsSymbols {
        val symDensity get() = "\\rho"
        val symPressure get() = "P"
        val symVolumeFlowRate get() = "Q"
        val symArea get() = "A"
        val symVelocity get() = "v"
        val symVelocityField get() = "u"
        val symForce get() = "F"
        val symDynamicViscosity get() = "\\mu"
        val symKinematicViscosity get() = "\\nu"
        val symPartialDifferential get() = "\\partial"
        val symGradient get() = "\\nabla"
        val symLaplacian get() = "\\Delta"
    }

//    interface FluidDynamicsEquations : FluidDynamicsSymbols {
//        val continuityEquation: String
//            get() = "$symDensity A_1 v_1 = $symDensity A_2 v_2"
//        val bernoulliEquation: String
//            get() = "P_1 + \\frac{1}{2} \\rho v_1^2 + \\rho gh_1 = P_2 + \\frac{1}{2} \\rho v_2^2 + \\rho gh_2"
//    }
//
//    interface NavierStokesEquations : FluidDynamicsSymbols {
//        // Incompressible Navier-Stokes momentum equation
//        val incompressibleNS: String
//            get() = "$symDensity (\\frac{\\partial $symVelocityField}{\\partial t} + ($symVelocityField \\cdot $symGradient) $symVelocityField) = -$symGradient $symPressure + $symDynamicViscosity $symLaplacian $symVelocityField + $symForce"
//
//        // Compressible Navier-Stokes momentum equation
//        val compressibleNS: String
//            get() = "$symDensity \\frac{\\partial $symVelocityField}{\\partial t} + $symDensity ($symVelocityField \\cdot $symGradient) $symVelocityField = -$symGradient $symPressure + $symDynamicViscosity $symLaplacian $symVelocityField + $symForce"
//    }
//
//    interface ContinuityEquation : FluidDynamicsSymbols{
//        // Incompressible continuity equation
//        val incompressibleContinuity: String
//            get() = "$symGradient \\cdot $symVelocityField = 0"
//
//        // Compressible continuity equation
//        val compressibleContinuity: String
//            get() = "\\frac{\\partial $symDensity}{\\partial t} + $symGradient \\cdot ($symDensity $symVelocityField) = 0"
//    }
//
//    interface EulerEquations : FluidDynamicsSymbols {
//        // Euler equation (inviscid flow)
//        val euler: String
//            get() = "$symDensity \\frac{\\partial $symVelocityField}{\\partial t} + $symDensity ($symVelocityField \\cdot $symGradient) $symVelocityField = -$symGradient $symPressure + $symForce"
//    }
//
//    interface BernoulliEquation : FluidDynamicsSymbols{
//        // Bernoulli's equation for steady flow along a streamline
//        val bernoulli: String
//            get() = "p + \\frac{1}{2} $symDensity $symVelocityField^2 + $symDensity g h = \\text{constant}"
//    }
//
//    interface ViscosityEquations : FluidDynamicsSymbols {
//        // Relationship between dynamic and kinematic viscosity
//        val viscosityRelation: String
//            get() = "$symKinematicViscosity = \\frac{$symDynamicViscosity}{$symDensity}"
//    }
//
//    interface NavierStokesEquations : FluidDynamicsSymbols {
//        // Incompressible Navier-Stokes momentum equation
//        val incompressibleNS: String
//            get() = "$symDensity (\\frac{\\partial ${symVelocityField}}{\\partial t} + (${symVelocityField} \\cdot ${symGradient}) ${symVelocityField}) = -${symGradient} $symPressure + $symDynamicViscosity $symLaplacian $symVelocityField + $symForce"
//
//        // Compressible Navier-Stokes momentum equation
//        val compressibleNS: String
//            get() = "$symDensity \\frac{\\partial ${symVelocityField}}{\\partial t} + $symDensity (${symVelocityField} \\cdot ${symGradient}) $symVelocityField = -${symGradient} $symPressure + $symDynamicViscosity $symLaplacian $symVelocityField + $symForce"
//    }
//
//    interface ContinuityEquation : FluidDynamicsSymbols{
//        // Incompressible continuity equation
//        val incompressibleContinuity: String
//            get() = "$symGradient \\cdot $symVelocityField = 0"
//
//        // Compressible continuity equation
//        val compressibleContinuity: String
//            get() = "\\frac{\\partial ${symDensity}}{\\partial t} + $symGradient \\cdot (${symDensity} ${symVelocityField}) = 0"
//    }
//
//    interface EulerEquations : FluidDynamicsSymbols {
//        // Euler equation (inviscid flow)
//        val euler: String
//            get() = "$symDensity \\frac{\\partial ${symVelocityField}}{\\partial t} + $symDensity (${symVelocityField} \\cdot ${symGradient}) $symVelocityField = -${symGradient} $symPressure + $symForce"
//    }
//
//    interface BernoulliEquation : FluidDynamicsSymbols{
//        // Bernoulli's equation for steady flow along a streamline
//        val bernoulli: String
//            get() = "$symPressure + \\frac{1}{2} $symDensity ${symVelocityField}^2 = \\text{constant}"
//    }
//
//    interface ViscosityEquations : FluidDynamicsSymbols {
//        // Relationship between dynamic and kinematic viscosity
//        val viscosityRelation: String
//            get() = "$symKinematicViscosity = \\frac{${symDynamicViscosity}}{${symDensity}}"
//    }

}