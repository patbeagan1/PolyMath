# Platform integrations, web/mobile spikes, quality — agent tasks

Cross-cutting quality, performance, and integration experiments.

---

## PQ-01 — `./gradlew test` baseline script note in README

**Dependencies:** none  
**Scope:** `PolyMath/README.md`.  
**Steps:**

1. Add a “Quality” subsection: exact commands for `./gradlew test`, `./gradlew build`, and when to use `./pm fix all`.
2. No CI changes.

**Verify:** N/A  
**Done when:** Commands were run once by implementer and match Gradle wrapper.

---

## PQ-02 — Micro-benchmark for deep expression tree (JMH or simple measure)

**Dependencies:** none  
**Scope:** `jvmTest` or dedicated benchmark source set — follow repo conventions; if none, add **minimal** `jvmTest` that runs timed loop with assertion that median < threshold on CI-friendly size (relaxed) **or** document opt-in benchmark in `docs/`.  
**Steps:**

1. Prefer not adding heavy deps; simple `measureTimeMillis` in `jvmTest` with generous slack is acceptable.
2. Goal: catch catastrophic regression, not scientific benchmarking.

**Verify:** `./gradlew :math-algebra:jvmTest` or appropriate target.  
**Done when:** Test not flaky (use warm-up loop).

---

## PQ-03 — Web expression editor spike (static HTML)

**Dependencies:** none  
**Scope:** `PolyMath/docs/spikes/web-expression-editor/` new folder.  
**Steps:**

1. Single `index.html` + `README.md` loading KaTeX (CDN OK) rendering a **hard-coded** LaTeX string produced by library (string may be pasted manually first).
2. README states this is not production UI.

**Verify:** Open file in browser manually.  
**Done when:** README lists limitations.

---

## PQ-04 — Proof-assistance / verification: scope note

**Dependencies:** none  
**Scope:** `PolyMath/docs/roadmap/99-platform-tooling-quality/proof-verification-scope.md`.  
**Steps:**

1. Summarize why full proof verification is out of scope short-term.
2. List 3 feasible stepping-stone tasks (e.g. property tests for ring laws on `Complex` after AC-01).

**Verify:** N/A  
**Done when:** Stepping stones map to task IDs in other discipline files.

---

## PQ-05 — Integration stub: export expression string for external CAS

**Dependencies:** none  
**Scope:** `docs/` or tiny utility in `polymath_tool` — string formatter only.  
**Steps:**

1. Document a function or CLI flag that prints a **Mathematica**-style or **SymPy**-style string for a tiny AST subset (no external deps).
2. Test as pure string snapshot in Python `unittest` or Kotlin if placed in JVM test.

**Verify:** Appropriate `./gradlew` or `uv run` test command for location chosen.  
**Done when:** One format, clearly documented.

---

## PQ-06 — Test coverage: weakest package triage

**Dependencies:** none  
**Scope:** documentation only.  
**Steps:**

1. Run coverage if project already configured; else manually identify least-tested public package via grep for `commonTest` vs `commonMain` file counts.
2. Output `PolyMath/docs/roadmap/99-platform-tooling-quality/coverage-triage.md` with top 3 packages and suggested next 3 test tasks (one paragraph each).

**Verify:** N/A  
**Done when:** Actionable follow-ups.
