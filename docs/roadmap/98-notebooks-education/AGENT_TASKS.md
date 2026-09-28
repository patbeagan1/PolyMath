# Notebooks & education — agent tasks

**Primary locations:** `PolyMath/notebooks/`, `PolyMath/docs/`

---

## NB-01 — One new Kotlin notebook cell flow for domain workflow

**Dependencies:** none  
**Scope:** `PolyMath/notebooks/*.ipynb` (one notebook).  
**Steps:**

1. Add a short section demonstrating **units + expression** together (import lines must match published artifact coordinates or project-local modules per existing notebook style).
2. Keep outputs cleared or committed per repo convention — follow existing notebook in folder.

**Verify:** Manual: open in Jupyter; optional `nbconvert --execute` if CI supports it (do not add CI in this task unless already present).  
**Done when:** Notebook runs top-to-bottom locally per instructions in new markdown cell.

---

## NB-02 — Domain-first tutorial markdown page

**Dependencies:** none  
**Scope:** `PolyMath/docs/tutorial/domain-first-workflow.md`.  
**Steps:**

1. Write a 5–10 minute read using vocabulary of one field (e.g. mechanics) while referencing PolyMath types.
2. Link to `docs/roadmap/README.md` for agents extending the library.

**Verify:** N/A  
**Done when:** Links valid.

---

## NB-03 — “Explain” or stepwise narrative for one existing API

**Dependencies:** none  
**Scope:** if `explain()` exists on expressions, document it; if not, add **doc-only** “how to manually explain” using `toLatex` + comments.  
**Steps:**

1. One markdown file under `docs/tutorial/`.

**Verify:** N/A  
**Done when:** Examples compile if they include Kotlin snippets (use repo snippet style).
