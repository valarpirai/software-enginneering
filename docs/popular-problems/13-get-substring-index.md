# Get Substring Index

**Difficulty:** Easy | **LeetCode:** [Find the Index of the First Occurrence in a String #28](https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/)

---

## Problem

Given two strings `haystack` and `needle`, return the index of the first occurrence of `needle` in `haystack`. Return `-1` if not found.

---

## Intuition

Slide a window of size `needle.length()` across `haystack`. At each position, check if the substring matches `needle`. The first match wins.

---

## Approach

**Sliding Window with substring comparison** — O(n·m) but simple. For production, KMP runs in O(n+m) but is harder to implement.

---

## Sample Input / Output

```
Input:  haystack = "sadbutsad", needle = "sad"
Output: 0   (first occurrence at index 0)

Input:  haystack = "leetcode", needle = "leeto"
Output: -1
```

---

## Step-by-step Trace

Input: `haystack = "sadbutsad"`, `needle = "sad"` (length 3)

| i | haystack[i..i+3] | Matches "sad"? |
|---|-----------------|----------------|
| 0 | "sad" | **Yes → return 0** |

Input: `haystack = "hello"`, `needle = "ll"`

| i | haystack[i..i+2] | Matches "ll"? |
|---|-----------------|---------------|
| 0 | "he" | No |
| 1 | "el" | No |
| 2 | "ll" | **Yes → return 2** |

---

## Java Solution

```java
// Time: O(n·m)  Space: O(1)
int strStr(String haystack, String needle) {
    int n = haystack.length(), m = needle.length();
    for (int i = 0; i <= n - m; i++)
        if (haystack.substring(i, i + m).equals(needle))
            return i;
    return -1;
}
```

**Using built-in (interview shorthand):**

```java
int strStr(String haystack, String needle) {
    return haystack.indexOf(needle);
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| `haystack="a", needle=""` | `0` | Empty needle always found at 0 |
| `haystack="", needle="a"` | `-1` | Empty haystack |
| `needle.length > haystack.length` | `-1` | Needle can't fit |
| `haystack = needle` | `0` | Exact match |

---

## Related Problems

| Problem | Link |
|---------|------|
| Repeated Substring Pattern | [LeetCode 459](https://leetcode.com/problems/repeated-substring-pattern/) |
| Implement strStr() (KMP) | [LeetCode 28](https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/) |
| Longest Common Prefix | [LeetCode 14](https://leetcode.com/problems/longest-common-prefix/) |
