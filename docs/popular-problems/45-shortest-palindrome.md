# Shortest Palindrome

**Difficulty:** Hard | **LeetCode:** [#214](https://leetcode.com/problems/shortest-palindrome/)

---

## Problem

Given a string `s`, find the shortest palindrome by adding characters to the front. Return the shortest palindrome you can find by performing this transformation.

---

## Intuition

Adding to the front means you need to find the longest palindromic prefix of `s`. The characters after that prefix must be reversed and prepended. To find the longest palindromic prefix efficiently, use KMP on `s + "#" + reverse(s)`.

---

## Approach

**KMP failure function** — concatenate `s + "#" + reverse(s)`. The failure function value at the last position tells you the length of the longest palindromic prefix. Prepend the remaining suffix (reversed) to `s`.

---

## Sample Input / Output

```
Input:  s = "aacecaaa"
Output: "aaacecaaa"   (add one 'a' at front)

Input:  s = "abcd"
Output: "dcbabcd"    (add "dcb" at front)
```

---

## Step-by-step Trace

Input: `s = "abcd"`, `rev = "dcba"`, combined = `"abcd#dcba"`

KMP failure function on `"abcd#dcba"`:

| i | char | kmp[i] |
|---|------|--------|
| 0 | a | 0 |
| 1 | b | 0 |
| 2 | c | 0 |
| 3 | d | 0 |
| 4 | # | 0 |
| 5 | d | 0 |
| 6 | c | 0 |
| 7 | b | 0 |
| 8 | a | **1** |

Longest palindromic prefix = 1 (just "a"). Prepend `reverse(s[1..]) = "dcb"`.

Result: `"dcb" + "abcd" = "dcbabcd"` ✓

---

## Java Solution

```java
// Time: O(n)  Space: O(n)
String shortestPalindrome(String s) {
    String rev = new StringBuilder(s).reverse().toString();
    String combined = s + "#" + rev;
    int[] kmp = new int[combined.length()];
    for (int i = 1; i < combined.length(); i++) {
        int j = kmp[i-1];
        while (j > 0 && combined.charAt(i) != combined.charAt(j)) j = kmp[j-1];
        if (combined.charAt(i) == combined.charAt(j)) j++;
        kmp[i] = j;
    }
    int longestPalPrefix = kmp[combined.length()-1];
    return rev.substring(0, s.length() - longestPalPrefix) + s;
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| `""` | `""` | Empty string |
| `"a"` | `"a"` | Single char is already palindrome |
| `"aaa"` | `"aaa"` | Already palindrome |
| `"ab"` | `"bab"` | Add 'b' to front |

---

## Related Problems

| Problem | Link |
|---------|------|
| Longest Palindromic Substring | [LeetCode 5](https://leetcode.com/problems/longest-palindromic-substring/) |
| Palindrome Pairs | [LeetCode 336](https://leetcode.com/problems/palindrome-pairs/) |
| Implement strStr() (KMP) | [LeetCode 28](https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/) |
