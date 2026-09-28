# LaTeX rendering & export — agent tasks

**Primary module:** `latex-builder`

---

## LX-01 — Extension points documentation

**Dependencies:** none  
**Scope:** `latex-builder/README.md` or `docs/` in module.  
**Steps:**

1. Document how to add a new `toLatex` visitor case (or project’s pattern).
2. Link to one example class in `math-algebra`.

**Verify:** N/A  
**Done when:** New contributor can locate visitor interface.

---

## LX-02 — Typst export for Add/Mul/Pow only

**Dependencies:** none  
**Scope:** `latex-builder` and/or `math-base` rendering interface.  
**Steps:**

1. Add `toTypst()` (name per convention) for a minimal node subset: literals, add, mul, pow.
2. Tests: snapshot string equality for 2–3 expressions.

**Verify:** `./gradlew :latex-builder:test` (and any module defining `toTypst`).  
**Done when:** Unsupported nodes throw or return documented sentinel — match project error style.

---

## LX-03 — MathML export spike (subset)

**Dependencies:** none  
**Scope:** `latex-builder` or adjacent.  
**Steps:**

1. Implement MathML string export for **same** subset as LX-02.
2. Test string contains `<math` and expected `<mi>` / `<mo>` tokens for one expression.

**Verify:** `./gradlew :latex-builder:test`  
**Done when:** Clearly labeled experimental API.
