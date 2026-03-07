package main.repl

/**
 * JVM entry point for the algebra REPL.
 */
fun main(args: Array<String>) {
    val repl = AlgebraRepl()
    
    println("PolyMath Algebra REPL")
    println("Type expressions or commands (prefix with :). Type :help for help, :quit to exit.")
    println()
    
    while (true) {
        print("> ")
        val input = readLine() ?: break
        
        if (input.trim().lowercase() == ":quit" || input.trim().lowercase() == ":q") {
            println("Goodbye!")
            break
        }
        
        val result = repl.processInput(input)
        println(result)
        println()
    }
}
