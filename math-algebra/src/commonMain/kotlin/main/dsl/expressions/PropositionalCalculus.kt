package main.dsl.expressions

import dev.patbeagan.math.base.GenericSymbols
import main.dsl.CanDisplay
import main.dsl.mathnum.*

interface PropositionalCalculus {
    enum class Priority(val value: Int) {
        Other(0),
        Or(1),
        And(2),
        Xor(3),
        Not(4),
        Value(5)
    }

    interface BinaryOperation : BooleanExpression {
        val left: BooleanExpression
        val right: BooleanExpression
    }

    interface UnaryOperation : BooleanExpression {
        val operand: BooleanExpression
    }

    class And(
        override val left: BooleanExpression,
        override val right: BooleanExpression
    ) : BinaryOperation {
        override val priority: Priority = Priority.And
        override fun evaluate(): Boolean = left.evaluate() && right.evaluate()
        override fun toLatex(): String = "${left.toLatexPriority(this)} \\land ${right.toLatexPriority(this)}"
    }

    class Not(
        override val operand: BooleanExpression
    ) : UnaryOperation {
        override val priority: Priority = Priority.Not
        override fun evaluate(): Boolean = operand.evaluate().not()
        override fun toLatex(): String = "\\neg ${operand.toLatexPriority(this)}"
    }

    class Or(
        override val left: BooleanExpression,
        override val right: BooleanExpression
    ) : BinaryOperation {
        override val priority: Priority = Priority.Or
        override fun evaluate(): Boolean = left.evaluate() || right.evaluate()
        override fun toLatex(): String = "${left.toLatexPriority(this)} \\lor ${right.toLatexPriority(this)}"
    }

    class Xor(
        override val left: BooleanExpression,
        override val right: BooleanExpression
    ) : BinaryOperation {
        override val priority: Priority = Priority.Xor
        override fun evaluate(): Boolean = left.evaluate().xor(right.evaluate())
        override fun toLatex(): String = "${left.toLatexPriority(this)} \\oplus ${right.toLatexPriority(this)}"
    }

    class Nand(
        override val left: BooleanExpression,
        override val right: BooleanExpression
    ) : BinaryOperation {
        override val priority: Priority = Priority.Not
        override fun evaluate(): Boolean = left.evaluate().and(right.evaluate()).not()
        override fun toLatex(): String = "\neg (${left.toLatexPriority(this)} \\land ${right.toLatexPriority(this)})"
    }

    class Implies(
        override val left: BooleanExpression,
        override val right: BooleanExpression
    ) : BinaryOperation {
        override val priority: Priority = Priority.Other
        override fun evaluate(): Boolean = left.evaluate().and(right.evaluate().not())
        override fun toLatex(): String = "${left.toLatexPriority(this)} \\implies ${right.toLatexPriority(this)}"
    }
   class Equivalence(
        override val left: BooleanExpression,
        override val right: BooleanExpression
    ) : BinaryOperation {
        override val priority: Priority = Priority.Other
        override fun evaluate(): Boolean = left.evaluate()== right.evaluate()
        override fun toLatex(): String = "${left.toLatexPriority(this)} \\implies ${right.toLatexPriority(this)}"
    }

    class Fact(
        val glyph: String,
        val value: Boolean = true,
        val name: String? = null
    ) : BooleanExpression {
        override val priority: Priority = Priority.Value
        override fun evaluate(): Boolean = value
        override fun toLatex(): String = glyph
    }

    sealed interface BooleanExpression : GenericSymbols {
        val priority: Priority
        fun evaluate(): Boolean
        fun toLatex(): String

        fun BooleanExpression.toLatexPriority(
            parent: BooleanExpression,
            position: Position = Position.NotApplicable
        ): String = when {
            this.priority < parent.priority -> {
                // items listed here have inverted priorities
                when (parent.priority) {
                    else -> "(${toLatex()})"
                }
            }

            else -> toLatex()
        }

        enum class Position {
            NotApplicable
        }

        fun not() = Not(this)
        infix fun and(other: BooleanExpression) = And(this, other)
        infix fun or(other: BooleanExpression) = Or(this, other)
        infix fun xor(other: BooleanExpression) = Xor(this, other)
        infix fun nand(other: BooleanExpression) = Nand(this, other)
        infix fun implies(other: BooleanExpression) = Implies(this, other)
        infix fun isEquivalentTo(other: BooleanExpression) = Equivalence(this, other)


        fun getOperands(): Set<BooleanExpression> = getOperandsExcept { false }

        fun getOperandsExcept(shouldSkip: (BooleanExpression) -> Boolean = { false }): Set<BooleanExpression> {
            val operands = mutableSetOf<BooleanExpression>()
            walk(shouldSkip = shouldSkip) { operands.add(it) }
            return operands
        }

        fun getVariables(): Set<Variable> = getOperands().filterIsInstanceTo(mutableSetOf())
        fun getFunctions(): Set<MathFunction> = getOperands().filterIsInstanceTo(mutableSetOf())
        fun getConstants(): Set<Scalar.RealNum> = getOperands().filterIsInstanceTo(mutableSetOf())
        fun getDisplayableValues(): Set<CanDisplay> = getOperands().filterIsInstanceTo(mutableSetOf())


        fun descendants(depth: Int = Int.MAX_VALUE): List<BooleanExpression> {
            val c = mutableListOf<BooleanExpression>()
            walk(depthMax = depth) { c.add(it) }
            return c
        }

        fun walk(
            action: (BooleanExpression) -> Unit
        ) = walk(this, Int.MAX_VALUE, 0, { false }, action)

        fun walk(
            depthMax: Int = Int.MAX_VALUE,
            shouldSkip: (BooleanExpression) -> Boolean = { false },
            action: (BooleanExpression) -> Unit
        ) = walk(this, depthMax, 0, shouldSkip, action)

        private fun walk(
            expression: BooleanExpression,
            depthMax: Int,
            depth: Int,
            shouldSkip: (BooleanExpression) -> Boolean = { false },
            action: (BooleanExpression) -> Unit
        ) {
            expression.also(action)

            when {
                depth == depthMax -> return
                depth > 1 && shouldSkip(expression) -> return
            }

            when (expression) {
                is UnaryOperation -> {
                    walk(expression.operand, depthMax, depth + 1, shouldSkip, action)
                }

                is BinaryOperation -> {
                    walk(expression.left, depthMax, depth + 1, shouldSkip, action)
                    walk(expression.right, depthMax, depth + 1, shouldSkip, action)
                }

                is Fact -> Unit
            }
        }
    }
}