package main.dictionary

interface DictionaryTypeTheory {
    interface TypeTheorySymbols {
        // Basic types
        val typeUnit get() = "1" // Unit type
        val typeZero get() = "0" // Empty type
        val typeBool get() = "\\mathbf{Bool}" // Boolean type
        val typeNat get() = "\\mathbf{Nat}" // Natural numbers type

        // Function type
        val functionType get() = "\\rightarrow"

        // Product and Sum types
        val productType get() = "\\times"
        val sumType get() = "+"

        // Type variables
        val typeVariableA get() = "A"
        val typeVariableB get() = "B"

        // Lambda abstraction and application
        val lambdaAbstraction get() = "\\lambda"
        val lambdaApplication get() = " "

        // Universal and existential types
        val universalType get() = "\\forall"
        val existentialType get() = "\\exists"
    }

    interface TypeTheoryFormulas : TypeTheorySymbols {
        // Function type
        val functionTypeAB: String
            get() = "$typeVariableA $functionType $typeVariableB"

        // Product type
        val productTypeAB: String
            get() = "$typeVariableA $productType $typeVariableB"

        // Sum type
        val sumTypeAB: String
            get() = "$typeVariableA $sumType $typeVariableB"

        // Lambda abstraction
        val lambdaAbstractionX: String
            get() = "$lambdaAbstraction x . x"

        // Universal type
        val universalTypeA: String
            get() = "$universalType $typeVariableA . $typeVariableA $functionType $typeBool"

        // Existential type
        val existentialTypeA: String
            get() = "$existentialType $typeVariableA . $typeVariableA $functionType $typeBool"
    }


}