package dev.patbeagan.math.geometry.d0

import kotlin.jvm.JvmInline
import kotlin.math.*

infix fun Int.coord(other: Int) = GeometryShapeD0.CompressedPoint.from(this, other)


interface GeometryShapeD0 {

    /**
     * Holds an X,Y coordinate as a single Long.
     * The X portion is the leading half, and the Y portion is the trailing half.
     */
    @JvmInline
    value class CompressedPoint(private val base: Long) : Comparable<CompressedPoint> {


        constructor(x: Int, y: Int) : this(l(x, y))

        override fun compareTo(other: CompressedPoint): Int = when {
            other.y < y -> -other.distanceFrom(this)
            other.x < x -> -other.distanceFrom(this)
            else -> other.distanceFrom(this)
        }.roundToInt()

        /**
         * The first coordinate in the CompressedPoint
         */
        val x: Int get() = (base shr 32).toInt()

        /**
         * The second coordinate in the CompressedPoint
         */
        val y: Int get() = base.toInt() // (base and (2.0.pow(32) - 1).toLong()).toInt()


        /**
         * Computes the true distance between the two listed points.
         */
        fun distanceFrom(other: CompressedPoint): Double = distance(
            x.toDouble(),
            y.toDouble(),
            other.x.toDouble(),
            other.y.toDouble()
        )


        /**
         * A 3D distance formula
         */
        fun distance(
            x1: Int,
            y1: Int,
            z1: Int,
            x2: Int,
            y2: Int,
            z2: Int,
        ): Double {
            val d1 = (x2 - x1).toDouble().pow(2)
            val d2 = (y2 - y1).toDouble().pow(2)
            val d3 = (z2 - z1).toDouble().pow(2)
            return sqrt(d1 * d2 * d3)
        }

        fun distance(xy1: Pair<Double, Double>, xy2: Pair<Double, Double>) =
            distance(xy1.first, xy1.second, xy2.first, xy2.second)

        fun distance(x1: Double, y1: Double, x2: Double, y2: Double): Double =
            sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1))

        fun toCartesian(range: Double, angle: Double): Pair<Double, Double> =
            range * cos(angle) to range * sin(angle)

        fun convertToPointAndTranslateBy(x: Int, y: Int): (Pair<Double, Double>) -> CompressedPoint = {
            from(x + it.first.roundToInt(), y + it.second.roundToInt())
        }

        /**
         * Computes the manhattan distance between the 2 points.
         *
         * Manhattan distance is the number of steps that you'd have to take
         * to move between the points in a grid system.
         * Only purely vertical or horiztonal moves are allowed.
         */
        fun distanceManhattanFrom(other: CompressedPoint): Int =
            (x - other.x).absoluteValue + (y - other.y).absoluteValue

        /**
         * Checks to see if the point is inside the given triangle.
         */
        fun isInTriangle(
            v1: CompressedPoint,
            v2: CompressedPoint,
            v3: CompressedPoint
        ): Boolean {
            fun sign(p1: CompressedPoint, p2: CompressedPoint, p3: CompressedPoint): Float =
                (p1.x.toFloat() - p3.x.toFloat()) *
                        (p2.y.toFloat() - p3.y.toFloat()) -
                        (p2.x.toFloat() - p3.x.toFloat()) *
                        (p1.y.toFloat() - p3.y.toFloat())

            val hasNeg: Boolean
            val hasPos: Boolean
            val d1: Float = sign(this, v1, v2)
            val d2: Float = sign(this, v2, v3)
            val d3: Float = sign(this, v3, v1)
            hasNeg = d1 < 0 || d2 < 0 || d3 < 0
            hasPos = d1 > 0 || d2 > 0 || d3 > 0
            return !(hasNeg && hasPos)
        }

        override fun toString(): String =
            "CompressedPoint of ${this.x} to ${this.y}\n" //+
//            "                   ${Integer.toBinaryString(x)}\n" +
//            "                   ${Integer.toBinaryString(y)}"

        companion object {
            /**
             * Generates a [CompressedPoint] from a set of X,Y coordinates.
             *
             * @return the [CompressedPoint] that is created by concatenating these coordinates
             */
            fun from(x: Int, y: Int): CompressedPoint = CompressedPoint(
                // todo replace with ULong
                // toLong modifies the leading bit to keep the sign, so we have to do it manually
                l(x, y)
            )

            fun l(x: Int, y: Int) = (x.toLong() shl 32) or (y.toLong() and (2.0.pow(32) - 1).toLong())
        }
    }
}