# Linear algebra, numerics, optimization — agent tasks

**Primary modules:** `math-base`, `math-algebra`

---

## LN-01 — Small fixed-size matrices (2×2, 3×3)

**Dependencies:** none  
**Scope:** `math-algebra` (or `math-base` if matrix types belong next to scalars — follow existing package structure).  
**Steps:**

1. Add `Matrix2`, `Matrix3` (or single `Matrix` with dimension enum) with `times` for vector and matrix, `det`, `transpose`.
2. Use `Double` coefficients initially; document precision expectations.
3. `commonTest`: identity multiplication, det of known matrix, non-commutative multiply example.

**Verify:** `./gradlew :math-algebra:test`  
**Done when:** APIs KDoc’d; tests green.

---

## LN-02 — Bisection root finding (scalar)

**Dependencies:** none  
**Scope:** `math-algebra` util package or `math-base` if utilities live there.  
**Steps:**

1. Implement `bisection(f, a, b, tol, maxIter)` with bracket validation.
2. Tests: linear root, cubic with known root, bad bracket throws.

**Verify:** `./gradlew` on the module you touched + tests.  
**Done when:** Function is pure (no global state); errors are typed or documented.

---

## LN-03 — Golden-section 1D minimization

**Dependencies:** none  
**Scope:** Same as LN-02.  
**Steps:**

1. Implement unimodal minimization on `[a,b]` with golden section.
2. Test on parabola with known minimum.

**Verify:** `./gradlew` + tests for touched module.  
**Done when:** `maxIter` and tolerance documented.

---

## LN-04 — Numerical derivative helper (optional for other tasks)

**Dependencies:** none  
**Scope:** test-only or `math-algebra` internal util.  
**Steps:**

1. Central difference `numericDerivative(f, x, h)` with default `h`.
2. Used only from tests unless already needed — avoid polluting public API unless justified.

**Verify:** `./gradlew :math-algebra:test`  
**Done when:** Used by at least one existing or new test.
