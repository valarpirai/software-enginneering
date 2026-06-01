# String Subsequences

**Difficulty:** Medium | **LeetCode:** [Distinct Subsequences #115](https://leetcode.com/problems/distinct-subsequences/)

---

## Problem

Given two strings `s` and `t`, return the number of distinct subsequences of `s` that equal `t`.

---

## Intuition

At each character of `s`, you choose: use it to match the current character of `t`, or skip it. These choices branch into a decision tree with overlapping subproblems — use DP to count all valid branches without recomputation.

---

## Approach

**2D DP** — `dp[i][j]` = number of ways to form `t[0..j-1]` using `s[0..i-1]`. If `s[i-1] == t[j-1]`, you can either use this match or skip it. If different, you can only skip.

Recurrence:
- If `s[i-1] == t[j-1]`: `dp[i][j] = dp[i-1][j-1] + dp[i-1][j]`
- Else: `dp[i][j] = dp[i-1][j]`

---

## Sample Input / Output

```
Input:  s = "rabbbit", t = "rabbit"
Output: 3   (three ways to choose which 'b' to skip)
```

---

## Step-by-step Trace

Input: `s = "rabb"`, `t = "rab"` (simplified)

| | "" | r | a | b |
|---|---|---|---|---|
| **""** | 1 | 0 | 0 | 0 |
| **r** | 1 | 1 | 0 | 0 |
| **a** | 1 | 1 | 1 | 0 |
| **b** | 1 | 1 | 1 | 1 |
| **b** | 1 | 1 | 1 | **2** |

`dp[4][3] = 2` — two ways to form "rab" from "rabb".

---

## Java Solution

```java
// Time: O(m×n)  Space: O(m×n)
int numDistinct(String s, String t) {
    int m = s.length(), n = t.length();
    int[][] dp = new int[m+1][n+1];
    for (int i = 0; i <= m; i++) dp[i][0] = 1; // empty t matches once

    for (int i = 1; i <= m; i++)
        for (int j = 1; j <= n; j++) {
            dp[i][j] = dp[i-1][j]; // always: skip s[i-1]
            if (s.charAt(i-1) == t.charAt(j-1))
                dp[i][j] += dp[i-1][j-1]; // also: use the match
        }
    return dp[m][n];
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| `s = "a", t = "a"` | `1` | Exact match |
| `s = "a", t = "b"` | `0` | No match possible |
| `s = "aaa", t = "a"` | `3` | Three ways to pick one 'a' |
| `t` longer than `s` | `0` | Can't form a longer string |

---

## Related Problems

| Problem | Link |
|---------|------|
| Is Subsequence | [LeetCode 392](https://leetcode.com/problems/is-subsequence/) |
| Longest Common Subsequence | [LeetCode 1143](https://leetcode.com/problems/longest-common-subsequence/) |
| Edit Distance | [LeetCode 72](https://leetcode.com/problems/edit-distance/) |
