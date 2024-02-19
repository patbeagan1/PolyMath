package main.dictionary

interface DictionaryAutomataTheory {
    interface AutomataTheorySymbols {
        // Basic symbols for finite automata
        val states get() = "Q"
        val alphabet get() = "\\Sigma"
        val transitionFunction get() = "\\delta"
        val startState get() = "q_0"
        val acceptStates get() = "F"

        // Symbols for pushdown automata
        val stackAlphabet get() = "\\Gamma"
        val stackStartSymbol get() = "Z_0"

        // Symbols for Turing machines
        val tapeAlphabet get() = "\\Gamma"
        val blankSymbol get() = "\\_"
        val leftMove get() = "L"
        val rightMove get() = "R"
        val haltState get() = "H"
    }

    interface AutomataTheoryFormulas : AutomataTheorySymbols {
        // DFA (Deterministic Finite Automaton) transition
        val dfaTransition: String
            get() = "$transitionFunction: $states \\times $alphabet \\rightarrow $states"

        // NFA (Nondeterministic Finite Automaton) transition (can have multiple states for an input symbol)
        val nfaTransition: String
            get() = "$transitionFunction: $states \\times $alphabet \\rightarrow 2^$states"

        // Pushdown Automata transition
        val pushdownTransition: String
            get() = "$transitionFunction: $states \\times $alphabet \\times $stackAlphabet \\rightarrow 2^{($states \\times $stackAlphabet^*)}"

        // Turing Machine transition
        val turingMachineTransition: String
            get() = "$transitionFunction: $states \\times $tapeAlphabet \\rightarrow $states \\times $tapeAlphabet \\times \\{$leftMove, $rightMove\\}"

        // Regular expression for concatenation of two symbols A and B
        val regexConcatenation: String
            get() = "AB"

        // Regular expression for union (or choice) of two symbols A and B
        val regexUnion: String
            get() = "A|B"

        // Regular expression for Kleene star (zero or more repetitions) of a symbol A
        val regexKleeneStar: String
            get() = "A^*"
    }


}