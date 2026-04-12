# Foundation & cross-cutting — agent tasks

**Discipline root:** `PolyMath/docs/roadmap/00-foundation-cross-cutting/`  
**Typical touch points:** `docs/`, top-level `README.md`, `CONTRIBUTING.md`, cross-module `README` snippets.

---

## FC-01 — Bounded-context map for existing modules

**Dependencies:** none  
**Scope:** Documentation only under `PolyMath/docs/`.  
**Steps:**

1. Add `PolyMath/docs/architecture/bounded-contexts.md`.
2. For each included Gradle module (`math-base`, `math-algebra`, `math-geometry`, `physics-classical`, `signal-processing`, `units-base`, `units-common`, `units-data`, `latex-builder`, `repl-algebra`), write one subsection: purpose, main packages, upstream/downstream dependencies, and “anti-corruption” boundaries (what types may leak across).
3. Link the file from `PolyMath/README.md` under a short “Architecture” bullet.

**Verify:** N/A (docs).  
**Done when:** File exists, every module in `settings.gradle.kts` is covered, README links to it.

---

## FC-02 — ADR template + first ADR for units/math boundary

**Dependencies:** none  
**Scope:** `PolyMath/docs/adr/` (new).  
**Steps:**

1. Add `PolyMath/docs/adr/README.md` explaining ADR format.
2. Add `PolyMath/docs/adr/0001-record-template.md` as template.
3. Add `PolyMath/docs/adr/0002-units-math-boundary.md` describing how `units-*` types should interact with `math-*` expressions (current state + intended direction; no code change required).

**Verify:** N/A.  
**Done when:** Three files exist; ADR 0002 is concrete enough to guide a future code change.

---

## FC-03 — Ubiquitous-language glossary (core DSL)

**Dependencies:** none  
**Scope:** `PolyMath/math-base` KDoc only (no behavior change).  
**Steps:**

1. Add or extend a `package-info.kt` (or module README) in the primary public package for the DSL documenting terms: `Expression`, `Variable`, `Constant`, `evaluate`, `toLatex`, `simplify` (adjust names to match actual public API).
2. Keep definitions short (1–2 sentences each).

**Verify:** `./gradlew :math-base:compileKotlinJvm` (or project-default compile task for the module).  
**Done when:** Glossary visible in Dokka or IDE quick-doc for that package.

---

## FC-04 — “New scientific domain module” contributor checklist

**Dependencies:** none  
**Scope:** `PolyMath/CONTRIBUTING.md` or `PolyMath/docs/contributing/new-domain-module.md`.  
**Steps:**

1. Document directory layout mirroring `physics-classical` / `signal-processing`: `build.gradle.kts`, `src/commonMain`, `src/commonTest`, naming conventions.
2. Include checklist: add to `settings.gradle.kts`, depends on `math-base` + relevant `units-*`, minimal smoke test, `project.meta.yaml` script entries if needed.

**Verify:** N/A.  
**Done when:** A new contributor can follow the checklist without asking questions.

---

## FC-05 — Reference “domain service” example in physics-classical

**Dependencies:** FC-03 helpful but not required.  
**Scope:** `PolyMath/physics-classical` only.  
**Steps:**

1. Add a small, documented façade (e.g. `ProjectileMotion` or rename to fit existing code style) that composes `math-*` primitives for one physical scenario.
2. Add `commonTest` tests that only assert public API of that façade (numerical tolerances documented).

**Verify:** `./gradlew :physics-classical:test`  
**Done when:** Tests pass; class KDoc states it is a DDD-style example, not core framework.

---

## FC-06 — Cross-domain dimensional analysis note

**Dependencies:** none  
**Scope:** `PolyMath/docs/architecture/dimensional-analysis.md`.  
**Steps:**

1. Explain current capabilities of `units-*` for preventing invalid adds.
2. List 3–5 realistic “sharp edges” when mixing modules (e.g. raw `Double` vs typed units).
3. Propose incremental mitigations (each mitigation = future task in `70-units-measures`).

**Verify:** N/A.  
**Done when:** Doc is actionable; each edge links to a follow-up idea (can be new tasks later).

---

## FC-07 — Repeatable “new domain” scaffold doc

**Dependencies:** FC-04  
**Scope:** `PolyMath/docs/roadmap/patterns/new-domain-scaffold.md`.  
**Steps:**

1. Copy concrete file tree from one existing module.
2. List naming rules for packages, tests, and publishing keys.

**Verify:** N/A.  
**Done when:** FC-04 checklist and this doc do not contradict each other.
