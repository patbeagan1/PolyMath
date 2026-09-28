# Algebra & calculus — agent tasks

**Primary modules:** `math-base`, `math-algebra`

---

## AC-01 — Complex number value type (basics)

**Dependencies:** none  
**Scope:** `math-base` (preferred) or `math-algebra` if types already live there — pick one module and stay within it.  
**Steps:**

1. Introduce an immutable `Complex` (real, imag) with `plus`, `minus`, `times`, `div`, `conjugate`, `abs`.
2. Add `commonTest` tests for arithmetic identities (e.g. `(a+b)*c` with small integer complexes).
3. Document radians vs degrees policy in KDoc if trig interaction is added; if not, state “no trig yet”.

**Verify:** `./gradlew :math-base:test` (adjust module if placed in `math-algebra`).  
**Done when:** Public API KDoc’d; tests green.

---

## AC-02 — Symbolic derivative: core unary/binary cases

**Dependencies:** none  
**Scope:** `math-algebra` expression hierarchy only.  
**Steps:**

1. Implement `derivative(variable: Variable)` (or project’s naming) for: constants, `Variable`, sum, product, power with integer exponent, `sin`, `cos`, `exp`, `ln` — **subset matching existing AST nodes**.
2. Add tests comparing symbolic result to numeric small-epsilon derivative for random numeric assignments.

**Verify:** `./gradlew :math-algebra:test`  
**Done when:** Supported node types documented in KDoc; unsupported types throw clear exception or return “not supported” per existing project pattern.

---

## AC-03 — Symbolic antiderivative (limited family)

**Dependencies:** AC-02  
**Scope:** `math-algebra`.  
**Steps:**

1. Implement indefinite integration for same family as AC-02 + constant rule + sum rule.
2. Omit cases you cannot justify; document omissions.

**Verify:** `./gradlew :math-algebra:test`  
**Done when:** Each supported form has at least one test.

---

## AC-04 — Expression simplification pass (one rule family)

**Dependencies:** none  
**Scope:** `math-algebra` simplifier.  
**Steps:**

1. Choose one family (e.g. constant folding for `+`/`-`/`*`, or idempotent `x+0`).
2. Implement behind existing `simplify` entry point or add package-private helper then wire one call site.
3. Add regression tests from real simplification bugs if any exist in `commonTest`.

**Verify:** `./gradlew :math-algebra:test`  
**Done when:** No behavior change outside documented family; tests cover new branches.

---

## AC-05 — Rational arithmetic edge-case tests

**Dependencies:** none  
**Scope:** `math-base` rational / fraction types.  
**Steps:**

1. Identify rational normalization rules in code.
2. Add tests for: sign canonicalization, division by zero behavior, overflow policy (document expected behavior).

**Verify:** `./gradlew :math-base:test`  
**Done when:** Behaviors explicit in tests or KDoc.
