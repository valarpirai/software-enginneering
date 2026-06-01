# Edit Distance

**Difficulty:** Hard | **LeetCode:** [#72](https://leetcode.com/problems/edit-distance/)

---

## Problem

Given two strings `word1` and `word2`, return the minimum number of operations (insert, delete, replace) to convert `word1` into `word2`.

---

## Intuition

Compare the strings character by character. If the last characters match, no operation needed for them — solve the rest. If not, take the cheapest of: insert, delete, or replace — then add 1. Overlapping subproblems → DP.

---

## Approach

**2D DP** — `dp[i][j]` = min operations to convert `word1[0..i-1]` to `word2[0..j-1]`.
- If characters match: `dp[i][j] = dp[i-1][j-1]`
- Else: `dp[i][j] = 1 + min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1])` (replace, delete, insert)

---

## Sample Input / Output

```
Input:  word1 = "horse", word2 = "ros"
Output: 3
  horse → rorse (replace h with r)
  rorse → rose  (delete r)
  rose  → ros   (delete e)
```

---

## Step-by-step Trace

Input: `word1 = "abc"`, `word2 = "ac"` (simplified)

| | "" | a | c |
|---|---|---|---|
| **""** | 0 | 1 | 2 |
| **a** | 1 | 0 | 1 |
| **b** | 2 | 1 | 1 |
| **c** | 3 | 2 | **1** |

1 operation: delete 'b' from "abc" → "ac" ✓

---

## Java Solution

```java
// Time: O(m×n)  Space: O(m×n)
int minDistance(String word1, String word2) {
    int m = word1.length(), n = word2.length();
    int[][] dp = new int[m+1][n+1];
    for (int i = 0; i <= m; i++) dp[i][0] = i;
    for (int j = 0; j <= n; j++) dp[0][j] = j;
    for (int i = 1; i <= m; i++)
        for (int j = 1; j <= n; j++) {
            if (word1.charAt(i-1) == word2.charAt(j-1))
                dp[i][j] = dp[i-1][j-1];
            else
                dp[i][j] = 1 + Math.min(dp[i-1][j-1],    // replace
                                Math.min(dp[i-1][j],       // delete
                                         dp[i][j-1]));     // insert
        }
    return dp[m][n];
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| One empty string | length of other | All inserts or deletes |
| Identical strings | `0` | No operations needed |
| `"a","b"` | `1` | One replace |

---

## Related Problems

| Problem | Link |
|---------|------|
| One Edit Distance | [LeetCode 161](https://leetcode.com/problems/one-edit-distance/) |
| Longest Common Subsequence | [LeetCode 1143](https://leetcode.com/problems/longest-common-subsequence/) |
| Minimum ASCII Delete Sum | [LeetCode 712](https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/) |
