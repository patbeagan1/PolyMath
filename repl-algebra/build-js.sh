#!/bin/bash
# Build script for JavaScript target of repl-algebra module
# This compiles the REPL to JavaScript so it can be used from a webpage

set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_ROOT="$(cd "$SCRIPT_DIR/.." && pwd)"

cd "$PROJECT_ROOT"

echo "Building repl-algebra for JavaScript target..."

# Build the JavaScript target
./gradlew :repl-algebra:jsBrowserProductionWebpack

echo ""
echo "Build complete!"
echo ""
echo "The JavaScript bundle is located at:"
echo "  $PROJECT_ROOT/repl-algebra/build/dist/js/productionExecutable/"
echo ""
echo "Look for repl-algebra.js in that directory"
echo ""
echo "To use it in a webpage:"
echo "  1. Copy the generated JS file to your web server"
echo "  2. Include it in your HTML: <script src='repl-algebra.js'></script>"
echo "  3. Use the ReplJs object: ReplJs.evaluate('1 + 1')"
