# Geometry & spatial math — agent tasks

**Primary module:** `math-geometry`

---

## GE-01 — Vector3 with dot, cross, norm

**Dependencies:** none  
**Scope:** `math-geometry`.  
**Steps:**

1. Add `Vector3` data class with `dot`, `cross`, `norm`, `normalized()`.
2. Tests: orthogonality, `cross(u,v)` perpendicular to `u` and `v`, right-hand rule with one fixed example.

**Verify:** `./gradlew :math-geometry:test`  
**Done when:** Division by zero on `normalized()` documented.

---

## GE-02 — Axis-angle or quaternion stub (orientation)

**Dependencies:** GE-01  
**Scope:** `math-geometry`.  
**Steps:**

1. Add minimal `Quaternion` or rotation matrix builder from axis-angle (choose one representation).
2. Test: rotate basis vector `e_x` by 90° about `z` yields `e_y` within epsilon.

**Verify:** `./gradlew :math-geometry:test`  
**Done when:** API marked experimental if incomplete.
