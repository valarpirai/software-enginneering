# Valid Brackets

**Difficulty:** Easy | **LeetCode:** [Valid Parentheses #20](https://leetcode.com/problems/valid-parentheses/)

---

## Problem

Given a string of brackets `()[]{}`, determine if the brackets are valid. Every open bracket must be closed by the same type in the correct order.

---

## Intuition

Use a stack. Push every opening bracket. When you see a closing bracket, the top of the stack must be the matching opener. If it is, pop it. If not — or the stack is empty — invalid. At the end, a valid string leaves the stack empty.

---

## Approach

**Stack** — push openers, pop and match on closers. An empty stack at the end means all brackets are matched.

---

## Sample Input / Output

```
Input:  s = "({[]})"
Output: true

Input:  s = "([)]"
Output: false
```

---

## Step-by-step Trace

Input: `s = "({[]})"`

| i | char | Action | Stack |
|---|------|--------|-------|
| 0 | `(` | push | [`(`] |
| 1 | `{` | push | [`(`, `{`] |
| 2 | `[` | push | [`(`, `{`, `[`] |
| 3 | `]` | top=`[` matches → pop | [`(`, `{`] |
| 4 | `}` | top=`{` matches → pop | [`(`] |
| 5 | `)` | top=`(` matches → pop | [] |

Stack empty → return `true` ✓

---

## Java Solution

```java
// Time: O(n)  Space: O(n)
boolean isValid(String s) {
    Deque<Character> stack = new ArrayDeque<>();
    for (char c : s.toCharArray()) {
        if (c == '(' || c == '[' || c == '{') {
            stack.push(c);
        } else {
            if (stack.isEmpty()) return false;
            char top = stack.pop();
            if (c == ')' && top != '(') return false;
            if (c == ']' && top != '[') return false;
            if (c == '}' && top != '{') return false;
        }
    }
    return stack.isEmpty();
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| `""` | `true` | Empty string is valid |
| `"("` | `false` | Unclosed bracket |
| `")"` | `false` | Closer with empty stack |
| `"()[]{}"` | `true` | All types, sequential |
| `"([)]"` | `false` | Wrong nesting order |

---

## Related Problems

| Problem | Link |
|---------|------|
| Min Stack | [LeetCode 155](https://leetcode.com/problems/min-stack/) |
| Longest Valid Parentheses | [LeetCode 32](https://leetcode.com/problems/longest-valid-parentheses/) |
| Generate Parentheses | [LeetCode 22](https://leetcode.com/problems/generate-parentheses/) |
