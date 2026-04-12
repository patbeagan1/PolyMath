# Physics & future science modules — agent tasks

**Primary module:** `physics-classical`

---

## PH-01 — Document roadmap for “next” physics subdomain

**Dependencies:** none  
**Scope:** `PolyMath/docs/roadmap/80-physics-domain-modules/next-domains.md`.  
**Steps:**

1. Pick one area (e.g. thermodynamics basics OR point-mass mechanics extensions) not yet well covered.
2. List 5–8 future **code** tasks with module placement (`physics-classical` vs new module name).

**Verify:** N/A  
**Done when:** Each listed follow-up is agent-sized (one PR scope).

---

## PH-02 — Single new closed-form formula with tests

**Dependencies:** none  
**Scope:** `physics-classical` only.  
**Steps:**

1. Add one well-known formula (e.g. kinetic energy, ideal gas law as typed wrapper) using existing `math-*` patterns.
2. Tests compare to hand-calculated `Double` values.

**Verify:** `./gradlew :physics-classical:test`  
**Done when:** Units policy documented (typed vs `Double`).

---

## PH-03 — “Chemistry module” feasibility note (doc only)

**Dependencies:** FC-04  
**Scope:** `PolyMath/docs/roadmap/80-physics-domain-modules/chemistry-module-feasibility.md`.  
**Steps:**

1. Summarize which `units-*` types would back mol-based quantities.
2. Recommend whether chemistry deserves a sibling module vs `physics-classical` extension.

**Verify:** N/A  
**Done when:** Clear recommendation + risks.
