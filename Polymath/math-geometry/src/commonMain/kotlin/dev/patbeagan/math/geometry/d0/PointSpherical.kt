package dev.patbeagan.math.geometry.d0

import dev.patbeagan.math.base.GenericSymbols
import main.dsl.expressions.ScalarAlgebra
import main.dsl.expressions.ScalarExpression
import main.dsl.mathnum.Scalar
import main.dsl.mathnum.Variable
import main.dsl.mathnum.num

data class PointSpherical(
    val radius: Variable,
    val angle: Variable,
    val polar: Variable
) : Point {
    override fun distanceTo(point: Point): ScalarExpression =
        toPointCartesian().distanceTo(point.toPointCartesian())

    override fun translateBy(point: Point) {
        val spherical = point.toPointSpherical()
        radius.setTo((radius + spherical.radius).evaluate())
        angle.setTo((angle + spherical.angle).evaluate())
        polar.setTo((polar + spherical.polar).evaluate())
    }

    override fun toPointSpherical(): PointSpherical = this
    override fun toPointCylindrical(): PointCylindrical = toPointCartesian().toPointCylindrical()
    override fun toPointCartesian(): PointCartesian = PointCartesian.of(
        (radius * ScalarAlgebra.Sin(polar) * ScalarAlgebra.Cos(angle)).evaluate(),
        (radius * ScalarAlgebra.Sin(polar) * ScalarAlgebra.Sin(angle)).evaluate(),
        (radius * ScalarAlgebra.Cos(polar)).evaluate()
    )

    companion object : GenericSymbols {
        fun of(radius: Double = 0.0, angle: Double = 0.0, polar: Double = 0.0) =
            of(radius.num(), angle.num(), polar.num())

        fun of(
            x: ScalarExpression = Scalar.RealNum(0.0),
            y: ScalarExpression = Scalar.RealNum(0.0),
            z: ScalarExpression = Scalar.RealNum(0.0)
        ) = PointSpherical(
            Variable(symRho, x.evaluate().num()),
            Variable(symPhi, y.evaluate().num()),
            Variable(PointCylindrical.symZPrime, z.evaluate().num()),
        )
    }
}