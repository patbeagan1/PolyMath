package dev.patbeagan.math.geometry.d3

import dev.patbeagan.math.geometry.Geometry
import dev.patbeagan.math.geometry.Geometry.constPi
import main.dsl.expressions.ScalarAlgebra
import main.dsl.expressions.ScalarRelation
import main.dsl.mathnum.Variable
import main.dsl.mathnum.num

/**
 * digraph G {
 *     node [shape=box];
 *     Shape3D -> Polyhedron;
 *     Shape3D -> NonPolyhedron;
 *
 *     Polyhedron -> Prism;
 *     Polyhedron -> Pyramid;
 *     Polyhedron -> Cube;
 *
 *     Prism -> RectangularPrism;
 *
 *     NonPolyhedron -> Sphere;
 *     NonPolyhedron -> Cylinder;
 *     NonPolyhedron -> Cone;
 * }
 */
sealed class GeometryShapeD3 {
    sealed class Polyhedron : GeometryShapeD3() {
        abstract val vertices: Int
        abstract val faces: Int
        abstract val edges: Int
        fun eulerFormulaForPolyhedra(): ScalarRelation.Equation =
            vertices.num() - edges.num() + faces.num() isEqualTo 2.num()
    }

    sealed class NonPolyhedron : GeometryShapeD3()
    sealed class Prism : Polyhedron()
    sealed class Pyramid : Polyhedron() {
        fun pyramidSurfaceArea(
            baseSide: Double = Double.NaN,
            slantHeight: Double = Double.NaN
        ): ScalarRelation.Equation {
            val b = Variable("b_s").setTo(baseSide)
            val s = Geometry.symSlantHeight.setTo(slantHeight)
            val baseArea = b * b
            return Geometry.symSurfaceArea.standsFor(baseArea + (2.num() * b * s))
        }
    }

    sealed class Cube : Polyhedron() {
        override val edges: Int = 12
        override val faces: Int = 6
        override val vertices: Int = 8

        fun cubeVolume(
            side: Double = Double.NaN
        ): ScalarRelation.Equation {
            val s = Geometry.symSide.setTo(side)
            return Geometry.symVolume.standsFor(s pow 3.num())
        }

        fun cubeSurfaceArea(
            side: Double = Double.NaN
        ): ScalarRelation.Equation {
            val s = Geometry.symSide.setTo(side)
            return Geometry.symSurfaceArea.standsFor(6.num() * s.squared())
        }

        fun cubeDiagonal(
            side: Double = Double.NaN
        ): ScalarRelation.Equation {
            val s = Geometry.symSide.setTo(side)
            return Variable("d").standsFor(s * ScalarAlgebra.Sqrt(3.0.num()))
        }
    }

    sealed class RectangularPrism : Prism() {
        fun rectangularPrismVolume(
            length: Double = Double.NaN,
            width: Double = Double.NaN,
            height: Double = Double.NaN
        ): ScalarRelation.Equation {
            val l = Geometry.symLength.setTo(length)
            val w = Geometry.symWidth.setTo(width)
            val h = Geometry.symHeight.setTo(height)
            return Geometry.symVolume.standsFor(l * w * h)
        }

        fun rectangularPrismSurfaceArea(
            length: Double = Double.NaN,
            width: Double = Double.NaN,
            height: Double = Double.NaN
        ): ScalarRelation.Equation {
            val l = Geometry.symLength.setTo(length)
            val w = Geometry.symWidth.setTo(width)
            val h = Geometry.symHeight.setTo(height)
            return Geometry.symSurfaceArea.standsFor(2.num() * (l * w + w * h + h * l))
        }
    }

    sealed class Sphere : NonPolyhedron() {
        fun sphereVolume(
            radius: Double = Double.NaN
        ): ScalarRelation.Equation {
            val pi = constPi
            val r = Geometry.symRadius.setTo(radius)
            return Geometry.symVolume.standsFor((4.0.num() / 3.0.num()) * pi * (r pow 3.num()))
        }

        fun sphereSurfaceArea(
            radius: Double = Double.NaN
        ): ScalarRelation.Equation {
            val pi = constPi
            val r = Geometry.symRadius.setTo(radius)
            return Geometry.symSurfaceArea.standsFor(4.num() * pi * r.squared())
        }
    }

    sealed class Cylinder : NonPolyhedron() {
        fun cylinderVolume(
            radius: Double = Double.NaN,
            height: Double = Double.NaN
        ): ScalarRelation.Equation {
            val pi = constPi
            val r = Geometry.symRadius.setTo(radius)
            val h = Geometry.symHeight.setTo(height)
            return Geometry.symVolume.standsFor(pi * r.squared() * h)
        }

        fun cylinderSurfaceArea(
            radius: Double = Double.NaN,
            height: Double = Double.NaN
        ): ScalarRelation.Equation {
            val r = Geometry.symRadius.setTo(radius)
            val h = Geometry.symHeight.setTo(height)
            return Geometry.symSurfaceArea.standsFor(2.num() * constPi * r * (r + h))
        }

        fun cylinderLateralSurfaceArea(
            radius: Double = Double.NaN,
            height: Double = Double.NaN
        ): ScalarRelation.Equation {
            val pi = constPi
            val r = Geometry.symRadius.setTo(radius)
            val h = Geometry.symHeight.setTo(height)
            return Geometry.symSurfaceArea.standsFor(2.num() * pi * r * h)
        }
    }

    sealed class Cone : NonPolyhedron() {
        fun coneVolume(
            radius: Double = Double.NaN,
            height: Double = Double.NaN
        ): ScalarRelation.Equation {
            val pi = constPi
            val r = Geometry.symRadius.setTo(radius)
            val h = Geometry.symHeight.setTo(height)
            return Geometry.symVolume.standsFor((1.0.num() / 3.0.num()) * pi * r.squared() * h)
        }

        fun coneSurfaceArea(
            radius: Double = Double.NaN,
            slantHeight: Double = Double.NaN
        ): ScalarRelation.Equation {
            val r = Geometry.symRadius.setTo(radius)
            val s = Geometry.symSlantHeight.setTo(slantHeight)
            return Geometry.symSurfaceArea.standsFor(constPi * r * (r + s))
        }
    }
}