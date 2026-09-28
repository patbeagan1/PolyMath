# Differential equations — agent tasks

**Primary modules:** `math-base`, `math-algebra`

---

## DE-01 — Explicit Euler step for scalar ODE

**Dependencies:** none  
**Scope:** `math-algebra` (numerical ODE util) or dedicated subpackage.  
**Steps:**

1. Implement `eulerStep(y, t, dt, f)` for `dy/dt = f(t, y)` with `Double` state.
2. Test: exponential ODE (`dy/dt = y`, `y(0)=1`) matches `exp(dt)` at one step with small `dt`.

**Verify:** `./gradlew :math-algebra:test`  
**Done when:** API documented; no symbolic solver claimed.

---

## DE-02 — RK4 step (scalar)

**Dependencies:** DE-01 optional (shared test fixtures OK).  
**Scope:** same package as DE-01.  
**Steps:**

1. Implement classic RK4 single step.
2. Test same exponential ODE; assert smaller error than Euler for same `dt`.

**Verify:** `./gradlew :math-algebra:test`  
**Done when:** Comparison test documents expected error order loosely (order-of-magnitude).
