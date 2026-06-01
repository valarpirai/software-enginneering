# Minimum Cost Path in Matrix

**Difficulty:** Medium | **LeetCode:** [Minimum Path Sum #64](https://leetcode.com/problems/minimum-path-sum/)

---

## Problem

Given an `m × n` grid of non-negative integers, find the path from the top-left to the bottom-right that minimizes the sum. You can only move right or down.

---

## Intuition

The minimum cost to reach any cell is the cell's value plus the minimum of the cost to reach from the left or from above. Build the solution bottom-up, filling the grid row by row.

---

## Approach

**2D Dynamic Programming** — `dp[i][j]` = minimum path sum to reach cell `(i, j)`. Base cases: first row (only move right) and first column (only move down). Recurrence: `dp[i][j] = grid[i][j] + min(dp[i-1][j], dp[i][j-1])`.

---

## Sample Input / Output

```
Input:
  grid = [[1, 3, 1],
          [1, 5, 1],
          [4, 2, 1]]

Output: 7   (path: 1→3→1→1→1)
```

---

## Step-by-step Trace

Input: grid above. Build dp table:

| | col 0 | col 1 | col 2 |
|---|-------|-------|-------|
| **row 0** | 1 | 1+3=4 | 4+1=5 |
| **row 1** | 1+1=2 | min(4,2)+5=7 | min(5,7)+1=6 |
| **row 2** | 2+4=6 | min(7,6)+2=8 | min(6,8)+1=**7** |

Return `dp[2][2] = 7` ✓

---

## Java Solution

```java
// Time: O(m×n)  Space: O(m×n)
int minPathSum(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    int[][] dp = new int[m][n];
    dp[0][0] = grid[0][0];

    for (int j = 1; j < n; j++) dp[0][j] = dp[0][j-1] + grid[0][j];
    for (int i = 1; i < m; i++) dp[i][0] = dp[i-1][0] + grid[i][0];

    for (int i = 1; i < m; i++)
        for (int j = 1; j < n; j++)
            dp[i][j] = grid[i][j] + Math.min(dp[i-1][j], dp[i][j-1]);

    return dp[m-1][n-1];
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| `[[5]]` | `5` | Single cell |
| Single row `[[1,2,3]]` | `6` | Only move right |
| Single column | Sum of all | Only move down |

---

## Related Problems

| Problem | Link |
|---------|------|
| Unique Paths | [LeetCode 62](https://leetcode.com/problems/unique-paths/) |
| Triangle | [LeetCode 120](https://leetcode.com/problems/triangle/) |
| Dungeon Game | [LeetCode 174](https://leetcode.com/problems/dungeon-game/) |
