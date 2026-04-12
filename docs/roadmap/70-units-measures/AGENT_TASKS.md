# Units & measures — agent tasks

**Primary modules:** `units-base`, `units-common`, `units-data`, `polymath_tool` (`./pm`)

---

## UM-01 — Regression sweep: `./pm fix imports` clean

**Dependencies:** none  
**Scope:** `units-common` / `units-data` imports after generator or manual edits.  
**Steps:**

1. From `PolyMath/`, run `./pm fix imports` (or `uv run python -m polymath_tool fix imports` per project README).
2. If changes produced, commit-worthy diff should be reviewed; task goal is **zero** unexpected failures and Kotlin compile.

**Verify:** `./gradlew :units-common:compileKotlinJvm` and `:units-data:compileKotlinJvm` (use project’s actual targets).  
**Done when:** No compile errors; document any intentional skips in task notes (PR description).

---

## UM-02 — One new conversion pair with tests

**Dependencies:** none  
**Scope:** one file pair in `units-common` following existing pattern.  
**Steps:**

1. Pick one documented missing conversion from project issues or `docs/` summaries (or a clearly useful pair).
2. Implement `toX()` / reciprocal following neighboring types.
3. Add `commonTest` asserting round-trip within documented epsilon.

**Verify:** `./gradlew :units-common:test`  
**Done when:** Single conversion family only — do not refactor unrelated units.

---

## UM-03 — `units-data` storage units: doc vs implementation audit

**Dependencies:** none  
**Scope:** `units-data` + `docs/STORAGE_UNITS_SUMMARY.md`.  
**Steps:**

1. List each public type in storage package; confirm summary doc mentions it or add one line each.
2. No behavior change unless doc reveals a bug — then **separate** bugfix task.

**Verify:** `./gradlew :units-data:test`  
**Done when:** Doc PR is self-contained.

---

## UM-04 — Dimensional-analysis doc cross-links

**Dependencies:** FC-06 (can run in parallel; add cross-link after FC-06 exists or add stub link)  
**Scope:** `PolyMath/docs/architecture/dimensional-analysis.md` only.  
**Steps:**

1. Add links from dimensional-analysis doc to `units-base` README or key interfaces.

**Verify:** N/A  
**Done when:** Links resolve.
