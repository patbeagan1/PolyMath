package dev.patbeagan.latexbuilder

class TexMathBuilder {
   private val content = StringBuilder()

   override fun toString(): String = content.toString()

   fun equation(equationContent: String) {
       content.append("\\begin{equation}\n$equationContent\n\\end{equation}\n")
   }

   fun align(vararg lines: String) {
       content.append("\\begin{align}\n")
       lines.forEach { line ->
           content.append("$line \\\\\n")
       }
       content.append("\\end{align}\n")
   }

   fun equation(body: TexMathBuilder.() -> Unit) {
       content.append("\\begin{equation}")
       TexMathBuilder().apply(body).let {
           content.append(it.toString())
       }
       content.append("\\end{equation}")
   }

   fun align(vararg equations: TexMathBuilder.() -> Unit) {
       content.append("\\begin{align}")
       equations.forEach { eq ->
           TexMathBuilder().apply(eq).let {
               content.append(it.toString())
           }
           content.append("\\\\")
       }
       content.append("\\end{align}")
   }

   fun inlineMath(equationContent: String) {
       content.append("\$ $equationContent \$\n")
   }

   fun symbol(sym: String) {
       content.append(sym)
   }

   fun fraction(numerator: TexMathBuilder.() -> Unit, denominator: TexMathBuilder.() -> Unit) {
       content.append("\\frac{")
       TexMathBuilder().apply(numerator).let {
           content.append(it.toString())
       }
       content.append("}{")
       TexMathBuilder().apply(denominator).let {
           content.append(it.toString())
       }
       content.append("}")
   }

   fun sqrt(body: TexMathBuilder.() -> Unit) {
       content.append("\\sqrt{")
       TexMathBuilder().apply(body).let {
           content.append(it.toString())
       }
       content.append("}")
   }

   fun integral(from: String, to: String, body: TexMathBuilder.() -> Unit) {
       content.append("\\int_{$from}^{$to} ")
       TexMathBuilder().apply(body).let {
           content.append(it.toString())
       }
       content.append("\\, dx")
   }

   fun summation(from: String, to: String, body: TexMathBuilder.() -> Unit) {
       content.append("\\sum_{$from}^{$to} ")
       TexMathBuilder().apply(body).let {
           content.append(it.toString())
       }
   }

   fun matrix(rows: List<TexMathBuilder.() -> Unit>) {
       content.append("\\begin{bmatrix}")
       rows.forEach { row ->
           TexMathBuilder().apply(row).let {
               content.append(it.toString())
           }
           content.append("\\\\")
       }
       content.append("\\end{matrix}")
   }
}