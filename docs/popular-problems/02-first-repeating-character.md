# First Repeating Character

**Difficulty:** Easy | **LeetCode:** [First Unique Character #387](https://leetcode.com/problems/first-unique-character-in-a-string/)

---

## Problem

Given a string, find the first character that appears more than once. Return the character. If none exists, return `'\0'`.

---

## Intuition

Count every character's frequency in one pass. Then walk the string again from left to right. The first character with a count greater than 1 is the answer.

---

## Approach

Use a **frequency array** of size 26 (one slot per lowercase letter). Two passes: first to count, second to find the first duplicate.

---

## Sample Input / Output

```
Input:  s = "abcabd"
Output: 'a'   (first character that repeats)
```

---

## Step-by-step Trace

**Pass 1 — count frequencies:**

| char | freq array update | freq['a'-'a'] | freq['b'-'a'] | freq['c'-'a'] | freq['d'-'a'] |
|------|-------------------|---------------|---------------|---------------|---------------|
| a | freq[0]++ | 1 | 0 | 0 | 0 |
| b | freq[1]++ | 1 | 1 | 0 | 0 |
| c | freq[2]++ | 1 | 1 | 1 | 0 |
| a | freq[0]++ | 2 | 1 | 1 | 0 |
| b | freq[1]++ | 2 | 2 | 1 | 0 |
| d | freq[3]++ | 2 | 2 | 1 | 1 |

**Pass 2 — find first with freq > 1:**

| i | s[i] | freq | > 1? |
|---|------|------|------|
| 0 | 'a' | 2 | **Yes → return 'a'** |

---

## Java Solution

```java
// Time: O(n)  Space: O(1) — fixed 26-slot array
char firstRepeating(String s) {
    int[] freq = new int[26];
    for (char c : s.toCharArray()) freq[c - 'a']++;
    for (char c : s.toCharArray())
        if (freq[c - 'a'] > 1) return c;
    return '\0';
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| `"abcd"` | `'\0'` | No repeating character |
| `"aabb"` | `'a'` | First repeat is 'a', not 'b' |
| `"a"` | `'\0'` | Single character can't repeat |
| `"aa"` | `'a'` | Same character twice |

---

## Related Problems

| Problem | Link |
|---------|------|
| First Unique Character in a String | [LeetCode 387](https://leetcode.com/problems/first-unique-character-in-a-string/) |
| Find All Duplicates in an Array | [LeetCode 442](https://leetcode.com/problems/find-all-duplicates-in-an-array/) |
| Contains Duplicate | [LeetCode 217](https://leetcode.com/problems/contains-duplicate/) |
