# Longest Common Subsequence

**Difficulty:** Medium | **LeetCode:** [#1143](https://leetcode.com/problems/longest-common-subsequence/)

---

## Problem

Given two strings, return the length of their longest common subsequence (LCS). A subsequence maintains relative order but doesn't need to be contiguous.

---

## Intuition

Compare characters from both strings. If they match, the LCS includes that character — add 1 to the LCS of the remaining strings. If not, take the better of skipping a character from either string.

---

## Approach

**2D DP** — `dp[i][j]` = LCS length of `s1[0..i-1]` and `s2[0..j-1]`.
- If `s1[i-1] == s2[j-1]`: `dp[i][j] = dp[i-1][j-1] + 1`
- Else: `dp[i][j] = max(dp[i-1][j], dp[i][j-1])`

---

## Sample Input / Output

```
Input:  s1 = "ABCBDAB", s2 = "BDCAB"
Output: 4   (LCS = "BCAB" or "BDAB")
```

---

## Step-by-step Trace

Input: `s1 = "ABCD"`, `s2 = "ACD"` (simplified)

| | "" | A | C | D |
|---|---|---|---|---|
| **""** | 0 | 0 | 0 | 0 |
| **A** | 0 | 1 | 1 | 1 |
| **B** | 0 | 1 | 1 | 1 |
| **C** | 0 | 1 | 2 | 2 |
| **D** | 0 | 1 | 2 | **3** |

LCS = 3 ("ACD") ✓

---

## Java Solution

```java
// Time: O(m×n)  Space: O(m×n)
int longestCommonSubsequence(String s1, String s2) {
    int m = s1.length(), n = s2.length();
    int[][] dp = new int[m+1][n+1];
    for (int i = 1; i <= m; i++)
        for (int j = 1; j <= n; j++) {
            if (s1.charAt(i-1) == s2.charAt(j-1))
                dp[i][j] = dp[i-1][j-1] + 1;
            else
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
        }
    return dp[m][n];
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| One empty string | `0` | No common characters |
| Identical strings | length of string | Entire string is LCS |
| No common chars `"abc","xyz"` | `0` | Nothing in common |

---

## Related Problems

| Problem | Link |
|---------|------|
| Longest Common Substring | Variation |
| Edit Distance | [LeetCode 72](https://leetcode.com/problems/edit-distance/) |
| Shortest Common Supersequence | [LeetCode 1092](https://leetcode.com/problems/shortest-common-supersequence/) |
