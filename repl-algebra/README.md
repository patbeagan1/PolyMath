# PolyMath Algebra REPL

A multiplatform REPL (Read-Eval-Print Loop) for polymath algebra that can visualize algebra trees, modify expressions, and simplify them.

## Features

- **Dual Input Formats**: Supports both infix notation (`1 + 1`) and command notation (`add 1 1`)
- **Visualization**: View expression trees in Graphviz format
- **Simplification**: Automatically simplify algebraic expressions
- **Multiplatform**: Works on JVM, JavaScript (browser), and Native platforms
- **Web-Ready**: Includes build script for JavaScript target for web usage

## Usage

### JVM (Command Line)

Run the REPL interactively:

```bash
./gradlew :repl-algebra:run
```

Then you can enter expressions:
```
> 1 + 1
Expression: 1 + 1
Value: 2.0

> :simplify
Original: 1 + 1
Simplified: 2
Value: 2.0

> :help
```

### JavaScript (Web)

Build for JavaScript:

```bash
./repl-algebra/build-js.sh
```

Or manually:

```bash
./gradlew :repl-algebra:jsBrowserProductionWebpack
```

The generated JavaScript file can be included in a webpage:

```html
<script src="repl-algebra.js"></script>
<script>
    const result = ReplJs.evaluate("1 + 1");
    console.log(result);
    
    const latex = ReplJs.getCurrentLatex();
    console.log(latex);
</script>
```

## Input Formats

### Infix Notation

Standard mathematical notation:
- `1 + 1`
- `2 * 3`
- `x^2`
- `(1 + 2) * 3`
- `sqrt(4)`
- `sin(pi/2)`

### Command Notation

Verb-based commands:
- `add 1 1`
- `multiply 2 3`
- `power 2 3`
- `sqrt 4`
- `sin 1.57`

## REPL Commands

Commands are prefixed with `:`:

- `:help` or `:h` - Show help message
- `:simplify` or `:s` - Simplify the current expression
- `:visualize` or `:v` - Show expression tree (Graphviz format)
- `:latex` or `:l` - Show LaTeX representation
- `:eval` or `:e` - Evaluate the current expression
- `:set <var> <value>` - Set a variable value
- `:clear` - Clear the current expression
- `:vars` - Show all variable assignments
- `:tree` - Show text representation of the tree

## Examples

### Basic Arithmetic

```
> 1 + 1
Expression: 1 + 1
Value: 2.0

> 2 * 3 + 4
Expression: 2 \cdot 3 + 4
Value: 10.0
```

### Variables

```
> x + 1
Expression: x + 1
Value: NaN

> :set x 5
Set x = 5.0

> x + 1
Expression: x + 1
Value: 6.0
```

### Simplification

```
> (x + 1) + (x + 2)
Expression: (x + 1) + (x + 2)
Value: 13.0

> :simplify
Original: (x + 1) + (x + 2)
Simplified: 2 \cdot x + 3
Value: 13.0
```

### Visualization

```
> 1 + 2 * 3
Expression: 1 + 2 \cdot 3
Value: 7.0

> :visualize
digraph {
rankdir = "RL"
...
}
```

## Architecture

- **Parser**: Recursive descent parser supporting both infix and command notation
- **AlgebraRepl**: Main REPL class that processes input and manages state
- **ReplJs**: JavaScript API wrapper for web usage
- **Platform-specific entry points**: JVM (Main.kt) and JS (ReplJs.kt)

## Building

### All Platforms

```bash
./gradlew :repl-algebra:build
```

### JavaScript Only

```bash
./repl-algebra/build-js.sh
```

### JVM Only

```bash
./gradlew :repl-algebra:jvmJar
```

## Testing

Run tests:

```bash
./gradlew :repl-algebra:test
```

## Integration

The REPL module depends on `math-algebra` and can be used as a library:

```kotlin
import main.repl.AlgebraRepl

val repl = AlgebraRepl()
val result = repl.processInput("1 + 1")
println(result)
```
