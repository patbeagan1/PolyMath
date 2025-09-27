package main.dictionary

interface DictionaryArithmetic {
    interface ArithmeticSymbols {
        // Basic symbols
        val addition get() = "+"
        val subtraction get() = "-"
        val multiplication get() = "\\times"
        val division get() = "\\div"
        val symEquals get() = "="
        val power get() = "^"

        // Variables often used
        val operandA get() = "a"
        val operandB get() = "b"
        val result get() = "r"
    }

    interface ArithmeticFormulas : ArithmeticSymbols {
        // Addition
        val additionFormula: String
            get() = "$operandA $addition $operandB $symEquals $result"

        // Subtraction
        val subtractionFormula: String
            get() = "$operandA $subtraction $operandB $symEquals $result"

        // Multiplication
        val multiplicationFormula: String
            get() = "$operandA $multiplication $operandB $symEquals $result"

        // Division
        val divisionFormula: String
            get() = "$operandA $division $operandB $symEquals $result"

        // Exponentiation
        val powerFormula: String
            get() = "$operandA$power$operandB $symEquals $result"

        // Distributive property
        val distributiveProperty: String
            get() = "$operandA $multiplication ($operandB $addition r) $symEquals ($operandA $multiplication $operandB) $addition ($operandA $multiplication r)"

        // Associative property of addition
        val associativePropertyAddition: String
            get() = "($operandA $addition $operandB) $addition r $symEquals $operandA $addition ($operandB $addition r)"

        // Associative property of multiplication
        val associativePropertyMultiplication: String
            get() = "($operandA $multiplication $operandB) $multiplication r $symEquals $operandA $multiplication ($operandB $multiplication r)"

        // Commutative property of addition
        val commutativePropertyAddition: String
            get() = "$operandA $addition $operandB $symEquals $operandB $addition $operandA"

        // Commutative property of multiplication
        val commutativePropertyMultiplication: String
            get() = "$operandA $multiplication $operandB $symEquals $operandB $multiplication $operandA"
    }


}