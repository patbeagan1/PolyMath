# PolyMath agent roadmap (per-discipline subtrees)

This directory breaks the PolyMath roadmap into **small, independently executable tasks** for automation or AI agents. Work is grouped by **discipline** (one subdirectory per discipline — a “subtree” of the roadmap, aligned with Gradle modules where possible).

## How to use this as an agent

1. Open **one** discipline folder below; do not mix unrelated disciplines in a single PR unless the task explicitly says so.
2. Pick **one task** (by ID) from that folder’s `AGENT_TASKS.md`.
3. Follow **Scope**, **Steps**, **Verify**, and **Done when** in that task only.
4. Run the listed Gradle (or `./pm`) commands from the **PolyMath** project root unless the task says otherwise.
5. If a task is blocked, choose another task in the same discipline with **Dependencies: none**.

## Discipline index

| Folder | Focus | Primary modules / tools |
|--------|--------|-------------------------|
| [00-foundation-cross-cutting](00-foundation-cross-cutting/AGENT_TASKS.md) | DDD, patterns, consistency, docs architecture | repo-wide `docs/`, `CONTRIBUTING.md` |
| [10-math-algebra-calculus](10-math-algebra-calculus/AGENT_TASKS.md) | Algebra, calculus, symbols | `math-base`, `math-algebra` |
| [20-math-linear-numerics-optimization](20-math-linear-numerics-optimization/AGENT_TASKS.md) | Linear algebra, numerics, optimization | `math-base`, `math-algebra` |
| [30-math-differential-equations](30-math-differential-equations/AGENT_TASKS.md) | ODE/PDE helpers | `math-base`, `math-algebra` |
| [40-statistics-probability](40-statistics-probability/AGENT_TASKS.md) | Distributions, stats | `math-base`, `math-algebra` |
| [50-geometry-spatial](50-geometry-spatial/AGENT_TASKS.md) | 2D/3D geometry | `math-geometry` |
| [60-graph-theory](60-graph-theory/AGENT_TASKS.md) | Graphs, algorithms, viz | `lib-graph` (when enabled), `math-base` |
| [70-units-measures](70-units-measures/AGENT_TASKS.md) | Units, conversions, dimensional stories | `units-base`, `units-common`, `units-data`, `polymath_tool` |
| [80-physics-domain-modules](80-physics-domain-modules/AGENT_TASKS.md) | Physics and future science modules | `physics-classical`, future modules |
| [90-signal-processing](90-signal-processing/AGENT_TASKS.md) | DSP | `signal-processing` |
| [95-latex-rendering-export](95-latex-rendering-export/AGENT_TASKS.md) | LaTeX, Typst, MathML | `latex-builder` |
| [98-notebooks-education](98-notebooks-education/AGENT_TASKS.md) | Notebooks, tutorials | `notebooks/`, `docs/` |
| [99-platform-tooling-quality](99-platform-tooling-quality/AGENT_TASKS.md) | Web/mobile spikes, perf, tests, integrations | whole repo |

## Relationship to `project.meta.yaml`

High-level `roadmap.todo` entries in `project.meta.yaml` mirror these task IDs so `m tasks todo` stays searchable. The **authoritative** checklists and acceptance criteria live in each discipline’s `AGENT_TASKS.md`.

## Optional future git subtrees

If PolyMath is ever split for release, these discipline folders map cleanly to **module name prefixes** (for example `units-*`, `math-*`) for a future `git subtree split` policy. No subtree split is required to complete the tasks in this roadmap.
