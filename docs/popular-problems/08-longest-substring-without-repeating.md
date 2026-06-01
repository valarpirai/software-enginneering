# Longest Substring Without Repeating Characters

**Difficulty:** Medium | **LeetCode:** [#3](https://leetcode.com/problems/longest-substring-without-repeating-characters/)

---

## Problem

Given a string, find the length of the longest substring that contains no repeating characters.

---

## Intuition

Use a sliding window. Expand the right boundary one character at a time. When a duplicate enters the window, shrink from the left until the duplicate is removed. Track the longest valid window seen.

---

## Approach

**Variable Sliding Window** — a `HashSet` tracks characters in the current window. Expand `right`. When `s[right]` is already in the set, remove from the left until it is gone.

---

## Sample Input / Output

```
Input:  s = "abcabcbb"
Output: 3   (substring "abc")
```

---

## Step-by-step Trace

Input: `s = "abcabcbb"`

| right | s[right] | in window? | left | window | max |
|-------|---------|------------|------|--------|-----|
| 0 | a | No | 0 | {a} | 1 |
| 1 | b | No | 0 | {a,b} | 2 |
| 2 | c | No | 0 | {a,b,c} | 3 |
| 3 | a | Yes → remove 'a', left=1 | 1 | {b,c,a} | 3 |
| 4 | b | Yes → remove 'b', left=2 | 2 | {c,a,b} | 3 |
| 5 | c | Yes → remove 'c', left=3 | 3 | {a,b,c} | 3 |
| 6 | b | Yes → remove 'a','b', left=5 | 5 | {c,b} | 3 |
| 7 | b | Yes → remove 'c','b', left=7 | 7 | {b} | 3 |

Return `3` ✓

---

## Java Solution

```java
// Time: O(n)  Space: O(min(n, charset))
int lengthOfLongestSubstring(String s) {
    Set<Character> window = new HashSet<>();
    int left = 0, max = 0;
    for (int right = 0; right < s.length(); right++) {
        while (window.contains(s.charAt(right)))
            window.remove(s.charAt(left++));
        window.add(s.charAt(right));
        max = Math.max(max, right - left + 1);
    }
    return max;
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| `""` | `0` | Empty string |
| `"bbbbb"` | `1` | All same character |
| `"abcde"` | `5` | No repeats — entire string |
| `" "` | `1` | Single space is a valid character |

---

## Related Problems

| Problem | Link |
|---------|------|
| Longest Substring with At Most K Distinct Characters | [LeetCode 340](https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/) |
| Minimum Window Substring | [LeetCode 76](https://leetcode.com/problems/minimum-window-substring/) |
| Fruit Into Baskets | [LeetCode 904](https://leetcode.com/problems/fruit-into-baskets/) |
