# Paths in Matrix

**Difficulty:** Medium | **LeetCode:** [Unique Paths #62](https://leetcode.com/problems/unique-paths/)

---

## Problem

A robot starts at the top-left of an `m × n` grid. It can only move right or down. Count the number of unique paths to the bottom-right corner.

---

## Intuition

The number of ways to reach any cell is the sum of ways to reach from the left and from above. Cells in the first row can only be reached by moving right (one way each). Cells in the first column can only be reached by moving down (one way each). Fill the rest bottom-up.

---

## Approach

**2D DP** — `dp[i][j]` = number of unique paths to reach `(i, j)`. Base: `dp[0][j] = 1` and `dp[i][0] = 1`. Recurrence: `dp[i][j] = dp[i-1][j] + dp[i][j-1]`.

---

## Sample Input / Output

```
Input:  m = 3, n = 7
Output: 28
```

For m=3, n=3:

```
dp table:
  1  1  1
  1  2  3
  1  3  6

Output: 6
```

---

## Step-by-step Trace

Input: `m = 3, n = 3`

| | col 0 | col 1 | col 2 |
|---|-------|-------|-------|
| **row 0** | 1 | 1 | 1 |
| **row 1** | 1 | 1+1=2 | 1+2=3 |
| **row 2** | 1 | 1+2=3 | 2+3=**6** |

Return `dp[2][2] = 6` ✓

---

## Java Solution

```java
// Time: O(m×n)  Space: O(m×n)
int uniquePaths(int m, int n) {
    int[][] dp = new int[m][n];
    for (int i = 0; i < m; i++) dp[i][0] = 1;
    for (int j = 0; j < n; j++) dp[0][j] = 1;
    for (int i = 1; i < m; i++)
        for (int j = 1; j < n; j++)
            dp[i][j] = dp[i-1][j] + dp[i][j-1];
    return dp[m-1][n-1];
}
```

**Space-optimized O(n):**

```java
int uniquePaths(int m, int n) {
    int[] dp = new int[n];
    Arrays.fill(dp, 1);
    for (int i = 1; i < m; i++)
        for (int j = 1; j < n; j++)
            dp[j] += dp[j-1];
    return dp[n-1];
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| `m=1, n=1` | `1` | Already at destination |
| `m=1, n=k` | `1` | Only one path — move right k times |
| `m=k, n=1` | `1` | Only one path — move down k times |

---

## Related Problems

| Problem | Link |
|---------|------|
| Unique Paths II (with obstacles) | [LeetCode 63](https://leetcode.com/problems/unique-paths-ii/) |
| Minimum Path Sum | [LeetCode 64](https://leetcode.com/problems/minimum-path-sum/) |
| Number of Ways to Stay in the Same Place After Some Steps | [LeetCode 1269](https://leetcode.com/problems/number-of-ways-to-stay-in-the-same-place-after-some-steps/) |
