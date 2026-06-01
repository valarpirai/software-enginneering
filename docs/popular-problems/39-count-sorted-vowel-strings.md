# Count Sorted Vowel Strings

**Difficulty:** Medium | **LeetCode:** [#1641](https://leetcode.com/problems/count-sorted-vowel-strings/)

---

## Problem

Count the number of strings of length `n` that consist only of vowels (a, e, i, o, u) and are lexicographically sorted (each character ≥ the previous).

---

## Intuition

For a sorted string, you never go "back" in the alphabet. `dp[i][v]` = number of sorted strings of length `i` ending with vowel `v`. To extend: you can append the same vowel or any vowel that comes after it.

---

## Approach

**2D DP** — 5 vowels × n lengths. `dp[i][v]` = sum of `dp[i-1][v..4]` (can't use a vowel earlier in the alphabet than `v`). Optimize using suffix sums.

---

## Sample Input / Output

```
Input:  n = 2
Output: 15
Examples: "aa","ae","ai","ao","au","ee","ei","eo","eu","ii","io","iu","oo","ou","uu"
```

---

## Step-by-step Trace

Input: `n = 2`. Start: each vowel has 1 string of length 1.

| | a | e | i | o | u | total |
|---|---|---|---|---|---|-------|
| n=1 | 1 | 1 | 1 | 1 | 1 | 5 |
| n=2 | 5 | 4 | 3 | 2 | 1 | **15** |

For n=2, `a` can be followed by a/e/i/o/u (5), `e` by e/i/o/u (4), etc.

---

## Java Solution

```java
// Time: O(n)  Space: O(1)
int countVowelStrings(int n) {
    int a = 1, e = 1, i = 1, o = 1, u = 1;
    for (int step = 1; step < n; step++) {
        a = a + e + i + o + u;
        e = e + i + o + u;
        i = i + o + u;
        o = o + u;
        // u stays 1
    }
    return a + e + i + o + u;
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| `n=1` | `5` | Just the 5 vowels themselves |
| `n=33` | `66045` | Grows as C(n+4, 4) — combinations with repetition |

---

## Related Problems

| Problem | Link |
|---------|------|
| Count Sorted Vowel Strings | [LeetCode 1641](https://leetcode.com/problems/count-sorted-vowel-strings/) |
| Unique Paths | [LeetCode 62](https://leetcode.com/problems/unique-paths/) |
| Pascal's Triangle | [LeetCode 118](https://leetcode.com/problems/pascals-triangle/) |
