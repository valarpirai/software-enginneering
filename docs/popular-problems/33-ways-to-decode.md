# Ways to Decode

**Difficulty:** Medium | **LeetCode:** [Decode Ways #91](https://leetcode.com/problems/decode-ways/)

---

## Problem

A string of digits encodes letters where `'1'→A`, `'2'→B`, ..., `'26'→Z`. Given an encoded string, return the number of ways to decode it.

---

## Intuition

At each position, you can decode one digit (if valid: 1–9) or two digits (if valid: 10–26). The total ways at position `i` is the sum of ways when you took one digit and ways when you took two digits.

---

## Approach

**1D DP** — `dp[i]` = number of ways to decode `s[0..i-1]`. Walk forward, adding contributions from valid one-digit and two-digit decodings.

---

## Sample Input / Output

```
Input:  s = "226"
Output: 3
Ways: "2 2 6" (BBF), "22 6" (VF), "2 26" (BZ)

Input:  s = "06"
Output: 0   (leading zero is invalid)
```

---

## Step-by-step Trace

Input: `s = "226"`

| i | s[i] | one digit valid? | two digit valid? | dp[i] |
|---|------|-----------------|-----------------|-------|
| 0 | — | — | — | dp[0]=1 (empty) |
| 1 | '2' | Yes (2≠0) | — | dp[1]=1 |
| 2 | '2' | Yes | "22"→22≤26 Yes | dp[2]=dp[1]+dp[0]=2 |
| 3 | '6' | Yes | "26"→26≤26 Yes | dp[3]=dp[2]+dp[1]=**3** |

---

## Java Solution

```java
// Time: O(n)  Space: O(n)
int numDecodings(String s) {
    int n = s.length();
    int[] dp = new int[n + 1];
    dp[0] = 1; // empty string: one way
    dp[1] = s.charAt(0) == '0' ? 0 : 1;

    for (int i = 2; i <= n; i++) {
        int one = Integer.parseInt(s.substring(i-1, i));
        int two = Integer.parseInt(s.substring(i-2, i));
        if (one >= 1)            dp[i] += dp[i-1];
        if (two >= 10 && two <= 26) dp[i] += dp[i-2];
    }
    return dp[n];
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| `"0"` | `0` | '0' alone is invalid |
| `"10"` | `1` | Only "10"→J |
| `"100"` | `0` | "10" + "0" is invalid |
| `"27"` | `1` | Only "2"+"7" (27 > 26) |

---

## Related Problems

| Problem | Link |
|---------|------|
| Decode Ways II | [LeetCode 639](https://leetcode.com/problems/decode-ways-ii/) |
| Climbing Stairs | [LeetCode 70](https://leetcode.com/problems/climbing-stairs/) |
| Restore IP Addresses | [LeetCode 93](https://leetcode.com/problems/restore-ip-addresses/) |
