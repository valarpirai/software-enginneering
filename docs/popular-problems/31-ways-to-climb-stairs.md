# Ways to Climb Stairs

**Difficulty:** Easy | **LeetCode:** [Climbing Stairs #70](https://leetcode.com/problems/climbing-stairs/)

---

## Problem

You are climbing a staircase with `n` steps. Each time you can climb 1 or 2 steps. How many distinct ways can you reach the top?

---

## Intuition

To reach step `n`, you came from step `n-1` (one step) or step `n-2` (two steps). The total ways to reach `n` is the sum of ways to reach `n-1` and `n-2`. This is exactly the Fibonacci sequence.

---

## Approach

**DP (Fibonacci pattern)** — only the last two values matter. Use two variables instead of an array.

---

## Sample Input / Output

```
Input:  n = 5
Output: 8

Ways: [1,1,1,1,1], [1,1,1,2], [1,1,2,1], [1,2,1,1], [2,1,1,1],
      [1,2,2], [2,1,2], [2,2,1]
```

---

## Step-by-step Trace

Input: `n = 5`

| step | ways |
|------|------|
| 1 | 1 |
| 2 | 2 |
| 3 | 1+2=3 |
| 4 | 2+3=5 |
| 5 | 3+5=**8** |

---

## Java Solution

```java
// Time: O(n)  Space: O(1)
int climbStairs(int n) {
    if (n <= 2) return n;
    int prev2 = 1, prev1 = 2;
    for (int i = 3; i <= n; i++) {
        int curr = prev1 + prev2;
        prev2 = prev1;
        prev1 = curr;
    }
    return prev1;
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| `n=1` | `1` | Only one way: [1] |
| `n=2` | `2` | [1,1] or [2] |
| `n=3` | `3` | [1,1,1], [1,2], [2,1] |

---

## Related Problems

| Problem | Link |
|---------|------|
| Min Cost Climbing Stairs | [LeetCode 746](https://leetcode.com/problems/min-cost-climbing-stairs/) |
| Fibonacci Number | [LeetCode 509](https://leetcode.com/problems/fibonacci-number/) |
| Jump Game | [LeetCode 55](https://leetcode.com/problems/jump-game/) |
