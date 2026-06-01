# Longest Possible Palindrome

**Difficulty:** Medium | **LeetCode:** [Longest Palindromic Substring #5](https://leetcode.com/problems/longest-palindromic-substring/)

---

## Problem

Given a string, return the longest substring that is a palindrome.

---

## Intuition

Every palindrome expands from its center. For a string of length n there are `2n - 1` possible centers (each character, and each gap between characters). Expand outward from each center as long as the characters match.

---

## Approach

**Expand Around Center** — for each center, expand left and right while characters match. Track the longest palindrome found. Two passes per center: one for odd-length palindromes, one for even-length.

---

## Sample Input / Output

```
Input:  s = "babad"
Output: "bab"  (or "aba" — both valid)

Input:  s = "cbbd"
Output: "bb"
```

---

## Step-by-step Trace

Input: `s = "babad"`, checking center at index 1 ('a'):

| Expand | left | right | s[l] | s[r] | Match? | Palindrome |
|--------|------|-------|------|------|--------|------------|
| start | 1 | 1 | a | a | Yes | "a" |
| +1 | 0 | 2 | b | b | Yes | "bab" |
| +2 | -1 | 3 | — | — | out of bounds | stop |

Best at this center: `"bab"` (length 3)

Checking center between index 1 and 2 ('a','b'):

| left | right | s[l] | s[r] | Match? |
|------|-------|------|------|--------|
| 1 | 2 | a | b | No → stop immediately |

Final answer: `"bab"` ✓

---

## Java Solution

```java
// Time: O(n²)  Space: O(1)
String longestPalindrome(String s) {
    int start = 0, maxLen = 1;
    for (int i = 0; i < s.length(); i++) {
        // Odd-length palindromes
        int len1 = expand(s, i, i);
        // Even-length palindromes
        int len2 = expand(s, i, i + 1);
        int len = Math.max(len1, len2);
        if (len > maxLen) {
            maxLen = len;
            start = i - (len - 1) / 2;
        }
    }
    return s.substring(start, start + maxLen);
}

int expand(String s, int left, int right) {
    while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
        left--;
        right++;
    }
    return right - left - 1;
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| `"a"` | `"a"` | Single character |
| `"ac"` | `"a"` or `"c"` | No palindrome longer than 1 |
| `"racecar"` | `"racecar"` | Entire string is palindrome |
| `"aacabdkacaa"` | `"aca"` | Multiple candidates |

---

## Related Problems

| Problem | Link |
|---------|------|
| Palindromic Substrings | [LeetCode 647](https://leetcode.com/problems/palindromic-substrings/) |
| Longest Palindromic Subsequence | [LeetCode 516](https://leetcode.com/problems/longest-palindromic-subsequence/) |
| Valid Palindrome | [LeetCode 125](https://leetcode.com/problems/valid-palindrome/) |
