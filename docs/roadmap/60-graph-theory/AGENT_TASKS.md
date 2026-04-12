# Graph theory — agent tasks

**Primary module:** `lib-graph` (currently commented out in `settings.gradle.kts`) or documentation-only if module remains disabled.

---

## GT-01 — Re-enable `lib-graph` in Gradle settings

**Dependencies:** none  
**Scope:** `PolyMath/settings.gradle.kts`, `lib-graph/build.gradle.kts` and related.  
**Steps:**

1. Uncomment `include(":lib-graph")`.
2. Run `./gradlew :lib-graph:compileKotlinJvm` (or appropriate compile task for targets).
3. Fix configuration errors until compile succeeds (minimal fix — no feature work).

**Verify:** `./gradlew :lib-graph:build`  
**Done when:** Root `./gradlew build` passes with `lib-graph` included.

---

## GT-02 — BFS shortest path on adjacency list

**Dependencies:** GT-01  
**Scope:** `lib-graph`.  
**Steps:**

1. Implement generic BFS returning path for unweighted directed graph.
2. Tests: simple 4-node graph, unreachable target returns empty or optional per API choice.

**Verify:** `./gradlew :lib-graph:test`  
**Done when:** Time/space complexity noted in KDoc.

---

## GT-03 — Graphviz export for tiny graph (optional)

**Dependencies:** GT-02  
**Scope:** `lib-graph` or bridge to existing Graphviz pipeline in repo.  
**Steps:**

1. Serialize 3–5 node graph to `.dot` string.
2. Test string contains expected edges only.

**Verify:** `./gradlew :lib-graph:test`  
**Done when:** No shell dependency required for test.
