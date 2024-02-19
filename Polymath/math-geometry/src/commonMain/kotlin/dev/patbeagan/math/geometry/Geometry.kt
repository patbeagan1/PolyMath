package dev.patbeagan.math.geometry

import main.dsl.mathnum.Variable
import main.formulas.MathConstants

object Geometry : MathConstants {
    val symAngle get() = Variable("\\theta", name = "Angle")
    val symArea get() = Variable("A", name = "Area")
    val symBase get() = Variable("b", name = "Base")
    val symBaseArea get() = Variable("A_b", name = "BaseArea")
    val symCircumference get() = Variable("C", name = "Circumference")
    val symDiameter get() = Variable("d", name = "Diameter")
    val symHeight get() = Variable("h", name = "Height")
    val symLength get() = Variable("l", name = "Length")
    val symPerimeter get() = Variable("P", name = "Perimeter")
    val symRadius get() = Variable("r", name = "Radius")
    val symSide get() = Variable("s", name = "Side")
    val symSlantHeight get() = Variable("l", name = "SlantHeight")
    val symSurfaceArea get() = Variable("A_s", name = "SurfaceArea")
    val symVolume get() = Variable("V", name = "Volume")
    val symWidth get() = Variable("w", name = "Width")
    val symCentralAngle get() = Variable("\\theta_c", name = "CentralAngle")


    val pythagorasTheorem: String
        get() = "a^2 + b^2 = c^2, \\forall \\triangle ABC \\text{ where } c \\text{ is the hypotenuse}"

    val totalInternalAnglesTriangle: String
        get() = "\\sum_{i=1}^{3} \\angle i = 180^\\circ \\text{ in } \\triangle ABC"
}