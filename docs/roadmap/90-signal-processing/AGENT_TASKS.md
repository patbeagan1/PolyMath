# Signal processing — agent tasks

**Primary module:** `signal-processing`

---

## SP-01 — Window function unit tests (one family)

**Dependencies:** none  
**Scope:** `signal-processing` existing window implementations.  
**Steps:**

1. Pick Hann, Hamming, or rectangular (whichever exists or is smallest to add).
2. Add tests: symmetry, endpoints, sum-to-N normalization policy as documented in DSP literature or project choice.

**Verify:** `./gradlew :signal-processing:test`  
**Done when:** One window family fully specified in tests.

---

## SP-02 — FFT round-trip smoke (if API exists)

**Dependencies:** none  
**Scope:** `signal-processing`.  
**Steps:**

1. If forward/inverse FFT public API exists, add test: impulse → spectrum → inverse recovers impulse within epsilon.
2. If API missing, change task to: document missing API in `AGENT_TASKS.md` follow-up list only (file `SP-02-deferred.md`) — **prefer implementation** if trivial wrapper exists.

**Verify:** `./gradlew :signal-processing:test`  
**Done when:** Clear pass or explicit defer note with issue-style bullet list.
