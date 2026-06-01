# N-Queens

**Difficulty:** Hard | **LeetCode:** [#51](https://leetcode.com/problems/n-queens/)

---

## Problem

Place `n` queens on an `n × n` chessboard such that no two queens attack each other (no two in the same row, column, or diagonal). Return all valid board configurations.

---

## Intuition

Place one queen per row. For each row, try every column. A column is valid if no other queen is in the same column or on the same diagonal. Backtrack when no valid column exists for the current row.

---

## Approach

**Backtracking row by row** — track which columns and diagonals are occupied using three boolean arrays. When all n rows are placed, record the board.

---

## Sample Input / Output

```
Input:  n = 4
Output:
  [".Q..",     ["..Q.",
   "...Q",      "Q...",
   "Q...",      "...Q",
   "..Q."]      ".Q.."]
```

---

## Step-by-step Trace

Input: `n = 4`, placing row by row

| Row | Try col | Column used? | Diag \ | Diag / | Valid? |
|-----|---------|-------------|--------|--------|--------|
| 0 | 1 | No | No | No | Place → col[1], d1[1], d2[3] |
| 1 | 3 | No | No | No | Place → col[3], d1[4], d2[2] |
| 2 | 0 | No | No | No | Place → col[0], d1[2], d2[6] |
| 3 | 2 | No | No | No | Place → **valid solution** |

Solution 1: Q at (0,1),(1,3),(2,0),(3,2) → `[.Q.., ...Q, Q..., ..Q.]` ✓

---

## Java Solution

```java
// Time: O(n!)  Space: O(n)
List<List<String>> solveNQueens(int n) {
    List<List<String>> result = new ArrayList<>();
    boolean[] cols = new boolean[n];
    boolean[] diag1 = new boolean[2*n]; // row - col + n
    boolean[] diag2 = new boolean[2*n]; // row + col
    int[] queens = new int[n]; // queens[row] = col
    Arrays.fill(queens, -1);
    backtrack(queens, n, 0, cols, diag1, diag2, result);
    return result;
}

void backtrack(int[] queens, int n, int row, boolean[] cols, boolean[] diag1, boolean[] diag2, List<List<String>> result) {
    if (row == n) { result.add(buildBoard(queens, n)); return; }
    for (int col = 0; col < n; col++) {
        if (cols[col] || diag1[row-col+n] || diag2[row+col]) continue;
        queens[row] = col;
        cols[col] = diag1[row-col+n] = diag2[row+col] = true;
        backtrack(queens, n, row+1, cols, diag1, diag2, result);
        cols[col] = diag1[row-col+n] = diag2[row+col] = false;
    }
}

List<String> buildBoard(int[] queens, int n) {
    List<String> board = new ArrayList<>();
    for (int r = 0; r < n; r++) {
        char[] row = new char[n];
        Arrays.fill(row, '.');
        row[queens[r]] = 'Q';
        board.add(new String(row));
    }
    return board;
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| `n=1` | `[["Q"]]` | Trivial — one queen fills the board |
| `n=2` | `[]` | No valid placement exists |
| `n=3` | `[]` | No valid placement exists |

---

## Related Problems

| Problem | Link |
|---------|------|
| N-Queens II (count solutions) | [LeetCode 52](https://leetcode.com/problems/n-queens-ii/) |
| Sudoku Solver | [LeetCode 37](https://leetcode.com/problems/sudoku-solver/) |
| Word Search | [LeetCode 79](https://leetcode.com/problems/word-search/) |
