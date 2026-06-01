# Word Search

**Difficulty:** Medium | **LeetCode:** [#79](https://leetcode.com/problems/word-search/)

---

## Problem

Given an `m × n` board of characters and a word, return true if the word exists in the grid. The word must be formed from adjacent cells (horizontally or vertically). The same cell may not be used twice.

---

## Intuition

Try starting from every cell that matches the first letter. DFS outward, matching one character at a time. Mark cells as visited during the search, then unmark (backtrack) when done.

---

## Approach

**Backtracking DFS** — for each cell matching `word[0]`, launch DFS. At each step, check bounds, visited status, and character match. Mark visited before recursing, unmark after.

---

## Sample Input / Output

```
Board:
  [['A','B','C','E'],
   ['S','F','C','S'],
   ['A','D','E','E']]
word = "ABCCED"
Output: true   (A→B→C→C→E→D)
```

---

## Step-by-step Trace

Input: board above, word = "ABCCED"

| Step | Cell | Char | Word[i] | Match? | Action |
|------|------|------|---------|--------|--------|
| 1 | (0,0) | A | A | Yes | mark visited, go to i=1 |
| 2 | (0,1) | B | B | Yes | mark visited, go to i=2 |
| 3 | (0,2) | C | C | Yes | mark visited, go to i=3 |
| 4 | (1,2) | C | C | Yes | mark visited, go to i=4 |
| 5 | (2,2) | E | E | Yes | mark visited, go to i=5 |
| 6 | (2,1) | D | D | Yes | i=6 == word.length → **return true** |

---

## Java Solution

```java
// Time: O(m×n×4^L)  Space: O(L) — L = word length
boolean exist(char[][] board, String word) {
    for (int i = 0; i < board.length; i++)
        for (int j = 0; j < board[0].length; j++)
            if (dfs(board, word, i, j, 0)) return true;
    return false;
}

boolean dfs(char[][] board, String word, int i, int j, int k) {
    if (k == word.length()) return true;
    if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return false;
    if (board[i][j] != word.charAt(k)) return false;

    char temp = board[i][j];
    board[i][j] = '#'; // mark visited
    boolean found = dfs(board, word, i+1, j, k+1) ||
                    dfs(board, word, i-1, j, k+1) ||
                    dfs(board, word, i, j+1, k+1) ||
                    dfs(board, word, i, j-1, k+1);
    board[i][j] = temp; // unmark
    return found;
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| Word longer than all cells | `false` | Can't use cell twice |
| Single cell board, word matches | `true` | Trivial case |
| Same letter repeated, word needs them non-adjacent | `false` | Path constraint |

---

## Related Problems

| Problem | Link |
|---------|------|
| Word Search II (multiple words) | [LeetCode 212](https://leetcode.com/problems/word-search-ii/) |
| Number of Islands | [LeetCode 200](https://leetcode.com/problems/number-of-islands/) |
| Path with Maximum Gold | [LeetCode 1219](https://leetcode.com/problems/path-with-maximum-gold/) |
