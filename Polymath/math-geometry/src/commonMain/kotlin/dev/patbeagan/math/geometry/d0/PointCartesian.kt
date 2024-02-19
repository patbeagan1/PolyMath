package dev.patbeagan.math.geometry.d0

import dev.patbeagan.math.base.GenericSymbols
import main.dsl.expressions.ScalarAlgebra
import main.dsl.expressions.ScalarExpression
import main.dsl.mathnum.MathNum
import main.dsl.mathnum.Scalar
import main.dsl.mathnum.Variable
import main.dsl.mathnum.num
import main.formulas.MathConstants

data class PointCartesian(
    val x: Variable = Variable(symX, 0.0),
    val y: Variable = Variable(symY, 0.0),
    val z: Variable = Variable(symZ, 0.0)
) : Point, MathConstants {

    private val distance = Distance()

    fun distanceTo(other: PointCartesian): ScalarExpression = distance.setupWith(other).formula

    /**
     * Computes the manhattan distance between the 2 points.
     *
     * Manhattan distance is the distance that you would have to travel
     * if you could only move on one axis at a time.
     *
     * Named for the distance between 2 locations on the grid streets of manhattan.
     */
    fun distanceManhattanTo(other: PointCartesian): ScalarExpression {
        val x1 = Variable("x_1", x)
        val x2 = Variable("x_2", other.x)
        val y1 = Variable("y_1", y)
        val y2 = Variable("y_2", other.y)
        val z1 = Variable("z_1", z)
        val z2 = Variable("z_2", other.z)
        return ScalarAlgebra.Abs((x1 - x2)) + ScalarAlgebra.Abs((y1 - y2)) + ScalarAlgebra.Abs((z1 - z2))
    }

    override fun distanceTo(point: Point) = distanceTo(point.toPointCartesian())

    override fun translateBy(point: Point) {
        val cartesian = point.toPointCartesian()
        x.setTo((x + cartesian.x).evaluate())
        y.setTo((y + cartesian.y).evaluate())
        z.setTo((z + cartesian.z).evaluate())
    }

    override fun toPointCartesian(): PointCartesian = this

    override fun toPointCylindrical() = PointCylindrical.of(
        ScalarAlgebra.Sqrt(x.squared() + y.squared()).evaluate().num(),
        invertTan(x, y).evaluate().num(),
        z
    )

    override fun toPointSpherical() = PointSpherical.of(
        ScalarAlgebra.Sqrt(x.squared() + y.squared() + z.squared()).evaluate().num(),
        invertTan(x, y).evaluate().num(),
        ScalarAlgebra.ArcCos(z / (ScalarAlgebra.Sqrt(x.squared() + y.squared() + z.squared()))).evaluate().num()
    )

    private fun invertTan(xi: MathNum, yi: MathNum) = when {
        xi.evaluate() > 0 -> ScalarAlgebra.ArcTan(yi / xi)
        xi.evaluate() < 0 -> constPi + ScalarAlgebra.ArcTan(yi / xi)
        xi.evaluate() == 0.0 -> when {
            yi.evaluate() < 0 -> 3.num() * (constPi / 2.num())
            yi.evaluate() > 0 -> constPi / 2.num()
            else -> throw ArithmeticException()
        }

        else -> throw ArithmeticException()
    }

    private inner class Distance {
        private val x1 = Variable("x_1", x)
        private val x2 = Variable("x_2", 0.0)
        private val y1 = Variable("y_1", y)
        private val y2 = Variable("y_2", 0.0)
        private val z1 = Variable("z_1", z)
        private val z2 = Variable("z_2", 0.0)

        val formula = ScalarAlgebra.Sqrt(
            (z2 - z1).squared() +
                    (y2 - y1).squared() +
                    (x2 - x1).squared()
        )

        fun setupWith(other: PointCartesian) = apply {
            x2.setTo(other.x.evaluate())
            y2.setTo(other.y.evaluate())
            z2.setTo(other.z.evaluate())
        }
    }

    companion object : GenericSymbols {

        fun of(x: Double = 0.0, y: Double = 0.0, z: Double = 0.0) =
            of(x.num(), y.num(), z.num())

        fun of(x: Int = 0, y: Int = 0, z: Int = 0) =
            of(x.num(), y.num(), z.num())

        fun of(
            x: ScalarExpression = Scalar.RealNum(0.0),
            y: ScalarExpression = Scalar.RealNum(0.0),
            z: ScalarExpression = Scalar.RealNum(0.0)
        ) = PointCartesian(
            Variable(symX, x.evaluate()),
            Variable(symY, y.evaluate()),
            Variable(symZ, z.evaluate()),
        )
    }
}