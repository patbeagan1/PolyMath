# Statistics & probability — agent tasks

**Primary modules:** `math-base`, `math-algebra`

---

## ST-01 — Normal distribution PDF/ln-PDF

**Dependencies:** none  
**Scope:** new types under `math-algebra` or `math-base` statistics package (create package if missing).  
**Steps:**

1. Implement `NormalDistribution(mean: Double, sigma: Double)` with validation `sigma > 0`.
2. Expose `pdf(x)` and optionally `logPdf(x)`.
3. Tests: integral approximation or known values at `mean`, `mean±sigma`.

**Verify:** `./gradlew` for module containing the code + tests.  
**Done when:** Edge cases (`sigma` invalid) documented and tested.

---

## ST-02 — Bernoulli and Binomial PMF

**Dependencies:** none  
**Scope:** same statistics package as ST-01 or adjacent file.  
**Steps:**

1. `Bernoulli(p)` with `p in (0,1)`; `Binomial(n,p)` PMF for small `n` with exact factorials using existing combinatorics if present.
2. Tests against textbook values for `n=5`, `k=2`, `p=0.25`.

**Verify:** `./gradlew` + tests.  
**Done when:** Combinatorial overflow behavior documented for large `n` (may throw or require log domain — pick one).

---

## ST-03 — Basic descriptive stats on `DoubleArray`

**Dependencies:** none  
**Scope:** `math-base` util (pure Kotlin).  
**Steps:**

1. Implement `mean`, `variance` (sample vs population — document choice), `stdDev`.
2. Tests include single-element and constant array.

**Verify:** `./gradlew :math-base:test`  
**Done when:** Empty array behavior defined (throw or NaN — document).
