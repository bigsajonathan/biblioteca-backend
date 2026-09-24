# Review Backend Change

Review backend code for correctness, risk, and adherence to project conventions.

## Assigned Agent

Use the `spring-api-reviewer` agent to perform the review.

## Required Input
* Review target - a file, a package, or an entity/vertical flow by name (all of its layers: model, dto, repo, service, service.impl, controller).
* When the target has no pending changes, this is a best-practices audit of the existing code, not a diff review.

## Workflow

1. Read `CLAUDE.md` to understand project conventions.
2. Resolve the review target to concrete files: for a named flow, gather all of its layers; for a package, list its files.
3. Delegate the review to the `spring-api-reviewer` agent.
4. When the target is a catalog-style resource, check it against the `Libro` baseline as a best-practices/conventions reference.
5. Confirm REST behavior, persistence, mapping, validation, and configuration were checked, and that missing tests were called out.
6. Run a targeted compile when useful and feasible.
7. Review the agent's output for completeness; if findings lack file/line references or severity, ask it to revise before continuing.
8. Return a final summary including:

    * Findings, ordered by severity, with file/line references
    * Open questions or assumptions
    * Verification performed
    * Residual test gaps

## Acceptance Criteria

- Findings include file and line references.
- The review prioritizes runtime and API-impacting issues.
- If no findings exist, the response states that clearly and identifies residual risk.
