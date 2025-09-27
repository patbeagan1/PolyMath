package main.dictionary

interface DictionaryLogic {

    // 2. Logical Operators
    interface LogicalOperators {
        val symAnd get() = "\\land"
        val symOr get() = "\\lor"
        val symNot get() = "\\lnot"
        val symForAll get() = "\\forall"
        val symExists get() = "\\exists"
        val symImplies get() = "\\Rightarrow"
        val symEquivalent get() = "\\Leftrightarrow"
    }

    interface DeonticLogicSymbols : LogicalOperators {

        // Basic deontic modalities
        val obligation get() = "O"
        val permission get() = "P"
        val prohibition get() = "\\neg P" // prohibition is often the negation of permission

        // Standard symbols
        val implies get() = "\\rightarrow"
        val and get() = "\\land"
        val or get() = "\\lor"
        val not get() = "\\neg"
    }

    interface DeonticLogicFormulas : DeonticLogicSymbols {

        // Basic properties of deontic modalities
        val obligatoryImpliesPermissible: String
            get() = "$obligation A $implies $permission A"

        val contradictoryObligations: String
            get() = "($obligation A $and $obligation $not A) $implies \\text{Contradiction}"

        val prohibitionIsNegationOfPermission: String
            get() = "$prohibition A $symEquivalent $not $permission A"

        // Other common formulas might include conditions where if one action is obligatory, another becomes prohibited, etc.
        // For example: "If it's obligatory to tell the truth, then it's prohibited to lie."
        val obligatoryTruthProhibitsLying: String
            get() = "$obligation \\text{TellingTruth} $implies $prohibition \\text{Lying}"

        // Another example: "If it's permissible to enter, then it's not obligatory to stay out."
        val permissibleEntryNotObligatoryStayOut: String
            get() = "$permission \\text{Entering} $implies $not $obligation \\text{StayingOut}"

    }


    interface LogicSymbols {

        // Propositional logic symbols
        val conjunction get() = "\\land"
        val disjunction get() = "\\lor"
        val implication get() = "\\rightarrow"
        val biconditional get() = "\\leftrightarrow"
        val negation get() = "\\neg"
        val trueLiteral get() = "\\top"
        val falseLiteral get() = "\\bot"

        // Quantifiers for predicate logic
        val universalQuantifier get() = "\\forall"
        val existentialQuantifier get() = "\\exists"

        // Placeholder for variables, predicates, and functions
        val variable get() = "x"
        val predicate get() = "P(x)"
        val function get() = "f(x)"
    }

    interface LogicFormulas : LogicSymbols {

        // Basic laws of propositional logic

        val commutativity: String
            get() = "P $conjunction Q = Q $conjunction P and P $disjunction Q = Q $disjunction P"

        val associativity: String
            get() = "(P $conjunction Q) $conjunction R = P $conjunction (Q $conjunction R) and (P $disjunction Q) $disjunction R = P $disjunction (Q $disjunction R)"

        val distributivity: String
            get() = "P $conjunction (Q $disjunction R) = (P $conjunction Q) $disjunction (P $conjunction R) and P $disjunction (Q $conjunction R) = (P $disjunction Q) $conjunction (P $disjunction R)"

        val identity: String
            get() = "P $conjunction $trueLiteral = P and P $disjunction $falseLiteral = P"

        val negationLaws: String
            get() = "P $conjunction $negation P = $falseLiteral and P $disjunction $negation P = $trueLiteral"

        val doubleNegation: String
            get() = "$negation$negation P = P"

        // De Morgan's laws
        val deMorgansLaws: String
            get() = "$negation (P $conjunction Q) = $negation P $disjunction $negation Q and $negation (P $disjunction Q) = $negation P $conjunction $negation Q"

        // Basic formulas for predicate logic
        val universalInstantiation: String
            get() = "If $universalQuantifier $variable P(x), then P(c) for any element c"

        val existentialGeneralization: String
            get() = "If P(c) for some element c, then $existentialQuantifier $variable P(x)"

    }

    interface LambdaCalculusSymbols {

        // Lambda calculus symbols
        val lambda get() = "\\lambda"
        val dot get() = "."
        val application get() = " "
        val betaReduction get() = "\\rightarrow_{\\beta}"
        val alphaEquivalence get() = "\\equiv_{\\alpha}"

        // Turing machine symbols
        val tape get() = "\\tau"
        val leftShift get() = "L"
        val rightShift get() = "R"
        val haltState get() = "H"

        // Formal language & automata symbols
        val kleeneStar get() = "^*"
        val emptyString get() = "\\varepsilon"
        val pushDownAutomata get() = "PDA"
        val finiteAutomaton get() = "FA"
        val nonDeterministic get() = "N"

        // Boolean logic symbols
        val conjunction get() = "\\land"
        val disjunction get() = "\\lor"
        val implication get() = "\\Rightarrow"
        val equivalence get() = "\\Leftrightarrow"
        val negation get() = "\\lnot"

        // Big-O notation
        val bigO get() = "O"
    }

    interface ComputerScienceFormulas : LambdaCalculusSymbols {

        // Lambda abstraction in lambda calculus
        val lambdaAbstraction: String
            get() = "$lambda x $dot M"

        // Lambda application in lambda calculus
        val lambdaApplication: String
            get() = "M N"

        // Beta reduction in lambda calculus
        val betaReductionFormula: String
            get() = "(\\lambda x $dot M) N $betaReduction M[N/x]"

        // Turing machine shift operations
        val turingShiftLeft: String
            get() = "$tape, q $rightShift q', \\sigma"

        val turingShiftRight: String
            get() = "$tape, q $leftShift q', \\sigma"

        // Closure properties of regular languages
        val closureConcatenation: String
            get() = "L_1 L_2"

        val closureUnion: String
            get() = "L_1 \\cup L_2"

        val closureKleeneStar: String
            get() = "L$kleeneStar"

        // Boolean logic
        val modusPonens: String
            get() = "p, p $implication q $equivalence q"
    }
}
