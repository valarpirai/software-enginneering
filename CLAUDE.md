# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## What This Repo Is

A personal DSA (Data Structures & Algorithms) learning course, published as a static docs site via [Docsify](https://docsify.js.org/). Content lives in `docs/` as Markdown files with embedded Mermaid diagrams. Code examples use **Java** (preferred). Scratch exercises are in Python and Java at the root.

## Previewing the Docs Site

Docsify serves straight from the `docs/` directory. Any static server works:

```bash
# Python (no install needed)
python3 -m http.server 3000 --directory docs

# Node (if docsify-cli installed)
npx docsify serve docs
```

Then open `http://localhost:3000`. Mermaid diagrams render in the browser — preview there, not in a Markdown renderer.

## Running Python Exercises

```bash
python3 n_queens.py
```

No dependencies or virtual environment needed; the exercises are self-contained scripts.

## Doc File Naming Convention

Files in `docs/` follow a numbered prefix scheme:

| Range | Category |
|-------|----------|
| `00–11` | Data Structures |
| `20–26` | Sorting & Searching |
| `30–3x` | Graph Algorithms |
| `40–4x` | Problem-Solving Techniques |

New topics go into the next available number in the appropriate range. After adding a file, update both `docs/_sidebar.md` (navigation) and `docs/00-index.md` (complexity table + file listing).

## Audience

The docs target **beginners**. Assume the reader knows basic programming (variables, loops, functions) but has no prior DSA knowledge. Avoid jargon without defining it first.

## Writing Style — Hemingway Rules

Write like Hemingway. Short sentences. Active voice. No filler words.

- **Short sentences.** If a sentence needs a comma, split it into two.
- **Active voice.** "The stack stores values." Not "Values are stored by the stack."
- **No filler.** Cut "basically", "simply", "in order to", "it is important to note that".
- **Concrete over abstract.** Show an example before explaining the rule.
- **Grade 6 reading level.** If a 12-year-old can't read it, rewrite it.
- **One idea per paragraph.**

## Doc File Structure

Every topic doc must follow this section order:

### 1. H1 Title + one-line description

### 2. Intuition (minimal theory)
Two to four sentences max. Answer "what problem does this solve and why does this shape make sense?" — no proofs, no formal notation.

### 3. Operations table
| Method | Description | Time Complexity |

### 4. Sample Input
State the concrete input that will be traced through the entire doc. Use it consistently in the diagram and variable trace.

### 5. Mermaid diagram — visual representation
Show the structure or algorithm state step by step using the sample input. Use `block-beta` for stack/array structures (renders as stacked vertical boxes, not a linked-list chain). Use `graph TD` or `sequenceDiagram` for algorithm flow.

### 6. Step-by-step variable trace
Markdown table showing variable values at each step as the algorithm processes the sample input:

| Step | Action | key variables... | Notes |
|------|--------|-----------------|-------|

### 7. Java implementation
Standalone class snippet. Include time and space complexity as a comment above the method.

### 8. Common Mistakes
Short bulleted list of non-obvious errors beginners typically make with this topic.

### 9. Practice Problems
2–3 curated LeetCode problems, ordered Easy → Medium → Hard:

| Difficulty | Problem | Link |
|------------|---------|------|

### 10. Deep Dive *(optional section at the bottom)*
Formal complexity proofs, mathematical derivations, or implementation trade-offs. Section heading must be `## Deep Dive`. Only include when there is genuinely useful depth beyond the intuition.

---

Mermaid diagrams use fenced blocks tagged `mermaid`. Stack/array diagrams use `block-beta` to render as vertical boxes, not linked-list chains.

## Sidebar & Index Maintenance

`docs/_sidebar.md` — controls the left nav in Docsify. Must be updated when adding or renaming a doc.

`docs/00-index.md` — master complexity reference. Each new data structure or algorithm belongs in the relevant table here.

## Code Style

- Java examples are standalone class snippets (no `main` needed unless the example is a runnable demo).
- Python files at the root are scripts, not modules — they run directly with `python3`.
- No HTML tags in inline comments in source files.
