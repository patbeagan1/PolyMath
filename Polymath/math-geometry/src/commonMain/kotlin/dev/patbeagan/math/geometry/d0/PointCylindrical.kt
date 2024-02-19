package dev.patbeagan.math.geometry.d0

import dev.patbeagan.math.base.GenericSymbols
import main.dsl.expressions.ScalarAlgebra
import main.dsl.expressions.ScalarExpression
import main.dsl.mathnum.Scalar
import main.dsl.mathnum.Variable
import main.dsl.mathnum.num

data class PointCylindrical(
    val radius: Variable,
    val angle: Variable,
    val depth: Variable
) : Point {
    override fun distanceTo(point: Point): ScalarExpression =
        toPointCartesian().distanceTo(point.toPointCartesian())

    override fun translateBy(point: Point) {
        val cylindrical = point.toPointCylindrical()
        radius.setTo((radius + cylindrical.radius).evaluate())
        angle.setTo((angle + cylindrical.angle).evaluate())
        depth.setTo((depth + cylindrical.depth).evaluate())
    }

    override fun toPointSpherical(): PointSpherical = toPointCartesian().toPointSpherical()
    override fun toPointCylindrical(): PointCylindrical = this
    override fun toPointCartesian(): PointCartesian {
        return PointCartesian.of(
            (radius * ScalarAlgebra.Cos(angle)).evaluate(),
            (radius * ScalarAlgebra.Sin(angle)).evaluate(),
            depth.evaluate()
        )
    }

    companion object : GenericSymbols {
        const val symZPrime = "\\z'"

        fun of(radius: Double = 0.0, angle: Double = 0.0, depth: Double = 0.0) =
            of(radius.num(), angle.num(), depth.num())

        fun of(
            radius: ScalarExpression = Scalar.RealNum(0.0),
            angle: ScalarExpression = Scalar.RealNum(0.0),
            depth: ScalarExpression = Scalar.RealNum(0.0)
        ) = PointCylindrical(
            Variable(symRho, radius.evaluate().num()),
            Variable(symPhi, angle.evaluate().num()),
            Variable(symZPrime, depth.evaluate().num()),
        )
    }
}