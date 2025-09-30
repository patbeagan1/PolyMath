import com.measures.storage.*

fun main() {
    val nibble1 = Nibble(2.0)
    val nibble2 = Nibble(3.0)
    val word1 = Word(1.0)
    
    println("Nibble1: ${nibble1.value}")
    println("Nibble2: ${nibble2.value}")
    println("Word1: ${word1.value}")
    
    val sum = nibble1 + nibble2
    val total = nibble1 + word1
    
    println("Sum: ${sum.value}")
    println("Total: ${total.value}")
    
    // Check what 1 word equals in nibbles
    val wordToNibble = word1.toNibble()
    println("1 word = ${wordToNibble.value} nibbles")
}
