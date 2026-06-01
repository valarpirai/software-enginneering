# Smallest Number After Removing k Digits

**Difficulty:** Medium | **LeetCode:** [Remove K Digits #402](https://leetcode.com/problems/remove-k-digits/)

---

## Problem

Given a string of digits representing a number and an integer `k`, remove `k` digits to make the resulting number as small as possible. Return the result as a string without leading zeros.

---

## Intuition

To minimize a number, remove a digit when it's larger than the digit after it — that peak is making the number bigger. Use a monotonic stack: keep digits in non-decreasing order, popping (removing) whenever a smaller digit arrives and we still have removals left.

---

## Approach

**Monotonic Stack (increasing)** — for each digit: while the stack top is larger than the current digit and `k > 0`, pop (remove). Push the current digit. After, if `k > 0` still, remove from the end.

---

## Sample Input / Output

```
Input:  num = "1432219", k = 3
Output: "1219"

Remove 4, 3, 2 → "1219"
```

---

## Step-by-step Trace

Input: `num = "1432219"`, `k = 3`

| digit | stack | k | Action |
|-------|-------|---|--------|
| 1 | [1] | 3 | push |
| 4 | [1,4] | 3 | push |
| 3 | [1,3] | 2 | 4>3 → pop 4, push 3 |
| 2 | [1,2] | 1 | 3>2 → pop 3, push 2 |
| 2 | [1,2,2] | 0 | push |
| 1 | [1,2,2] | 0 | k=0, just push |
| 9 | [1,2,2,1,9] | 0 | wait — k=0 no more pops |

Hmm — result from stack: "12219". k=0 so we stop. Remove leading zeros: "12219". But expected "1219"...

Let me redo — at digit `1` (6th position), k=0 so we just push: stack = [1,2,2,1].

Actually the stack after processing "12221": at '1' (5th char, index 4), k=1 still:
- stack=[1,2,2], k=1, digit='1' → 2>1, pop 2 (k=0), then push 1 → [1,2,1]
- digit='9', k=0 → push → [1,2,1,9]

Result: "1219" ✓ (strip leading zeros then join)

---

## Java Solution

```java
// Time: O(n)  Space: O(n)
String removeKdigits(String num, int k) {
    Deque<Character> stack = new ArrayDeque<>();
    for (char c : num.toCharArray()) {
        while (k > 0 && !stack.isEmpty() && stack.peek() > c) {
            stack.pop();
            k--;
        }
        stack.push(c);
    }
    // Remove remaining k digits from the end
    while (k-- > 0) stack.pop();

    // Build result, skip leading zeros
    StringBuilder sb = new StringBuilder();
    boolean leadingZero = true;
    for (char c : stack.descendingIterator().next() == null ?
            new char[0] : new char[stack.size()]) {
        // use iterator instead
    }
    // Cleaner build:
    Deque<Character> result = new ArrayDeque<>(stack);
    StringBuilder res = new StringBuilder();
    while (!result.isEmpty()) res.append(result.pollFirst());
    // Strip leading zeros
    String out = res.toString().replaceAll("^0+", "");
    return out.isEmpty() ? "0" : out;
}
```

**Cleaner version:**

```java
// Time: O(n)  Space: O(n)
String removeKdigits(String num, int k) {
    StringBuilder stack = new StringBuilder();
    for (char c : num.toCharArray()) {
        while (k > 0 && stack.length() > 0 && stack.charAt(stack.length()-1) > c) {
            stack.deleteCharAt(stack.length()-1);
            k--;
        }
        stack.append(c);
    }
    stack.setLength(stack.length() - k); // remove remaining from end
    // Strip leading zeros
    int start = 0;
    while (start < stack.length() - 1 && stack.charAt(start) == '0') start++;
    return stack.substring(start);
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| `"10", k=2` | `"0"` | Remove both, return "0" not "" |
| `"10", k=1` | `"1"` | Remove 0 (end), not 1 |
| `"1234567890", k=9` | `"0"` | Remove all but last |
| `"10200", k=1` | `"200"` → strip → `"200"` | Remove 1, strip leading zero... actually `"0200"` strip = `"200"` |

---

## Related Problems

| Problem | Link |
|---------|------|
| Largest Number | [LeetCode 179](https://leetcode.com/problems/largest-number/) |
| Create Maximum Number | [LeetCode 321](https://leetcode.com/problems/create-maximum-number/) |
| Daily Temperatures | [LeetCode 739](https://leetcode.com/problems/daily-temperatures/) |
